package com.example.administraor.cloudnote.activity.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.LogUtil;
import com.example.administraor.cloudnote.R;
import com.example.administraor.cloudnote.activity.activity.activity.editNoteActivity;
import com.example.administraor.cloudnote.activity.activity.adapter.RecyclerViewAdapter;
import com.example.administraor.cloudnote.activity.activity.db.NoteDB;
import com.example.administraor.cloudnote.activity.activity.model.Note;
import com.example.administraor.cloudnote.activity.activity.utils.dbUtil;
import com.melnykov.fab.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administraor on 2015/12/6.
 */
public class NoteFragment extends Fragment implements View.OnClickListener{
    String TAG="NoteFragment";
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.addNoteBtn)
    FloatingActionButton mAddNoteBtn;
    private RecyclerViewAdapter mRecAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Note> noteList;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, null);
        ButterKnife.bind(this, view);
        Bundle bundle=getArguments();
        noteList=(ArrayList<Note>)bundle.getSerializable("noteList");
        mRecyclerView.setHasFixedSize(true);          //adapter的改变不能影响recyclerview
        mLayoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecAdapter=new RecyclerViewAdapter(noteList,getActivity());
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
        mAddNoteBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static NoteFragment newInstance(ArrayList<Note> noteList){
        Bundle bundle=new Bundle();
        bundle.putSerializable("noteList", noteList);
        NoteFragment noteFragment=new NoteFragment();
        noteFragment.setArguments(bundle);
        return noteFragment;
    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(),editNoteActivity.class);
        startActivity(intent);
    }
}
