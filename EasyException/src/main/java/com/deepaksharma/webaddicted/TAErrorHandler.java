package com.deepaksharma.webaddicted;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.deepaksharma.webaddicted.Enum.AlertType;
import com.deepaksharma.webaddicted.FileError.FileWrite;
import com.deepaksharma.webaddicted.interfaces.PermissionListener;
import com.deepaksharma.webaddicted.permission.PermissionActivity;

import static com.deepaksharma.webaddicted.FileError.FileWrite.writeFile;

public class TAErrorHandler implements PermissionListener {
//    public static @NonNull
//    ErrorEnable mErrorEnable;
    public static @NonNull
    AlertType mAlertType;
    public static @NonNull
    Context appContext;
    public static String strException = null;

    public static void AlertType(@NonNull AlertType alertType) {//, @NonNull ErrorEnable errorEnable) {
        mAlertType = alertType;
//        mErrorEnable = errorEnable;
    }

    //    Toast & PopUp Type Alert
    public static void handler(@NonNull Activity activity, @NonNull Exception exception) {
        String expMsg = exception.getMessage();
        if (activity != null && appContext != null) {
//        if (mErrorEnable != null && mErrorEnable.equals(ErrorEnable.ENABLE)) {
            if (mAlertType != null && mAlertType.equals(AlertType.TOAST)) {
                Toast.makeText(activity, expMsg, Toast.LENGTH_SHORT).show();
            } else if (mAlertType != null && mAlertType.equals(AlertType.POP_UP)) {
                alertDialog(activity, expMsg);
            }else if (mAlertType != null && mAlertType.equals(AlertType.NONE)) {
            }
            writeFile(activity, expMsg);
        }
    }

    //      SNACKBAR Alert
    public static void handler(@NonNull View view, @NonNull Exception exception) {
        String expMsg = exception.getMessage();
        if (appContext != null) {
//        if (mErrorEnable != null && mErrorEnable.equals(ErrorEnable.ENABLE)) {
            if (mAlertType != null && mAlertType.equals(AlertType.SNACKBAR)) {
                if (view != null) {
//                    logToFile(exceptionMsg);
                    Snackbar.make(view, expMsg, Snackbar.LENGTH_LONG).show();
                    FileWrite.writeFile(appContext, expMsg);
                }
            }else if (mAlertType != null && mAlertType.equals(AlertType.NONE)) {
                FileWrite.writeFile(appContext, expMsg);
            }
        }
    }

    //      POP_UP Alert Dialog
    private static void alertDialog(Activity activity, String exceptionMsg) {
        AlertDialog builder = new AlertDialog.Builder(activity).create();
        builder.setTitle(activity.getResources().getString(R.string.EXP_TITLE));
        builder.setMessage(exceptionMsg);
        builder.setCancelable(true);
        builder.setButton(
                activity.getResources().getString(R.string.OK),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    public void permissionInit() {
        PermissionActivity.checkListener(this);
    }

    @Override
    public void onAccepted() {
        if(strException != null)
            FileWrite.logFile(appContext, strException);
    }

    @Override
    public void onDenied() {

    }

//    module initialize & check permission.
    public static void init(Context context) {
        appContext = context;
        if(appContext!=null && Build.VERSION.SDK_INT >= 23){
            Intent intent = new Intent(context, PermissionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            new TAErrorHandler().permissionInit();
        }
    }
}
