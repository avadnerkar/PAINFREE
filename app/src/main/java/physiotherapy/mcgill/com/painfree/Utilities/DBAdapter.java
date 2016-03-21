package physiotherapy.mcgill.com.painfree.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import physiotherapy.mcgill.com.painfree.MainGroup.MainActivity;

/**
 * Created by Abhishek Vadnerkar on 16-02-20.
 */
public class DBAdapter {

    /////////////////////////////////////////////////////////////////////
    //	Constants & Data
    /////////////////////////////////////////////////////////////////////
    // For logging:
    private static final String TAG = "DBAdapter";



    public static final String KEY_ROWID = "_id";
    public static final String KEY_UNIQUEID = "[Device Specific ID]";
    public static final String KEY_EXTRACTIONPERIOD = "[Period of extraction]";
    public static final String KEY_SUBJECTID = "[Subject ID]";
    public static final String KEY_SITE = "[Site]";
    public static final String KEY_COMPLETED_BY = "[Completed by]";
    public static final String KEY_DATE = "[Date]";
    public static final String KEY_ARRIVALDATE = "[Arrival date]";
    public static final String KEY_ARRIVALTIME = "[Arrival time]";

    public static final String KEY_DATEOFBIRTH = "[Date of birth]";
    public static final String KEY_SEX = "[Sex]";

    public static final String KEY_FRACTURESITE_FOOT = "[Foot]";
    public static final String KEY_FRACTURESITE_ANKLE = "[Ankle]";
    public static final String KEY_FRACTURESITE_TIBIA = "[Tibia or fibula]";
    public static final String KEY_FRACTURESITE_FEMUR = "[Femur]";
    public static final String KEY_FRACTURESITE_HIP = "[Hip]";
    public static final String KEY_FRACTURESITE_PELVIS = "[Pelvis]";
    public static final String KEY_FRACTURESITE_VERTEBRA = "[Vertebra]";
    public static final String KEY_FRACTURESITE_RIB = "[Rib]";
    public static final String KEY_FRACTURESITE_HUMERUS = "[Humerus]";
    public static final String KEY_FRACTURESITE_FOREARM = "[Forearm]";
    public static final String KEY_FRACTURESITE_WRIST = "[Wrist]";

    public static final String KEY_INJURY_MECHANISM = "[Injury mechanism]";

    public static final String KEY_TRIAGE_DATE = "[Triage date]";
    public static final String KEY_TRIAGE_TIME = "[Triage time]";
    public static final String KEY_CTAS = "[CTAS priority]";
    public static final String KEY_GLASGOW = "[Glasgow scale]";
    public static final String KEY_PAINSCALE = "[Pain scale score]";
    public static final String KEY_COLLECTIVEORDER = "[Collective order]";
    public static final String KEY_HISTORYOFDEMENTIA = "[History of dementia]";
    public static final String KEY_ALTEREDCOGNITION = "[Altered cognition]";

    public static final String KEY_PHYSICIAN_EXAMINATION_DATE = "[Physician examination date]";
    public static final String KEY_PHYSICIAN_EXAMINATION_TIME = "[Physician examination time]";

