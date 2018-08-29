package com.deepaksharma.webaddicted.permission;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.deepaksharma.webaddicted.interfaces.PermissionListener;

public class PermissionActivity extends Activity {
    private static final int MY_PERMISSIONS_REQUEST = 1;
    public static String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static Activity mActivity;
    public static PermissionListener mPermissionListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = PermissionActivity.this;
        permissions();
    }

    public void permissions() {
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Check PermissionActivity Now
            ActivityCompat.requestPermissions(mActivity,
                    permissions,
                    MY_PERMISSIONS_REQUEST);
        } else {
            mPermissionListener.onAccepted();
            this.finish();
        }
    }

    public void onRequestPermissionsResult(int requestCode, final String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPermissionListener.onAccepted();
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PermissionActivity.this);
                    builder.setTitle("Need Permission");
                    builder.setMessage("This app needs permission");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ActivityCompat.requestPermissions(PermissionActivity.this, permissions, MY_PERMISSIONS_REQUEST);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
//                            ActivityCompat.requestPermissions(PermissionActivity.this, permissions, MY_PERMISSIONS_REQUEST);
                            mPermissionListener.onDenied();
                            finish();
                        }
                    });

                    builder.show();
                }
                return;
            }
        }
    }

    public static void checkListener(PermissionListener permissionListener) {
        mPermissionListener = permissionListener;
    }
}
