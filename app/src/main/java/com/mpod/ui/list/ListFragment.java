package com.mpod.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mpod.R;
import com.mpod.data.Artist;
import com.mpod.ui.details.DetailsActivity;
import com.mpod.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * Created by xnorcode on 15/07/2018.
 */
public class ListFragment extends DaggerFragment implements ListContract.View {

    @BindView(R.id.list_recyclerView)
    RecyclerView mRecyclerView;

    private ListRecyclerAdapter mRecyclerAdapter;

    @Inject
    ListContract.Presenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        // bind butterknife with view
        ButterKnife.bind(this, rootView);

        // set layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // init recycler adapter and register it with this view
        mRecyclerAdapter = new ListRecyclerAdapter(this);

        // set recycler view adapter
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // register view
        mPresenter.setView(this);

        // get artist to search from intent
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(AppConstants.ARTIST_NAME)) {
            // search and download artist
            mPresenter.searchArtist(arguments.getString(AppConstants.ARTIST_NAME));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.dropView();

        if (mRecyclerAdapter != null) mRecyclerAdapter.destroy();
        mRecyclerAdapter = null;

        mRecyclerView = null;
    }

    @Override
    public void showArtists(List<Artist> artists) {
        // add artists to adapter for display
        mRecyclerAdapter.swapData(artists);
    }

    @Override
    public void showArtistDetails(String mbid) {
        // pass searched artist name to list activity
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(AppConstants.ARTIST_MBID, mbid);
        startActivity(intent);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {
        // used for manual injection of presenter
    }
}
