package physiotherapy.mcgill.com.painfree.MainGroup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.AppUtils;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;

public class NewPatientActivity extends AppCompatActivity {

    private Context context;
    private String dateString;
    private String arrivalDateString;
    private String arrivalTimeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);
        setTitle(getString(R.string.patient_new));
        context = this;

        setupButtons();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_patient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.patient_save) {

            EditText editSubjectID = (EditText) findViewById(R.id.edit_subject_id);
            EditText editSite = (EditText) findViewById(R.id.edit_site);
            EditText editCompletedBy = (EditText) findViewById(R.id.edit_completed_by);
            String subjectID = editSubjectID.getText().toString();
            String site = editSite.getText().toString();
            String completedBy = editCompletedBy.getText().toString();

            if (subjectID.equals("")){
                AppUtils.showSimpleDialog(getString(R.string.error_id), context);
                return true;
            }

            MainActivity.currentPatientId = MainActivity.myDb.insertNewRow(subjectID, site, completedBy, dateString, arrivalDateString, arrivalTimeString);

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupButtons(){
        final Button dateButton = (Button) findViewById(R.id.select_date);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDatePickerDialog(dateButton);
            }
        });

        final Button arrivalDateButton = (Button) findViewById(R.id.select_arrival_date);
        arrivalDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDatePickerDialog(arrivalDateButton);
            }
        });

        final Button arrivalTimeButton = (Button) findViewById(R.id.select_arrival_time);
        arrivalTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTimePickerDialog(arrivalTimeButton);
            }
        });
    }

    private void launchDatePickerDialog(final Button button){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                // Display Selected date in textbox
                final String value = year + "-" + String.format("%02d", monthOfYear+1) + "-" + String.format("%02d", dayOfMonth);
                button.setText(value);
                switch (button.getId()){
                    case (R.id.select_date):
                        dateString = value;
                        break;
                    case (R.id.select_arrival_date):
                        arrivalDateString = value;
                        break;
                }
            }
        }, mYear, mMonth, mDay);
        dpd.show();
    }


    private void launchTimePickerDialog(final Button button){

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String value = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
                button.setText(value);
                arrivalTimeString = value;
            }
        }, hour, minute, false);
        tpd.show();

    }


}
