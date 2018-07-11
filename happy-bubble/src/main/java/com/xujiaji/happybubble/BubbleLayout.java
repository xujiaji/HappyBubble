package com.xujiaji.happybubble;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * 气泡布局
 * Created by JiajiXu on 17-12-1.
 */

public class BubbleLayout extends FrameLayout
{
    private Paint mPaint;
    private Path mPath;
    private Look mLook;
    private int mBubblePadding;
    private int mWidth, mHeight;
    private int mLeft, mTop, mRight, mBottom;
    private int mLookPosition, mLookWidth, mLookLength;
    private int mShadowColor, mShadowRadius, mShadowX, mShadowY;
    private int mBubbleRadius, mBubbleColor;
    private OnClickEdgeListener mListener;
    private Region mRegion = new Region();

    /**
     * 箭头指向
     */
    public enum Look
    {
        /**
         * 坐上右下
         */
        LEFT(1), TOP(2), RIGHT(3), BOTTOM(4);
        int value;

        Look(int v)
        {
            value = v;
        }

        public static Look getType(int value)
        {
            Look type = Look.BOTTOM;
            switch (value)
            {
                case 1:
                    type = Look.LEFT;
                    break;
                case 2:
                    type = Look.TOP;
                    break;
                case 3:
                    type = Look.RIGHT;
                    break;
                case 4:
                    type = Look.BOTTOM;
                    break;
            }

            return type;
        }
    }


    public BubbleLayout(Context context)
    {
        this(context, null);
    }

    public BubbleLayout(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public BubbleLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        setWillNotDraw(false);
        initAttr(context.obtainStyledAttributes(attrs, R.styleable.BubbleLayout, defStyleAttr, 0));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
        initPadding();
    }

    public void initPadding()
    {
        int p = mBubblePadding * 2;
        switch (mLook)
        {
            case BOTTOM:
                setPadding(p, p, p, mLookLength + p);
                break;
            case TOP:
                setPadding(p, p + mLookLength, p, p);
                break;
            case LEFT:
                setPadding(p + mLookLength, p, p, p);
                break;
            case RIGHT:
                setPadding(p, p, p + mLookLength, p);
                break;
        }
    }

