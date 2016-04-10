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
public class FragmentD extends Fragment {

    public static FragmentListAdapter adapter;
    private static ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_d, container, false);

        listView = (ListView) v.findViewById(R.id.list_d);

        ArrayList<FragmentItem> items = new ArrayList<>();
        items.add(new FragmentItem(getString(R.string.triage_date), FragmentItem.CellType.DATEPICKER, new String[]{null, "2016-02-01", "2019-12-31"}, null, DBAdapter.KEY_ARRIVALDATE));
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(getString(R.string.triage_time), FragmentItem.CellType.TIMEPICKER, null, null, DBAdapter.KEY_ARRIVALTIME));
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(getString(R.string.ctas_priority), FragmentItem.CellType.SPINNER, new String[]{getString(R.string.none), getString(R.string.ctas_1), getString(R.string.ctas_2), getString(R.string.ctas_3), getString(R.string.ctas_4), getString(R.string.ctas_5)}, null, DBAdapter.KEY_CTAS));
        items.add(new FragmentItem(getString(R.string.glasgow), FragmentItem.CellType.SPINNER, new String[]{"None", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}, null, DBAdapter.KEY_GLASGOW));
        items.add(new FragmentItem(getString(R.string.pain_scale_score), FragmentItem.CellType.SPINNER, new String[]{"None", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, null, DBAdapter.KEY_PAINSCALE));
        items.add(new FragmentItem(getString(R.string.collective_order), FragmentItem.CellType.CHECKBOX, new String[]{getString(R.string.acetaminophen), getString(R.string.nsaids), getString(R.string.opioid)}, null, DBAdapter.KEY_COLLECTIVEORDER));
        items.get(items.size()-1).extraOptions = new String[]{getString(R.string.not_applicable)};
        items.get(items.size()-1).hasNone = true;
        items.add(new FragmentItem(getString(R.string.history_of_dementia), FragmentItem.CellType.RADIO, new String[]{getString(R.string.yes), getString(R.string.no)}, null, DBAdapter.KEY_HISTORYOFDEMENTIA));
        items.add(new FragmentItem(getString(R.string.altered_cognition), FragmentItem.CellType.CHECKBOX, new String[]{getString(R.string.confusion), getString(R.string.agitation), getString(R.string.disorientation)}, null, DBAdapter.KEY_ALTEREDCOGNITION));

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
}
