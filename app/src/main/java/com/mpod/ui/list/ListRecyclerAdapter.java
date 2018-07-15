package com.mpod.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mpod.R;
import com.mpod.data.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xnorcode on 15/07/2018.
 */
public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.ListViewHolder> {


    /**
     * Custom ViewHolder for our recycler adapter item views
     */
    class ListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_artist_image)
        ImageView mImage;


        @BindView(R.id.item_artist_name)
        TextView mName;


        @BindView(R.id.item_artist_listeners)
        TextView mListeners;

        View mView;


        public ListViewHolder(View view) {
            super(view);

            // bind data with butterknife
            ButterKnife.bind(this, view);

            // bind view
            mView = view;
        }
    }


    private List<Artist> mArtists;

    private ListContract.View mView;


    /**
     * Constructor
     *
     * @param mView The view to respond to
     */
    public ListRecyclerAdapter(ListContract.View mView) {
        this.mView = mView;
    }


    /**
     * Add artist to show in list
     *
     * @param artists The list of artists
     */
    public void swapData(List<Artist> artists) {
        mArtists = artists;
        notifyDataSetChanged();
    }


    /**
     * Release memory ref
     */
    public void destroy() {
        mArtists = null;
        mView = null;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        if (mArtists == null || mArtists.size() == 0) return;

        // get artist
        Artist artist = mArtists.get(position);

        // show artist image
        Picasso.get().load(artist.getImageUrl()).into(holder.mImage);

        // show artist name
        holder.mName.setText(artist.getName());

        // show artist listeners
        StringBuilder sb = new StringBuilder("Listeners: ");
        sb.append(artist.getListeners());
        holder.mListeners.setText(sb.toString());

        // set onClick listener for each view to pass artist mbid to details activity
        holder.mView.setOnClickListener(v -> mView.showArtistDetails(artist.getMbID()));
    }


    @Override
    public int getItemCount() {
        if (mArtists == null) return 0;
        return mArtists.size();
    }
}
