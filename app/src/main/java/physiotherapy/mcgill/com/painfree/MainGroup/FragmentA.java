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
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static FragmentListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_a, container, false);

        ListView listView = (ListView) v.findViewById(R.id.list_a);

        ArrayList<FragmentItem> items = new ArrayList<>();

        items.add(new FragmentItem(getString(R.string.date_of_birth), FragmentItem.CellType.DATEPICKER, null, null, DBAdapter.KEY_DATEOFBIRTH));
        items.add(new FragmentItem(getString(R.string.sex), FragmentItem.CellType.RADIO, new String[]{getString(R.string.female), getString(R.string.male)}, null, DBAdapter.KEY_SEX));

        adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        return v;
    }

    public static FragmentA newInstance(int sectionNumber) {

        FragmentA f = new FragmentA();
        Bundle b = new Bundle();

        b.putInt(ARG_SECTION_NUMBER, sectionNumber);
        f.setArguments(b);

        return f;
    }
}