    public static final String KEY_PAIN_ASSESSMENT_NUM = "[Number of pain assessments]";
    public static final String KEY_PAIN_ASSESSMENT_1_DATE = "[Pain assessment 1 date]";
    public static final String KEY_PAIN_ASSESSMENT_1_TIME = "[Pain assessment 1 time]";
    public static final String KEY_PAIN_ASSESSMENT_1_SCORE = "[Pain assessment 1 score]";
    public static final String KEY_PAIN_ASSESSMENT_2_DATE = "[Pain assessment 2 date]";
    public static final String KEY_PAIN_ASSESSMENT_2_TIME = "[Pain assessment 2 time]";
    public static final String KEY_PAIN_ASSESSMENT_2_SCORE = "[Pain assessment 2 score]";
    public static final String KEY_PAIN_ASSESSMENT_3_DATE = "[Pain assessment 3 date]";
    public static final String KEY_PAIN_ASSESSMENT_3_TIME = "[Pain assessment 3 time]";
    public static final String KEY_PAIN_ASSESSMENT_3_SCORE = "[Pain assessment 3 score]";
    public static final String KEY_PAIN_ASSESSMENT_4_DATE = "[Pain assessment 4 date]";
    public static final String KEY_PAIN_ASSESSMENT_4_TIME = "[Pain assessment 4 time]";
    public static final String KEY_PAIN_ASSESSMENT_4_SCORE = "[Pain assessment 4 score]";
    public static final String KEY_PAIN_ASSESSMENT_5_DATE = "[Pain assessment 5 date]";
    public static final String KEY_PAIN_ASSESSMENT_5_TIME = "[Pain assessment 5 time]";
    public static final String KEY_PAIN_ASSESSMENT_5_SCORE = "[Pain assessment 5 score]";
    public static final String KEY_PAIN_ASSESSMENT_6_DATE = "[Pain assessment 6 date]";
    public static final String KEY_PAIN_ASSESSMENT_6_TIME = "[Pain assessment 6 time]";
    public static final String KEY_PAIN_ASSESSMENT_6_SCORE = "[Pain assessment 6 score]";

    public static final String KEY_ANALGESIC_PRES_NUM = "[Number of analgesic prescriptions]";
    public static final String KEY_ANALGESIC_PRES_1_DATE = "[Analgesic prescription 1 date]";
    public static final String KEY_ANALGESIC_PRES_1_TIME = "[Analgesic prescription 1 time]";
    public static final String KEY_ANALGESIC_PRES_1_TYPE = "[Analgesic prescription 1 type]";
    public static final String KEY_ANALGESIC_PRES_1_MODE = "[Analgesic prescription 1 mode]";
    public static final String KEY_ANALGESIC_PRES_2_DATE = "[Analgesic prescription 2 date]";
    public static final String KEY_ANALGESIC_PRES_2_TIME = "[Analgesic prescription 2 time]";
    public static final String KEY_ANALGESIC_PRES_2_TYPE = "[Analgesic prescription 2 type]";
    public static final String KEY_ANALGESIC_PRES_2_MODE = "[Analgesic prescription 2 mode]";
    public static final String KEY_ANALGESIC_PRES_3_DATE = "[Analgesic prescription 3 date]";
    public static final String KEY_ANALGESIC_PRES_3_TIME = "[Analgesic prescription 3 time]";
    public static final String KEY_ANALGESIC_PRES_3_TYPE = "[Analgesic prescription 3 type]";
    public static final String KEY_ANALGESIC_PRES_3_MODE = "[Analgesic prescription 3 mode]";
    public static final String KEY_ANALGESIC_PRES_4_DATE = "[Analgesic prescription 4 date]";
    public static final String KEY_ANALGESIC_PRES_4_TIME = "[Analgesic prescription 4 time]";
    public static final String KEY_ANALGESIC_PRES_4_TYPE = "[Analgesic prescription 4 type]";
    public static final String KEY_ANALGESIC_PRES_4_MODE = "[Analgesic prescription 4 mode]";
    public static final String KEY_ANALGESIC_PRES_5_DATE = "[Analgesic prescription 5 date]";
    public static final String KEY_ANALGESIC_PRES_5_TIME = "[Analgesic prescription 5 time]";
    public static final String KEY_ANALGESIC_PRES_5_TYPE = "[Analgesic prescription 5 type]";
    public static final String KEY_ANALGESIC_PRES_5_MODE = "[Analgesic prescription 5 mode]";
    public static final String KEY_ANALGESIC_PRES_6_DATE = "[Analgesic prescription 6 date]";
    public static final String KEY_ANALGESIC_PRES_6_TIME = "[Analgesic prescription 6 time]";
    public static final String KEY_ANALGESIC_PRES_6_TYPE = "[Analgesic prescription 6 type]";
    public static final String KEY_ANALGESIC_PRES_6_MODE = "[Analgesic prescription 6 mode]";

//    public static final String KEY_ANALGESIC_ADMIN_NUM = "[Number of analgesic administrations]";
//    public static final String KEY_ANALGESIC_ADMIN_1_DATE = "[Analgesic administration 1 date]";
//    public static final String KEY_ANALGESIC_ADMIN_1_TIME = "[Analgesic administration 1 time]";
//    public static final String KEY_ANALGESIC_ADMIN_1_ORDER = "[Analgesic administration 1 type of order]";
//    public static final String KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN = "[Analgesic administration 1 Acetaminophen]";
//    public static final String KEY_ANALGESIC_ADMIN_1_NSAIDS = "[Analgesic administration 1 NSAIDs]";
//    public static final String KEY_ANALGESIC_ADMIN_1_OPIOID = "[Analgesic administration 1 Opioid]";
//    public static final String KEY_ANALGESIC_ADMIN_1_ROUTE = "[Analgesic administration 1 Route]";
//    public static final String KEY_ANALGESIC_ADMIN_1_FREQUENCY = "[Analgesic administration 1 Frequency]";


