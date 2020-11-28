package com.xujiaji.happybubble;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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

public class BubbleLayout extends FrameLayout {
    private Paint mPaint;
    private Path mPath;
    private Look mLook;
    private int mBubblePadding;
    private int mWidth, mHeight;
    private int mLeft, mTop, mRight, mBottom;
    private int mLookPosition, mLookWidth, mLookLength;
    private int mShadowColor, mShadowRadius, mShadowX, mShadowY;
    private int mBubbleRadius, mBubbleColor;
    // 坐上弧度，右上弧度，右下弧度，左下弧度
    private int mLTR, mRTR, mRDR, mLDR;
    // 箭头
    //     箭头尖分左右两个弧度分别是由 mArrowTopLeftRadius, mArrowTopRightRadius 控制
    //     箭头底部左右两个弧度分别是由 mArrowDownLeftRadius, mArrowDownRightRadius 控制
    private int mArrowTopLeftRadius, mArrowTopRightRadius, mArrowDownLeftRadius, mArrowDownRightRadius;

    private OnClickEdgeListener mListener;
    private Region mRegion = new Region();
    // 气泡背景图
    private Bitmap mBubbleImageBg = null;
    // 气泡背景显示区域
    private RectF mBubbleImageBgRectF = new RectF();
    private Paint mBubbleImageBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    /**
     * 箭头指向
     */
    public enum Look {
        /**
         * 坐上右下
         */
        LEFT(1), TOP(2), RIGHT(3), BOTTOM(4);
        int value;

        Look(int v) {
            value = v;
        }

        public static Look getType(int value) {
            Look type = Look.BOTTOM;
            switch (value) {
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


    public BubbleLayout(Context context) {
        this(context, null);
    }

    public BubbleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        setWillNotDraw(false);
        initAttr(context.obtainStyledAttributes(attrs, R.styleable.BubbleLayout, defStyleAttr, 0));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
        mBubbleImageBgPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        initPadding();
    }

    public void initPadding() {
        int p = mBubblePadding * 2;
        switch (mLook) {
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
    private void initAttr(TypedArray a) {
        mLook = Look.getType(a.getInt(R.styleable.BubbleLayout_lookAt, Look.BOTTOM.value));
        mLookPosition = a.getDimensionPixelOffset(R.styleable.BubbleLayout_lookPosition, 0);
        mLookWidth = a.getDimensionPixelOffset(R.styleable.BubbleLayout_lookWidth, Util.dpToPx(getContext(), 13F));
        mLookLength = a.getDimensionPixelOffset(R.styleable.BubbleLayout_lookLength, Util.dpToPx(getContext(), 12F));
        mShadowRadius = a.getDimensionPixelOffset(R.styleable.BubbleLayout_shadowRadius, Util.dpToPx(getContext(), 3.3F));
        mShadowX = a.getDimensionPixelOffset(R.styleable.BubbleLayout_shadowX, Util.dpToPx(getContext(), 1F));
        mShadowY = a.getDimensionPixelOffset(R.styleable.BubbleLayout_shadowY, Util.dpToPx(getContext(), 1F));

        mBubbleRadius = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubbleRadius, Util.dpToPx(getContext(), 8F));
        mLTR = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubbleLeftTopRadius, -1);
        mRTR = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubbleRightTopRadius, -1);
        mRDR = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubbleRightDownRadius, -1);
        mLDR = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubbleLeftDownRadius, -1);
        
