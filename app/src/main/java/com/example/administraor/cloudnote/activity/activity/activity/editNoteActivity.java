package com.example.administraor.cloudnote.activity.activity.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administraor.cloudnote.R;
import com.example.administraor.cloudnote.activity.activity.db.NoteDB;
import com.example.administraor.cloudnote.activity.activity.model.NoteBook;
import com.example.administraor.cloudnote.activity.activity.utils.dbUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class editNoteActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    String TAG = "editNoteActivitya";
    @Bind(R.id.noteBar)
    Toolbar noteBar;
    @Bind(R.id.include_noteBar)
    AppBarLayout includeNoteBar;
    @Bind(R.id.titleEdit)
    MaterialEditText titleEdit;
    @Bind(R.id.contentEdit)
    MaterialEditText contentEdit;
    @Bind(R.id.spinner)
    Spinner spinner;
    private NoteDB noteDB;
    private ArrayList<NoteBook> bookList;
    private dbUtil dbUtil;
    private String noteInBook;
    private String noteTitle;
    private String noteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_edit_note);
        ButterKnife.bind(this);
        //设置notebar
        noteBar.setTitle("note");
        setSupportActionBar(noteBar);
        noteBar.setNavigationIcon(R.drawable.back1);
        noteBar.setOnMenuItemClickListener(this);
        noteBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        noteDB = NoteDB.getInstance(this);
        dbUtil = new dbUtil();
        bookList = dbUtil.getBooks(noteDB);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        for (int i = 0; i < bookList.size(); i++) {
            String title = bookList.get(i).getTitle();
            adapter.add(title);
        }
        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                noteInBook = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                noteInBook = parent.getItemAtPosition(0).toString();
            }
        });


    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.note_save:
                noteTitle=titleEdit.getText().toString();
                noteContent=contentEdit.getText().toString();
                if(noteTitle==null){
                    Toast.makeText(this,"title can not be null!",Toast.LENGTH_SHORT).show();
                }
                if(noteContent==null){
                    Toast.makeText(this,"content can not be null!",Toast.LENGTH_SHORT).show();
                }
                dbUtil dbUtil=new dbUtil();
                dbUtil.insertNote(noteDB,noteInBook,noteTitle,noteContent);






                break;
            case R.id.add_photo:
                Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
                getImage.addCategory(Intent.CATEGORY_OPENABLE);
                getImage.setType("image/*");
                startActivityForResult(getImage, 1);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"执行到c来了");
        ContentResolver resolver=getContentResolver();
        if(resultCode==RESULT_OK && data!=null){
            if(requestCode==1){
                Uri originalUri=data.getData();
                Log.d(TAG, "这是uri"+originalUri);
                Log.d(TAG, "这是data"+data.toString());
                try{
                    Bitmap originalBitmap= BitmapFactory.decodeStream(resolver.openInputStream(originalUri));
                    Log.d(TAG, "这是初始map"+originalBitmap.toString());
                    Bitmap bitmap=Bitmap.createScaledBitmap(originalBitmap,300,300,true);
                    Log.d(TAG, "这是变化后的"+bitmap.toString());
                    if(bitmap!=null){
                        insertToEditText(getBitmapMime(bitmap,originalUri),contentEdit);
                        Log.d(TAG, bitmap.toString());
                    }else{
                        Toast.makeText(this,"获取图片失败",Toast.LENGTH_SHORT).show();
                    }
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }


            }
        }






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notebar_menu, menu);
        return true;
    }


     private SpannableString getBitmapMime(Bitmap bit,Uri uri){
         String path=uri.getPath();
         Log.d(TAG, "这是uri"+uri);
         Log.d(TAG, "这是path"+path);
         SpannableString ss=new SpannableString(path);
         ImageSpan span=new ImageSpan(this,bit);
         ss.setSpan(span, 0, path.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
         return ss;
     }

     private void insertToEditText(SpannableString ss,MaterialEditText editText){
         Editable et=editText.getText();
         int start=editText.getSelectionStart();
         Log.d(TAG, "这是start" + start);
         if(start <0 || start >= editText.length()){
             et.append(ss);
         }else{
             et.insert(start,ss);
         }
         editText.setText(et);
         editText.setSelection(start + ss.length());  //设置光标在插入的图片后面
         Log.d(TAG,editText.getText()+"");
     }


}