    public static final String KEY_EVIDENCE_ALTERED_COGNITION = "[Evidence of altered cognition]";
    public static final String KEY_SHORT_CAM_SCORE = "[Short CAM score]";
    public static final String KEY_MENTAL_WORSENING = "[Worsening of mental status]";

    public static final String KEY_DISCHARGE_DATE = "[Discharge date]";
    public static final String KEY_DISCHARGE_TIME = "[Discharge time]";
    public static final String KEY_DISCHARGE_DESTINATION = "[Discharge destination]";
    public static final String KEY_DISCHARGE_TOOL = "[Discharge tool given to patient]";
    public static final String KEY_RETURN_ED = "[Return to ED within 7 days]";
    public static final String KEY_RETURN_ED_REASON = "[Return to ED reason]";



    // TODO: Setup your data fields here:
    public static List<String> dataMap;


    // DB info: its name, and the tables we are using
    public static final String DATABASE_NAME = "PainFreeDB";
    public static final String DATA_TABLE = "dataTable";

    // Track DB version if a new version of your app changes the format.
    public static final int DATABASE_VERSION = 15;


    //Table Create Statements
    private static String DATA_CREATE_SQL;

    // Context of application who uses us.
    private final Context context;

    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    /////////////////////////////////////////////////////////////////////
    //	Public methods:
    /////////////////////////////////////////////////////////////////////

