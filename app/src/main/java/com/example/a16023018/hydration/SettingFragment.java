package com.example.a16023018.hydration;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;


public class SettingFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    String TAG = "HydrationNotification";
    LocalData localData;

    ToggleButton tgbtn;
    Spinner spinner;

    LinearLayout ll_set_time, ll_terms;

    int time = 5;
    int reqCode = 12345;

    ClipboardManager myClipboard;
    public static SettingFragment newInstance(){
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }
public SettingFragment(){ }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_setting, container, false);

        //Spinner
        spinner = (Spinner) rootView.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.arrays, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        //Notification
        tgbtn = (ToggleButton)rootView.findViewById(R.id.toggleButton);

        tgbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Boolean checkbtn = tgbtn.isChecked();
                    Log.d("checkswitch",checkbtn+"");

                     SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                     SharedPreferences.Editor editor = prefs.edit();
                     editor.putBoolean("checkswitch", checkbtn);
                     editor.commit();

                    alarmNotify();
                }else {
                    Boolean checkbtn = tgbtn.isChecked();
                    Log.d("checkswitch",checkbtn+"");
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("checkswitch", checkbtn);
                    editor.commit();
                }
            }
        });

        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /*
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("userChoiceSpinner",spinnerPos);
        Log.d("userChoiceSpinner",""+spinnerPos);
        editor.commit();
    }
    */

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Boolean checkswitch = prefs.getBoolean("checkswitch", false);
        int userchoice = prefs.getInt("userChoiceSpinner",0);
        tgbtn.setChecked(checkswitch);
        Log.d("spinner selection",""+userchoice);
        if(userchoice != -1) {
            // set the selected value of the spinner
            spinner.setSelection(userchoice);
        }

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String item = parent.getItemAtPosition(pos).toString();
        int index = spinner.getSelectedItemPosition();
        String text = spinner.getSelectedItem().toString();
        Toast.makeText(getActivity(), "Index: "+index, Toast.LENGTH_SHORT).show();
        if(index==1){
            time=60;
            int spinnerPos = 1;
            Log.d("time",""+time);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("userChoiceSpinner",spinnerPos);
            editor.commit();
        }else if(index==2){
            time=120;
            int spinnerPos = 2;
            Log.d("time",""+time);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("userChoiceSpinner",spinnerPos);
            editor.commit();
        }else{
            time = 30;
            int spinnerPos = 0;
            Log.d("time",""+time);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("userChoiceSpinner",spinnerPos);
            editor.commit();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public void alarmNotify(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, time);
        Log.d("timespinner",""+time);
        Intent intent = new Intent(getActivity(),
                ScheduledNotificationReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getActivity(), reqCode,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager am = (AlarmManager)
                getActivity().getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                pendingIntent);
    }

}
