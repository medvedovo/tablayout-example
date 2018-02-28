package ru.medvedovo.tablayouttest.presentation.main;

import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import ru.medvedovo.tablayouttest.R;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!this.isTaskRoot()) {
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        presenter.onCreate(getSupportFragmentManager(), savedInstanceState, getIntent());
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showSystemMessage(String message) {
        Snackbar.make(findViewById(R.id.content), message, Snackbar.LENGTH_LONG).show();
    }
}
