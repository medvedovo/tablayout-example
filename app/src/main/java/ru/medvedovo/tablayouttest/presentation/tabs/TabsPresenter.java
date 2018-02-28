package ru.medvedovo.tablayouttest.presentation.tabs;

import com.arellomobile.mvp.MvpPresenter;

import ru.medvedovo.tablayouttest.App;

public class TabsPresenter extends MvpPresenter<TabsView> {
    void onBackPressed() {
        App.INSTANCE.getRouter().exit();
    }
}
