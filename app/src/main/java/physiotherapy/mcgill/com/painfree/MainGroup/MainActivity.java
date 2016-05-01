package physiotherapy.mcgill.com.painfree.MainGroup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import physiotherapy.mcgill.com.painfree.Dialogs.DialogEditText;
import physiotherapy.mcgill.com.painfree.Dialogs.DialogTwoButton;
import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.ActivityIndicator;
import physiotherapy.mcgill.com.painfree.Utilities.AppUtils;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;

public class MainActivity extends AppCompatActivity {

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
    public static ViewPager mViewPager;
    public static DBAdapter myDb;
    public static long currentPatientId;
    public static Context context;
    public static Toolbar actionBar;
    public static TextView actionBarTitle;
    public static final String PREFS_NAME = "PAINFREE_PREFS";
    public static String deviceID;
    public static String KEY_DEVICE_ID = "device_ID";
    public static TabLayout tabLayout;
    public static boolean programmaticallySelectTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openDB();
        currentPatientId = -1;

        context = this;

        programmaticallySelectTab = false;

        // Set up the action bar.
        actionBar = (Toolbar) findViewById(R.id.toolbar);
        actionBarTitle = (TextView) actionBar.findViewById(R.id.toolbar_title);
        setSupportActionBar(actionBar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(7);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.GONE);

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.

            tabLayout.addTab(tabLayout.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)));
        }

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        assert tabLayout != null;
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (!programmaticallySelectTab) {
                    checkMandatoryFields(tab.getPosition());
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
        myDb = new DBAdapter(this.getApplicationContext());
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

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


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
        actionBarTitle.setText("");
        currentPatientId = -1;
        tabLayout.setVisibility(View.GONE);
        FragmentA.setFragmentVisibility();
        FragmentB.setFragmentVisibility();
        FragmentC.setFragmentVisibility();
        FragmentD.setFragmentVisibility();
        FragmentE.setFragmentVisibility();
        FragmentF.setFragmentVisibility();
        FragmentG.setFragmentVisibility();

        invalidateOptionsMenu();

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
            // Show 7 total pages.
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

    public boolean checkMandatoryFields(final int tabPosition){
        ArrayList<String> unfilledMandatoryFields = new ArrayList<>();
        switch (tabPosition){
            case 0:
                unfilledMandatoryFields = FragmentA.unfilledMandatoryFields();
                break;
            case 1:
                unfilledMandatoryFields = FragmentB.unfilledMandatoryFields();
                break;
            case 2:
                unfilledMandatoryFields = FragmentC.unfilledMandatoryFields();
                break;
            case 4:
                unfilledMandatoryFields = FragmentE.unfilledMandatoryFields();
                break;
            default:
                break;
        }

        if (unfilledMandatoryFields.size() == 2 && unfilledMandatoryFields.get(0).equals("EXCLUSION")){
            AppUtils.showDefaultAlertDialog(getString(R.string.notice), unfilledMandatoryFields.get(1), context, new AppUtils.DefaultAlertHandler() {
                @Override
                public void onClick() {

                }
            });
            return false;
        } else {
            String message = "";
            for (String field : unfilledMandatoryFields){
                message = message + field + "\n";
            }

            if (!message.equals("")){

                AppUtils.showDefaultAlertDialog(getString(R.string.mandatory_fields_title), message, context, new AppUtils.DefaultAlertHandler() {
                    @Override
                    public void onClick() {
                        programmaticallySelectTab = true;
                        mViewPager.setCurrentItem(tabPosition);
                        programmaticallySelectTab = false;
                    }
                });
                return true;
            } else {
                return false;
            }
        }


    }


    Runnable newPatientRunnable = new Runnable(){
        public void run(){
            clearPatientSelection();

            long id = MainActivity.myDb.insertNewRow();
            actionBarTitle.setText(deviceID + "-" + id);
            MainActivity.currentPatientId = id;
            tabLayout.setVisibility(View.VISIBLE);
            programmaticallySelectTab = true;
            mViewPager.setCurrentItem(0);
            programmaticallySelectTab = false;
            invalidateOptionsMenu();
            FragmentA.setFragmentVisibility();
            FragmentB.setFragmentVisibility();
            FragmentC.setFragmentVisibility();
            FragmentD.setFragmentVisibility();
            FragmentE.setFragmentVisibility();
            FragmentF.setFragmentVisibility();
            FragmentG.setFragmentVisibility();
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


                                if (c.getColumnName(i).equals("Medical record number")){
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
                    c = myDb.getAllRowDataWithKeys(new String[]{DBAdapter.KEY_UNIQUEID, DBAdapter.KEY_MEDICALRECORDNUMBER});

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
