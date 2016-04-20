package com.stringcheese.recipez.recip_ez;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static android.widget.Toast.LENGTH_LONG;


public class CalendarFragment extends Fragment implements View.OnClickListener{


    public CalendarFragment() {
        // Required empty public constructor ,
    }

    CalendarView cal;
    GregorianCalendar date, selectedDate;
    int i = 0;

    int flag;

    File file;
    String filename;
    String meals;
    ListView listView;
    ArrayAdapter adapter;
    int adapterFlag = 1;
    int dateChanged = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        //meals = getArguments().getString("message");

        filename = "calendarrecipes.txt";

        cal = (CalendarView) v.findViewById(R.id.calendarView);
        listView = (ListView) v.findViewById(R.id.recipeslist);

        System.out.println("onCreateView test");

        FloatingActionButton addMealsButton = (FloatingActionButton) v.findViewById(R.id.cal_add);
        addMealsButton.setOnClickListener(this);

        GregorianCalendar gregorianCalendar=new GregorianCalendar();
        String month=String.valueOf(gregorianCalendar.get(GregorianCalendar.MONTH));
        String day=String.valueOf(gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH));
        String year=String.valueOf(gregorianCalendar.get(GregorianCalendar.YEAR));

        new CalendarRecipes();

        int y = Integer.parseInt(year);
        int d = Integer.parseInt(day);
        int m = Integer.parseInt(month);
        date = new GregorianCalendar(y, m, d);

        flag = 0;

        file = getActivity().getFileStreamPath(filename);

