package ru.medvedovo.tablayouttest.presentation.details;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import ru.medvedovo.tablayouttest.R;
import ru.medvedovo.tablayouttest.data.dataclass.local.DataItem;
import ru.medvedovo.tablayouttest.other.FragmentFabric;

public class DetailsFragment extends MvpAppCompatFragment implements DetailsView, FragmentFabric.IFragment {

    public static String DETAILS_OBJECT = "details_object";

    @InjectPresenter
    DetailsPresenter presenter;

    ImageView image;
    TextView number;
    TextView text;

    public static DetailsFragment newInstance(Bundle bundle) {
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate((DataItem) getArguments().getSerializable(DETAILS_OBJECT));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        image = view.findViewById(R.id.image);
        number = view.findViewById(R.id.number);
        text = view.findViewById(R.id.text);

        return view;
    }

    @Override
    public void setImage(String url) {
        Glide.with(this).load(url)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(image);
    }

    @Override
    public void setNumber(String number) {
        this.number.setText(number);
    }

    @Override
    public void setText(String text) {
        this.text.setText(text);
    }

    @Override
    public FragmentFabric.Type getType() {
        return FragmentFabric.Type.DETAILS;
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }
}
