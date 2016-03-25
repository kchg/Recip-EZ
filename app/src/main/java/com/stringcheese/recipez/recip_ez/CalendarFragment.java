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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static android.widget.Toast.LENGTH_LONG;


public class CalendarFragment extends Fragment {


    public CalendarFragment() {
        // Required empty public constructor
    }

    Button test;
    CalendarView cal;
    GregorianCalendar date, selectedDate;
    int i = 0;
    EditText bText, lText, dText;
    Button save, edit;
    HashMap <GregorianCalendar,String[]> recipes;
    int flag;

    File file;
    String filename;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        recipes = new HashMap<>();
        filename = "calendarrecipes.txt";

        bText= (EditText) v.findViewById(R.id.bEditText);
        lText= (EditText) v.findViewById(R.id.lEditText);
        dText= (EditText) v.findViewById(R.id.dEditText);


        cal = (CalendarView) v.findViewById(R.id.calendarView);

        GregorianCalendar gregorianCalendar=new GregorianCalendar();
        String month=String.valueOf(gregorianCalendar.get(GregorianCalendar.MONTH));
        String day=String.valueOf(gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH));
        String year=String.valueOf(gregorianCalendar.get(GregorianCalendar.YEAR));

        int y = Integer.parseInt(year);
        int d = Integer.parseInt(day);
        int m = Integer.parseInt(month);
        date = new GregorianCalendar(y, m, d);

        flag = 0;

        file = getActivity().getFileStreamPath(filename);

        if(file.exists())
        {
            Toast.makeText(getActivity(), "File exists!", Toast.LENGTH_SHORT).show();
            try
            {
                Scanner getLine = new Scanner(file);
                Scanner tokenizer;

                while (getLine.hasNextLine()) {
                    String line = getLine.nextLine();
                    tokenizer = new Scanner(line);
                    System.out.println(line);

                    while (tokenizer.hasNextInt()) {
                        int y1 = tokenizer.nextInt();
                        int m1 = tokenizer.nextInt();
                        int d1 = tokenizer.nextInt();

                        GregorianCalendar cal = new GregorianCalendar(y1, m1, d1);
                        String[] meals = new String[3];
                        while (tokenizer.hasNext()) {
                            String type = tokenizer.next();
                            String recipeName;
                            if (type == "b") {
                                recipeName = tokenizer.next();
                                meals[0] = recipeName;
                            }

                            else if (type == "l") {
                                recipeName = tokenizer.next();
                                meals[1] = recipeName;
                            }

                            else if (type == "d") {
                                recipeName = tokenizer.next();
                                meals[2] = recipeName;
                            }
                        }
                        recipes.put(cal, meals);
                    }
                    tokenizer.close();
                }
                getLine.close();
            }

            catch(IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(getActivity(), "File does not exist!!", Toast.LENGTH_SHORT).show();
            file = new File(getActivity().getFilesDir(), filename);
        }

            save = (Button) v.findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonClicked(v);
            }
        });

        edit = (Button) v.findViewById(R.id.editButton);
        edit.setEnabled(false);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editButtonClicked(v);
            }
        });

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                selectedDate = new GregorianCalendar(year, month, dayOfMonth);

                if(recipes.get(selectedDate)!=null)
                {
                    bText.setEnabled(false);
                    lText.setEnabled(false);
                    dText.setEnabled(false);
                    save.setEnabled(false);
                    edit.setEnabled(true);

                    //update the next field that need to be there
                    String[] s = recipes.get(selectedDate);
                    bText.setText(s[0]);
                    lText.setText(s[1]);
                    dText.setText(s[2]);

                }

                else {
                    bText.setText("");
                    lText.setText("");
                    dText.setText("");

                    bText.setEnabled(true);
                    lText.setEnabled(true);
                    dText.setEnabled(true);
                    save.setEnabled(true);
                    edit.setEnabled(false);
                }
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

        if (flag == 0) {
            recipes.put(date, recipeArr);
            flag = 1;
        }

        else {
            recipes.put(selectedDate, recipeArr);
        }


        writeToFile();
        save.setEnabled(false);
        edit.setEnabled(true);
    }

    public void editButtonClicked(View view) {
       // Toast.makeText(getActivity(), "Edit was pressed!", Toast.LENGTH_SHORT).show();

        bText.setEnabled(true);
        lText.setEnabled(true);
        dText.setEnabled(true);
        save.setEnabled(true);
        edit.setEnabled(false);


    }

    private void writeToFile() {
        try
        {
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            for (GregorianCalendar gregObject: recipes.keySet()){

                GregorianCalendar key = gregObject;
                String[] value = recipes.get(gregObject);
                int y = key.YEAR;
                int m = key.MONTH;
                int d = key.DAY_OF_MONTH;

                writer.printf("%d %d %d ", y, m, d);
                if (!value[0].equals("")) {
                    writer.printf("b %s ", value[0]);
                }
                if (!value[1].equals("")) {
                    writer.printf("l %s ", value[1]);
                }
                if (!value[2].equals("")) {
                    writer.printf("d %s ", value[2]);
                }

                writer.print("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
