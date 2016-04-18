package physiotherapy.mcgill.com.painfree.MainGroup;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentE extends Fragment {

    public static FragmentListAdapter adapter;
    private static ListView listView;
    private static ArrayList<FragmentItem> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_e, container, false);

        listView = (ListView) v.findViewById(R.id.list_e);

        items = new ArrayList<>();
        items.add(new FragmentItem(getString(R.string.physician_examination_date), FragmentItem.CellType.DATEPICKER, new String[]{null, "2016-02-01", "2019-12-31"}, null, DBAdapter.KEY_PHYSICIAN_EXAMINATION_DATE));
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(getString(R.string.physician_examination_time), FragmentItem.CellType.TIMEPICKER, null, null, DBAdapter.KEY_PHYSICIAN_EXAMINATION_TIME));
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(null, FragmentItem.CellType.PAIN_ASSESSMENTS, null, null, null));
        items.add(new FragmentItem(null, FragmentItem.CellType.ANALGESIC_PRESCRIPTION, null, null, null));
        adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        return v;
    }

    public static FragmentE newInstance() {

        return new FragmentE();
    }

    @Override
    public void onResume() {
        super.onResume();
        setFragmentVisibility();
    }

    public static void setFragmentVisibility(){
        adapter.notifyDataSetChanged();
        if (MainActivity.currentPatientId == -1){
            listView.setVisibility(View.INVISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
        }
    }


    public static ArrayList<String> unfilledMandatoryFields() {

        ArrayList<String> unfilledTitles = new ArrayList<>();

        if (MainActivity.currentPatientId != -1) {

            Cursor cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_PAIN_ASSESSMENT_NUM, DBAdapter.KEY_PAIN_ASSESSMENTS_BOOL});

            if (cursor.moveToFirst()){
                if (cursor.getString(1) == null){
                    int numAssessments = cursor.getInt(0);
                    if (numAssessments == 0){
                        unfilledTitles.add("Pain assessments");
                    } else {
                        String[] keys = Arrays.copyOfRange(PainAssessments.keys, (numAssessments-1)*3+1, numAssessments*3+1);
                        Cursor cursor1 = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);
                        for (int i=0; i<3; i++){
                            if (cursor1.getString(i) == null || cursor1.getString(i).equals("None")){
                                unfilledTitles.add("Pain assessments");
                                break;
                            }
                        }
                        cursor1.close();
                    }
                }
            }

            cursor.close();


            cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_ANALGESIC_PRES_NUM, DBAdapter.KEY_ANALGESIC_PRES_BOOL});

            if (cursor.moveToFirst()){
                if (cursor.getString(1) == null){
                    int numAssessments = cursor.getInt(0);
                    if (numAssessments == 0){
                        unfilledTitles.add("Analgesic prescription");
                    } else {
                        String[] keys = Arrays.copyOfRange(AnalgesicPrescription.keys, (numAssessments-1)*28+1, numAssessments*28+1);
                        boolean[] mandatoryKeys = Arrays.copyOfRange(AnalgesicPrescription.manadatoryKeys, (numAssessments-1)*28+1, numAssessments*28+1);
                        Cursor cursor1 = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);
                        for (int i=0; i<28; i++){
                            if (mandatoryKeys[i] && (cursor1.getString(i) == null || cursor1.getString(i).equals(""))){
                                unfilledTitles.add("Analgesic prescription");
                                break;
                            }
                        }
                        cursor1.close();
                    }
                }
            }

            cursor.close();

        }

        return unfilledTitles;
    }
}
