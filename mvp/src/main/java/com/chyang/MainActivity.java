package com.chyang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chyang.bean.User;
import com.chyang.mvp.R;
import com.chyang.presenter.UserLoginPresenter;
import com.chyang.view.IUserLoginView;

public class MainActivity extends AppCompatActivity implements IUserLoginView{


    private EditText edUsername;
    private EditText edPassword;
    private Button mBtnlogin;
    private Button mBtnClear;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        edUsername = (EditText) findViewById(R.id.ed_username);
        edPassword = (EditText) findViewById(R.id.ed_password);

        mBtnlogin = (Button) findViewById(R.id.bt_login);
        mBtnClear = (Button) findViewById(R.id.bt_clear);

        mBtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.clear();
            }
        });
    }

    @Override
    public String getUserName() {
        return edUsername.getText().toString().trim();
    }

    @Override
    public String getPasword() {
        return edUsername.getText().toString().trim();
    }

    @Override
    public void clearUsrName() {
        edUsername.getText().clear();
    }

    @Override
    public void clearPassword() {
        edPassword.getText().clear();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, "登陆成功"+user,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
    }
}
