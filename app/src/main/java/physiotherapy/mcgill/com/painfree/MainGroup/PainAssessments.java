package physiotherapy.mcgill.com.painfree.MainGroup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
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
public class PainAssessments {

    public static final String[] keys = new String[]{DBAdapter.KEY_PAIN_ASSESSMENT_NUM,
            DBAdapter.KEY_PAIN_ASSESSMENT_1_DATE, DBAdapter.KEY_PAIN_ASSESSMENT_1_TIME, DBAdapter.KEY_PAIN_ASSESSMENT_1_SCORE,
            DBAdapter.KEY_PAIN_ASSESSMENT_2_DATE, DBAdapter.KEY_PAIN_ASSESSMENT_2_TIME, DBAdapter.KEY_PAIN_ASSESSMENT_2_SCORE,
            DBAdapter.KEY_PAIN_ASSESSMENT_3_DATE, DBAdapter.KEY_PAIN_ASSESSMENT_3_TIME, DBAdapter.KEY_PAIN_ASSESSMENT_3_SCORE,
            DBAdapter.KEY_PAIN_ASSESSMENT_4_DATE, DBAdapter.KEY_PAIN_ASSESSMENT_4_TIME, DBAdapter.KEY_PAIN_ASSESSMENT_4_SCORE,
            DBAdapter.KEY_PAIN_ASSESSMENT_5_DATE, DBAdapter.KEY_PAIN_ASSESSMENT_5_TIME, DBAdapter.KEY_PAIN_ASSESSMENT_5_SCORE,
            DBAdapter.KEY_PAIN_ASSESSMENT_6_DATE, DBAdapter.KEY_PAIN_ASSESSMENT_6_TIME, DBAdapter.KEY_PAIN_ASSESSMENT_6_SCORE};
    public static final int numFields = 3;

    public static View setupPainAssessmentSection(final Context context, ViewGroup parent, final ArrayAdapter adapter, final int index, final int globalIndex, final EDEvents.MinusHandler handler){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int mMonth;
        Calendar mcurrentDate;
        int mDay;
        int mYear;


        final String[] painSpinnerOptions = new String[]{"", "None", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        final Cursor cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);

        if (cursor.moveToFirst()) {
            final int numAssessments = cursor.getInt(0);

            final View assessmentView = inflater.inflate(R.layout.subcell_pain_assessment, parent, false);
            TextView assessmentTitle = (TextView) assessmentView.findViewById(R.id.title);
            assessmentTitle.setText("Event" + " " + String.valueOf(globalIndex + 1) + " - Pain assessment");


            //Spinner
            final Spinner painSpinner = (Spinner) assessmentView.findViewById(R.id.spinner);
            ArrayAdapter<String> painSpinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, painSpinnerOptions);
            painSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            painSpinner.setAdapter(painSpinnerAdapter);

            TextView spinnerTitle = (TextView) assessmentView.findViewById(R.id.spinner_title);
            spinnerTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    painSpinner.performClick();
                }
            });

            painSpinner.setSelection(0);
            String value = cursor.getString(index*numFields+3);
            if (value != null && !value.equals("")) {
                for (int k = 0; k < painSpinnerOptions.length; k++) {
                    if (value.equals(painSpinnerOptions[k])) {
                        painSpinner.setSelection(k);
                        break;
                    }
                }
            }

            painSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 3], painSpinnerOptions[position]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //Datepicker

            final Button buttonDate = (Button) assessmentView.findViewById(R.id.buttonDate);

            mcurrentDate = Calendar.getInstance();
            mYear = mcurrentDate.get(Calendar.YEAR);
            mMonth = mcurrentDate.get(Calendar.MONTH);
            mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

            String dateString = cursor.getString(index * numFields + 1);
            if (dateString != null && !dateString.equals("")) {
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
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 1], text);
                            adapter.notifyDataSetChanged();


                        }
                    }, year, month, day);
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date minDate = f.parse("2016-01-01");

//                        if (index>0){
//                            String previousDateString = cursor.getString((index-1)*3+1);
//                            minDate = f.parse(previousDateString);
//                        }
                        mDatePicker.getDatePicker().setMinDate(minDate.getTime());

                        Date maxDate = f.parse("2019-12-31");
//                        if (index<numAssessments-1){
//                            String nextDateString = cursor.getString((index+1)*3+1);
//                            if (nextDateString != null && !nextDateString.equals("")){
//                                maxDate = f.parse(nextDateString);
//                            }
//                        }
                        mDatePicker.getDatePicker().setMaxDate(maxDate.getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    mDatePicker.show();
                }
            });


            //Time picker
            final Button timePickerButton = (Button) assessmentView.findViewById(R.id.buttonTime);
            timePickerButton.setText(context.getString(R.string.select_time));

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            String timeString = cursor.getString(index*numFields+2);
            if (timeString != null && !timeString.equals("")) {
                timePickerButton.setText(timeString);
                String[] parts = timeString.split(":");
                hour = Integer.parseInt(parts[0]);
                minute = Integer.parseInt(parts[1]);
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
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index*numFields+2], value);
                                }
                            };
                            thread.start();
                        }
                    }, mHour, mMinute, true);
                    tpd.show();
                }
            });

            FloatingActionButton minusButton = (FloatingActionButton) assessmentView.findViewById(R.id.fabMinus);
            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Cursor tempCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);

                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_PAIN_ASSESSMENT_NUM, String.valueOf(Math.max(numAssessments - 1, 0)));

                    for (int j=index; j<numAssessments-1; j++){
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[j*numFields+1], tempCursor.getString((j+1)*numFields+1));
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[j*numFields+2], tempCursor.getString((j+1)*numFields+2));
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[j*numFields+3], tempCursor.getString((j+1)*numFields+3));
                    }
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[(numAssessments - 1)*numFields+1], null);
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[(numAssessments - 1)*numFields+2], null);
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[(numAssessments - 1)*numFields+3], null);

                    tempCursor.close();
                    handler.onClick();
                }
            });


            cursor.close();
            return assessmentView;

        }
        cursor.close();

        return null;

    }

    public static boolean canAdd(){
        Cursor cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, DBAdapter.KEY_PAIN_ASSESSMENT_NUM);
        int num = cursor.getInt(0);
        cursor.close();
        boolean canAdd = true;
        if (num > 0){
            cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, (num-1)*numFields+1, num*numFields+1));
            for (int i=0; i<cursor.getColumnCount(); i++){
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
