package edu.qc.cs370.macrotracker.macrotracker.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import edu.qc.cs370.macrotracker.MainActivity;
import edu.qc.cs370.macrotracker.R;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {
  ArrayAdapter adapter;
  String update_info;

  public SettingsFragment() {
    // Required constructor
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_settings, parent, false);

    ListView settingsList = view.findViewById(R.id.settingsList);

    // TODO Create a CustomSettingsAdapter java class. - DV
    // Implementing an arrayadapter for settings list view. - DV
    final ArrayList<String> settings = new ArrayList<>();
    settings.add("Name: " + edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.getName());
    settings.add("DOB: " + edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.getDob());
    settings.add("Weight: " + edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.getWeight());
    settings.add("Calories: " + edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.getCalories());
    settings.add("Fat: " + edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.getFat());
    settings.add("Carbs: " + edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.getCarbs());
    settings.add("Protein: " + edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.getProtein());

    adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, settings);
    settingsList.setAdapter(adapter);

    // Adding an action listener for the list view.
    settingsList.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
        // TODO add the on click events for each list item here. - DV
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        final EditText edittext = new EditText(getContext());
        final int setting_position = position;
        final String info_type = parent.getItemAtPosition(position).toString().split(":")[0];
        alert.setTitle("Settings");
        alert.setMessage("Update " + info_type);
        alert.setView(edittext);
        edittext.setText( parent.getItemAtPosition(position).toString().split(":")[1]);
        alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {
            //What ever you want to do with the value
            Editable YouEditTextValue = edittext.getText();
            updateInfo(info_type, YouEditTextValue.toString());
            settings.add(setting_position, info_type + ": " + YouEditTextValue.toString());
            settings.remove(setting_position+1);
          }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {
            // what ever you want to do with No option.
          }
        });

        alert.show();

      }
    });

    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    // Setup any handles to view objects here
  }

  public void updateInfo(String info_type, String info){
    if (info_type.equals("Name")){
      edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.setName(info);
      edu.qc.cs370.macrotracker.macrotracker.MainActivity.foodDatabase.userDao().updateUser(edu.qc.cs370.macrotracker.macrotracker.MainActivity.user);
    }
    else if (info_type.equals("DOB")){
      edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.setDob(info);
      edu.qc.cs370.macrotracker.macrotracker.MainActivity.foodDatabase.userDao().updateUser(edu.qc.cs370.macrotracker.macrotracker.MainActivity.user);
    }
    else if (info_type.equals("Weight")){
      int val = parseToInt(info);
      if (val != 0) {
        edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.setWeight(val);
        edu.qc.cs370.macrotracker.macrotracker.MainActivity.foodDatabase.userDao().updateUser(edu.qc.cs370.macrotracker.macrotracker.MainActivity.user);
      }
    }
    else if (info_type.equals("Calories")){
      int val = parseToInt(info);
      if (val != 0) {
        edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.setCalories(val);
        edu.qc.cs370.macrotracker.macrotracker.MainActivity.foodDatabase.userDao().updateUser(edu.qc.cs370.macrotracker.macrotracker.MainActivity.user);
      }
    }
    else if (info_type.equals("Fat")){
      int val = parseToInt(info);
      if (val != 0) {
        edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.setFat(val);
        edu.qc.cs370.macrotracker.macrotracker.MainActivity.foodDatabase.userDao().updateUser(edu.qc.cs370.macrotracker.macrotracker.MainActivity.user);
      }
    }
    else if (info_type.equals("Carbs")){
      int val = parseToInt(info);
      if (val != 0) {
        edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.setCarbs(val);
        edu.qc.cs370.macrotracker.macrotracker.MainActivity.foodDatabase.userDao().updateUser(edu.qc.cs370.macrotracker.macrotracker.MainActivity.user);
      }
    }
    else if (info_type.equals("Protein")){
      int val = parseToInt(info);
      if (val != 0) {
        edu.qc.cs370.macrotracker.macrotracker.MainActivity.user.setProtein(val);
        edu.qc.cs370.macrotracker.macrotracker.MainActivity.foodDatabase.userDao().updateUser(edu.qc.cs370.macrotracker.macrotracker.MainActivity.user);
      }
    }
    else {
      //do nothing
    }
  }

  public int parseToInt(String val){
    int value = 0;
    try {
      value = Integer.parseInt(val);
    }catch (Exception e){
      // do nothing
    }
    return value;
  }
}
