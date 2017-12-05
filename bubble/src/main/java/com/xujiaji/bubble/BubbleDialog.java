package com.xujiaji.bubble;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by JiajiXu on 17-12-4.
 */

public class BubbleDialog extends Dialog
{
    private BubbleLayout mBubbleLayout;
    private View mAddView;//需要添加的view
    private View mClickedView;//点击的View
    private boolean mCalBar;//计算中是否包含状态栏
    private int mOffsetX, mOffsetY;//x和y方向的偏移

    public BubbleDialog(Context context)
    {
        super(context, R.style.bubble_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mBubbleLayout = new BubbleLayout(getContext());
        if (mAddView != null)
        {
            mBubbleLayout.addView(mAddView);
        }
        setContentView(mBubbleLayout);

        Window window = getWindow();
        if (window == null) return;

        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBubbleLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                dialogPosition();
            }
        });

    }

    private void dialogPosition()
    {
        if (mClickedView == null)
        {
            throw new RuntimeException("Please add the clicked view.");
        }

        int[] clickedViewLocation = new int[2];
        mClickedView.getLocationOnScreen(clickedViewLocation);
        Window window = getWindow();
        if (window == null) return;
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = clickedViewLocation[0] + mClickedView.getWidth() / 2 - mBubbleLayout.getWidth() / 2;
        params.y = clickedViewLocation[1] - (mCalBar ? Util.getStatusHeight(getContext()) : 0) - mBubbleLayout.getHeight() + mOffsetY;

        if (params.x <= 0)
        {
            mBubbleLayout.setLookPosition(clickedViewLocation[0] + mClickedView.getWidth() / 2 - mBubbleLayout.getLookWidth() / 2);
        } else if (params.x + mBubbleLayout.getWidth() > Util.getScreenWH(getContext())[0])
        {
            mBubbleLayout.setLookPosition(clickedViewLocation[0] - (Util.getScreenWH(getContext())[0] - mBubbleLayout.getWidth()) + mClickedView.getWidth() / 2 - mBubbleLayout.getLookWidth() / 2);
        } else
        {
            mBubbleLayout.setLookPosition(clickedViewLocation[0] - params.x + mClickedView.getWidth() / 2 - mBubbleLayout.getLookWidth() / 2);
        }
        mBubbleLayout.setLook(BubbleLayout.Look.BOTTOM);
        mBubbleLayout.invalidate();
        window.setAttributes(params);
    }

    public BubbleDialog calBar(boolean cal)
    {
        this.mCalBar = cal;
        return this;
    }

    public BubbleDialog setClickedView(View view)
    {
        this.mClickedView = view;
        return this;
    }
    
    public BubbleDialog addContentView(View view)
    {
        this.mAddView = view;
        return this;
    }

    public BubbleDialog setOffsetX(int offsetX)
    {
        this.mOffsetX = offsetX;
        return this;
    }

    public BubbleDialog setOffsetY(int offsetY)
    {
        this.mOffsetY = offsetY;
        return this;
    }

    public BubbleDialog setTransParentBackground()
    {
        Window window = getWindow();
        if (window == null) return this;
        window.setDimAmount(0);
        return this;
    }
}
