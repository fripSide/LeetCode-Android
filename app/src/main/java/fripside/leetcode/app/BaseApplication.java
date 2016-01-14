package fripside.leetcode.app;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;

/**
 * Created by fripside on 1/12/16.
 */
public class BaseApplication extends Application {

    public static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        CONTEXT = getApplicationContext();
    }
}
