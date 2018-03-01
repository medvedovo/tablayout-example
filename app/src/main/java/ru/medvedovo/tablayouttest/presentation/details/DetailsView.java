package ru.medvedovo.tablayouttest.presentation.details;

import com.arellomobile.mvp.MvpView;

interface DetailsView extends MvpView {
    void setImage(String url);
    void setNumber(String number);
    void setText(String text);
}
