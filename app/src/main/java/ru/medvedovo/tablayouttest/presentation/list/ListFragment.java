package ru.medvedovo.tablayouttest.presentation.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import ru.medvedovo.tablayouttest.R;
import ru.medvedovo.tablayouttest.other.FragmentFabric;

public class ListFragment extends MvpAppCompatFragment implements ListView, FragmentFabric.IFragment {

    public static String LIST_TYPE = "list_type";

    @InjectPresenter
    ListPresenter presenter;

    private RecyclerView list;

    public static ListFragment newInstance(Bundle bundle) {
        ListFragment fragment = new ListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate(getArguments().getString(LIST_TYPE, ""));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        list = view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setAdapter(presenter.getAdapter());
        return view;
    }

    @Override
    public FragmentFabric.Type getType() {
        return FragmentFabric.Type.LIST;
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }
}
