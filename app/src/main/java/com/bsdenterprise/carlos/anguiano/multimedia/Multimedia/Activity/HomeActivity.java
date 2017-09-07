package com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bsdenterprise.carlos.anguiano.multimedia.R;
import com.bsdenterprise.carlos.anguiano.multimedia.VideoPlayer.Activity.VideoPlayerActivity;

import java.io.File;
import java.util.ArrayList;

import static com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity.MainAlbumListActivity.EXTRA_JID;
import static com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity.MainAlbumListActivity.EXTRA_NAME;
import static com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity.MainAlbumListActivity.EXTRA_RESULT_SELECTED_ALBUM;
import static com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity.MainSingleAlbumActivity.EXTRA_RESULT_SELECTED_PICTURE;
import static com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity.MainSingleAlbumActivity.EXTRA_RESULT_SELECTED_VIDEO;
import static com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity.MainSingleAlbumActivity.EXTRA_TYPE_BUCKET;
import static com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Activity.MainSingleAlbumActivity.EXTRA_TYPE_FILE;
import static com.bsdenterprise.carlos.anguiano.multimedia.VideoPlayer.Activity.VideoPlayerActivity.EXTRA_MEDIA_PATHS_VIDEO;

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

        }

//        ------------------------------------        ------------------------------------
        if (requestCode == 12 && resultCode == Activity.RESULT_OK) {
            if (data.hasExtra(EXTRA_RESULT_SELECTED_PICTURE)) {
                Log.i(TAG, "onActivityResult: ");
                Intent i = new Intent(this, ShowMediaFileActivity.class);
                i.putExtra(ShowMediaFileActivity.EXTRA_RESULT_SELECTED_PICTURE, data.getStringArrayListExtra(EXTRA_RESULT_SELECTED_PICTURE));
                i.putExtra(EXTRA_TYPE_BUCKET, data.getStringExtra(EXTRA_TYPE_BUCKET));
                i.putExtra(EXTRA_TYPE_FILE, data.getStringExtra(EXTRA_TYPE_FILE));
                startActivityForResult(i, 80);
            }
            if (data.hasExtra(EXTRA_RESULT_SELECTED_VIDEO)) {
                Log.i(TAG, "onActivityResult: ");
                Intent videoPlayer = new Intent(this, VideoPlayerActivity.class);
                videoPlayer.putExtra(EXTRA_MEDIA_PATHS_VIDEO, data.getStringArrayListExtra(EXTRA_RESULT_SELECTED_VIDEO));
                videoPlayer.putExtra(EXTRA_TYPE_BUCKET, data.getStringExtra(EXTRA_TYPE_BUCKET));
                videoPlayer.putExtra(EXTRA_TYPE_FILE, data.getStringExtra(EXTRA_TYPE_FILE));
                videoPlayer.putExtra(EXTRA_NAME, user);
                startActivityForResult(videoPlayer, 81);
            }

        }

        //        ------------------------------------        ------------------------------------
        if (requestCode == 12 & resultCode == Activity.RESULT_CANCELED || requestCode == 81 & resultCode == Activity.RESULT_CANCELED) {
            Log.i(TAG, "onActivityResult: ");
            Intent intent = new Intent(this, MainAlbumListActivity.class);
            intent.putExtra(EXTRA_JID, jid);
            intent.putExtra(EXTRA_NAME, user);
            startActivityForResult(intent, RESULT_LOAD_IMAGE);

        }
        //        ------------------------------------        ------------------------------------


        if (requestCode == 81 && resultCode == Activity.RESULT_OK) {
            if (data.hasExtra(EXTRA_MEDIA_PATHS_VIDEO)) {
                ArrayList<String> mImagePaths;
                mImagePaths = data.getExtras().getStringArrayList(EXTRA_MEDIA_PATHS_VIDEO);
                if (mImagePaths != null) {
                    Intent i = new Intent(this, ShowMediaFileActivity.class);
                    i.putExtra(ShowMediaFileActivity.EXTRA_RESULT_SELECTED_VIDEO, mImagePaths);
                    i.putExtra(EXTRA_TYPE_BUCKET, data.getStringExtra(EXTRA_TYPE_BUCKET));
                    i.putExtra(EXTRA_TYPE_FILE, data.getStringExtra(EXTRA_TYPE_FILE));
                    startActivityForResult(i, 81);
                }
            } else {
                ArrayList<String> paths = data.getExtras().getStringArrayList("paths");
                if (paths == null) {
                    return;
                } else {
                    StringBuffer result = new StringBuffer();
                    for (int i = 0; i < paths.size(); i++) {
                        result.append(paths.get(i));
                    }
                    String fileString = result.toString();
                    File file = new File(fileString);
                    Log.i(TAG, "onActivityResult: ");
                }
            }

        }


        //        ------------------------------------        ------------------------------------

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_CANCELED) {
            Log.i(TAG, "onActivityResult: ");
        }
        if (requestCode == 80 && resultCode == RESULT_CANCELED) {
            Log.i(TAG, "onActivityResult: ");
            Intent intent = new Intent(this, MainAlbumListActivity.class);
            intent.putExtra(EXTRA_JID, jid);
            intent.putExtra(EXTRA_NAME, user);
            startActivityForResult(intent, RESULT_LOAD_IMAGE);
        }
    }
}
