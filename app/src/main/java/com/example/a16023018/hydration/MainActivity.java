package com.example.a16023018.hydration;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    //Database
    DataSource dataSource;
    ArrayList<Data> al;
    ArrayAdapter<Data> aa;

    //Notification
    String TAG = "RemindMe";
    LocalData localData;
    SwitchCompat reminderSwitch;
    TextView tvTime, tvTotal;

    LinearLayout ll_set_time, ll_terms;

    int hour, min;

    ClipboardManager myClipboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTotal = (TextView) findViewById(R.id.textViewTotal);
        dataSource = new DataSource(MainActivity.this);
        al = new ArrayList<Data>();

        //Notification
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Title")
                .setContentText("Welcome Message......Hello World!")
                .setSmallIcon(R.drawable.notification_icon)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        Boolean switchstate = i.getBooleanExtra("switch", false);

        //sharedpreference
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean tgpref = preferences.getBoolean("tgpref", true);  //default is true
        if (tgpref = true) //if (tgpref) may be enough, not sure
        {
            switchstate = true;
        } else {
            switchstate = false;
        }


        if (switchstate == true) {
            notificationManager.notify(0, notification);
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_progression:
                        selectedFragment = ProgressFragment.newInstance();
                        Toast.makeText(MainActivity.this, "Action Current Progress Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_all:
                        selectedFragment = AllProgressFragment.newInstance();
                        Toast.makeText(MainActivity.this, "Action Progression Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_setting:
                        selectedFragment = SettingFragment.newInstance();
                        Toast.makeText(MainActivity.this, "Action Settings Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, ProgressFragment.newInstance());
        transaction.commit();

    }




}