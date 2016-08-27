package physiotherapy.mcgill.com.painfree.MainGroup;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Cursor cursor;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FragmentItem.CellType i1 = FragmentItem.CellType.values()[this.getItemViewType(position)];

        if (i1 == FragmentItem.CellType.NUMERIC) {

            ViewHolderNumeric holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.cell_fragment_numeric, parent, false);
                holder = new ViewHolderNumeric();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                holder.editText = (EditText) convertView.findViewById(R.id.edit);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolderNumeric) convertView.getTag();
            }

            holder.textView.setText(items.get(position).title);

            if (items.get(position).uiOptions != null) {
                holder.editText.setHint(items.get(position).uiOptions[0]);
            }

            if (items.get(position).extraOptions != null && items.get(position).extraOptions[0].equals("password")) {
                holder.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                holder.editText.setTransformationMethod(null);
            }

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            if (cursor.moveToFirst()) {
                String text = cursor.getString(0);
                if (text != null) {
                    holder.editText.setText(text);
                } else {
                    holder.editText.setText("");
                }

            } else {
                holder.editText.setText("");
            }

            holder.editText.addTextChangedListener(new TextWatcher() {
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

            ViewHolderRadio holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.cell_fragment_radio, parent, false);
                holder = new ViewHolderRadio();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                holder.rg = (RadioGroup) convertView.findViewById(R.id.rg);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderRadio) convertView.getTag();

            }

            holder.textView.setText(items.get(position).title);
            holder.rg.removeAllViews();

            if (items.get(position).databaseOptions == null) {
                items.get(position).databaseOptions = items.get(position).uiOptions;
            }

            if (items.get(position).uiOptions.length > 2 && items.get(position).uiOptions.length < 7) {
                holder.rg.setOrientation(RadioGroup.VERTICAL);
            } else {
                holder.rg.setOrientation(RadioGroup.HORIZONTAL);
            }

            for (int i = 0; i < items.get(position).uiOptions.length; i++) {
                RadioButton rb = new RadioButton(context);
                rb.setText(items.get(position).uiOptions[i]);
                rb.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_medium));
                holder.rg.addView(rb);

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
                            ((RadioButton) holder.rg.getChildAt(i)).setChecked(true);
                        }
                    }
                } else {
                    holder.rg.clearCheck();
                }

            } else {
                holder.rg.clearCheck();
            }

            cursor.close();

        } else if (i1 == FragmentItem.CellType.RADIO_WITH_SPECIFY) {

            final ViewHolderRadioWithSpecify holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.cell_fragment_radio_with_specify, parent, false);
                holder = new ViewHolderRadioWithSpecify();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                holder.editText = (EditText) convertView.findViewById(R.id.specify);
                holder.rg = (RadioGroup) convertView.findViewById(R.id.rg);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderRadioWithSpecify) convertView.getTag();
            }

            holder.textView.setText(items.get(position).title);
            holder.rg.removeAllViews();

            if (items.get(position).databaseOptions == null) {
                items.get(position).databaseOptions = items.get(position).uiOptions;
            }

            if (items.get(position).uiOptions.length > 2 && items.get(position).uiOptions.length < 7) {
                holder.rg.setOrientation(RadioGroup.VERTICAL);
                holder.rg.setOrientation(RadioGroup.VERTICAL);
            } else {
                holder.rg.setOrientation(RadioGroup.HORIZONTAL);
            }

            for (int i = 0; i < items.get(position).uiOptions.length; i++) {
                RadioButton rb = new RadioButton(context);
                rb.setText(items.get(position).uiOptions[i]);
                rb.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_medium));
                holder.rg.addView(rb);

                final int index = i;
                rb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(final View view) {

                        if (index==0){
                            holder.editText.setVisibility(View.VISIBLE);
                        } else {
                            holder.editText.setVisibility(View.GONE);
                            holder.editText.setText("");
                        }

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, items.get(position).databaseOptions[index]);
                                if (index == 1){
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).extraOptions[0], "");
                                }
                            }
                        };
                        thread.start();

                    }
                });
            }

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            holder.editText.setVisibility(View.GONE);
            if (cursor.moveToFirst()) {
                String radioValue = cursor.getString(0);
                if (radioValue != null && !radioValue.equals("")) {
                    for (int i = 0; i < items.get(position).databaseOptions.length; i++) {
                        String rbString = items.get(position).databaseOptions[i];
                        if (rbString.equals(radioValue)) {
                            ((RadioButton) holder.rg.getChildAt(i)).setChecked(true);

                            if (i==0){
                                holder.editText.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                } else {
                    holder.rg.clearCheck();
                }

            } else {
                holder.rg.clearCheck();
            }

            cursor.close();


            Cursor cursor2 = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).extraOptions[0]);

            if (cursor2.moveToFirst()) {
                String text = cursor2.getString(0);
                if (text != null) {
                    holder.editText.setText(text);
                } else {
                    holder.editText.setText("");
                }

            } else {
                holder.editText.setText("");
            }

            holder.editText.addTextChangedListener(new TextWatcher() {
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

        else if (i1 == FragmentItem.CellType.RETURN_TO_ED) {

            final ViewHolderReturnToED holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.cell_fragment_return_to_ed, parent, false);
                holder = new ViewHolderReturnToED();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                holder.editText = (EditText) convertView.findViewById(R.id.specify);
                holder.reasonLayout = (LinearLayout) convertView.findViewById(R.id.reasonLayout);
                holder.cbUncontrolledPain = (CheckBox) convertView.findViewById(R.id.cbUncontrolledPain);
                holder.rg = (RadioGroup) convertView.findViewById(R.id.rg);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderReturnToED) convertView.getTag();
            }

            holder.textView.setText(items.get(position).title);
            holder.rg.removeAllViews();

            if (items.get(position).databaseOptions == null) {
                items.get(position).databaseOptions = items.get(position).uiOptions;
            }

            if (items.get(position).uiOptions.length > 2 && items.get(position).uiOptions.length < 7) {
                holder.rg.setOrientation(RadioGroup.VERTICAL);
                holder.rg.setOrientation(RadioGroup.VERTICAL);
            } else {
                holder.rg.setOrientation(RadioGroup.HORIZONTAL);
            }

            for (int i = 0; i < items.get(position).uiOptions.length; i++) {
                RadioButton rb = new RadioButton(context);
                rb.setText(items.get(position).uiOptions[i]);
                rb.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_medium));
                holder.rg.addView(rb);

                final int index = i;
                rb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(final View view) {

                        if (index==0){
                            holder.reasonLayout.setVisibility(View.VISIBLE);
                        } else {
                            holder.reasonLayout.setVisibility(View.GONE);
                            holder.editText.setText("");
                            holder.cbUncontrolledPain.setChecked(false);
                        }

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, items.get(position).databaseOptions[index]);
                                if (index == 1){
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).extraOptions[0], "");
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_RETURN_ED_UNCONTROLLED_PAIN, null);
                                }
                            }
                        };
                        thread.start();

                    }
                });
            }

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            holder.reasonLayout.setVisibility(View.GONE);
            if (cursor.moveToFirst()) {
                String radioValue = cursor.getString(0);
                if (radioValue != null && !radioValue.equals("")) {
                    for (int i = 0; i < items.get(position).databaseOptions.length; i++) {
                        String rbString = items.get(position).databaseOptions[i];
                        if (rbString.equals(radioValue)) {
                            ((RadioButton) holder.rg.getChildAt(i)).setChecked(true);

                            if (i==0){
                                holder.reasonLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                } else {
                    holder.rg.clearCheck();
                }

            } else {
                holder.rg.clearCheck();
            }

            cursor.close();

            Cursor cursor3 = MainActivity.myDb.getDataField(MainActivity.currentPatientId, DBAdapter.KEY_RETURN_ED_UNCONTROLLED_PAIN);
            if (cursor3.moveToFirst()){
                String uncPain = cursor3.getString(0);
                if (uncPain != null && uncPain.equals("Yes")){
                    holder.cbUncontrolledPain.setChecked(true);
                } else {
                    holder.cbUncontrolledPain.setChecked(false);
                }
            }

            cursor3.close();

            holder.cbUncontrolledPain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dbValue = null;
                    if (((CheckBox)v).isChecked()){
                        dbValue = "Yes";
                    }

                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_RETURN_ED_UNCONTROLLED_PAIN, dbValue);
                }
            });


            Cursor cursor2 = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).extraOptions[0]);

            if (cursor2.moveToFirst()) {
                String text = cursor2.getString(0);
                if (text != null) {
                    holder.editText.setText(text);
                } else {
                    holder.editText.setText("");
                }

            } else {
                holder.editText.setText("");
            }

            holder.editText.addTextChangedListener(new TextWatcher() {
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

            final ViewHolderDatePicker holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.cell_fragment_datepicker, parent, false);
                holder = new ViewHolderDatePicker();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                holder.button = (Button) convertView.findViewById(R.id.button);
                holder.clearButton = (Button) convertView.findViewById(R.id.clear);
                holder.noneBox = (CheckBox) convertView.findViewById(R.id.noneCheckbox);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderDatePicker) convertView.getTag();
            }


            holder.textView.setText(items.get(position).title);
            holder.button.setText(context.getString(R.string.select_date));
            holder.noneBox.setChecked(false);

            if (items.get(position).extraOptions != null && items.get(position).extraOptions[0].contains("optional")) {
                holder.clearButton.setVisibility(View.VISIBLE);
            } else {
                holder.clearButton.setVisibility(View.GONE);
            }


            if (items.get(position).extraOptions != null && items.get(position).extraOptions[0].contains("none")) {
                holder.noneBox.setVisibility(View.VISIBLE);
            } else {
                holder.noneBox.setVisibility(View.GONE);
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
                        holder.noneBox.setChecked(true);
                    } else if (!dateString.equals("")) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = format.parse(dateString);
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            mYear = cal.get(Calendar.YEAR);
                            mMonth = cal.get(Calendar.MONTH);
                            mDay = cal.get(Calendar.DAY_OF_MONTH);

                            holder.button.setText(mYear + "-" + String.format("%02d", mMonth + 1) + "-" + String.format("%02d", mDay));
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

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    DatePickerDialog mDatePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            final String text = selectedyear + "-" + String.format("%02d", selectedmonth + 1) + "-" + String.format("%02d", selectedday);
                            holder.button.setText(text);
                            holder.noneBox.setChecked(false);
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, text);

                                    //Synchronise arrival date and triage date
                                    if (Objects.equals(items.get(position).dbKey, DBAdapter.KEY_ARRIVALDATE)){

                                        Cursor syncCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_TRIAGE_DATE, DBAdapter.KEY_PHYSICIAN_EXAMINATION_DATE, DBAdapter.KEY_DISCHARGE_DATE});
                                        syncCursor.moveToFirst();
                                        //if (syncCursor.getString(0) == null || syncCursor.getString(0).equals("")){
                                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_TRIAGE_DATE, text);
                                        //}
                                        //if (syncCursor.getString(1) == null || syncCursor.getString(1).equals("")){
                                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_PHYSICIAN_EXAMINATION_DATE, text);
                                        //}
                                        //if (syncCursor.getString(2) == null || syncCursor.getString(2).equals("")){
                                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_DISCHARGE_DATE, text);
                                        //}
                                        syncCursor.close();

                                        ((Activity) context).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                FragmentD.adapter.notifyDataSetChanged();
                                                FragmentE.adapter.notifyDataSetChanged();
                                                FragmentG.adapter.notifyDataSetChanged();
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

