package com.xiayiye.honorfirst.jetpack.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiayiye.honorfirst.R;

import androidx.navigation.Navigation;

/**
 * @author xiayiye
 */
public class JetPackDetailFragment extends Fragment {

    public JetPackDetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jet_pack_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.bt_to_jump_home).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_jetPackDetailFragment_to_jetPackHomeFragment));
    }
}
