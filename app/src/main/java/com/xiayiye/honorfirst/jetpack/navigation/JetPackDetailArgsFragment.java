package com.xiayiye.honorfirst.jetpack.navigation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiayiye.honorfirst.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JetPackDetailArgsFragment extends Fragment {


    public JetPackDetailArgsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jet_pack_detail_args, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取传递过来的参数
        String name = getArguments().getString("name");
        String nameData = getArguments().getString("name_data");
        ((TextView) getView().findViewById(R.id.tv_show_args)).setText(nameData);
    }
}