    /**
     * 初始化参数
     */
    private void initAttr(TypedArray a)
    {
        mLook = Look.getType(a.getInt(R.styleable.BubbleLayout_lookAt, Look.BOTTOM.value));
        mLookPosition = a.getDimensionPixelOffset(R.styleable.BubbleLayout_lookPosition, 0);
        mLookWidth = a.getDimensionPixelOffset(R.styleable.BubbleLayout_lookWidth, Util.dpToPx(getContext(), 17F));
        mLookLength = a.getDimensionPixelOffset(R.styleable.BubbleLayout_lookLength, Util.dpToPx(getContext(), 17F));
        mShadowRadius = a.getDimensionPixelOffset(R.styleable.BubbleLayout_shadowRadius, Util.dpToPx(getContext(), 3.3F));
        mShadowX = a.getDimensionPixelOffset(R.styleable.BubbleLayout_shadowX, Util.dpToPx(getContext(), 1F));
        mShadowY = a.getDimensionPixelOffset(R.styleable.BubbleLayout_shadowY, Util.dpToPx(getContext(), 1F));
        mBubbleRadius = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubbleRadius, Util.dpToPx(getContext(), 7F));
        mBubblePadding = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubblePadding, Util.dpToPx(getContext(), 8));
        mShadowColor = a.getColor(R.styleable.BubbleLayout_shadowColor, Color.GRAY);
        mBubbleColor = a.getColor(R.styleable.BubbleLayout_bubbleColor, Color.WHITE);
        a.recycle();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        initData();
    }

    @Override
    public void invalidate()
    {
        initData();
        super.invalidate();
    }

    @Override
    public void postInvalidate()
    {
        initData();
        super.postInvalidate();
    }

    /**
     * 初始化数据
     */
    private void initData()
    {
        mPaint.setPathEffect(new CornerPathEffect(mBubbleRadius));
        mPaint.setShadowLayer(mShadowRadius, mShadowX, mShadowY, mShadowColor);

        mLeft = mBubblePadding + (mLook == Look.LEFT ? mLookLength : 0);
        mTop = mBubblePadding + (mLook == Look.TOP ? mLookLength : 0);
        mRight = mWidth - mBubblePadding - (mLook == Look.RIGHT ? mLookLength : 0);
        mBottom = mHeight - mBubblePadding - (mLook == Look.BOTTOM ? mLookLength : 0);
        mPaint.setColor(mBubbleColor);

        mPath.reset();

        int topOffset = (topOffset = mLookPosition) + mLookLength > mBottom ? mBottom - mLookWidth : topOffset;
        topOffset = topOffset > mBubblePadding ? topOffset : mBubblePadding;
        int leftOffset = (leftOffset = mLookPosition) + mLookLength > mRight ? mRight - mLookWidth : leftOffset;
        leftOffset = leftOffset > mBubblePadding ? leftOffset : mBubblePadding;

        switch (mLook)
        {
            case LEFT:
                mPath.moveTo(mLeft, topOffset);
                mPath.rLineTo(-mLookLength, mLookWidth / 2);
                mPath.rLineTo(mLookLength, mLookWidth / 2);
                mPath.lineTo(mLeft, mBottom);
                mPath.lineTo(mRight, mBottom);
                mPath.lineTo(mRight, mTop);
                mPath.lineTo(mLeft, mTop);
                break;
            case TOP:
                mPath.moveTo(leftOffset, mTop);
                mPath.rLineTo(mLookWidth / 2, -mLookLength);
                mPath.rLineTo(mLookWidth / 2, mLookLength);
                mPath.lineTo(mRight, mTop);
                mPath.lineTo(mRight, mBottom);
                mPath.lineTo(mLeft, mBottom);
                mPath.lineTo(mLeft, mTop);
                break;
            case RIGHT:
                mPath.moveTo(mRight, topOffset);
                mPath.rLineTo(mLookLength, mLookWidth / 2);
                mPath.rLineTo(-mLookLength, mLookWidth / 2);
                mPath.lineTo(mRight, mBottom);
                mPath.lineTo(mLeft, mBottom);
                mPath.lineTo(mLeft, mTop);
                mPath.lineTo(mRight, mTop);
                break;
            case BOTTOM:
                mPath.moveTo(leftOffset, mBottom);
                mPath.rLineTo(mLookWidth / 2, mLookLength);
                mPath.rLineTo(mLookWidth / 2, -mLookLength);
                mPath.lineTo(mRight, mBottom);
                mPath.lineTo(mRight, mTop);
                mPath.lineTo(mLeft, mTop);
                mPath.lineTo(mLeft, mBottom);
                break;
        }

        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            RectF r = new RectF();
            mPath.computeBounds(r, true);
            mRegion.setPath(mPath, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
            if (!mRegion.contains((int) event.getX(), (int) event.getY()))
            {
                mListener.edge();
            }
        }
        return super.onTouchEvent(event);
    }

    public Paint getPaint()
    {
        return mPaint;
    }

    public Path getPath()
    {
        return mPath;
    }

    public Look getLook()
    {
        return mLook;
    }

    public int getLookPosition()
    {
        return mLookPosition;
    }

    public int getLookWidth()
    {
        return mLookWidth;
    }

    public int getLookLength()
    {
        return mLookLength;
    }

    public int getShadowColor()
    {
        return mShadowColor;
    }

    public int getShadowRadius()
    {
        return mShadowRadius;
    }

    public int getShadowX()
    {
        return mShadowX;
    }

    public int getShadowY()
    {
        return mShadowY;
    }

    public int getBubbleRadius()
    {
        return mBubbleRadius;
    }

    public int getBubbleColor()
    {
        return mBubbleColor;
    }

    public void setBubbleColor(int mBubbleColor)
    {
        this.mBubbleColor = mBubbleColor;
    }

    public void setLook(Look mLook)
    {
        this.mLook = mLook;
        initPadding();
    }

    public void setLookPosition(int mLookPosition)
    {
        this.mLookPosition = mLookPosition;
    }

    public void setLookWidth(int mLookWidth)
    {
        this.mLookWidth = mLookWidth;
    }

    public void setLookLength(int mLookLength)
    {
        this.mLookLength = mLookLength;
        initPadding();
    }

    public void setShadowColor(int mShadowColor)
    {
        this.mShadowColor = mShadowColor;
    }

    public void setShadowRadius(int mShadowRadius)
    {
        this.mShadowRadius = mShadowRadius;
    }

    public void setShadowX(int mShadowX)
    {
        this.mShadowX = mShadowX;
    }

    public void setShadowY(int mShadowY)
    {
        this.mShadowY = mShadowY;
    }

    public void setBubbleRadius(int mBubbleRadius)
    {
        this.mBubbleRadius = mBubbleRadius;
    }


    public Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("mLookPosition", this.mLookPosition);
        bundle.putInt("mLookWidth", this.mLookWidth);
        bundle.putInt("mLookLength", this.mLookLength);
        bundle.putInt("mShadowColor", this.mShadowColor);
        bundle.putInt("mShadowRadius", this.mShadowRadius);
        bundle.putInt("mShadowX", this.mShadowX);
        bundle.putInt("mShadowY", this.mShadowY);
        bundle.putInt("mBubbleRadius", this.mBubbleRadius);
        bundle.putInt("mWidth", this.mWidth);
        bundle.putInt("mHeight", this.mHeight);
        bundle.putInt("mLeft", this.mLeft);
        bundle.putInt("mTop", this.mTop);
        bundle.putInt("mRight", this.mRight);
        bundle.putInt("mBottom", this.mBottom);
        return bundle;
    }

    //    private int mWidth, mHeight;
//    private int mLeft, mTop, mRight, mBottom;
    public void onRestoreInstanceState(Parcelable state)
    {
        if (state instanceof Bundle)
        {
            Bundle bundle = (Bundle) state;
            this.mLookPosition = bundle.getInt("mLookPosition");
            this.mLookWidth = bundle.getInt("mLookWidth");
            this.mLookLength = bundle.getInt("mLookLength");
            this.mShadowColor = bundle.getInt("mShadowColor");
            this.mShadowRadius = bundle.getInt("mShadowRadius");
            this.mShadowX = bundle.getInt("mShadowX");
            this.mShadowY = bundle.getInt("mShadowY");
            this.mBubbleRadius = bundle.getInt("mBubbleRadius");
            this.mWidth = bundle.getInt("mWidth");
            this.mHeight = bundle.getInt("mHeight");
            this.mLeft = bundle.getInt("mLeft");
            this.mTop = bundle.getInt("mTop");
            this.mRight = bundle.getInt("mRight");
            this.mBottom = bundle.getInt("mBottom");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void setOnClickEdgeListener(OnClickEdgeListener l)
    {
        this.mListener = l;
    }

    /**
     * 触摸到气泡的边缘
     */
    public interface OnClickEdgeListener
    {
        void edge();
    }

}
