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
 * Created by Abhishek Vadnerkar on 16-05-07.
 */
public class NerveBlock {

    public static final String[] keys = new String[]{DBAdapter.KEY_NERVE_BLOCK_NUM,
            DBAdapter.KEY_NERVE_BLOCK_1_DATE, DBAdapter.KEY_NERVE_BLOCK_1_TIME, DBAdapter.KEY_NERVE_BLOCK_1_ORDER, DBAdapter.KEY_NERVE_BLOCK_1_TYPE,
            DBAdapter.KEY_NERVE_BLOCK_2_DATE, DBAdapter.KEY_NERVE_BLOCK_2_TIME, DBAdapter.KEY_NERVE_BLOCK_2_ORDER, DBAdapter.KEY_NERVE_BLOCK_2_TYPE,
            DBAdapter.KEY_NERVE_BLOCK_3_DATE, DBAdapter.KEY_NERVE_BLOCK_3_TIME, DBAdapter.KEY_NERVE_BLOCK_3_ORDER, DBAdapter.KEY_NERVE_BLOCK_3_TYPE,
            DBAdapter.KEY_NERVE_BLOCK_4_DATE, DBAdapter.KEY_NERVE_BLOCK_4_TIME, DBAdapter.KEY_NERVE_BLOCK_4_ORDER, DBAdapter.KEY_NERVE_BLOCK_4_TYPE,
            DBAdapter.KEY_NERVE_BLOCK_5_DATE, DBAdapter.KEY_NERVE_BLOCK_5_TIME, DBAdapter.KEY_NERVE_BLOCK_5_ORDER, DBAdapter.KEY_NERVE_BLOCK_5_TYPE,
            DBAdapter.KEY_NERVE_BLOCK_6_DATE, DBAdapter.KEY_NERVE_BLOCK_6_TIME, DBAdapter.KEY_NERVE_BLOCK_6_ORDER, DBAdapter.KEY_NERVE_BLOCK_6_TYPE,
            DBAdapter.KEY_NERVE_BLOCK_7_DATE, DBAdapter.KEY_NERVE_BLOCK_7_TIME, DBAdapter.KEY_NERVE_BLOCK_7_ORDER, DBAdapter.KEY_NERVE_BLOCK_7_TYPE,
            DBAdapter.KEY_NERVE_BLOCK_8_DATE, DBAdapter.KEY_NERVE_BLOCK_8_TIME, DBAdapter.KEY_NERVE_BLOCK_8_ORDER, DBAdapter.KEY_NERVE_BLOCK_8_TYPE};


    public static final int numFields = 4;

    public static View setupNerveBlockSection(final Context context, ViewGroup parent, final ArrayAdapter adapter, final int index, final int globalIndex, final EDEvents.MinusHandler handler){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int mMonth;
        Calendar mcurrentDate;
        int mDay;
        int mYear;

        final String[] spinnerOrderOptions = new String[]{"", context.getString(R.string.none), context.getString(R.string.written), context.getString(R.string.verbal)};

        final Cursor cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);

        if (cursor.moveToFirst()) {
            final int numAssessments = cursor.getInt(0);

            final View assessmentView = inflater.inflate(R.layout.subcell_nerve_block, parent, false);
            TextView assessmentTitle = (TextView) assessmentView.findViewById(R.id.title);
            assessmentTitle.setText("Event" + " " + String.valueOf(globalIndex + 1) + " - Nerve block");

            FloatingActionButton minusButton = (FloatingActionButton) assessmentView.findViewById(R.id.fabMinus);
            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Cursor tempCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_NERVE_BLOCK_NUM, String.valueOf(Math.max(numAssessments - 1, 0)));

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
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 1], text);
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
                                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 2], value);
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
                String value = cursor.getString(index * numFields + 3);
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
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 3], spinnerOrderOptions[position]);
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

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 4], s.toString());
                            }
                        };
                        thread.start();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                String type = cursor.getString(index * numFields + 4);
                if (type != null) {
                    editNerveBlockType.setText(type);
                } else {
                    editNerveBlockType.setText("");
                }
            }


            cursor.close();
            return assessmentView;
        }

        cursor.close();
        return null;
    }

    public static boolean canAdd(){
        Cursor cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, DBAdapter.KEY_NERVE_BLOCK_NUM);
        int num = cursor.getInt(0);
        cursor.close();
        boolean canAdd = true;
        if (num > 0){
            cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, Arrays.copyOfRange(keys, (num-1)*numFields+1, num*numFields+1));
            for (int i=0; i<cursor.getColumnCount(); i++){
                if ((cursor.getString(i) == null || cursor.getString(i).equals(""))){
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
