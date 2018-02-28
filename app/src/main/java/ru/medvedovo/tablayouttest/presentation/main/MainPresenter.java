package ru.medvedovo.tablayouttest.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.medvedovo.tablayouttest.App;
import ru.medvedovo.tablayouttest.R;
import ru.medvedovo.tablayouttest.other.FragmentFabric;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Navigator navigator;

    void onCreate(FragmentManager fragmentManager, Bundle bundle, Intent intent) {
        this.navigator = new SupportFragmentNavigator(fragmentManager, R.id.content) {
            @Override
            protected Fragment createFragment(String screenKey, Object data) {
                return (Fragment) FragmentFabric.create(FragmentFabric.Type.valueOf(screenKey), data != null ? (Bundle) data : new Bundle());
            }

            @Override
            protected void showSystemMessage(String message) {
                getViewState().showSystemMessage(message);
            }

            @Override
            protected void exit() {
                getViewState().finish();
            }
        };
        App.INSTANCE.getRouter().newRootScreen(FragmentFabric.Type.TABS.name());
    }

    void onPause() {
        App.INSTANCE.getNavigator().removeNavigator();
    }

    void onResume() {
        App.INSTANCE.getNavigator().setNavigator(this.navigator);
    }
}
