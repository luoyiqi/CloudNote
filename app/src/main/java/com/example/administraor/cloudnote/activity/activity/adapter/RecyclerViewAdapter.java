package com.example.administraor.cloudnote.activity.activity.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVFile;
import com.example.administraor.cloudnote.R;
import com.example.administraor.cloudnote.activity.activity.model.Note;
import com.example.administraor.cloudnote.activity.activity.utils.BitmapChange;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 *
 *
 * Created by Administraor on 2015/12/3.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Note> notes;
    private Context context;

    //接下来的代码是为Recyclerview设置点击监听，不然他就只有一个OnItemTouchListener，不好用
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }




    public RecyclerViewAdapter(ArrayList<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);
        ViewHolder recViewHolder = new ViewHolder(v);
        return recViewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.titleText.setText(notes.get(position).getTitle());
        //把每一个note的第一段话作为contentText，便于显示
        holder.contentText.setText(notes.get(position).getContents().get(1));
        AVFile imageFile=notes.get(position).getPhotos().get(0);
        String url=imageFile.getThumbnailUrl(false, 100, 100);
        Bitmap bitmap= BitmapChange.getHttpBitmap(url);
        holder.contentImage.setImageBitmap(bitmap);

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


    @Override
    public int getItemCount() {
        return notes.size();
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'card_view.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.titleText)
        TextView titleText;
        @Bind(R.id.contentText)
        TextView contentText;
        @Bind(R.id.contentImage)
        ImageView contentImage;
        @Bind(R.id.card_view)
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }

        @Override
        public void onClick(View v) {
            //<NEED>这里要加入cardview被点击之后的事件，肯定是要打开笔记编辑的页面。
        }
    }
}
