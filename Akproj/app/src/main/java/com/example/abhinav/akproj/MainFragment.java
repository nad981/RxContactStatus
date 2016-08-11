package com.example.abhinav.akproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainFragment extends Fragment {

    ArrayAdapter mMessageviewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String[] listdata = {
                "Jhon | how r u?",
                "David | Where r u?",
                "Mary | I am here!"
        };

        List<String> contactlist = new ArrayList<String>(Arrays.asList(listdata));
        View rootview = inflater.inflate(R.layout.fragment_main,container,false);

        mMessageviewAdapter = new ArrayAdapter<String>(getContext(),R.layout.message_list,R.id.list_messages,contactlist);


        ListView listView = (ListView) rootview.findViewById(R.id.messagelist);
        listView.setAdapter(mMessageviewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String contact_details = (String) mMessageviewAdapter.getItem(i);

                Intent detailintent = new Intent(getActivity(),Read.class);
                detailintent.putExtra("DETAILS",contact_details);
                startActivity(detailintent);
            }
        });

        return rootview;
    }
}
