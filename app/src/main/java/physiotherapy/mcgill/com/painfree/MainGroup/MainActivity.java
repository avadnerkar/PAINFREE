package physiotherapy.mcgill.com.painfree.MainGroup;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import physiotherapy.mcgill.com.painfree.Dialogs.DialogEditText;
import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.ActivityIndicator;
import physiotherapy.mcgill.com.painfree.Utilities.AppUtils;
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
    public static long currentPatientId;
    public static Context context;
    public static ActionBar actionBar;
    public static final String PREFS_NAME = "PAINFREE_PREFS";
    public static String deviceID;
    public static String KEY_DEVICE_ID = "device_ID";

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
        mViewPager.setOffscreenPageLimit(7);

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

        final SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        deviceID = sharedPreferences.getString(KEY_DEVICE_ID, null);

        if (deviceID == null){
            AppUtils.showEditTextDialog(getString(R.string.device_id_message), context, new DialogEditText.ClickHandler() {
                @Override
                public void onClick(String text) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_DEVICE_ID, text);
                    deviceID = text;
                    editor.apply();
                }
            });
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
            menu.findItem(R.id.clear).setVisible(false);
        }
        else{
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


        if (item.getItemId() == R.id.action_export_csv) {
            exportRunnable.run();
            return true;
        }


        if (id == R.id.patient_list) {

            clearPatientSelection();
            Intent intent = new Intent(this, PatientListActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void clearPatientSelection(){
        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        currentPatientId = -1;
        FragmentA.setFragmentVisibility();
        FragmentB.setFragmentVisibility();
        FragmentC.setFragmentVisibility();
        FragmentD.setFragmentVisibility();
        FragmentE.setFragmentVisibility();
        FragmentF.setFragmentVisibility();
        FragmentG.setFragmentVisibility();

        invalidateOptionsMenu();

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
                case 0: return FragmentA.newInstance();
                case 1: return FragmentB.newInstance();
                case 2: return FragmentC.newInstance();
                case 3: return FragmentD.newInstance();
                case 4: return FragmentE.newInstance();
                case 5: return FragmentF.newInstance();
                case 6: return FragmentG.newInstance();
                default: return FragmentA.newInstance();
            }


        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 7;
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
                case 6:
                    return getString(R.string.title_sectionG).toUpperCase(l);
            }
            return null;
        }
    }


    Runnable newPatientRunnable = new Runnable(){
        public void run(){
            clearPatientSelection();

            AppUtils.showListDialog(getString(R.string.period_of_extraction), new String[]{getString(R.string.pre1), getString(R.string.pre2), getString(R.string.post1), getString(R.string.post2)}, context, new AppUtils.ListHandler() {
                @Override
                public void onClick(final String text) {
                    long id = MainActivity.myDb.insertNewRow(text);
                    actionBar.setTitle(deviceID + "-" + id);
                    MainActivity.currentPatientId = id;
                    invalidateOptionsMenu();
                    FragmentA.setFragmentVisibility();
                    FragmentB.setFragmentVisibility();
                    FragmentC.setFragmentVisibility();
                    FragmentD.setFragmentVisibility();
                    FragmentE.setFragmentVisibility();
                    FragmentF.setFragmentVisibility();
                    FragmentG.setFragmentVisibility();
                }
            });
        }
    };


    Runnable exportRunnable = new Runnable(){
        public void run(){
            exportToCSV();
        }
    };


    public void exportToCSV() {

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

                                if (c.getColumnName(i).equals(DBAdapter.KEY_SUBJECTID)){
                                    list.add("Confidential");
                                } else {
                                    list.add(c.getString(i));
                                }

                            }
                            String[] arrStr = list.toArray(new String[list.size()]);
                            writer.writeNext(arrStr);
                        } while(c.moveToNext());
                    }

                    writer.close();
                    c.close();

                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ActivityIndicator.dismissProgressDialog();
                            CharSequence toastMessage = "Export successful";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, toastMessage, duration);
                            toast.show();
                        }
                    });

                    File idFilename = new File(path, "/PainFree_ids.csv");
                    CSVWriter idWriter = new CSVWriter(new FileWriter(idFilename), '\t');
                    c = myDb.getAllRowDataWithKeys(new String[]{DBAdapter.KEY_UNIQUEID, DBAdapter.KEY_SUBJECTID});

                    idWriter.writeNext(new String[]{"sep=\t"});
                    idWriter.writeNext(c.getColumnNames());

                    if (c.moveToFirst()){
                        do {
                            List<String> list = new ArrayList<>();
                            list.add(c.getString(0));
                            list.add(c.getString(1));
                            String[] arrStr = list.toArray(new String[list.size()]);
                            idWriter.writeNext(arrStr);

                        } while (c.moveToNext());
                    }

                    c.close();
                    idWriter.close();




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


}
