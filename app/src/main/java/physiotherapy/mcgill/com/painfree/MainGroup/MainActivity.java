package physiotherapy.mcgill.com.painfree.MainGroup;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.ActivityIndicator;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    public static DBAdapter myDb;
    public static int currentPatientId;
    public static String currentSubjectId;
    public String[] patientListString;
    public static Context context;
    public static ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openDB();
        currentPatientId = -1;

        context = this;

        // Set up the action bar.
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Crimson)));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Crimson)));

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }



    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }

    private void closeDB() {
        myDb.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();



        if (currentPatientId == -1){

        } else {
            ListView layoutA = (ListView) findViewById(R.id.list_a);
            layoutA.setVisibility(View.VISIBLE);
            ListView layoutB = (ListView) findViewById(R.id.list_b);
            layoutB.setVisibility(View.VISIBLE);
            ListView layoutC = (ListView) findViewById(R.id.list_c);
            layoutC.setVisibility(View.VISIBLE);
            ListView layoutD = (ListView) findViewById(R.id.list_d);
            layoutD.setVisibility(View.VISIBLE);
            ListView layoutE = (ListView) findViewById(R.id.list_e);
            layoutE.setVisibility(View.VISIBLE);
            ListView layoutF = (ListView) findViewById(R.id.list_f);
            layoutF.setVisibility(View.VISIBLE);

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(currentPatientId==-1){
            menu.findItem(R.id.action_update_patient).setVisible(false);
            menu.findItem(R.id.clear).setVisible(false);
        }
        else{
            menu.findItem(R.id.action_update_patient).setVisible(true);
            menu.findItem(R.id.clear).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.clear_all) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete all patients?");
            builder.setMessage("This cannot be undone");
            builder.setCancelable(true);

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // if this button is clicked, close
                    // current activity

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Delete...");
                    alert.setMessage("Enter Pin :");

                    // Set an EditText view to get user input
                    final EditText input = new EditText(MainActivity.this);
                    alert.setView(input);

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String value = input.getText().toString();
                            if (value.equals("1379")) {
                                myDb.deleteAllRowData();
                                clearPatientSelection();
                                Context context = getApplicationContext();
                                CharSequence toastMessage = "All patients deleted";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, toastMessage, duration);
                                toast.show();
                            } else {
                                Context context = getApplicationContext();
                                CharSequence toastMessage = "Incorrect PIN";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, toastMessage, duration);
                                toast.show();
                            }
                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                        }
                    });
                    alert.show();

                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // if this button is clicked, just close
                    // the dialog box and do nothing
                    dialog.cancel();
                }
            });

            // create alert dialog
            AlertDialog alertDialog = builder.create();

            // show it
            alertDialog.show();

            return true;
        }

        if (id == R.id.clear) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete current patient?");
            builder.setMessage("This cannot be undone");
            builder.setCancelable(true);

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // if this button is clicked, close
                    // current activity
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Delete...");
                    alert.setMessage("Enter Pin :");

                    // Set an EditText view to get user input
                    final EditText input = new EditText(MainActivity.this);
                    alert.setView(input);

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String value = input.getText().toString();
                            if (value.equals("1379")) {
                                myDb.deleteRowData(currentPatientId);
                                clearPatientSelection();
                                Context context = getApplicationContext();
                                CharSequence toastMessage = "Patient deleted";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, toastMessage, duration);
                                toast.show();
                            } else {
                                Context context = getApplicationContext();
                                CharSequence toastMessage = "Incorrect PIN";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, toastMessage, duration);
                                toast.show();
                            }
                            return;
                        }
                    });
                    alert.show();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // if this button is clicked, just close
                    // the dialog box and do nothing
                    dialog.cancel();
                }
            });

            // create alert dialog
            AlertDialog alertDialog = builder.create();

            // show it
            alertDialog.show();


            return true;
        }

        if (id == R.id.patient_new) {
            newPatientRunnable.run();
            return true;
        }


        if (id == R.id.action_update_patient) {
            updatePatientRunnable.run();
            return true;
        }


        if (item.getItemId() == R.id.action_export_csv) {
            exportRunnable.run();
            return true;
        }


        if (id == R.id.patient_list) {

            //TODO: Select patient activity
            //Intent intent = new Intent(this, SelectPatientActivity.class);
            //startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void clearPatientSelection(){
        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        currentPatientId = -1;
        ListView layoutA = (ListView) findViewById(R.id.list_a);
        layoutA.setVisibility(View.INVISIBLE);
        ListView layoutB = (ListView) findViewById(R.id.list_b);
        layoutB.setVisibility(View.INVISIBLE);
        ListView layoutC = (ListView) findViewById(R.id.list_c);
        layoutC.setVisibility(View.INVISIBLE);
        ListView layoutD = (ListView) findViewById(R.id.list_d);
        layoutD.setVisibility(View.INVISIBLE);
        ListView layoutE = (ListView) findViewById(R.id.list_e);
        layoutE.setVisibility(View.INVISIBLE);
        ListView layoutF = (ListView) findViewById(R.id.list_f);
        layoutF.setVisibility(View.INVISIBLE);

        invalidateOptionsMenu();

        currentSubjectId = null;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch(position){
                case 0: return FragmentA.newInstance(position + 1);
                case 1: return FragmentB.newInstance(position + 1);
                case 2: return FragmentC.newInstance(position + 1);
                case 3: return FragmentD.newInstance(position + 1);
                case 4: return FragmentE.newInstance(position + 1);
                case 5: return FragmentF.newInstance(position + 1);
                default: return FragmentA.newInstance(position + 1);
            }


        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_sectionA).toUpperCase(l);
                case 1:
                    return getString(R.string.title_sectionB).toUpperCase(l);
                case 2:
                    return getString(R.string.title_sectionC).toUpperCase(l);
                case 3:
                    return getString(R.string.title_sectionD).toUpperCase(l);
                case 4:
                    return getString(R.string.title_sectionE).toUpperCase(l);
                case 5:
                    return getString(R.string.title_sectionF).toUpperCase(l);
            }
            return null;
        }
    }


    Runnable newPatientRunnable = new Runnable(){
        public void run(){
            clearPatientSelection();

            //TODO: Add New Patient activity
            //Intent intent = new Intent(MainActivity.this, PatientFormActivity.class);
            //startActivity(intent);
        }
    };


    Runnable updatePatientRunnable = new Runnable(){
        public void run(){

            //TODO: Add New Patient activity
            //Intent intent = new Intent(MainActivity.this, PatientFormActivity.class);
            //startActivity(intent);
        }
    };


    Runnable exportRunnable = new Runnable(){
        public void run(){
            exportToCSV();
        }
    };


    public void exportToCSV() {

        verifyStoragePermissions(this);
        ActivityIndicator.showProgressDialog(this);

        Thread thread = new Thread(){
            @Override
            public void run() {
                File path = Environment.getExternalStorageDirectory();
                File filename = new File(path, "/PainFree_database.csv");

                try {
                    CSVWriter writer = new CSVWriter(new FileWriter(filename), '\t');
                    writer.writeNext(new String[]{"sep=\t"});


                    Cursor c = myDb.getAllRowDataWithKeys(DBAdapter.dataMap.toArray(new String[DBAdapter.dataMap.size()]));
                    writer.writeNext(c.getColumnNames());

                    if (c.moveToFirst()){
                        do {
                            List<String> list = new ArrayList<>();
                            for (int i=0; i<c.getColumnCount(); i++){
                                list.add(c.getString(i));
                            }
                            String[] arrStr = list.toArray(new String[list.size()]);
                            writer.writeNext(arrStr);
                        } while(c.moveToNext());
                    }

                    writer.close();
                    c.close();


                } catch (final IOException e){

                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ActivityIndicator.dismissProgressDialog();
                            System.err.println("Caught IOException: " + e.getMessage());
                            CharSequence toastMessage = "Export failed";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, toastMessage, duration);
                            toast.show();
                        }
                    });

                }

            }
        };
        thread.start();


    }



    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
    }

}
