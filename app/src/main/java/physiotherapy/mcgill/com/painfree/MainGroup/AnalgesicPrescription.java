package physiotherapy.mcgill.com.painfree.MainGroup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.AppUtils;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;

/**
 * Created by Abhishek Vadnerkar on 16-03-20.
 */
public class AnalgesicPrescription {

    public static final String[] keys = new String[]{DBAdapter.KEY_ANALGESIC_PRES_NUM,
            DBAdapter.KEY_ANALGESIC_PRES_1_DATE, DBAdapter.KEY_ANALGESIC_PRES_1_TIME, DBAdapter.KEY_ANALGESIC_PRES_1_TYPE, DBAdapter.KEY_ANALGESIC_PRES_1_TYPE_OTHER, DBAdapter.KEY_ANALGESIC_PRES_1_MODE, DBAdapter.KEY_ANALGESIC_PRES_1_BY, DBAdapter.KEY_ANALGESIC_PRES_1_NUM_ER, DBAdapter.KEY_ANALGESIC_PRES_1_NUM_OTHER, DBAdapter.KEY_ANALGESIC_PRES_1_NUM_UNKNOWN,
            DBAdapter.KEY_ANALGESIC_PRES_2_DATE, DBAdapter.KEY_ANALGESIC_PRES_2_TIME, DBAdapter.KEY_ANALGESIC_PRES_2_TYPE, DBAdapter.KEY_ANALGESIC_PRES_2_TYPE_OTHER, DBAdapter.KEY_ANALGESIC_PRES_2_MODE, DBAdapter.KEY_ANALGESIC_PRES_2_BY, DBAdapter.KEY_ANALGESIC_PRES_2_NUM_ER, DBAdapter.KEY_ANALGESIC_PRES_2_NUM_OTHER, DBAdapter.KEY_ANALGESIC_PRES_2_NUM_UNKNOWN,
            DBAdapter.KEY_ANALGESIC_PRES_3_DATE, DBAdapter.KEY_ANALGESIC_PRES_3_TIME, DBAdapter.KEY_ANALGESIC_PRES_3_TYPE, DBAdapter.KEY_ANALGESIC_PRES_3_TYPE_OTHER, DBAdapter.KEY_ANALGESIC_PRES_3_MODE, DBAdapter.KEY_ANALGESIC_PRES_3_BY, DBAdapter.KEY_ANALGESIC_PRES_3_NUM_ER, DBAdapter.KEY_ANALGESIC_PRES_3_NUM_OTHER, DBAdapter.KEY_ANALGESIC_PRES_3_NUM_UNKNOWN,
            DBAdapter.KEY_ANALGESIC_PRES_4_DATE, DBAdapter.KEY_ANALGESIC_PRES_4_TIME, DBAdapter.KEY_ANALGESIC_PRES_4_TYPE, DBAdapter.KEY_ANALGESIC_PRES_4_TYPE_OTHER, DBAdapter.KEY_ANALGESIC_PRES_4_MODE, DBAdapter.KEY_ANALGESIC_PRES_4_BY, DBAdapter.KEY_ANALGESIC_PRES_4_NUM_ER, DBAdapter.KEY_ANALGESIC_PRES_4_NUM_OTHER, DBAdapter.KEY_ANALGESIC_PRES_4_NUM_UNKNOWN,
            DBAdapter.KEY_ANALGESIC_PRES_5_DATE, DBAdapter.KEY_ANALGESIC_PRES_5_TIME, DBAdapter.KEY_ANALGESIC_PRES_5_TYPE, DBAdapter.KEY_ANALGESIC_PRES_5_TYPE_OTHER, DBAdapter.KEY_ANALGESIC_PRES_5_MODE, DBAdapter.KEY_ANALGESIC_PRES_5_BY, DBAdapter.KEY_ANALGESIC_PRES_5_NUM_ER, DBAdapter.KEY_ANALGESIC_PRES_5_NUM_OTHER, DBAdapter.KEY_ANALGESIC_PRES_5_NUM_UNKNOWN,
            DBAdapter.KEY_ANALGESIC_PRES_6_DATE, DBAdapter.KEY_ANALGESIC_PRES_6_TIME, DBAdapter.KEY_ANALGESIC_PRES_6_TYPE, DBAdapter.KEY_ANALGESIC_PRES_6_TYPE_OTHER, DBAdapter.KEY_ANALGESIC_PRES_6_MODE, DBAdapter.KEY_ANALGESIC_PRES_6_BY, DBAdapter.KEY_ANALGESIC_PRES_6_NUM_ER, DBAdapter.KEY_ANALGESIC_PRES_6_NUM_OTHER, DBAdapter.KEY_ANALGESIC_PRES_6_NUM_UNKNOWN,
            DBAdapter.KEY_ANALGESIC_PRES_7_DATE, DBAdapter.KEY_ANALGESIC_PRES_7_TIME, DBAdapter.KEY_ANALGESIC_PRES_7_TYPE, DBAdapter.KEY_ANALGESIC_PRES_7_TYPE_OTHER, DBAdapter.KEY_ANALGESIC_PRES_7_MODE, DBAdapter.KEY_ANALGESIC_PRES_7_BY, DBAdapter.KEY_ANALGESIC_PRES_7_NUM_ER, DBAdapter.KEY_ANALGESIC_PRES_7_NUM_OTHER, DBAdapter.KEY_ANALGESIC_PRES_7_NUM_UNKNOWN,
            DBAdapter.KEY_ANALGESIC_PRES_8_DATE, DBAdapter.KEY_ANALGESIC_PRES_8_TIME, DBAdapter.KEY_ANALGESIC_PRES_8_TYPE, DBAdapter.KEY_ANALGESIC_PRES_8_TYPE_OTHER, DBAdapter.KEY_ANALGESIC_PRES_8_MODE, DBAdapter.KEY_ANALGESIC_PRES_8_BY, DBAdapter.KEY_ANALGESIC_PRES_8_NUM_ER, DBAdapter.KEY_ANALGESIC_PRES_8_NUM_OTHER, DBAdapter.KEY_ANALGESIC_PRES_8_NUM_UNKNOWN};

