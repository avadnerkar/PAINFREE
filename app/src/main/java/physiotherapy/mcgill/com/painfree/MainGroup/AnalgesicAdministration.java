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
            DBAdapter.KEY_ANALGESIC_ADMIN_1_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_1_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_1_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_1_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_1_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_1_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_2_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_2_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_2_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_2_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_2_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_2_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_3_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_3_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_3_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_3_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_3_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_3_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_4_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_4_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_4_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_4_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_4_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_4_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_5_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_5_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_5_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_5_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_5_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_5_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_6_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_6_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_6_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_6_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_6_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_6_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_7_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_7_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_7_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_7_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_7_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_7_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_8_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_8_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_8_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_8_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_8_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_8_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_9_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_9_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_9_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_9_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_9_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_9_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_9_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_10_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_10_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_10_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_10_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_10_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_10_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_10_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_11_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_11_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_11_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_11_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_11_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_11_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_11_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_12_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_12_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_12_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_12_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_12_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_12_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_12_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_13_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_13_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_13_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_13_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_13_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_13_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_13_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_14_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_14_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_14_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_14_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_14_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_14_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_14_REFUSAL,
            DBAdapter.KEY_ANALGESIC_ADMIN_15_DATE, DBAdapter.KEY_ANALGESIC_ADMIN_15_TIME, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_ID, DBAdapter.KEY_ANALGESIC_ADMIN_15_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_15_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_15_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_15_ORDER, DBAdapter.KEY_ANALGESIC_ADMIN_15_REFUSAL};

    public static final boolean[] mandatoryKeys = new boolean[]{
            true, true, false, false, false, false, false, false};

    public static final String[][] adminPresKeys = new String[][]{
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_1_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_2_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_3_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_4_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_5_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_6_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_7_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_8_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_9_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_10_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_11_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_12_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_13_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_14_PRES_OPIOID_FREQ},
            new String[]{DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_ACETAMINOPHEN, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_ACETAMINOPHEN_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_ACETAMINOPHEN_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_ACETAMINOPHEN_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_NSAIDS, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_NSAIDS_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_NSAIDS_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_NSAIDS_FREQ, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_OPIOID, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_OPIOID_DOSE, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_OPIOID_ROUTE, DBAdapter.KEY_ANALGESIC_ADMIN_15_PRES_OPIOID_FREQ}
    };

    public static final int numFields = 8;

    public static View setupAnalgesicAdministrationSection(final Context context, ViewGroup parent, final ArrayAdapter adapter, final int index, final int globalIndex, final EDEvents.MinusHandler handler){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int mMonth;
        Calendar mcurrentDate;
        int mDay;
        int mYear;

        final String[] spinnerOrderOptions = new String[]{context.getString(R.string.written), context.getString(R.string.verbal)};

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
                final ArrayList<String[]> optionIds = new ArrayList<>();
                options.add("");
                optionIds.add(null);
                Cursor cursor1 = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, AnalgesicPrescription.idKeys);
                if (cursor1.moveToFirst()) {
                    for (int i=0; i<AnalgesicPrescription.idKeys.length; i++) {
                        String option = cursor1.getString(i);
                        if (option != null && option.length() > 0) {
                            options.add(option);
                            optionIds.add(AnalgesicPrescription.idArray[i]);
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
                    MainActivity.myDb.updateFields(MainActivity.currentPatientId, adminPresKeys[index], null);
                }


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 3], spinnerOptions[position]);
                        if (optionIds.get(position) != null) {
                            Cursor cursorPres = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, optionIds.get(position));
                            if (cursorPres.moveToFirst()) {
                                ArrayList<String> presFields = new ArrayList<>();
                                for (int m=0; m<cursorPres.getColumnCount(); m++) {
                                    presFields.add(cursorPres.getString(m));
                                }

                                MainActivity.myDb.updateFields(MainActivity.currentPatientId, adminPresKeys[index], presFields.toArray(new String[presFields.size()]));
                            }
                            cursorPres.close();
                        } else {
                            MainActivity.myDb.updateFields(MainActivity.currentPatientId, adminPresKeys[index], null);
                        }

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
                //Analgesic type

                CheckBox[] analgesicCheckboxes = new CheckBox[]{(CheckBox) assessmentView.findViewById(R.id.cb_acetaminophen), (CheckBox) assessmentView.findViewById(R.id.cb_nsaids), (CheckBox) assessmentView.findViewById(R.id.cb_opioid)};

                for (int j=0; j<3; j++){
                    final int J = j;

                    //Checkbox
                    String analgesicValue = cursor.getString(index*numFields + 4 + j);
                    if (analgesicValue != null && analgesicValue.equals(context.getString(R.string.yes))){
                        analgesicCheckboxes[j].setChecked(true);
                    } else {
                        analgesicCheckboxes[j].setChecked(false);
                    }


                    analgesicCheckboxes[j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (((CheckBox)v).isChecked()){
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index*numFields + 4 + J], context.getString(R.string.yes));
                            } else {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index*numFields + 4 + J], null);
                            }
                        }
                    });




                }
            }



            {
                //Spinner - Order
                final Spinner order = (Spinner) assessmentView.findViewById(R.id.spinner_order);
                TextView textView = (TextView) assessmentView.findViewById(R.id.text_spinner_order);
                ArrayAdapter<String> spinnerOrderAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerOrderOptions);
                spinnerOrderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                order.setAdapter(spinnerOrderAdapter);

                order.setSelection(0);
                String value = cursor.getString(index * numFields + 7);
                if (value != null && !value.equals("")) {
                    for (int k = 0; k < spinnerOrderOptions.length; k++) {
                        if (value.equals(spinnerOrderOptions[k])) {
                            order.setSelection(k);
                            break;
                        }
                    }
                } else {
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 7], spinnerOrderOptions[0]);
                }


                order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 7], spinnerOrderOptions[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        order.performClick();
                    }
                });
            }


            {
                //Spinner - refusal
                final String[] spinnerRefusalOptions = new String[]{"", context.getString(R.string.refusal_of_analgesia), context.getString(R.string.contraindicated)};
                final Spinner refusal = (Spinner) assessmentView.findViewById(R.id.spinner_refusal);
                TextView textView = (TextView) assessmentView.findViewById(R.id.text_spinner_refusal);
                ArrayAdapter<String> spinnerRefusalAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerRefusalOptions);
                spinnerRefusalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                refusal.setAdapter(spinnerRefusalAdapter);

                refusal.setSelection(0);
                String value = cursor.getString(index * numFields + 8);
                if (value != null && !value.equals("")) {
                    for (int k = 0; k < spinnerRefusalOptions.length; k++) {
                        if (value.equals(spinnerRefusalOptions[k])) {
                            refusal.setSelection(k);
                            break;
                        }
                    }
                }


                refusal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, keys[index * numFields + 8], spinnerRefusalOptions[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refusal.performClick();
                    }
                });
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
