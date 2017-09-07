package com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.LoginFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Adapter.ShowMediaAdapter;
import com.bsdenterprise.carlos.anguiano.multimedia.R;
import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import static com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity.MainSingleAlbumActivity.EXTRA_RESULT_SELECTED_PICTURE;
import static com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity.MainSingleAlbumActivity.EXTRA_TYPE_BUCKET;
import static com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity.MainSingleAlbumActivity.EXTRA_TYPE_FILE;

public class ShowMediaFileActivity extends AppCompatActivity {

    public static final String EXTRA_RESULT_SELECTED_PICTURE = "extra_result_selected_picture";
    private static final String TAG = ShowMediaFileActivity.class.getSimpleName();
    private ArrayList<String> mImagePath = new ArrayList<>();
    private Toolbar toolbar;
    private ImageView addPicture;
    private ShowMediaAdapter adapter;
    private int currentPage;
    private EditText photoDescription;
    private String newPath;
    private FloatingActionButton send;
    private ImageView imageView;
    private BitmapFactory.Options options;
    private LinearLayout thumbnailsContainer;
    //    private ViewPager viewpagerMedia;
    private String typeBucket;
    private String typeFile;
    private int position;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_media_file);
        Log.i(TAG, "onCreate: ");

        startView();
        initToolbar();
        showIntent();
        inflateThumbnails();


        adapter = new ShowMediaAdapter(this, mImagePath);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mImagePath.size(); i++) {
            stringBuilder.append(mImagePath.get(i));
        }
        newPath = stringBuilder.toString();

        if (newPath.contains(".jpg") || (newPath.contains(".png")) || (newPath.contains(".jpeg"))) {
            createViewPager();
        }

        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoreImages();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPhoto();

            }
        });
    }

    private void sendPhoto() {
        Log.i(TAG, "sendPhoto: ");
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.clear();
        MenuItem item = menu.add(Menu.NONE, R.id.crop_image_menu_crop, Menu.NONE, R.string.crop_image_menu_crop);
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setIcon(R.drawable.crop);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.crop_image_menu_crop:
                final int[] chatMessage = new int[1];
                chatMessage[0] = adapter.getItemPosition(currentPage);
                position = viewPager.getCurrentItem();

                String a = adapter.getCurrentItem(position);
                CropImage.activity(Uri.fromFile(new File(a)))
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addMoreImages() {
        Log.i(TAG, "addMoreImages: ");
        Intent intent = new Intent(this, MainSingleAlbumActivity.class);
        intent.putStringArrayListExtra(EXTRA_RESULT_SELECTED_PICTURE, mImagePath);
        intent.putExtra(EXTRA_TYPE_BUCKET, typeBucket);
        intent.putExtra(EXTRA_TYPE_FILE, typeFile);
        startActivityForResult(intent, 20);
    }

    private void showIntent() {
        Log.i(TAG, "showIntent: ");
        typeBucket = getIntent().getStringExtra(EXTRA_TYPE_BUCKET);
        typeFile = getIntent().getStringExtra(EXTRA_TYPE_FILE);
        if (getIntent().hasExtra(EXTRA_RESULT_SELECTED_PICTURE)) {
            mImagePath = getIntent().getExtras().getStringArrayList(EXTRA_RESULT_SELECTED_PICTURE);
        }
    }

    private void startView() {
        Log.i(TAG, "initView: ");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        addPicture = (ImageView) findViewById(R.id.imageView5);
        photoDescription = (EditText) findViewById(R.id.photoDescription);
        send = (FloatingActionButton) findViewById(R.id.fab);
        thumbnailsContainer = (LinearLayout) findViewById(R.id.container);
//        viewpagerMedia = (ViewPager) findViewById(R.id.viewpagerMedia);
        disableEditText(photoDescription);

    }

    private void initToolbar() {
        Log.i(TAG, "initToolbar: ");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Hola Mundo");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void inflateThumbnails() {
        for (int i = 0; i < mImagePath.size(); i++) {
            View imageLayout = getLayoutInflater().inflate(R.layout.item_image, null);
            ImageView one = imageLayout.findViewById(R.id.img_thumb);
            one.setOnClickListener(onChagePageClickListener(i));
            options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            options.inDither = false;


            Uri uri = Uri.parse(mImagePath.get(i));
            Glide.with(this)
                    .load(new File(uri.getPath()))
                    .into(one);

            thumbnailsContainer.addView(imageLayout);
        }
    }

    private View.OnClickListener onChagePageClickListener(final int i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(i);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK && mImagePath.size() != 0) {

                try {
                    viewPager.setAdapter(null);
                    Uri resultUri = result.getUri();
                    String newPath = resultUri.getPath();
                    mImagePath.remove(position);
                    mImagePath.add(newPath);
                    adapter = new ShowMediaAdapter(this, mImagePath);
                    createViewPager();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Log.i(TAG, "onActivityResult: " + "" + result.getError());
            }
        }
        if (requestCode == 20 && resultCode == Activity.RESULT_OK) {
            Log.i(TAG, "onActivityResult: ");
            viewPager.setAdapter(null);
            mImagePath = data.getExtras().getStringArrayList(EXTRA_RESULT_SELECTED_PICTURE);
            if (mImagePath != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < mImagePath.size(); i++) {
                    stringBuilder.append(mImagePath.get(i));
                }
                String newString = stringBuilder.toString();
                if (newString.contains(".jpg") || (newString.contains(".png")) || (newString.contains(".jpeg"))) {
                    adapter = new ShowMediaAdapter(this, mImagePath);
                    viewPager.setAdapter(adapter);
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
        viewPager.setAdapter(adapter);
    }

}
