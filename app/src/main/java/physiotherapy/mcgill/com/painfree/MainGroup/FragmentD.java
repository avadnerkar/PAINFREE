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
public class FragmentD extends Fragment {

    public static FragmentListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_d, container, false);

        ListView listView = (ListView) v.findViewById(R.id.list_d);

        ArrayList<FragmentItem> items = new ArrayList<>();
        adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);


        return v;
    }

    public static FragmentD newInstance() {

        return new FragmentD();
    }
}
