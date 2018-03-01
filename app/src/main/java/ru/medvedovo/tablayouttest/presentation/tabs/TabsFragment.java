package ru.medvedovo.tablayouttest.presentation.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import ru.medvedovo.tablayouttest.R;
import ru.medvedovo.tablayouttest.other.FragmentFabric;

public class TabsFragment extends MvpAppCompatFragment implements TabsView, FragmentFabric.IFragment {

    @InjectPresenter
    TabsPresenter presenter;

    private ViewPager pager;
    private TabLayout tabLayout;

    public static TabsFragment newInstance(Bundle bundle) {
        TabsFragment fragment = new TabsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate(getFragmentManager(), 2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        pager = view.findViewById(R.id.pager);
        tabLayout = view.findViewById(R.id.tabLayout);

        pager.setAdapter(presenter.getPager());

        tabLayout.addTab(tabLayout.newTab().setText("Cats"));
        tabLayout.addTab(tabLayout.newTab().setText("Dogs"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
        Bundle bundle = new Bundle();
        bundle.putString("hello", "world");
        onSaveInstanceState(bundle);
    }

    @Override
    public FragmentFabric.Type getType() {
        return FragmentFabric.Type.TABS;
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }
}
