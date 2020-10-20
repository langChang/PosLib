package com.nhsoft.poslib.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.nhsoft.poslib.R;
import com.nhsoft.poslib.RetailPosManager;

import java.lang.reflect.Field;

/**
 * Created by Iverson on 2016/12/23 下午8:20
 * 此类用于：吐司工具类
 */

public class CustomToast extends ToastCompat {
    private Toast   lastInstance;
    private Context context;

    private static String  oldMsg;
    private static long    oneTime = 0;
    private static long    twoTime = 0;
    private static boolean isEnableShow;


    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public CustomToast(Context context) {
        super(context);
        this.context = context.getApplicationContext();
    }

    public static CustomToast makeCustomText(Context context, CharSequence text,
                                             int duration) {
        context = context.getApplicationContext();
        CustomToast toast = new CustomToast(context);
        /*设置Toast的位置*/
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(duration);
        /*让Toast显示为我们自定义的样子*/
        toast.setView(getToastView(context, text));


        twoTime = System.currentTimeMillis();
        if (text.equals(oldMsg)) {
            if (twoTime - oneTime > 2000) {
                isEnableShow = true;
                oneTime = twoTime;
            }else {
                isEnableShow = false;
            }
        } else {
            oldMsg = String.valueOf(text);
            isEnableShow = true;
            oneTime = twoTime;
        }


//        try {
//            Object mTN = getField(toast.getClass().getSuperclass(), toast, "mTN");
//            if (mTN != null) {
//                Object mParams = getField(mTN.getClass(), mTN, "mParams");
//                if (mParams != null
//                        && mParams instanceof WindowManager.LayoutParams) {
//                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
//                    params.windowAnimations = R.style.anim_view;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return toast;
    }

    public static void toastShort(Context context, String msg){
        makeCustomText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toastShort( String msg){
        makeCustomText(RetailPosManager.sContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void show() {
        if (lastInstance != null) {
            lastInstance.cancel();
        }
        if(isEnableShow){
            super.show();
            lastInstance = this;
        }
    }

    public static CustomToast makeCustomText(Context context, int text,
                                             int duration) {
        return makeCustomText(context, context.getString(text), duration);
    }

    //获取toast布局文件和位置
    public static View getToastView(Context context, CharSequence msg) {
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.base_custom_toast, null);
//        RelayoutViewTool.relayoutViewWithScale(v,BaseApplication.mScreenWidthScale);
        TextView text = (TextView) v.findViewById(R.id.toast_text);
        text.setText(msg);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 70);
//        params.topMargin = PixelUtil.dp2px()
        params.bottomMargin = PixelUtil.dp2px(context, 100);
        text.setLayoutParams(params);
        return v;
    }

    public static int getWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    private static Object getField(Class<?> clz, Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = clz.getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }
}
