package com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bsdenterprise.carlos.anguiano.multimedia.R;
import com.bsdenterprise.carlos.anguiano.multimedia.Multimedia.Utils.TouchImageView;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Carlos Anguiano on 06/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public class ShowMediaAdapter extends PagerAdapter {
    private ArrayList<String> paths = new ArrayList<>();
    private LayoutInflater inflater;
    private final Activity _activity;

    @Override
    public int getItemPosition(Object object) {
        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).equals(object))
                return i;
        }
        return 0;
    }

    public ShowMediaAdapter(Activity activity,ArrayList<String> imagePaths) {
        this._activity = activity;
        this.paths = imagePaths;
        this.inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return this.paths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position + " of " + getCount();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final TouchImageView imgDisplay;

        ImageButton btnPlay;
        final ImageButton btnDownload;
        final ProgressBar progressBar;
        TextView messageTextView;
        View viewLayout = inflater.inflate(R.layout.item_fullscreen_image, container, false);

//https://stackoverflow.com/questions/33818873/setting-images-from-url-in-viewpager-android/33819252
//        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View viewLayout = inflater.inflate(R.layout.item_fullscreen_image, container, false);

        imgDisplay = (TouchImageView) viewLayout.findViewById(R.id.imgDisplay);
        messageTextView = (TextView) viewLayout.findViewById(R.id.photoDescription);
        btnPlay = (ImageButton) viewLayout.findViewById(R.id.btnPlay);
        btnDownload = (ImageButton) viewLayout.findViewById(R.id.btnDownload);
        progressBar = (ProgressBar) viewLayout.findViewById(R.id.progressBar);

        messageTextView.setVisibility(View.GONE);
        btnPlay.setVisibility(View.GONE);
        btnDownload.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        String localPath = paths.get(position);
        File file = new File(localPath);
        if (file.exists()) {
            Glide.with(_activity)
                    .load(new File(localPath))
                    .into(imgDisplay);
            container.addView(viewLayout);
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(_activity);
            alertDialog.setTitle("Error de Imagen");
            alertDialog.setMessage("Virifica que la imagen exista dentro de la tarjeta SD");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    _activity.finish();
                }
            });
            AlertDialog alertDialog1 = alertDialog.create();
            alertDialog.show();
        }


        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);

    }

    @Nullable
    public String getCurrentItem(int position){
        if(paths.size()>0) {
            return paths.get(position);
        }else
            return null;
    }
}
