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
import java.util.Calendar;
import java.util.Date;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;

/**
 * Created by Abhishek Vadnerkar on 16-03-26.
 */

public class AnalgesicAdministration {

    public static View setupAnalgesicAdministration(final Context context, ViewGroup parent, final ArrayAdapter adapter){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int mMonth;
        Button button;
        Calendar mcurrentDate;
        int mDay;
        int mYear;
        View rowView = inflater.inflate(R.layout.cell_fragment_analgesic_admin, parent, false);
        final int numFields = 16;

        final String[] keys = new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_NUM,
                DBAdapter.KEY_ANALGESIC_ADMIN_1_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_1_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_1_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_1_DOSE_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_1_DOSE_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_1_DOSE_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_1_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_1_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_1_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_1_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_1_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_1_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_1_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_1_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_1_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_1_REFUSAL,
                DBAdapter.KEY_ANALGESIC_ADMIN_2_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_2_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_2_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_2_DOSE_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_2_DOSE_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_2_DOSE_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_2_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_2_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_2_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_2_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_2_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_2_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_2_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_2_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_2_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_2_REFUSAL,
                DBAdapter.KEY_ANALGESIC_ADMIN_3_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_3_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_3_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_3_DOSE_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_3_DOSE_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_3_DOSE_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_3_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_3_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_3_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_3_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_3_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_3_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_3_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_3_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_3_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_3_REFUSAL,
                DBAdapter.KEY_ANALGESIC_ADMIN_4_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_4_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_4_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_4_DOSE_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_4_DOSE_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_4_DOSE_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_4_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_4_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_4_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_4_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_4_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_4_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_4_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_4_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_4_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_4_REFUSAL,
                DBAdapter.KEY_ANALGESIC_ADMIN_5_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_5_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_5_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_5_DOSE_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_5_DOSE_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_5_DOSE_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_5_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_5_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_5_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_5_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_5_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_5_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_5_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_5_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_5_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_5_REFUSAL,
                DBAdapter.KEY_ANALGESIC_ADMIN_6_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_6_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_6_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_6_DOSE_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_6_DOSE_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_6_DOSE_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_6_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_6_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_6_FREQUENCY, DBAdapter.KEY_ANALGESIC_ADMIN_6_NERVE_BLOCK_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_6_NERVE_BLOCK_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_6_NERVE_BLOCK_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_6_NERVE_BLOCK_TYPE, DBAdapter.KEY_ANALGESIC_ADMIN_6_ALTERNATIVE_PAIN_RELIEF, DBAdapter.KEY_ANALGESIC_ADMIN_6_ALTERNATIVE_PAIN_RELIEF_OTHER, DBAdapter.KEY_ANALGESIC_ADMIN_6_REFUSAL
        };

        final LinearLayout container = (LinearLayout) rowView.findViewById(R.id.container);
        container.removeAllViews();

        final String[] spinnerOrderOptions = new String[]{"", context.getString(R.string.none), context.getString(R.string.written), context.getString(R.string.verbal)};
        final String[] checkBoxTypeItems = new String[]{context.getString(R.string.acetaminophen), context.getString(R.string.nsaids), context.getString(R.string.opioid)};
        final String[] spinnerRouteOptions = new String[]{"", context.getString(R.string.im), context.getString(R.string.iv), context.getString(R.string.po), context.getString(R.string.sc), context.getString(R.string.pr)};
        final String[] checkBoxAlternativeRelief = new String[]{context.getString(R.string.ice), context.getString(R.string.cool_cloths), context.getString(R.string.soft_cushions)};


