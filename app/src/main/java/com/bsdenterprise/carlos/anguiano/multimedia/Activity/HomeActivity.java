package com.bsdenterprise.carlos.anguiano.multimedia.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bsdenterprise.carlos.anguiano.multimedia.R;

import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainAlbumListActivity.EXTRA_JID;
import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainAlbumListActivity.EXTRA_NAME;
import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainAlbumListActivity.EXTRA_RESULT_SELECTED_ALBUM;
import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainSingleAlbumActivity.EXTRA_RESULT_SELECTED_PICTURE;
import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainSingleAlbumActivity.EXTRA_RESULT_SELECTED_VIDEO;
import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainSingleAlbumActivity.EXTRA_TYPE_BUCKET;
import static com.bsdenterprise.carlos.anguiano.multimedia.Activity.MainSingleAlbumActivity.EXTRA_TYPE_FILE;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private static final int RESULT_LOAD_IMAGE = 3;//Result Album
    private static final String EXTRA_BUCKET = "extra_bucket";
    private static final String EXTRA_TYPE_ALBUM = "extra_type_album";
    private static final String EXTRA_BACK_SELECT = "extra_back_select";

    private Button button;
    private String jid = "carchat@bsdenterprise.com";
    private String user = "Carlos Anguiano";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = (Button) findViewById(R.id.buttonHome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: ");
                MainAlbumListActivity.startForResult(HomeActivity.this, user, jid, RESULT_LOAD_IMAGE);
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
                intent.putExtra(MainAlbumListActivity.EXTRA_BUCKET, data.getStringExtra(EXTRA_BUCKET));
                intent.putExtra(MainAlbumListActivity.EXTRA_TYPE_ALBUM, data.getStringExtra(EXTRA_TYPE_ALBUM));
                intent.putExtra(MainAlbumListActivity.EXTRA_BACK_SELECT, data.getBooleanExtra(EXTRA_BACK_SELECT, true));
                startActivityForResult(intent, 12);
            } else {
                Log.i(TAG, "onActivityResult: ");
            }

        } else if (requestCode == 12 && resultCode == Activity.RESULT_OK || requestCode == 12 & resultCode == 0 || requestCode == 81 & resultCode == 0) {
            if (resultCode == 0) {
                Intent intent = new Intent(this, MainAlbumListActivity.class);
                intent.putExtra(EXTRA_JID, jid);
                intent.putExtra(EXTRA_NAME, user);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);

            } else {
                if (data.hasExtra(EXTRA_RESULT_SELECTED_PICTURE)) {
                    Log.i(TAG, "onActivityResult: ");

                    Intent i = new Intent(this, PhotoViewerActivity.class);
                    i.putExtra(PhotoViewerActivity.EXTRA_MEDIA_PATHS, data.getStringArrayListExtra(EXTRA_RESULT_SELECTED_PICTURE));
                    i.putExtra(EXTRA_TYPE_BUCKET, data.getStringExtra(EXTRA_TYPE_BUCKET));
                    i.putExtra(EXTRA_TYPE_FILE, data.getStringExtra(EXTRA_TYPE_FILE));
                    startActivityForResult(i, 80);
                }
                if (data.hasExtra(EXTRA_RESULT_SELECTED_VIDEO)) {
                    Log.i(TAG, "onActivityResult: ");
//                    Intent videoPlayer = new Intent(this, VideoPlayerActivity.class);
//                    videoPlayer.putExtra(EXTRA_MEDIA_PATHS_VIDEO, data.getStringArrayListExtra(EXTRA_RESULT_SELECTED_MEDIA_VIDEO));
//                    videoPlayer.putExtra(EXTRA_PHOTO_ORIENTATION, orientation);
//                    videoPlayer.putExtra(EXTRA_TYPE_BUCKET, data.getStringExtra(EXTRA_TYPE_BUCKET));
//                    videoPlayer.putExtra(EXTRA_TYPE_FILE, data.getStringExtra(EXTRA_TYPE_FILE));
//                    videoPlayer.putExtra(EXTRA_NAME, nameMultimedia);
//                    videoPlayer.putExtra(EXTRA_PHOTO_EDIT, true);
//                    startActivityForResult(videoPlayer, 81);
                }
            }
        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_CANCELED) {
            Log.i(TAG, "onActivityResult: ");
        }
    }
}
