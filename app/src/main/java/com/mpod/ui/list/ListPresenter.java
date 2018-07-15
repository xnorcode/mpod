package com.mpod.ui.list;

import com.mpod.data.source.ArtistRepository;
import com.mpod.utils.Schedulers.BaseSchedulersProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by xnorcode on 15/07/2018.
 */
public class ListPresenter implements ListContract.Presenter {

    private ListContract.View mView;

    ArtistRepository mArtistRepository;

    // using this method to be able to perform tests easily
    BaseSchedulersProvider mSchedulersProvider;

    private CompositeDisposable mCompositeDisposable;

    @Inject
    public ListPresenter(ArtistRepository mArtistRepository, BaseSchedulersProvider schedulersProvider) {
        this.mArtistRepository = mArtistRepository;
        this.mSchedulersProvider = schedulersProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }


    @Override
    public void searchArtist(String name) {
        Disposable disposable = mArtistRepository.searchArtist(name)
                .subscribeOn(mSchedulersProvider.io())
                .observeOn(mSchedulersProvider.ui())
                .subscribe(artists -> mView.showArtists(artists),
                        throwable -> mView.showError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void setView(ListContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
        mCompositeDisposable = null;
    }
}
