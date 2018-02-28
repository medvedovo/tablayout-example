package ru.medvedovo.tablayouttest.presentation.main;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {
    void finish();
    void showSystemMessage(String message);
}
