package physiotherapy.mcgill.com.painfree.MainGroup;

/**
 * Created by Abhishek Vadnerkar on 16-02-28.
 */
public class FragmentItem {

    public String dbKey;
    public String title;
    public enum CellType {
        RADIO, NUMERIC, DATEPICKER, TIMEPICKER, FRACTURESITE, TEXT, SPINNER, SPINNER_WITH_OTHER, CHECKBOX, PAIN_ASSESSMENTS, ANALGESIC_PRESCRIPTION, ANALGESIC_ADMINISTRATION
    }
    public CellType cellType;
    public enum Group {
        A, B, C, D, E, F
    }
    public Group group;
    public String[] uiOptions;
    public String[] databaseOptions;
    public String[] extraOptions;
    public boolean isMandatory;


    public FragmentItem(String title, CellType cellType, String[] uiOptions, String[] databaseOptions, String dbKey){
        this.title = title;
        this.cellType = cellType;
        this.uiOptions = uiOptions;
        this.databaseOptions = databaseOptions;
        this.dbKey = dbKey;
        this.isMandatory = false;
    }

}
