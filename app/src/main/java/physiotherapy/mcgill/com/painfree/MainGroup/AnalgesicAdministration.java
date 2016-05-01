package physiotherapy.mcgill.com.painfree.MainGroup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;

/**
 * Created by Abhishek Vadnerkar on 16-04-30.
 */
public class AnalgesicAdministration {

    public static final String[] keys = new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_NUM,
            DBAdapter.KEY_ANALGESIC_ADMIN_1_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_1_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_1_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_1_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_1_NSAIDS_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_1_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_1_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_1_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_1_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_1_OPIOID_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_1_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_1_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_1_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_1_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_1_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_1_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_1_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_1_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_1_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_2_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_2_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_2_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_2_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_2_NSAIDS_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_2_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_2_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_2_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_2_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_2_OPIOID_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_2_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_2_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_2_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_2_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_2_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_2_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_2_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_2_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_2_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_3_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_3_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_3_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_3_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_3_NSAIDS_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_3_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_3_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_3_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_3_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_3_OPIOID_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_3_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_3_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_3_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_3_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_3_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_3_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_3_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_3_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_3_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_4_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_4_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_4_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_4_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_4_NSAIDS_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_4_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_4_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_4_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_4_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_4_OPIOID_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_4_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_4_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_4_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_4_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_4_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_4_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_4_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_4_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_4_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_5_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_5_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_5_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_5_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_5_NSAIDS_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_5_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_5_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_5_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_5_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_5_OPIOID_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_5_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_5_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_5_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_5_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_5_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_5_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_5_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_5_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_5_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_6_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_6_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_6_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_6_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_6_NSAIDS_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_6_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_6_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_6_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_6_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_6_OPIOID_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_6_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_6_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_6_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_6_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_6_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_6_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_6_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_6_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_6_REFUSAL};

    public static final boolean[] mandatoryKeys = new boolean[]{
            true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, true, false, false};


    public static final int numFields = 24;

    public static View setupAnalgesicAdministrationSection(final Context context, ViewGroup parent, final ArrayAdapter adapter, final int index, final int globalIndex, final EDEvents.MinusHandler handler){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int mMonth;
        Calendar mcurrentDate;
        int mDay;
        int mYear;

        final String[] spinnerOrderOptions = new String[]{"", context.getString(R.string.none), context.getString(R.string.written), context.getString(R.string.verbal)};
        final String[] spinnerRouteOptions = new String[]{"", context.getString(R.string.im), context.getString(R.string.iv), context.getString(R.string.po), context.getString(R.string.sc), context.getString(R.string.pr)};
        final String[] checkBoxAlternativeRelief = new String[]{context.getString(R.string.ice), context.getString(R.string.cool_cloths), context.getString(R.string.soft_cushions)};

        final Cursor cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);

        if (cursor.moveToFirst()) {
            final int numAssessments = cursor.getInt(0);

            final View assessmentView = inflater.inflate(R.layout.subcell_analgesic_admin, parent, false);
            TextView assessmentTitle = (TextView) assessmentView.findViewById(R.id.title);
            assessmentTitle.setText("Event" + " " + String.valueOf(globalIndex + 1) + " - Analgesic administration");

            FloatingActionButton minusButton = (FloatingActionButton) assessmentView.findViewById(R.id.fabMinus);
            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Cursor tempCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_ANALGESIC_ADMIN_NUM, String.valueOf(Math.max(numAssessments - 1, 0)));

                    for (int j=index; j<numAssessments-1; j++){
                        for (int k=1; k<=numFields; k++){
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[j*numFields+k], tempCursor.getString((j+1)*numFields+k));
                        }
                    }
                    MainActivity.myDb.updateFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, (numAssessments - 1)*numFields + 1, (numAssessments)*numFields), null);
                    tempCursor.close();
                    handler.onClick();
                }
            });


            //Datepicker
            {
                final Button buttonDate = (Button) assessmentView.findViewById(R.id.buttonAdminDate);

                mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final CheckBox noneBox = (CheckBox) assessmentView.findViewById(R.id.buttonAdminDateNone);
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
//                                String previousDateString = cursor.getString((index-1)*numFields+5);
//                                minDate = f.parse(previousDateString);
//                            }
                            mDatePicker.getDatePicker().setMinDate(minDate.getTime());

                            Date maxDate = f.parse("2019-12-31");
//                            if (index<numAssessments-1){
//                                String nextDateString = cursor.getString((index+1)*numFields+5);
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
                final Button timePickerButton = (Button) assessmentView.findViewById(R.id.buttonAdminTime);
                timePickerButton.setText(context.getString(R.string.select_time));

                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                final CheckBox noneBox = (CheckBox) assessmentView.findViewById(R.id.buttonAdminTimeNone);
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
                //Analgesic type, dose, frequency, route, order

                CheckBox[] analgesicCheckboxes = new CheckBox[]{(CheckBox) assessmentView.findViewById(R.id.checkbox_acetaminophen), (CheckBox) assessmentView.findViewById(R.id.checkbox_nsaids), (CheckBox) assessmentView.findViewById(R.id.checkbox_opioid)};
                final EditText[] doses = new EditText[]{(EditText) assessmentView.findViewById(R.id.edit_acetaminophen_dose), (EditText) assessmentView.findViewById(R.id.edit_nsaids_dose), (EditText) assessmentView.findViewById(R.id.edit_opioid_dose)};
                final EditText[] frequencies = new EditText[]{(EditText) assessmentView.findViewById(R.id.edit_acetaminophen_frequency), (EditText) assessmentView.findViewById(R.id.edit_nsaids_frequency), (EditText) assessmentView.findViewById(R.id.edit_opioid_frequency)};
                final Spinner[] routes = new Spinner[]{(Spinner) assessmentView.findViewById(R.id.acetaminophen_route), (Spinner) assessmentView.findViewById(R.id.nsaids_route), (Spinner) assessmentView.findViewById(R.id.opioid_route)};
                final Spinner[] orders = new Spinner[]{(Spinner) assessmentView.findViewById(R.id.acetaminophen_order), (Spinner) assessmentView.findViewById(R.id.nsaids_order), (Spinner) assessmentView.findViewById(R.id.opioid_order)};
                final LinearLayout fields[] = new LinearLayout[]{(LinearLayout) assessmentView.findViewById(R.id.acetaminophenGroup), (LinearLayout) assessmentView.findViewById(R.id.nsaidsGroup), (LinearLayout) assessmentView.findViewById(R.id.opioidGroup)};
                final TextView[] routeTitles = new TextView[]{(TextView) assessmentView.findViewById(R.id.text_acetaminophen_route), (TextView) assessmentView.findViewById(R.id.text_nsaids_route), (TextView) assessmentView.findViewById(R.id.text_opioid_route)};
                final TextView[] orderTitles = new TextView[]{(TextView) assessmentView.findViewById(R.id.text_acetaminophen_order), (TextView) assessmentView.findViewById(R.id.text_nsaids_order), (TextView) assessmentView.findViewById(R.id.text_opioid_order)};

                for (int j=0; j<3; j++){
                    final int J = j;

                    //Checkbox
                    String analgesicValue = cursor.getString(index*numFields + 3 + j*5);
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
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index*numFields + 3 + J*5], context.getString(R.string.yes));
                                fields[J].setVisibility(View.VISIBLE);
                            } else {
                                MainActivity.myDb.updateFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, index*numFields + 3 + J*5, index*numFields + 3 + (J+1)*5), new String[]{null, null, null, null, null});
                                fields[J].setVisibility(View.GONE);
                                doses[J].setText("");
                                frequencies[J].setText("");
                                routes[J].setSelection(0);
                                orders[J].setSelection(0);
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

                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 4 + J * 5], s.toString());
                                }
                            };
                            thread.start();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    String dose = cursor.getString(index * numFields + 4 + J*5);
                    if (dose != null) {
                        doses[j].setText(dose);
                    } else {
                        doses[j].setText("");
                    }


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

                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 5 + J*5], s.toString());
                                }
                            };
                            thread.start();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    String frequency = cursor.getString(index * numFields + 5 + J*5);
                    if (frequency != null) {
                        frequencies[j].setText(frequency);
                    } else {
                        frequencies[j].setText("");
                    }


                    //Spinner - Route
                    ArrayAdapter<String> spinnerRouteAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerRouteOptions);
                    spinnerRouteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    routes[j].setAdapter(spinnerRouteAdapter);

                    routes[j].setSelection(0);
                    String routeValue = cursor.getString(index * numFields + 6 + j*5);
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
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 6 + J*5], spinnerRouteOptions[position]);
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


                    //Spinner - Order
                    ArrayAdapter<String> spinnerOrderAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerOrderOptions);
                    spinnerOrderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    orders[j].setAdapter(spinnerOrderAdapter);

                    orders[j].setSelection(0);
                    String value = cursor.getString(index * numFields + 7 + j*5);
                    if (value != null && !value.equals("")) {
                        for (int k = 0; k < spinnerOrderOptions.length; k++) {
                            if (value.equals(spinnerOrderOptions[k])) {
                                orders[j].setSelection(k);
                                break;
                            }
                        }
                    }


                    orders[j].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 7 + J*5], spinnerOrderOptions[position]);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    orderTitles[j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            orders[J].performClick();
                        }
                    });

                }
            }



            {
                //Nerve block - Datepicker

                final Button nerveBlockButtonDate = (Button) assessmentView.findViewById(R.id.nerveBlockButtonDate);

                mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final CheckBox noneBox = (CheckBox) assessmentView.findViewById(R.id.nerveBlockButtonDateNone);
                noneBox.setChecked(false);

                noneBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean isChecked = ((CheckBox) v).isChecked();
                        if (isChecked) {
                            nerveBlockButtonDate.setText(context.getString(R.string.select_date));
                        }

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                if (isChecked) {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 18], context.getString(R.string.none));
                                } else {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 18], null);
                                }
                            }
                        };
                        thread.start();
                    }
                });

                String dateString = cursor.getString(index * numFields + 18);

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

                            nerveBlockButtonDate.setText(mYear + "-" + String.format("%02d", mMonth + 1) + "-" + String.format("%02d", mDay));
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }



                final int nerveBlockYear = mYear;
                final int nerveBlockMonth = mMonth;
                final int nerveBlockDay = mDay;

                nerveBlockButtonDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        DatePickerDialog mDatePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                                final String text = selectedyear + "-" + String.format("%02d", selectedmonth + 1) + "-" + String.format("%02d", selectedday);
                                nerveBlockButtonDate.setText(text);
                                noneBox.setChecked(false);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 18], text);
                                adapter.notifyDataSetChanged();

                            }
                        }, nerveBlockYear, nerveBlockMonth, nerveBlockDay);
                        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date minDate = f.parse("2016-01-01");