    public DBAdapter(Context ctx) {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    // Open the database connection.
    public DBAdapter open() {

        dataMap = new ArrayList<>();
        dataMap.add(KEY_ROWID);
        dataMap.add(KEY_UNIQUEID);
        dataMap.add(KEY_EXTRACTIONPERIOD);

        dataMap.add(KEY_SUBJECTID);
        dataMap.add(KEY_SITE);
        dataMap.add(KEY_COMPLETED_BY);
        dataMap.add(KEY_DATE);
        dataMap.add(KEY_ARRIVALDATE);
        dataMap.add(KEY_ARRIVALTIME);

        dataMap.add(KEY_DATEOFBIRTH);
        dataMap.add(KEY_SEX);

        dataMap.add(KEY_FRACTURESITE_FOOT);
        dataMap.add(KEY_FRACTURESITE_ANKLE);
        dataMap.add(KEY_FRACTURESITE_TIBIA);
        dataMap.add(KEY_FRACTURESITE_FEMUR);
        dataMap.add(KEY_FRACTURESITE_HIP);
        dataMap.add(KEY_FRACTURESITE_PELVIS);
        dataMap.add(KEY_FRACTURESITE_VERTEBRA);
        dataMap.add(KEY_FRACTURESITE_RIB);
        dataMap.add(KEY_FRACTURESITE_HUMERUS);
        dataMap.add(KEY_FRACTURESITE_FOREARM);
        dataMap.add(KEY_FRACTURESITE_WRIST);

        dataMap.add(KEY_INJURY_MECHANISM);

        dataMap.add(KEY_TRIAGE_DATE);
        dataMap.add(KEY_TRIAGE_TIME);
        dataMap.add(KEY_CTAS);
        dataMap.add(KEY_GLASGOW);
        dataMap.add(KEY_PAINSCALE);
        dataMap.add(KEY_COLLECTIVEORDER);
        dataMap.add(KEY_HISTORYOFDEMENTIA);
        dataMap.add(KEY_ALTEREDCOGNITION);

        dataMap.add(KEY_PHYSICIAN_EXAMINATION_DATE);
        dataMap.add(KEY_PHYSICIAN_EXAMINATION_TIME);

        dataMap.add(KEY_PAIN_ASSESSMENT_NUM);
        dataMap.add(KEY_PAIN_ASSESSMENT_1_DATE);
        dataMap.add(KEY_PAIN_ASSESSMENT_1_TIME);
        dataMap.add(KEY_PAIN_ASSESSMENT_1_SCORE);
        dataMap.add(KEY_PAIN_ASSESSMENT_2_DATE);
        dataMap.add(KEY_PAIN_ASSESSMENT_2_TIME);
        dataMap.add(KEY_PAIN_ASSESSMENT_2_SCORE);
        dataMap.add(KEY_PAIN_ASSESSMENT_3_DATE);
        dataMap.add(KEY_PAIN_ASSESSMENT_3_TIME);
        dataMap.add(KEY_PAIN_ASSESSMENT_3_SCORE);
        dataMap.add(KEY_PAIN_ASSESSMENT_4_DATE);
        dataMap.add(KEY_PAIN_ASSESSMENT_4_TIME);
        dataMap.add(KEY_PAIN_ASSESSMENT_4_SCORE);
        dataMap.add(KEY_PAIN_ASSESSMENT_5_DATE);
        dataMap.add(KEY_PAIN_ASSESSMENT_5_TIME);
        dataMap.add(KEY_PAIN_ASSESSMENT_5_SCORE);
        dataMap.add(KEY_PAIN_ASSESSMENT_6_DATE);
        dataMap.add(KEY_PAIN_ASSESSMENT_6_TIME);
        dataMap.add(KEY_PAIN_ASSESSMENT_6_SCORE);

        dataMap.add(KEY_ANALGESIC_PRES_NUM);
        dataMap.add(KEY_ANALGESIC_PRES_1_DATE);
        dataMap.add(KEY_ANALGESIC_PRES_1_TIME);
        dataMap.add(KEY_ANALGESIC_PRES_1_TYPE);
        dataMap.add(KEY_ANALGESIC_PRES_1_MODE);
        dataMap.add(KEY_ANALGESIC_PRES_2_DATE);
        dataMap.add(KEY_ANALGESIC_PRES_2_TIME);
        dataMap.add(KEY_ANALGESIC_PRES_2_TYPE);
        dataMap.add(KEY_ANALGESIC_PRES_2_MODE);
        dataMap.add(KEY_ANALGESIC_PRES_3_DATE);
        dataMap.add(KEY_ANALGESIC_PRES_3_TIME);
        dataMap.add(KEY_ANALGESIC_PRES_3_TYPE);
        dataMap.add(KEY_ANALGESIC_PRES_3_MODE);
        dataMap.add(KEY_ANALGESIC_PRES_4_DATE);
        dataMap.add(KEY_ANALGESIC_PRES_4_TIME);
        dataMap.add(KEY_ANALGESIC_PRES_4_TYPE);
        dataMap.add(KEY_ANALGESIC_PRES_4_MODE);
        dataMap.add(KEY_ANALGESIC_PRES_5_DATE);
        dataMap.add(KEY_ANALGESIC_PRES_5_TIME);
        dataMap.add(KEY_ANALGESIC_PRES_5_TYPE);
        dataMap.add(KEY_ANALGESIC_PRES_5_MODE);
        dataMap.add(KEY_ANALGESIC_PRES_6_DATE);
        dataMap.add(KEY_ANALGESIC_PRES_6_TIME);
        dataMap.add(KEY_ANALGESIC_PRES_6_TYPE);
        dataMap.add(KEY_ANALGESIC_PRES_6_MODE);

        dataMap.add(KEY_EVIDENCE_ALTERED_COGNITION);
        dataMap.add(KEY_SHORT_CAM_SCORE);
        dataMap.add(KEY_MENTAL_WORSENING);

        dataMap.add(KEY_DISCHARGE_DATE);
        dataMap.add(KEY_DISCHARGE_TIME);
        dataMap.add(KEY_DISCHARGE_DESTINATION);
        dataMap.add(KEY_DISCHARGE_TOOL);
        dataMap.add(KEY_RETURN_ED);
        dataMap.add(KEY_RETURN_ED_REASON);

        generateCreateDataString();

        db = myDBHelper.getWritableDatabase();

        return this;
    }

    private void generateCreateDataString(){
        DATA_CREATE_SQL = "create table " + DATA_TABLE + " (" + KEY_ROWID + " integer primary key autoincrement, ";

        int index = 0;
        for (String key : dataMap){
            if (index>0){
                DATA_CREATE_SQL = DATA_CREATE_SQL + key + " text, ";
            }
            index++;
        }
        DATA_CREATE_SQL = DATA_CREATE_SQL.substring(0, DATA_CREATE_SQL.length()-2); //Trim last comma
        DATA_CREATE_SQL = DATA_CREATE_SQL + ");";
    }

    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }


