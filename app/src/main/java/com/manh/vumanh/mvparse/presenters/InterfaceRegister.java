package com.manh.vumanh.mvparse.presenters;

public interface InterfaceRegister {
    boolean checkInfor(String username, String pass, String repass, String email);
    void registerAccount(String usermane, String pass, String email);
}
