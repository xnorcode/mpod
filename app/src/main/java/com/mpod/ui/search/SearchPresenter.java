package com.mpod.ui.search;

import com.mpod.data.source.ArtistRepository;
import com.mpod.utils.Schedulers.BaseSchedulersProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by xnorcode on 15/07/2018.
 */
public class SearchPresenter implements SearchContract.Presenter {


    private SearchContract.View mView;

    ArtistRepository mArtistRepository;

    // using this method to be able to perform tests easily
    BaseSchedulersProvider mSchedulersProvider;

    private CompositeDisposable mCompositeDisposable;


    @Inject
    public SearchPresenter(ArtistRepository artistRepository, BaseSchedulersProvider schedulersProvider) {
        this.mArtistRepository = artistRepository;
        this.mSchedulersProvider = schedulersProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }


    @Override
    public void startSearch(String artistName) {
        Disposable disposable = mArtistRepository.searchArtist(artistName)
                .subscribeOn(mSchedulersProvider.io())
                .observeOn(mSchedulersProvider.ui())
                .subscribe(artists -> mView.startListActivity(artistName),
                        throwable -> mView.showError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void setView(SearchContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
        mCompositeDisposable = null;
    }
}
