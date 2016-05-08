package physiotherapy.mcgill.com.painfree.MainGroup;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentF extends Fragment {

    public static FragmentListAdapter adapter;
    private static ListView listView;
    private static ArrayList<FragmentItem> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_f, container, false);

        listView = (ListView) v.findViewById(R.id.list_f);

        items = new ArrayList<>();
        //items.add(new FragmentItem(getString(R.string.evidence_altered_cognition), FragmentItem.CellType.RADIO, new String[]{getString(R.string.positive), getString(R.string.negative), getString(R.string.not_specified)}, null, DBAdapter.KEY_EVIDENCE_ALTERED_COGNITION));
        items.add(new FragmentItem(getString(R.string.evidence_altered_cognition), FragmentItem.CellType.CHECKBOX, new String[]{getString(R.string.confusion), getString(R.string.agitation), getString(R.string.disorientation)}, null, DBAdapter.KEY_EVIDENCE_ALTERED_COGNITION));
        items.get(items.size()-1).extraOptions = new String[]{getString(R.string.none)};
        items.get(items.size()-1).hasNone = true;
        items.get(items.size()-1).hasOther = true;
        items.get(items.size()-1).isMandatory = true;
        items.add(new FragmentItem(getString(R.string.short_cam_score), FragmentItem.CellType.RADIO, new String[]{getString(R.string.positive), getString(R.string.negative), getString(R.string.not_specified)}, null, DBAdapter.KEY_SHORT_CAM_SCORE));
        items.get(items.size()-1).isMandatory = true;
        items.add(new FragmentItem(getString(R.string.worsening_of_mental_status), FragmentItem.CellType.SPINNER, new String[]{"", getString(R.string.yes), getString(R.string.no), getString(R.string.not_applicable)}, null, DBAdapter.KEY_MENTAL_WORSENING));
        items.get(items.size()-1).isMandatory = true;
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
