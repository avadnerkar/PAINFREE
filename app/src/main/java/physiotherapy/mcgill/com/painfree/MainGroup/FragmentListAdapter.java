package physiotherapy.mcgill.com.painfree.MainGroup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

        View rowView = null;
        TextView textView;
        Cursor cursor;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (items.get(position).cellType){
            case NUMERIC:
                rowView = inflater.inflate(R.layout.cell_fragment_numeric, parent, false);
                textView = (TextView) rowView.findViewById(R.id.title);
                textView.setText(items.get(position).title);
                EditText editText = (EditText) rowView.findViewById(R.id.edit_numeric);
                editText.setHint(items.get(position).uiOptions[0]);

                cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

                if (cursor.moveToFirst()){
                    String text = cursor.getString(cursor.getColumnIndex(items.get(position).dbKey));
                    if (text !=null){
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
                        Thread thread = new Thread(){
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

                break;
            case RADIO:
                rowView = inflater.inflate(R.layout.cell_fragment_radio, parent, false);
                textView = (TextView) rowView.findViewById(R.id.title);
                textView.setText(items.get(position).title);

                final RadioGroup rg = (RadioGroup) rowView.findViewById(R.id.rg);

                if (items.get(position).databaseOptions == null){
                    items.get(position).databaseOptions = items.get(position).uiOptions;
                }

                if (items.get(position).uiOptions.length >2 && items.get(position).uiOptions.length < 7){
                    rg.setOrientation(RadioGroup.VERTICAL);
                } else {
                    rg.setOrientation(RadioGroup.HORIZONTAL);
                }

                for (int i=0; i<items.get(position).uiOptions.length; i++){
                    RadioButton rb = new RadioButton(context);
                    rb.setText(items.get(position).uiOptions[i]);
                    rg.addView(rb);

                    final int index = i;
                    rb.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(final View view) {
                            Thread thread = new Thread(){
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

                if (cursor.moveToFirst()){
                    String radioValue = cursor.getString(cursor.getColumnIndex(items.get(position).dbKey));
                    if (radioValue != null && !radioValue.equals("")){
                        for (int i=0; i<items.get(position).databaseOptions.length; i++){
                            String rbString = items.get(position).databaseOptions[i];
                            if (rbString.equals(radioValue)){
                                ((RadioButton)rg.getChildAt(i)).setChecked(true);
                            }
                        }
                    } else {
                        rg.clearCheck();
                    }

                } else {
                    rg.clearCheck();
                }

                cursor.close();
                break;

            case DATEPICKER:

                rowView = inflater.inflate(R.layout.cell_fragment_datepicker, parent, false);
                textView = (TextView) rowView.findViewById(R.id.title);
                textView.setText(items.get(position).title);

                final Button button = (Button) rowView.findViewById(R.id.button);
                button.setText(context.getString(R.string.select_date));

                cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);
                Calendar mcurrentDate=Calendar.getInstance();
                int mYear=mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);
                if (cursor.moveToFirst()){
                    String dateString = cursor.getString(0);
                    if (dateString !=null && !dateString.equals("")){
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = format.parse(dateString);
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            mYear = cal.get(Calendar.YEAR);
                            mMonth = cal.get(Calendar.MONTH);
                            mDay = cal.get(Calendar.DAY_OF_MONTH);

                            button.setText(mYear + "-" + String.format("%02d", mMonth+1) + "-" + String.format("%02d", mDay));
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

                final int year = mYear;
                final int month = mMonth;
                final int day = mDay;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        DatePickerDialog mDatePicker=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                                String text = selectedyear + "-" + String.format("%02d", selectedmonth+1) + "-" + String.format("%02d", selectedday);
                                button.setText(text);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, text);
                            }
                        },year, month, day);
                        mDatePicker.show();  }
                });
                break;

            case FRACTURESITE:
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

                break;
        }

        return rowView;
    }


    private void setupFractureSiteClickListener(final CheckBox cb, final CheckBox cbLeft, final CheckBox cbRight, final String dbKey, final View view){

        loadFractureSiteCheckbox(cb, cbLeft, cbRight, dbKey, view);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setFractureSiteVisibility(cb, cbLeft, cbRight, isChecked, view);
                writeFractureSiteVisibility(cb, cbLeft, cbRight, dbKey);
            }
        });

        if (cbLeft != null){
            cbLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    writeFractureSiteVisibility(cb, cbLeft, cbRight, dbKey);
                }
            });
        }

        if (cbRight != null){
            cbRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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
    }


}
