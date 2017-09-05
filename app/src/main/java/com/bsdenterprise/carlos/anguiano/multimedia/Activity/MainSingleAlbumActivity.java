package com.bsdenterprise.carlos.anguiano.multimedia.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bsdenterprise.carlos.anguiano.multimedia.R;

public class MainSingleAlbumActivity extends AppCompatActivity {

    private static final String TAG = MainSingleAlbumActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_single_album);
        Log.i(TAG, "onCreate: ");
    }
}
