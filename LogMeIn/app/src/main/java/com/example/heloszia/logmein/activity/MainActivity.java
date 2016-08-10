package com.example.heloszia.logmein.activity;

import com.example.heloszia.logmein.R;
import com.example.heloszia.logmein.helper.SQLiteHandler;
import com.example.heloszia.logmein.helper.SessionManager;

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

public class MainActivity extends Activity {

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    private int lvl=-1;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);



        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");


        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        lvl = Integer.parseInt(user.get("user_lvl"));
        button ib=new button("RtBbinpK5XI",(LinearLayout) findViewById(R.id.linearLayout),getApplicationContext(),lvl,0);
        button ib2=new button("bX9CvhbfQgg",(LinearLayout) findViewById(R.id.linearLayout),getApplicationContext(),lvl,1);
        /*button imagebutton= new button("RtBbinpK5XI", (ImageButton) findViewById(R.id.video_1),lvl,0);
        button imagebutton2= new button("bX9CvhbfQgg", (ImageButton) findViewById(R.id.video_2),lvl,1);
        button imagebutton3= new button("2Y6Nne8RvaA", (ImageButton) findViewById(R.id.video_3),lvl,1);
        button imagebutton4= new button("a59gmGkq_pw", (ImageButton) findViewById(R.id.video_4),lvl,1);
        */
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private class button{
        private String video;
        private ImageButton ib;
        private int userlvl, reqlvl;
/*
        public button(String ivideo, ImageButton iimagebtn, int ulvl, int rlvl){
            video=ivideo;
            imgbtn=iimagebtn;
            userlvl=lvl;
            reqlvl=rlvl;
            new DownloadImageTask(imgbtn).execute("http://img.youtube.com/vi/" + video + "/hqdefault.jpg");
            addListenerOnButton();
        }*/

        public button(String ivideo, LinearLayout layout,Context context, int ulvl, int rlvl){
            video=ivideo;
            ib=new ImageButton(context);
            ib.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            userlvl=lvl;
            reqlvl=rlvl;
            new DownloadImageTask(ib).execute("http://img.youtube.com/vi/" + video + "/hqdefault.jpg");
            addListenerOnButton();

            layout.addView(ib);

        }

        public void addListenerOnButton() {

            ib.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if(userlvl >= reqlvl) {
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


}
