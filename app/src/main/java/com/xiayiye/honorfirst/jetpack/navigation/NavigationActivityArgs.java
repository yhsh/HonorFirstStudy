package com.xiayiye.honorfirst.jetpack.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiayiye.honorfirst.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

/**
 * @author xiayiye
 */
public class NavigationActivityArgs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_args);
        NavController navController = Navigation.findNavController(this, R.id.fragment_args);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.fragment_args).navigateUp();
//        return super.onSupportNavigateUp();
    }
}
