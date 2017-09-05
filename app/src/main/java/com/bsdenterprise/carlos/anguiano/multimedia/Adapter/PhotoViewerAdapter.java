//package com.bsdenterprise.carlos.anguiano.multimedia.Adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.net.Uri;
//import android.support.annotation.Nullable;
//import android.support.v4.view.PagerAdapter;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.ImageButton;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.bsdenterprise.carlos.anguiano.multimedia.R;
//
//import java.io.File;
//import java.util.ArrayList;
//
///**
// * Created by Carlos Anguiano on 05/09/17.
// * For more info contact: c.joseanguiano@gmail.com
// */
//
//class PhotoViewerAdapter extends PagerAdapter {
//    private static final int MAX_WIDTH = AndroidUtilities.getScreenWidth()/3;
//    private static final int MAX_HEIGHT = 1280;
//
//    private static int size = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));
//    private ArrayList<String> paths = new ArrayList<>();
//    private final Activity _activity;
//    private ArrayList<String> _imagePaths;
//
//    @Override
//    public int getItemPosition(Object object) {
//        for (int i = 0; i < paths.size(); i++) {
//            if (paths.get(i).equals(object))
//                return i;
//        }
//        return 0;
//    }
//
//    // constructor
//    public PhotoViewerAdapter(Activity activity,
//                              ArrayList<String> imagePaths) {
//        this._activity = activity;
//        this.paths = imagePaths;
//
////        for (ChatMessage chatMessage:_imagePaths) {
////            MessageAttachment attachment = null;
////            if(chatMessage.getMessageType().equals(IMAGE)
////                    ||chatMessage.getMessageType().equals(VIDEO)){
////                paths.add(chatMessage);
////            }
////        }
//    }
//    @Nullable
//    public String getCurrentItem(int position){
//        if(paths.size()>0) {
//            return paths.get(position);
//        }else
//            return null;
//    }
//    @Override
//    public int getCount() {
//        return this.paths.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return position +" of "+ getCount();
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, final int position) {
//        final TouchImageView imgDisplay;
//        ImageButton btnPlay;
//        final ImageButton btnDownload;
//        final ProgressBar progressBar;
//        TextView messageTextView;
//
//        LayoutInflater inflater = (LayoutInflater) _activity
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View viewLayout = inflater.inflate(R.layout.item_fullscreen_image, container,
//                false);
//
//        imgDisplay = viewLayout.findViewById(R.id.imgDisplay);
//        messageTextView = viewLayout.findViewById(R.id.photoDescription);
//        btnPlay = viewLayout.findViewById(R.id.btnPlay);
//        btnDownload = viewLayout.findViewById(R.id.btnDownload);
//        progressBar = viewLayout.findViewById(R.id.progressBar);
//
////        BitmapFactory.Options options = new BitmapFactory.Options();
////        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
////        Bitmap bitmap = BitmapFactory.decodeFile(_imagePaths.get(position), options);
////        imgDisplay.setImageBitmap(bitmap);
//        messageTextView.setVisibility(View.GONE);
//        btnPlay.setVisibility(View.GONE);
//        btnDownload.setVisibility(View.GONE);
//        progressBar.setVisibility(View.GONE);
//        String localPath = paths.get(position);
//
//        File thumbnailFile = AndroidUtilities
//                .createImageThumbnail(localPath);
////                File imageFile = new File(localPath);
//
//        if (thumbnailFile!=null && thumbnailFile.exists()) {
//            Uri uri = Uri.fromFile(thumbnailFile);
//
//            Picasso.with(ApplicationSingleton.getInstance())
//                    .load(uri)
//                    .into(imgDisplay);
//        } /*else {
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(_activity);
//                    alertDialogBuilder.setTitle(R.string.title_image_sd);
//                    alertDialogBuilder.setMessage(R.string.subtitle_image_sd);
//                    alertDialogBuilder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface qbitsChat, int which) {
//                            qbitsChat.dismiss();
//                            //_activity.finish();
//                            //
//                            /// TODO: Tomar posicion de la imagen.
//
//                        }
//                    });
//                    AlertDialog alertDialogBuilder1 = alertDialogBuilder.create();
//                    alertDialogBuilder.show();
//                }*/
//
//
//
//        container.addView(viewLayout);
//
//        return viewLayout;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((FrameLayout) object);
//
//    }
//
//    public void removeItem(int currentPage) {
//        paths.remove(currentPage);
//        notifyDataSetChanged();
//    }
//
////    public void removeMessage(int currentPage) {
////        paths.remove(currentPage);
////        notifyItemRemoved();
////            for (int i = 0; i < paths.size(); i++) {
////                if(qbitsChat.getRemoteJid().equals(dialogs.get(i).getRemoteJid())){
////                    this.dialogs.remove(i);
////                    notifyItemRemoved(i);
////                }
////            }
////
////    }
//}