//                            if (items.get(position).dbKey.equals(DBAdapter.KEY_ARRIVALDATE)){
//
//                                Cursor dateCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_PHYSICIAN_EXAMINATION_DATE, DBAdapter.KEY_DISCHARGE_DATE});
//                                if (dateCursor.moveToFirst()){
//                                    String maxDateString = dateCursor.getString(0);
//                                    String secondMaxDateString = dateCursor.getString(1);
//                                    if (maxDateString != null && !maxDateString.equals("") && !maxDateString.equals("None")){
//                                        maxDate = f.parse(maxDateString);
//                                    } else if (secondMaxDateString != null && !secondMaxDateString.equals("") && !secondMaxDateString.equals("None")){
//                                        maxDate = f.parse(secondMaxDateString);
//                                    }
//                                }
//                                dateCursor.close();
//                            }
//
//                            if (items.get(position).dbKey.equals(DBAdapter.KEY_PHYSICIAN_EXAMINATION_DATE)){
//
//                                Cursor dateCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_ARRIVALDATE, DBAdapter.KEY_DISCHARGE_DATE});
//                                if (dateCursor.moveToFirst()){
//
//                                    String minDateString = dateCursor.getString(0);
//                                    if (minDateString != null && !minDateString.equals("") && !minDateString.equals("None")){
//                                        minDate = f.parse(minDateString);
//                                    }
//
//                                    String maxDateString = dateCursor.getString(1);
//                                    if (maxDateString != null && !maxDateString.equals("") && !maxDateString.equals("None")){
//                                        maxDate = f.parse(maxDateString);
//                                    }
//                                }
//                                dateCursor.close();
//                            }
//
//                            if (items.get(position).dbKey.equals(DBAdapter.KEY_DISCHARGE_DATE)){
//
//                                Cursor dateCursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_ARRIVALDATE, DBAdapter.KEY_PHYSICIAN_EXAMINATION_DATE});
//                                if (dateCursor.moveToFirst()){
//
//                                    String minDateString = dateCursor.getString(1);
//                                    String secondMinDateString = dateCursor.getString(0);
//                                    if (minDateString != null && !minDateString.equals("") && !minDateString.equals("None")){
//                                        minDate = f.parse(minDateString);
//                                    } else if (secondMinDateString != null && !secondMinDateString.equals("") && !secondMinDateString.equals("None")){
//                                        minDate = f.parse(secondMinDateString);
//                                    }
//
//                                }
//                                dateCursor.close();
//                            }

                            mDatePicker.getDatePicker().setMinDate(minDate.getTime());
                            mDatePicker.getDatePicker().setMaxDate(maxDate.getTime());

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }

                    mDatePicker.show();
                }
            });

            holder.clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.button.setText(context.getString(R.string.select_date));
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, "");

                        }
                    };
                    thread.start();
                }
            });

            holder.noneBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean isChecked = ((CheckBox) v).isChecked();
                    if (isChecked) {
                        holder.button.setText(context.getString(R.string.select_date));
                    }

                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            if (isChecked) {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, context.getString(R.string.none));
                            } else {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, "");
                            }

                        }
                    };
                    thread.start();

                }
            });

            cursor.close();

        } else if (i1 == FragmentItem.CellType.TIMEPICKER) {

            final ViewHolderTimePicker holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.cell_fragment_datepicker, parent, false);
                holder = new ViewHolderTimePicker();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                holder.button = (Button) convertView.findViewById(R.id.button);
                holder.clearButton = (Button) convertView.findViewById(R.id.clear);
                holder.noneBox = (CheckBox) convertView.findViewById(R.id.noneCheckbox);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderTimePicker) convertView.getTag();
            }

            holder.textView.setText(items.get(position).title);

            holder.button.setText(context.getString(R.string.select_time));
            holder.noneBox.setChecked(false);
            if (items.get(position).extraOptions != null && items.get(position).extraOptions[0].contains("optional")) {
                holder.clearButton.setVisibility(View.VISIBLE);
            } else {
                holder.clearButton.setVisibility(View.GONE);
            }

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);


            if (items.get(position).extraOptions != null && items.get(position).extraOptions[0].contains("none")) {
                holder.noneBox.setVisibility(View.VISIBLE);
            } else {
                holder.noneBox.setVisibility(View.GONE);
            }

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            if (cursor.moveToFirst()) {
                String timeString = cursor.getString(0);

                if (timeString != null){

                    if (timeString.equals("None")){
                        holder.noneBox.setChecked(true);
                    } else if (!timeString.equals("")) {
                        holder.button.setText(timeString);
                        String[] parts = timeString.split(":");
                        hour = Integer.parseInt(parts[0]);
                        minute = Integer.parseInt(parts[1]);
                    }
                }

            }

            final int mHour = hour;
            final int mMinute = minute;

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    TimePickerDialog tpd = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            final String value = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
                            holder.button.setText(value);
                            holder.noneBox.setChecked(false);
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
                    }, mHour, mMinute, true);
                    tpd.show();
                }
            });

            holder.clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.button.setText(context.getString(R.string.select_date));
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

            holder.noneBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean isChecked = ((CheckBox) v).isChecked();
                    if (isChecked) {
                        holder.button.setText(context.getString(R.string.select_time));
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

            final ViewHolderFractureSite holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.cell_fragment_fracture_site, parent, false);
                holder = new ViewHolderFractureSite();
                holder.cbFoot = (CheckBox) convertView.findViewById(R.id.foot);
                holder.cbAnkle = (CheckBox) convertView.findViewById(R.id.ankle);
                holder.cbTibia = (CheckBox) convertView.findViewById(R.id.tibiaFibula);
                holder.cbFemur = (CheckBox) convertView.findViewById(R.id.femur);
                holder.cbHip = (CheckBox) convertView.findViewById(R.id.hip);
                holder.cbPelvis = (CheckBox) convertView.findViewById(R.id.pelvis);
                holder.cbVertebra = (CheckBox) convertView.findViewById(R.id.vertebra);
                holder.cbRib = (CheckBox) convertView.findViewById(R.id.rib);
                holder.cbHumerus = (CheckBox) convertView.findViewById(R.id.humerus);
                holder.cbForearm = (CheckBox) convertView.findViewById(R.id.forearm);
                holder.cbWrist = (CheckBox) convertView.findViewById(R.id.wrist);
                holder.cbHead = (CheckBox) convertView.findViewById(R.id.head);
                holder.cbToes = (CheckBox) convertView.findViewById(R.id.toes);
                holder.cbFingers = (CheckBox) convertView.findViewById(R.id.fingers);
                holder.cbHand = (CheckBox) convertView.findViewById(R.id.hand);


                holder.cbFootLeft = (CheckBox) convertView.findViewById(R.id.leftFoot);
                holder.cbFootRight = (CheckBox) convertView.findViewById(R.id.rightFoot);
                holder.cbFootUnspecified = (CheckBox) convertView.findViewById(R.id.unspecifiedFoot);
                holder.cbAnkleLeft = (CheckBox) convertView.findViewById(R.id.leftAnkle);
                holder.cbAnkleRight = (CheckBox) convertView.findViewById(R.id.rightAnkle);
                holder.cbAnkleUnspecified = (CheckBox) convertView.findViewById(R.id.unspecifiedAnkle);
                holder.cbTibiaLeft = (CheckBox) convertView.findViewById(R.id.leftTibiaFibula);
                holder.cbTibiaRight = (CheckBox) convertView.findViewById(R.id.rightTibiaFibula);
                holder.cbTibiaUnspecified = (CheckBox) convertView.findViewById(R.id.unspecifiedTibiaFibula);
                holder.cbFemurLeft = (CheckBox) convertView.findViewById(R.id.leftFemur);
                holder.cbFemurRight = (CheckBox) convertView.findViewById(R.id.rightFemur);
                holder.cbFemurUnspecified = (CheckBox) convertView.findViewById(R.id.unspecifiedFemur);
                holder.cbHipLeft = (CheckBox) convertView.findViewById(R.id.leftHip);
                holder.cbHipRight = (CheckBox) convertView.findViewById(R.id.rightHip);
                holder.cbHipUnspecified = (CheckBox) convertView.findViewById(R.id.unspecifiedHip);
                holder.cbRibLeft = (CheckBox) convertView.findViewById(R.id.leftRib);
                holder.cbRibRight = (CheckBox) convertView.findViewById(R.id.rightRib);
                holder.cbRibUnspecified = (CheckBox) convertView.findViewById(R.id.unspecifiedRib);
                holder.cbHumerusLeft = (CheckBox) convertView.findViewById(R.id.leftHumerus);
                holder.cbHumerusRight = (CheckBox) convertView.findViewById(R.id.rightHumerus);
                holder.cbHumerusUnspecified = (CheckBox) convertView.findViewById(R.id.unspecifiedHumerus);
                holder.cbForearmLeft = (CheckBox) convertView.findViewById(R.id.leftForearm);
                holder.cbForearmRight = (CheckBox) convertView.findViewById(R.id.rightForearm);
                holder.cbForearmUnspecified = (CheckBox) convertView.findViewById(R.id.unspecifiedForearm);
                holder.cbWristLeft = (CheckBox) convertView.findViewById(R.id.leftWrist);
                holder.cbWristRight = (CheckBox) convertView.findViewById(R.id.rightWrist);
                holder.cbWristUnspecified = (CheckBox) convertView.findViewById(R.id.unspecifiedWrist);
                holder.cbHandLeft = (CheckBox) convertView.findViewById(R.id.leftHand);
                holder.cbHandRight = (CheckBox) convertView.findViewById(R.id.rightHand);
                holder.cbHandUnspecified = (CheckBox) convertView.findViewById(R.id.unspecifiedHand);

                holder.editOther = (EditText) convertView.findViewById(R.id.editOther);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderFractureSite) convertView.getTag();
            }

            setupFractureSiteClickListener(holder.cbFoot, holder.cbFootLeft, holder.cbFootRight, holder.cbFootUnspecified, DBAdapter.KEY_FRACTURESITE_FOOT, convertView);
            setupFractureSiteClickListener(holder.cbAnkle, holder.cbAnkleLeft, holder.cbAnkleRight, holder.cbAnkleUnspecified, DBAdapter.KEY_FRACTURESITE_ANKLE, convertView);
            setupFractureSiteClickListener(holder.cbTibia, holder.cbTibiaLeft, holder.cbTibiaRight, holder.cbTibiaUnspecified, DBAdapter.KEY_FRACTURESITE_TIBIA, convertView);
            setupFractureSiteClickListener(holder.cbFemur, holder.cbFemurLeft, holder.cbFemurRight, holder.cbFemurUnspecified, DBAdapter.KEY_FRACTURESITE_FEMUR, convertView);
            setupFractureSiteClickListener(holder.cbHip, holder.cbHipLeft, holder.cbHipRight, holder.cbHipUnspecified, DBAdapter.KEY_FRACTURESITE_HIP, convertView);
            setupFractureSiteClickListener(holder.cbPelvis, null, null, null, DBAdapter.KEY_FRACTURESITE_PELVIS, convertView);
            setupFractureSiteClickListener(holder.cbVertebra, null, null, null, DBAdapter.KEY_FRACTURESITE_VERTEBRA, convertView);
            setupFractureSiteClickListener(holder.cbRib, holder.cbRibLeft, holder.cbRibRight, holder.cbRibUnspecified, DBAdapter.KEY_FRACTURESITE_RIB, convertView);
            setupFractureSiteClickListener(holder.cbHumerus, holder.cbHumerusLeft, holder.cbHumerusRight, holder.cbHumerusUnspecified, DBAdapter.KEY_FRACTURESITE_HUMERUS, convertView);
            setupFractureSiteClickListener(holder.cbForearm, holder.cbForearmLeft, holder.cbForearmRight, holder.cbForearmUnspecified, DBAdapter.KEY_FRACTURESITE_FOREARM, convertView);
            setupFractureSiteClickListener(holder.cbWrist, holder.cbWristLeft, holder.cbWristRight, holder.cbWristUnspecified, DBAdapter.KEY_FRACTURESITE_WRIST, convertView);
            setupFractureSiteClickListener(holder.cbHead, null, null, null, DBAdapter.KEY_FRACTURESITE_HEAD, convertView);
            setupFractureSiteClickListener(holder.cbToes, null, null, null, DBAdapter.KEY_FRACTURESITE_TOES, convertView);
            setupFractureSiteClickListener(holder.cbFingers, null, null, null, DBAdapter.KEY_FRACTURESITE_FINGERS, convertView);
            setupFractureSiteClickListener(holder.cbHand, holder.cbHandLeft, holder.cbHandRight, holder.cbHandUnspecified, DBAdapter.KEY_FRACTURESITE_HAND, convertView);

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, DBAdapter.KEY_FRACTURESITE_OTHER);

            if (cursor.moveToFirst()) {
                String text = cursor.getString(0);
                if (text != null) {
                    holder.editOther.setText(text);
                } else {
                    holder.editOther.setText("");
                }

            } else {
                holder.editOther.setText("");
            }

            holder.editOther.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_FRACTURESITE_OTHER, charSequence.toString());
                        }
                    };
                    thread.start();

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            cursor.close();

        } else if (i1 == FragmentItem.CellType.TEXT) {

            ViewHolderText holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.cell_fragment_text, parent, false);
                holder = new ViewHolderText();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                holder.editText = (EditText) convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderText) convertView.getTag();
            }

            holder.textView.setText(items.get(position).title);

            if (items.get(position).uiOptions != null) {
                holder.editText.setHint(items.get(position).uiOptions[0]);
            }


            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            if (cursor.moveToFirst()) {
                String text = cursor.getString(0);
                if (text != null) {
                    holder.editText.setText(text);
                } else {
                    holder.editText.setText("");
                }

            } else {
                holder.editText.setText("");
            }

            holder.editText.addTextChangedListener(new TextWatcher() {
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

            final ViewHolderSpinner holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.cell_fragment_spinner, parent, false);
                holder = new ViewHolderSpinner();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                holder.spinner = (Spinner) convertView.findViewById(R.id.spinner);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderSpinner) convertView.getTag();
            }

            holder.textView.setText(items.get(position).title);

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, items.get(position).uiOptions);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spinner.setAdapter(spinnerAdapter);

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.spinner.performClick();
                }
            });

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            final String[] databaseArray;
            if (items.get(position).databaseOptions == null) {
                databaseArray = items.get(position).uiOptions;
            } else {
                databaseArray = items.get(position).databaseOptions;
            }

            holder.spinner.setSelection(0);
            if (cursor.moveToFirst()) {
                String value = cursor.getString(0);
                if (value != null && !value.equals("")) {
                    for (int i = 0; i < databaseArray.length; i++) {
                        if (value.equals(databaseArray[i])) {
                            holder.spinner.setSelection(i);
                            break;
                        }
                    }
                }
            }
            cursor.close();

            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

            final ViewHolderSpinnerWithOther holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.cell_fragment_spinner_with_other, parent, false);
                holder = new ViewHolderSpinnerWithOther();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                holder.spinner = (Spinner) convertView.findViewById(R.id.spinner);
                holder.editText = (EditText) convertView.findViewById(R.id.other);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderSpinnerWithOther) convertView.getTag();
            }
            holder.spinner.setOnItemSelectedListener(null);
            holder.textView.setText(items.get(position).title);

            final String[] spinnerOptions = Arrays.copyOf(items.get(position).uiOptions, items.get(position).uiOptions.length + 1);
            spinnerOptions[spinnerOptions.length - 1] = context.getString(R.string.other);

            String[] extraOptions;
            if (items.get(position).extraOptions != null){
                extraOptions = Arrays.copyOf(items.get(position).extraOptions, items.get(position).extraOptions.length + 1);
                extraOptions[extraOptions.length - 1] = "";
            } else {
                extraOptions = null;
            }

            SpinnerAdapter spinnerWithOtherAdapter = new SpinnerAdapter(context, spinnerOptions, extraOptions);
            holder.spinner.setAdapter(spinnerWithOtherAdapter);

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.spinner.performClick();
                }
            });

            cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

            final String[] dataArray;
            if (items.get(position).databaseOptions == null) {
                dataArray = items.get(position).uiOptions;
            } else {
                dataArray = items.get(position).databaseOptions;
            }

            holder.spinner.setSelection(0, false);
            if (cursor.moveToFirst()) {
                String value = cursor.getString(0);
                if (value != null && !value.equals("")) {

                    if (value.startsWith(context.getString(R.string.other))) {
                        holder.spinner.setSelection(spinnerOptions.length - 1, false);
                        holder.editText.setVisibility(View.VISIBLE);

                        holder.editText.setTag("tag");
                        if (value.length() > context.getString(R.string.other).length()) {
                            holder.editText.setText(value.substring(context.getString(R.string.other).length() + 3));
                        } else {
                            holder.editText.setText("");
                        }

                        holder.editText.setTag(null);
                    } else {
                        holder.editText.setVisibility(View.GONE);
                        holder.editText.setTag("tag");
                        holder.editText.setText("");
                        holder.editText.setTag(null);
                        for (int i = 0; i < dataArray.length; i++) {
                            if (value.equals(dataArray[i])) {
                                holder.spinner.setSelection(i, false);
                                break;
                            }
                        }
                    }


                } else {
                    holder.editText.setVisibility(View.GONE);
                }
            }
            cursor.close();

            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int j, long id) {

                    holder.editText.setTag("tag");
                    holder.editText.setText("");
                    holder.editText.setTag(null);
                    if (j == spinnerOptions.length - 1) {
                        holder.editText.setVisibility(View.VISIBLE);

                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, context.getString(R.string.other) + " - " + holder.editText.getText().toString());
                                }
                            };
                            thread.start();

                    } else {
                        holder.editText.setVisibility(View.GONE);

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

            holder.editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                    if (holder.editText.getTag() == null) {
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

            final ViewHolderCheckBox holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.cell_fragment_checkbox, parent, false);
                holder = new ViewHolderCheckBox();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                holder.cg = (LinearLayout) convertView.findViewById(R.id.checkGroup);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderCheckBox) convertView.getTag();
            }

            holder.textView.setText(items.get(position).title);

            final EditText editText = (EditText) convertView.findViewById(R.id.other);
            editText.setVisibility(View.GONE);

            holder.cg.removeAllViews();

            if (items.get(position).uiOptions.length > 1) {
                holder.cg.setOrientation(RadioGroup.VERTICAL);
            } else {
                holder.cg.setOrientation(RadioGroup.HORIZONTAL);
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
                cbNone.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_medium));
                cbNone.setText(items.get(position).extraOptions[0]);
            }
            final CheckBox cbNoneFinal = cbNone;

            CheckBox cbSecondNone = null;
            if (items.get(position).hasSecondNone){
                cbSecondNone = new CheckBox(context);
                cbSecondNone.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_medium));
                cbSecondNone.setText(items.get(position).extraOptions[1]);
            }
            final CheckBox cbSecondNoneFinal = cbSecondNone;


            CheckBox cbOther = null;
            if (items.get(position).hasOther){
                cbOther = new CheckBox(context);
                cbOther.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_medium));
                cbOther.setText(context.getString(R.string.other));
            }
            final CheckBox cbOtherFinal = cbOther;

            for (int i = 0; i < items.get(position).uiOptions.length; i++) {
                CheckBox cb = new CheckBox(context);
                cb.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_medium));
                cb.setText(items.get(position).uiOptions[i]);
                holder.cg.addView(cb);


                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            if (cbNoneFinal != null) {
                                cbNoneFinal.setChecked(false);
                            }

                            if (cbSecondNoneFinal != null) {
                                cbSecondNoneFinal.setChecked(false);
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
                                for (int i = 0; i < holder.cg.getChildCount(); i++) {
                                    CheckBox cb = (CheckBox) holder.cg.getChildAt(i);
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
                holder.cg.addView(cbNoneFinal);

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

            if (cbSecondNoneFinal != null){
                holder.cg.addView(cbSecondNoneFinal);

                cbSecondNoneFinal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                if (cbSecondNoneFinal.isChecked()) {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, cbSecondNoneFinal.getText().toString());
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
                holder.cg.addView(cbOtherFinal);

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
                            ((CheckBox) holder.cg.getChildAt(i)).setChecked(true);
                        } else {
                            ((CheckBox) holder.cg.getChildAt(i)).setChecked(false);
                        }
                    }
                    if (items.get(position).hasNone){
                        if (answer.equals(items.get(position).extraOptions[0])){
                            cbNoneFinal.setChecked(true);
                        } else {
                            cbNoneFinal.setChecked(false);
                        }
                    }

                    if (items.get(position).hasSecondNone){
                        if (answer.equals(items.get(position).extraOptions[1])){
                            cbSecondNoneFinal.setChecked(true);
                        } else {
                            cbSecondNoneFinal.setChecked(false);
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
                    for (int i = 0; i < holder.cg.getChildCount(); i++) {
                        CheckBox box = (CheckBox) holder.cg.getChildAt(i);
                        box.setChecked(false);
                    }
                }

            } else {
                for (int i = 0; i < holder.cg.getChildCount(); i++) {
                    CheckBox box = (CheckBox) holder.cg.getChildAt(i);
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

        } else if (i1 == FragmentItem.CellType.ED_EVENTS) {

            convertView = EDEvents.setupEventSection(context, parent, convertView, this);

        }



        return convertView;
    }


    private void setupFractureSiteClickListener(final CheckBox cb, final CheckBox cbLeft, final CheckBox cbRight, final CheckBox cbUnspecified, final String dbKey, final View view){

        loadFractureSiteCheckbox(cb, cbLeft, cbRight, cbUnspecified, dbKey, view);

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFractureSiteVisibility(cb, cbLeft, cbRight, cbUnspecified, ((CheckBox) v).isChecked(), view);
                writeFractureSiteVisibility(cb, cbLeft, cbRight, cbUnspecified, dbKey);
            }
        });

        if (cbLeft != null){

            cbLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cbUnspecified.setChecked(false);
                    writeFractureSiteVisibility(cb, cbLeft, cbRight, cbUnspecified, dbKey);
                }
            });
        }

        if (cbRight != null){

            cbRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cbUnspecified.setChecked(false);
                    writeFractureSiteVisibility(cb, cbLeft, cbRight, cbUnspecified, dbKey);
                }
            });
        }

        if (cbUnspecified != null){

            cbUnspecified.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cbLeft.setChecked(false);
                    cbRight.setChecked(false);
                    writeFractureSiteVisibility(cb, cbLeft, cbRight, cbUnspecified, dbKey);
                }
            });
        }
    }

    private void setFractureSiteVisibility(CheckBox cb, CheckBox cbLeft, CheckBox cbRight, CheckBox cbUnspecified, boolean isChecked, View view){
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
                cbUnspecified.setChecked(false);
            }
        }
    }

    private void writeFractureSiteVisibility(CheckBox cb, CheckBox cbLeft, CheckBox cbRight, CheckBox cbUnspecified, final String dbKey){
        String value = "";
        if (cb.isChecked()){
            if (cbLeft != null){

                if (cbUnspecified.isChecked()){
                    value = context.getString(R.string.unspecified);
                }

                if (cbLeft.isChecked()){
                    value = context.getString(R.string.left);
                }

                if (cbRight.isChecked()){
                    value = value + context.getString(R.string.right);
                }

                if (value.equals("")){
                    value = "Incomplete";
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


    private void loadFractureSiteCheckbox(final CheckBox cb, final CheckBox cbLeft, final CheckBox cbRight, final CheckBox cbUnspecified, final String dbKey, final View view){
        Cursor cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, dbKey);

        if (cursor.moveToFirst()){
            String checkValue = cursor.getString(0);
            if (checkValue != null  && !checkValue.equals("")){

                cb.setChecked(true);
                setFractureSiteVisibility(cb, cbLeft, cbRight, cbUnspecified, true, view);

                if (cbLeft != null){
                    if (checkValue.equals(context.getString(R.string.unspecified))){
                        cbLeft.setChecked(false);
                        cbRight.setChecked(false);
                        cbUnspecified.setChecked(true);
                    } else {
                        cbUnspecified.setChecked(false);

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
                setFractureSiteVisibility(cb, cbLeft, cbRight, cbUnspecified, false, view);

                if (cbLeft != null){
                    cbLeft.setChecked(false);
                    cbRight.setChecked(false);
                    cbUnspecified.setChecked(false);
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


    static class ViewHolderNumeric{
        private TextView textView;
        private EditText editText;
    }

    static class ViewHolderRadio{
        private TextView textView;
        private RadioGroup rg;
    }

    static class ViewHolderRadioWithSpecify{
        private TextView textView;
        private EditText editText;
        private RadioGroup rg;
    }

    static class ViewHolderReturnToED{
        private TextView textView;
        private EditText editText;
        private RadioGroup rg;
        private LinearLayout reasonLayout;
        private CheckBox cbUncontrolledPain;
    }

    static class ViewHolderDatePicker{
        private TextView textView;
        private Button button;
        private Button clearButton;
        private CheckBox noneBox;
    }

    static class ViewHolderTimePicker{
        private TextView textView;
        private Button button;
        private Button clearButton;
        private CheckBox noneBox;
    }

    static class ViewHolderFractureSite{
        private CheckBox cbFoot;
        private CheckBox cbAnkle;
        private CheckBox cbTibia;
        private CheckBox cbFemur;
        private CheckBox cbHip;
        private CheckBox cbPelvis;
        private CheckBox cbVertebra;
        private CheckBox cbRib;
        private CheckBox cbHumerus;
        private CheckBox cbForearm;
        private CheckBox cbWrist;
        private CheckBox cbHead;
        private CheckBox cbToes;
        private CheckBox cbFingers;
        private CheckBox cbHand;

        private CheckBox cbFootLeft;
        private CheckBox cbFootRight;
        private CheckBox cbFootUnspecified;
        private CheckBox cbAnkleLeft;
        private CheckBox cbAnkleRight;
        private CheckBox cbAnkleUnspecified;
        private CheckBox cbTibiaLeft;
        private CheckBox cbTibiaRight;
        private CheckBox cbTibiaUnspecified;
        private CheckBox cbFemurLeft;
        private CheckBox cbFemurRight;
        private CheckBox cbFemurUnspecified;
        private CheckBox cbHipLeft;
        private CheckBox cbHipRight;
        private CheckBox cbHipUnspecified;
        private CheckBox cbRibLeft;
        private CheckBox cbRibRight;
        private CheckBox cbRibUnspecified;
        private CheckBox cbHumerusLeft;
        private CheckBox cbHumerusRight;
        private CheckBox cbHumerusUnspecified;
        private CheckBox cbForearmLeft;
        private CheckBox cbForearmRight;
        private CheckBox cbForearmUnspecified;
        private CheckBox cbWristLeft;
        private CheckBox cbWristRight;
        private CheckBox cbWristUnspecified;
        private CheckBox cbHandLeft;
        private CheckBox cbHandRight;
        private CheckBox cbHandUnspecified;

        private EditText editOther;

    }

    static class ViewHolderText{
        private TextView textView;
        private EditText editText;
    }

    static class ViewHolderSpinner{
        private TextView textView;
        private Spinner spinner;

    }

    static class ViewHolderSpinnerWithOther{
        private TextView textView;
        private EditText editText;
        private Spinner spinner;
    }

    static class ViewHolderCheckBox{
        private TextView textView;
        private EditText editText;
        private LinearLayout cg;
    }

}


