package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiayiye.honorfirst.R;
import com.xiayiye.honorfirst.ActivityManageAll;
import com.xiayiye.honorfirst.httpstudy.HttpStudyActivity;
import com.xiayiye.honorfirst.inter.BasketballCoach;
import com.xiayiye.honorfirst.inter.BasketballPlayer;
import com.xiayiye.honorfirst.inter.PingBangCoach;
import com.xiayiye.honorfirst.inter.PingBingPlayer;
import com.xiayiye.honorfirst.jetpack.JetPackActivity;

/**
 * @author xiayiye
 */
public class AppActivity extends Activity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        ListView lvApp = findViewById(R.id.lv_app);
        lvApp.setOnItemClickListener(this);
//        分别实现乒乓球运动员和乒乓球教练的方法
        PingBingPlayer pingBingPlayer = new PingBingPlayer();
        pingBingPlayer.setAge(63);
        pingBingPlayer.setName("邓亚萍");
        pingBingPlayer.haveEat();
        pingBingPlayer.speak();
        pingBingPlayer.study();
        pingBingPlayer.sleep();

        PingBangCoach pingBangCoach = new PingBangCoach();
        pingBangCoach.setAge(67);
        pingBangCoach.setName("王国光");
        pingBangCoach.haveEat();
        pingBangCoach.speak();
        pingBangCoach.teach();
        pingBangCoach.sleep();

//        分别实现篮球运动员和篮球教练的方法
        BasketballPlayer basketballPlayer = new BasketballPlayer();
        basketballPlayer.setAge(33);
        basketballPlayer.setName("刘光");
        basketballPlayer.haveEat();
        basketballPlayer.study();
        basketballPlayer.sleep();

        BasketballCoach basketballCoach = new BasketballCoach();
        basketballCoach.setAge(45);
        basketballCoach.setName("张小华");
        basketballCoach.haveEat();
        basketballCoach.teach();
        basketballCoach.sleep();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                //跳转以前学习的页面
                goToBeforeStudy();
                break;
            case 1:
                //跳转RxJava学习页面
                toRxJavaStudy();
                break;
            case 2:
                //跳转购物车抛物线动画页面
                linAnimate();
                break;
            case 3:
                goJetPack();
                break;
            case 4:
                httpStudy();
                break;
            case 5:
                //跳转倒影图片页面
                invertedImage();
                break;
            case 6:
                ActivityManageAll.getInstance2().goActivity(BallActivity.class);
                break;
            default:
        }
    }

    /**
     * 自定义倒影图片
     */
    private void invertedImage() {
        ActivityManageAll.getInstance2().goActivity(InvertedImageActivity.class);
    }

    private void httpStudy() {
        ActivityManageAll.getInstance2().goActivity(HttpStudyActivity.class);
    }

    private void goJetPack() {
        ActivityManageAll.getInstance2().goActivity(JetPackActivity.class);
    }

    /**
     * 抛物线动画
     */
    private void linAnimate() {
        ActivityManageAll.getInstance2().goActivity(LineAnimateActivity.class);
    }

    private void toRxJavaStudy() {
        ActivityManageAll.getInstance2().goActivity(RxJavaStudyActivity.class);
    }

    private void goToBeforeStudy() {
        ActivityManageAll.getInstance1().goActivity(MainActivity.class);
    }
}
