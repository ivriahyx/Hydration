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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
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


public class SettingFragment extends Fragment {

    String TAG = "HydrationNotification";
    LocalData localData;

    SwitchCompat reminderSwitch;
    TextView tvTime;

    LinearLayout ll_set_time, ll_terms;

    int hour, min;

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
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        //final Switch switchN = (Switch)rootView.findViewById(R.id.switchNotification);
        //final Boolean switchState = switchN.isChecked();

        //Notification

        tvTime = (TextView) rootView.findViewById(R.id.tv_reminder_time_desc);
        reminderSwitch = (SwitchCompat) rootView.findViewById(R.id.switchNotification);
        reminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.SECOND, 5);

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
        });

/*
        //Notification
        localData = new LocalData(getActivity());

        myClipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        ll_set_time = (LinearLayout) rootView.findViewById(R.id.ll_set_time);
        //ll_terms = (LinearLayout) rootView.findViewById(R.id.ll_terms);

        hour = localData.get_hour();
        min = localData.get_min();

        tvTime.setText(getFormatedTime(hour, min));
        reminderSwitch.setChecked(localData.getReminderStatus());

        if (!localData.getReminderStatus())
            ll_set_time.setAlpha(0.4f);

        reminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                localData.setReminderStatus(isChecked);
                if (isChecked) {
                    Log.d(TAG, "onCheckedChanged: true");
                    NotificationScheduler.setReminder(getActivity(), AlarmReceiver.class, localData.get_hour(), localData.get_min());
                    ll_set_time.setAlpha(1f);
                } else {
                    Log.d(TAG, "onCheckedChanged: false");
                    NotificationScheduler.cancelReminder(getActivity(), AlarmReceiver.class);
                    ll_set_time.setAlpha(0.4f);
                }

            }
        });

        ll_set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (localData.getReminderStatus())
                    showTimePickerDialog(localData.get_hour(), localData.get_min());
            }
        });

        ll_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
*/

 /*
        switchN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Boolean switchState = switchN.isChecked();
                Intent i = new Intent(getActivity().getBaseContext(),MainActivity.class);
                i.putExtra("switch", switchState);
                getActivity().startActivity(i);
                if((switchN.isChecked()))
                {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("tgpref", true); // value to store
                    editor.commit();
                }
                else
                {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("tgpref", false); // value to store
                    editor.commit();
                }
            }

            });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSource dataSource = new DataSource(getActivity());
                dataSource.deleteData(dataSource.getAllData().size());
            }
        });
*/


        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    //Notification
/*
    private void showTimePickerDialog(int h, int m) {

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.timepicker_header, null);

        TimePickerDialog builder = new TimePickerDialog(getActivity(), R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        Log.d(TAG, "onTimeSet: hour " + hour);
                        Log.d(TAG, "onTimeSet: min " + min);
                        localData.set_hour(hour);
                        localData.set_min(min);
                        tvTime.setText(getFormatedTime(hour, min));
                        NotificationScheduler.setReminder(getActivity(), AlarmReceiver.class, localData.get_hour(), localData.get_min());

                    }
                }, h, m, false);

        builder.setCustomTitle(view);
        builder.show();

    }

    public String getFormatedTime(int h, int m) {
        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "hh:mm a";

        String oldDateString = h + ":" + m;
        String newDateString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, getCurrentLocale());
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDateString;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return getResources().getConfiguration().locale;
        }
    }
*/


}
