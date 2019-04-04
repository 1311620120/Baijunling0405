package com.example.baijunling0405.view.activiti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baijunling0405.R;
import com.example.baijunling0405.model.RegJson;
import com.example.baijunling0405.presenter.LoginPresenter;
import com.example.baijunling0405.presenter.RegPresenter;
import com.example.baijunling0405.view.interfaces.IMainView;

public class RegActivity extends AppCompatActivity implements IMainView ,View.OnClickListener {
    private Intent intent;
    private EditText name;
    private EditText pwd;
    private RegPresenter regPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
        initData();
    }

    private void initView() {
        name = findViewById(R.id.name);
        pwd = findViewById(R.id.pwd);
        findViewById(R.id.que).setOnClickListener(this);

    }

    private void initData() {
        regPresenter = new RegPresenter(this);

    }


    @Override
    public void onSuccess(Object o) {
        RegJson regJson =(RegJson)o;
        if (regJson.getStatus().equals("0000")){
            Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            intent = new Intent(RegActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


        case R.id.que:
        String nname = name.getText().toString().trim();
        String ppwd = pwd.getText().toString().trim();
        regPresenter.RegData(nname,ppwd);
        regPresenter.setView(this);
        break;
        }
    }
}
