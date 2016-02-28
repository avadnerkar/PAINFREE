package physiotherapy.mcgill.com.painfree.MainGroup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import physiotherapy.mcgill.com.painfree.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentC extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static FragmentListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_c, container, false);

        ListView listView = (ListView) v.findViewById(R.id.list_c);

        ArrayList<FragmentItem> items = new ArrayList<>();
        adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        return v;
    }

    public static FragmentC newInstance(int sectionNumber) {

        FragmentC f = new FragmentC();
        Bundle b = new Bundle();

        b.putInt(ARG_SECTION_NUMBER, sectionNumber);
        f.setArguments(b);

        return f;
    }
}
