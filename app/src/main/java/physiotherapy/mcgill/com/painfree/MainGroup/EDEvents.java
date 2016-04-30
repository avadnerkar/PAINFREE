package physiotherapy.mcgill.com.painfree.MainGroup;

import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import physiotherapy.mcgill.com.painfree.R;
import physiotherapy.mcgill.com.painfree.Utilities.AppUtils;
import physiotherapy.mcgill.com.painfree.Utilities.DBAdapter;

/**
 * Created by Abhishek Vadnerkar on 16-04-30.
 */
public class EDEvents {

    public static View setupEventSection(final Context context, ViewGroup parent, final ArrayAdapter adapter){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cell_fragment_event, parent, false);

        final int maxEvents = 6;


        final LinearLayout container = (LinearLayout) rowView.findViewById(R.id.container);
        container.removeAllViews();

        CheckBox cbNone = (CheckBox) rowView.findViewById(R.id.checkboxNone);
        cbNone.setChecked(false);
        Cursor cursorNone = MainActivity.myDb.getDataField(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_BOOL);
        if (cursorNone.moveToFirst()){
            if (cursorNone.getString(0) != null && cursorNone.getString(0).equals(context.getString(R.string.yes))){
                cbNone.setChecked(true);
            }
        }
        cursorNone.close();

        cbNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_BOOL, context.getString(R.string.yes));
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_NUM, String.valueOf(0));
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_ORDER, null);




                } else {
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_BOOL, null);
                }

                adapter.notifyDataSetChanged();
            }
        });

        final ArrayList<Integer> orderOfEvents = new ArrayList<>();
        Cursor cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_EVENTS_BOOL, DBAdapter.KEY_EVENTS_NUM, DBAdapter.KEY_EVENTS_ORDER});

        if (cursor.moveToFirst()){
            final int numEvents = cursor.getInt(1);

            final String orderString = cursor.getString(2);
            if (orderString != null){
                for (int i=0; i<orderString.length(); i++){
                    Integer type = Character.getNumericValue(orderString.charAt(i));
                    orderOfEvents.add(type);
                }
            }

            FloatingActionButton addButton = (FloatingActionButton) rowView.findViewById(R.id.fabPlus);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AppUtils.showListDialog(context.getString(R.string.select_type_of_event), new String[]{context.getString(R.string.pain_assessment), context.getString(R.string.analgesic_prescription), context.getString(R.string.analgesic_administration)}, context, new AppUtils.ListHandler() {
                        @Override
                        public void onClick(int i) {

                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_NUM, String.valueOf(numEvents+1));
                            orderOfEvents.add(i);

                            String orderStringNew = TextUtils.join("", orderOfEvents);
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_NUM, orderStringNew);
                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_BOOL, null);
                            switch (i){
                                case 0:
                                    break;
                                case 1:
                                    break;
                                case 2:
                                    break;
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });

                }
            });


            for (int i=0; i<orderOfEvents.size(); i++){
                Integer type = orderOfEvents.get(i);

                switch (type){
                    case 0:{
                        break;
                    }
                    case 1:{
                        break;
                    }

                    case 2:{
                        break;
                    }
                }
            }

        }




        return rowView;

    }

}
