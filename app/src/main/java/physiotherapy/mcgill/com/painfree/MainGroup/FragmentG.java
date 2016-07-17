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
public class FragmentG extends Fragment {


    public static FragmentListAdapter adapter;
    private static ListView listView;
    private static ArrayList<FragmentItem> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_g, container, false);

        listView = (ListView) v.findViewById(R.id.list_g);

        items = new ArrayList<>();
        items.add(new FragmentItem(getString(R.string.discharge_date), FragmentItem.CellType.DATEPICKER, new String[]{null, "2016-01-01", "2019-12-31"}, null, DBAdapter.KEY_DISCHARGE_DATE));
        items.get(items.size()-1).isMandatory = true;
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(getString(R.string.discharge_time), FragmentItem.CellType.TIMEPICKER, null, null, DBAdapter.KEY_DISCHARGE_TIME));
        items.get(items.size()-1).isMandatory = true;
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(getString(R.string.discharge_destination), FragmentItem.CellType.SPINNER, new String[]{"", getString(R.string.return_home), getString(R.string.long_term_care_facility), getString(R.string.admitting), getString(R.string.transfer), getString(R.string.lwbs), getString(R.string.death), getString(R.string.not_specified)}, null, DBAdapter.KEY_DISCHARGE_DESTINATION));
        items.get(items.size()-1).isMandatory = true;
        items.add(new FragmentItem(getString(R.string.discharge_tool_given), FragmentItem.CellType.RADIO, new String[]{getString(R.string.yes), getString(R.string.no), getString(R.string.not_specified)}, null, DBAdapter.KEY_DISCHARGE_TOOL));
        items.add(new FragmentItem(getString(R.string.discharge_prescription_given), FragmentItem.CellType.RADIO, new String[]{getString(R.string.yes), getString(R.string.no), getString(R.string.not_specified)}, null, DBAdapter.KEY_DISCHARGE_PRESCRIPTION));
        items.get(items.size()-1).isMandatory = true;

        items.add(new FragmentItem(getString(R.string.return_to_ed), FragmentItem.CellType.RETURN_TO_ED, new String[]{getString(R.string.yes), getString(R.string.no)}, null, DBAdapter.KEY_RETURN_ED));
        items.get(items.size()-1).isMandatory = true;
        items.get(items.size()-1).extraOptions = new String[]{DBAdapter.KEY_RETURN_ED_REASON};
        //items.add(new FragmentItem(getString(R.string.if_yes_why), FragmentItem.CellType.SPINNER_WITH_OTHER, new String[]{"", getString(R.string.uncontrolled_pain), getString(R.string.scheduled_cast_check), getString(R.string.issues_with_cast), getString(R.string.swelling), getString(R.string.worsening_of_health_status), getString(R.string.new_fracture)}, null, DBAdapter.KEY_RETURN_ED_REASON));

        adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        return v;
    }

    public static FragmentG newInstance() {

        return new FragmentG();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
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
