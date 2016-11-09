package com.chyang.presenter;

import android.os.Handler;

import com.chyang.bean.User;
import com.chyang.biz.IUserBiz;
import com.chyang.biz.OnLoginListener;
import com.chyang.biz.UserBiz;
import com.chyang.view.IUserLoginView;

/**
 * Created by caihuiyang on 2016/11/9.
 */

public class UserLoginPresenter {

    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }


    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPasword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                      userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFiled() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                      userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });
            }
        });
    }


    public void clear() {
        userLoginView.clearUsrName();
        userLoginView.clearPassword();
    }

}
