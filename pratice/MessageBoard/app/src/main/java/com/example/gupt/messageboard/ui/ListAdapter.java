package com.example.gupt.messageboard.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

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

            TextView titleView  = (TextView) convertView.findViewById(R.id.item_title);
            TextView authorview = (TextView) convertView.findViewById(R.id.item_author);
            TextView timeview   = (TextView) convertView.findViewById(R.id.item_time);

            Messege msg = msgs.get(position);
            titleView.setText(msg.getTitle());
            authorview.setText(msg.getAuthor());
            timeview.setText(msg.getTime());
            convertView.setTag((Object)msg);
            convertView.setClickable(true);
            final View finalConvertView = convertView;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Messege msg = (Messege) finalConvertView.getTag();
                    Toast.makeText(context, Integer.toString(msg.getId())
                            , Toast.LENGTH_SHORT).show();

                }
            });
        }
        return convertView;
    }
}
