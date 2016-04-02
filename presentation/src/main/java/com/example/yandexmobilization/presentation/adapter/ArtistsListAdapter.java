package com.example.yandexmobilization.presentation.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yandexmobilization.R;
import com.example.yandexmobilization.domain.model.Artist;
import com.example.yandexmobilization.presentation.ui.activity.ArtistsListActivity;
import com.example.yandexmobilization.presentation.view.ArtistsListMvpView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Жамбыл on 4/2/2016.
 */
public class ArtistsListAdapter extends RecyclerView.Adapter<ArtistsListAdapter.ArtistViewHolder> {

    private List<Artist> artists;
    private Context context;
    private ArtistsListMvpView artistsListMvpView;

    public ArtistsListAdapter(Context context, ArtistsListMvpView artistsListMvpView) {
        this.context = context;
        this.artistsListMvpView = artistsListMvpView;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_template_view, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ArtistViewHolder holder, final int position) {
        holder.nameTextView.setText(artists.get(position).getName());
        Picasso.with(context).load(artists.get(position).getBigCover()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                holder.imageView.setTransitionName("image");
                artistsListMvpView.showArtistDetails(artists.get(position),holder.imageView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.name_text_view) TextView nameTextView;
        @Bind(R.id.card_view) CardView cardView;
        @Bind(R.id.image) ImageView imageView;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            cardView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
    }
}
