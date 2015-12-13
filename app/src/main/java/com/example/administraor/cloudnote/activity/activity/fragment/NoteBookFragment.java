package com.example.administraor.cloudnote.activity.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administraor.cloudnote.R;
import com.example.administraor.cloudnote.activity.activity.adapter.NoteBookAdapter;
import com.example.administraor.cloudnote.activity.activity.model.NoteBook;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administraor on 2015/12/9.
 */
public class NoteBookFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.bookRecView)
    RecyclerView bookRecView;
    @Bind(R.id.addBookBtn)
    FloatingActionButton addBookBtn;
    private NoteBookAdapter mBookAdapter;
    ArrayList<NoteBook> bookList;

    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notebook, null);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        bookList = (ArrayList<NoteBook>) bundle.getSerializable("bookList");
        bookRecView.setHasFixedSize(true);          //adapter的改变不能影响recyclerview
        mLayoutManager = new LinearLayoutManager(getActivity());
        bookRecView.setLayoutManager(mLayoutManager);
        mBookAdapter = new NoteBookAdapter(getActivity(),bookList);
        bookRecView.setAdapter(mBookAdapter);
        mBookAdapter.setOnItemClickListener(new NoteBookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //这里添加点击事件处理代码，当然是打开编辑界面
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //这里添加长按事件处理代码，当然是删除
            }
        });
        addBookBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    public static NoteBookFragment newInstance(ArrayList<NoteBook> bookList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("bookList", bookList);
        NoteBookFragment noteBookFragment = new NoteBookFragment();
        noteBookFragment.setArguments(bundle);
        return noteBookFragment;
    }


    @Override
    public void onClick(View v) {

    }
}
