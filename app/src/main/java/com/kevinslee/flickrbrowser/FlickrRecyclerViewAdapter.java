package com.kevinslee.flickrbrowser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    public void onBindViewHolder(FlickrImageViewHolder holder, int position, List<Object> payloads) {
        //Wants new data to be stored in the view holder.
        super.onBindViewHolder(holder, position, payloads);
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
        Log.d(TAG, "getItemCount: called");
        return ((mPhotoList!=null)&&(mPhotoList.size()!=0) ? mPhotoList.size() : 0);
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

        public FlickrImageViewHolder(View itemView){
            super(itemView);
            Log.d(TAG, "FlickrImageViewHolder: starts");
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }

    }
}
