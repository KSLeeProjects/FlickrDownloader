package com.kevinslee.flickrbrowser;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        activateToolbar(true);

        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra(PHOTO_TRANSFER);
        if (photo!=null){
            Resources resources = getResources();
            TextView photoTitle = (TextView) findViewById(R.id.photo_title);
            String text = resources.getString(R.string.photo_title_text, photo.getTitle());
            photoTitle.setText(text);
//            photoTitle.setText("Title : " + photo.getTitle());

            TextView photoTags = (TextView) findViewById(R.id.photo_tags);
            text = resources.getString(R.string.photo_tags_text, photo.getTags());
            photoTags.setText(text);
//            photoTags.setText("Tags : " + photo.getTags());

            TextView photoAuthor = (TextView) findViewById(R.id.photo_author);
            photoAuthor.setText(resources.getString(R.string.photo_author_text, photo.getAuthor()));
//            photoAuthor.setText("Author : " + photo.getAuthor());

            ImageView photoImage = (ImageView) findViewById(R.id.photo_image);
            Picasso.get().load(photo.getLink())
                    .error(R.drawable.placeholder1)
                    .placeholder(R.drawable.placeholder1)
                    .into(photoImage);
        }


    }

}
