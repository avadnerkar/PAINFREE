package physiotherapy.mcgill.com.painfree.MainGroup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

import physiotherapy.mcgill.com.painfree.R;
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

        Button arrivalTimeButton = (Button) findViewById(R.id.select_arrival_time);
        arrivalTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                final String value = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                button.setText(value);
                dateString = value;
            }
        }, mYear, mMonth, mDay);
        dpd.show();
    }

    private void launchArrivalDatePickerDialog(final Button button){
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
                final String value = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                button.setText(value);
                arrivalDateString = value;
            }
        }, mYear, mMonth, mDay);
        dpd.show();
    }


}
