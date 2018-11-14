package com.example.gupt.messageboard.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gupt.messageboard.R;
import com.example.gupt.messageboard.model.Messege;

import java.util.List;

/**
 * Created by Kong on 2018/11/14 0014.
 */

public class ListAdapter extends BaseAdapter {
    private Context       context;
    private List<Messege> msgs;
    private LayoutInflater layoutInflater;

    public ListAdapter(Context context, List<Messege> msgs) {
        this.context   = context;
        this.msgs      = msgs;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return msgs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            //convertView.set
            //convertView.setClickable(true);
            //TextView titleView = convertView.findViewById(R.id.item_title);
            //TextView authorview = convertView.findViewById(R.id.item_);
            //TextView titleView = convertView.findViewById(R.id.item_title);

        }
        return convertView;
    }
}
