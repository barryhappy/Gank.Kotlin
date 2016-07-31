package com.barryzhang.gankkotlin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.barryzhang.gankkotlin.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UtilShare {
    public static SharedPreferences getSharedPreferences() {
        SharedPreferences sharedPreferences = App.instance
                .getSharedPreferences("GANK_KOTLIN", Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static void saveData(String key, int value) {
        Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value);
        editor.commit();
    }


    public static void saveData(String key, long value) {
        Editor editor = getSharedPreferences().edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void saveData(String key, String value) {
        Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveData(String key, boolean value) {
        Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void saveData(String key, List<String> value) {
        Editor editor = getSharedPreferences().edit();
        editor.putString(key, new Gson().toJson(value));
        editor.commit();
    }

    public static List<String> getDataStringList(String key){
        SharedPreferences sharedPreferences = getSharedPreferences();
        String string = sharedPreferences.getString(key, "");
        if(!TextUtils.isEmpty(string)){
            try {
                Type type = new TypeToken<List<String>>() {}.getType();
                return new Gson().fromJson(string, type);
            }catch (Exception e){
                // do nothing~
            }
        }
        return new ArrayList<>();
    }


    public static boolean getDataBoolean(String key) {
        return getDataBoolean(key, false);
    }

    public static boolean getDataBoolean(String key, boolean defaulValue) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getBoolean(key, defaulValue);
    }

    public static String getDataString(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getString(key, "");
    }

    public static int getDataInt(String key) {
        return getDataInt(key, 0);
    }

    public static int getDataInt(String key, int defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static long getDataLong(String key, long defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getLong(key, defaultValue);
    }

}
