package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding3.widget.RxTextView;
import com.xiayiye.honorfirst.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * @author xiayiye
 */
public class RxJavaSearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_search);
        EditText etInputSearch = findViewById(R.id.et_input_search);
        TextView tvShowSearchResult = findViewById(R.id.tv_show_search_result);

        RxTextView.textChanges(etInputSearch).debounce(500, TimeUnit.MILLISECONDS).filter(new Predicate<CharSequence>() {
            @Override
            public boolean test(CharSequence charSequence) throws Exception {
                return charSequence.toString().trim().length() > 0;
            }
        }).switchMap(new Function<CharSequence, ObservableSource<List<String>>>() {
            @Override
            public ObservableSource<List<String>> apply(CharSequence charSequence) throws Exception {
                Log.e("打印文本监听charSequence", charSequence.toString());
                List<String> list = new ArrayList<>();
                list.add("abc");
                list.add("123");
                return Observable.just(list);
            }
        })/*.flatMap(new Function<CharSequence, ObservableSource<List<String>>>() {
            @Override
            public ObservableSource<List<String>> apply(CharSequence charSequence) throws Exception {
                Log.e("打印文本监听charSequence", charSequence.toString());
                List<String> list = new ArrayList<>();
                list.add("abc");
                list.add("123");
                return Observable.just(list);
            }
        })*/.subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                Log.e("打印文本监听", "accept");
                tvShowSearchResult.setText(strings.toString());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.e("打印文本监听", "Action");
            }
        });
    }
}
