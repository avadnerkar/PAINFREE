package physiotherapy.mcgill.com.painfree.Utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import physiotherapy.mcgill.com.painfree.Dialogs.DialogEditText;
import physiotherapy.mcgill.com.painfree.Dialogs.DialogSimple;
import physiotherapy.mcgill.com.painfree.Dialogs.DialogThreeButton;
import physiotherapy.mcgill.com.painfree.Dialogs.DialogTwoButton;
import physiotherapy.mcgill.com.painfree.R;

/**
 * Created by Abhishek Vadnerkar on 16-02-27.
 */
public class AppUtils {

    // SIMPLE WARNING ALERT
    @SuppressWarnings("deprecation")
    public static void showAlert(final String title, final String msg, final Context context) {

        Activity a = (Activity) context;
        a.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle(title);
                alertDialog.setMessage(msg);
                alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
    }

    /*
    Displays custom one button dialog
     */
    public static void showSimpleDialog(String message, Context context){
        final DialogSimple dialog = new DialogSimple(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        TextView textView = (TextView) dialog.findViewById(R.id.dialog_title);
        textView.setText(message);
    }

    public static void showTwoButtonDialog(String message, String positiveButton, String negativeButton, Context context, DialogTwoButton.ClickHandler clickHandler){
        final DialogTwoButton dialog = new DialogTwoButton(context, clickHandler);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        TextView textView = (TextView) dialog.findViewById(R.id.dialog_title);
        textView.setText(message);

        textView = (TextView) dialog.findViewById(R.id.dialog_ok);
        textView.setText(positiveButton);

        textView = (TextView) dialog.findViewById(R.id.dialog_cancel);
        textView.setText(negativeButton);
    }

    public static void showThreeButtonDialog(String message, String positiveButton, String negativeButton, String thirdButton, Context context, DialogThreeButton.ClickHandler clickHandler){
        final DialogThreeButton dialog = new DialogThreeButton(context, clickHandler);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        TextView textView = (TextView) dialog.findViewById(R.id.dialog_title);
        textView.setText(message);

        textView = (TextView) dialog.findViewById(R.id.dialog_ok);
        textView.setText(positiveButton);

        textView = (TextView) dialog.findViewById(R.id.dialog_cancel);
        textView.setText(negativeButton);

        textView = (TextView) dialog.findViewById(R.id.dialog_third);
        textView.setText(thirdButton);
    }

    /*
    Displays custom list dialog
     */
    public static void showListDialog(String title, final String[] items, Context context, final ListHandler h){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                h.onClick(i);
            }
        });
        builder.show();
    }

    public interface ListHandler{
        void onClick(int index);
    }


    public static void showEditTextDialog(String title, Context context, final DialogEditText.ClickHandler h){
        final DialogEditText dialog = new DialogEditText(context, h);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
        TextView textView = (TextView) dialog.findViewById(R.id.dialog_title);
        textView.setText(title);
    }

    public static void showDefaultAlertDialog(String title, String message, Context context, final DefaultAlertHandler handler){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.onClick();
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    public static void showDefaultTwoButtonAlertDialog(String title, String message, String button1, String button2, Context context, final DefaultTwoButtonAlertHandler handler){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton(button1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.onPositiveClick();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(button2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.onNegativeClick();
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    public interface DefaultAlertHandler{
        void onClick();
    }

    public interface DefaultTwoButtonAlertHandler{
        void onPositiveClick();

        void onNegativeClick();
    }



    public static void showAlertWithImage(String title, String msg, Context context, ImageView image) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(context).
                        setTitle(title).
                        setMessage(msg).
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).
                        setView(image);
        builder.create().show();
    }

    public static void showPopup(final String msg, final Context context) {
        Activity a = (Activity) context;
        a.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

}
