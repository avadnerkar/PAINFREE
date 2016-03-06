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
    public static final String KEY_UNIQUEID = "DeviceSpecificID";
    public static final String KEY_EXTRACTIONPERIOD = "PeriodOfExtraction";
    public static final String KEY_SUBJECTID = "SubjectID";
    public static final String KEY_SITE = "Site";
    public static final String KEY_COMPLETED_BY = "CompletedBy";
    public static final String KEY_DATE = "Date";
    public static final String KEY_ARRIVALDATE = "ArrivalDate";
    public static final String KEY_ARRIVALTIME = "ArrivalTime";

    public static final String KEY_DATEOFBIRTH = "DateOfBirth";
    public static final String KEY_SEX = "Sex";

    public static final String KEY_FRACTURESITE_FOOT = "Foot";
    public static final String KEY_FRACTURESITE_ANKLE = "Ankle";
    public static final String KEY_FRACTURESITE_TIBIA = "TibiaOrFibula";
    public static final String KEY_FRACTURESITE_FEMUR = "Femur";
    public static final String KEY_FRACTURESITE_HIP = "Hip";
    public static final String KEY_FRACTURESITE_PELVIS = "Pelvis";
    public static final String KEY_FRACTURESITE_VERTEBRA = "Vertebra";
    public static final String KEY_FRACTURESITE_RIB = "Rib";
    public static final String KEY_FRACTURESITE_HUMERUS = "Humerus";
    public static final String KEY_FRACTURESITE_FOREARM = "Forearm";
    public static final String KEY_FRACTURESITE_WRIST = "Wrist";



    // TODO: Setup your data fields here:
    public static List<String> dataMap;


    // DB info: its name, and the tables we are using
    public static final String DATABASE_NAME = "PainFreeDB";
    public static final String DATA_TABLE = "dataTable";

    // Track DB version if a new version of your app changes the format.
    public static final int DATABASE_VERSION = 6;


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
                if (!columnList.contains(key)){
                    Log.d("DEBUG","Adding column " + key);
                    _db.execSQL("ALTER TABLE " + DATA_TABLE + " ADD COLUMN " + key + " text");
                }
            }

            c.close();

        }
    }

}
