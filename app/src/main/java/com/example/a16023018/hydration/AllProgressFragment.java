package com.example.a16023018.hydration;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AllProgressFragment extends Fragment {
    private static final String TAG = "HydrationCalendar";
    private CalendarPickerView calendar;
    DataSource dataSource;

    public static AllProgressFragment newInstance(){
        AllProgressFragment fragment = new AllProgressFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_all_progress, container, false);
        //dataSource = new DataSource(getActivity());
       //TextView tvAllProgressDisplay = (TextView)rootView.findViewById(R.id.textViewAllProgressDisplay);
        //tvAllProgressDisplay.setText(dataSource.getAllData().toString());

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);

        calendar = (CalendarPickerView) rootView.findViewById(R.id.calendar_view);
        calendar.init(lastYear.getTime(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.SINGLE) //
                .withSelectedDate(new Date());

        return rootView;

    }

    private Date getDateWithYear(int year) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    private Date getDateWithYearAndMonthForDay(int year, int month, int day) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    private List<Date> getHighlightedDaysForMonth(int... month) {
        List<Date> dateList = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        for (int i = 0; i < month.length; i++) {
            for (int j = 0; j < 25; j++) {
                dateList.add(getDateWithYearAndMonthForDay(c.get(Calendar.YEAR), i, j));
            }
        }

        return dateList;
    }

}
