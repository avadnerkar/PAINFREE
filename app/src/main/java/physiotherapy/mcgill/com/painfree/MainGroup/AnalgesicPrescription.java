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
import java.util.HashMap;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.AppUtils;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;

/**
 * Created by Abhishek Vadnerkar on 16-03-20.
 */
public class AnalgesicPrescription {

    public static final String[] keys = new String[]{DBAdapter.KEY_ANALGESIC_PRES_NUM,
            DBAdapter.KEY_ANALGESIC_PRES_1_DATE, DBAdapter.KEY_ANALGESIC_PRES_1_TIME, DBAdapter.KEY_ANALGESIC_PRES_1_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_1_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_1_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_1_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_1_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_1_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_1_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_1_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_1_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_1_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_1_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_1_OPIOID_FREQ, DBAdapter.KEY_ANALGESIC_PRES_1_MODE, DBAdapter.KEY_ANALGESIC_PRES_1_STATUS,
            DBAdapter.KEY_ANALGESIC_PRES_2_DATE, DBAdapter.KEY_ANALGESIC_PRES_2_TIME, DBAdapter.KEY_ANALGESIC_PRES_2_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_2_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_2_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_2_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_2_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_2_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_2_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_2_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_2_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_2_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_2_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_2_OPIOID_FREQ, DBAdapter.KEY_ANALGESIC_PRES_2_MODE, DBAdapter.KEY_ANALGESIC_PRES_2_STATUS,
            DBAdapter.KEY_ANALGESIC_PRES_3_DATE, DBAdapter.KEY_ANALGESIC_PRES_3_TIME, DBAdapter.KEY_ANALGESIC_PRES_3_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_3_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_3_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_3_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_3_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_3_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_3_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_3_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_3_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_3_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_3_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_3_OPIOID_FREQ, DBAdapter.KEY_ANALGESIC_PRES_3_MODE, DBAdapter.KEY_ANALGESIC_PRES_3_STATUS,
            DBAdapter.KEY_ANALGESIC_PRES_4_DATE, DBAdapter.KEY_ANALGESIC_PRES_4_TIME, DBAdapter.KEY_ANALGESIC_PRES_4_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_4_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_4_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_4_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_4_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_4_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_4_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_4_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_4_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_4_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_4_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_4_OPIOID_FREQ, DBAdapter.KEY_ANALGESIC_PRES_4_MODE, DBAdapter.KEY_ANALGESIC_PRES_4_STATUS,
            DBAdapter.KEY_ANALGESIC_PRES_5_DATE, DBAdapter.KEY_ANALGESIC_PRES_5_TIME, DBAdapter.KEY_ANALGESIC_PRES_5_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_5_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_5_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_5_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_5_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_5_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_5_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_5_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_5_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_5_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_5_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_5_OPIOID_FREQ, DBAdapter.KEY_ANALGESIC_PRES_5_MODE, DBAdapter.KEY_ANALGESIC_PRES_5_STATUS,
            DBAdapter.KEY_ANALGESIC_PRES_6_DATE, DBAdapter.KEY_ANALGESIC_PRES_6_TIME, DBAdapter.KEY_ANALGESIC_PRES_6_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_6_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_6_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_6_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_6_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_6_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_6_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_6_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_6_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_6_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_6_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_6_OPIOID_FREQ, DBAdapter.KEY_ANALGESIC_PRES_6_MODE, DBAdapter.KEY_ANALGESIC_PRES_6_STATUS,
            DBAdapter.KEY_ANALGESIC_PRES_7_DATE, DBAdapter.KEY_ANALGESIC_PRES_7_TIME, DBAdapter.KEY_ANALGESIC_PRES_7_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_7_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_7_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_7_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_7_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_7_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_7_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_7_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_7_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_7_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_7_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_7_OPIOID_FREQ, DBAdapter.KEY_ANALGESIC_PRES_7_MODE, DBAdapter.KEY_ANALGESIC_PRES_7_STATUS,
            DBAdapter.KEY_ANALGESIC_PRES_8_DATE, DBAdapter.KEY_ANALGESIC_PRES_8_TIME, DBAdapter.KEY_ANALGESIC_PRES_8_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_8_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_8_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_8_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_8_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_8_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_8_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_8_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_8_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_8_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_8_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_8_OPIOID_FREQ, DBAdapter.KEY_ANALGESIC_PRES_8_MODE, DBAdapter.KEY_ANALGESIC_PRES_8_STATUS};

