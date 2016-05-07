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
    //public static final String KEY_SUBJECTID = "[Subject ID]";
    public static final String KEY_MEDICALRECORDNUMBER = "[Medical record number]";
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
    public static final String KEY_FRACTURESITE_HEAD = "[Head]";
    public static final String KEY_FRACTURESITE_TOES = "[Toes]";
    public static final String KEY_FRACTURESITE_FINGERS = "[Fingers]";

    public static final String KEY_INJURY_MECHANISM = "[Injury mechanism]";

    public static final String KEY_TRIAGE_DATE = "[Triage date]";
    public static final String KEY_TRIAGE_TIME = "[Triage time]";
    public static final String KEY_CTAS = "[CTAS priority]";
    public static final String KEY_GLASGOW = "[Glasgow scale]";
    public static final String KEY_PAINSCALE = "[Pain scale score]";
    public static final String KEY_COLLECTIVEORDER = "[Collective order]";
    public static final String KEY_HISTORYOFALTEREDCOGNITION = "[Past diagnosis of altered cognition]";
    public static final String KEY_HISTORYOFALTEREDCOGNITIONSPECIFY = "[Specify altered cognition]";
    public static final String KEY_ALTEREDCOGNITION = "[Altered cognition]";

    public static final String KEY_PHYSICIAN_EXAMINATION_DATE = "[Physician examination date]";
    public static final String KEY_PHYSICIAN_EXAMINATION_TIME = "[Physician examination time]";

    public static final String KEY_EVENTS_NUM = "[Number of events]";
    public static final String KEY_EVENTS_BOOL = "[No events]";
    public static final String KEY_EVENTS_ORDER = "[Order of events]";
    public static final String KEY_FIRST_EVENT = "[Hours to first event]";

    //public static final String KEY_PAIN_ASSESSMENTS_BOOL = "[No pain assessments]";
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
    public static final String KEY_PAIN_ASSESSMENT_7_DATE = "[Pain assessment 7 date]";
    public static final String KEY_PAIN_ASSESSMENT_7_TIME = "[Pain assessment 7 time]";
    public static final String KEY_PAIN_ASSESSMENT_7_SCORE = "[Pain assessment 7 score]";
    public static final String KEY_PAIN_ASSESSMENT_8_DATE = "[Pain assessment 8 date]";
    public static final String KEY_PAIN_ASSESSMENT_8_TIME = "[Pain assessment 8 time]";
    public static final String KEY_PAIN_ASSESSMENT_8_SCORE = "[Pain assessment 8 score]";

    //public static final String KEY_ANALGESIC_PRES_BOOL = "[No analgesic prescriptions]";
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
    public static final String KEY_ANALGESIC_PRES_7_DATE = "[Analgesic prescription 7 date]";
    public static final String KEY_ANALGESIC_PRES_7_TIME = "[Analgesic prescription 7 time]";
    public static final String KEY_ANALGESIC_PRES_7_TYPE = "[Analgesic prescription 7 type]";
    public static final String KEY_ANALGESIC_PRES_7_MODE = "[Analgesic prescription 7 mode]";
    public static final String KEY_ANALGESIC_PRES_8_DATE = "[Analgesic prescription 8 date]";
    public static final String KEY_ANALGESIC_PRES_8_TIME = "[Analgesic prescription 8 time]";
    public static final String KEY_ANALGESIC_PRES_8_TYPE = "[Analgesic prescription 8 type]";
    public static final String KEY_ANALGESIC_PRES_8_MODE = "[Analgesic prescription 8 mode]";

    public static final String KEY_ANALGESIC_ADMIN_NUM = "[Number of analgesic administrations]";
    public static final String KEY_ANALGESIC_ADMIN_1_DATE = "[Analgesic administration 1 date]";
    public static final String KEY_ANALGESIC_ADMIN_1_TIME = "[Analgesic administration 1 time]";
    public static final String KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN = "[Analgesic administration 1 acetaminophen]";
    public static final String KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_DOSE = "[Analgesic administration 1 acetaminophen dose]";
    public static final String KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_FREQUENCY = "[Analgesic administration 1 acetaminophen frequency]";
    public static final String KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_ROUTE = "[Analgesic administration 1 acetaminophen route]";
    public static final String KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_ORDER = "[Analgesic administration 1 acetaminophen order]";
    public static final String KEY_ANALGESIC_ADMIN_1_NSAIDS = "[Analgesic administration 1 NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_1_NSAIDS_DOSE = "[Analgesic administration 1 NSAIDs dose]";
    public static final String KEY_ANALGESIC_ADMIN_1_NSAIDS_FREQUENCY = "[Analgesic administration 1 NSAIDs frequency]";
    public static final String KEY_ANALGESIC_ADMIN_1_NSAIDS_ROUTE = "[Analgesic administration 1 NSAIDs route]";
    public static final String KEY_ANALGESIC_ADMIN_1_NSAIDS_ORDER = "[Analgesic administration 1 NSAIDs order]";
    public static final String KEY_ANALGESIC_ADMIN_1_OPIOID = "[Analgesic administration 1 opioid]";
    public static final String KEY_ANALGESIC_ADMIN_1_OPIOID_DOSE = "[Analgesic administration 1 opioid dose]";
    public static final String KEY_ANALGESIC_ADMIN_1_OPIOID_FREQUENCY = "[Analgesic administration 1 opioid frequency]";
    public static final String KEY_ANALGESIC_ADMIN_1_OPIOID_ROUTE = "[Analgesic administration 1 opioid route]";
    public static final String KEY_ANALGESIC_ADMIN_1_OPIOID_ORDER = "[Analgesic administration 1 opioid order]";
    public static final String KEY_ANALGESIC_ADMIN_1_REFUSAL = "[Analgesic administration 1 refused]";
    public static final String KEY_ANALGESIC_ADMIN_2_DATE = "[Analgesic administration 2 date]";
    public static final String KEY_ANALGESIC_ADMIN_2_TIME = "[Analgesic administration 2 time]";
    public static final String KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN = "[Analgesic administration 2 acetaminophen]";
    public static final String KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_DOSE = "[Analgesic administration 2 acetaminophen dose]";
    public static final String KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_FREQUENCY = "[Analgesic administration 2 acetaminophen frequency]";
    public static final String KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_ROUTE = "[Analgesic administration 2 acetaminophen route]";
    public static final String KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_ORDER = "[Analgesic administration 2 acetaminophen order]";
    public static final String KEY_ANALGESIC_ADMIN_2_NSAIDS = "[Analgesic administration 2 NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_2_NSAIDS_DOSE = "[Analgesic administration 2 NSAIDs dose]";
    public static final String KEY_ANALGESIC_ADMIN_2_NSAIDS_FREQUENCY = "[Analgesic administration 2 NSAIDs frequency]";
    public static final String KEY_ANALGESIC_ADMIN_2_NSAIDS_ROUTE = "[Analgesic administration 2 NSAIDs route]";
    public static final String KEY_ANALGESIC_ADMIN_2_NSAIDS_ORDER = "[Analgesic administration 2 NSAIDs order]";
    public static final String KEY_ANALGESIC_ADMIN_2_OPIOID = "[Analgesic administration 2 opioid]";
    public static final String KEY_ANALGESIC_ADMIN_2_OPIOID_DOSE = "[Analgesic administration 2 opioid dose]";
    public static final String KEY_ANALGESIC_ADMIN_2_OPIOID_FREQUENCY = "[Analgesic administration 2 opioid frequency]";
    public static final String KEY_ANALGESIC_ADMIN_2_OPIOID_ROUTE = "[Analgesic administration 2 opioid route]";
    public static final String KEY_ANALGESIC_ADMIN_2_OPIOID_ORDER = "[Analgesic administration 2 opioid order]";
    public static final String KEY_ANALGESIC_ADMIN_2_REFUSAL = "[Analgesic administration 2 refused]";
    public static final String KEY_ANALGESIC_ADMIN_3_DATE = "[Analgesic administration 3 date]";
    public static final String KEY_ANALGESIC_ADMIN_3_TIME = "[Analgesic administration 3 time]";
    public static final String KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN = "[Analgesic administration 3 acetaminophen]";
    public static final String KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_DOSE = "[Analgesic administration 3 acetaminophen dose]";
    public static final String KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_FREQUENCY = "[Analgesic administration 3 acetaminophen frequency]";
    public static final String KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_ROUTE = "[Analgesic administration 3 acetaminophen route]";
    public static final String KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_ORDER = "[Analgesic administration 3 acetaminophen order]";
    public static final String KEY_ANALGESIC_ADMIN_3_NSAIDS = "[Analgesic administration 3 NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_3_NSAIDS_DOSE = "[Analgesic administration 3 NSAIDs dose]";
    public static final String KEY_ANALGESIC_ADMIN_3_NSAIDS_FREQUENCY = "[Analgesic administration 3 NSAIDs frequency]";
    public static final String KEY_ANALGESIC_ADMIN_3_NSAIDS_ROUTE = "[Analgesic administration 3 NSAIDs route]";
    public static final String KEY_ANALGESIC_ADMIN_3_NSAIDS_ORDER = "[Analgesic administration 3 NSAIDs order]";
    public static final String KEY_ANALGESIC_ADMIN_3_OPIOID = "[Analgesic administration 3 opioid]";
    public static final String KEY_ANALGESIC_ADMIN_3_OPIOID_DOSE = "[Analgesic administration 3 opioid dose]";
    public static final String KEY_ANALGESIC_ADMIN_3_OPIOID_FREQUENCY = "[Analgesic administration 3 opioid frequency]";
    public static final String KEY_ANALGESIC_ADMIN_3_OPIOID_ROUTE = "[Analgesic administration 3 opioid route]";
    public static final String KEY_ANALGESIC_ADMIN_3_OPIOID_ORDER = "[Analgesic administration 3 opioid order]";
    public static final String KEY_ANALGESIC_ADMIN_3_REFUSAL = "[Analgesic administration 3 refused]";
    public static final String KEY_ANALGESIC_ADMIN_4_DATE = "[Analgesic administration 4 date]";
    public static final String KEY_ANALGESIC_ADMIN_4_TIME = "[Analgesic administration 4 time]";
    public static final String KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN = "[Analgesic administration 4 acetaminophen]";
    public static final String KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_DOSE = "[Analgesic administration 4 acetaminophen dose]";
    public static final String KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_FREQUENCY = "[Analgesic administration 4 acetaminophen frequency]";
    public static final String KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_ROUTE = "[Analgesic administration 4 acetaminophen route]";
    public static final String KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_ORDER = "[Analgesic administration 4 acetaminophen order]";
    public static final String KEY_ANALGESIC_ADMIN_4_NSAIDS = "[Analgesic administration 4 NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_4_NSAIDS_DOSE = "[Analgesic administration 4 NSAIDs dose]";
    public static final String KEY_ANALGESIC_ADMIN_4_NSAIDS_FREQUENCY = "[Analgesic administration 4 NSAIDs frequency]";
    public static final String KEY_ANALGESIC_ADMIN_4_NSAIDS_ROUTE = "[Analgesic administration 4 NSAIDs route]";
    public static final String KEY_ANALGESIC_ADMIN_4_NSAIDS_ORDER = "[Analgesic administration 4 NSAIDs order]";
    public static final String KEY_ANALGESIC_ADMIN_4_OPIOID = "[Analgesic administration 4 opioid]";
    public static final String KEY_ANALGESIC_ADMIN_4_OPIOID_DOSE = "[Analgesic administration 4 opioid dose]";
    public static final String KEY_ANALGESIC_ADMIN_4_OPIOID_FREQUENCY = "[Analgesic administration 4 opioid frequency]";
    public static final String KEY_ANALGESIC_ADMIN_4_OPIOID_ROUTE = "[Analgesic administration 4 opioid route]";
    public static final String KEY_ANALGESIC_ADMIN_4_OPIOID_ORDER = "[Analgesic administration 4 opioid order]";
    public static final String KEY_ANALGESIC_ADMIN_4_REFUSAL = "[Analgesic administration 4 refused]";
    public static final String KEY_ANALGESIC_ADMIN_5_DATE = "[Analgesic administration 5 date]";
    public static final String KEY_ANALGESIC_ADMIN_5_TIME = "[Analgesic administration 5 time]";
    public static final String KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN = "[Analgesic administration 5 acetaminophen]";
    public static final String KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_DOSE = "[Analgesic administration 5 acetaminophen dose]";
    public static final String KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_FREQUENCY = "[Analgesic administration 5 acetaminophen frequency]";
    public static final String KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_ROUTE = "[Analgesic administration 5 acetaminophen route]";
    public static final String KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_ORDER = "[Analgesic administration 5 acetaminophen order]";
    public static final String KEY_ANALGESIC_ADMIN_5_NSAIDS = "[Analgesic administration 5 NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_5_NSAIDS_DOSE = "[Analgesic administration 5 NSAIDs dose]";
    public static final String KEY_ANALGESIC_ADMIN_5_NSAIDS_FREQUENCY = "[Analgesic administration 5 NSAIDs frequency]";
    public static final String KEY_ANALGESIC_ADMIN_5_NSAIDS_ROUTE = "[Analgesic administration 5 NSAIDs route]";
    public static final String KEY_ANALGESIC_ADMIN_5_NSAIDS_ORDER = "[Analgesic administration 5 NSAIDs order]";
    public static final String KEY_ANALGESIC_ADMIN_5_OPIOID = "[Analgesic administration 5 opioid]";
    public static final String KEY_ANALGESIC_ADMIN_5_OPIOID_DOSE = "[Analgesic administration 5 opioid dose]";
    public static final String KEY_ANALGESIC_ADMIN_5_OPIOID_FREQUENCY = "[Analgesic administration 5 opioid frequency]";
    public static final String KEY_ANALGESIC_ADMIN_5_OPIOID_ROUTE = "[Analgesic administration 5 opioid route]";
    public static final String KEY_ANALGESIC_ADMIN_5_OPIOID_ORDER = "[Analgesic administration 5 opioid order]";
    public static final String KEY_ANALGESIC_ADMIN_5_REFUSAL = "[Analgesic administration 5 refused]";
    public static final String KEY_ANALGESIC_ADMIN_6_DATE = "[Analgesic administration 6 date]";
    public static final String KEY_ANALGESIC_ADMIN_6_TIME = "[Analgesic administration 6 time]";
    public static final String KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN = "[Analgesic administration 6 acetaminophen]";
    public static final String KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_DOSE = "[Analgesic administration 6 acetaminophen dose]";
    public static final String KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_FREQUENCY = "[Analgesic administration 6 acetaminophen frequency]";
    public static final String KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_ROUTE = "[Analgesic administration 6 acetaminophen route]";
    public static final String KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_ORDER = "[Analgesic administration 6 acetaminophen order]";
    public static final String KEY_ANALGESIC_ADMIN_6_NSAIDS = "[Analgesic administration 6 NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_6_NSAIDS_DOSE = "[Analgesic administration 6 NSAIDs dose]";
    public static final String KEY_ANALGESIC_ADMIN_6_NSAIDS_FREQUENCY = "[Analgesic administration 6 NSAIDs frequency]";
    public static final String KEY_ANALGESIC_ADMIN_6_NSAIDS_ROUTE = "[Analgesic administration 6 NSAIDs route]";
    public static final String KEY_ANALGESIC_ADMIN_6_NSAIDS_ORDER = "[Analgesic administration 6 NSAIDs order]";
    public static final String KEY_ANALGESIC_ADMIN_6_OPIOID = "[Analgesic administration 6 opioid]";
    public static final String KEY_ANALGESIC_ADMIN_6_OPIOID_DOSE = "[Analgesic administration 6 opioid dose]";
    public static final String KEY_ANALGESIC_ADMIN_6_OPIOID_FREQUENCY = "[Analgesic administration 6 opioid frequency]";
    public static final String KEY_ANALGESIC_ADMIN_6_OPIOID_ROUTE = "[Analgesic administration 6 opioid route]";
    public static final String KEY_ANALGESIC_ADMIN_6_OPIOID_ORDER = "[Analgesic administration 6 opioid order]";
    public static final String KEY_ANALGESIC_ADMIN_6_REFUSAL = "[Analgesic administration 6 refused]";
    public static final String KEY_ANALGESIC_ADMIN_7_DATE = "[Analgesic administration 7 date]";
    public static final String KEY_ANALGESIC_ADMIN_7_TIME = "[Analgesic administration 7 time]";
    public static final String KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN = "[Analgesic administration 7 acetaminophen]";
    public static final String KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_DOSE = "[Analgesic administration 7 acetaminophen dose]";
    public static final String KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_FREQUENCY = "[Analgesic administration 7 acetaminophen frequency]";
    public static final String KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_ROUTE = "[Analgesic administration 7 acetaminophen route]";
    public static final String KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_ORDER = "[Analgesic administration 7 acetaminophen order]";
    public static final String KEY_ANALGESIC_ADMIN_7_NSAIDS = "[Analgesic administration 7 NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_7_NSAIDS_DOSE = "[Analgesic administration 7 NSAIDs dose]";
    public static final String KEY_ANALGESIC_ADMIN_7_NSAIDS_FREQUENCY = "[Analgesic administration 7 NSAIDs frequency]";
    public static final String KEY_ANALGESIC_ADMIN_7_NSAIDS_ROUTE = "[Analgesic administration 7 NSAIDs route]";
    public static final String KEY_ANALGESIC_ADMIN_7_NSAIDS_ORDER = "[Analgesic administration 7 NSAIDs order]";
    public static final String KEY_ANALGESIC_ADMIN_7_OPIOID = "[Analgesic administration 7 opioid]";
    public static final String KEY_ANALGESIC_ADMIN_7_OPIOID_DOSE = "[Analgesic administration 7 opioid dose]";
    public static final String KEY_ANALGESIC_ADMIN_7_OPIOID_FREQUENCY = "[Analgesic administration 7 opioid frequency]";
    public static final String KEY_ANALGESIC_ADMIN_7_OPIOID_ROUTE = "[Analgesic administration 7 opioid route]";
    public static final String KEY_ANALGESIC_ADMIN_7_OPIOID_ORDER = "[Analgesic administration 7 opioid order]";
    public static final String KEY_ANALGESIC_ADMIN_7_REFUSAL = "[Analgesic administration 7 refused]";
    public static final String KEY_ANALGESIC_ADMIN_8_DATE = "[Analgesic administration 8 date]";
    public static final String KEY_ANALGESIC_ADMIN_8_TIME = "[Analgesic administration 8 time]";
    public static final String KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN = "[Analgesic administration 8 acetaminophen]";
    public static final String KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_DOSE = "[Analgesic administration 8 acetaminophen dose]";
    public static final String KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_FREQUENCY = "[Analgesic administration 8 acetaminophen frequency]";
    public static final String KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_ROUTE = "[Analgesic administration 8 acetaminophen route]";
    public static final String KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_ORDER = "[Analgesic administration 8 acetaminophen order]";
    public static final String KEY_ANALGESIC_ADMIN_8_NSAIDS = "[Analgesic administration 8 NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_8_NSAIDS_DOSE = "[Analgesic administration 8 NSAIDs dose]";
    public static final String KEY_ANALGESIC_ADMIN_8_NSAIDS_FREQUENCY = "[Analgesic administration 8 NSAIDs frequency]";
    public static final String KEY_ANALGESIC_ADMIN_8_NSAIDS_ROUTE = "[Analgesic administration 8 NSAIDs route]";
    public static final String KEY_ANALGESIC_ADMIN_8_NSAIDS_ORDER = "[Analgesic administration 8 NSAIDs order]";
    public static final String KEY_ANALGESIC_ADMIN_8_OPIOID = "[Analgesic administration 8 opioid]";
    public static final String KEY_ANALGESIC_ADMIN_8_OPIOID_DOSE = "[Analgesic administration 8 opioid dose]";
    public static final String KEY_ANALGESIC_ADMIN_8_OPIOID_FREQUENCY = "[Analgesic administration 8 opioid frequency]";
    public static final String KEY_ANALGESIC_ADMIN_8_OPIOID_ROUTE = "[Analgesic administration 8 opioid route]";
    public static final String KEY_ANALGESIC_ADMIN_8_OPIOID_ORDER = "[Analgesic administration 8 opioid order]";
    public static final String KEY_ANALGESIC_ADMIN_8_REFUSAL = "[Analgesic administration 8 refused]";

    public static final String KEY_NERVE_BLOCK_NUM = "[Number of nerve block administrations]";
    public static final String KEY_NERVE_BLOCK_1_DATE = "[Nerve block 1 date]";
    public static final String KEY_NERVE_BLOCK_1_TIME = "[Nerve block 1 time]";
    public static final String KEY_NERVE_BLOCK_1_ORDER = "[Nerve block 1 order]";
    public static final String KEY_NERVE_BLOCK_1_TYPE = "[Nerve block 1 type]";
    public static final String KEY_NERVE_BLOCK_2_DATE = "[Nerve block 2 date]";
    public static final String KEY_NERVE_BLOCK_2_TIME = "[Nerve block 2 time]";
    public static final String KEY_NERVE_BLOCK_2_ORDER = "[Nerve block 2 order]";
    public static final String KEY_NERVE_BLOCK_2_TYPE = "[Nerve block 2 type]";
    public static final String KEY_NERVE_BLOCK_3_DATE = "[Nerve block 3 date]";
    public static final String KEY_NERVE_BLOCK_3_TIME = "[Nerve block 3 time]";
    public static final String KEY_NERVE_BLOCK_3_ORDER = "[Nerve block 3 order]";
    public static final String KEY_NERVE_BLOCK_3_TYPE = "[Nerve block 3 type]";
    public static final String KEY_NERVE_BLOCK_4_DATE = "[Nerve block 4 date]";
    public static final String KEY_NERVE_BLOCK_4_TIME = "[Nerve block 4 time]";
    public static final String KEY_NERVE_BLOCK_4_ORDER = "[Nerve block 4 order]";
    public static final String KEY_NERVE_BLOCK_4_TYPE = "[Nerve block 4 type]";
    public static final String KEY_NERVE_BLOCK_5_DATE = "[Nerve block 5 date]";
    public static final String KEY_NERVE_BLOCK_5_TIME = "[Nerve block 5 time]";
    public static final String KEY_NERVE_BLOCK_5_ORDER = "[Nerve block 5 order]";
    public static final String KEY_NERVE_BLOCK_5_TYPE = "[Nerve block 5 type]";
    public static final String KEY_NERVE_BLOCK_6_DATE = "[Nerve block 6 date]";
    public static final String KEY_NERVE_BLOCK_6_TIME = "[Nerve block 6 time]";
    public static final String KEY_NERVE_BLOCK_6_ORDER = "[Nerve block 6 order]";
    public static final String KEY_NERVE_BLOCK_6_TYPE = "[Nerve block 6 type]";
    public static final String KEY_NERVE_BLOCK_7_DATE = "[Nerve block 7 date]";
    public static final String KEY_NERVE_BLOCK_7_TIME = "[Nerve block 7 time]";
    public static final String KEY_NERVE_BLOCK_7_ORDER = "[Nerve block 7 order]";
    public static final String KEY_NERVE_BLOCK_7_TYPE = "[Nerve block 7 type]";
    public static final String KEY_NERVE_BLOCK_8_DATE = "[Nerve block 8 date]";
    public static final String KEY_NERVE_BLOCK_8_TIME = "[Nerve block 8 time]";
    public static final String KEY_NERVE_BLOCK_8_ORDER = "[Nerve block 8 order]";
    public static final String KEY_NERVE_BLOCK_8_TYPE = "[Nerve block 8 type]";

    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_NUM = "[Number of alternative pain reliefs]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_1_DATE = "[Alternative pain relief 1 date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_1_TIME = "[Alternative pain relief 1 time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_1 = "[Alternative pain relief 1]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_1_OTHER = "[Alternative pain relief 1 other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_2_DATE = "[Alternative pain relief 2 date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_2_TIME = "[Alternative pain relief 2 time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_2 = "[Alternative pain relief 2]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_2_OTHER = "[Alternative pain relief 2 other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_3_DATE = "[Alternative pain relief 3 date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_3_TIME = "[Alternative pain relief 3 time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_3 = "[Alternative pain relief 3]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_3_OTHER = "[Alternative pain relief 3 other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_4_DATE = "[Alternative pain relief 4 date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_4_TIME = "[Alternative pain relief 4 time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_4 = "[Alternative pain relief 4]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_4_OTHER = "[Alternative pain relief 4 other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_5_DATE = "[Alternative pain relief 5 date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_5_TIME = "[Alternative pain relief 5 time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_5 = "[Alternative pain relief 5]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_5_OTHER = "[Alternative pain relief 5 other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_6_DATE = "[Alternative pain relief 6 date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_6_TIME = "[Alternative pain relief 6 time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_6 = "[Alternative pain relief 6]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_6_OTHER = "[Alternative pain relief 6 other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_7_DATE = "[Alternative pain relief 7 date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_7_TIME = "[Alternative pain relief 7 time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_7 = "[Alternative pain relief 7]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_7_OTHER = "[Alternative pain relief 7 other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_8_DATE = "[Alternative pain relief 8 date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_8_TIME = "[Alternative pain relief 8 time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_8 = "[Alternative pain relief 8]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_8_OTHER = "[Alternative pain relief 8 other]";


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
    public static final int DATABASE_VERSION = 18;


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
        //dataMap.add(KEY_SUBJECTID);
        dataMap.add(KEY_MEDICALRECORDNUMBER);
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
        dataMap.add(KEY_FRACTURESITE_HEAD);
        dataMap.add(KEY_FRACTURESITE_TOES);
        dataMap.add(KEY_FRACTURESITE_FINGERS);

        dataMap.add(KEY_INJURY_MECHANISM);

        dataMap.add(KEY_TRIAGE_DATE);
        dataMap.add(KEY_TRIAGE_TIME);
        dataMap.add(KEY_CTAS);
        dataMap.add(KEY_GLASGOW);
        dataMap.add(KEY_PAINSCALE);
        dataMap.add(KEY_COLLECTIVEORDER);
        dataMap.add(KEY_HISTORYOFALTEREDCOGNITION);
        dataMap.add(KEY_HISTORYOFALTEREDCOGNITIONSPECIFY);
        dataMap.add(KEY_ALTEREDCOGNITION);

        dataMap.add(KEY_PHYSICIAN_EXAMINATION_DATE);
        dataMap.add(KEY_PHYSICIAN_EXAMINATION_TIME);

        dataMap.add(KEY_EVENTS_BOOL);
        dataMap.add(KEY_EVENTS_NUM);
        dataMap.add(KEY_EVENTS_ORDER);
        dataMap.add(KEY_FIRST_EVENT);

        //dataMap.add(KEY_PAIN_ASSESSMENTS_BOOL);
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
        dataMap.add(KEY_PAIN_ASSESSMENT_7_DATE);
        dataMap.add(KEY_PAIN_ASSESSMENT_7_TIME);
        dataMap.add(KEY_PAIN_ASSESSMENT_7_SCORE);
        dataMap.add(KEY_PAIN_ASSESSMENT_8_DATE);
        dataMap.add(KEY_PAIN_ASSESSMENT_8_TIME);
        dataMap.add(KEY_PAIN_ASSESSMENT_8_SCORE);

        //dataMap.add(KEY_ANALGESIC_PRES_BOOL);
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
        dataMap.add(KEY_ANALGESIC_PRES_7_DATE);
        dataMap.add(KEY_ANALGESIC_PRES_7_TIME);
        dataMap.add(KEY_ANALGESIC_PRES_7_TYPE);
        dataMap.add(KEY_ANALGESIC_PRES_7_MODE);
        dataMap.add(KEY_ANALGESIC_PRES_8_DATE);
        dataMap.add(KEY_ANALGESIC_PRES_8_TIME);
        dataMap.add(KEY_ANALGESIC_PRES_8_TYPE);
        dataMap.add(KEY_ANALGESIC_PRES_8_MODE);

        dataMap.add(KEY_ANALGESIC_ADMIN_NUM);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_DATE);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_TIME);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_NSAIDS);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_NSAIDS_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_NSAIDS_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_NSAIDS_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_NSAIDS_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_OPIOID);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_OPIOID_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_OPIOID_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_OPIOID_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_OPIOID_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_1_REFUSAL);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_DATE);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_TIME);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_NSAIDS);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_NSAIDS_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_NSAIDS_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_NSAIDS_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_NSAIDS_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_OPIOID);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_OPIOID_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_OPIOID_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_OPIOID_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_OPIOID_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_2_REFUSAL);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_DATE);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_TIME);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_NSAIDS);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_NSAIDS_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_NSAIDS_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_NSAIDS_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_NSAIDS_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_OPIOID);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_OPIOID_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_OPIOID_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_OPIOID_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_OPIOID_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_3_REFUSAL);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_DATE);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_TIME);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_NSAIDS);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_NSAIDS_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_NSAIDS_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_NSAIDS_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_NSAIDS_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_OPIOID);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_OPIOID_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_OPIOID_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_OPIOID_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_OPIOID_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_4_REFUSAL);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_DATE);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_TIME);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_NSAIDS);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_NSAIDS_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_NSAIDS_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_NSAIDS_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_NSAIDS_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_OPIOID);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_OPIOID_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_OPIOID_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_OPIOID_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_OPIOID_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_5_REFUSAL);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_DATE);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_TIME);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_NSAIDS);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_NSAIDS_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_NSAIDS_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_NSAIDS_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_NSAIDS_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_OPIOID);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_OPIOID_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_OPIOID_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_OPIOID_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_OPIOID_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_6_REFUSAL);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_DATE);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_TIME);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_NSAIDS);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_NSAIDS_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_NSAIDS_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_NSAIDS_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_NSAIDS_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_OPIOID);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_OPIOID_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_OPIOID_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_OPIOID_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_OPIOID_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_7_REFUSAL);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_DATE);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_TIME);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_NSAIDS);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_NSAIDS_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_NSAIDS_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_NSAIDS_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_NSAIDS_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_OPIOID);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_OPIOID_DOSE);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_OPIOID_FREQUENCY);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_OPIOID_ROUTE);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_OPIOID_ORDER);
        dataMap.add(KEY_ANALGESIC_ADMIN_8_REFUSAL);

        dataMap.add(KEY_NERVE_BLOCK_NUM);
        dataMap.add(KEY_NERVE_BLOCK_1_DATE);
        dataMap.add(KEY_NERVE_BLOCK_1_TIME);
        dataMap.add(KEY_NERVE_BLOCK_1_ORDER);
        dataMap.add(KEY_NERVE_BLOCK_1_TYPE);
        dataMap.add(KEY_NERVE_BLOCK_2_DATE);
        dataMap.add(KEY_NERVE_BLOCK_2_TIME);
        dataMap.add(KEY_NERVE_BLOCK_2_ORDER);
        dataMap.add(KEY_NERVE_BLOCK_2_TYPE);
        dataMap.add(KEY_NERVE_BLOCK_3_DATE);
        dataMap.add(KEY_NERVE_BLOCK_3_TIME);
        dataMap.add(KEY_NERVE_BLOCK_3_ORDER);
        dataMap.add(KEY_NERVE_BLOCK_3_TYPE);
        dataMap.add(KEY_NERVE_BLOCK_4_DATE);
        dataMap.add(KEY_NERVE_BLOCK_4_TIME);
        dataMap.add(KEY_NERVE_BLOCK_4_ORDER);
        dataMap.add(KEY_NERVE_BLOCK_4_TYPE);
        dataMap.add(KEY_NERVE_BLOCK_5_DATE);
        dataMap.add(KEY_NERVE_BLOCK_5_TIME);
        dataMap.add(KEY_NERVE_BLOCK_5_ORDER);
        dataMap.add(KEY_NERVE_BLOCK_5_TYPE);
        dataMap.add(KEY_NERVE_BLOCK_6_DATE);
        dataMap.add(KEY_NERVE_BLOCK_6_TIME);
        dataMap.add(KEY_NERVE_BLOCK_6_ORDER);
        dataMap.add(KEY_NERVE_BLOCK_6_TYPE);
        dataMap.add(KEY_NERVE_BLOCK_7_DATE);
        dataMap.add(KEY_NERVE_BLOCK_7_TIME);
        dataMap.add(KEY_NERVE_BLOCK_7_ORDER);
        dataMap.add(KEY_NERVE_BLOCK_7_TYPE);
        dataMap.add(KEY_NERVE_BLOCK_8_DATE);
        dataMap.add(KEY_NERVE_BLOCK_8_TIME);
        dataMap.add(KEY_NERVE_BLOCK_8_ORDER);
        dataMap.add(KEY_NERVE_BLOCK_8_TYPE);

        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_NUM);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_1_DATE);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_1_TIME);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_1);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_1_OTHER);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_2_DATE);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_2_TIME);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_2);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_2_OTHER);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_3_DATE);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_3_TIME);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_3);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_3_OTHER);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_4_DATE);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_4_TIME);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_4);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_4_OTHER);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_5_DATE);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_5_TIME);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_5);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_5_OTHER);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_6_DATE);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_6_TIME);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_6);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_6_OTHER);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_7_DATE);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_7_TIME);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_7);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_7_OTHER);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_8_DATE);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_8_TIME);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_8);
        dataMap.add(KEY_ALTERNATIVE_PAIN_RELIEF_8_OTHER);

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


    public long insertNewRow(){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_PAIN_ASSESSMENT_NUM, "0");
        initialValues.put(KEY_ANALGESIC_PRES_NUM, "0");
        initialValues.put(KEY_ANALGESIC_ADMIN_NUM, "0");
        initialValues.put(KEY_NERVE_BLOCK_NUM, "0");
        initialValues.put(KEY_ALTERNATIVE_PAIN_RELIEF_NUM, "0");
        long id = db.insert(DATA_TABLE, null, initialValues);

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

    public boolean updateFields(long rowId, String[] keys, String[] values){
        Cursor c = getDataFields(rowId, keys);

        if (!c.moveToFirst()) {
            return false;
        }
        c.close();

        String where = KEY_ROWID + "= ?";
        ContentValues newValues = new ContentValues();
        for (int i=0; i<keys.length; i++){
            if (values != null){
                newValues.put(keys[i], values[i]);
            } else {
                newValues.putNull(keys[i]);
            }

        }
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