    public static final int numFields = 9;

    public static final String[] idKeys = new String[]{DBAdapter.KEY_ANALGESIC_PRES_1_ID, DBAdapter.KEY_ANALGESIC_PRES_2_ID, DBAdapter.KEY_ANALGESIC_PRES_3_ID, DBAdapter.KEY_ANALGESIC_PRES_4_ID, DBAdapter.KEY_ANALGESIC_PRES_5_ID, DBAdapter.KEY_ANALGESIC_PRES_6_ID, DBAdapter.KEY_ANALGESIC_PRES_7_ID, DBAdapter.KEY_ANALGESIC_PRES_8_ID};

    public static View setupAnalgesicPrescriptionSection(final Context context, ViewGroup parent, final ArrayAdapter adapter, final int index, final int globalIndex, final EDEvents.MinusHandler handler){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int mMonth;
        Calendar mcurrentDate;
        int mDay;
        int mYear;

        final String[] spinnerOptions = new String[]{"", context.getString(R.string.not_specified), context.getString(R.string.standard_order), context.getString(R.string.collective_order_body), context.getString(R.string.structured_prescription), context.getString(R.string.pharmaceutical_algorithm)};
        final String[] checkBoxItems = new String[]{context.getString(R.string.acetaminophen), context.getString(R.string.nsaids), context.getString(R.string.opioid)};
        final String[] spinnerPresByOptions = new String[]{"", context.getString(R.string.er_md_option), context.getString(R.string.other_md_option), context.getString(R.string.unknown_option)};

        final Cursor cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);
        final Cursor cursor2 = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, idKeys);

        if (cursor.moveToFirst()) {
            final int numAssessments = cursor.getInt(0);

            final View assessmentView = inflater.inflate(R.layout.subcell_analgesic_pres, parent, false);
            TextView assessmentTitle = (TextView) assessmentView.findViewById(R.id.title);
            assessmentTitle.setText("Event" + " " + String.valueOf(globalIndex + 1) + " - Analgesic prescription");

            FloatingActionButton minusButton = (FloatingActionButton) assessmentView.findViewById(R.id.fabMinus);
            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Cursor tempCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);
                    Cursor idCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, idKeys);
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_ANALGESIC_PRES_NUM, String.valueOf(Math.max(numAssessments - 1, 0)));
                    for (int j=index; j<numAssessments-1; j++){
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, idKeys[j], idCursor.getString(j+1));
                        for (int k=1; k<=numFields; k++){
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[j*numFields+k], tempCursor.getString((j+1)*numFields+k));
                        }
                    }
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, idKeys[numAssessments-1], null);
                    MainActivity.myDb.updateFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, (numAssessments - 1)*numFields + 1, (numAssessments)*numFields + 1), null);
                    tempCursor.close();
                    idCursor.close();
                    handler.onClick();
                }
            });

            //Prescription ID
            {
                final TextView textView = (TextView) assessmentView.findViewById(R.id.pres_id);
                String idString = cursor2.getString(index);
                if (idString != null) {
                    textView.setText("Prescription ID: " + idString);
                } else {
                    textView.setText("Prescription ID not found");
                }
            }

            //Datepicker
            {
                final Button buttonDate = (Button) assessmentView.findViewById(R.id.buttonDate);

                mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final CheckBox noneBox = (CheckBox) assessmentView.findViewById(R.id.buttonDateNone);
                noneBox.setChecked(false);

                noneBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean isChecked = ((CheckBox) v).isChecked();
                        if (isChecked) {
                            buttonDate.setText(context.getString(R.string.select_date));
                        }

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                if (isChecked) {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 1], context.getString(R.string.none));
                                } else {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 1], null);
                                }
                            }
                        };
                        thread.start();
                    }
                });

                String dateString = cursor.getString(index * numFields + 1);

                if (dateString != null){

                    if (dateString.equals("None")){
                        noneBox.setChecked(true);
                    } else if (!dateString.equals("")) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = format.parse(dateString);
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            mYear = cal.get(Calendar.YEAR);
                            mMonth = cal.get(Calendar.MONTH);
                            mDay = cal.get(Calendar.DAY_OF_MONTH);

                            buttonDate.setText(mYear + "-" + String.format("%02d", mMonth + 1) + "-" + String.format("%02d", mDay));
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }


                final int year = mYear;
                final int month = mMonth;
                final int day = mDay;

                buttonDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        DatePickerDialog mDatePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                                final String text = selectedyear + "-" + String.format("%02d", selectedmonth + 1) + "-" + String.format("%02d", selectedday);
                                buttonDate.setText(text);
                                noneBox.setChecked(false);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 1], text);
                                adapter.notifyDataSetChanged();

                            }
                        }, year, month, day);
                        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date minDate = f.parse("2016-01-01");
