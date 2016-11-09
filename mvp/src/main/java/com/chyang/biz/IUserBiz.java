package com.chyang.biz;

/**
 * Created by caihuiyang on 2016/11/9.
 */

public interface IUserBiz {

    public void login(String username, String password, OnLoginListener onLoginListener);
}
