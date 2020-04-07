package com.xiayiye.honorfirst.jetpack.livedata;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xiayiye.honorfirst.R;

/**
 * @author xiayiye
 */
public class LiveDataActivity extends AppCompatActivity {
    private TextView tvShowResult;
    private LiveDataDemo liveDataDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        liveDataDemo = ViewModelProviders.of(this).get(LiveDataDemo.class);
        tvShowResult = findViewById(R.id.tv_show_result);
        ImageButton btAddLike = findViewById(R.id.bt_add_like);
        ImageButton btDislike = findViewById(R.id.bt_dislike);
        liveDataDemo.getNumber().observe(this, integer -> tvShowResult.setText(new StringBuffer("点赞数量：").append(integer)));
        btAddLike.setOnClickListener(v -> liveDataDemo.addLikeAndDisLike(1));
        btDislike.setOnClickListener(v -> liveDataDemo.addLikeAndDisLike(-1));
    }
}
