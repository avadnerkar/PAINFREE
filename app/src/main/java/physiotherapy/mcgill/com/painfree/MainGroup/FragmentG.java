package physiotherapy.mcgill.com.painfree.MainGroup;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_g, container, false);

        listView = (ListView) v.findViewById(R.id.list_g);

        ArrayList<FragmentItem> items = new ArrayList<>();
        items.add(new FragmentItem(getString(R.string.discharge_date), FragmentItem.CellType.DATEPICKER, new String[]{null, "2016-02-01", "2019-12-31"}, null, DBAdapter.KEY_DISCHARGE_DATE));
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(getString(R.string.discharge_time), FragmentItem.CellType.TIMEPICKER, null, null, DBAdapter.KEY_DISCHARGE_TIME));
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(getString(R.string.discharge_destination), FragmentItem.CellType.SPINNER_WITH_OTHER, new String[]{"", getString(R.string.return_home), getString(R.string.admitting), getString(R.string.transfer), getString(R.string.lwbs), getString(R.string.death), getString(R.string.not_available)}, null, DBAdapter.KEY_DISCHARGE_DESTINATION));
        items.add(new FragmentItem(getString(R.string.discharge_tool_given), FragmentItem.CellType.RADIO, new String[]{getString(R.string.yes), getString(R.string.no)}, null, DBAdapter.KEY_DISCHARGE_TOOL));

        items.add(new FragmentItem(getString(R.string.return_to_ed), FragmentItem.CellType.RADIO, new String[]{getString(R.string.yes), getString(R.string.no), getString(R.string.no_information)}, null, DBAdapter.KEY_RETURN_ED));
        items.add(new FragmentItem(getString(R.string.if_yes_why), FragmentItem.CellType.SPINNER_WITH_OTHER, new String[]{"", getString(R.string.uncontrolled_pain), getString(R.string.scheduled_cast_check), getString(R.string.issues_with_cast), getString(R.string.swelling), getString(R.string.worsening_of_health_status), getString(R.string.new_fracture)}, null, DBAdapter.KEY_RETURN_ED_REASON));

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


}
