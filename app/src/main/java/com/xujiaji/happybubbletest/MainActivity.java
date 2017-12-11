package com.xujiaji.happybubbletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

import com.xujiaji.happybubble.BubbleDialog;
import com.xujiaji.happybubble.BubbleLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener
{

    private BubbleLayout mBubbleLayout;
    private View mBtnDialogTop;
    private View csPurple;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView()
    {
        mBubbleLayout = findViewById(R.id.bubbleLayout);
        findViewById(R.id.rbLeft).setOnClickListener(this);
        findViewById(R.id.rbTop).setOnClickListener(this);
        findViewById(R.id.rbRight).setOnClickListener(this);
        findViewById(R.id.rbBottom).setOnClickListener(this);
        findViewById(R.id.cbWhite).setOnClickListener(this);
        findViewById(R.id.cbGrey).setOnClickListener(this);
        findViewById(R.id.cbBlack).setOnClickListener(this);
        findViewById(R.id.cbRed).setOnClickListener(this);
        findViewById(R.id.cbOrange).setOnClickListener(this);
        findViewById(R.id.cbBlue).setOnClickListener(this);
        findViewById(R.id.cbGreen).setOnClickListener(this);
        findViewById(R.id.cbPurple).setOnClickListener(this);
        findViewById(R.id.csWhite).setOnClickListener(this);
        findViewById(R.id.csGrey).setOnClickListener(this);
        findViewById(R.id.csBlack).setOnClickListener(this);
        findViewById(R.id.csRed).setOnClickListener(this);
        findViewById(R.id.csOrange).setOnClickListener(this);
        findViewById(R.id.csBlue).setOnClickListener(this);
        findViewById(R.id.csGreen).setOnClickListener(this);
        findViewById(R.id.tvNextPage).setOnClickListener(this);
        csPurple = findViewById(R.id.csPurple);
        csPurple.setOnClickListener(this);
        mBtnDialogTop = findViewById(R.id.btnDialogTop);
        mBtnDialogTop.setOnClickListener(this);

        ((SeekBar) findViewById(R.id.sbBubbleRadius)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.sbLookPosition)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.sbLookWidth)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.sbLookLength)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.sbShadowRadius)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.sbShadowX)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.sbShadowY)).setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b)
    {
        switch (seekBar.getId())
        {
            case R.id.sbBubbleRadius:
                mBubbleLayout.setBubbleRadius(dpTopx(i));
                break;
            case R.id.sbLookPosition:
                mBubbleLayout.setLookPosition(dpTopx(i));
                break;
            case R.id.sbLookWidth:
                mBubbleLayout.setLookWidth(dpTopx(i));
                break;
            case R.id.sbLookLength:
                mBubbleLayout.setLookLength(dpTopx(i));
                break;
            case R.id.sbShadowRadius:
                mBubbleLayout.setShadowRadius(dpTopx(i));
                break;
            case R.id.sbShadowX:
                mBubbleLayout.setShadowX(dpTopx(i));
                break;
            case R.id.sbShadowY:
                mBubbleLayout.setShadowY(dpTopx(i));
                break;
        }
        mBubbleLayout.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            default:
            case R.id.tvNextPage:
                startActivity(new Intent(this, TestDialogActivity.class));
                break;
            case R.id.rbLeft:
                mBubbleLayout.setLook(BubbleLayout.Look.LEFT);
                break;
            case R.id.rbTop:
                mBubbleLayout.setLook(BubbleLayout.Look.TOP);
                break;
            case R.id.rbRight:
                mBubbleLayout.setLook(BubbleLayout.Look.RIGHT);
                break;
            case R.id.rbBottom:
                mBubbleLayout.setLook(BubbleLayout.Look.BOTTOM);
                break;
            case R.id.cbWhite:
                mBubbleLayout.setBubbleColor(getResources().getColor(android.R.color.white));
                break;
            case R.id.cbGrey:
                mBubbleLayout.setBubbleColor(getResources().getColor(android.R.color.darker_gray));
                break;
            case R.id.cbBlack:
                mBubbleLayout.setBubbleColor(getResources().getColor(android.R.color.black));
                break;
            case R.id.cbRed:
                mBubbleLayout.setBubbleColor(getResources().getColor(android.R.color.holo_red_light));
                break;
            case R.id.cbOrange:
                mBubbleLayout.setBubbleColor(getResources().getColor(android.R.color.holo_orange_light));
                break;
            case R.id.cbBlue:
                mBubbleLayout.setBubbleColor(getResources().getColor(android.R.color.holo_blue_light));
                break;
            case R.id.cbGreen:
                mBubbleLayout.setBubbleColor(getResources().getColor(android.R.color.holo_green_light));
                break;
            case R.id.cbPurple:
                mBubbleLayout.setBubbleColor(getResources().getColor(android.R.color.holo_purple));
                break;
            case R.id.csWhite:
                mBubbleLayout.setShadowColor(getResources().getColor(android.R.color.white));
            case R.id.csGrey:
                mBubbleLayout.setShadowColor(getResources().getColor(android.R.color.darker_gray));
                break;
            case R.id.csBlack:
                mBubbleLayout.setShadowColor(getResources().getColor(android.R.color.black));
                break;
            case R.id.csRed:
                mBubbleLayout.setShadowColor(getResources().getColor(android.R.color.holo_red_light));
                break;
            case R.id.csOrange:
                mBubbleLayout.setShadowColor(getResources().getColor(android.R.color.holo_orange_light));
                break;
            case R.id.csBlue:
                mBubbleLayout.setShadowColor(getResources().getColor(android.R.color.holo_blue_light));
                break;
            case R.id.csGreen:
                mBubbleLayout.setShadowColor(getResources().getColor(android.R.color.holo_green_light));
                break;
            case R.id.csPurple:
                mBubbleLayout.setShadowColor(getResources().getColor(android.R.color.holo_purple));
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.test, null))
                        .setClickedView(csPurple)
                        .calBar(true)
                        .setTransParentBackground()
                        .show();
                break;
            case R.id.btnDialogTop:
                new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.test, null))
                        .setClickedView(mBtnDialogTop)
                        .setTransParentBackground()
                        .calBar(true)
                        .show();
                break;
        }
        mBubbleLayout.invalidate();
    }


    public int dpTopx(float dipValue)
    {
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
