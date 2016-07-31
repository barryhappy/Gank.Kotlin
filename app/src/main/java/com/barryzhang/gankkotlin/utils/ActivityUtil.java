package com.barryzhang.gankkotlin.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.barryzhang.gankkotlin.R;

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/15 12:08.
 */
public class ActivityUtil {

    public static void showFragment (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {

    }

    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_out,
                android.R.anim.fade_out);

        Fragment f = fragmentManager.findFragmentById(frameId);
        if(f!=null){
            transaction.hide(f);
        }
        if(fragment.isAdded()) {
            transaction.show(fragment);
        }else{
            transaction.add(frameId, fragment);
        }
        transaction.commit();

    }
//    public static void showFragmentToActivity (@NonNull FragmentManager fragmentManager,
//                                              @NonNull Fragment fragment, int frameId) {
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        Fragment f = fragmentManager.findFragmentById(frameId);
//        if(f!=null){
//            transaction.hide(f);
//        }
//
//        transaction.show(fragment);
//        transaction.commit();
//    }

}
