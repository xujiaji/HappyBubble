package com.xujiaji.happybubbletest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xujiaji.happybubble.BubbleDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/12.
 */

public class SetClickedViewTestActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private List<String> lists = new ArrayList<>();


    private BubbleDialog bubbleDialog;
    private TextView dialogText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setAdapter(new MyAdapter());
        for (int i = 0; i < 100; i++) {
            lists.add("text" + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        View dialogMain = getLayoutInflater().inflate(R.layout.dialog_simple, null);
        dialogText = dialogMain.findViewById(R.id.tv_title);
        bubbleDialog = new BubbleDialog(SetClickedViewTestActivity.this)
                .addContentView(dialogMain)
                .calBar(true)
                .setTransParentBackground()
                .setRelativeOffset(-16)
                .setThroughEvent(true, false)
                .autoPosition(true);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_simple, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            String str = lists.get(position);
            holder.mTvTitle.setText(str);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogText.setText("当前点击位置" + position);
                    bubbleDialog.setClickedView(v);
                    bubbleDialog.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return lists.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            View view;
            TextView mTvTitle;

            ViewHolder(View view) {
                super(view);
                this.view = view;
                this.mTvTitle = (TextView) view.findViewById(R.id.tv_title);
            }
        }
    }
}
