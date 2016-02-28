package physiotherapy.mcgill.com.painfree.MainGroup;

import android.content.Context;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;

import physiotherapy.mcgill.com.painfree.R;

/**
 * Created by Abhishek Vadnerkar on 16-02-28.
 */
public class FragmentListAdapter extends ArrayAdapter<FragmentItem> {

    private Context context;
    private ArrayList<FragmentItem> items;


    public FragmentListAdapter(Context context, ArrayList<FragmentItem> items){
        super(context, R.layout.cell_fragment_radio, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getViewTypeCount() {
        return FragmentItem.CellType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return (items.get(position).cellType).ordinal();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = null;
        TextView textView;
        Cursor cursor;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (items.get(position).cellType){
            case NUMERIC:
                rowView = inflater.inflate(R.layout.cell_fragment_numeric, parent, false);
                textView = (TextView) rowView.findViewById(R.id.title);
                textView.setText(items.get(position).title);
                EditText editText = (EditText) rowView.findViewById(R.id.edit_numeric);
                editText.setHint(items.get(position).uiOptions[0]);

                cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

                if (cursor.moveToFirst()){
                    String text = cursor.getString(cursor.getColumnIndex(items.get(position).dbKey));
                    if (text !=null){
                        editText.setText(text);
                    } else {
                        editText.setText("");
                    }

                } else {
                    editText.setText("");
                }

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                        Thread thread = new Thread(){
                            @Override
                            public void run() {
                                MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, charSequence.toString());
                            }
                        };
                        thread.start();

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                cursor.close();

                break;
            case RADIO:
                rowView = inflater.inflate(R.layout.cell_fragment_radio, parent, false);
                textView = (TextView) rowView.findViewById(R.id.title);
                textView.setText(items.get(position).title);

                final RadioGroup rg = (RadioGroup) rowView.findViewById(R.id.rg);

                if (items.get(position).databaseOptions == null){
                    items.get(position).databaseOptions = items.get(position).uiOptions;
                }

                if (items.get(position).uiOptions.length >2 && items.get(position).uiOptions.length < 7){
                    rg.setOrientation(RadioGroup.VERTICAL);
                } else {
                    rg.setOrientation(RadioGroup.HORIZONTAL);
                }

                for (int i=0; i<items.get(position).uiOptions.length; i++){
                    RadioButton rb = new RadioButton(context);
                    rb.setText(items.get(position).uiOptions[i]);
                    rg.addView(rb);

                    final int index = i;
                    rb.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(final View view) {
                            Thread thread = new Thread(){
                                @Override
                                public void run() {
                                    MainActivity.myDb.updateFieldData(MainActivity.currentPatientId, items.get(position).dbKey, items.get(position).databaseOptions[index]);
                                }
                            };
                            thread.start();

                        }
                    });
                }

                cursor = MainActivity.myDb.getDataField(MainActivity.currentPatientId, items.get(position).dbKey);

                if (cursor.moveToFirst()){
                    String radioValue = cursor.getString(cursor.getColumnIndex(items.get(position).dbKey));
                    if (radioValue != null && !radioValue.equals("")){
                        for (int i=0; i<items.get(position).databaseOptions.length; i++){
                            String rbString = items.get(position).databaseOptions[i];
                            if (rbString.equals(radioValue)){
                                ((RadioButton)rg.getChildAt(i)).setChecked(true);
                            }
                        }
                    } else {
                        rg.clearCheck();
                    }

                } else {
                    rg.clearCheck();
                }

                cursor.close();
                break;

            case DATEPICKER:

                rowView = inflater.inflate(R.layout.cell_fragment_datepicker, parent, false);
                textView = (TextView) rowView.findViewById(R.id.title);
                textView.setText(items.get(position).title);
                
                Button button = (Button) rowView.findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        }

        return rowView;
    }



}
