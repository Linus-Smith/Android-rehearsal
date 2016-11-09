package com.chyang.view;

import com.chyang.bean.User;

/**
 * Created by caihuiyang on 2016/11/9.
 */

public interface IUserLoginView {

    String getUserName();

    String getPasword();

    void clearUsrName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
