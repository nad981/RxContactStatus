package com.example.kbaldor.rxcontactstatus;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.example.kbaldor.rxcontactstatus.adapters.ContactListViewAdapter;
import com.example.kbaldor.rxcontactstatus.adapters.UserInfo;
import com.example.kbaldor.rxcontactstatus.stages.GetChallengeStage;
import com.example.kbaldor.rxcontactstatus.stages.GetServerKeyStage;
import com.example.kbaldor.rxcontactstatus.stages.LogInStage;
import com.example.kbaldor.rxcontactstatus.stages.RegisterContactsStage;
import com.example.kbaldor.rxcontactstatus.stages.RegistrationStage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.schedulers.TimeInterval;

public class MainActivity extends AppCompatActivity {

    Crypto myCrypto;

    String username = "RxContactsDemo";
    String server_name = "http://129.115.27.54:25666";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        object = new Object();

        contacts = new ArrayList<>();
        //adding two users alice and bob
        //need to find the available status for bob and alice
        contacts.add(new UserInfo("alice", false));
        contacts.add(new UserInfo("bob", false));

        contactNames = new ArrayList<>();
        for(UserInfo userInfo : contacts) {
            contactNames.add(userInfo.getUserName());
        }

        adapter = new ContactListViewAdapter(this, contacts, contactNames, mutex);
        initUI();

        myCrypto = new Crypto(getPreferences(Context.MODE_PRIVATE));

        Observable.just(0) // the value doesn't matter, it just kicks things off
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .flatMap(new GetServerKeyStage(server_name))
                .flatMap(new RegistrationStage(server_name, username,
                                               getBase64Image(), myCrypto.getPublicKeyString()))
                .flatMap(new GetChallengeStage(server_name,username,myCrypto))
                .flatMap(new LogInStage(server_name, username))
                .flatMap(new RegisterContactsStage(server_name, username, contacts))
                .subscribe(new Observer<Notification>() {
            @Override
            public void onCompleted() {

                // now that we have the initial state, start polling for updates

                Observable.interval(0,1, TimeUnit.SECONDS, Schedulers.newThread())
                        .subscribeOn(AndroidSchedulers.mainThread())
                     //   .take(5) // would only poll five times
                     //   .takeWhile( <predicate> ) // could stop based on a flag variable
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onCompleted() {}

                            @Override
                            public void onError(Throwable e) {}

                            @Override
                            public void onNext(Long numTicks) {
                                Log.d("POLL","Polling "+numTicks);
                            }
                        });
            }

            @Override
            public void onError(Throwable e) {
                Log.d("LOG","Error: ",e);
            }

            @Override
            public void onNext(Notification notification) {
                // handle initial state here
                Log.d("LOG","Next "+ notification);
                if(notification instanceof Notification.LogIn) {
                    Log.d("LOG","User "+((Notification.LogIn)notification).username+" is logged in");
                }
                if(notification instanceof Notification.LogOut) {
                    Log.d("LOG","User "+((Notification.LogOut)notification).username+" is logged out");
                }
            }
        });

    }



    //Checking for users status - login and logout
    // creating a json object to get the status
    private void notifications(ArrayList<String> contactNames) {
        try {
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("people", new JSONArray(contactNames));
            JSONObject response = WebHelper.JSONPut(server_name + "/register-people", json);

            JSONObject status = response.getJSONObject("people-status-map");

            for (String contact : contactNames) {
                Log.d("POLL RESULTS ", contact + " : " + status.getString(contact));
            }

            synchronized (obect) {
                for (UserInfo userInfo : contacts) {
                    String onlineStatus = status.getString(userInfo.getUserName());

                    if (onlineStatus == null) {
                        Log.d("UPDATE CONTACTS STATUS ", "Can't get status for <" + userInfo.getUserName() + ">");
                    } else {
                        if (onlineStatus.equals("logged-in")) {
                            userInfo.setIsOnline(true);
                        } else {
                            userInfo.setIsOnline(false);
                        }
                    }
                }
            }

            // Update changes in ListView
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    String getBase64Image(){
        InputStream is;
        byte[] buffer = new byte[0];
        try {
            is = getAssets().open("images/ic_android_black_24dp.png");
            buffer = new byte[is.available()];
            is.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(buffer,Base64.DEFAULT).trim();
    }

    boolean register(String username, String base64Image, String keyString){


        return true;
    }


}
}
