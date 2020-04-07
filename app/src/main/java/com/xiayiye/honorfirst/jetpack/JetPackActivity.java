package com.xiayiye.honorfirst.jetpack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.xiayiye.honorfirst.R;
import com.xiayiye.honorfirst.jetpack.databinding.DataBindingActivity;
import com.xiayiye.honorfirst.jetpack.livedata.LiveDataActivity;
import com.xiayiye.honorfirst.jetpack.viewmodel.ViewModelActivity;

/**
 * @author xiayiye
 */
public class JetPackActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jet_pack);
        ListView lvJetPack = findViewById(R.id.lv_jet_pack);
        lvJetPack.setOnItemClickListener((parent, view, position, id) -> choosePage(position));
    }

    private void choosePage(int position) {
        switch (position) {
            case 0:
                jumpPage(ViewModelActivity.class);
                break;
            case 1:
                jumpPage(LiveDataActivity.class);
                break;
            case 2:
                jumpPage(DataBindingActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * 跳转到指定的学习页面
     *
     * @param clazz 跳转的页面
     */
    private void jumpPage(Class clazz) {
        startActivity(new Intent(getApplication(), clazz));
    }
}
