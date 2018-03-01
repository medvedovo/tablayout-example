package ru.medvedovo.tablayouttest.presentation.tabs;

import android.os.Parcelable;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.medvedovo.tablayouttest.App;

@InjectViewState
public class TabsPresenter extends MvpPresenter<TabsView> {
    private TabsPager pager;
    private Parcelable pagerState;

    void onCreate(FragmentManager fragmentManager, int tabCount) {
        pager = new TabsPager(fragmentManager, tabCount);
    }

    TabsPager getPager() {
        return pager;
    }

    void onResume() {
        if (pagerState != null) {
            pager.restoreState(pagerState, ClassLoader.getSystemClassLoader());
        }
        pager.notifyDataSetChanged();
    }

    void onPause() {
        pagerState = pager.saveState();
    }

    void onBackPressed() {
        App.INSTANCE.getRouter().exit();
    }
}
