package com.shock.demoapp.ui.list.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.shock.demoapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shahid on 18/8/16.
 */
public class GridCellAdapter extends BaseAdapter implements View.OnClickListener {
    private final Context _context;

    private final List<String> list;
    private static final int DAY_OFFSET = 1;
    private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int daysInMonth;
    private int currentDayOfMonth;
    private int currentWeekDay;
    private List<String> blockedDates;

    // Days in Current Month
    public GridCellAdapter(Context context, int textViewResourceId, int month, int year, List<String> blockedDates) {
        super();
        this._context = context;
        this.list = new ArrayList<String>();
        this.blockedDates = blockedDates;
        Calendar calendar = Calendar.getInstance();
        setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
        setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));

        // Print Month
        printMonth(month, year);

        // Find Number of Events
    }

    private String getMonthAsString(int i) {
        return months[i];
    }

    private int getNumberOfDaysOfMonth(int i) {
        return daysOfMonth[i];
    }

    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    private void printMonth(int mm, int yy) {
        int trailingSpaces = 0;
        int daysInPrevMonth = 0;
        int prevMonth = 0;
        int prevYear = 0;
        int nextMonth = 0;
        int nextYear = 0;

        int currentMonth = mm - 1;
        daysInMonth = getNumberOfDaysOfMonth(currentMonth);


        // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
        GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);

        if (currentMonth == 11) {
            prevMonth = currentMonth - 1;
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
            nextMonth = 0;
            prevYear = yy;
            nextYear = yy + 1;
        } else if (currentMonth == 0) {
            prevMonth = 11;
            prevYear = yy - 1;
            nextYear = yy;
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
            nextMonth = 1;
        } else {
            prevMonth = currentMonth - 1;
            nextMonth = currentMonth + 1;
            nextYear = yy;
            prevYear = yy;
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
        }

        int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        trailingSpaces = currentWeekDay;

        if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1) {
            ++daysInMonth;
        }

        // Trailing Month days
        for (int i = 0; i < trailingSpaces; i++) {
            list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear);
        }

        // Current Month Days
        for (int i = 1; i <= daysInMonth; i++) {
            if (i == getCurrentDayOfMonth())
                list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
            else
                list.add(String.valueOf(i) + "-BLACK" + "-" + getMonthAsString(currentMonth) + "-" + yy);
        }

        // Leading Month days
        for (int i = 0; i < list.size() % 7; i++) {
            list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
        }
    }

    private HashMap<String, Integer> findNumberOfEventsPerMonth(int year, int month) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        return map;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.calendar_day_gridcell, parent, false);
        }

        // Get a reference to the Day gridcell
        Button gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
        String[] day_color = list.get(position).split("-");
        String theday = day_color[0];
        String themonth = day_color[2];
        String theyear = day_color[3];

        String s = "";
        Date mdate = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            mdate = new SimpleDateFormat("yyyy-MMMM-dd").parse(theyear + "-" + themonth + "-" + theday);
            s = simpleDateFormat.format(mdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        gridcell.setText(theday);
        gridcell.setBackgroundColor(_context.getResources().getColor(R.color.transparent));
        if (Calendar.getInstance().getTimeInMillis() < mdate.getTime()) {
            if (blockedDates != null && blockedDates.size() > 0 && blockedDates.contains(s)) {
                gridcell.setOnClickListener(this);
                gridcell.setTextColor(Color.BLACK);
                gridcell.setBackgroundColor(Color.LTGRAY);
                gridcell.setTag("not available. Please select another date");
            } else {
                if (day_color[1].equals("GREY"))
                    gridcell.setTextColor(Color.LTGRAY);

                if (day_color[1].equals("BLACK")) {
                    gridcell.setTextColor(Color.BLACK);
                    gridcell.setOnClickListener(this);
                    gridcell.setTag(theday + "-" + themonth + "-" + theyear);
                }

                if (day_color[1].equals("BLUE")) {
                    gridcell.setTextColor(Color.BLUE);
                    gridcell.setOnClickListener(this);
                    gridcell.setTag(theday + "-" + themonth + "-" + theyear);
                }
            }
        } else {
            gridcell.setTextColor(Color.LTGRAY);
        }
        return row;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(_context, "Selected date is " + (String) view.getTag(), Toast.LENGTH_SHORT).show();
    }

    public int getCurrentDayOfMonth() {
        return currentDayOfMonth;
    }

    private void setCurrentDayOfMonth(int currentDayOfMonth) {
        this.currentDayOfMonth = currentDayOfMonth;
    }

    public void setCurrentWeekDay(int currentWeekDay) {
        this.currentWeekDay = currentWeekDay;
    }

    public int getCurrentWeekDay() {
        return currentWeekDay;
    }
}
