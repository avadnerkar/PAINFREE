package physiotherapy.mcgill.com.painfree.MainGroup;

/**
 * Created by Abhishek Vadnerkar on 16-02-24.
 */
public class FormItem {

    public String dbKey;
    public String title;
    public enum CellType {
        RADIO, TEXT
    }
    public CellType cellType;
    public String[] options;
    public Object misc;


    public FormItem(String title, CellType cellType, String[] options, String dbKey){
        this.title = title;
        this.cellType = cellType;
        this.options = options;
        this.dbKey = dbKey;
    }

}
