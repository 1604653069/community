package com.example.community.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.community.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailedFragment extends Fragment implements View.OnClickListener {
    private PopupWindow popupWindow;
    private View contentView;
    private CircleImageView cirleImageView;
    private Dialog bgSetDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailed,container,false);
        initView(view);
        return view;
    }
    public void initView(View view){
        cirleImageView = view.findViewById(R.id.detail_touxiang);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.open_from_camera:
                Toast.makeText(getContext(), "你点击的是照相机", Toast.LENGTH_SHORT).show();
                break;
            case R.id.open_album:
                Toast.makeText(getContext(), "你点击的是相册", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel:
                bgSetDialog.dismiss();
                break;
        }
    }
}
