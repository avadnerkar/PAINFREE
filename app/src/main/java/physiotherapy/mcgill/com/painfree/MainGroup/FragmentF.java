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

    public static FragmentListAdapter adapter;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_f, container, false);

        listView = (ListView) v.findViewById(R.id.list_f);

        ArrayList<FragmentItem> items = new ArrayList<>();
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
        if (MainActivity.currentPatientId == -1){
            listView.setVisibility(View.INVISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
        }
    }
}
