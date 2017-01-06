package com.example.dongyuan.dyandroidstudy;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;


import java.io.File;
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
    private File phonefile;


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

        initview();
    }

    public void initview(){
        if(addtype.equals("1")){
            c_img.setVisibility(View.GONE);
            c_video.setVisibility(View.GONE);
        }
        if(addtype.equals("2")){
            c_img.setVisibility(View.VISIBLE);
            c_video.setVisibility(View.GONE);

            Intent iimg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            phonefile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/"+getTime()+".jpg");
            iimg.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(phonefile));
            startActivityForResult(iimg, 1);
        }
        if(addtype.equals("3")){
            c_img.setVisibility(View.GONE);
            c_video.setVisibility(View.VISIBLE);
        }
    }


    public void addDB(){
        ContentValues cv = new ContentValues();
        cv.put("content", ettext.getText().toString());
        cv.put("time", getTime());
        cv.put("img", phonefile+"");

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1){
            Bitmap bitmap = BitmapFactory.decodeFile(phonefile.getAbsolutePath());

            c_img.setImageBitmap(bitmap);
        }
    }
}
