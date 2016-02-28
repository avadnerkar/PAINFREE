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
public class FragmentF extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_f, container, false);

        ListView listView = (ListView) v.findViewById(R.id.list_f);

        ArrayList<FragmentItem> items = new ArrayList<>();
        FragmentListAdapter adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        return v;
    }

    public static FragmentF newInstance(int sectionNumber) {

        FragmentF f = new FragmentF();
        Bundle b = new Bundle();

        b.putInt(ARG_SECTION_NUMBER, sectionNumber);
        f.setArguments(b);

        return f;
    }
}
