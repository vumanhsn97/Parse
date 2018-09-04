package com.manh.vumanh.mvparse.views;

public interface ViewLogin {
    void sendWrong(String mess);
    void loginSuccess();
    void showProgress();
    void dismissProgress();
}