    public static final int numFields = 16;

    public static final String[] idKeys = new String[]{DBAdapter.KEY_ANALGESIC_PRES_1_ID, DBAdapter.KEY_ANALGESIC_PRES_2_ID, DBAdapter.KEY_ANALGESIC_PRES_3_ID, DBAdapter.KEY_ANALGESIC_PRES_4_ID, DBAdapter.KEY_ANALGESIC_PRES_5_ID, DBAdapter.KEY_ANALGESIC_PRES_6_ID, DBAdapter.KEY_ANALGESIC_PRES_7_ID, DBAdapter.KEY_ANALGESIC_PRES_8_ID};
    public static final String[][] idArray = new String[][]{
            new String[]{DBAdapter.KEY_ANALGESIC_PRES_1_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_1_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_1_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_1_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_1_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_1_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_1_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_1_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_1_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_1_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_1_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_1_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_PRES_2_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_2_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_2_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_2_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_2_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_2_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_2_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_2_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_2_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_2_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_2_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_2_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_PRES_3_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_3_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_3_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_3_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_3_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_3_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_3_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_3_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_3_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_3_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_3_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_3_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_PRES_4_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_4_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_4_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_4_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_4_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_4_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_4_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_4_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_4_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_4_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_4_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_4_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_PRES_5_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_5_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_5_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_5_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_5_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_5_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_5_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_5_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_5_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_5_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_5_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_5_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_PRES_6_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_6_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_6_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_6_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_6_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_6_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_6_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_6_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_6_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_6_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_6_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_6_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_PRES_7_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_7_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_7_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_7_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_7_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_7_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_7_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_7_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_7_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_7_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_7_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_7_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_PRES_8_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_PRES_8_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_PRES_8_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_8_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_PRES_8_NSAIDS, DBAdapter.KEY_ANALGESIC_PRES_8_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_PRES_8_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_8_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_PRES_8_OPIOID, DBAdapter.KEY_ANALGESIC_PRES_8_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_PRES_8_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_PRES_8_OPIOID_FREQ}};

