package com.deepaksharma.deepaksharma.easyexception;

import android.app.Application;

import com.deepaksharma.webaddicted.Enum.AlertType;
import com.deepaksharma.webaddicted.TAErrorHandler;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TAErrorHandler.init(this);
        TAErrorHandler.AlertType(AlertType.POP_UP);//, ErrorEnable.ENABLE);
    }
}