//                            if (index>0){
//                                String previousDateString = cursor.getString((index-1)*numFields+1);
//                                minDate = f.parse(previousDateString);
//                            }
                            mDatePicker.getDatePicker().setMinDate(minDate.getTime());

                            Date maxDate = f.parse("2019-12-31");
//                            if (index<numAssessments-1){
//                                String nextDateString = cursor.getString((index+1)*numFields+1);
//                                if (nextDateString != null && !nextDateString.equals("")){
//                                    maxDate = f.parse(nextDateString);
//                                }
//                            }
                            mDatePicker.getDatePicker().setMaxDate(maxDate.getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        mDatePicker.show();
                    }
                });
            }

            {
                //Time picker
                final Button timePickerButton = (Button) assessmentView.findViewById(R.id.buttonTime);
                timePickerButton.setText(context.getString(R.string.select_time));

                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                final CheckBox noneBox = (CheckBox) assessmentView.findViewById(R.id.buttonTimeNone);
                noneBox.setChecked(false);

                noneBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean isChecked = ((CheckBox) v).isChecked();
                        if (isChecked) {
                            timePickerButton.setText(context.getString(R.string.select_time));
                        }

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                if (isChecked) {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 2], context.getString(R.string.none));
                                } else {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 2], null);
                                }
                            }
                        };
                        thread.start();
                    }
                });

                String timeString = cursor.getString(index * numFields + 2);

                if (timeString != null){

                    if (timeString.equals("None")){
                        noneBox.setChecked(true);
                    } else if (!timeString.equals("")) {
                        timePickerButton.setText(timeString);
                        String[] parts = timeString.split(":");
                        hour = Integer.parseInt(parts[0]);
                        minute = Integer.parseInt(parts[1]);
                    }
                }



                final int mHour = hour;
                final int mMinute = minute;

                timePickerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        TimePickerDialog tpd = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                final String value = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
                                timePickerButton.setText(value);
                                noneBox.setChecked(false);
                                Thread thread = new Thread() {
                                    @Override
                                    public void run() {
                                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 2], value);
                                    }
                                };
                                thread.start();
                            }
                        }, mHour, mMinute, true);
                        tpd.show();
                    }
                });
            }

            {
                //Checkbox
                final LinearLayout cg = (LinearLayout) assessmentView.findViewById(R.id.checkGroup);

                if (checkBoxItems.length > 2) {
                    cg.setOrientation(RadioGroup.VERTICAL);
                } else {
                    cg.setOrientation(RadioGroup.HORIZONTAL);
                }

                for (String checkBoxItem : checkBoxItems) {
                    CheckBox cb = new CheckBox(context);
                    cb.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_medium));
                    cb.setText(checkBoxItem);
                    cb.setChecked(false);
                    cg.addView(cb);

                    cb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    String answer = "";
                                    for (int k = 0; k < checkBoxItems.length; k++) {
                                        CheckBox cb = (CheckBox) cg.getChildAt(k);
                                        if (cb.isChecked()) {
                                            answer = answer + " " + checkBoxItems[k];
                                        }
                                    }

                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 3], answer);
                                }
                            };
                            thread.start();
                        }
                    });


                }

                final EditText editOther = (EditText) assessmentView.findViewById(R.id.edit_type_other);


                String answer = cursor.getString(index * numFields + 3);
                if (answer != null) {
                    for (int j = 0; j < checkBoxItems.length; j++) {
                        if (answer.contains(checkBoxItems[j])) {
                            ((CheckBox) cg.getChildAt(j)).setChecked(true);
                        } else {
                            ((CheckBox) cg.getChildAt(j)).setChecked(false);
                        }
                    }
                }

                String other = cursor.getString(index * numFields + 4);
                if (other != null) {
                    editOther.setText(other);
                } else {
                    editOther.setText("");
                }

                editOther.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 4], s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }


            {
                //Spinner
                final Spinner spinner = (Spinner) assessmentView.findViewById(R.id.spinner);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerOptions);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);

                spinner.setSelection(0);
                String value = cursor.getString(index * numFields + 5);
                if (value != null && !value.equals("")) {
                    for (int k = 0; k < spinnerOptions.length; k++) {
                        if (value.equals(spinnerOptions[k])) {
                            spinner.setSelection(k);
                            break;
                        }
                    }
                }


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 5], spinnerOptions[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                TextView textView = (TextView) assessmentView.findViewById(R.id.text_mode_of_prescription);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spinner.performClick();
                    }
                });
            }


            {
                //Spinner - Prescribed by
                final Spinner spinner = (Spinner) assessmentView.findViewById(R.id.spinner_prescribed_by);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerPresByOptions);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);

                spinner.setSelection(0);
                String value = cursor.getString(index * numFields + 6);
                if (value != null && !value.equals("")) {
                    for (int k = 0; k < spinnerPresByOptions.length; k++) {
                        if (value.equals(spinnerPresByOptions[k])) {
                            spinner.setSelection(k);
                            break;
                        }
                    }
                }


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 6], spinnerPresByOptions[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                TextView textView = (TextView) assessmentView.findViewById(R.id.text_prescribed_by);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spinner.performClick();
                    }
                });
            }

            {
                //EditText - ER MD
                EditText editText = (EditText) assessmentView.findViewById(R.id.edit_number_er);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(final CharSequence s, int start, int before, int count) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 7], s.toString());
                            }
                        };
                        thread.start();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                String type = cursor.getString(index * numFields + 7);
                if (type != null) {
                    editText.setText(type);
                } else {
                    editText.setText("");
                }
            }

            {
                //EditText - Other MD
                EditText editText = (EditText) assessmentView.findViewById(R.id.edit_number_other);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(final CharSequence s, int start, int before, int count) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 8], s.toString());
                            }
                        };
                        thread.start();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                String type = cursor.getString(index * numFields + 8);
                if (type != null) {
                    editText.setText(type);
                } else {
                    editText.setText("");
                }
            }

            {
                //EditText - Unknown
                EditText editText = (EditText) assessmentView.findViewById(R.id.edit_number_unknown);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(final CharSequence s, int start, int before, int count) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 9], s.toString());
                            }
                        };
                        thread.start();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                String type = cursor.getString(index * numFields + 9);
                if (type != null) {
                    editText.setText(type);
                } else {
                    editText.setText("");
                }
            }

            cursor.close();
            cursor2.close();
            return assessmentView;
        }

        cursor.close();
        cursor2.close();
        return null;

    }

    public static boolean canAdd(){
        Cursor cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, DBAdapter.KEY_ANALGESIC_PRES_NUM);
        int num = cursor.getInt(0);
        cursor.close();
        boolean canAdd = true;
        if (num > 0){
            cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, (num-1)*numFields+1, num*numFields+1));
            for (int i=0; i<6; i++){

                if (i==3)
                    continue;

                if (cursor.getString(i) == null || cursor.getString(i).equals("")){
                    canAdd = false;
                    break;
                }
            }
        }
        return canAdd;
    }

    public static void clearData(){
        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[0], "0");
        MainActivity.myDb.updateFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, 1, keys.length), null);
    }

}
