package com.manh.vumanh.mvparse.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class BaseFragment extends Fragment{
    FragmentTransaction fragmentTransaction;
    public void changeFragment(Fragment a, int id, boolean backstack){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, a);
        if(backstack == true){
            fragmentTransaction.addToBackStack("");
        } else {
            fragmentTransaction.disallowAddToBackStack();
        }
        fragmentTransaction.commit();
    }

}
