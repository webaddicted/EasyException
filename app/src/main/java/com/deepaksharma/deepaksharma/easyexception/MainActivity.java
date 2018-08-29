package com.deepaksharma.deepaksharma.easyexception;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.deepaksharma.webaddicted.TAErrorHandler;

import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
    }

    public void expHandleToast(View v) {
        try {
            FileInputStream fis = null;
            fis = new FileInputStream("B:/myfile.txt");
            int k;
            while ((k = fis.read()) != -1) {
                System.out.print((char) k);
            }
            fis.close();
        } catch (Exception exp) {
            TAErrorHandler.handler(MainActivity.this, exp);
        }
    }

    public void expHandleSnackbar(View v) {
        try {
            int deep = 25/0;
        }catch (ArithmeticException exp) {
            TAErrorHandler.handler(MainActivity.this, exp);
        }
    }

    public boolean valid() {
        return Boolean.parseBoolean(null);
    }
}
