package ru.medvedovo.tablayouttest.presentation.list;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.medvedovo.tablayouttest.App;
import ru.medvedovo.tablayouttest.data.RetrofitRepository;
import ru.medvedovo.tablayouttest.data.dataclass.Response;
import ru.medvedovo.tablayouttest.data.dataclass.local.DataItem;
import ru.medvedovo.tablayouttest.other.FragmentFabric;
import ru.medvedovo.tablayouttest.presentation.details.DetailsFragment;

@InjectViewState
public class ListPresenter extends MvpPresenter<ListView> {

    private ListAdapter adapter;
    private Disposable disposable;

    void onCreate(String listId) {
        if (listId != null && !listId.isEmpty()) {
            RetrofitRepository.getApi().getData(listId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Response>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onSuccess(Response response) {
                            if (response.data != null) {
                                adapter.data.addAll(response.data);
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("GET_DATA", e.getMessage());
                        }
                    });
        }

        adapter = new ListAdapter(new ListAdapter.IClickListener() {
            @Override
            public void onItemClick(DataItem item) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(DetailsFragment.DETAILS_OBJECT, item);
                App.INSTANCE.getRouter().navigateTo(FragmentFabric.Type.DETAILS.name(), bundle);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    ListAdapter getAdapter() {
        return adapter;
    }

    void onBackPressed() {
        App.INSTANCE.getRouter().exit();
    }
}
