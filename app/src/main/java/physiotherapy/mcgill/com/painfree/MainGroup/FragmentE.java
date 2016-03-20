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
public class FragmentE extends Fragment {

    public static FragmentListAdapter adapter;
    private static ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_e, container, false);

        listView = (ListView) v.findViewById(R.id.list_e);

        ArrayList<FragmentItem> items = new ArrayList<>();
        items.add(new FragmentItem(getString(R.string.physician_examination_date), FragmentItem.CellType.DATEPICKER, new String[]{null, "2016-02-01", "2019-12-31"}, null, DBAdapter.KEY_PHYSICIAN_EXAMINATION_DATE));
        items.add(new FragmentItem(getString(R.string.physician_examination_time), FragmentItem.CellType.TIMEPICKER, null, null, DBAdapter.KEY_PHYSICIAN_EXAMINATION_TIME));
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
}
