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
public class JetPackHomeFragment extends Fragment {

    public JetPackHomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jet_pack_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.bt_go_detail).setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_jetPackHomeFragment_to_jetPackDetailFragment));
    }
}
