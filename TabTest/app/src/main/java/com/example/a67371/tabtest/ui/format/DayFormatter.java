package com.example.a67371.tabtest.ui.format;

import android.support.annotation.NonNull;

import com.example.a67371.tabtest.ui.calendar.CalendarDay;
import com.example.a67371.tabtest.ui.calendar.MaterialCalendarView;

import java.text.SimpleDateFormat;

/**
 * Supply labels for a given day. Default implementation is to format using a {@linkplain SimpleDateFormat}
 */
public interface DayFormatter {

    /**
     * Format a given day into a string
     *
     * @param day the day
     * @return a label for the day
     */
    @NonNull
    String format(@NonNull CalendarDay day);

    /**
     * Default implementation used by {@linkplain MaterialCalendarView}
     */
    public static final DayFormatter DEFAULT = new DateFormatDayFormatter();
}
