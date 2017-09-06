package com.bsdenterprise.carlos.anguiano.multimedia.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bsdenterprise.carlos.anguiano.multimedia.R;
import com.mikepenz.iconics.typeface.ITypeface;

import java.util.ArrayList;

import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainSingleAlbumActivity.EXTRA_RESULT_SELECTED_PICTURE;

public class ShowMediaFileActivity extends AppCompatActivity {

    public static final String EXTRA_RESULT_SELECTED_PICTURE = "extra_result_selected_picture";
    private static final String TAG = ShowMediaFileActivity.class.getSimpleName();
    private ArrayList<String> mImagePath = new ArrayList<>();
    private Toolbar toolbar;
    private ImageView addPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_media_file);
        Log.i(TAG, "onCreate: ");
        initToolbar();
        startView();
        showIntent();

        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoreImages();
            }
        });
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
            mImagePath = getIntent().getExtras().getStringArrayList(EXTRA_RESULT_SELECTED_PICTURE);
        }
    }

    private void startView() {
        Log.i(TAG, "initView: ");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        addPicture = (ImageView) findViewById(R.id.imageView5);
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
}
