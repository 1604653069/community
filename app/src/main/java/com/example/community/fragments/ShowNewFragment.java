package com.example.community.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.community.R;

public class ShowNewFragment extends Fragment {
    private WebView webView;
    private ImageView back;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_new_fragment,container,false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        back = view.findViewById(R.id.title_back);
        webView = view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        Log.i("TAG","新闻的url地址是:"+url);
        webView.loadUrl(url);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new NewFragment()).commit();
            }
        });
    }

}
