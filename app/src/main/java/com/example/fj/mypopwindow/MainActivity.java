package com.example.fj.mypopwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.fj.mypopwindow.callback.DataCallBack;
import com.example.fj.mypopwindow.factory.PopupWindowFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements DataCallBack{


    @BindView(R.id.btn_show)
    Button btnShow;
    @BindView(R.id.btn_age)
    Button btnAge;
    @BindView(R.id.btn_name)
    Button btnName;
    @BindView(R.id.btn_address)
    Button btnAddress;
    @BindView(R.id.btn_pwd)
    Button btnPwd;
    @BindView(R.id.btn_sex)
    Button btnSex;
    @BindView(R.id.btn_height)
    Button btnHeight;
    @BindView(R.id.btn_county)
    Button btnCounty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_show, R.id.btn_age, R.id.btn_name, R.id.btn_address, R.id.btn_pwd, R.id.btn_sex, R.id.btn_height, R.id.btn_county})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_show:
                PopupWindowFactory.creatPop(this,this,btnShow);
                Log.i(TAG, "onViewClicked:show" );
                break;
            case R.id.btn_age:
                PopupWindowFactory.creatPop(this,this,btnAge);
                Log.i(TAG, "onViewClicked:age" );
                break;
            case R.id.btn_name:
                PopupWindowFactory.creatPop(this,this,btnName);
                break;
            case R.id.btn_address:
                PopupWindowFactory.creatPop(this,this,btnAddress);
                break;
            case R.id.btn_pwd:
                PopupWindowFactory.creatPop(this,this,btnPwd);
                break;
            case R.id.btn_sex:
                PopupWindowFactory.creatPop(this,this,btnSex);
                break;
            case R.id.btn_height:
                PopupWindowFactory.creatPop(this,this,btnHeight);
                break;
            case R.id.btn_county:
                PopupWindowFactory.creatPop(this,this,btnCounty);
                break;
        }
    }

    @Override
    public void callBack(String data) {
        Log.i(TAG,data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && PopupWindowFactory.isShowing()){
            PopupWindowFactory.dismiss();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private static final String TAG = "MainActivity";
}
