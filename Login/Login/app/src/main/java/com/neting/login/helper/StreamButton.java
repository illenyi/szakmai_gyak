package com.neting.login.helper;
/*
import java.io.InputStream;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class StreamButton{
    private String video;
    private ImageButton ib;

    public StreamButton(String ivideo, LinearLayout layout, Context context){
        video=ivideo;
        ib=new ImageButton(context);
        ib.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        new DownloadImageTask(ib).execute("http://img.youtube.com/vi/" + video + "/hqdefault.jpg");
        addListenerOnButton();
        layout.addView(ib);
    }

    public void addListenerOnButton() {

        ib.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //TODO permission
                if(true) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + video));
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),
                            "Fizess elő, hogy megnézhesd a videót!!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageButton bmImage;

        public DownloadImageTask(ImageButton bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
*/