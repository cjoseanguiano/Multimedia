package com.bsdenterprise.carlos.anguiano.multimedia.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bsdenterprise.carlos.anguiano.multimedia.R;

import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainAlbumListActivity.EXTRA_RESULT_SELECTED_ALBUM;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private static final int RESULT_LOAD_IMAGE = 3;//Result Album
    private static final String EXTRA_BUCKET = "extra_bucket";
    private static final String EXTRA_TYPE_ALBUM = "extra_type_album";
    private static final String EXTRA_BACK_SELECT = "extra_back_select";

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = (Button) findViewById(R.id.buttonHome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: ");
                MainAlbumListActivity.startForResult(HomeActivity.this, "Carlos Anguiano", "carchat@bsdenterprise.com", RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            Log.i(TAG, "onActivityResult: ");
            if (data.hasExtra(EXTRA_RESULT_SELECTED_ALBUM)) {
                Intent intent = new Intent(this, MainSingleAlbumActivity.class);
                intent.putExtra(EXTRA_BUCKET, data.getStringExtra(EXTRA_BUCKET));
                intent.putExtra(EXTRA_TYPE_ALBUM, data.getStringExtra(EXTRA_TYPE_ALBUM));
                intent.putExtra(EXTRA_BACK_SELECT, data.getBooleanExtra(EXTRA_BACK_SELECT, true));
                startActivityForResult(intent, 12);
            } else {
                Log.i(TAG, "onActivityResult: ");
            }

        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_CANCELED) {
            Log.i(TAG, "onActivityResult: ");
        }
    }
}
