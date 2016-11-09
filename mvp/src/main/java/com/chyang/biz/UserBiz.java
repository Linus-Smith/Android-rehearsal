package com.chyang.biz;

import com.chyang.bean.User;

/**
 * Created by caihuiyang on 2016/11/9.
 */

public class UserBiz implements IUserBiz {


    @Override
    public void login(final String username, final String password, final OnLoginListener onLoginListener) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }

                if("linus".equals(username) && "123".equals(password)) {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    onLoginListener.loginSuccess(user);
                } else {
                    onLoginListener.loginFiled();
                }
            }
        }.start();
    }
}
