package com.manh.vumanh.mvparse.views;

public interface ViewRegister {
    void sendWrong(String text);
    void registerSuccess();
    void showProgress();
    void dismissProgress();
}
