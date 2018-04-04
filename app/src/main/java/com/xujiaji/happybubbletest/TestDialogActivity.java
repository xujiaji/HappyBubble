package com.xujiaji.happybubbletest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xujiaji.happybubble.Auto;
import com.xujiaji.happybubble.BubbleDialog;
import com.xujiaji.happybubble.BubbleLayout;
import com.xujiaji.happybubble.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private RadioButton mRbAuto, mRbAutoUpAndDown, mRbAutoLeftAndRight;
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
    private Auto mAuto;

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
        mRbAutoUpAndDown = (RadioButton) findViewById(R.id.rbAutoUpAndDown);
        mRbAutoUpAndDown.setOnClickListener(this);
        mRbAutoLeftAndRight = (RadioButton) findViewById(R.id.rbAutoLeftAndRight);
        mRbAutoLeftAndRight.setOnClickListener(this);
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
                mRbAuto = null;
                mPosition = BubbleDialog.Position.LEFT;
                break;
            case R.id.rbTop:
                mRbAuto = null;
                mPosition = BubbleDialog.Position.TOP;
                break;
            case R.id.rbRight:
                mRbAuto = null;
                mPosition = BubbleDialog.Position.RIGHT;
                break;
            case R.id.rbBottom:
                mRbAuto = null;
                mPosition = BubbleDialog.Position.BOTTOM;
                break;
            case R.id.rbAuto:
                mAuto = Auto.AROUND;
                break;
            case R.id.rbAutoLeftAndRight:
                mAuto = Auto.LEFT_AND_RIGHT;
                break;
            case R.id.rbAutoUpAndDown:
                mAuto = Auto.UP_AND_DOWN;
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
                        .autoPosition(mAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
                        .calBar(true);
                mCurrentDialog.show();

                break;
            case R.id.button2:
                startActivity(new Intent(this, SetClickedViewTestActivity.class));
//                mCurrentDialog = new BubbleDialog(this)
//                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
//                        .setClickedView(mButton2)
//                        .setPosition(mPosition)
//                        .autoPosition(isAuto)
//                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
//                        .calBar(true);
//                mCurrentDialog.show();

                break;
            case R.id.button3:
                initListDialog();
//                mCurrentDialog = new BubbleDialog(this)
//                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
//                        .setClickedView(mButton3)
//                        .setPosition(mPosition)
//                        .autoPosition(isAuto)
//                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
//                        .calBar(true);
//                mCurrentDialog.show();

                break;
            case R.id.button4:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton4)
                        .setPosition(mPosition)
                        .setOffsetY(8)
                        .autoPosition(mAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
                        .calBar(true);
                mCurrentDialog.show();

                break;
            case R.id.button5:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view2, null))
                        .setClickedView(mButton5)
                        .setPosition(mPosition)
                        .autoPosition(mAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
                        .calBar(true);
                mCurrentDialog.show();
                break;
            case R.id.button6:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton6)
                        .setPosition(mPosition)
                        .autoPosition(mAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true);
                mCurrentDialog.show();
                break;
            case R.id.button7:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton7)
                        .setPosition(mPosition)
                        .calBar(true)
                        .autoPosition(mAuto)
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
                        .autoPosition(mAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true);
                mCurrentDialog.show();

                break;
            case R.id.button9:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
                        .setClickedView(mButton9)
                        .setPosition(mPosition)
                        .calBar(true)
                        .autoPosition(mAuto)
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
                codDialog.autoPosition(mAuto);
                codDialog.setThroughEvent(mCheckBoxThrough.isChecked(), true);
                mCurrentDialog = codDialog;
                mCurrentDialog.show();
                break;
            case R.id.button11:
//                mCurrentDialog = new BubbleDialog(this)
//                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view3, null))
//                        .setClickedView(mButton11)
//                        .setPosition(mPosition)
//                        .autoPosition(isAuto)
//                        .setThroughEvent(mCheckBoxThrough.isChecked(), true)
//                        .calBar(true);
//
//                mCurrentDialog.show();

                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view, null))
                        .setClickedView(mButton11)
                        .setPosition(mPosition)
                        .calBar(true)
                        .softShowUp()
                        .autoPosition(mAuto)
                        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                                Util.dpToPx(this, 200),
                                Util.dpToPx(this, 32))
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true);
                mCurrentDialog.show();

                break;
            case R.id.button12:
                mCurrentDialog = new BubbleDialog(this)
                        .addContentView(LayoutInflater.from(this).inflate(R.layout.dialog_view, null))
                        .setClickedView(mButton12)
                        .setPosition(mPosition)
                        .calBar(true)
                        .softShowUp()
                        .autoPosition(mAuto)
                        .setThroughEvent(mCheckBoxThrough.isChecked(), true);
                mCurrentDialog.show();

                break;
        }
    }

    private void initListDialog()
    {
        final List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            Map<String, String> map = new HashMap<>();
            map.put("text", "Text " + i);
            list.add(map);
        }

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_test_list, null);
        Button btnAdd = view.findViewById(R.id.btnAdd);
        ListView mListView = view.findViewById(R.id.listView);
        final SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_1, new String[]{"text"}, new int[]{android.R.id.text1});
        mListView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Map<String, String> map = new HashMap<>();
                map.put("text", "Text click");
                list.add(map);
                adapter.notifyDataSetChanged();
            }
        });


        mCurrentDialog = new BubbleDialog(this)
                .addContentView(view)
                .setClickedView(mButton3)
                .setPosition(mPosition)
                .autoPosition(mAuto)
                .setThroughEvent(mCheckBoxThrough.isChecked(), true)
                .calBar(true);
        mCurrentDialog.show();
    }
}
