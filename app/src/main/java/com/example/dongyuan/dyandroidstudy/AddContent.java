package com.example.dongyuan.dyandroidstudy;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dongyuan on 2017/1/6.
 */

public class AddContent extends Activity implements View.OnClickListener{
    private String addtype;
    private Button savebtn, cancelbtn;
    private EditText ettext;
    private ImageView c_img;
    private VideoView c_video;
    private NotesDB notesdb;
    private SQLiteDatabase dbWriter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);
        addtype = getIntent().getStringExtra("flag");

        savebtn = (Button) findViewById(R.id.c_save);
        savebtn.setOnClickListener(this);
        cancelbtn = (Button) findViewById(R.id.c_cancel);
        cancelbtn.setOnClickListener(this);
        ettext = (EditText) findViewById(R.id.c_ettext);
        c_img = (ImageView) findViewById(R.id.c_img);
        c_video = (VideoView) findViewById(R.id.c_video);

        notesdb = new NotesDB(this);
        dbWriter = notesdb.getWritableDatabase();

    }

    public void addDB(){
        ContentValues cv = new ContentValues();
        cv.put("content", ettext.getText().toString());
        cv.put("time", getTime());

        dbWriter.insert("notes", null, cv);
    }

    private String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String str = format.format(date);
        return str;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.c_save:
                addDB();
                finish();
                break;
            case R.id.c_cancel:
                finish();
                break;
        }
    }
}
