package com.bsdenterprise.carlos.anguiano.multimedia.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.bsdenterprise.carlos.anguiano.multimedia.R;

import java.util.ArrayList;

import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainSingleAlbumActivity.EXTRA_RESULT_SELECTED_PICTURE;

public class ShowMediaFileActivity extends AppCompatActivity {

    public static final String EXTRA_RESULT_SELECTED_PICTURE = "extra_result_selected_picture";
    private static final String TAG = ShowMediaFileActivity.class.getSimpleName();
    private ArrayList<String> mImagePath = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_media_file);
        initToolbar();
        Log.i(TAG, "onCreate: ");
        if (getIntent().hasExtra(EXTRA_RESULT_SELECTED_PICTURE)) {
            mImagePath = getIntent().getExtras().getStringArrayList(EXTRA_RESULT_SELECTED_PICTURE);
            Log.i(TAG, "onCreate: ");

        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
}
