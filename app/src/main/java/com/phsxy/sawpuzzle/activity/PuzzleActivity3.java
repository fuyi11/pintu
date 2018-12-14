package com.phsxy.sawpuzzle.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.phsxy.sawpuzzle.R;
import com.phsxy.sawpuzzle.adapter.PuzzleListAdapter;
import com.phsxy.sawpuzzle.bean.Pieces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: CC_0625
 * date: 2018/12/14
 * dec:
 **/
public class PuzzleActivity3 extends AppCompatActivity {
    @BindView(R.id.webview)
    WebView mWebView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_activity3);
        ButterKnife.bind(PuzzleActivity3.this);
        mWebView.loadUrl("http://10.20.15.252:8000/jquery%E6%8B%96%E5%8A%A8%E5%9B%BE%E7%89%87%E7%A7%BB%E5%8A%A8%E6%8B%BC%E5%9B%BE%E5%B0%8F%E6%B8%B8%E6%88%8F/");
//        mWebView.loadUrl("http://10.20.15.226:8000/new_file3.html");
        WebSettings settings = mWebView.getSettings();

        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSavePassword(true);
        settings.setSaveFormData(true);
        settings.setJavaScriptEnabled(true);     // enable navigator.geolocation
        settings.setGeolocationEnabled(true);
        settings.setDomStorageEnabled(true);
        mWebView.requestFocus();
//        mWebView.setScrollBarStyle(0);

        mWebView.setWebChromeClient(new WebChromeClient());
    }

}