/*
        try {
            PrintWriter writer1 = new PrintWriter(file, "UTF-8");
            writer1.printf("2016 3 18 b Spahgetti | eggs and bacon | l Tuna Sandwich | d mac and cheese |%n" +
                    "2016 3 19 b Egg bagel Sandwich | l apple | sandwich | poop and ketchup | d steak and potatos |%n" +
                     "2016 3 20 b Eggs and Bacon | l blt sandwich | d cheese pizza | pepperoni pizza |%n");
            writer1.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

*/
        if(file.exists())
        {
            try
            {
                Scanner getLine = new Scanner(file);
                Scanner tokenizer;

                while (getLine.hasNextLine()) {

                    String line = getLine.nextLine();
                    tokenizer = new Scanner(line);
                    System.out.println("Printing line = " + line);

                    while (tokenizer.hasNextInt()) {
                        int y1 = tokenizer.nextInt();
                        int m1 = tokenizer.nextInt();
                        int d1 = tokenizer.nextInt();

                        GregorianCalendar date = new GregorianCalendar(y1, m1, d1);

                        MealData data = new MealData();
                        while (tokenizer.hasNext()) {
                            String type = tokenizer.next();

                            if (type.equals("b")) {
                                String recipeName = "";

                                while (tokenizer.hasNext()) {
                                    String tmp = tokenizer.next();

                                    if (tmp.equals("l") || tmp.equals("d")) {
                                        type = tmp;
                                        break;
                                    }

                                    if (tmp.equals("|")) {
                                        data.setBreakfastItems(recipeName);
                                        recipeName = "";
                                        continue;
                                    }

                                    recipeName += " " + tmp;
                                }
                            }

                            if (type.equals("l")) {
                                String recipeName = "";

                                while (tokenizer.hasNext()) {
                                    String tmp = tokenizer.next();

                                    if (tmp.equals("d")) {
                                        type = tmp;
                                        break;
                                    }

                                    if (tmp.equals("|")) {
                                        data.setLunchItems(recipeName);
                                        recipeName = "";
                                        continue;
                                    }

                                    recipeName += " " + tmp;
                                }
                            }

                            if (type.equals("d")) {
                                String recipeName = "";

                                while (tokenizer.hasNext()) {
                                    String tmp = tokenizer.next();

                                    if (tmp.equals("|")) {
                                        data.setDinnerItems(recipeName);
                                        recipeName = "";
                                        continue;
                                    }

                                    recipeName += " " + tmp;
                                }
                            }
                        }
                        CalendarRecipes.recipes.put(date, data);
                    }
                    tokenizer.close();
                }
                getLine.close();
            }

            catch(IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(getActivity(), "Not working", Toast.LENGTH_SHORT).show();
            file = new File(getActivity().getFilesDir(), filename);
        }
/*
        for (Map.Entry<GregorianCalendar, MealData> entry : CalendarRecipes.recipes.entrySet()) {
            GregorianCalendar key = entry.getKey();
            MealData value = entry.getValue();

            System.out.printf("%d %d %d", key.get(Calendar.YEAR), key.get(Calendar.MONTH), key.get(Calendar.DAY_OF_MONTH));

            System.out.println();
            System.out.print("b ");
            for (String b : value.breakfastItems) {
                System.out.print(b + " ");
            }

            System.out.println();
            System.out.print("l ");
            for (String l : value.lunchItems) {
                System.out.print(l + " ");
            }

            System.out.println();
            System.out.print("d ");
            for (String di : value.dinnerItems) {
                System.out.print(di + " ");
            }
            System.out.println();
        }

*/

        if (CalendarRecipes.recipes.get(date) != null) {
            MealData values = CalendarRecipes.recipes.get(date);
            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, values.mergeLists());
            listView.setAdapter(adapter);
        }

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                //Toast.makeText(getActivity(), "Date = " + year + " " + month + " " + dayOfMonth, Toast.LENGTH_SHORT).show();

                flag = 1;
                selectedDate = new GregorianCalendar(year, month, dayOfMonth);
                dateChanged = 0;

                if(CalendarRecipes.recipes.get(selectedDate)!=null) {
                    MealData values = CalendarRecipes.recipes.get(selectedDate);
                    adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, values.mergeLists());
                    listView.setAdapter(adapter);
                    adapterFlag = 0;
                } else if (adapterFlag == 0){
                    adapter.clear();
                }
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    public void onResume() {
        super.onResume();
        if (CalendarRecipes.addedFlag == 1) {
            MealData values;
            if (CalendarRecipes.recipes.get(date) != null && dateChanged == 1) {
                values = CalendarRecipes.recipes.get(date);
            } else if (CalendarRecipes.recipes.get(selectedDate) != null) {
                values = CalendarRecipes.recipes.get(selectedDate);
            } else {
                values = new MealData();
            }

            for (int i = 0; i < CalendarRecipes.mealNames.size(); i += 2) {
                if (CalendarRecipes.mealNames.get(i).equals("b")) {
                    values.setBreakfastItems(CalendarRecipes.mealNames.get(i+1));
                } else if (CalendarRecipes.mealNames.get(i).equals("l")) {
                    values.setLunchItems(CalendarRecipes.mealNames.get(i+1));
                } else if (CalendarRecipes.mealNames.get(i).equals("d")) {
                    values.setDinnerItems(CalendarRecipes.mealNames.get(i+1));
                }
            }

            if (dateChanged == 1) {
                CalendarRecipes.recipes.put(date, values);
            } else {
                CalendarRecipes.recipes.put(selectedDate, values);
            }

            CalendarRecipes.mealNames.clear();
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values.mergeLists());
            listView.setAdapter(adapter);
            System.out.print("Whyyyyy");
            writeToFile();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cal_add:
                Intent intent = new Intent(getActivity(), meals_display.class);
                startActivity(intent);
                break;
        }
    }

    private void writeToFile() {
        try
        {
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            for (GregorianCalendar gregObject: CalendarRecipes.recipes.keySet()) {
                int bflag = 1, lflag = 1, dflag = 1;

                MealData values = CalendarRecipes.recipes.get(gregObject);

                int year = gregObject.get(Calendar.YEAR);
                int month = gregObject.get(Calendar.MONTH);
                int day = gregObject.get(Calendar.DAY_OF_MONTH);

                writer.printf("%d %d %d ", year, month, day);
                System.out.printf("writetofile %d %d %d ", year, month, day);

                for (String b : values.getBreakfastItems()) {
                    if (bflag == 1) {
                        System.out.print("b ");
                        writer.printf("b ");
                        bflag = 0;
                    }

                    System.out.printf("%s | ", b);
                    writer.printf("%s | ", b);

                }

                for (String l : values.getLunchItems()) {
                    if (lflag == 1) {
                        System.out.print("l ");
                        writer.printf("l ");
                        lflag = 0;
                    }

                    System.out.printf("%s | ", l);
                    writer.printf("%s | ", l);
                }

                for (String d : values.getDinnerItems()) {
                    if (dflag == 1) {
                        System.out.print("d ");
                        writer.printf("d ");
                        dflag = 0;
                    }

                    System.out.printf("%s | ", d);
                    writer.printf("%s | ", d);
                }

                System.out.println();
                writer.println();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
