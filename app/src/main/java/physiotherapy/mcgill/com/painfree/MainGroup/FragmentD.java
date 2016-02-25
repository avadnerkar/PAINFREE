package physiotherapy.mcgill.com.painfree.MainGroup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import physiotherapy.mcgill.com.painfree.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentD extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    //private static ArrayList<CellItem> items;
    //private static CellListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_a, container, false);

        ListView listView = (ListView) v.findViewById(R.id.list_a);

        //items = new ArrayList<>();

        //adapter = new CellListAdapter(getActivity(), items);

        //listView.setAdapter(nurseAdapter);


        return v;
    }

    public static FragmentD newInstance(int sectionNumber) {

        FragmentD f = new FragmentD();
        Bundle b = new Bundle();

        b.putInt(ARG_SECTION_NUMBER, sectionNumber);
        f.setArguments(b);

        return f;
    }
}