        Cursor cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);

        if (cursor.moveToFirst()) {
            final int numAssessments = cursor.getInt(0);

            FloatingActionButton addButton = (FloatingActionButton) rowView.findViewById(R.id.fabPlus);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int addedIndex = Math.min(numAssessments + 1, 6);
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_ANALGESIC_ADMIN_NUM, String.valueOf(addedIndex));
                    adapter.notifyDataSetChanged();
                }
            });


            for (int i = 0; i < numAssessments; i++) {
                final View assessmentView = inflater.inflate(R.layout.subcell_analgesic_admin, container, false);
                TextView assessmentTitle = (TextView) assessmentView.findViewById(R.id.title);
                assessmentTitle.setText("Administration" + " " + String.valueOf(i + 1));


                final int I = i;

                //Datepicker

                final Button buttonDate = (Button) assessmentView.findViewById(R.id.buttonDate);

                mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                String dateString = cursor.getString(i * numFields + 1);
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
                                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I * numFields + 1], text);
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

                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                String timeString = cursor.getString(I*numFields+2);
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
                                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I*numFields+2], value);
                                    }
                                };
                                thread.start();
                            }
                        }, mHour, mMinute, false);
                        tpd.show();
                    }
                });


                //Checkbox - types
                final LinearLayout cg = (LinearLayout) assessmentView.findViewById(R.id.checkGroup);

                if (checkBoxTypeItems.length > 2) {
                    cg.setOrientation(RadioGroup.VERTICAL);
                } else {
                    cg.setOrientation(RadioGroup.HORIZONTAL);
                }

                for (int j = 0; j < checkBoxTypeItems.length; j++) {
                    CheckBox cb = new CheckBox(context);
                    cb.setText(checkBoxTypeItems[j]);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0,10,0,10);
                    cb.setLayoutParams(layoutParams);
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
                                            answer = answer + " " + checkBoxTypeItems[k];
                                        }
                                    }

                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I*numFields+3], answer);
                                }
                            };
                            thread.start();
                        }
                    });

                }

                String answer = cursor.getString(i*numFields+3);

                if (answer != null) {
                    for (int j = 0; j < cg.getChildCount(); j++) {
                        if (answer.contains(checkBoxTypeItems[j])) {
                            ((CheckBox) cg.getChildAt(j)).setChecked(true);
                        } else {
                            ((CheckBox) cg.getChildAt(j)).setChecked(false);
                        }
                    }
                }


                //EditTexts - doses
                final LinearLayout groupDoses = (LinearLayout) assessmentView.findViewById(R.id.doseGroup);

                if (checkBoxTypeItems.length > 2) {
                    groupDoses.setOrientation(RadioGroup.VERTICAL);
                } else {
                    groupDoses.setOrientation(RadioGroup.HORIZONTAL);
                }

                for (int j = 0; j < checkBoxTypeItems.length; j++) {
                    final int J = j;
                    EditText editText = new EditText(context);
                    editText.setHint(context.getString(R.string.enter_dose));
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                            context.getResources().getDimension(R.dimen.text_medium));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                            android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
                    editText.setLayoutParams(params);

                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(final CharSequence s, int start, int before, int count) {
                            Thread thread = new Thread() {
                                @Override
                                public void run() {

                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I*numFields+4 + J], s.toString());
                                }
                            };
                            thread.start();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    String dose = cursor.getString(i*numFields+4 + j);
                    if (dose != null){
                        editText.setText(dose);
                    } else {
                        editText.setText("");
                    }

                    groupDoses.addView(editText);

                }




                //Spinner - Order
                final Spinner spinnerOrder = (Spinner) assessmentView.findViewById(R.id.spinnerOrder);
                ArrayAdapter<String> spinnerOrderAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerOrderOptions);
                spinnerOrderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerOrder.setAdapter(spinnerOrderAdapter);

                spinnerOrder.setSelection(0);
                String value = cursor.getString(i*numFields+7);
                if (value != null && !value.equals("")) {
                    for (int k = 0; k < spinnerOrderOptions.length; k++) {
                        if (value.equals(spinnerOrderOptions[k])) {
                            spinnerOrder.setSelection(k);
                            break;
                        }
                    }
                }


                spinnerOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I * numFields + 7], spinnerOrderOptions[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                //Spinner - Route
                final Spinner spinnerRoute = (Spinner) assessmentView.findViewById(R.id.spinnerRoute);
                ArrayAdapter<String> spinnerRouteAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerRouteOptions);
                spinnerRouteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRoute.setAdapter(spinnerRouteAdapter);

                spinnerRoute.setSelection(0);
                String routeValue = cursor.getString(i*numFields+8);
                if (routeValue != null && !routeValue.equals("")) {
                    for (int k = 0; k < spinnerRouteOptions.length; k++) {
                        if (routeValue.equals(spinnerRouteOptions[k])) {
                            spinnerRoute.setSelection(k);
                            break;
                        }
                    }
                }


                spinnerRoute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I * numFields + 8], spinnerRouteOptions[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



                //Frequency
                EditText editFrequency = (EditText) assessmentView.findViewById(R.id.editFrequency);
                editFrequency.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(final CharSequence s, int start, int before, int count) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I*numFields+9], s.toString());
                            }
                        };
                        thread.start();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                String frequency = cursor.getString(i*numFields + 9);
                if (frequency != null){
                    editFrequency.setText(frequency);
                } else {
                    editFrequency.setText("");
                }


                //Nerve block - Datepicker

                final Button nerveBlockButtonDate = (Button) assessmentView.findViewById(R.id.nerveBlockButtonDate);

                mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                dateString = cursor.getString(i * numFields + 10);
                if (dateString != null && !dateString.equals("")) {
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
                                Thread thread = new Thread() {
                                    @Override
                                    public void run() {
                                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I * numFields + 10], text);
                                    }
                                };
                                thread.start();

                            }
                        }, nerveBlockYear, nerveBlockMonth, nerveBlockDay);
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


                //Nerve block Time picker
                final Button nerveBlockTimePickerButton = (Button) assessmentView.findViewById(R.id.nerveBlockButtonTime);
                nerveBlockTimePickerButton.setText(context.getString(R.string.select_time));

                c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                timeString = cursor.getString(I * numFields + 11);
                if (timeString != null && !timeString.equals("")) {
                    nerveBlockTimePickerButton.setText(timeString);
                    String[] parts = timeString.split(":");
                    hour = Integer.parseInt(parts[0]);
                    minute = Integer.parseInt(parts[1]);
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
                                Thread thread = new Thread() {
                                    @Override
                                    public void run() {
                                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I*numFields+11], value);
                                    }
                                };
                                thread.start();
                            }
                        }, nerveBlockHour, nerveBlockMinute, false);
                        tpd.show();
                    }
                });


                //Spinner - Nerve block Order
                final Spinner spinnerNerveBlockOrder = (Spinner) assessmentView.findViewById(R.id.spinnerNerveBlockOrder);
                ArrayAdapter<String> spinnerNerveBlockOrderAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerOrderOptions);
                spinnerNerveBlockOrderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerNerveBlockOrder.setAdapter(spinnerNerveBlockOrderAdapter);

                spinnerNerveBlockOrder.setSelection(0);
                value = cursor.getString(i*numFields+12);
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
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I * numFields + 12], spinnerOrderOptions[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


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

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I*numFields+13], s.toString());
                            }
                        };
                        thread.start();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                String type = cursor.getString(i*numFields + 13);
                if (type != null){
                    editNerveBlockType.setText(type);
                } else {
                    editNerveBlockType.setText("");
                }




                //Checkbox - alternative pain relief
                final LinearLayout cgPainRelief = (LinearLayout) assessmentView.findViewById(R.id.checkGroupPainRelief);

                if (checkBoxAlternativeRelief.length > 2) {
                    cgPainRelief.setOrientation(RadioGroup.VERTICAL);
                } else {
                    cgPainRelief.setOrientation(RadioGroup.HORIZONTAL);
                }

                for (int j = 0; j < checkBoxAlternativeRelief.length; j++) {
                    CheckBox cb = new CheckBox(context);
                    cb.setText(checkBoxAlternativeRelief[j]);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0,10,0,10);
                    cb.setLayoutParams(layoutParams);
                    cb.setChecked(false);
                    cgPainRelief.addView(cb);


                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    String answer = "";
                                    for (int k = 0; k < cgPainRelief.getChildCount(); k++) {
                                        CheckBox cb = (CheckBox) cgPainRelief.getChildAt(k);
                                        if (cb.isChecked()) {
                                            answer = answer + " " + checkBoxAlternativeRelief[k];
                                        }
                                    }

                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I*numFields+14], answer);
                                }
                            };
                            thread.start();
                        }
                    });

                }

                answer = cursor.getString(i*numFields+14);

                if (answer != null) {
                    for (int j = 0; j < cgPainRelief.getChildCount(); j++) {
                        if (answer.contains(checkBoxAlternativeRelief[j])) {
                            ((CheckBox) cgPainRelief.getChildAt(j)).setChecked(true);
                        } else {
                            ((CheckBox) cgPainRelief.getChildAt(j)).setChecked(false);
                        }
                    }
                }


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

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I*numFields+15], s.toString());
                            }
                        };
                        thread.start();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                String other = cursor.getString(i*numFields + 15);
                if (other != null){
                    editPainReliefOther.setText(other);
                } else {
                    editPainReliefOther.setText("");
                }



                //Checkbox - refusal
                CheckBox cbRefusal = (CheckBox) assessmentView.findViewById(R.id.analgesia_refused);
                cbRefusal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[I*numFields+16], isChecked ? context.getString(R.string.yes) : "");
                            }
                        };
                        thread.start();
                    }
                });

                String refused = cursor.getString(i*numFields + 16);
                if (refused != null && refused != ""){
                    cbRefusal.setChecked(true);
                } else {
                    cbRefusal.setChecked(false);
                }


                //Minus button
                FloatingActionButton minusButton = (FloatingActionButton) assessmentView.findViewById(R.id.fabMinus);
                minusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor tempCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);

                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_ANALGESIC_ADMIN_NUM, String.valueOf(Math.max(numAssessments - 1, 0)));

                        for (int j=I; j<numAssessments-1; j++){

                            for (int k=1; k<=numFields; k++){
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[j*numFields+k], tempCursor.getString((j+1)*numFields+k));
                            }

                        }

                        for (int k=1; k<=numFields; k++){
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[(numAssessments - 1)*numFields+k], null);
                        }

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
