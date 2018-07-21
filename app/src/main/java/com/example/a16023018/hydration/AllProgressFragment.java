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

import com.stacktips.view.CalendarListener;
import com.stacktips.view.CustomCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class AllProgressFragment extends Fragment {
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

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CustomCalendarView calendarView = (CustomCalendarView) rootView.findViewById(R.id.calendar_view);

        //Initialize calendar with date
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());

        //Show Monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        //Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);

        //set Decorators, customize the look and feel
        List decorators = new ArrayList<>();
        decorators.add(new CalendarDayDecorator(getActivity()));
        calendarView.setDecorators(decorators);

        //call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(currentCalendar);

        //Handling custom calendar events
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;

    }


}
