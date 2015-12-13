package com.example.administraor.cloudnote.activity.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administraor.cloudnote.R;
import com.example.administraor.cloudnote.activity.activity.model.NoteBook;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administraor on 2015/12/9.
 */
public class NoteBookAdapter extends RecyclerView.Adapter<NoteBookAdapter.ViewHolder> {


    private Context context;
    private ArrayList<NoteBook> noteBooks;


    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }


    @Override
    public int getItemCount() {
        return noteBooks.size();
    }

    public NoteBookAdapter(Context context, ArrayList<NoteBook> noteBooks) {
        this.context = context;
        this.noteBooks = noteBooks;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notebook_view, parent, false);
        ViewHolder recViewHolder = new ViewHolder(v);
        return recViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bookTitleText.setText(noteBooks.get(position).getTitle());

        //因为设置了回调，所以要在这类设置点击事件
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });

        }

    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'notebook_view.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.notebook)
        ImageView notebook;
        @Bind(R.id.bookTitleText)
        TextView bookTitleText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
