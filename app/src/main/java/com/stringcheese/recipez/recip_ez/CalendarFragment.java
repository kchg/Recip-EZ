package com.stringcheese.recipez.recip_ez;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Gravity;
import android.content.Context;
import android.graphics.Color;
import android.widget.CalendarView.OnDateChangeListener;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.widget.Toast.LENGTH_LONG;


public class CalendarFragment extends Fragment {


    public CalendarFragment() {
        // Required empty public constructor
    }

    Button test;
    CalendarView cal;
    Long date;
    int i = 0;
    EditText bText, lText, dText;
    Button save, edit;
    ArrayMap <Long,String[]> recipes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        recipes = new ArrayMap<>();
        bText= (EditText) v.findViewById(R.id.bEditText);
        lText= (EditText) v.findViewById(R.id.lEditText);
        dText= (EditText) v.findViewById(R.id.dEditText);

        cal = (CalendarView) v.findViewById(R.id.calendarView);
        date = cal.getDate();

        save = (Button) v.findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonClicked(v);
            }
        });

        edit = (Button) v.findViewById(R.id.editButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editButtonClicked(v);
            }
        });

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = cal.getDate();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    public void saveButtonClicked(View view) {
        Toast.makeText(getActivity(), "Your recipes are saved!" , Toast.LENGTH_SHORT).show();

        String b,l,d;
        String[] recipeArr;

        b = bText.getText().toString();
        bText.setEnabled(false);

        l = lText.getText().toString();
        lText.setEnabled(false);

        d = dText.getText().toString();
        dText.setEnabled(false);

        recipeArr = new String[3];
        recipeArr[0] = b;
        recipeArr[1] = l;
        recipeArr[2] = d;

        recipes.put(date, recipeArr);
        save.setEnabled(false);

    }

    public void editButtonClicked(View view) {
       // Toast.makeText(getActivity(), "Edit was pressed!", Toast.LENGTH_SHORT).show();
    }

}
