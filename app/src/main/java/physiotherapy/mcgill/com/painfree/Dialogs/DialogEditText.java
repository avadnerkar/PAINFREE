package physiotherapy.mcgill.com.painfree.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import physiotherapy.mcgill.com.painfree.R;

/**
 * Created by Abhishek Vadnerkar on 16-03-06.
 */
public class DialogEditText extends Dialog{

    public Context context;
    public Dialog d;
    public Button ok;
    public ClickHandler handler;

    public DialogEditText(Context context, ClickHandler handler) {
        super(context);
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_edit_text);

        final EditText editText = (EditText) findViewById(R.id.editDialog);
        ok = (Button) findViewById(R.id.dialog_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                handler.onClick(text);
                dismiss();
            }
        });
    }

    public interface ClickHandler{
        void onClick(String text);
    }

}
