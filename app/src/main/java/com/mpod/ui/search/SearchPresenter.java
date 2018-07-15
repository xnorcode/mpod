package com.mpod.ui.search;

import com.mpod.data.source.ArtistRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xnorcode on 15/07/2018.
 */
public class SearchPresenter implements SearchContract.Presenter {


    private SearchContract.View mView;

    ArtistRepository mArtistRepository;

    private CompositeDisposable mCompositeDisposable;


    @Inject
    public SearchPresenter(ArtistRepository artistRepository) {
        this.mArtistRepository = artistRepository;
        this.mCompositeDisposable = new CompositeDisposable();
    }


    @Override
    public void startSearch(String artistName) {
        Disposable disposable = mArtistRepository.searchArtist(artistName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
