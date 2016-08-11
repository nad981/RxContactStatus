package com.example.abhinav.akproj;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {


    public ContactsFragment() {
        // Required empty public constructor
    }


        ArrayAdapter mContactviewAdapter;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            String[] listdata = {
                    "Jhon",
                    "David",
                    "Mary"
            };

            Log.e("Error","I am before error");

            List<String> contactlist = new ArrayList<String>(Arrays.asList(listdata));
            View rootview = inflater.inflate(R.layout.fragment_contacts,container,false);

            mContactviewAdapter = new ArrayAdapter<String>(getContext(),R.layout.contacts_list,R.id.list_contacts,contactlist);


            ListView listView = (ListView) rootview.findViewById(R.id.contactlist);
            listView.setAdapter(mContactviewAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String contact_details = (String) mContactviewAdapter.getItem(i);

                    Intent contactintent = new Intent(getActivity(),Compose.class);
                    contactintent.putExtra("DETAILS",contact_details);
                    startActivity(contactintent);
                }
            });


            return rootview;
        }

}