        mArrowTopLeftRadius   = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubbleArrowTopLeftRadius,  Util.dpToPx(getContext(), 3F));
        mArrowTopRightRadius  = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubbleArrowTopRightRadius, Util.dpToPx(getContext(), 3F));
        mArrowDownLeftRadius  = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubbleArrowDownLeftRadius, Util.dpToPx(getContext(), 6F));
        mArrowDownRightRadius = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubbleArrowDownRightRadius, Util.dpToPx(getContext(), 6F));

        mBubblePadding = a.getDimensionPixelOffset(R.styleable.BubbleLayout_bubblePadding, Util.dpToPx(getContext(), 8));
        mShadowColor = a.getColor(R.styleable.BubbleLayout_shadowColor, Color.GRAY);
        mBubbleColor = a.getColor(R.styleable.BubbleLayout_bubbleColor, Color.WHITE);

        final int bubbleBgRes = a.getResourceId(R.styleable.BubbleLayout_bubbleBgRes, -1);
        if (bubbleBgRes != -1) {
            mBubbleImageBg = BitmapFactory.decodeResource(getResources(), bubbleBgRes);
        }
        a.recycle();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        initData();
    }

    @Override
    public void invalidate() {
        initData();
        super.invalidate();
    }

    @Override
    public void postInvalidate() {
        initData();
        super.postInvalidate();
    }

    /**
     * 初始化数据
     */
    private void initData() {
//        mPaint.setPathEffect(new CornerPathEffect(mBubbleRadius));
        mPaint.setShadowLayer(mShadowRadius, mShadowX, mShadowY, mShadowColor);

        mLeft = mBubblePadding + (mLook == Look.LEFT ? mLookLength : 0);
        mTop = mBubblePadding + (mLook == Look.TOP ? mLookLength : 0);
        mRight = mWidth - mBubblePadding - (mLook == Look.RIGHT ? mLookLength : 0);
        mBottom = mHeight - mBubblePadding - (mLook == Look.BOTTOM ? mLookLength : 0);
        mPaint.setColor(mBubbleColor);

        mPath.reset();

        int topOffset = (topOffset = mLookPosition) + mLookLength > mBottom ? mBottom - mLookWidth : topOffset;
        topOffset = Math.max(topOffset, mBubblePadding);
        int leftOffset = (leftOffset = mLookPosition) + mLookLength > mRight ? mRight - mLookWidth : leftOffset;
        leftOffset = Math.max(leftOffset, mBubblePadding);

        switch (mLook) {
            case LEFT:
                // 判断是否足够画箭头，偏移的量 > 气泡圆角 + 气泡箭头下右圆弧
                if (topOffset > getLTR() * 2 + mArrowDownRightRadius) {
                    mPath.moveTo(mLeft, topOffset - mArrowDownRightRadius);
                    mPath.rCubicTo(0F, mArrowDownRightRadius,
                            -mLookLength, mLookWidth / 2F - mArrowTopRightRadius + mArrowDownRightRadius,
                            -mLookLength, mLookWidth / 2F + mArrowDownRightRadius);
                } else {
                    // 起点移动到箭头尖
                    mPath.moveTo(mLeft - mLookLength, topOffset + mLookWidth / 2F);
                }

                // 判断是否足够画箭头，偏移的量 + 箭头宽 <= 气泡高 - 气泡圆角 - 气泡箭头下右圆弧
                if (topOffset + mLookWidth < mBottom - getLDR() - mArrowDownLeftRadius) {
                    mPath.rCubicTo(0F, mArrowTopLeftRadius,
                            mLookLength, mLookWidth / 2F,
                            mLookLength, mLookWidth / 2F + mArrowDownLeftRadius);
                    mPath.lineTo(mLeft, mBottom - getLDR());
                }
                mPath.quadTo(mLeft, mBottom,
                        mLeft + getLDR(), mBottom);
                mPath.lineTo(mRight - getRDR(), mBottom);
                mPath.quadTo(mRight, mBottom, mRight, mBottom - getRDR());
                mPath.lineTo(mRight, mTop + getRTR());
                mPath.quadTo(mRight, mTop, mRight - getRTR(), mTop);
                mPath.lineTo(mLeft + getLTR(), mTop);
                if (topOffset > getLTR() * 2 + mArrowDownRightRadius) {
                    mPath.quadTo(mLeft, mTop, mLeft, mTop + getLTR());
                } else {
                    mPath.quadTo(mLeft, mTop, mLeft - mLookLength, topOffset + mLookWidth / 2F);
                }
                break;
            case TOP:
                if (leftOffset > getLTR() * 2 + mArrowDownLeftRadius) {
                    mPath.moveTo(leftOffset - mArrowDownLeftRadius, mTop);
                    mPath.rCubicTo(mArrowDownLeftRadius, 0,
                            mLookWidth / 2F - mArrowTopLeftRadius + mArrowDownLeftRadius, -mLookLength,
                            mLookWidth / 2F + mArrowDownLeftRadius, -mLookLength);
                } else {
                    mPath.moveTo(leftOffset + mLookWidth / 2F, mTop - mLookLength);
                }

                if (leftOffset + mLookWidth < mRight - getRTR() - mArrowDownRightRadius) {
                    mPath.rCubicTo(mArrowTopRightRadius, 0F,
                            mLookWidth / 2F, mLookLength,
                            mLookWidth / 2F + mArrowDownRightRadius, mLookLength);
                    mPath.lineTo(mRight - getRTR(), mTop);
                }
                mPath.quadTo(mRight, mTop, mRight, mTop + getRTR());
                mPath.lineTo(mRight, mBottom - getRDR());
                mPath.quadTo(mRight, mBottom, mRight - getRDR(), mBottom);
                mPath.lineTo(mLeft + getLDR(), mBottom);
                mPath.quadTo(mLeft, mBottom, mLeft, mBottom - getLDR());
                mPath.lineTo(mLeft, mTop + getLTR());
                if (leftOffset > getLTR() * 2 + mArrowDownLeftRadius) {
                    mPath.quadTo(mLeft, mTop, mLeft + getLTR(), mTop);
                } else {
                    mPath.quadTo(mLeft, mTop, leftOffset + mLookWidth / 2F, mTop - mLookLength);
                }
                break;
            case RIGHT:
                if (topOffset > getRTR() * 2 + mArrowDownLeftRadius) {
                    mPath.moveTo(mRight, topOffset - mArrowDownLeftRadius);
                    mPath.rCubicTo(0, mArrowDownLeftRadius,
                            mLookLength, mLookWidth / 2F - mArrowTopLeftRadius + mArrowDownLeftRadius,
                            mLookLength, mLookWidth / 2F + mArrowDownLeftRadius);
                } else {
                    mPath.moveTo(mRight + mLookLength, topOffset + mLookWidth / 2F);
                }

                if (topOffset + mLookWidth < mBottom - getRDR() - mArrowDownRightRadius) {
                    mPath.rCubicTo(0F, mArrowTopRightRadius,
                            -mLookLength, mLookWidth / 2F,
                            -mLookLength, mLookWidth / 2F + mArrowDownRightRadius);
                    mPath.lineTo(mRight, mBottom - getRDR());
                }
                mPath.quadTo(mRight, mBottom,
                        mRight - getRDR(), mBottom);
                mPath.lineTo(mLeft + getLDR(), mBottom);
                mPath.quadTo(mLeft, mBottom, mLeft, mBottom - getLDR());
                mPath.lineTo(mLeft, mTop + getLTR());
                mPath.quadTo(mLeft, mTop, mLeft + getLTR(), mTop);
                mPath.lineTo(mRight - getRTR(), mTop);
                if (topOffset > getRTR() * 2 + mArrowDownLeftRadius) {
                    mPath.quadTo(mRight, mTop, mRight, mTop + getRTR());
                } else {
                    mPath.quadTo(mRight, mTop, mRight + mLookLength, topOffset + mLookWidth / 2F);
                }
                break;
            case BOTTOM:
                if (leftOffset > getLDR() * 2 + mArrowDownRightRadius) {
                    mPath.moveTo(leftOffset - mArrowDownRightRadius, mBottom);
                    mPath.rCubicTo(mArrowDownRightRadius, 0,
                            mLookWidth / 2F - mArrowTopRightRadius + mArrowDownRightRadius, mLookLength,
                            mLookWidth / 2F + mArrowDownRightRadius, mLookLength);
                } else {
                    mPath.moveTo(leftOffset + mLookWidth / 2F, mBottom + mLookLength);
                }

                if (leftOffset + mLookWidth < mRight - getRDR() - mArrowDownLeftRadius) {
                    mPath.rCubicTo(mArrowTopLeftRadius, 0F,
                            mLookWidth / 2F, -mLookLength,
                            mLookWidth / 2F + mArrowDownLeftRadius, -mLookLength);
                    mPath.lineTo(mRight - getRDR(), mBottom);
                }
                mPath.quadTo(mRight, mBottom, mRight, mBottom - getRDR());
                mPath.lineTo(mRight, mTop + getRTR());
                mPath.quadTo(mRight, mTop, mRight - getRTR(), mTop);
                mPath.lineTo(mLeft + getLTR(), mTop);
                mPath.quadTo(mLeft, mTop, mLeft, mTop + getLTR());
                mPath.lineTo(mLeft, mBottom - getLDR());
                if (leftOffset > getLDR() * 2 + mArrowDownRightRadius) {
                    mPath.quadTo(mLeft, mBottom, mLeft + getLDR(), mBottom);
                } else {
                    mPath.quadTo(mLeft, mBottom, leftOffset + mLookWidth / 2F, mBottom + mLookLength);
                }
                break;
        }

        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        if (mBubbleImageBg != null) {
            mPath.computeBounds(mBubbleImageBgRectF, true);

            canvas.drawBitmap(mBubbleImageBg, null, mBubbleImageBgRectF, mBubbleImageBgPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            RectF r = new RectF();
            mPath.computeBounds(r, true);
            mRegion.setPath(mPath, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
            if (!mRegion.contains((int) event.getX(), (int) event.getY()) && mListener != null) {
                mListener.edge();
            }
        }
        return super.onTouchEvent(event);
    }

    public Paint getPaint() {
        return mPaint;
    }

    public Path getPath() {
        return mPath;
    }

    public Look getLook() {
        return mLook;
    }

    public int getLookPosition() {
        return mLookPosition;
    }

    public int getLookWidth() {
        return mLookWidth;
    }

    public int getLookLength() {
        return mLookLength;
    }

    public int getShadowColor() {
        return mShadowColor;
    }

    public int getShadowRadius() {
        return mShadowRadius;
    }

    public int getShadowX() {
        return mShadowX;
    }

    public int getShadowY() {
        return mShadowY;
    }

    public int getBubbleRadius() {
        return mBubbleRadius;
    }

    public int getBubbleColor() {
        return mBubbleColor;
    }

    public void setBubbleColor(int mBubbleColor) {
        this.mBubbleColor = mBubbleColor;
    }

    public void setLook(Look mLook) {
        this.mLook = mLook;
        initPadding();
    }

    public void setLookPosition(int mLookPosition) {
        this.mLookPosition = mLookPosition;
    }

    public void setLookWidth(int mLookWidth) {
        this.mLookWidth = mLookWidth;
    }

    public void setLookLength(int mLookLength) {
        this.mLookLength = mLookLength;
        initPadding();
    }

    public void setShadowColor(int mShadowColor) {
        this.mShadowColor = mShadowColor;
    }

    public void setShadowRadius(int mShadowRadius) {
        this.mShadowRadius = mShadowRadius;
    }

    public void setShadowX(int mShadowX) {
        this.mShadowX = mShadowX;
    }

    public void setShadowY(int mShadowY) {
        this.mShadowY = mShadowY;
    }

    public void setBubbleRadius(int mBubbleRadius) {
        this.mBubbleRadius = mBubbleRadius;
    }

    public int getLTR() {
        return mLTR == -1 ? mBubbleRadius : mLTR;
    }

    public void setLTR(int mLTR) {
        this.mLTR = mLTR;
    }

    public int getRTR() {
        return mRTR == -1 ? mBubbleRadius : mRTR;
    }

    public void setRTR(int mRTR) {
        this.mRTR = mRTR;
    }

    public int getRDR() {
        return mRDR == -1 ? mBubbleRadius : mRDR;
    }

    public void setRDR(int mRDR) {
        this.mRDR = mRDR;
    }

    public int getLDR() {
        return mLDR == -1 ? mBubbleRadius : mLDR;
    }

    public void setLDR(int mLDR) {
        this.mLDR = mLDR;
    }

    public int getArrowTopLeftRadius() {
        return mArrowTopLeftRadius;
    }

    public void setArrowTopLeftRadius(int mArrowTopLeftRadius) {
        this.mArrowTopLeftRadius = mArrowTopLeftRadius;
    }

    public int getArrowTopRightRadius() {
        return mArrowTopRightRadius;
    }

    public void setArrowTopRightRadius(int mArrowTopRightRadius) {
        this.mArrowTopRightRadius = mArrowTopRightRadius;
    }

    public int getArrowDownLeftRadius() {
        return mArrowDownLeftRadius;
    }

    public void setArrowDownLeftRadius(int mArrowDownLeftRadius) {
        this.mArrowDownLeftRadius = mArrowDownLeftRadius;
    }

    public int getArrowDownRightRadius() {
        return mArrowDownRightRadius;
    }

    public void setArrowDownRightRadius(int mArrowDownRightRadius) {
        this.mArrowDownRightRadius = mArrowDownRightRadius;
    }

    /**
     * 设置背景图片
     * @param bitmap 图片
     */
    public void setBubbleImageBg(Bitmap bitmap) {
        mBubbleImageBg = bitmap;
    }

    /**
     * 设置背景图片资源
     * @param res 图片资源
     */
    public void setBubbleImageBgRes(int res) {
        mBubbleImageBg = BitmapFactory.decodeResource(getResources(), res);
    }

    public Parcelable onSaveInstanceState() {
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

        bundle.putInt("mLTR", this.mLTR);
        bundle.putInt("mRTR", this.mRTR);
        bundle.putInt("mRDR", this.mRDR);
        bundle.putInt("mLDR", this.mLDR);

        bundle.putInt("mArrowTopLeftRadius", this.mArrowTopLeftRadius);
        bundle.putInt("mArrowTopRightRadius", this.mArrowTopRightRadius);
        bundle.putInt("mArrowDownLeftRadius", this.mArrowDownLeftRadius);
        bundle.putInt("mArrowDownRightRadius", this.mArrowDownRightRadius);

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
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.mLookPosition = bundle.getInt("mLookPosition");
            this.mLookWidth = bundle.getInt("mLookWidth");
            this.mLookLength = bundle.getInt("mLookLength");
            this.mShadowColor = bundle.getInt("mShadowColor");
            this.mShadowRadius = bundle.getInt("mShadowRadius");
            this.mShadowX = bundle.getInt("mShadowX");
            this.mShadowY = bundle.getInt("mShadowY");
            this.mBubbleRadius = bundle.getInt("mBubbleRadius");

            this.mLTR = bundle.getInt("mLTR");
            this.mRTR = bundle.getInt("mRTR");
            this.mRDR = bundle.getInt("mRDR");
            this.mLDR = bundle.getInt("mLDR");

            this.mArrowTopLeftRadius = bundle.getInt("mArrowTopLeftRadius");
            this.mArrowTopRightRadius = bundle.getInt("mArrowTopRightRadius");
            this.mArrowDownLeftRadius = bundle.getInt("mArrowDownLeftRadius");
            this.mArrowDownRightRadius = bundle.getInt("mArrowDownRightRadius");

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

    public void setOnClickEdgeListener(OnClickEdgeListener l) {
        this.mListener = l;
    }

    /**
     * 触摸到气泡的边缘
     */
    public interface OnClickEdgeListener {
        void edge();
    }

}
