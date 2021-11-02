package com.xujiaji.happybubble;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * Created by JiajiXu on 17-12-4.
 */

public class Util
{

    public static int dpToPx(Context context, float dipValue)
    {
        float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 获取状态栏的高度
     */
    public static int getStatusHeight(Context context)
    {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }
  /**
     * 通过Activity的内容距离顶部高度来获取状态栏高度，该方法获取需要在onWindowFocusChanged回调之后
     *
     * @param activity
     * @return
     */

    public static int getStatusBarByTop(Activity activity) {
        Rect rect = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }
    /**
     * 获取屏幕宽高
     */
    public static int[] getScreenWH(Context context)
    {
        WindowManager manager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return new int[]{outMetrics.widthPixels, outMetrics.heightPixels};
    }

    /**
     * 隐藏软键盘
     */
    public static void hide(Dialog dialog)
    {
        View view = dialog.getCurrentFocus();
        if (view instanceof TextView)
        {
            InputMethodManager mInputMethodManager = (InputMethodManager) dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

}
