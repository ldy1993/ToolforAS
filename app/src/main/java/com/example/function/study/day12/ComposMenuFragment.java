package com.example.function.study.day12;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldy.study.R;

import me.jerry.framework.android.FragmentFrame;

/**
 * @author 东阳
 */
public class ComposMenuFragment extends AppBaseFragment {

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return layoutInflater.inflate(R.layout.fragment_menu_setting, null);
//    }

    @Override
    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_menu_setting, null);
    }


}
