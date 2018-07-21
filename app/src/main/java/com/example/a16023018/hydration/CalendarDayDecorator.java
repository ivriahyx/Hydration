package com.example.a16023018.hydration;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.stacktips.view.DayDecorator;
import com.stacktips.view.DayView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class CalendarDayDecorator implements DayDecorator {

    Context context;

    ListView lv;
    ArrayList<Data> al;
    ArrayAdapter aa;
    private int mProgress = 0;
    DataSource dataSource;

    public CalendarDayDecorator(Context context){
        al = new ArrayList<Data>();
        dataSource = new DataSource(context);
        this.context = context;
    }

    @Override
    public void decorate(DayView dayView) {
        int status = getDayStatus(dayView);
        switch (status){
            case 1: dayView.setBackgroundResource(R.drawable.block1);
                break;
            case 2: dayView.setBackgroundResource(R.drawable.block2);
                break;
            case 3: dayView.setBackgroundResource(R.drawable.block3);
                break;
            case 4: dayView.setBackgroundResource(R.drawable.block4);
                break;
            case 5: dayView.setBackgroundResource(R.drawable.block5);
                break;
            case 6: dayView.setBackgroundResource(R.drawable.block6);
                break;
            case 7: dayView.setBackgroundResource(R.drawable.block7);
                break;
            case 8: dayView.setBackgroundResource(R.drawable.block8);
                break;
            default:
                dayView.setBackgroundResource(R.drawable.block0);
        }
    }

    public int getDayStatus(DayView dayView){
        Date date = dayView.getDate();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(date);

        int cup = dataSource.getCupsByDate(formattedDate);

        return cup;
    }

}
