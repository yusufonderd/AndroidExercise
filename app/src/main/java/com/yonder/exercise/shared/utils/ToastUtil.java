package com.yonder.exercise.shared.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by YusufMac on 01/06/17.
 */

public class ToastUtil {

    private Toast toast;
    private Context context;

    private static ToastUtil toastUtil;

    private ToastUtil(Context context) {
        this.context = context;
        this.toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }


    public static ToastUtil newInstance(Context context) {

        if (toastUtil == null) {
            toastUtil = new ToastUtil(context);
        }
        return toastUtil;
    }

    public void showToast(String message) {
        if (this.toast == null) {
            this.toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            this.toast.setText(message);
        }
        this.toast.show();
    }


    public void showToast(int message) {
        if (this.toast == null) {
            this.toast = Toast.makeText(context, context.getString(message), Toast.LENGTH_SHORT);
        } else {
            this.toast.setText(message);
        }
        this.toast.show();
    }

}