    public static View setupAnalgesicPrescriptionSection(final Context context, ViewGroup parent, final ArrayAdapter adapter, final int index, final int globalIndex, final EDEvents.MinusHandler handler){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int mMonth;
        Calendar mcurrentDate;
        int mDay;
        int mYear;

        final String[] spinnerOptions = new String[]{context.getString(R.string.standard_order), context.getString(R.string.collective_order_body), context.getString(R.string.structured_prescription)};
        final String[] spinnerOptionsStatus = new String[]{context.getString(R.string.active), context.getString(R.string.ceased)};

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
                //Analgesic type, dose, route, frequency

                CheckBox[] analgesicCheckboxes = new CheckBox[]{(CheckBox) assessmentView.findViewById(R.id.checkbox_acetaminophen), (CheckBox) assessmentView.findViewById(R.id.checkbox_nsaids), (CheckBox) assessmentView.findViewById(R.id.checkbox_opioid)};
                final EditText[] doses = new EditText[]{(EditText) assessmentView.findViewById(R.id.edit_acetaminophen_dose), (EditText) assessmentView.findViewById(R.id.edit_nsaids_dose), (EditText) assessmentView.findViewById(R.id.edit_opioid_dose)};
                final EditText[] frequencies = new EditText[]{(EditText) assessmentView.findViewById(R.id.edit_acetaminophen_frequency), (EditText) assessmentView.findViewById(R.id.edit_nsaids_frequency), (EditText) assessmentView.findViewById(R.id.edit_opioid_frequency)};
                final Spinner[] routes = new Spinner[]{(Spinner) assessmentView.findViewById(R.id.acetaminophen_route), (Spinner) assessmentView.findViewById(R.id.nsaids_route), (Spinner) assessmentView.findViewById(R.id.opioid_route)};
                final LinearLayout fields[] = new LinearLayout[]{(LinearLayout) assessmentView.findViewById(R.id.acetaminophenGroup), (LinearLayout) assessmentView.findViewById(R.id.nsaidsGroup), (LinearLayout) assessmentView.findViewById(R.id.opioidGroup)};
                final TextView[] routeTitles = new TextView[]{(TextView) assessmentView.findViewById(R.id.text_acetaminophen_route), (TextView) assessmentView.findViewById(R.id.text_nsaids_route), (TextView) assessmentView.findViewById(R.id.text_opioid_route)};
                final String[] spinnerRouteOptions = new String[]{"", context.getString(R.string.im), context.getString(R.string.iv), context.getString(R.string.po), context.getString(R.string.sc), context.getString(R.string.pr)};
                final int numItems = 4;

                for (int j=0; j<3; j++){
                    final int J = j;

                    //Checkbox
                    String analgesicValue = cursor.getString(index*numFields + 3 + j*numItems);
                    if (analgesicValue != null && analgesicValue.equals(context.getString(R.string.yes))){
                        fields[j].setVisibility(View.VISIBLE);
                        analgesicCheckboxes[j].setChecked(true);
                    } else {
                        fields[j].setVisibility(View.GONE);
                        analgesicCheckboxes[j].setChecked(false);
                    }


                    analgesicCheckboxes[j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (((CheckBox)v).isChecked()){
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index*numFields + 3 + J*numItems], context.getString(R.string.yes));
                                fields[J].setVisibility(View.VISIBLE);
                            } else {
                                MainActivity.myDb.updateFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, index*numFields + 3 + J*numItems, index*numFields + 3 + (J+1)*numItems), new String[]{null, null, null, null});
                                fields[J].setVisibility(View.GONE);
                                doses[J].setText("");
                                frequencies[J].setText("");
                                routes[J].setSelection(0);
                            }
                        }
                    });

                    //Dose
                    doses[j].addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(final CharSequence s, int start, int before, int count) {
                            Thread thread = new Thread() {
                                @Override
                                public void run() {

                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 4 + J * numItems], s.toString());
                                }
                            };
                            thread.start();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    String dose = cursor.getString(index * numFields + 4 + J*numItems);
                    if (dose != null) {
                        doses[j].setText(dose);
                    } else {
                        doses[j].setText("");
                    }


                    //Spinner - Route
                    ArrayAdapter<String> spinnerRouteAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerRouteOptions);
                    spinnerRouteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    routes[j].setAdapter(spinnerRouteAdapter);

                    routes[j].setSelection(0);
                    String routeValue = cursor.getString(index * numFields + 5 + j*numItems);
                    if (routeValue != null && !routeValue.equals("")) {
                        for (int k = 0; k < spinnerRouteOptions.length; k++) {
                            if (routeValue.equals(spinnerRouteOptions[k])) {
                                routes[j].setSelection(k);
                                break;
                            }
                        }
                    }


                    routes[j].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 5 + J*numItems], spinnerRouteOptions[position]);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    routeTitles[j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            routes[J].performClick();
                        }
                    });


                    //Frequency
                    frequencies[j].addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(final CharSequence s, int start, int before, int count) {
                            Thread thread = new Thread() {
                                @Override
                                public void run() {

                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 6 + J*numItems], s.toString());
                                }
                            };
                            thread.start();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    String frequency = cursor.getString(index * numFields + 6 + J*numItems);
                    if (frequency != null) {
                        frequencies[j].setText(frequency);
                    } else {
                        frequencies[j].setText("");
                    }

                }

            }


            {
                //Spinner
                final Spinner spinner = (Spinner) assessmentView.findViewById(R.id.spinner);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerOptions);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);

                spinner.setSelection(0);
                String value = cursor.getString(index * numFields + 15);
                if (value != null && !value.equals("")) {
                    for (int k = 0; k < spinnerOptions.length; k++) {
                        if (value.equals(spinnerOptions[k])) {
                            spinner.setSelection(k);
                            break;
                        }
                    }
                } else {
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 15], spinnerOptions[0]);
                }


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 15], spinnerOptions[position]);
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
                //Spinner - prescription status
                final Spinner spinner = (Spinner) assessmentView.findViewById(R.id.spinner_pres_status);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerOptionsStatus);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);

                spinner.setSelection(0);
                String value = cursor.getString(index * numFields + 16);
                if (value != null && !value.equals("")) {
                    for (int k = 0; k < spinnerOptionsStatus.length; k++) {
                        if (value.equals(spinnerOptionsStatus[k])) {
                            spinner.setSelection(k);
                            break;
                        }
                    }
                } else {
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 16], spinnerOptionsStatus[0]);
                }


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 16], spinnerOptionsStatus[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                TextView textView = (TextView) assessmentView.findViewById(R.id.text_prescription_status);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spinner.performClick();
                    }
                });
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
            for (int i=0; i<2; i++){

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
