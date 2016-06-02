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
    public static final String KEY_UNIQUEID = "[DeviceSpecificID]";
    public static final String KEY_EXTRACTIONPERIOD = "[ExtractPeriod]";
    //public static final String KEY_SUBJECTID = "[Subject ID]";
    public static final String KEY_MEDICALRECORDNUMBER = "[MRN]";
    public static final String KEY_SITE = "[Site]";
    public static final String KEY_COMPLETED_BY = "[CompletedBy]";
    public static final String KEY_DATE = "[Date]";
    public static final String KEY_ARRIVALDATE = "[ArrivalDate]";
    public static final String KEY_ARRIVALTIME = "[ArrivalTime]";

    public static final String KEY_DATEOFBIRTH = "[DOB]";
    public static final String KEY_SEX = "[Sex]";

    public static final String KEY_FRACTURESITE_FOOT = "[Frac_Foot]";
    public static final String KEY_FRACTURESITE_ANKLE = "[Frac_Ankle]";
    public static final String KEY_FRACTURESITE_TIBIA = "[Frac_Tibia_Fibula]";
    public static final String KEY_FRACTURESITE_FEMUR = "[Frac_Femur]";
    public static final String KEY_FRACTURESITE_HIP = "[Frac_Hip]";
    public static final String KEY_FRACTURESITE_PELVIS = "[Frac_Pelvis]";
    public static final String KEY_FRACTURESITE_VERTEBRA = "[Frac_Vertebra]";
    public static final String KEY_FRACTURESITE_RIB = "[Frac_Rib]";
    public static final String KEY_FRACTURESITE_HUMERUS = "[Frac_Humerus]";
    public static final String KEY_FRACTURESITE_FOREARM = "[Frac_Forearm]";
    public static final String KEY_FRACTURESITE_WRIST = "[Frac_Wrist]";
    public static final String KEY_FRACTURESITE_HEAD = "[Frac_Head]";
    public static final String KEY_FRACTURESITE_TOES = "[Frac_Toes]";
    public static final String KEY_FRACTURESITE_FINGERS = "[Frac_Fingers]";
    public static final String KEY_FRACTURESITE_OTHER = "[Frac_Other]";

    public static final String KEY_INJURY_MECHANISM = "[InjuryMechanism]";

    public static final String KEY_TRIAGE_DATE = "[TriageDate]";
    public static final String KEY_TRIAGE_TIME = "[TriageTime]";
    public static final String KEY_CTAS = "[CTAS_priority]";
    public static final String KEY_GLASGOW = "[GlasgowScale]";
    public static final String KEY_PAINSCALE = "[PainScaleScore]";
    public static final String KEY_COLLECTIVEORDER = "[CollectiveOrder]";
    public static final String KEY_HISTORYOFALTEREDCOGNITION = "[PastAlteredCog]";
    public static final String KEY_HISTORYOFALTEREDCOGNITIONSPECIFY = "[SpecifyAlteredCog]";
    public static final String KEY_ALTEREDCOGNITION = "[AlteredCog]";

    public static final String KEY_PHYSICIAN_EXAMINATION_DATE = "[PhysicianExamDate]";
    public static final String KEY_PHYSICIAN_EXAMINATION_TIME = "[PhysicianExamTime]";

    public static final String KEY_EVENTS_NUM = "[N_events]";
    public static final String KEY_EVENTS_BOOL = "[ZeroEvents]";
    public static final String KEY_EVENTS_ORDER = "[EventOrder]";
    public static final String KEY_FIRST_EVENT = "[HoursFirstEvent]";

    //public static final String KEY_PAIN_ASSESSMENTS_BOOL = "[No pain assessments]";
    public static final String KEY_PAIN_ASSESSMENT_NUM = "[N_PainAssess]";
    public static final String KEY_PAIN_ASSESSMENT_1_DATE = "[PainAssess1_date]";
    public static final String KEY_PAIN_ASSESSMENT_1_TIME = "[PainAssess1_time]";
    public static final String KEY_PAIN_ASSESSMENT_1_SCORE = "[PainAssess1_score]";
    public static final String KEY_PAIN_ASSESSMENT_2_DATE = "[PainAssess2_date]";
    public static final String KEY_PAIN_ASSESSMENT_2_TIME = "[PainAssess2_time]";
    public static final String KEY_PAIN_ASSESSMENT_2_SCORE = "[PainAssess2_score]";
    public static final String KEY_PAIN_ASSESSMENT_3_DATE = "[PainAssess3_date]";
    public static final String KEY_PAIN_ASSESSMENT_3_TIME = "[PainAssess3_time]";
    public static final String KEY_PAIN_ASSESSMENT_3_SCORE = "[PainAssess3_score]";
    public static final String KEY_PAIN_ASSESSMENT_4_DATE = "[PainAssess4_date]";
    public static final String KEY_PAIN_ASSESSMENT_4_TIME = "[PainAssess4_time]";
    public static final String KEY_PAIN_ASSESSMENT_4_SCORE = "[PainAssess4_score]";
    public static final String KEY_PAIN_ASSESSMENT_5_DATE = "[PainAssess5_date]";
    public static final String KEY_PAIN_ASSESSMENT_5_TIME = "[PainAssess5_time]";
    public static final String KEY_PAIN_ASSESSMENT_5_SCORE = "[PainAssess5_score]";
    public static final String KEY_PAIN_ASSESSMENT_6_DATE = "[PainAssess6_date]";
    public static final String KEY_PAIN_ASSESSMENT_6_TIME = "[PainAssess6_time]";
    public static final String KEY_PAIN_ASSESSMENT_6_SCORE = "[PainAssess6_score]";
    public static final String KEY_PAIN_ASSESSMENT_7_DATE = "[PainAssess7_date]";
    public static final String KEY_PAIN_ASSESSMENT_7_TIME = "[PainAssess7_time]";
    public static final String KEY_PAIN_ASSESSMENT_7_SCORE = "[PainAssess7_score]";
    public static final String KEY_PAIN_ASSESSMENT_8_DATE = "[PainAssess8_date]";
    public static final String KEY_PAIN_ASSESSMENT_8_TIME = "[PainAssess8_time]";
    public static final String KEY_PAIN_ASSESSMENT_8_SCORE = "[PainAssess8_score]";

    //public static final String KEY_ANALGESIC_PRES_BOOL = "[No analgesic prescriptions]";
    public static final String KEY_ANALGESIC_PRES_NUM = "[N_ang_pres]";
    public static final String KEY_ANALGESIC_PRES_1_DATE = "[AngPres1_date]";
    public static final String KEY_ANALGESIC_PRES_1_TIME = "[AngPres1_time]";
    public static final String KEY_ANALGESIC_PRES_1_TYPE = "[AngPres1_type]";
    public static final String KEY_ANALGESIC_PRES_1_MODE = "[AngPres1_mode]";
    public static final String KEY_ANALGESIC_PRES_2_DATE = "[AngPres2_date]";
    public static final String KEY_ANALGESIC_PRES_2_TIME = "[AngPres2_time]";
    public static final String KEY_ANALGESIC_PRES_2_TYPE = "[AngPres2_type]";
    public static final String KEY_ANALGESIC_PRES_2_MODE = "[AngPres2_mode]";
    public static final String KEY_ANALGESIC_PRES_3_DATE = "[AngPres3_date]";
    public static final String KEY_ANALGESIC_PRES_3_TIME = "[AngPres3_time]";
    public static final String KEY_ANALGESIC_PRES_3_TYPE = "[AngPres3_type]";
    public static final String KEY_ANALGESIC_PRES_3_MODE = "[AngPres3_mode]";
    public static final String KEY_ANALGESIC_PRES_4_DATE = "[AngPres4_date]";
    public static final String KEY_ANALGESIC_PRES_4_TIME = "[AngPres4_time]";
    public static final String KEY_ANALGESIC_PRES_4_TYPE = "[AngPres4_type]";
    public static final String KEY_ANALGESIC_PRES_4_MODE = "[AngPres4_mode]";
    public static final String KEY_ANALGESIC_PRES_5_DATE = "[AngPres5_date]";
    public static final String KEY_ANALGESIC_PRES_5_TIME = "[AngPres5_time]";
    public static final String KEY_ANALGESIC_PRES_5_TYPE = "[AngPres5_type]";
    public static final String KEY_ANALGESIC_PRES_5_MODE = "[AngPres5_mode]";
    public static final String KEY_ANALGESIC_PRES_6_DATE = "[AngPres6_date]";
    public static final String KEY_ANALGESIC_PRES_6_TIME = "[AngPres6_time]";
    public static final String KEY_ANALGESIC_PRES_6_TYPE = "[AngPres6_type]";
    public static final String KEY_ANALGESIC_PRES_6_MODE = "[AngPres6_mode]";
    public static final String KEY_ANALGESIC_PRES_7_DATE = "[AngPres7_date]";
    public static final String KEY_ANALGESIC_PRES_7_TIME = "[AngPres7_time]";
    public static final String KEY_ANALGESIC_PRES_7_TYPE = "[AngPres7_type]";
    public static final String KEY_ANALGESIC_PRES_7_MODE = "[AngPres7_mode]";
    public static final String KEY_ANALGESIC_PRES_8_DATE = "[AngPres8_date]";
    public static final String KEY_ANALGESIC_PRES_8_TIME = "[AngPres8_time]";
    public static final String KEY_ANALGESIC_PRES_8_TYPE = "[AngPres8_type]";
    public static final String KEY_ANALGESIC_PRES_8_MODE = "[AngPres8_mode]";

    public static final String KEY_ANALGESIC_ADMIN_NUM = "[N_ang_admin]";
    public static final String KEY_ANALGESIC_ADMIN_1_DATE = "[AngAdm1_date]";
    public static final String KEY_ANALGESIC_ADMIN_1_TIME = "[AngAdm1_time]";
    public static final String KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN = "[AngAdm1_acet]";
    public static final String KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_DOSE = "[AngAdm1_acet_dose]";
    public static final String KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_FREQUENCY = "[AngAdm1_acet_freq]";
    public static final String KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_ROUTE = "[AngAdm1_acet_route]";
    public static final String KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_ORDER = "[AngAdm1_acet_order]";
    public static final String KEY_ANALGESIC_ADMIN_1_NSAIDS = "[AngAdm1_NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_1_NSAIDS_DOSE = "[AngAdm1_NSAIDs_dose]";
    public static final String KEY_ANALGESIC_ADMIN_1_NSAIDS_FREQUENCY = "[AngAdm1_NSAIDs_freq]";
    public static final String KEY_ANALGESIC_ADMIN_1_NSAIDS_ROUTE = "[AngAdm1_NSAIDs_route]";
    public static final String KEY_ANALGESIC_ADMIN_1_NSAIDS_ORDER = "[AngAdm1_NSAIDs_order]";
    public static final String KEY_ANALGESIC_ADMIN_1_OPIOID = "[AngAdm1_opioid]";
    public static final String KEY_ANALGESIC_ADMIN_1_OPIOID_DOSE = "[AngAdm1_opioid_dose]";
    public static final String KEY_ANALGESIC_ADMIN_1_OPIOID_FREQUENCY = "[AngAdm1_opioid_freq]";
    public static final String KEY_ANALGESIC_ADMIN_1_OPIOID_ROUTE = "[AngAdm1_opioid_route]";
    public static final String KEY_ANALGESIC_ADMIN_1_OPIOID_ORDER = "[AngAdm1_opioid_order]";
    public static final String KEY_ANALGESIC_ADMIN_1_REFUSAL = "[AngAdm1_refused]";
    public static final String KEY_ANALGESIC_ADMIN_2_DATE = "[AngAdm2_date]";
    public static final String KEY_ANALGESIC_ADMIN_2_TIME = "[AngAdm2_time]";
    public static final String KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN = "[AngAdm2_acet]";
    public static final String KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_DOSE = "[AngAdm2_acet_dose]";
    public static final String KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_FREQUENCY = "[AngAdm2_acet_freq]";
    public static final String KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_ROUTE = "[AngAdm2_acet_route]";
    public static final String KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_ORDER = "[AngAdm2_acet_order]";
    public static final String KEY_ANALGESIC_ADMIN_2_NSAIDS = "[AngAdm2_NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_2_NSAIDS_DOSE = "[AngAdm2_NSAIDs_dose]";
    public static final String KEY_ANALGESIC_ADMIN_2_NSAIDS_FREQUENCY = "[AngAdm2_NSAIDs_freq]";
    public static final String KEY_ANALGESIC_ADMIN_2_NSAIDS_ROUTE = "[AngAdm2_NSAIDs_route]";
    public static final String KEY_ANALGESIC_ADMIN_2_NSAIDS_ORDER = "[AngAdm2_NSAIDs_order]";
    public static final String KEY_ANALGESIC_ADMIN_2_OPIOID = "[AngAdm2_opioid]";
    public static final String KEY_ANALGESIC_ADMIN_2_OPIOID_DOSE = "[AngAdm2_opioid_dose]";
    public static final String KEY_ANALGESIC_ADMIN_2_OPIOID_FREQUENCY = "[AngAdm2_opioid_freq]";
    public static final String KEY_ANALGESIC_ADMIN_2_OPIOID_ROUTE = "[AngAdm2_opioid_route]";
    public static final String KEY_ANALGESIC_ADMIN_2_OPIOID_ORDER = "[AngAdm2_opioid_order]";
    public static final String KEY_ANALGESIC_ADMIN_2_REFUSAL = "[AngAdm2_refused]";
    public static final String KEY_ANALGESIC_ADMIN_3_DATE = "[AngAdm3_date]";
    public static final String KEY_ANALGESIC_ADMIN_3_TIME = "[AngAdm3_time]";
    public static final String KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN = "[AngAdm3_acet]";
    public static final String KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_DOSE = "[AngAdm3_acet_dose]";
    public static final String KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_FREQUENCY = "[AngAdm3_acet_freq]";
    public static final String KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_ROUTE = "[AngAdm3_acet_route]";
    public static final String KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_ORDER = "[AngAdm3_acet_order]";
    public static final String KEY_ANALGESIC_ADMIN_3_NSAIDS = "[AngAdm3_NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_3_NSAIDS_DOSE = "[AngAdm3_NSAIDs_dose]";
    public static final String KEY_ANALGESIC_ADMIN_3_NSAIDS_FREQUENCY = "[AngAdm3_NSAIDs_freq]";
    public static final String KEY_ANALGESIC_ADMIN_3_NSAIDS_ROUTE = "[AngAdm3_NSAIDs_route]";
    public static final String KEY_ANALGESIC_ADMIN_3_NSAIDS_ORDER = "[AngAdm3_NSAIDs_order]";
    public static final String KEY_ANALGESIC_ADMIN_3_OPIOID = "[AngAdm3_opioid]";
    public static final String KEY_ANALGESIC_ADMIN_3_OPIOID_DOSE = "[AngAdm3_opioid_dose]";
    public static final String KEY_ANALGESIC_ADMIN_3_OPIOID_FREQUENCY = "[AngAdm3_opioid_freq]";
    public static final String KEY_ANALGESIC_ADMIN_3_OPIOID_ROUTE = "[AngAdm3_opioid_route]";
    public static final String KEY_ANALGESIC_ADMIN_3_OPIOID_ORDER = "[AngAdm3_opioid_order]";
    public static final String KEY_ANALGESIC_ADMIN_3_REFUSAL = "[AngAdm3_refused]";
    public static final String KEY_ANALGESIC_ADMIN_4_DATE = "[AngAdm4_date]";
    public static final String KEY_ANALGESIC_ADMIN_4_TIME = "[AngAdm4_time]";
    public static final String KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN = "[AngAdm4_acet]";
    public static final String KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_DOSE = "[AngAdm4_acet_dose]";
    public static final String KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_FREQUENCY = "[AngAdm4_acet_freq]";
    public static final String KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_ROUTE = "[AngAdm4_acet_route]";
    public static final String KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_ORDER = "[AngAdm4_acet_order]";
    public static final String KEY_ANALGESIC_ADMIN_4_NSAIDS = "[AngAdm4_NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_4_NSAIDS_DOSE = "[AngAdm4_NSAIDs_dose]";
    public static final String KEY_ANALGESIC_ADMIN_4_NSAIDS_FREQUENCY = "[AngAdm4_NSAIDs_freq]";
    public static final String KEY_ANALGESIC_ADMIN_4_NSAIDS_ROUTE = "[AngAdm4_NSAIDs_route]";
    public static final String KEY_ANALGESIC_ADMIN_4_NSAIDS_ORDER = "[AngAdm4_NSAIDs_order]";
    public static final String KEY_ANALGESIC_ADMIN_4_OPIOID = "[AngAdm4_opioid]";
    public static final String KEY_ANALGESIC_ADMIN_4_OPIOID_DOSE = "[AngAdm4_opioid_dose]";
    public static final String KEY_ANALGESIC_ADMIN_4_OPIOID_FREQUENCY = "[AngAdm4_opioid_freq]";
    public static final String KEY_ANALGESIC_ADMIN_4_OPIOID_ROUTE = "[AngAdm4_opioid_route]";
    public static final String KEY_ANALGESIC_ADMIN_4_OPIOID_ORDER = "[AngAdm4_opioid_order]";
    public static final String KEY_ANALGESIC_ADMIN_4_REFUSAL = "[AngAdm4_refused]";
    public static final String KEY_ANALGESIC_ADMIN_5_DATE = "[AngAdm5_date]";
    public static final String KEY_ANALGESIC_ADMIN_5_TIME = "[AngAdm5_time]";
    public static final String KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN = "[AngAdm5_acet]";
    public static final String KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_DOSE = "[AngAdm5_acet_dose]";
    public static final String KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_FREQUENCY = "[AngAdm5_acet_freq]";
    public static final String KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_ROUTE = "[AngAdm5_acet_route]";
    public static final String KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_ORDER = "[AngAdm5_acet_order]";
    public static final String KEY_ANALGESIC_ADMIN_5_NSAIDS = "[AngAdm5_NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_5_NSAIDS_DOSE = "[AngAdm5_NSAIDs_dose]";
    public static final String KEY_ANALGESIC_ADMIN_5_NSAIDS_FREQUENCY = "[AngAdm5_NSAIDs_freq]";
    public static final String KEY_ANALGESIC_ADMIN_5_NSAIDS_ROUTE = "[AngAdm5_NSAIDs_route]";
    public static final String KEY_ANALGESIC_ADMIN_5_NSAIDS_ORDER = "[AngAdm5_NSAIDs_order]";
    public static final String KEY_ANALGESIC_ADMIN_5_OPIOID = "[AngAdm5_opioid]";
    public static final String KEY_ANALGESIC_ADMIN_5_OPIOID_DOSE = "[AngAdm5_opioid_dose]";
    public static final String KEY_ANALGESIC_ADMIN_5_OPIOID_FREQUENCY = "[AngAdm5_opioid_freq]";
    public static final String KEY_ANALGESIC_ADMIN_5_OPIOID_ROUTE = "[AngAdm5_opioid_route]";
    public static final String KEY_ANALGESIC_ADMIN_5_OPIOID_ORDER = "[AngAdm5_opioid_order]";
    public static final String KEY_ANALGESIC_ADMIN_5_REFUSAL = "[AngAdm5_refused]";
    public static final String KEY_ANALGESIC_ADMIN_6_DATE = "[AngAdm6_date]";
    public static final String KEY_ANALGESIC_ADMIN_6_TIME = "[AngAdm6_time]";
    public static final String KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN = "[AngAdm6_acet]";
    public static final String KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_DOSE = "[AngAdm6_acet_dose]";
    public static final String KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_FREQUENCY = "[AngAdm6_acet_freq]";
    public static final String KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_ROUTE = "[AngAdm6_acet_route]";
    public static final String KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_ORDER = "[AngAdm6_acet_order]";
    public static final String KEY_ANALGESIC_ADMIN_6_NSAIDS = "[AngAdm6_NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_6_NSAIDS_DOSE = "[AngAdm6_NSAIDs_dose]";
    public static final String KEY_ANALGESIC_ADMIN_6_NSAIDS_FREQUENCY = "[AngAdm6_NSAIDs_freq]";
    public static final String KEY_ANALGESIC_ADMIN_6_NSAIDS_ROUTE = "[AngAdm6_NSAIDs_route]";
    public static final String KEY_ANALGESIC_ADMIN_6_NSAIDS_ORDER = "[AngAdm6_NSAIDs_order]";
    public static final String KEY_ANALGESIC_ADMIN_6_OPIOID = "[AngAdm6_opioid]";
    public static final String KEY_ANALGESIC_ADMIN_6_OPIOID_DOSE = "[AngAdm6_opioid_dose]";
    public static final String KEY_ANALGESIC_ADMIN_6_OPIOID_FREQUENCY = "[AngAdm6_opioid_freq]";
    public static final String KEY_ANALGESIC_ADMIN_6_OPIOID_ROUTE = "[AngAdm6_opioid_route]";
    public static final String KEY_ANALGESIC_ADMIN_6_OPIOID_ORDER = "[AngAdm6_opioid_order]";
    public static final String KEY_ANALGESIC_ADMIN_6_REFUSAL = "[AngAdm6_refused]";
    public static final String KEY_ANALGESIC_ADMIN_7_DATE = "[AngAdm7_date]";
    public static final String KEY_ANALGESIC_ADMIN_7_TIME = "[AngAdm7_time]";
    public static final String KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN = "[AngAdm7_acet]";
    public static final String KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_DOSE = "[AngAdm7_acet_dose]";
    public static final String KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_FREQUENCY = "[AngAdm7_acet_freq]";
    public static final String KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_ROUTE = "[AngAdm7_acet_route]";
    public static final String KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_ORDER = "[AngAdm7_acet_order]";
    public static final String KEY_ANALGESIC_ADMIN_7_NSAIDS = "[AngAdm7_NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_7_NSAIDS_DOSE = "[AngAdm7_NSAIDs_dose]";
    public static final String KEY_ANALGESIC_ADMIN_7_NSAIDS_FREQUENCY = "[AngAdm7_NSAIDs_freq]";
    public static final String KEY_ANALGESIC_ADMIN_7_NSAIDS_ROUTE = "[AngAdm7_NSAIDs_route]";
    public static final String KEY_ANALGESIC_ADMIN_7_NSAIDS_ORDER = "[AngAdm7_NSAIDs_order]";
    public static final String KEY_ANALGESIC_ADMIN_7_OPIOID = "[AngAdm7_opioid]";
    public static final String KEY_ANALGESIC_ADMIN_7_OPIOID_DOSE = "[AngAdm7_opioid_dose]";
    public static final String KEY_ANALGESIC_ADMIN_7_OPIOID_FREQUENCY = "[AngAdm7_opioid_freq]";
    public static final String KEY_ANALGESIC_ADMIN_7_OPIOID_ROUTE = "[AngAdm7_opioid_route]";
    public static final String KEY_ANALGESIC_ADMIN_7_OPIOID_ORDER = "[AngAdm7_opioid_order]";
    public static final String KEY_ANALGESIC_ADMIN_7_REFUSAL = "[AngAdm7_refused]";
    public static final String KEY_ANALGESIC_ADMIN_8_DATE = "[AngAdm8_date]";
    public static final String KEY_ANALGESIC_ADMIN_8_TIME = "[AngAdm8_time]";
    public static final String KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN = "[AngAdm8_acet]";
    public static final String KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_DOSE = "[AngAdm8_acet_dose]";
    public static final String KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_FREQUENCY = "[AngAdm8_acet_freq]";
    public static final String KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_ROUTE = "[AngAdm8_acet_route]";
    public static final String KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_ORDER = "[AngAdm8_acet_order]";
    public static final String KEY_ANALGESIC_ADMIN_8_NSAIDS = "[AngAdm8_NSAIDs]";
    public static final String KEY_ANALGESIC_ADMIN_8_NSAIDS_DOSE = "[AngAdm8_NSAIDs_dose]";
    public static final String KEY_ANALGESIC_ADMIN_8_NSAIDS_FREQUENCY = "[AngAdm8_NSAIDs_freq]";
    public static final String KEY_ANALGESIC_ADMIN_8_NSAIDS_ROUTE = "[AngAdm8_NSAIDs_route]";
    public static final String KEY_ANALGESIC_ADMIN_8_NSAIDS_ORDER = "[AngAdm8_NSAIDs_order]";
    public static final String KEY_ANALGESIC_ADMIN_8_OPIOID = "[AngAdm8_opioid]";
    public static final String KEY_ANALGESIC_ADMIN_8_OPIOID_DOSE = "[AngAdm8_opioid_dose]";
    public static final String KEY_ANALGESIC_ADMIN_8_OPIOID_FREQUENCY = "[AngAdm8_opioid_freq]";
    public static final String KEY_ANALGESIC_ADMIN_8_OPIOID_ROUTE = "[AngAdm8_opioid_route]";
    public static final String KEY_ANALGESIC_ADMIN_8_OPIOID_ORDER = "[AngAdm8_opioid_order]";
    public static final String KEY_ANALGESIC_ADMIN_8_REFUSAL = "[AngAdm8_refused]";

    public static final String KEY_NERVE_BLOCK_NUM = "[N_nerve_block]";
    public static final String KEY_NERVE_BLOCK_1_DATE = "[NerveBlock1_date]";
    public static final String KEY_NERVE_BLOCK_1_TIME = "[NerveBlock1_time]";
    public static final String KEY_NERVE_BLOCK_1_ORDER = "[NerveBlock1_order]";
    public static final String KEY_NERVE_BLOCK_1_TYPE = "[NerveBlock1_type]";
    public static final String KEY_NERVE_BLOCK_2_DATE = "[NerveBlock2_date]";
    public static final String KEY_NERVE_BLOCK_2_TIME = "[NerveBlock2_time]";
    public static final String KEY_NERVE_BLOCK_2_ORDER = "[NerveBlock2_order]";
    public static final String KEY_NERVE_BLOCK_2_TYPE = "[NerveBlock2_type]";
    public static final String KEY_NERVE_BLOCK_3_DATE = "[NerveBlock3_date]";
    public static final String KEY_NERVE_BLOCK_3_TIME = "[NerveBlock3_time]";
    public static final String KEY_NERVE_BLOCK_3_ORDER = "[NerveBlock3_order]";
    public static final String KEY_NERVE_BLOCK_3_TYPE = "[NerveBlock3_type]";
    public static final String KEY_NERVE_BLOCK_4_DATE = "[NerveBlock4_date]";
    public static final String KEY_NERVE_BLOCK_4_TIME = "[NerveBlock4_time]";
    public static final String KEY_NERVE_BLOCK_4_ORDER = "[NerveBlock4_order]";
    public static final String KEY_NERVE_BLOCK_4_TYPE = "[NerveBlock4_type]";
    public static final String KEY_NERVE_BLOCK_5_DATE = "[NerveBlock5_date]";
    public static final String KEY_NERVE_BLOCK_5_TIME = "[NerveBlock5_time]";
    public static final String KEY_NERVE_BLOCK_5_ORDER = "[NerveBlock5_order]";
    public static final String KEY_NERVE_BLOCK_5_TYPE = "[NerveBlock5_type]";
    public static final String KEY_NERVE_BLOCK_6_DATE = "[NerveBlock6_date]";
    public static final String KEY_NERVE_BLOCK_6_TIME = "[NerveBlock6_time]";
    public static final String KEY_NERVE_BLOCK_6_ORDER = "[NerveBlock6_order]";
    public static final String KEY_NERVE_BLOCK_6_TYPE = "[NerveBlock6_type]";
    public static final String KEY_NERVE_BLOCK_7_DATE = "[NerveBlock7_date]";
    public static final String KEY_NERVE_BLOCK_7_TIME = "[NerveBlock7_time]";
    public static final String KEY_NERVE_BLOCK_7_ORDER = "[NerveBlock7_order]";
    public static final String KEY_NERVE_BLOCK_7_TYPE = "[NerveBlock7_type]";
    public static final String KEY_NERVE_BLOCK_8_DATE = "[NerveBlock8_date]";
    public static final String KEY_NERVE_BLOCK_8_TIME = "[NerveBlock8_time]";
    public static final String KEY_NERVE_BLOCK_8_ORDER = "[NerveBlock8_order]";
    public static final String KEY_NERVE_BLOCK_8_TYPE = "[NerveBlock8_type]";

    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_NUM = "[N_alt_relief]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_1_DATE = "[AltRelief1_date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_1_TIME = "[AltRelief1_time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_1 = "[AltRelief1]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_1_OTHER = "[AltRelief1_other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_2_DATE = "[AltRelief2_date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_2_TIME = "[AltRelief2_time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_2 = "[AltRelief2]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_2_OTHER = "[AltRelief2_other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_3_DATE = "[AltRelief3_date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_3_TIME = "[AltRelief3_time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_3 = "[AltRelief3]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_3_OTHER = "[AltRelief3_other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_4_DATE = "[AltRelief4_date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_4_TIME = "[AltRelief4_time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_4 = "[AltRelief4]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_4_OTHER = "[AltRelief4_other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_5_DATE = "[AltRelief5_date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_5_TIME = "[AltRelief5_time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_5 = "[AltRelief5]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_5_OTHER = "[AltRelief5_other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_6_DATE = "[AltRelief6_date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_6_TIME = "[AltRelief6_time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_6 = "[AltRelief6]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_6_OTHER = "[AltRelief6_other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_7_DATE = "[AltRelief7_date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_7_TIME = "[AltRelief7_time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_7 = "[AltRelief7]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_7_OTHER = "[AltRelief7_other]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_8_DATE = "[AltRelief8_date]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_8_TIME = "[AltRelief8_time]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_8 = "[AltRelief8]";
    public static final String KEY_ALTERNATIVE_PAIN_RELIEF_8_OTHER = "[AltRelief8_other]";


    public static final String KEY_EVIDENCE_ALTERED_COGNITION = "[EvidAlteredCog]";
    public static final String KEY_SHORT_CAM_SCORE = "[ShortCAM_Score]";
    public static final String KEY_MENTAL_WORSENING = "[Worsening_Mental_Status]";

    public static final String KEY_DISCHARGE_DATE = "[DischargeDate]";
    public static final String KEY_DISCHARGE_TIME = "[DischargeTime]";
    public static final String KEY_DISCHARGE_DESTINATION = "[DischargeDestination]";
    public static final String KEY_DISCHARGE_TOOL = "[DischargeTool]";
    public static final String KEY_RETURN_ED = "[Return_ED_7_days]";
    public static final String KEY_RETURN_ED_UNCONTROLLED_PAIN = "[Return_ED_unc_pain]";
    public static final String KEY_RETURN_ED_REASON = "[Return_ED_reason]";



    // TODO: Setup your data fields here:
    public static List<DBColumn> dataMap;


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
        dataMap.add(new DBColumn(KEY_ROWID, "99999"));
        dataMap.add(new DBColumn(KEY_UNIQUEID, "xxxxxxxxxxxx"));

        dataMap.add(new DBColumn(KEY_EXTRACTIONPERIOD, "xxxxxx"));
        //dataMap.add(new DBColumn(KEY_SUBJECTID, ""));
        dataMap.add(new DBColumn(KEY_MEDICALRECORDNUMBER, "999999999999"));
        dataMap.add(new DBColumn(KEY_SITE, "xxx"));
        dataMap.add(new DBColumn(KEY_COMPLETED_BY, "xxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ARRIVALDATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ARRIVALTIME, "12:45"));

        dataMap.add(new DBColumn(KEY_DATEOFBIRTH, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_SEX, "Female"));

        dataMap.add(new DBColumn(KEY_FRACTURESITE_FOOT, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_ANKLE, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_TIBIA, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_FEMUR, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_HIP, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_PELVIS, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_VERTEBRA, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_RIB, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_HUMERUS, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_FOREARM, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_WRIST, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_HEAD, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_TOES, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_FINGERS, "xxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FRACTURESITE_OTHER, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));

        dataMap.add(new DBColumn(KEY_INJURY_MECHANISM, "xxxxxxxxxxxxxxxxxxxx"));

        dataMap.add(new DBColumn(KEY_TRIAGE_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_TRIAGE_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_CTAS, "xxxx"));
        dataMap.add(new DBColumn(KEY_GLASGOW, "xxxx"));
        dataMap.add(new DBColumn(KEY_PAINSCALE, "xxxx"));
        dataMap.add(new DBColumn(KEY_COLLECTIVEORDER, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_HISTORYOFALTEREDCOGNITION, "xxx"));
        dataMap.add(new DBColumn(KEY_HISTORYOFALTEREDCOGNITIONSPECIFY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTEREDCOGNITION, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));

        dataMap.add(new DBColumn(KEY_PHYSICIAN_EXAMINATION_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_PHYSICIAN_EXAMINATION_TIME, "13:45"));

        dataMap.add(new DBColumn(KEY_EVENTS_BOOL, "xxx"));
        dataMap.add(new DBColumn(KEY_EVENTS_NUM, "99"));
        dataMap.add(new DBColumn(KEY_EVENTS_ORDER, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_FIRST_EVENT, "999.99"));

        //dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENTS_BOOL, ""));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_NUM, "9"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_1_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_1_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_1_SCORE, "xxxx"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_2_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_2_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_2_SCORE, "xxxx"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_3_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_3_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_3_SCORE, "xxxx"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_4_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_4_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_4_SCORE, "xxxx"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_5_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_5_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_5_SCORE, "xxxx"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_6_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_6_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_6_SCORE, "xxxx"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_7_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_7_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_7_SCORE, "xxxx"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_8_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_8_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_PAIN_ASSESSMENT_8_SCORE, "xxxx"));

        //dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_BOOL, ""));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_NUM, "9"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_1_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_1_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_1_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_1_MODE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_2_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_2_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_2_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_2_MODE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_3_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_3_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_3_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_3_MODE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_4_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_4_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_4_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_4_MODE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_5_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_5_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_5_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_5_MODE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_6_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_6_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_6_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_6_MODE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_7_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_7_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_7_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_7_MODE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_8_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_8_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_8_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_PRES_8_MODE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));

        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_NUM, "9"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_ACETAMINOPHEN_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_NSAIDS, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_NSAIDS_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_NSAIDS_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_NSAIDS_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_NSAIDS_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_OPIOID, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_OPIOID_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_OPIOID_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_OPIOID_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_OPIOID_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_1_REFUSAL, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_ACETAMINOPHEN_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_NSAIDS, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_NSAIDS_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_NSAIDS_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_NSAIDS_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_NSAIDS_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_OPIOID, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_OPIOID_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_OPIOID_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_OPIOID_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_OPIOID_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_2_REFUSAL, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_ACETAMINOPHEN_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_NSAIDS, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_NSAIDS_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_NSAIDS_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_NSAIDS_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_NSAIDS_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_OPIOID, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_OPIOID_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_OPIOID_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_OPIOID_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_OPIOID_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_3_REFUSAL, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_ACETAMINOPHEN_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_NSAIDS, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_NSAIDS_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_NSAIDS_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_NSAIDS_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_NSAIDS_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_OPIOID, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_OPIOID_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_OPIOID_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_OPIOID_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_OPIOID_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_4_REFUSAL, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_ACETAMINOPHEN_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_NSAIDS, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_NSAIDS_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_NSAIDS_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_NSAIDS_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_NSAIDS_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_OPIOID, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_OPIOID_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_OPIOID_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_OPIOID_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_OPIOID_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_5_REFUSAL, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_ACETAMINOPHEN_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_NSAIDS, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_NSAIDS_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_NSAIDS_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_NSAIDS_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_NSAIDS_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_OPIOID, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_OPIOID_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_OPIOID_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_OPIOID_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_OPIOID_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_6_REFUSAL, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_ACETAMINOPHEN_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_NSAIDS, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_NSAIDS_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_NSAIDS_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_NSAIDS_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_NSAIDS_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_OPIOID, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_OPIOID_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_OPIOID_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_OPIOID_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_OPIOID_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_7_REFUSAL, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_ACETAMINOPHEN_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_NSAIDS, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_NSAIDS_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_NSAIDS_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_NSAIDS_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_NSAIDS_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_OPIOID, "xxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_OPIOID_DOSE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_OPIOID_FREQUENCY, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_OPIOID_ROUTE, "xx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_OPIOID_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_ANALGESIC_ADMIN_8_REFUSAL, "xxx"));

        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_NUM, "9"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_1_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_1_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_1_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_1_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_2_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_2_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_2_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_2_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_3_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_3_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_3_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_3_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_4_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_4_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_4_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_4_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_5_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_5_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_5_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_5_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_6_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_6_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_6_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_6_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_7_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_7_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_7_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_7_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_8_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_8_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_8_ORDER, "xxxxxxx"));
        dataMap.add(new DBColumn(KEY_NERVE_BLOCK_8_TYPE, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));

        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_NUM, "9"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_1_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_1_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_1, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_1_OTHER, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_2_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_2_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_2, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_2_OTHER, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_3_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_3_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_3, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_3_OTHER, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_4_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_4_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_4, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_4_OTHER, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_5_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_5_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_5, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_5_OTHER, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_6_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_6_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_6, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_6_OTHER, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_7_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_7_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_7, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_7_OTHER, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_8_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_8_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_8, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_ALTERNATIVE_PAIN_RELIEF_8_OTHER, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));

        dataMap.add(new DBColumn(KEY_EVIDENCE_ALTERED_COGNITION, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_SHORT_CAM_SCORE, "xxxx"));
        dataMap.add(new DBColumn(KEY_MENTAL_WORSENING, "xxxxxxxxxxxxxx"));

        dataMap.add(new DBColumn(KEY_DISCHARGE_DATE, "2016-12-31"));
        dataMap.add(new DBColumn(KEY_DISCHARGE_TIME, "13:45"));
        dataMap.add(new DBColumn(KEY_DISCHARGE_DESTINATION, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        dataMap.add(new DBColumn(KEY_DISCHARGE_TOOL, "xxx"));
        dataMap.add(new DBColumn(KEY_RETURN_ED, "xxx"));
        dataMap.add(new DBColumn(KEY_RETURN_ED_UNCONTROLLED_PAIN, "xxx"));
        dataMap.add(new DBColumn(KEY_RETURN_ED_REASON, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));

        generateCreateDataString();

        db = myDBHelper.getWritableDatabase();

        return this;
    }

    private void generateCreateDataString(){
        DATA_CREATE_SQL = "create table " + DATA_TABLE + " (" + KEY_ROWID + " integer primary key autoincrement, ";

        int index = 0;
        for (DBColumn column : dataMap){
            String key = column.header;
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

            for (DBColumn column : dataMap){
                String key = column.header;
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
