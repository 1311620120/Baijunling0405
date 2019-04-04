package com.example.baijunling0405.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.baijunling0405.R;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/4 10:11:14
 * @Description:
 */
public class TwoFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.twofragment, null);
        ImageView img = view.findViewById(R.id.img1);
        Glide.with(getActivity()).load("http://172.17.8.100/small/user/v1/login").into(img);
        return view;
    }
}
