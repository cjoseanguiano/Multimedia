package com.bsdenterprise.carlos.anguiano.multimedia.Interface;

import com.bsdenterprise.carlos.anguiano.multimedia.Model.DataPicturesAlbum;

/**
 * Created by Carlos Anguiano on 05/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public interface MediaAdapterAllAlbumClickListener {
    void itemClicked(DataPicturesAlbum dataPicturesAlbums, String folder, String type, boolean backPressed);
}