    /////////////////////////////////////////////////////////////////////
    //	Data methods:
    /////////////////////////////////////////////////////////////////////


    public long insertNewRow(String extractionPeriod){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EXTRACTIONPERIOD, extractionPeriod);
        long id = db.insert(DATA_TABLE, null, initialValues);

        ContentValues updatedValues = new ContentValues();
        String value = MainActivity.deviceID + "-" + id;

        updateFieldData(id, KEY_UNIQUEID, value);
        updateFieldData(id, KEY_PAIN_ASSESSMENT_NUM, "0");

        return id;
    }



    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRowData(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        return db.delete(DATA_TABLE, where, null) != 0;
    }

    public boolean deleteAllRowData() {
        return db.delete(DATA_TABLE, null, null) != 0;
    }


    // Return all data in the database.
    public Cursor getAllRowData() {
        String where = null;
        Cursor c = 	db.query(true, DATA_TABLE, null,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getAllRowDataWithKeys(String[] keys){
        String where = null;
        Cursor c = db.query(true, DATA_TABLE, keys, where, null, null, null, null, null);
        if (c !=null){
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getRowData(long rowId) {

        String where = KEY_ROWID + "=" + rowId;
        Cursor c = db.query(true, DATA_TABLE, null, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;

    }


    public boolean updateFieldData(long rowId, String key, String value) {

        Cursor c = getDataField(rowId, key);

        if (!c.moveToFirst()) {
            return false;
        }
        c.close();
        String where = KEY_ROWID + "= ?";
        ContentValues newValues = new ContentValues();
        newValues.put(key, value);
        return db.update(DATA_TABLE, newValues, where, new String[]{String.valueOf(rowId)}) != 0;
    }


    public Cursor getDataField(long rowId, String key){
        String where = KEY_ROWID + "= ?";
        Cursor c = db.query(DATA_TABLE, new String[]{key}, where, new String[]{String.valueOf(rowId)}, null, null, null);
        if (c != null){
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getDataFields(long rowId, String[] keys){
        String where = KEY_ROWID + "= ?";
        Cursor c = db.query(DATA_TABLE, keys, where, new String[]{String.valueOf(rowId)}, null, null, null);
        if (c != null){
            c.moveToFirst();
        }
        return c;
    }


    /////////////////////////////////////////////////////////////////////
    //	Private Helper Classes:
    /////////////////////////////////////////////////////////////////////

    /**
     * Private class which handles database creation and upgrading.
     * Used to handle low-level database access.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATA_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", attempting to keep old data");


            Cursor c = _db.query(true, DATA_TABLE, null, null, null, null, null, null, null);
            if (c != null) {
                c.moveToFirst();
            }

            String[] columnNames = c.getColumnNames();
            List<String> columnList = Arrays.asList(columnNames);

            for (String key : dataMap){

                String charToDel = "[]";
                String pat = "[" + Pattern.quote(charToDel) + "]";
                String strippedkey = key.replaceAll(pat,"");


                if (!columnList.contains(strippedkey)){
                    Log.d("DEBUG","Adding column " + key);
                    _db.execSQL("ALTER TABLE " + DATA_TABLE + " ADD COLUMN " + key + " text");
                }
            }

            c.close();

        }
    }

}
