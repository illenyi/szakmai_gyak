package com.neting.login.activity;

import com.neting.login.R;
import com.neting.login.helper.SQLiteHandler;
import com.neting.login.helper.SessionManager;
//import com.neting.login.helper.StreamButton;

import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private SQLiteHandler db;
    private SessionManager session;

    protected String name;
    protected String email;

    ///////

    protected ListView mDrawerList;
    protected ArrayAdapter<String> mAdapter;

    protected ActionBarDrawerToggle mDrawerToggle;
    protected DrawerLayout mDrawerLayout;
    protected String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        name = user.get("name");
        email = user.get("email");

        setContentView(R.layout.activity_main);

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
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

    //////////

    private void addDrawerItems() {
        String[] osArray = { "Profile", "Videos", "Log out" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, position + ", " + id, Toast.LENGTH_SHORT).show();
                switch(position){
                    case 0:
                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        viewProfile();
                        break;
                    case 1:
                        //Toast.makeText(MainActivity.this, "Video list", Toast.LENGTH_SHORT).show();
                        viewVideos();
                        break;
                    case 2:
                        logoutUser();
                        break;
                }
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getSupportActionBar().setTitle(mActivityTitle);
                getSupportActionBar().setTitle(name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        //noinspection deprecation
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void viewProfile(){
        setContentView(R.layout.activity_profile);

        ((TextView)findViewById(R.id.name)).setText(name);
        ((TextView)findViewById(R.id.email)).setText(email);
    }

    private void viewVideos(){
        setContentView(R.layout.activity_videos);

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        //noinspection deprecation
        mDrawerLayout.setDrawerListener(mDrawerToggle);


        StreamButton bt1 = new StreamButton("RtBbinpK5XI",(LinearLayout) findViewById(R.id.layout_videos),getApplicationContext());
        StreamButton bt2 = new StreamButton("bX9CvhbfQgg",(LinearLayout) findViewById(R.id.layout_videos),getApplicationContext());
        StreamButton bt3 = new StreamButton("BC2dRkm8ATU",(LinearLayout) findViewById(R.id.layout_videos),getApplicationContext());

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        /*
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();
        */
    }

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

}
