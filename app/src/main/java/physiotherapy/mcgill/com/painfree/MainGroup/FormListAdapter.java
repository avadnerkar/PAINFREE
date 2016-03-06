package physiotherapy.mcgill.com.painfree.MainGroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import physiotherapy.mcgill.com.painfree.R;

/**
 * Created by Abhishek Vadnerkar on 16-02-24.
 */
public class FormListAdapter extends ArrayAdapter<FormItem> {

    private Context context;
    private ArrayList<FormItem> items;

    public FormListAdapter(Context context, ArrayList<FormItem> items){
        super(context, R.layout.cell_fragment_text, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getViewTypeCount() {
        return FormItem.CellType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return (items.get(position).cellType).ordinal();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = null;
        final TextView textView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (items.get(position).cellType) {

            case TEXT:

                rowView = inflater.inflate(R.layout.cell_fragment_text, parent, false);
                textView = (TextView) rowView.findViewById(R.id.title);
                textView.setText(items.get(position).title);
                EditText editText = (EditText) rowView.findViewById(R.id.text);
                //editText.setHint(items.get(position).options[0]);
                //editText.setInputType(InputType.TYPE_CLASS_TEXT);

                break;

        }

        return rowView;
    }


}
