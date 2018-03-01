package ru.medvedovo.tablayouttest.presentation.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.medvedovo.tablayouttest.App;
import ru.medvedovo.tablayouttest.data.dataclass.local.DataItem;

@InjectViewState
public class DetailsPresenter extends MvpPresenter<DetailsView> {

    void onCreate(DataItem item) {
        getViewState().setImage(item.url);
        getViewState().setNumber(item.number);
        getViewState().setText(item.text);
    }

    void onBackPressed() {
        App.INSTANCE.getRouter().exit();
    }
}
