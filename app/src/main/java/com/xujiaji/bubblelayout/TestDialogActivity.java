package com.xujiaji.bubblelayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xujiaji.bubble.BubbleDialog;

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

    private BubbleDialog.Position mPosition = BubbleDialog.Position.TOP;

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
            case R.id.button:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton)
                        .setPosition(mPosition)
                        .calBar(true)
                        .show();

                break;
            case R.id.button2:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton2)
                        .setPosition(mPosition)
                        .calBar(true)
                        .show();

                break;
            case R.id.button3:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton3)
                        .setPosition(mPosition)
                        .calBar(true)
                        .show();

                break;
            case R.id.button4:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton4)
                        .setPosition(mPosition)
                        .setOffsetY(8)
                        .calBar(true)
                        .show();

                break;
            case R.id.button5:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view2, null))
                        .setClickedView(mButton5)
                        .setPosition(mPosition)
                        .calBar(true)
                        .show();
                break;
            case R.id.button6:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton6)
                        .setPosition(mPosition)
                        .calBar(true)
                        .show();

                break;
            case R.id.button7:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton7)
                        .setPosition(mPosition)
                        .calBar(true)
                        .show();

                break;
            case R.id.button8:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton8)
                        .setPosition(mPosition)
                        .calBar(true)
                        .show();

                break;
            case R.id.button9:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton9)
                        .setPosition(mPosition)
                        .calBar(true)
                        .show();

                break;
            case R.id.button10:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton10)
                        .setPosition(mPosition)
                        .calBar(true)
                        .show();

                break;
            case R.id.button11:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton11)
                        .setPosition(mPosition)
                        .calBar(true)
                        .show();

                break;
            case R.id.button12:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view, null))
                        .setClickedView(mButton12)
                        .setPosition(mPosition)
                        .calBar(true)
                        .softShowUp()
                        .show();

                break;
        }
    }
}
