package com.neting.login.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.neting.login.R;

public class ProfileActivity extends ActionBarActivity {

    private TextView f_name;
    private TextView f_email;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_profile);

        f_name = (TextView)findViewById(R.id.name);
        f_email = (TextView)findViewById(R.id.email);
    }
}
