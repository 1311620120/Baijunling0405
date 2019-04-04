package com.example.baijunling0405.view.activiti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baijunling0405.R;
import com.example.baijunling0405.model.LoginJson;
import com.example.baijunling0405.presenter.LoginPresenter;
import com.example.baijunling0405.view.interfaces.IMainView;

public class LoginActivity extends AppCompatActivity implements IMainView,View.OnClickListener {

    private LoginPresenter loginPresenter;
    private Intent intent;
    private EditText name;
    private EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        name = findViewById(R.id.name);
        pwd = findViewById(R.id.pwd);
        findViewById(R.id.login).setOnClickListener(this);
       findViewById(R.id.reg).setOnClickListener(this);
    }

    private void initData() {
        loginPresenter = new LoginPresenter(this);

    }

    @Override
    public void onSuccess(Object o) {
        LoginJson loginJson =(LoginJson)o;
          if (loginJson.getStatus().equals("0000")){
              Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
              intent = new Intent(LoginActivity.this, ShowActivity.class);
              startActivity(intent);
          }else {
              Toast.makeText(LoginActivity.this,"账号密码有误",Toast.LENGTH_SHORT).show();
          }
    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.login:
        String nname = name.getText().toString().trim();
        String ppwd = pwd.getText().toString().trim();
        loginPresenter.LoginData(nname,ppwd);
        loginPresenter.setView(this);
        break;
    case R.id.reg:
        intent = new Intent(LoginActivity.this, RegActivity.class);
        startActivity(intent);
        break;
}

    }
}
