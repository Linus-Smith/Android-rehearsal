package com.chyang.biz;

import com.chyang.bean.User;

/**
 * Created by caihuiyang on 2016/11/9.
 */

public interface OnLoginListener {

    void loginSuccess(User user);
    void loginFiled();
}
