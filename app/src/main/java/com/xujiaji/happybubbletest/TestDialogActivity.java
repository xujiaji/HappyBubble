package com.xujiaji.happybubbletest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xujiaji.happybubble.BubbleDialog;
import com.xujiaji.happybubble.BubbleLayout;
import com.xujiaji.happybubble.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JiajiXu on 17-12-8.
 */

public class TestDialogActivity extends Activity implements View.OnClickListener
{
    /**
     * 左
     */
    private RadioButton mRbLeft;
    /**
     * 上
     */
    private RadioButton mRbTop;
    /**
     * 右
     */
    private RadioButton mRbRight;
    /**
     * 下
     */
    private RadioButton mRbBottom;

    private RadioButton mRbAuto;
    /**
     * Button
     */
    private Button mButton;
    /**
     * Button
     */
    private Button mButton2;
    /**
     * Button
     */
    private Button mButton3;
    /**
     * Button
     */
    private Button mButton4;
    /**
     * Button
     */
    private Button mButton5;
    /**
     * Button
     */
    private Button mButton6;
    /**
     * Button
     */
    private Button mButton7;
    /**
     * Button
     */
    private Button mButton8;
    /**
     * Button
     */
    private Button mButton9;
    /**
     * Button
     */
    private Button mButton10;
    /**
     * Button
     */
    private Button mButton11;
    /**
     * Button
     */
    private TextView mButton12;

    private CheckBox mCheckBoxThrough;
    private boolean isAuto;

    private BubbleDialog.Position mPosition = BubbleDialog.Position.TOP;
    private BubbleDialog mCurrentDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog);
        initView();
    }

    private void initView()
    {
        mRbLeft = (RadioButton) findViewById(R.id.rbLeft);
        mRbLeft.setOnClickListener(this);
        mRbTop = (RadioButton) findViewById(R.id.rbTop);
        mRbTop.setOnClickListener(this);
        mRbRight = (RadioButton) findViewById(R.id.rbRight);
        mRbRight.setOnClickListener(this);
        mRbBottom = (RadioButton) findViewById(R.id.rbBottom);
        mRbBottom.setOnClickListener(this);
        mRbAuto = (RadioButton) findViewById(R.id.rbAuto);
        mRbAuto.setOnClickListener(this);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton5.setOnClickListener(this);
        mButton6 = (Button) findViewById(R.id.button6);
        mButton6.setOnClickListener(this);
        mButton7 = (Button) findViewById(R.id.button7);
        mButton7.setOnClickListener(this);
        mButton8 = (Button) findViewById(R.id.button8);
        mButton8.setOnClickListener(this);
        mButton9 = (Button) findViewById(R.id.button9);
        mButton9.setOnClickListener(this);
        mButton10 = (Button) findViewById(R.id.button10);
        mButton10.setOnClickListener(this);
        mButton11 = (Button) findViewById(R.id.button11);
        mButton11.setOnClickListener(this);
        mButton12 = findViewById(R.id.button12);
        mButton12.setOnClickListener(this);
        mCheckBoxThrough = findViewById(R.id.checkBoxThrough);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            default:
                break;
            case R.id.rbLeft:
                mPosition = BubbleDialog.Position.LEFT;
                break;
            case R.id.rbTop:
                mPosition = BubbleDialog.Position.TOP;
                break;
            case R.id.rbRight:
                mPosition = BubbleDialog.Position.RIGHT;
                break;
            case R.id.rbBottom:
                mPosition = BubbleDialog.Position.BOTTOM;
                break;
            case R.id.rbAuto:
                isAuto = mRbAuto.isChecked();
                break;
        }

        if (mCurrentDialog != null && mCurrentDialog.isShowing())
        {
            mCurrentDialog.dismiss();
        }

        switch (v.getId())
        {
            default:
                break;
            case R.id.button:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton)
                        .setPosition(mPosition)
                        .autoPosition(isAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
                        .calBar(true);
                mCurrentDialog.show();

                break;
            case R.id.button2:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton2)
                        .setPosition(mPosition)
                        .autoPosition(isAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
                        .calBar(true);
                mCurrentDialog.show();

                break;
            case R.id.button3:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton3)
                        .setPosition(mPosition)
                        .autoPosition(isAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
                        .calBar(true);
                mCurrentDialog.show();

                break;
            case R.id.button4:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton4)
                        .setPosition(mPosition)
                        .setOffsetY(8)
                        .autoPosition(isAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
                        .calBar(true);
                mCurrentDialog.show();

                break;
            case R.id.button5:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view2, null))
                        .setClickedView(mButton5)
                        .setPosition(mPosition)
                        .autoPosition(isAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
                        .calBar(true);
                mCurrentDialog.show();
                break;
            case R.id.button6:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton6)
                        .setPosition(mPosition)
                        .autoPosition(isAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true);
                mCurrentDialog.show();
                break;
            case R.id.button7:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton7)
                        .setPosition(mPosition)
                        .calBar(true)
                        .autoPosition(isAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true);
                mCurrentDialog.show();

                break;
            case R.id.button8:
                BubbleLayout bl = new BubbleLayout(this);
                bl.setBubbleColor(Color.BLUE);
                bl.setShadowColor(Color.RED);
                bl.setLookLength(Util.dpToPx(this, 54));
                bl.setLookWidth(Util.dpToPx(this, 48));
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view5, null))
                        .setClickedView(mButton8)
                        .setPosition(mPosition)
                        .calBar(true)
                        .setBubbleLayout(bl)
                        .autoPosition(isAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true);
                mCurrentDialog.show();

                break;
            case R.id.button9:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton9)
                        .setPosition(mPosition)
                        .calBar(true)
                        .autoPosition(isAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true);
                mCurrentDialog.show();

                break;
            case R.id.button10:
                CustomOperateDialog codDialog = new CustomOperateDialog(this)
                        .setPosition(mPosition)
                        .setClickedView(mButton10);
                codDialog.setClickListener(new CustomOperateDialog.OnClickCustomButtonListener()
                {
                    @Override
                    public void onClick(String str)
                    {
                        mButton10.setText("点击了：" + str);
                    }
                });
                codDialog.autoPosition(isAuto);
                codDialog.setThroughEvent(mCheckBoxThrough.isChecked(), true);
                mCurrentDialog = codDialog;
                mCurrentDialog.show();
                break;
            case R.id.button11:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton11)
                        .setPosition(mPosition)
                        .autoPosition(isAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
                        .calBar(true);

                mCurrentDialog.show();

                break;
            case R.id.button12:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view, null))
                        .setClickedView(mButton12)
                        .setPosition(mPosition)
                        .calBar(true)
                        .softShowUp()
                        .autoPosition(isAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true);
                mCurrentDialog.show();

                break;
        }
    }
}
