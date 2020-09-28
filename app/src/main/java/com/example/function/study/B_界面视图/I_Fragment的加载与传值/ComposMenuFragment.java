package com.example.function.study.B_界面视图.I_Fragment的加载与传值;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldy.study.R;

/**
 * @author 东阳
 */
public class ComposMenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return layoutInflater.inflate(R.layout.fragment_menu_setting, null);
    }

//    @Override
//    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
//        return layoutInflater.inflate(R.layout.fragment_menu_setting, null);
//    }


}