//                            if (index>0){
//                                String previousDateString = cursor.getString((index-1)*numFields+22);
//                                minDate = f.parse(previousDateString);
//                            }
                            mDatePicker.getDatePicker().setMinDate(minDate.getTime());

                            Date maxDate = f.parse("2019-12-31");
//                            if (index<numAssessments-1){
//                                String nextDateString = cursor.getString((index+1)*numFields+22);
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
                //Nerve block Time picker
                final Button nerveBlockTimePickerButton = (Button) assessmentView.findViewById(R.id.nerveBlockButtonTime);
                nerveBlockTimePickerButton.setText(context.getString(R.string.select_time));

                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                final CheckBox noneBox = (CheckBox) assessmentView.findViewById(R.id.nerveBlockButtonTimeNone);
                noneBox.setChecked(false);

                noneBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean isChecked = ((CheckBox) v).isChecked();
                        if (isChecked) {
                            nerveBlockTimePickerButton.setText(context.getString(R.string.select_time));
                        }

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                if (isChecked) {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 19], context.getString(R.string.none));
                                } else {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 19], null);
                                }
                            }
                        };
                        thread.start();
                    }
                });

                String timeString = cursor.getString(index * numFields + 19);

                if (timeString != null){
                    if (timeString.equals("None")){
                        noneBox.setChecked(true);
                    } else if (!timeString.equals("")) {
                        nerveBlockTimePickerButton.setText(timeString);
                        String[] parts = timeString.split(":");
                        hour = Integer.parseInt(parts[0]);
                        minute = Integer.parseInt(parts[1]);
                    }
                }



                final int nerveBlockHour = hour;
                final int nerveBlockMinute = minute;

                nerveBlockTimePickerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        TimePickerDialog tpd = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                final String value = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
                                nerveBlockTimePickerButton.setText(value);
                                noneBox.setChecked(false);
                                Thread thread = new Thread() {
                                    @Override
                                    public void run() {
                                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 19], value);
                                    }
                                };
                                thread.start();
                            }
                        }, nerveBlockHour, nerveBlockMinute, true);
                        tpd.show();
                    }
                });
            }


            {
                //Spinner - Nerve block Order
                final Spinner spinnerNerveBlockOrder = (Spinner) assessmentView.findViewById(R.id.spinnerNerveBlockOrder);
                ArrayAdapter<String> spinnerNerveBlockOrderAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerOrderOptions);
                spinnerNerveBlockOrderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerNerveBlockOrder.setAdapter(spinnerNerveBlockOrderAdapter);

                spinnerNerveBlockOrder.setSelection(0);
                String value = cursor.getString(index * numFields + 20);
                if (value != null && !value.equals("")) {
                    for (int k = 0; k < spinnerOrderOptions.length; k++) {
                        if (value.equals(spinnerOrderOptions[k])) {
                            spinnerNerveBlockOrder.setSelection(k);
                            break;
                        }
                    }
                }


                spinnerNerveBlockOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 20], spinnerOrderOptions[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                TextView textView = (TextView) assessmentView.findViewById(R.id.text_type_of_order);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spinnerNerveBlockOrder.performClick();
                    }
                });
            }


            {
                //Nerve block type
                EditText editNerveBlockType = (EditText) assessmentView.findViewById(R.id.editNerveBlockType);
                editNerveBlockType.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(final CharSequence s, int start, int before, int count) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 21], s.toString());
                            }
                        };
                        thread.start();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                String type = cursor.getString(index * numFields + 21);
                if (type != null) {
                    editNerveBlockType.setText(type);
                } else {
                    editNerveBlockType.setText("");
                }
            }


            {
                //Checkbox - alternative pain relief
                final LinearLayout cgPainRelief = (LinearLayout) assessmentView.findViewById(R.id.checkGroupPainRelief);

                if (checkBoxAlternativeRelief.length > 2) {
                    cgPainRelief.setOrientation(RadioGroup.VERTICAL);
                } else {
                    cgPainRelief.setOrientation(RadioGroup.HORIZONTAL);
                }

                final CheckBox cbPainReliefNone = new CheckBox(context);
                cbPainReliefNone.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_medium));
                cbPainReliefNone.setText(context.getString(R.string.none));

                for (String aCheckBoxAlternativeRelief : checkBoxAlternativeRelief) {
                    CheckBox cb = new CheckBox(context);
                    cb.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_medium));
                    cb.setText(aCheckBoxAlternativeRelief);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, 10, 0, 10);
                    cb.setLayoutParams(layoutParams);
                    cb.setChecked(false);
                    cgPainRelief.addView(cb);


                    cb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            cbPainReliefNone.setChecked(false);

                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    String answer = "";
                                    for (int k = 0; k < checkBoxAlternativeRelief.length; k++) {
                                        CheckBox cb = (CheckBox) cgPainRelief.getChildAt(k);
                                        if (cb.isChecked()) {
                                            answer = answer + " " + checkBoxAlternativeRelief[k];
                                        }
                                    }

                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 22], answer);
                                }
                            };
                            thread.start();
                        }
                    });

                }

                cbPainReliefNone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()){
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 22], context.getString(R.string.none));
                            adapter.notifyDataSetChanged();
                        } else {
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 22], null);
                        }
                    }
                });

                cgPainRelief.addView(cbPainReliefNone);

                String answer = cursor.getString(index * numFields + 22);

                cbPainReliefNone.setChecked(false);
                if (answer != null) {
                    for (int j = 0; j < checkBoxAlternativeRelief.length; j++) {
                        if (answer.contains(checkBoxAlternativeRelief[j])) {
                            ((CheckBox) cgPainRelief.getChildAt(j)).setChecked(true);
                        } else {
                            ((CheckBox) cgPainRelief.getChildAt(j)).setChecked(false);
                        }
                    }

                    if (answer.equals(context.getString(R.string.none))){
                        cbPainReliefNone.setChecked(true);
                    }
                }
            }


            {
                //Pain relief other
                EditText editPainReliefOther = (EditText) assessmentView.findViewById(R.id.editPainReliefOther);
                editPainReliefOther.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(final CharSequence s, int start, int before, int count) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 23], s.toString());
                            }
                        };
                        thread.start();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                String other = cursor.getString(index * numFields + 23);
                if (other != null) {
                    editPainReliefOther.setText(other);
                } else {
                    editPainReliefOther.setText("");
                }
            }


            {
                //Checkbox - refusal
                CheckBox cbRefusal = (CheckBox) assessmentView.findViewById(R.id.analgesia_refused);

                cbRefusal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox) v).isChecked();
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 24], checked ? context.getString(R.string.yes) : null);
                    }
                });

                String refused = cursor.getString(index * numFields + 24);
                if (refused != null && !refused.equals("")) {
                    cbRefusal.setChecked(true);
                } else {
                    cbRefusal.setChecked(false);
                }
            }

            cursor.close();
            return assessmentView;
        }

        cursor.close();
        return null;
    }

    public static boolean canAdd(){
        Cursor cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, DBAdapter.KEY_ANALGESIC_ADMIN_NUM);
        int num = cursor.getInt(0);
        cursor.close();
        boolean canAdd = true;
        if (num > 0){
            cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, (num-1)*numFields+1, num*numFields+1));
            for (int i=0; i<cursor.getColumnCount(); i++){
                if (mandatoryKeys[i] && (cursor.getString(i) == null || cursor.getString(i).equals(""))){
                    canAdd = false;
                    break;
                }
            }
        }
        return canAdd;
    }


    public static void clearData(){
        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[0], "0");
        MainActivity.myDb.updateFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, 1, keys.length-1), null);
    }

}
