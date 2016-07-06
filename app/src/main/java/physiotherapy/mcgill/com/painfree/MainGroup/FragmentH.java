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
 * Created by avadn on 5/07/2016.
 */
public class FragmentH extends Fragment {

    public static FragmentListAdapter adapter;
    private static ListView listView;
    private static ArrayList<FragmentItem> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_h, container, false);

        listView = (ListView) v.findViewById(R.id.list_h);

        items = new ArrayList<>();
        items.add(new FragmentItem(getString(R.string.notes_and_comments), FragmentItem.CellType.TEXT, null, null, DBAdapter.KEY_NOTES));
        adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        return v;
    }

    public static FragmentH newInstance() {

        return new FragmentH();
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
