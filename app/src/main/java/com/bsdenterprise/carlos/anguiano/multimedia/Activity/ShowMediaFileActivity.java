package com.bsdenterprise.carlos.anguiano.multimedia.Activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bsdenterprise.carlos.anguiano.multimedia.Adapter.ShowMediaAdapter;
import com.bsdenterprise.carlos.anguiano.multimedia.R;
import com.bumptech.glide.Glide;
import com.mikepenz.iconics.typeface.ITypeface;

import java.io.File;
import java.util.ArrayList;

import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainSingleAlbumActivity.EXTRA_RESULT_SELECTED_PICTURE;

public class ShowMediaFileActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    public static final String EXTRA_RESULT_SELECTED_PICTURE = "extra_result_selected_picture";
    private static final String TAG = ShowMediaFileActivity.class.getSimpleName();
    private ArrayList<String> mImagePath = new ArrayList<>();
    private Toolbar toolbar;
    private ImageView addPicture;
    private ViewPager viewPager;
    private ShowMediaAdapter adapter;
    private int currentPage;
    private EditText photoDescription;
    private String newPath;
    private FloatingActionButton send;
    private ImageView imageView;
    private BitmapFactory.Options options;
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> resourceFile = new ArrayList<>();
    private LinearLayout thumbnailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_media_file);
        Log.i(TAG, "onCreate: ");
        resourceFile.add("/storage/sdcard0/dcim/IMG_20170406_164424.jpg");
        resourceFile.add("/storage/sdcard0/dcim/IMG_20170425_181009.jpg");
        resourceFile.add("/storage/sdcard0/dcim/IMG_20170425_181005.jpg");
        resourceFile.add("/storage/sdcard0/dcim/IMG_20170406_164430.jpg");
        resourceFile.add("/storage/sdcard0/dcim/IMG_20170406_164424.jpg");
        resourceFile.add("/storage/sdcard0/dcim/IMG_20170425_181009.jpg");
        resourceFile.add("/storage/sdcard0/dcim/IMG_20170425_181005.jpg");
        resourceFile.add("/storage/sdcard0/dcim/IMG_20170406_164430.jpg");

        startView();
        initToolbar();
        showIntent();

        setImagesData();
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

    public void createViewPager() {
        FrameLayout frameLayoutI = (FrameLayout) findViewById(R.id.fragmentContainer);
        viewPager = new ViewPager(this);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.MATCH_PARENT);
        viewPager.setId(R.id.viewPagerNew);
        viewPager.setLayoutParams(params);
        frameLayoutI.addView(viewPager);
        viewPager.setAdapter(adapter);
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        currentPage = position;

    }

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
                Log.i(TAG, "onOptionsItemSelected: ");
                return true;
            case R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addMoreImages() {
        Log.i(TAG, "addMoreImages: ");
    }

    private void showIntent() {
        Log.i(TAG, "showIntent: ");
        if (getIntent().hasExtra(EXTRA_RESULT_SELECTED_PICTURE)) {
            mImagePath.add("/storage/sdcard0/dcim/IMG_20170406_164424.jpg");
            mImagePath.add("/storage/sdcard0/dcim/IMG_20170406_164430.jpg");
            mImagePath.add("/storage/sdcard0/dcim/IMG_20170425_181005.jpg");
//            mImagePath = getIntent().getExtras().getStringArrayList(EXTRA_RESULT_SELECTED_PICTURE);
        }
    }

    private void startView() {
        Log.i(TAG, "initView: ");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        addPicture = (ImageView) findViewById(R.id.imageView5);
        photoDescription = (EditText) findViewById(R.id.photoDescription);
        send = (FloatingActionButton) findViewById(R.id.fab);
        thumbnailsContainer = (LinearLayout) findViewById(R.id.container);
//        disableEditText(photoDescription);

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
        for (int i = 0; i < images.size(); i++) {
            View imageLayout = getLayoutInflater().inflate(R.layout.item_image, null);
            ImageView one = imageLayout.findViewById(R.id.img_thumb);
            one.setOnClickListener(onChagePageClickListener(i));
            options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            options.inDither = false;


            Uri uri = Uri.parse(resourceFile.get(i));
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

    private void setImagesData() {
        for (int i = 0; i < resourceFile.size(); i++) {
            images.add(resourceFile.get(i));
        }
    }
}
