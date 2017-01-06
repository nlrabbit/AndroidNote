package com.example.dongyuan.dyandroidstudy;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by dongyuan on 2017/1/6.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private Cursor cursor;
    private LinearLayout layout;


    public MyAdapter( Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        layout = (LinearLayout) inflater.inflate(R.layout.cell, null);
        TextView contenttv = (TextView) layout.findViewById(R.id.list_content);
        TextView timetv = (TextView) layout.findViewById(R.id.list_time);
        ImageView imgiv = (ImageView) layout.findViewById(R.id.list_img);
        ImageView videoiv = (ImageView) layout.findViewById(R.id.list_video);

        cursor.moveToPosition(position);
        String content = cursor.getString(cursor.getColumnIndex("content"));
        String time = cursor.getString(cursor.getColumnIndex("time"));
        String uri = cursor.getString(cursor.getColumnIndex("path"));
        contenttv.setText(content);
        timetv.setText(time);


        imgiv.setImageBitmap(getImageThumbnail(uri, 200, 200));

        return layout;
    }

    public Bitmap getImageThumbnail(String Uri, int wid, int hei){
        Bitmap bitmap = null;
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        bitmap = BitmapFactory.decodeFile(Uri, opt);
        opt.inJustDecodeBounds = false;
        int bewid = opt.outWidth/wid;
        int behei = opt.outHeight/hei;
        int be = 1;
        if (bewid < behei)
            be = bewid;
        else be = behei;
        if (be<=0)
            be=1;

        opt.inSampleSize = be;

        bitmap = BitmapFactory.decodeFile(Uri, opt);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, wid, hei, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

        return  bitmap;





    }
}
