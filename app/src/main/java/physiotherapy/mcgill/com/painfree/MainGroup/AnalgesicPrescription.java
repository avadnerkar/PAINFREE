package physiotherapy.mcgill.com.painfree.MainGroup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;

/**
 * Created by Abhishek Vadnerkar on 16-03-20.
 */
public class AnalgesicPrescription {

    public static View setupAnalgesicPrescriptionSection(final Context context, ViewGroup parent, final ArrayAdapter adapter){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int mMonth;
        Button button;
        Calendar mcurrentDate;
        int mDay;
        int mYear;
        View rowView = inflater.inflate(R.layout.cell_fragment_analgesic_pres, parent, false);

        final String[] keys = new String[]{DBAdapter.KEY_ANALGESIC_PRES_NUM,
                DBAdapter.KEY_ANALGESIC_PRES_1_DATE, DBAdapter.KEY_ANALGESIC_PRES_1_TIME, DBAdapter.KEY_ANALGESIC_PRES_1_TYPE, DBAdapter.KEY_ANALGESIC_PRES_1_MODE,
                DBAdapter.KEY_ANALGESIC_PRES_2_DATE, DBAdapter.KEY_ANALGESIC_PRES_2_TIME, DBAdapter.KEY_ANALGESIC_PRES_2_TYPE, DBAdapter.KEY_ANALGESIC_PRES_2_MODE,
                DBAdapter.KEY_ANALGESIC_PRES_3_DATE, DBAdapter.KEY_ANALGESIC_PRES_3_TIME, DBAdapter.KEY_ANALGESIC_PRES_3_TYPE, DBAdapter.KEY_ANALGESIC_PRES_3_MODE,
                DBAdapter.KEY_ANALGESIC_PRES_4_DATE, DBAdapter.KEY_ANALGESIC_PRES_4_TIME, DBAdapter.KEY_ANALGESIC_PRES_4_TYPE, DBAdapter.KEY_ANALGESIC_PRES_4_MODE,
                DBAdapter.KEY_ANALGESIC_PRES_5_DATE, DBAdapter.KEY_ANALGESIC_PRES_5_TIME, DBAdapter.KEY_ANALGESIC_PRES_5_TYPE, DBAdapter.KEY_ANALGESIC_PRES_5_MODE,
                DBAdapter.KEY_ANALGESIC_PRES_6_DATE, DBAdapter.KEY_ANALGESIC_PRES_6_TIME, DBAdapter.KEY_ANALGESIC_PRES_6_TYPE, DBAdapter.KEY_ANALGESIC_PRES_6_MODE,};

        final LinearLayout container = (LinearLayout) rowView.findViewById(R.id.container);
        container.removeAllViews();

        final String[] spinnerOptions = new String[]{context.getString(R.string.standard_order), context.getString(R.string.collective_order), context.getString(R.string.pharmaceutical_algorithm)};
        final String[] checkBoxItems = new String[]{context.getString(R.string.acetaminophen), context.getString(R.string.nsaids), context.getString(R.string.opioid)};


        Cursor cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);

        if (cursor.moveToFirst()) {
            final int numAssessments = cursor.getInt(0);

            FloatingActionButton addButton = (FloatingActionButton) rowView.findViewById(R.id.fabPlus);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int addedIndex = Math.min(numAssessments + 1, 6);
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_ANALGESIC_PRES_NUM, String.valueOf(addedIndex));
                    adapter.notifyDataSetChanged();
                }
            });


            for (int i = 0; i < 6; i++) {
                final View assessmentView = inflater.inflate(R.layout.subcell_analgesic_pres, container, false);
                TextView assessmentTitle = (TextView) assessmentView.findViewById(R.id.title);
                assessmentTitle.setText("Prescription" + " " + String.valueOf(i + 1));


                //Spinner
                final Spinner spinner = (Spinner) assessmentView.findViewById(R.id.spinner);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerOptions);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);

                spinner.setSelection(0);
                String value = cursor.getString(i*4+4);
                if (value != null && !value.equals("")) {
                    for (int k = 0; k < spinnerOptions.length; k++) {
                        if (value.equals(spinnerOptions[k])) {
                            spinner.setSelection(k);
                            break;
                        }
                    }
                }

                final int I = i;
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I * 4 + 4], spinnerOptions[position]);
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

                String dateString = cursor.getString(i * 4 + 1);
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
                                Thread thread = new Thread() {
                                    @Override
                                    public void run() {
                                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I * 4 + 1], text);
                                    }
                                };
                                thread.start();

                            }
                        }, year, month, day);
                        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date minDate = f.parse("2016-02-01");
                            mDatePicker.getDatePicker().setMinDate(minDate.getTime());

                            Date maxDate = f.parse("2019-12-31");
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

                String timeString = cursor.getString(I*4+2);
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
                                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I*4+2], value);
                                    }
                                };
                                thread.start();
                            }
                        }, mHour, mMinute, false);
                        tpd.show();
                    }
                });


                //Checkbox
                final LinearLayout cg = (LinearLayout) rowView.findViewById(R.id.checkGroup);

                if (checkBoxItems.length > 2) {
                    cg.setOrientation(RadioGroup.VERTICAL);
                } else {
                    cg.setOrientation(RadioGroup.HORIZONTAL);
                }

                for (int j = 0; j < checkBoxItems.length; j++) {
                    CheckBox cb = new CheckBox(context);
                    cb.setText(checkBoxItems[j]);
                    cb.setChecked(false);
                    cg.addView(cb);


                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    String answer = "";
                                    for (int k = 0; k < cg.getChildCount(); k++) {
                                        CheckBox cb = (CheckBox) cg.getChildAt(k);
                                        if (cb.isChecked()) {
                                            answer = answer + " " + checkBoxItems[k];
                                        }
                                    }

                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I*4+3], answer);
                                }
                            };
                            thread.start();
                        }
                    });

                }

                String answer = cursor.getString(i*4+3);

                if (answer != null) {
                    for (int j = 0; j < cg.getChildCount(); j++) {
                        if (answer.contains(checkBoxItems[j])) {
                            ((CheckBox) cg.getChildAt(j)).setChecked(true);
                        } else {
                            ((CheckBox) cg.getChildAt(j)).setChecked(false);
                        }
                    }
                }


                if (i >= numAssessments) {
                    assessmentView.setVisibility(View.GONE);
                } else {
                    assessmentView.setVisibility(View.VISIBLE);
                }

                FloatingActionButton minusButton = (FloatingActionButton) assessmentView.findViewById(R.id.fabMinus);
                minusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor tempCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);

                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_ANALGESIC_PRES_NUM, String.valueOf(Math.max(numAssessments - 1, 0)));

                        for (int j=I; j<numAssessments-1; j++){
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[j*4+1], tempCursor.getString((j+1)*4+1));
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[j*4+2], tempCursor.getString((j+1)*4+2));
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[j*4+3], tempCursor.getString((j+1)*4+3));
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[j*4+4], tempCursor.getString((j+1)*4+4));
                        }
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[(numAssessments - 1)*4+1], null);
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[(numAssessments - 1)*4+2], null);
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[(numAssessments - 1)*4+3], null);
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[(numAssessments - 1)*4+4], null);

                        tempCursor.close();

                        adapter.notifyDataSetChanged();
                    }
                });

                container.addView(assessmentView);

            }
        }

        return rowView;

    }

}
