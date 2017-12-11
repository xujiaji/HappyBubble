package com.xujiaji.happybubbletest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.xujiaji.happybubble.BubbleDialog;

/**
 * 自定义可操作性dialog
 * Created by JiajiXu on 17-12-11.
 */

public class CustomOperateDialog extends BubbleDialog implements View.OnClickListener
{
    private ViewHolder mViewHolder;
    private OnClickCustomButtonListener mListener;

    public CustomOperateDialog(Context context)
    {
        super(context);
        calBar(true);
        setTransParentBackground();
        setPosition(Position.TOP);
        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_view4, null);
        mViewHolder = new ViewHolder(rootView);
        addContentView(rootView);
        mViewHolder.btn13.setOnClickListener(this);
        mViewHolder.btn14.setOnClickListener(this);
        mViewHolder.btn15.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (mListener != null)
        {
            mListener.onClick(((Button)v).getText().toString());
        }
    }

    private static class ViewHolder
    {
        Button btn13, btn14, btn15;
        public ViewHolder(View rootView)
        {
            btn13 = rootView.findViewById(R.id.button13);
            btn14 = rootView.findViewById(R.id.button14);
            btn15 = rootView.findViewById(R.id.button15);
        }
    }

    public void setClickListener(OnClickCustomButtonListener l)
    {
        this.mListener = l;
    }

    public interface OnClickCustomButtonListener
    {
        void onClick(String str);
    }
}
