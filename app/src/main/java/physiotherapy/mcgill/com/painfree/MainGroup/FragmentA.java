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
public class FragmentA extends Fragment {

    public static FragmentListAdapter adapter;
    private static ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_a, container, false);

        listView = (ListView) v.findViewById(R.id.list_a);

        ArrayList<FragmentItem> items = new ArrayList<>();
        items.add(new FragmentItem(getString(R.string.site), FragmentItem.CellType.SPINNER, new String[]{"", getString(R.string.jgh), getString(R.string.mgh), getString(R.string.lgh), getString(R.string.rvh), getString(R.string.hsc), getString(R.string.smh), getString(R.string.hdv)}, null, DBAdapter.KEY_SITE));
        items.add(new FragmentItem(getString(R.string.completed_by), FragmentItem.CellType.TEXT, null, null, DBAdapter.KEY_COMPLETED_BY));
        items.add(new FragmentItem(getString(R.string.date), FragmentItem.CellType.DATEPICKER, new String[]{null, "2016-02-01", "2019-12-31"}, null, DBAdapter.KEY_DATE));
        items.add(new FragmentItem(getString(R.string.subject_id), FragmentItem.CellType.NUMERIC, null, null, DBAdapter.KEY_SUBJECTID));
        items.add(new FragmentItem(getString(R.string.date_of_arrival), FragmentItem.CellType.DATEPICKER, null, null, DBAdapter.KEY_ARRIVALDATE));
        items.add(new FragmentItem(getString(R.string.time_of_arrival), FragmentItem.CellType.TIMEPICKER, null, null, DBAdapter.KEY_ARRIVALTIME));
        adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        return v;
    }

    public static FragmentA newInstance() {

        return new FragmentA();
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
