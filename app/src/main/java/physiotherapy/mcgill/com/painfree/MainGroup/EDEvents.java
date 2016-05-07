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

    public static View setupEventSection(final Context context, ViewGroup parent, View convertView, final ArrayAdapter adapter){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView;
        ViewHolderEvents holder;
        if (convertView == null){
            rowView = inflater.inflate(R.layout.cell_fragment_event, parent, false);
            holder = new ViewHolderEvents();
            holder.container = (LinearLayout) rowView.findViewById(R.id.container);
            holder.cbNone = (CheckBox) rowView.findViewById(R.id.checkboxNone);
            holder.addButton = (FloatingActionButton) rowView.findViewById(R.id.fabPlus);
            rowView.setTag(holder);
        } else {
            rowView = convertView;
            holder = (ViewHolderEvents) rowView.getTag();
        }

        final int maxEvents = 8;

        holder.container.removeAllViews();

        holder.cbNone.setChecked(false);
        Cursor cursorNone = MainActivity.myDb.getDataField(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_BOOL);
        if (cursorNone.moveToFirst()){
            if (cursorNone.getString(0) != null && cursorNone.getString(0).equals(context.getString(R.string.yes))){
                holder.cbNone.setChecked(true);
            }
        }
        cursorNone.close();

        holder.cbNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_BOOL, context.getString(R.string.yes));
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_NUM, String.valueOf(0));
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_ORDER, null);
                    PainAssessments.clearData();
                    AnalgesicPrescription.clearData();
                    AnalgesicAdministration.clearData();
                    NerveBlock.clearData();
                    AlternativePainRelief.clearData();


                } else {
                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_BOOL, null);
                }

                adapter.notifyDataSetChanged();
            }
        });

        final ArrayList<Integer> orderOfEvents = new ArrayList<>();
        final Cursor cursor = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_EVENTS_BOOL, DBAdapter.KEY_EVENTS_NUM, DBAdapter.KEY_EVENTS_ORDER});

        if (cursor.moveToFirst()){
            final int numEvents = cursor.getInt(1);

            final String orderString = cursor.getString(2);
            if (orderString != null){
                for (int i=0; i<orderString.length(); i++){
                    Integer type = Character.getNumericValue(orderString.charAt(i));
                    orderOfEvents.add(type);
                }
            }

            holder.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean canAdd = true;
                    if (numEvents >0){
                        int lastEventType = orderOfEvents.get(orderOfEvents.size()-1);


                        switch (lastEventType){
                            case 0:{
                                canAdd = PainAssessments.canAdd();
                                break;
                            }
                            case 1:{
                                canAdd = AnalgesicPrescription.canAdd();
                                break;
                            }
                            case 2:{
                                canAdd = AnalgesicAdministration.canAdd();
                                break;
                            }
                            case 3:{
                                canAdd = NerveBlock.canAdd();
                                break;
                            }
                            case 4:{
                                canAdd = AlternativePainRelief.canAdd();
                                break;
                            }
                        }
                    }

                    if (canAdd){
                        AppUtils.showListDialog(context.getString(R.string.select_type_of_event), new String[]{context.getString(R.string.pain_assessment), context.getString(R.string.analgesic_prescription), context.getString(R.string.analgesic_administration), context.getString(R.string.nerve_block), context.getString(R.string.alternative_pain_relief)}, context, new AppUtils.ListHandler() {
                            @Override
                            public void onClick(int i) {

                                Cursor cursor1 = MainActivity.myDb.getDataFields(MainActivity.currentPatientId, new String[]{DBAdapter.KEY_PAIN_ASSESSMENT_NUM, DBAdapter.KEY_ANALGESIC_PRES_NUM, DBAdapter.KEY_ANALGESIC_ADMIN_NUM, DBAdapter.KEY_NERVE_BLOCK_NUM, DBAdapter.KEY_ALTERNATIVE_PAIN_RELIEF_NUM});
                                cursor1.moveToFirst();

                                Cursor dateCursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, DBAdapter.KEY_ARRIVALDATE);
                                dateCursor.moveToFirst();
                                String defaultDate = dateCursor.getString(0);
                                dateCursor.close();
                                switch (i){
                                    case 0:
                                        int numPainAssessments = cursor1.getInt(0);
                                        if (numPainAssessments>=maxEvents){
                                            AppUtils.showAlert("Error", "Reached pain assessment limit", context);
                                            return;
                                        } else {
                                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_PAIN_ASSESSMENT_NUM, String.valueOf(numPainAssessments+1));
                                            if (!defaultDate.equals(context.getString(R.string.none))){
                                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, PainAssessments.keys[(numPainAssessments)*PainAssessments.numFields+1], defaultDate);
                                            }

                                        }
                                        break;
                                    case 1:
                                        int numAnalgesicPres = cursor1.getInt(1);
                                        if (numAnalgesicPres>=maxEvents){
                                            AppUtils.showAlert("Error", "Reached analgesic prescription limit", context);
                                            return;
                                        } else {
                                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_ANALGESIC_PRES_NUM, String.valueOf(numAnalgesicPres+1));
                                            if (!defaultDate.equals(context.getString(R.string.none))){
                                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, AnalgesicPrescription.keys[(numAnalgesicPres)*AnalgesicPrescription.numFields+1], defaultDate);
                                            }
                                        }
                                        break;
                                    case 2:
                                        int numAnalgesicAdmin = cursor1.getInt(2);
                                        if (numAnalgesicAdmin>=maxEvents){
                                            AppUtils.showAlert("Error", "Reached analgesic administration limit", context);
                                            return;
                                        } else {
                                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_ANALGESIC_ADMIN_NUM, String.valueOf(numAnalgesicAdmin+1));
                                            if (!defaultDate.equals(context.getString(R.string.none))){
                                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, AnalgesicAdministration.keys[(numAnalgesicAdmin)*AnalgesicAdministration.numFields+1], defaultDate);
                                            }
                                        }
                                        break;
                                    case 3:
                                        int numNerveBlock = cursor1.getInt(3);
                                        if (numNerveBlock>=maxEvents){
                                            AppUtils.showAlert("Error", "Reached nerve block limit", context);
                                            return;
                                        } else {
                                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_NERVE_BLOCK_NUM, String.valueOf(numNerveBlock+1));
                                            if (!defaultDate.equals(context.getString(R.string.none))){
                                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, NerveBlock.keys[(numNerveBlock)*NerveBlock.numFields+1], defaultDate);
                                            }
                                        }
                                        break;
                                    case 4:
                                        int numAlternativePainRelief = cursor1.getInt(4);
                                        if (numAlternativePainRelief>=maxEvents){
                                            AppUtils.showAlert("Error", "Reached alternative pain relief limit", context);
                                            return;
                                        } else {
                                            MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_ALTERNATIVE_PAIN_RELIEF_NUM, String.valueOf(numAlternativePainRelief+1));
                                            if (!defaultDate.equals(context.getString(R.string.none))){
                                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, AlternativePainRelief.keys[(numAlternativePainRelief)*AlternativePainRelief.numFields+1], defaultDate);
                                            }
                                        }
                                        break;
                                }
                                cursor1.close();

                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_NUM, String.valueOf(numEvents+1));
                                orderOfEvents.add(i);

                                String orderStringNew = TextUtils.join("", orderOfEvents);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_ORDER, orderStringNew);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_BOOL, null);

                                adapter.notifyDataSetChanged();
                            }
                        });
                    } else {
                        AppUtils.showDefaultAlertDialog("Error", "Please complete the current event", context, new AppUtils.DefaultAlertHandler() {
                            @Override
                            public void onClick() {

                            }
                        });
                    }



                }
            });


            int painAssessmentIndex = 0;
            int analgesicPresIndex = 0;
            int analgesicAdminIndex = 0;
            int nerveBlockIndex = 0;
            int alternativePainReliefIndex = 0;
            for (int i=0; i<orderOfEvents.size(); i++){
                Integer type = orderOfEvents.get(i);
                final int I = i;
                View childView = null;

                switch (type){
                    case 0:{

                        childView = PainAssessments.setupPainAssessmentSection(context, holder.container, adapter, painAssessmentIndex, i, new MinusHandler() {
                            @Override
                            public void onClick() {
                                orderOfEvents.remove(I);
                                String orderStringNew = TextUtils.join("", orderOfEvents);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_ORDER, orderStringNew);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_NUM, String.valueOf(numEvents-1));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        painAssessmentIndex++;
                        break;
                    }
                    case 1:{

                        childView = AnalgesicPrescription.setupAnalgesicPrescriptionSection(context, holder.container, adapter, analgesicPresIndex, i, new MinusHandler() {
                            @Override
                            public void onClick() {
                                orderOfEvents.remove(I);
                                String orderStringNew = TextUtils.join("", orderOfEvents);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_ORDER, orderStringNew);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_NUM, String.valueOf(numEvents-1));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        analgesicPresIndex++;
                        break;
                    }

                    case 2:{

                        childView = AnalgesicAdministration.setupAnalgesicAdministrationSection(context, holder.container, adapter, analgesicAdminIndex, i, new MinusHandler() {
                            @Override
                            public void onClick() {
                                orderOfEvents.remove(I);
                                String orderStringNew = TextUtils.join("", orderOfEvents);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_ORDER, orderStringNew);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_NUM, String.valueOf(numEvents-1));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        analgesicAdminIndex++;
                        break;
                    }

                    case 3:{

                        childView = NerveBlock.setupNerveBlockSection(context, holder.container, adapter, nerveBlockIndex, i, new MinusHandler() {
                            @Override
                            public void onClick() {
                                orderOfEvents.remove(I);
                                String orderStringNew = TextUtils.join("", orderOfEvents);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_ORDER, orderStringNew);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_NUM, String.valueOf(numEvents-1));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        nerveBlockIndex++;
                        break;
                    }

                    case 4:{

                        childView = AlternativePainRelief.setupAlternativePainReliefSection(context, holder.container, adapter, alternativePainReliefIndex, i, new MinusHandler() {
                            @Override
                            public void onClick() {
                                orderOfEvents.remove(I);
                                String orderStringNew = TextUtils.join("", orderOfEvents);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_ORDER, orderStringNew);
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, DBAdapter.KEY_EVENTS_NUM, String.valueOf(numEvents-1));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        alternativePainReliefIndex++;
                        break;
                    }
                }

                holder.container.addView(childView);
            }

        }

        cursor.close();

        return rowView;

    }

    public interface MinusHandler{
        void onClick();
    }

    static class ViewHolderEvents{
        private LinearLayout container;
        private CheckBox cbNone;
        private FloatingActionButton addButton;
    }

}
