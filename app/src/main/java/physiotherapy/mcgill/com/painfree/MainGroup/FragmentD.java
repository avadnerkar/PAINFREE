package physiotherapy.mcgill.com.painfree.MainGroup;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentD extends Fragment {

    public static FragmentListAdapter adapter;
    private static ListView listView;
    private static ArrayList<FragmentItem> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_d, container, false);

        listView = (ListView) v.findViewById(R.id.list_d);

        items = new ArrayList<>();
        items.add(new FragmentItem(getString(R.string.triage_date), FragmentItem.CellType.DATEPICKER, new String[]{null, "2016-01-01", "2019-12-31"}, null, DBAdapter.KEY_TRIAGE_DATE));
        items.get(items.size()-1).isMandatory = true;
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(getString(R.string.triage_time), FragmentItem.CellType.TIMEPICKER, null, null, DBAdapter.KEY_TRIAGE_TIME));
        items.get(items.size()-1).isMandatory = true;
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(getString(R.string.ctas_priority), FragmentItem.CellType.SPINNER, new String[]{"", getString(R.string.none), getString(R.string.ctas_1), getString(R.string.ctas_2), getString(R.string.ctas_3), getString(R.string.ctas_4), getString(R.string.ctas_5)}, null, DBAdapter.KEY_CTAS));
        items.get(items.size()-1).isMandatory = true;
        items.add(new FragmentItem(getString(R.string.glasgow), FragmentItem.CellType.SPINNER, new String[]{"", "None", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}, null, DBAdapter.KEY_GLASGOW));
        items.get(items.size()-1).isMandatory = true;
        items.add(new FragmentItem(getString(R.string.pain_scale_score), FragmentItem.CellType.SPINNER, new String[]{"", "None", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, null, DBAdapter.KEY_PAINSCALE));
        items.get(items.size()-1).isMandatory = true;
        items.add(new FragmentItem(getString(R.string.collective_order), FragmentItem.CellType.CHECKBOX, new String[]{getString(R.string.acetaminophen), getString(R.string.nsaids), getString(R.string.opioid)}, null, DBAdapter.KEY_COLLECTIVEORDER));
        items.get(items.size()-1).isMandatory = true;
        items.get(items.size()-1).extraOptions = new String[]{getString(R.string.not_used), getString(R.string.not_applicable)};
        items.get(items.size()-1).hasNone = true;
        items.get(items.size()-1).hasSecondNone = true;
        items.add(new FragmentItem(getString(R.string.past_diagnosis_of_altered_cognition), FragmentItem.CellType.RADIO_WITH_SPECIFY, new String[]{getString(R.string.yes), getString(R.string.no)}, null, DBAdapter.KEY_HISTORYOFALTEREDCOGNITION));
        items.get(items.size()-1).isMandatory = true;
        items.get(items.size()-1).extraOptions = new String[]{DBAdapter.KEY_HISTORYOFALTEREDCOGNITIONSPECIFY};
        //items.add(new FragmentItem(getString(R.string.altered_cognition), FragmentItem.CellType.RADIO, new String[]{getString(R.string.positive), getString(R.string.negative), getString(R.string.not_specified)}, null, DBAdapter.KEY_ALTEREDCOGNITION));
        items.add(new FragmentItem(getString(R.string.altered_cognition), FragmentItem.CellType.CHECKBOX, new String[]{getString(R.string.agitation), getString(R.string.disorientation)}, null, DBAdapter.KEY_ALTEREDCOGNITION));
        items.get(items.size()-1).extraOptions = new String[]{getString(R.string.none), getString(R.string.not_specified)};
        items.get(items.size()-1).hasNone = true;
        items.get(items.size()-1).hasSecondNone = true;

        items.get(items.size()-1).isMandatory = true;
        //items.get(items.size()-1).extraOptions = new String[]{getString(R.string.none)};
        //items.get(items.size()-1).hasNone = true;
        //items.get(items.size()-1).hasOther = true;

        adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);


        return v;
    }

    public static FragmentD newInstance() {

        return new FragmentD();
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
            for (FragmentItem item : items) {
                if (item.isMandatory) {
                    Cursor cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, item.dbKey);
                    boolean isFilled = false;
                    if (cursor.moveToFirst()) {
                        String value = cursor.getString(0);
                        if (value != null && !value.equals("")) {
                            isFilled = true;
                        }
                    }

                    if (!isFilled) {
                        String title = item.title;
                        title = title.replace(":", "");
                        unfilledTitles.add(title);
                    }

                    cursor.close();
                }
            }
        }

        return unfilledTitles;
    }
}
