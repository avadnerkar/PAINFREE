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
public class FragmentF extends Fragment {

    public static FragmentListAdapter adapter;
    private static ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_f, container, false);

        listView = (ListView) v.findViewById(R.id.list_f);

        ArrayList<FragmentItem> items = new ArrayList<>();
        items.add(new FragmentItem(getString(R.string.evidence_altered_cognition), FragmentItem.CellType.CHECKBOX, new String[]{getString(R.string.confusion), getString(R.string.agitation), getString(R.string.disorientation)}, null, DBAdapter.KEY_EVIDENCE_ALTERED_COGNITION));
        items.get(items.size()-1).extraOptions = new String[]{getString(R.string.none)};
        items.get(items.size()-1).hasNone = true;
        items.get(items.size()-1).hasOther = true;
        items.add(new FragmentItem(getString(R.string.short_cam_score), FragmentItem.CellType.SPINNER, new String[]{"None", "0", "1", "2", "3", "4", "5", "6", "7"}, null, DBAdapter.KEY_SHORT_CAM_SCORE));
        items.add(new FragmentItem(getString(R.string.worsening_of_mental_status), FragmentItem.CellType.SPINNER, new String[]{"", getString(R.string.yes), getString(R.string.no), getString(R.string.not_applicable)}, null, DBAdapter.KEY_MENTAL_WORSENING));
        adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        return v;
    }

    public static FragmentF newInstance() {

        return new FragmentF();
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
