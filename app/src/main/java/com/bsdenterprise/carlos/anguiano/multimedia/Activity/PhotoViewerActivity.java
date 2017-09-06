package com.bsdenterprise.carlos.anguiano.multimedia.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bsdenterprise.carlos.anguiano.multimedia.R;
import com.theartofdev.edmodo.cropper.CropImage;

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
    boolean iconRemove = false;
    private int position;
    private ViewPager viewPager;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK && mImagePaths.size() != 0) {

                try {
                    viewPager.setAdapter(null);
                    Uri resultUri = result.getUri();
                    String newPath = resultUri.getPath();
                    mImagePaths.remove(position);
                    mImagePaths.add(newPath);
//                    adapter = new PhotoViewerAdapter(this, mImagePaths);
//                    createViewPager();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Log.i(TAG, "onActivityResult: " + result.getError());
            }
        }
        if (requestCode == 20 && resultCode == Activity.RESULT_OK) {
            viewPager.setAdapter(null);
            paths = data.getExtras().getStringArrayList(EXTRA_RESULT_SELECTED_PICTURE);
            if (paths != null) {
                mImagePaths = paths;

                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < mImagePaths.size(); i++) {
                    stringBuilder.append(mImagePaths.get(i));
                }
                String newString = stringBuilder.toString();
                if (newString.contains(".jpg") || (newString.contains(".png")) || (newString.contains(".jpeg"))) {
//                    adapter = new PhotoViewerAdapter(this, paths);
                    createViewPager();
                }
            }
        }
    }

    public void createViewPager() {
        FrameLayout frameLayoutI = (FrameLayout) findViewById(R.id.fragmentContainer);
        viewPager = new ViewPager(this);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.MATCH_PARENT);
        viewPager.setId(R.id.viewPagerNew);
        viewPager.setLayoutParams(params);
        frameLayoutI.addView(viewPager);
//        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.clear();
        MenuItem item = menu.add(Menu.NONE, R.id.crop_image_menu_crop, Menu.NONE, R.string.crop_image_menu_crop);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setIcon(R.drawable.crop);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.crop_image_menu_crop:
//                final int[] chatMessage = new int[1];
////                chatMessage[0] = adapter.getItemPosition(currentPage);
////                adapter.removeItem(currentPage);
//                position = viewPager.getCurrentItem();
//
//                String a = adapter.getCurrentItem(position);
//                CropImage.activity(Uri.fromFile(new File(a)))
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .start(this);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

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