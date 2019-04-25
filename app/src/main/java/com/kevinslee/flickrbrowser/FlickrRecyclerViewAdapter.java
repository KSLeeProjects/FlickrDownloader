package com.kevinslee.flickrbrowser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
@SuppressWarnings({"unused", "Typo:"})
class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.FlickrImageViewHolder> {
    private static final String TAG = "FlickrRecyclerViewAdapt";
    private List<Photo> mPhotoList;

    public FlickrRecyclerViewAdapter(List<Photo> photoList, Context context) {
        mPhotoList = photoList;
        mContext = context;
    }

    @Override
    public void onBindViewHolder(FlickrImageViewHolder holder, int position) {
        //Wants new data to be stored in the view holder.
        if ((mPhotoList==null) || (mPhotoList.size()==0)){
            holder.thumbnail.setImageResource(R.drawable.clip);
            holder.title.setText("Cannot find any images!");
            holder.author.setText("Try other tags and try separating words by commas for more accurate results!");
        } else {
            Photo photoItem = mPhotoList.get(position);
            Log.d(TAG, "onBindViewHolder: " + photoItem.getTitle() + "-->" + position);
            Picasso.get().load(photoItem.getImage())
                    .error(R.drawable.placeholder1)
                    .placeholder(R.drawable.placeholder1)
                    .into(holder.thumbnail);

            holder.title.setText(photoItem.getTitle());
            holder.author.setText("By : " + photoItem.getAuthor());
        }
    }

    @Override
    public FlickrImageViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        //called by the layout manager when it needs a new view
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);

        return new FlickrImageViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return ((mPhotoList!=null)&&(mPhotoList.size()!=0) ? mPhotoList.size() : 1);
    }

    void loadNewData(List<Photo> newPhotos){
        mPhotoList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position){
        return ((mPhotoList!=null)&&(mPhotoList.size()!=0) ? mPhotoList.get(position) : null);
    }

    private Context mContext;

    static class FlickrImageViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "FlickrImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;
        TextView author = null;

        public FlickrImageViewHolder(View itemView){
            super(itemView);
            Log.d(TAG, "FlickrImageViewHolder: starts");
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.author = (TextView) itemView.findViewById(R.id.author);
        }

    }
}
