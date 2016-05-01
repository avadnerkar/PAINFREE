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
        items.add(new FragmentItem(getString(R.string.physician_examination_date), FragmentItem.CellType.DATEPICKER, new String[]{null, "2016-01-01", "2019-12-31"}, null, DBAdapter.KEY_PHYSICIAN_EXAMINATION_DATE));
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(getString(R.string.physician_examination_time), FragmentItem.CellType.TIMEPICKER, null, null, DBAdapter.KEY_PHYSICIAN_EXAMINATION_TIME));
        items.get(items.size()-1).extraOptions = new String[]{"optional none"};
        items.add(new FragmentItem(null, FragmentItem.CellType.ED_EVENTS, null, null, null));
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

            Cursor cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_EVENTS_NUM, DBAdapter.KEY_EVENTS_BOOL, DBAdapter.KEY_EVENTS_ORDER});

            if (cursor.moveToFirst()){
                if (cursor.getString(1) == null){
                    int numAssessments = cursor.getInt(0);
                    if (numAssessments == 0){
                        unfilledTitles.add("ED Events");
                    } else {

                        final ArrayList<Integer> orderOfEvents = new ArrayList<>();
                        final String orderString = cursor.getString(2);
                        if (orderString != null) {
                            for (int i = 0; i < orderString.length(); i++) {
                                Integer type = Character.getNumericValue(orderString.charAt(i));
                                orderOfEvents.add(type);
                            }
                        }

                        boolean complete = true;
                        int lastEventType = orderOfEvents.get(orderOfEvents.size()-1);
                        switch (lastEventType){
                            case 0:{
                                complete = PainAssessments.canAdd();
                                break;
                            }
                            case 1:{
                                complete = AnalgesicPrescription.canAdd();
                                break;
                            }
                            case 2:{
                                complete = AnalgesicAdministration.canAdd();
                                break;
                            }
                        }

                        if (!complete){
                            unfilledTitles.add("ED Events");
                        }
                    }
                }
            }

            cursor.close();
        }

        return unfilledTitles;
    }
}
