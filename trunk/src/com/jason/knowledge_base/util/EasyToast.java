package com.jason.knowledge_base.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.jason.knowledge_base.R;

/**
 * User: jason
 * Date: 12-12-12
 */
public class EasyToast {

    private static int gravity;
    private static int xOffset, yOffset;
    public static final int duration = Toast.LENGTH_SHORT;

    private static boolean customPosition;

    public static void setGravity(int gravity, int xOffset, int yOffset) {

        EasyToast.gravity = gravity;
        EasyToast.xOffset = xOffset;
        EasyToast.yOffset = yOffset;

        customPosition = true;
    }

    public static void show(Context context, int messageResourceId) {
        show(context, context.getString(messageResourceId));
    }

    public static void show(Context context, String msg) {
        prepare(context);
        show(msg);
    }

    public static void prepare(Context context) {

        try {
            Looper mainLooper = context.getMainLooper();

            if (handler == null || mainLooper != handler.getLooper()) {
                handler = new Handler(mainLooper);
            }

            if (appContext == null) {
                appContext = context.getApplicationContext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show(int messageResourceId) {
        show(appContext.getString(messageResourceId));
    }

    public static void show(final String msg) {

        try {
            handler.post(new Runnable() {
                @Override
                public void run() {

                    Toast toast = makeToast(msg);

                    if (customPosition) {
                        toast.setGravity(gravity, xOffset, yOffset);
                    }

                    toast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Toast makeToast(int messageResourceId) {
        return makeToast(appContext.getString(messageResourceId));
    }

    private static Toast makeToast(String msg) {

        Toast toast = new Toast(appContext);

        LayoutInflater inflate = LayoutInflater.from(appContext);

        View board = inflate.inflate(R.layout.simple_toast, null);
        TextView tv = (TextView) board.findViewById(R.id.message);
        tv.setText(msg);

        toast.setView(board);

        toast.setDuration(duration);

        return toast;
    }

    private static Handler handler;
    private static Context appContext;
}
