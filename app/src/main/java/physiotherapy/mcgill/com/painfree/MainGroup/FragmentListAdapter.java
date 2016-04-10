package physiotherapy.mcgill.com.painfree.MainGroup;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;

/**
 * Created by Abhishek Vadnerkar on 16-02-28.
 */
public class FragmentListAdapter extends ArrayAdapter<FragmentItem> {

    private Context context;
    private ArrayList<FragmentItem> items;


    public FragmentListAdapter(Context context, ArrayList<FragmentItem> items){
        super(context, R.layout.cell_fragment_radio, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getViewTypeCount() {
        return FragmentItem.CellType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return (items.get(position).cellType).ordinal();
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        View rowView = null;
        TextView textView;
        final Cursor cursor;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FragmentItem.CellType i1 = items.get(position).cellType;
        if (i1 == FragmentItem.CellType.NUMERIC) {
            rowView = inflater.inflate(R.layout.cell_fragment_numeric, parent, false);
            textView = (TextView) rowView.findViewById(R.id.title);
            textView.setText(items.get(position).title);
            EditText editText = (EditText) rowView.findViewById(R.id.edit);


            if (items.get(position).uiOptions != null) {
                editText.setHint(items.get(position).uiOptions[0]);
            }

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            if (cursor.moveToFirst()) {
                String text = cursor.getString(0);
                if (text != null) {
                    editText.setText(text);
                } else {
                    editText.setText("");
                }

            } else {
                editText.setText("");
            }

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, charSequence.toString());
                        }
                    };
                    thread.start();

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            cursor.close();


        } else if (i1 == FragmentItem.CellType.RADIO) {
            rowView = inflater.inflate(R.layout.cell_fragment_radio, parent, false);
            textView = (TextView) rowView.findViewById(R.id.title);
            textView.setText(items.get(position).title);

            final RadioGroup rg = (RadioGroup) rowView.findViewById(R.id.rg);

            if (items.get(position).databaseOptions == null) {
                items.get(position).databaseOptions = items.get(position).uiOptions;
            }

            if (items.get(position).uiOptions.length > 2 && items.get(position).uiOptions.length < 7) {
                rg.setOrientation(RadioGroup.VERTICAL);
            } else {
                rg.setOrientation(RadioGroup.HORIZONTAL);
            }

            for (int i = 0; i < items.get(position).uiOptions.length; i++) {
                RadioButton rb = new RadioButton(context);
                rb.setText(items.get(position).uiOptions[i]);
                rg.addView(rb);

                final int index = i;
                rb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(final View view) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, items.get(position).databaseOptions[index]);
                            }
                        };
                        thread.start();

                    }
                });
            }

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            if (cursor.moveToFirst()) {
                String radioValue = cursor.getString(0);
                if (radioValue != null && !radioValue.equals("")) {
                    for (int i = 0; i < items.get(position).databaseOptions.length; i++) {
                        String rbString = items.get(position).databaseOptions[i];
                        if (rbString.equals(radioValue)) {
                            ((RadioButton) rg.getChildAt(i)).setChecked(true);
                        }
                    }
                } else {
                    rg.clearCheck();
                }

            } else {
                rg.clearCheck();
            }

            cursor.close();

        } else if (i1 == FragmentItem.CellType.RADIO_WITH_SPECIFY) {
            rowView = inflater.inflate(R.layout.cell_fragment_radio_with_specify, parent, false);
            textView = (TextView) rowView.findViewById(R.id.title);
            textView.setText(items.get(position).title);

            EditText editText = (EditText) rowView.findViewById(R.id.specify);

            final RadioGroup rg = (RadioGroup) rowView.findViewById(R.id.rg);

            if (items.get(position).databaseOptions == null) {
                items.get(position).databaseOptions = items.get(position).uiOptions;
            }

            if (items.get(position).uiOptions.length > 2 && items.get(position).uiOptions.length < 7) {
                rg.setOrientation(RadioGroup.VERTICAL);
                rg.setOrientation(RadioGroup.VERTICAL);
            } else {
                rg.setOrientation(RadioGroup.HORIZONTAL);
            }

            for (int i = 0; i < items.get(position).uiOptions.length; i++) {
                RadioButton rb = new RadioButton(context);
                rb.setText(items.get(position).uiOptions[i]);
                rg.addView(rb);

                final int index = i;
                rb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(final View view) {

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, items.get(position).databaseOptions[index]);
                            }
                        };
                        thread.start();

                    }
                });
            }

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            if (cursor.moveToFirst()) {
                String radioValue = cursor.getString(0);
                if (radioValue != null && !radioValue.equals("")) {
                    for (int i = 0; i < items.get(position).databaseOptions.length; i++) {
                        String rbString = items.get(position).databaseOptions[i];
                        if (rbString.equals(radioValue)) {
                            ((RadioButton) rg.getChildAt(i)).setChecked(true);
                        }
                    }
                } else {
                    rg.clearCheck();
                }

            } else {
                rg.clearCheck();
            }

            cursor.close();


            Cursor cursor2 = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).extraOptions[0]);

            if (cursor2.moveToFirst()) {
                String text = cursor2.getString(0);
                if (text != null) {
                    editText.setText(text);
                } else {
                    editText.setText("");
                }

            } else {
                editText.setText("");
            }

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).extraOptions[0], charSequence.toString());
                        }
                    };
                    thread.start();

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            cursor2.close();

        }

        else if (i1 == FragmentItem.CellType.DATEPICKER) {
            rowView = inflater.inflate(R.layout.cell_fragment_datepicker, parent, false);
            textView = (TextView) rowView.findViewById(R.id.title);
            textView.setText(items.get(position).title);

            final Button button = (Button) rowView.findViewById(R.id.button);
            button.setText(context.getString(R.string.select_date));

            final Button clearButton = (Button) rowView.findViewById(R.id.clear);
            if (items.get(position).extraOptions != null && items.get(position).extraOptions[0].contains("optional")) {
                clearButton.setVisibility(View.VISIBLE);
            } else {
                clearButton.setVisibility(View.GONE);
            }

            final CheckBox noneBox = (CheckBox) rowView.findViewById(R.id.noneCheckbox);

            if (items.get(position).extraOptions != null && items.get(position).extraOptions[0].contains("none")) {
                noneBox.setVisibility(View.VISIBLE);
            } else {
                noneBox.setVisibility(View.GONE);
            }

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            Calendar mcurrentDate = Calendar.getInstance();
            int mYear = mcurrentDate.get(Calendar.YEAR);
            int mMonth = mcurrentDate.get(Calendar.MONTH);
            int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

            if (items.get(position).uiOptions != null && items.get(position).uiOptions[0] != null) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date defaultDate = f.parse(items.get(position).uiOptions[0]);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(defaultDate);
                    mYear = cal.get(Calendar.YEAR);
                    mMonth = cal.get(Calendar.MONTH);
                    mDay = cal.get(Calendar.DAY_OF_MONTH);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (cursor.moveToFirst()) {
                String dateString = cursor.getString(0);

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

                            button.setText(mYear + "-" + String.format("%02d", mMonth + 1) + "-" + String.format("%02d", mDay));
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }

            final int year = mYear;
            final int month = mMonth;
            final int day = mDay;

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    DatePickerDialog mDatePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            final String text = selectedyear + "-" + String.format("%02d", selectedmonth + 1) + "-" + String.format("%02d", selectedday);
                            button.setText(text);
                            noneBox.setChecked(false);
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, text);

                                    //Synchronise arrival date and triage date
                                    if (Objects.equals(items.get(position).dbKey, DBAdapter.KEY_ARRIVALDATE)){
                                        ((Activity) context).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                FragmentA.adapter.notifyDataSetChanged();
                                                FragmentD.adapter.notifyDataSetChanged();
                                            }
                                        });

                                    }

                                }
                            };
                            thread.start();


                        }
                    }, year, month, day);
                    if (items.get(position).uiOptions != null && items.get(position).uiOptions.length >= 3) {
                        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                        try {

                            Date minDate = f.parse(items.get(position).uiOptions[1]);
                            Date maxDate = f.parse(items.get(position).uiOptions[2]);

                            if (items.get(position).dbKey.equals(DBAdapter.KEY_ARRIVALDATE)){

                                Cursor dateCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_PHYSICIAN_EXAMINATION_DATE, DBAdapter.KEY_DISCHARGE_DATE});
                                if (dateCursor.moveToFirst()){
                                    String maxDateString = dateCursor.getString(0);
                                    String secondMaxDateString = dateCursor.getString(1);
                                    if (maxDateString != null && !maxDateString.equals("") && !maxDateString.equals("None")){
                                        maxDate = f.parse(maxDateString);
                                    } else if (secondMaxDateString != null && !secondMaxDateString.equals("") && !secondMaxDateString.equals("None")){
                                        maxDate = f.parse(secondMaxDateString);
                                    }
                                }
                                dateCursor.close();
                            }

                            if (items.get(position).dbKey.equals(DBAdapter.KEY_PHYSICIAN_EXAMINATION_DATE)){

                                Cursor dateCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_ARRIVALDATE, DBAdapter.KEY_DISCHARGE_DATE});
                                if (dateCursor.moveToFirst()){

                                    String minDateString = dateCursor.getString(0);
                                    if (minDateString != null && !minDateString.equals("") && !minDateString.equals("None")){
                                        minDate = f.parse(minDateString);
                                    }

                                    String maxDateString = dateCursor.getString(1);
                                    if (maxDateString != null && !maxDateString.equals("") && !maxDateString.equals("None")){
                                        maxDate = f.parse(maxDateString);
                                    }
                                }
                                dateCursor.close();
                            }

                            if (items.get(position).dbKey.equals(DBAdapter.KEY_DISCHARGE_DATE)){

                                Cursor dateCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_ARRIVALDATE, DBAdapter.KEY_PHYSICIAN_EXAMINATION_DATE});
                                if (dateCursor.moveToFirst()){

                                    String minDateString = dateCursor.getString(1);
                                    String secondMinDateString = dateCursor.getString(0);
                                    if (minDateString != null && !minDateString.equals("") && !minDateString.equals("None")){
                                        minDate = f.parse(minDateString);
                                    } else if (secondMinDateString != null && !secondMinDateString.equals("") && !secondMinDateString.equals("None")){
                                        minDate = f.parse(secondMinDateString);
                                    }

                                }
                                dateCursor.close();
                            }

                            mDatePicker.getDatePicker().setMinDate(minDate.getTime());
                            mDatePicker.getDatePicker().setMaxDate(maxDate.getTime());

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }

                    mDatePicker.show();
                }
            });

            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button.setText(context.getString(R.string.select_date));
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, "");
                            //Synchronise arrival date and triage date
                            if (Objects.equals(items.get(position).dbKey, DBAdapter.KEY_ARRIVALDATE)){
                                ((Activity) context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        FragmentA.adapter.notifyDataSetChanged();
                                        FragmentD.adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    };
                    thread.start();
                }
            });

            noneBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean isChecked = ((CheckBox) v).isChecked();
                    if (isChecked) {
                        button.setText(context.getString(R.string.select_date));
                    }

                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            if (isChecked) {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, context.getString(R.string.none));
                            } else {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, "");
                            }
                            if (Objects.equals(items.get(position).dbKey, DBAdapter.KEY_ARRIVALDATE)) {
                                ((Activity) context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        FragmentA.adapter.notifyDataSetChanged();
                                        FragmentD.adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    };
                    thread.start();

                }
            });

            cursor.close();

        } else if (i1 == FragmentItem.CellType.TIMEPICKER) {
            rowView = inflater.inflate(R.layout.cell_fragment_datepicker, parent, false);
            textView = (TextView) rowView.findViewById(R.id.title);
            textView.setText(items.get(position).title);

            final Button timePickerButton = (Button) rowView.findViewById(R.id.button);
            timePickerButton.setText(context.getString(R.string.select_time));

            final Button clearButton1 = (Button) rowView.findViewById(R.id.clear);
            if (items.get(position).extraOptions != null && items.get(position).extraOptions[0].contains("optional")) {
                clearButton1.setVisibility(View.VISIBLE);
            } else {
                clearButton1.setVisibility(View.GONE);
            }

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            final CheckBox noneBox = (CheckBox) rowView.findViewById(R.id.noneCheckbox);

            if (items.get(position).extraOptions != null && items.get(position).extraOptions[0].contains("none")) {
                noneBox.setVisibility(View.VISIBLE);
            } else {
                noneBox.setVisibility(View.GONE);
            }

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            if (cursor.moveToFirst()) {
                String timeString = cursor.getString(0);

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
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, value);

                                    //Synchronise arrival time and triage time
                                    if (Objects.equals(items.get(position).dbKey, DBAdapter.KEY_ARRIVALTIME)){
                                        ((Activity) context).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                FragmentA.adapter.notifyDataSetChanged();
                                                FragmentD.adapter.notifyDataSetChanged();
                                            }
                                        });

                                    }
                                }
                            };
                            thread.start();
                        }
                    }, mHour, mMinute, false);
                    tpd.show();
                }
            });

            clearButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timePickerButton.setText(context.getString(R.string.select_date));
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, "");
                            //Synchronise arrival date and triage date
                            if (Objects.equals(items.get(position).dbKey, DBAdapter.KEY_ARRIVALTIME)){
                                ((Activity) context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        FragmentA.adapter.notifyDataSetChanged();
                                        FragmentD.adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    };
                    thread.start();
                }
            });

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

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, context.getString(R.string.none));
                            } else {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, "");
                            }
                            if (Objects.equals(items.get(position).dbKey, DBAdapter.KEY_ARRIVALTIME)) {
                                ((Activity) context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        FragmentA.adapter.notifyDataSetChanged();
                                        FragmentD.adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    };
                    thread.start();

                }
            });


            cursor.close();

        } else if (i1 == FragmentItem.CellType.FRACTURESITE) {
            rowView = inflater.inflate(R.layout.cell_fragment_fracture_site, parent, false);
            CheckBox cbFoot = (CheckBox) rowView.findViewById(R.id.foot);
            CheckBox cbAnkle = (CheckBox) rowView.findViewById(R.id.ankle);
            CheckBox cbTibia = (CheckBox) rowView.findViewById(R.id.tibiaFibula);
            CheckBox cbFemur = (CheckBox) rowView.findViewById(R.id.femur);
            CheckBox cbHip = (CheckBox) rowView.findViewById(R.id.hip);
            CheckBox cbPelvis = (CheckBox) rowView.findViewById(R.id.pelvis);
            CheckBox cbVertebra = (CheckBox) rowView.findViewById(R.id.vertebra);
            CheckBox cbRib = (CheckBox) rowView.findViewById(R.id.rib);
            CheckBox cbHumerus = (CheckBox) rowView.findViewById(R.id.humerus);
            CheckBox cbForearm = (CheckBox) rowView.findViewById(R.id.forearm);
            CheckBox cbWrist = (CheckBox) rowView.findViewById(R.id.wrist);

            CheckBox cbFootLeft = (CheckBox) rowView.findViewById(R.id.leftFoot);
            CheckBox cbFootRight = (CheckBox) rowView.findViewById(R.id.rightFoot);
            CheckBox cbAnkleLeft = (CheckBox) rowView.findViewById(R.id.leftAnkle);
            CheckBox cbAnkleRight = (CheckBox) rowView.findViewById(R.id.rightAnkle);
            CheckBox cbTibiaLeft = (CheckBox) rowView.findViewById(R.id.leftTibiaFibula);
            CheckBox cbTibiaRight = (CheckBox) rowView.findViewById(R.id.rightTibiaFibula);
            CheckBox cbFemurLeft = (CheckBox) rowView.findViewById(R.id.leftFemur);
            CheckBox cbFemurRight = (CheckBox) rowView.findViewById(R.id.rightFemur);
            CheckBox cbHipLeft = (CheckBox) rowView.findViewById(R.id.leftHip);
            CheckBox cbHipRight = (CheckBox) rowView.findViewById(R.id.rightHip);
            CheckBox cbRibLeft = (CheckBox) rowView.findViewById(R.id.leftRib);
            CheckBox cbRibRight = (CheckBox) rowView.findViewById(R.id.rightRib);
            CheckBox cbHumerusLeft = (CheckBox) rowView.findViewById(R.id.leftHumerus);
            CheckBox cbHumerusRight = (CheckBox) rowView.findViewById(R.id.rightHumerus);
            CheckBox cbForearmLeft = (CheckBox) rowView.findViewById(R.id.leftForearm);
            CheckBox cbForearmRight = (CheckBox) rowView.findViewById(R.id.rightForearm);
            CheckBox cbWristLeft = (CheckBox) rowView.findViewById(R.id.leftWrist);
            CheckBox cbWristRight = (CheckBox) rowView.findViewById(R.id.rightWrist);

            setupFractureSiteClickListener(cbFoot, cbFootLeft, cbFootRight, DBAdapter.KEY_FRACTURESITE_FOOT, rowView);
            setupFractureSiteClickListener(cbAnkle, cbAnkleLeft, cbAnkleRight, DBAdapter.KEY_FRACTURESITE_ANKLE, rowView);
            setupFractureSiteClickListener(cbTibia, cbTibiaLeft, cbTibiaRight, DBAdapter.KEY_FRACTURESITE_TIBIA, rowView);
            setupFractureSiteClickListener(cbFemur, cbFemurLeft, cbFemurRight, DBAdapter.KEY_FRACTURESITE_FEMUR, rowView);
            setupFractureSiteClickListener(cbHip, cbHipLeft, cbHipRight, DBAdapter.KEY_FRACTURESITE_HIP, rowView);
            setupFractureSiteClickListener(cbPelvis, null, null, DBAdapter.KEY_FRACTURESITE_PELVIS, rowView);
            setupFractureSiteClickListener(cbVertebra, null, null, DBAdapter.KEY_FRACTURESITE_VERTEBRA, rowView);
            setupFractureSiteClickListener(cbRib, cbRibLeft, cbRibRight, DBAdapter.KEY_FRACTURESITE_RIB, rowView);
            setupFractureSiteClickListener(cbHumerus, cbHumerusLeft, cbHumerusRight, DBAdapter.KEY_FRACTURESITE_HUMERUS, rowView);
            setupFractureSiteClickListener(cbForearm, cbForearmLeft, cbForearmRight, DBAdapter.KEY_FRACTURESITE_FOREARM, rowView);
            setupFractureSiteClickListener(cbWrist, cbWristLeft, cbWristRight, DBAdapter.KEY_FRACTURESITE_WRIST, rowView);


        } else if (i1 == FragmentItem.CellType.TEXT) {
            rowView = inflater.inflate(R.layout.cell_fragment_text, parent, false);
            textView = (TextView) rowView.findViewById(R.id.title);
            textView.setText(items.get(position).title);
            EditText edit = (EditText) rowView.findViewById(R.id.text);

            if (items.get(position).uiOptions != null) {
                edit.setHint(items.get(position).uiOptions[0]);
            }


            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            if (cursor.moveToFirst()) {
                String text = cursor.getString(0);
                if (text != null) {
                    edit.setText(text);
                } else {
                    edit.setText("");
                }

            } else {
                edit.setText("");
            }

            edit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, charSequence.toString());
                        }
                    };
                    thread.start();

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            cursor.close();

        } else if (i1 == FragmentItem.CellType.SPINNER) {
            rowView = inflater.inflate(R.layout.cell_fragment_spinner, parent, false);
            textView = (TextView) rowView.findViewById(R.id.title);
            textView.setText(items.get(position).title);


            final Spinner spinner = (Spinner) rowView.findViewById(R.id.spinner);
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, items.get(position).uiOptions);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spinner.performClick();
                }
            });

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            final String[] databaseArray;
            if (items.get(position).databaseOptions == null) {
                databaseArray = items.get(position).uiOptions;
            } else {
                databaseArray = items.get(position).databaseOptions;
            }

            spinner.setSelection(0);
            if (cursor.moveToFirst()) {
                String value = cursor.getString(0);
                if (value != null && !value.equals("")) {
                    for (int i = 0; i < databaseArray.length; i++) {
                        if (value.equals(databaseArray[i])) {
                            spinner.setSelection(i);
                            break;
                        }
                    }
                }
            }
            cursor.close();

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int j, long id) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, databaseArray[j]);
                        }
                    };
                    thread.start();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } else if (i1 == FragmentItem.CellType.SPINNER_WITH_OTHER) {
            rowView = inflater.inflate(R.layout.cell_fragment_spinner_with_other, parent, false);
            textView = (TextView) rowView.findViewById(R.id.title);
            textView.setText(items.get(position).title);

            final String[] spinnerOptions = Arrays.copyOf(items.get(position).uiOptions, items.get(position).uiOptions.length + 1);
            spinnerOptions[spinnerOptions.length - 1] = context.getString(R.string.other);

            String[] extraOptions;
            if (items.get(position).extraOptions != null){
                extraOptions = Arrays.copyOf(items.get(position).extraOptions, items.get(position).extraOptions.length + 1);
                extraOptions[extraOptions.length - 1] = "";
            } else {
                extraOptions = null;
            }


            final EditText editOther = (EditText) rowView.findViewById(R.id.other);

            final Spinner spinnerWithOther = (Spinner) rowView.findViewById(R.id.spinner);
            SpinnerAdapter spinnerWithOtherAdapter = new SpinnerAdapter(context, spinnerOptions, extraOptions);
            spinnerWithOther.setAdapter(spinnerWithOtherAdapter);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spinnerWithOther.performClick();
                }
            });

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            final String[] dataArray;
            if (items.get(position).databaseOptions == null) {
                dataArray = items.get(position).uiOptions;
            } else {
                dataArray = items.get(position).databaseOptions;
            }

            spinnerWithOther.setSelection(0, false);
            if (cursor.moveToFirst()) {
                String value = cursor.getString(0);
                if (value != null && !value.equals("")) {

                    if (value.startsWith(context.getString(R.string.other))) {
                        spinnerWithOther.setSelection(spinnerOptions.length - 1, false);
                        editOther.setVisibility(View.VISIBLE);

                        editOther.setTag("tag");
                        if (value.length() > context.getString(R.string.other).length()) {
                            editOther.setText(value.substring(context.getString(R.string.other).length() + 3));
                        } else {
                            editOther.setText("");
                        }

                        editOther.setTag(null);
                    } else {
                        editOther.setVisibility(View.GONE);
                        editOther.setTag("tag");
                        editOther.setText("");
                        editOther.setTag(null);
                        for (int i = 0; i < dataArray.length; i++) {
                            if (value.equals(dataArray[i])) {
                                spinnerWithOther.setSelection(i, false);
                                break;
                            }
                        }
                    }


                } else {
                    editOther.setVisibility(View.GONE);
                }
            }
            cursor.close();

            spinnerWithOther.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int j, long id) {

                    editOther.setTag("tag");
                    editOther.setText("");
                    editOther.setTag(null);
                    if (j == spinnerOptions.length - 1) {
                        editOther.setVisibility(View.VISIBLE);

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, context.getString(R.string.other) + " - " + editOther.getText().toString());
                            }
                        };
                        thread.start();
                    } else {
                        editOther.setVisibility(View.GONE);

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, dataArray[j]);
                            }
                        };
                        thread.start();
                    }
                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            editOther.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                    if (editOther.getTag() == null) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, context.getString(R.string.other) + " - " + charSequence.toString());
                            }
                        };
                        thread.start();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


        } else if (i1 == FragmentItem.CellType.CHECKBOX) {
            rowView = inflater.inflate(R.layout.cell_fragment_checkbox, parent, false);

            textView = (TextView) rowView.findViewById(R.id.title);
            textView.setText(items.get(position).title);

            final EditText editText = (EditText) rowView.findViewById(R.id.other);
            editText.setVisibility(View.GONE);

            final LinearLayout cg = (LinearLayout) rowView.findViewById(R.id.checkGroup);

            if (items.get(position).uiOptions.length > 2) {
                cg.setOrientation(RadioGroup.VERTICAL);
            } else {
                cg.setOrientation(RadioGroup.HORIZONTAL);
            }

            final String[] checkboxDataArray;
            if (items.get(position).databaseOptions == null) {
                checkboxDataArray = items.get(position).uiOptions;
            } else {
                checkboxDataArray = items.get(position).databaseOptions;
            }

            CheckBox cbNone = null;
            if (items.get(position).hasNone){
                cbNone = new CheckBox(context);
                cbNone.setText(items.get(position).extraOptions[0]);
            }
            final CheckBox cbNoneFinal = cbNone;


            CheckBox cbOther = null;
            if (items.get(position).hasOther){
                cbOther = new CheckBox(context);
                cbOther.setText(context.getString(R.string.other));
            }
            final CheckBox cbOtherFinal = cbOther;

            for (int i = 0; i < items.get(position).uiOptions.length; i++) {
                CheckBox cb = new CheckBox(context);
                cb.setText(items.get(position).uiOptions[i]);
                cg.addView(cb);


                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            if (cbNoneFinal != null) {
                                cbNoneFinal.setChecked(false);
                            }

                            if (cbOtherFinal != null) {
                                cbOtherFinal.setChecked(false);
                                editText.setVisibility(View.GONE);

                            }
                        }

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                String answer = "";
                                for (int i = 0; i < cg.getChildCount(); i++) {
                                    CheckBox cb = (CheckBox) cg.getChildAt(i);
                                    if (cb.isChecked()) {
                                        answer = answer + " " + checkboxDataArray[i];
                                    }
                                }

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, answer);
                            }
                        };
                        thread.start();
                    }
                });



            }

            if (cbNoneFinal != null){
                cg.addView(cbNoneFinal);

                cbNoneFinal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                if (cbNoneFinal.isChecked()) {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, cbNoneFinal.getText().toString());
                                    ((Activity) context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            notifyDataSetChanged();
                                        }
                                    });
                                } else {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, "");
                                }
                            }
                        };
                        thread.start();
                    }
                });

            }

            if (cbOtherFinal != null){
                cg.addView(cbOtherFinal);

                cbOtherFinal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                if (cbOtherFinal.isChecked()) {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, context.getString(R.string.other));
                                    ((Activity)context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            notifyDataSetChanged();
                                        }
                                    });
                                } else {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, "");
                                    ((Activity)context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            editText.setVisibility(View.GONE);
                                        }
                                    });
                                }

                            }
                        };
                        thread.start();
                    }
                });
            }

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            if (cursor.moveToFirst()) {
                String answer = cursor.getString(0);

                if (answer != null) {
                    for (int i = 0; i < checkboxDataArray.length; i++) {
                        if (answer.contains(checkboxDataArray[i])) {
                            ((CheckBox) cg.getChildAt(i)).setChecked(true);
                        } else {
                            ((CheckBox) cg.getChildAt(i)).setChecked(false);
                        }
                    }
                    if (items.get(position).hasNone){
                        if (answer.equals(items.get(position).extraOptions[0])){
                            cbNoneFinal.setChecked(true);
                        } else {
                            cbNoneFinal.setChecked(false);
                        }
                    }

                    if (items.get(position).hasOther){

                        if (answer.startsWith(context.getString(R.string.other))){
                            cbOtherFinal.setChecked(true);
                            editText.setVisibility(View.VISIBLE);

                            editText.setTag("tag");
                            if (answer.length() > context.getString(R.string.other).length()) {
                                editText.setText(answer.substring(context.getString(R.string.other).length() + 3));
                            } else {
                                editText.setText("");
                            }
                            editText.setTag(null);
                        } else {
                            cbOtherFinal.setChecked(false);
                            editText.setVisibility(View.GONE);
                        }

                    }

                } else {
                    for (int i = 0; i < cg.getChildCount(); i++) {
                        CheckBox box = (CheckBox) cg.getChildAt(i);
                        box.setChecked(false);
                    }
                }

            } else {
                for (int i = 0; i < cg.getChildCount(); i++) {
                    CheckBox box = (CheckBox) cg.getChildAt(i);
                    box.setChecked(false);
                }
            }

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                    if (editText.getTag() == null) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, context.getString(R.string.other) + " - " + charSequence.toString());
                            }
                        };
                        thread.start();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            cursor.close();

        } else if (i1 == FragmentItem.CellType.PAIN_ASSESSMENTS) {
            rowView = PainAssessments.setupPainAssessmentSection(context, parent, this);
        } else if (i1 == FragmentItem.CellType.ANALGESIC_PRESCRIPTION){
            rowView = AnalgesicPrescription.setupAnalgesicPrescriptionSection(context, parent, this);
        } else if (i1 == FragmentItem.CellType.ANALGESIC_ADMINISTRATION){
            rowView = AnalgesicAdministration.setupAnalgesicAdministration(context, parent, this);
        }



        return rowView;
    }


    private void setupFractureSiteClickListener(final CheckBox cb, final CheckBox cbLeft, final CheckBox cbRight, final String dbKey, final View view){

        loadFractureSiteCheckbox(cb, cbLeft, cbRight, dbKey, view);

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFractureSiteVisibility(cb, cbLeft, cbRight, ((CheckBox) v).isChecked(), view);
                writeFractureSiteVisibility(cb, cbLeft, cbRight, dbKey);
            }
        });

        if (cbLeft != null){

            cbLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    writeFractureSiteVisibility(cb, cbLeft, cbRight, dbKey);
                }
            });
        }

        if (cbRight != null){

            cbRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    writeFractureSiteVisibility(cb, cbLeft, cbRight, dbKey);
                }
            });
        }
    }

    private void setFractureSiteVisibility(CheckBox cb, CheckBox cbLeft, CheckBox cbRight, boolean isChecked, View view){
        if (cbLeft != null) {

            String tag = cb.getTag().toString();
            String layoutName = tag + "Layout";
            int layoutID = context.getResources().getIdentifier(layoutName, "id", context.getPackageName());
            LinearLayout layout = (LinearLayout) view.findViewById(layoutID);
            if (isChecked) {
                layout.setVisibility(View.VISIBLE);
            } else {
                layout.setVisibility(View.GONE);
                cbLeft.setChecked(false);
                cbRight.setChecked(false);
            }
        }
    }

    private void writeFractureSiteVisibility(CheckBox cb, CheckBox cbLeft, CheckBox cbRight, final String dbKey){
        String value = "";
        if (cb.isChecked()){
            if (cbLeft != null){

                if (cbLeft.isChecked()){
                    value = context.getString(R.string.left);
                }

                if (cbRight.isChecked()){
                    value = value + context.getString(R.string.right);
                }

                if (value.equals("")){
                    value = context.getString(R.string.unspecified);
                }

            } else {
                value = context.getString(R.string.yes);
            }
        } else {
            value = "";
        }

        final String finalValue = value;

        Thread thread = new Thread(){
            @Override
            public void run() {
                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, dbKey, finalValue);
            }
        };
        thread.start();


    }


    private void loadFractureSiteCheckbox(final CheckBox cb, final CheckBox cbLeft, final CheckBox cbRight, final String dbKey, final View view){
        Cursor cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, dbKey);

        if (cursor.moveToFirst()){
            String checkValue = cursor.getString(0);
            if (checkValue != null  && !checkValue.equals("")){

                cb.setChecked(true);
                setFractureSiteVisibility(cb, cbLeft, cbRight, true, view);

                if (cbLeft != null){
                    if (checkValue.equals(context.getString(R.string.unspecified))){
                        cbLeft.setChecked(false);
                        cbRight.setChecked(false);
                    } else {
                        if (checkValue.contains(context.getString(R.string.left))){
                            cbLeft.setChecked(true);
                        } else {
                            cbLeft.setChecked(false);
                        }

                        if (checkValue.contains(context.getString(R.string.right))){
                            cbRight.setChecked(true);
                        } else {
                            cbRight.setChecked(false);
                        }
                    }
                }

            } else {
                cb.setChecked(false);
                setFractureSiteVisibility(cb, cbLeft, cbRight, false, view);

                if (cbLeft != null){
                    cbLeft.setChecked(false);
                    cbRight.setChecked(false);
                }
            }
        }

        cursor.close();
    }


    private class SpinnerAdapter extends BaseAdapter
    {
        LayoutInflater inflater;
        String[] titles;
        String[] subtitles;

        public SpinnerAdapter( Context context, String[] titles, String[] subtitles)
        {
            this.inflater = LayoutInflater.from(context);
            this.titles = titles;
            this.subtitles = subtitles;
        }

        @Override
        public int getCount()
        {
            return titles.length;
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            convertView = inflater.inflate(R.layout.cell_spinner_subtitle, null);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView subtitle = (TextView) convertView.findViewById(R.id.subtitle);
            title.setText(titles[position]);

            if (subtitles != null){
                subtitle.setText(subtitles[position]);
            } else {
                subtitle.setText("");
            }

            return convertView;

        }
    }

}
