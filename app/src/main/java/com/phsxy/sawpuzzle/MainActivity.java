package com.phsxy.sawpuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.phsxy.sawpuzzle.activity.PuzzleActivity;
import com.phsxy.sawpuzzle.activity.PuzzleActivity2;
import com.phsxy.sawpuzzle.activity.PuzzleActivity3;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_sawpuzzle)
    Button btn_sawpuzzle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
    }

    @OnClick({R.id.btn_sawpuzzle,R.id.btn_sawpuzzle2,R.id.btn_sawpuzzle3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sawpuzzle:
            Intent intent = new Intent(MainActivity.this,PuzzleActivity.class);
            startActivity(intent);
            break;
            case R.id.btn_sawpuzzle2:
            Intent intent2 = new Intent(MainActivity.this,PuzzleActivity2.class);
            startActivity(intent2);
            break;

            case R.id.btn_sawpuzzle3:
            Intent intent3 = new Intent(MainActivity.this,PuzzleActivity3.class);
            startActivity(intent3);
            break;
        }
    }
}
