package com.demo.qlsinhvien;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

public class Util {
    public static void daNgonNgu(Context context, String language){
        Locale locale = new Locale(language);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration,context.getResources().getDisplayMetrics());

    }
}
