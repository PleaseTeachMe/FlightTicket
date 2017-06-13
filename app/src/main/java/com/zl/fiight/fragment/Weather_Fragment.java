package com.zl.fiight.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zl.fiight.R;
import com.zl.fiight.activity.Main2Activity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Weather_Fragment extends Fragment {

    View mView;

    @Bind(R.id.bt_weather)
    Button bt_weather;
    public Weather_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_weather_, container, false);
        ButterKnife.bind(this,mView);
        return mView;
    }
    @OnClick(R.id.bt_weather)
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_weather:
                startActivity(new Intent(getActivity(), Main2Activity.class));
                break;
        }
    }

}
