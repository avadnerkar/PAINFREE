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
public class FragmentC extends Fragment {

    public static FragmentListAdapter adapter;
    private static ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_c, container, false);

        listView = (ListView) v.findViewById(R.id.list_c);

        ArrayList<FragmentItem> items = new ArrayList<>();

        items.add(new FragmentItem(null, FragmentItem.CellType.FRACTURESITE, null, null, null));
        items.add(new FragmentItem(getString(R.string.mechanism_of_injury), FragmentItem.CellType.SPINNER_WITH_OTHER, new String[]{getString(R.string.none), getString(R.string.mechanism1), getString(R.string.mechanism2), getString(R.string.mechanism3), getString(R.string.mechanism4), getString(R.string.mechanism5), getString(R.string.mechanism6), getString(R.string.mechanism7), getString(R.string.mechanism8), getString(R.string.mechanism9), getString(R.string.mechanism10), getString(R.string.mechanism11), getString(R.string.mechanism12), getString(R.string.mechanism13)}, new String[]{getString(R.string.none), getString(R.string.mechanismCode1), getString(R.string.mechanismCode2), getString(R.string.mechanismCode3), getString(R.string.mechanismCode4), getString(R.string.mechanismCode5), getString(R.string.mechanismCode6), getString(R.string.mechanismCode7), getString(R.string.mechanismCode8), getString(R.string.mechanismCode9), getString(R.string.mechanismCode10), getString(R.string.mechanismCode11), getString(R.string.mechanismCode12), getString(R.string.mechanismCode13)}, DBAdapter.KEY_INJURY_MECHANISM));
        items.get(items.size()-1).extraOptions = new String[]{"", getString(R.string.mechanismCode1), getString(R.string.mechanismCode2), getString(R.string.mechanismCode3), getString(R.string.mechanismCode4), getString(R.string.mechanismCode5), getString(R.string.mechanismCode6), getString(R.string.mechanismCode7), getString(R.string.mechanismCode8), getString(R.string.mechanismCode9), getString(R.string.mechanismCode10), getString(R.string.mechanismCode11), getString(R.string.mechanismCode12), getString(R.string.mechanismCode13)};

        adapter = new FragmentListAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        return v;
    }

    public static FragmentC newInstance() {

        return new FragmentC();
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
}
