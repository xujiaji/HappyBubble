package com.xujiaji.happybubble;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by JiajiXu on 17-12-4.
 */

public class BubbleDialog extends Dialog
{
    /**
     * 气泡位置
     */
    public enum Position
    {
        /**
         * 左边
         */
        LEFT,
        /**
         * 上边
         */
        TOP,
        /**
         * 右边
         */
        RIGHT,
        /**
         * 下边
         */
        BOTTOM
    }

    private BubbleLayout mBubbleLayout;
    private View mAddView;//需要添加的view
    private View mClickedView;//点击的View
    private boolean mCalBar;//计算中是否包含状态栏
    private int mOffsetX, mOffsetY;//x和y方向的偏移
    private boolean mSoftShowUp;//当软件盘弹出时Dialog上移
    private Position mPosition = Position.TOP;//气泡位置，默认上位

    public BubbleDialog(Context context)
    {
        super(context, R.style.bubble_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (mBubbleLayout == null)
        {
            mBubbleLayout = new BubbleLayout(getContext());
        }
        if (mAddView != null)
        {
            mBubbleLayout.addView(mAddView);
        }
        setContentView(mBubbleLayout);

        final Window window = getWindow();
        if (window == null) return;
        if (mSoftShowUp)
        {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLook();
        mBubbleLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                dialogPosition();
            }
        });
    }

    private void setLook()
    {
        switch (mPosition)
        {
            case LEFT:
                mBubbleLayout.setLook(BubbleLayout.Look.RIGHT);
                break;
            case TOP:
                mBubbleLayout.setLook(BubbleLayout.Look.BOTTOM);
                break;
            case RIGHT:
                mBubbleLayout.setLook(BubbleLayout.Look.LEFT);
                break;
            case BOTTOM:
                mBubbleLayout.setLook(BubbleLayout.Look.TOP);
                break;
        }
        mBubbleLayout.initPadding();
    }

    @Override
    public void dismiss()
    {
        if (mSoftShowUp)
        {
            Util.hide(BubbleDialog.this);
        }
        super.dismiss();
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
        switch (mPosition)
        {
            case TOP:
            case BOTTOM:
                params.x = clickedViewLocation[0] + mClickedView.getWidth() / 2 - mBubbleLayout.getWidth() / 2 + mOffsetX;
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
                if (mPosition == Position.BOTTOM)
                {
                    params.y = clickedViewLocation[1] - (mCalBar ? Util.getStatusHeight(getContext()) : 0) + mClickedView.getHeight() + mOffsetY;

                } else
                {
                    params.y = clickedViewLocation[1] - (mCalBar ? Util.getStatusHeight(getContext()) : 0) - mBubbleLayout.getHeight() + mOffsetY;

                }
                break;
            case LEFT:
            case RIGHT:
                params.y = clickedViewLocation[1] - (mCalBar ? Util.getStatusHeight(getContext()) : 0) + mOffsetY + mClickedView.getHeight() / 2 - mBubbleLayout.getHeight() / 2;
                if (params.y <= 0)
                {
                    mBubbleLayout.setLookPosition(clickedViewLocation[1] + mClickedView.getHeight() / 2 - mBubbleLayout.getLookWidth() / 2);
                } else if (params.y + mBubbleLayout.getHeight() > Util.getScreenWH(getContext())[1])
                {
                    mBubbleLayout.setLookPosition(clickedViewLocation[1] - (Util.getScreenWH(getContext())[1] - mBubbleLayout.getHeight() + mClickedView.getHeight() / 2 - mBubbleLayout.getLookWidth() / 2));
                } else
                {
                    mBubbleLayout.setLookPosition(clickedViewLocation[1] - params.y + mClickedView.getHeight() / 2 - mBubbleLayout.getLookWidth()/ 2 - (mCalBar ? Util.getStatusHeight(getContext()) : 0));
                }
                if (mPosition == Position.RIGHT)
                {

                    params.x = clickedViewLocation[0] + mClickedView.getWidth() + mOffsetX;
                } else
                {

                    params.x = clickedViewLocation[0] -  mBubbleLayout.getWidth() + mOffsetX;
                }
                break;
        }


        mBubbleLayout.invalidate();
        window.setAttributes(params);
    }

    /**
     * 计算时是否包含状态栏(如果有状态栏目，而没有设置为true将会出现上下的偏差)
     */
    public <T extends BubbleDialog> T calBar(boolean cal)
    {
        this.mCalBar = cal;
        return (T) this;
    }

    /**
     * 设置被点击的view来设置弹出dialog的位置
     */
    public <T extends BubbleDialog> T setClickedView(View view)
    {
        this.mClickedView = view;
        return (T) this;
    }

    /**
     * 当软件键盘弹出时，dialog根据条件上移
     */
    public <T extends BubbleDialog> T softShowUp()
    {
        this.mSoftShowUp = true;
        return (T) this;
    }

    /**
     * 设置dialog内容view
     */
    public <T extends BubbleDialog> T addContentView(View view)
    {
        this.mAddView = view;
        return (T) this;
    }

    public <T extends BubbleDialog> T setPosition(Position position)
    {
        this.mPosition = position;
        return (T) this;
    }

    /**
     * 设置x方向偏移量
     */
    public <T extends BubbleDialog> T setOffsetX(int offsetX)
    {
        this.mOffsetX = Util.dpToPx(getContext(), offsetX);
        return (T) this;
    }

    /**
     * 设置y方向偏移量
     */
    public <T extends BubbleDialog> T setOffsetY(int offsetY)
    {
        this.mOffsetY = Util.dpToPx(getContext(), offsetY);
        return (T) this;
    }

    /**
     * 自定义气泡布局
     */
    public <T extends BubbleDialog> T setBubbleLayout(BubbleLayout bl)
    {
        this.mBubbleLayout = bl;
        return (T) this;
    }

    /**
     * 背景全透明
     */
    public <T extends BubbleDialog> T setTransParentBackground()
    {
        Window window = getWindow();
        if (window == null) return (T) this;
        window.setDimAmount(0);
        return (T) this;
    }
}
