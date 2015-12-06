package com.example.administraor.cloudnote.activity.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administraor.cloudnote.R;
import com.example.administraor.cloudnote.activity.activity.adapter.RecyclerViewAdapter;
import com.melnykov.fab.FloatingActionButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administraor on 2015/12/6.
 */
public class NoteFragment extends Fragment {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.addNoteBtn)
    FloatingActionButton mAddNoteBtn;
    private RecyclerViewAdapter mRecAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, null);
        mRecyclerView.setHasFixedSize(true);          //adapter的改变不能影响recyclerview
        mLayoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecAdapter);
        mRecAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //这里添加点击事件处理代码，当然是打开编辑界面
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //这里添加长按事件处理代码，当然是删除
            }
        });

        ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
