package com.mpod.ui.details;

import com.mpod.data.source.ArtistRepository;
import com.mpod.utils.Schedulers.BaseSchedulersProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by xnorcode on 16/07/2018.
 */
public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View mView;

    ArtistRepository mArtistRepository;

    // using this method to be able to perform tests easily
    BaseSchedulersProvider mSchedulersProvider;

    private CompositeDisposable mCompositeDisposable;

    @Inject
    public DetailsPresenter(ArtistRepository mArtistRepository, BaseSchedulersProvider mSchedulersProvider) {
        this.mArtistRepository = mArtistRepository;
        this.mSchedulersProvider = mSchedulersProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getArtistInfo(String mbid) {
        Disposable disposable = mArtistRepository.getArtistInfo(mbid)
                .subscribeOn(mSchedulersProvider.io())
                .observeOn(mSchedulersProvider.ui())
                .subscribe(artist -> mView.showArtistDetails(artist),
                        throwable -> mView.showError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void setView(DetailsContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
        mCompositeDisposable = null;
    }
}
