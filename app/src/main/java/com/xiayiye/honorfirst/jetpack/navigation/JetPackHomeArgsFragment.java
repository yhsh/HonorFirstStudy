package com.xiayiye.honorfirst.jetpack.navigation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.xiayiye.honorfirst.R;

import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author xiayiye
 */
public class JetPackHomeArgsFragment extends Fragment {


    public JetPackHomeArgsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jet_pack_home_args, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //下面两种方法都可以
//        getView().findViewById(R.id.bt_take_args).setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.jetPackDetailArgsFragment));
        getView().findViewById(R.id.bt_take_args).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etInputData = getView().findViewById(R.id.et_input_data);
                String inputData = etInputData.getText().toString();
                if (TextUtils.isEmpty(inputData)) {
                    Toast.makeText(getActivity(), "请填写参数", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("name_data", inputData);
                Navigation.findNavController(v).navigate(R.id.action_jetPackHomeArgsFragment_to_jetPackDetailArgsFragment, bundle);
            }
        });
    }
}
