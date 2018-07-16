package com.mpod.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mpod.R;
import com.mpod.data.Artist;
import com.mpod.utils.AppConstants;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * Created by xnorcode on 16/07/2018.
 */
public class DetailsFragment extends DaggerFragment implements DetailsContract.View {

    @BindView(R.id.details_artist_image)
    ImageView mImage;

    @BindView(R.id.details_artist_name)
    TextView mName;

    @BindView(R.id.details_artist_listeners)
    TextView mListeners;

    @BindView(R.id.details_artist_playcount)
    TextView mPlaycount;

    @BindView(R.id.details_artist_summary)
    TextView mSummary;


    @Inject
    DetailsContract.Presenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        // bind butterknife with view
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        mPresenter.setView(this);

        // get artist mbid from intent
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(AppConstants.ARTIST_MBID)) {
            // find and download artist info
            mPresenter.getArtistInfo(arguments.getString(AppConstants.ARTIST_MBID));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.dropView();
    }

    @Override
    public void showArtistDetails(Artist artist) {
        // show artist image
        Picasso.get().load(artist.getImageUrl()).into(mImage);

        // show artist name
        mName.setText(artist.getName());
        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open artist last.fm profile in browser
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(artist.getLastfmProfileUrl()));
                startActivity(intent);
            }
        });

        // show artist listeners
        StringBuilder sb = new StringBuilder("Listeners: ");
        sb.append(artist.getListeners());
        mListeners.setText(sb.toString());

        // show artist playcount
        StringBuilder sb2 = new StringBuilder("Playcount: ");
        sb2.append(artist.getPlaycount());
        mPlaycount.setText(sb2.toString());

        // show artist bio summary
        mSummary.setText(Html.fromHtml(artist.getBioSummary()));
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        // used for manual injection of presenter
    }
}
