package com.example.administraor.cloudnote.activity.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.administraor.cloudnote.R;
import com.example.administraor.cloudnote.activity.activity.model.NoteBook;

import java.util.ArrayList;

/**
 * Created by Administraor on 2015/12/12.
 */
public class SpinnerListAdapter extends BaseAdapter implements SpinnerAdapter{
    private ArrayList<NoteBook> noteBooks;
    private Context context;

    public SpinnerListAdapter(ArrayList<NoteBook> noteBooks,Context context) {
        this.noteBooks=noteBooks;
        this.context=context;
    }

    @Override
    public int getCount() {
        return noteBooks.size();
    }

    @Override
    public Object getItem(int position) {
        return noteBooks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.spinner_item,null);
        if(convertView!=null){
            TextView textView=(TextView)convertView.findViewById(R.id.spinnerItem);
            textView.setText(noteBooks.get(position).getTitle());
        }
        return convertView;
    }
}
