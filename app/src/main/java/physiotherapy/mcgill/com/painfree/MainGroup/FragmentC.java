package physiotherapy.mcgill.com.painfree.MainGroup;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.regex.Pattern;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentC extends Fragment {

    public static FragmentListAdapter adapter;
    private static ListView listView;
    private static ArrayList<FragmentItem> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_c, container, false);

        listView = (ListView) v.findViewById(R.id.list_c);

        items = new ArrayList<>();

        items.add(new FragmentItem(null, FragmentItem.CellType.FRACTURESITE, null, null, null));
        items.add(new FragmentItem(getString(R.string.mechanism_of_injury), FragmentItem.CellType.SPINNER_WITH_OTHER, new String[]{"", getString(R.string.none), getString(R.string.mechanism1), getString(R.string.mechanism2), getString(R.string.mechanism4), getString(R.string.mechanism5), getString(R.string.mechanism6), getString(R.string.mechanism7), getString(R.string.mechanism8), getString(R.string.mechanism9), getString(R.string.mechanism10), getString(R.string.mechanism11), getString(R.string.mechanism12), getString(R.string.mechanism13), getString(R.string.mechanism14), getString(R.string.mechanism15), getString(R.string.mechanism16), getString(R.string.mechanism17), getString(R.string.mechanism18), getString(R.string.mechanism19), getString(R.string.mechanism20)}, new String[]{"", getString(R.string.none), getString(R.string.mechanismCode1), getString(R.string.mechanismCode2), getString(R.string.mechanismCode4), getString(R.string.mechanismCode5), getString(R.string.mechanismCode6), getString(R.string.mechanismCode7), getString(R.string.mechanismCode8), getString(R.string.mechanismCode9), getString(R.string.mechanismCode10), getString(R.string.mechanismCode11), getString(R.string.mechanismCode12), getString(R.string.mechanismCode13), getString(R.string.mechanismCode14), getString(R.string.mechanismCode15), getString(R.string.mechanismCode16), getString(R.string.mechanismCode17), getString(R.string.mechanismCode18), getString(R.string.mechanismCode19), getString(R.string.mechanismCode20)}, DBAdapter.KEY_INJURY_MECHANISM));
        items.get(items.size()-1).extraOptions = new String[]{"", "", getString(R.string.mechanismCode1), getString(R.string.mechanismCode2), getString(R.string.mechanismCode4), getString(R.string.mechanismCode5), getString(R.string.mechanismCode6), getString(R.string.mechanismCode7), getString(R.string.mechanismCode8), getString(R.string.mechanismCode9), getString(R.string.mechanismCode10), getString(R.string.mechanismCode11), getString(R.string.mechanismCode12), getString(R.string.mechanismCode13), getString(R.string.mechanismCode14), getString(R.string.mechanismCode15), getString(R.string.mechanismCode16), getString(R.string.mechanismCode17), getString(R.string.mechanismCode18), getString(R.string.mechanismCode19), getString(R.string.mechanismCode20)};
        items.get(items.size()-1).isMandatory = true;

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


    public static ArrayList<String> unfilledMandatoryFields() {

        ArrayList<String> unfilledTitles = new ArrayList<>();

        if (MainActivity.currentPatientId != -1) {


            String[] keys = new String[]{
                    DBAdapter.KEY_FRACTURESITE_FOOT,
                    DBAdapter.KEY_FRACTURESITE_ANKLE,
                    DBAdapter.KEY_FRACTURESITE_TIBIA,
                    DBAdapter.KEY_FRACTURESITE_FEMUR,
                    DBAdapter.KEY_FRACTURESITE_HIP,
                    DBAdapter.KEY_FRACTURESITE_RIB,
                    DBAdapter.KEY_FRACTURESITE_HUMERUS,
                    DBAdapter.KEY_FRACTURESITE_FOREARM,
                    DBAdapter.KEY_FRACTURESITE_WRIST,
                    DBAdapter.KEY_FRACTURESITE_HAND};
            Cursor cursor1 = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, keys);


            Boolean fractures = false;
            if (cursor1.moveToFirst()){
                for (int i=0; i<cursor1.getColumnCount(); i++){
                    if (cursor1.getString(i) != null && cursor1.getString(i).equals("Incomplete")){
                        String charToDel = "[]";
                        String pat = "[" + Pattern.quote(charToDel) + "]";
                        String strippedkey = keys[i].replaceAll(pat,"");
                        unfilledTitles.add(strippedkey);
                    }

                    if (cursor1.getString(i) != null && !cursor1.getString(i).equals("")){
                        fractures = true;
                    }
                }
            }

            cursor1.close();

//            if (!fractures){
//                Cursor cursor2 = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_FRACTURESITE_HEAD, DBAdapter.KEY_FRACTURESITE_TOES, DBAdapter.KEY_FRACTURESITE_FINGERS});
//                for (int i=0; i<cursor2.getColumnCount(); i++){
//                    if (cursor2.getString(i) != null && !cursor2.getString(i).equals("")){
//                        ArrayList<String> warning = new ArrayList<>();
//                        warning.add("EXCLUSION");
//                        warning.add("Head, toe, and finger fractures are excluded from this study");
//                        cursor2.close();
//                        return warning;
//                    }
//                }
//                cursor2.close();
//            }

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
