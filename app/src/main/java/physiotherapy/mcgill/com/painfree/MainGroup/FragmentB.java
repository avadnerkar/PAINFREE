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
public class FragmentB extends Fragment {

    public static FragmentListAdapter adapter;
    private static ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_b, container, false);

        listView = (ListView) v.findViewById(R.id.list_b);

        ArrayList<FragmentItem> items = new ArrayList<>();

        items.add(new FragmentItem(getString(R.string.date_of_birth), FragmentItem.CellType.DATEPICKER, null, null, DBAdapter.KEY_DATEOFBIRTH));
        items.add(new FragmentItem(getString(R.string.sex), FragmentItem.CellType.RADIO, new String[]{getString(R.string.female), getString(R.string.male)}, null, DBAdapter.KEY_SEX));

        adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        return v;
    }

    public static FragmentB newInstance() {

        return new FragmentB();
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
