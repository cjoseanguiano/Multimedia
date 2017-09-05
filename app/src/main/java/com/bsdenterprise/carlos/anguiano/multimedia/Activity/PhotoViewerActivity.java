package com.bsdenterprise.carlos.anguiano.multimedia.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bsdenterprise.carlos.anguiano.multimedia.R;

import java.util.ArrayList;

import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainSingleAlbumActivity.EXTRA_RESULT_SELECTED_PICTURE;

public class PhotoViewerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    public static final String TAG = PhotoViewerActivity.class.getSimpleName();
    private static final String EXTRA_TYPE_BUCKET = "extra_type_bucket";
    private static final String EXTRA_TYPE_FILE = "extra_type_file";
    public static final String EXTRA_MEDIA_PATHS = "extra_media_paths";

    private FloatingActionButton send;
    private Bundle bundle;
    private ArrayList<String> paths = new ArrayList<>();
    private ArrayList<String> mImagePaths = new ArrayList<>();
    private ImageView addPicture;
    private String typeBucket;
    private String typeFile;
    private EditText photoDescription;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);
        startView();
        setUpToolbar();
        getAllIntent();
        disableEditText(photoDescription);

        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPicture();
            }
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i(TAG, "onPageScrolled: ");
    }

    @Override
    public void onPageSelected(int position) {
        Log.i(TAG, "onPageSelected: ");
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.i(TAG, "onPageScrollStateChanged: ");
    }


    private void getAllIntent() {
        bundle = getIntent().getExtras();
        paths = getIntent().getExtras().getStringArrayList(EXTRA_RESULT_SELECTED_PICTURE);
        typeBucket = getIntent().getStringExtra(EXTRA_TYPE_BUCKET);
        typeFile = getIntent().getStringExtra(EXTRA_TYPE_FILE);

    }

    private void startView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        photoDescription = (EditText) findViewById(R.id.photoDescription);
        addPicture = (ImageView) findViewById(R.id.imageView5);
        send = (FloatingActionButton) findViewById(R.id.fab);

    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setTextColor(Color.TRANSPARENT);
        editText.setHintTextColor(Color.TRANSPARENT);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }

    private void addPicture() {
        Intent intent = new Intent(this, MainSingleAlbumActivity.class);
        mImagePaths.add("/storage/sdcard0/dcim/IMG_20170831_172828.jpg");
        intent.putStringArrayListExtra(EXTRA_RESULT_SELECTED_PICTURE, mImagePaths);
        intent.putExtra(EXTRA_TYPE_BUCKET, typeBucket);
        intent.putExtra(EXTRA_TYPE_FILE, typeFile);
        startActivityForResult(intent, 20);

    }
}