package com.tarek.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.RecyclerViewHolder> {
    private Context mContext;
    private Movie[] mMovie;
    final private ListItemClickListener mOnClickListener;


    public PostersAdapter(Context context,Movie[] movie,ListItemClickListener listener) {
        mContext = context;
        mOnClickListener = listener;
        mMovie = movie;
    }
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poster_grid_item,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Uri uri = Uri.parse(mMovie[position].getPosterURL());
        Log.d("TAGG","asd");
        Picasso.with(mContext).load(uri).placeholder(R.drawable.ic_highlight_off_black_24dp).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return JsonUtil.postersToShow;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.poster_iv);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
