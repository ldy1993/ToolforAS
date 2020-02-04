package com.example.function.study.view.day12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldy.study.R;

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
