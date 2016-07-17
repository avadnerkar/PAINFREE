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
import java.util.ArrayList;
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
            DBAdapter.KEY_ANALGESIC_ADMIN_1_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_1_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_1_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_1_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_1_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_1_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_1_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_1_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_1_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_2_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_2_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_2_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_2_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_2_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_2_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_2_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_2_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_2_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_3_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_3_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_3_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_3_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_3_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_3_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_3_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_3_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_3_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_4_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_4_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_4_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_4_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_4_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_4_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_4_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_4_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_4_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_5_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_5_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_5_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_5_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_5_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_5_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_5_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_5_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_5_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_6_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_6_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_6_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_6_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_6_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_6_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_6_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_6_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_6_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_7_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_7_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_7_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_7_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_7_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_7_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_7_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_7_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_7_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_8_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_8_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_8_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_8_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_8_NSAIDS_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_8_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_8_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_8_OPIOID_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_8_REFUSAL};

    public static final boolean[] mandatoryKeys = new boolean[]{
            true, true, false, false, false, false, false, false, false, false, false, false, false};


    public static final int numFields = 13;

    public static View setupAnalgesicAdministrationSection(final Context context, ViewGroup parent, final ArrayAdapter adapter, final int index, final int globalIndex, final EDEvents.MinusHandler handler){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int mMonth;
        Calendar mcurrentDate;
        int mDay;
        int mYear;

        final String[] spinnerOrderOptions = new String[]{"", context.getString(R.string.written), context.getString(R.string.verbal), context.getString(R.string.not_specified), context.getString(R.string.none)};

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
                    MainActivity.myDb.updateFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, (numAssessments - 1)*numFields + 1, (numAssessments)*numFields + 1), null);
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
                //Spinner - pres ID
                final Spinner spinner = (Spinner) assessmentView.findViewById(R.id.spinner);

                ArrayList<String> options = new ArrayList<>();
                options.add("");
                Cursor cursor1 = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, AnalgesicPrescription.idKeys);
                if (cursor1.moveToFirst()) {
                    for (int i=0; i<AnalgesicPrescription.idKeys.length; i++) {
                        String option = cursor1.getString(i);
                        if (option != null && option.length() > 0) {
                            options.add(option);
                        }
                    }
                }
                cursor1.close();
                final String spinnerOptions[] = options.toArray(new String[options.size()]);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerOptions);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);

                spinner.setSelection(0);
                String value = cursor.getString(index * numFields + 3);
                boolean valueFound = false;
                if (value != null && !value.equals("")) {
                    for (int k = 0; k < spinnerOptions.length; k++) {
                        if (value.equals(spinnerOptions[k])) {
                            spinner.setSelection(k);
                            valueFound = true;
                            break;
                        }
                    }
                }
                if (!valueFound) {
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 3], null);
                }


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 3], spinnerOptions[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                TextView textView = (TextView) assessmentView.findViewById(R.id.text_linked_pres);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spinner.performClick();
                    }
                });
            }


            {
                //Analgesic type, dose, order

                CheckBox[] analgesicCheckboxes = new CheckBox[]{(CheckBox) assessmentView.findViewById(R.id.checkbox_acetaminophen), (CheckBox) assessmentView.findViewById(R.id.checkbox_nsaids), (CheckBox) assessmentView.findViewById(R.id.checkbox_opioid)};
                final EditText[] doses = new EditText[]{(EditText) assessmentView.findViewById(R.id.edit_acetaminophen_dose), (EditText) assessmentView.findViewById(R.id.edit_nsaids_dose), (EditText) assessmentView.findViewById(R.id.edit_opioid_dose)};
                final Spinner[] orders = new Spinner[]{(Spinner) assessmentView.findViewById(R.id.acetaminophen_order), (Spinner) assessmentView.findViewById(R.id.nsaids_order), (Spinner) assessmentView.findViewById(R.id.opioid_order)};
                final LinearLayout fields[] = new LinearLayout[]{(LinearLayout) assessmentView.findViewById(R.id.acetaminophenGroup), (LinearLayout) assessmentView.findViewById(R.id.nsaidsGroup), (LinearLayout) assessmentView.findViewById(R.id.opioidGroup)};
                final TextView[] orderTitles = new TextView[]{(TextView) assessmentView.findViewById(R.id.text_acetaminophen_order), (TextView) assessmentView.findViewById(R.id.text_nsaids_order), (TextView) assessmentView.findViewById(R.id.text_opioid_order)};

                for (int j=0; j<3; j++){
                    final int J = j;

                    //Checkbox
                    String analgesicValue = cursor.getString(index*numFields + 4 + j*3);
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
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index*numFields + 4 + J*3], context.getString(R.string.yes));
                                fields[J].setVisibility(View.VISIBLE);
                            } else {
                                MainActivity.myDb.updateFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, index*numFields + 4 + J*3, index*numFields + 4 + (J+1)*3), new String[]{null, null, null});
                                fields[J].setVisibility(View.GONE);
                                doses[J].setText("");
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

                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 5 + J * 3], s.toString());
                                }
                            };
                            thread.start();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    String dose = cursor.getString(index * numFields + 5 + J*3);
                    if (dose != null) {
                        doses[j].setText(dose);
                    } else {
                        doses[j].setText("");
                    }


                    //Spinner - Order
                    ArrayAdapter<String> spinnerOrderAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerOrderOptions);
                    spinnerOrderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    orders[j].setAdapter(spinnerOrderAdapter);

                    orders[j].setSelection(0);
                    String value = cursor.getString(index * numFields + 6 + j*3);
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
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 6 + J*3], spinnerOrderOptions[position]);
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
                //Checkbox - refusal
                CheckBox cbRefusal = (CheckBox) assessmentView.findViewById(R.id.analgesia_refused);

                cbRefusal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox) v).isChecked();
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 13], checked ? context.getString(R.string.yes) : null);
                    }
                });

                String refused = cursor.getString(index * numFields + 13);
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
        MainActivity.myDb.updateFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, 1, keys.length), null);
    }

}
