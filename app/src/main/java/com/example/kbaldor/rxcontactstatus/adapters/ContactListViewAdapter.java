package com.example.kbaldor.rxcontactstatus.adapters;

/**
 * Created by Prasanthi on 8/6/2016.
 */
//java class that holds the contacts in adapter view on the main activity
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kbaldor.rxcontactstatus.R;

import java.util.ArrayList;

public class ContactListViewAdapter extends BaseAdapter {



    ArrayList<UserInfo> marrList;
    ArrayList<String> contactNames;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private Object object;



    public int getCount() {
        synchronized (object) {
            return marrList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        synchronized (object) {
            return marrList.get(position);
        }
    }
    public ContactListViewAdapter(Context context, ArrayList listData, ArrayList contactNames, Object object) {
        this.contactNames = contactNames;
        this.object = object;
        this.marrList = listData;
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder {
        protected TextView txtContactName;
        protected ImageView deleteIcon;
        protected ImageView onlineStatus;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.row_contact, null);

            holder = new ViewHolder();
            holder.txtContactName = (TextView) convertView.findViewById(R.id.txtContactName);
            holder.onlineStatus = (ImageView) convertView.findViewById(R.id.statusIcon);

            holder.deleteIcon = (ImageView) convertView.findViewById(R.id.deleteIcon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        synchronized (object) {

            final UserInfo userInfo = marrList.get(position);
            holder.txtContactName.setText(userInfo.userName);

            holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contactNames.remove(userInfo.getUserName());
                    marrList.remove(position);
                    notifyDataSetChanged();
                }
            });

            if(userInfo.isOnline == true) {
                //holder.onlineStatus.setVisibility(View.VISIBLE);
                holder.onlineStatus.setImageResource(R.drawable.online);
            } else {
                //holder.onlineStatus.setVisibility(View.INVISIBLE);
                holder.onlineStatus.setImageResource(R.drawable.offline);
            }
        }

        return convertView;
    }

    //row datas to viewed in a line recored

}
