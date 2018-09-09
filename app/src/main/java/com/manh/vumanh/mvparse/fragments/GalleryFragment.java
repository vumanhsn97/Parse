package com.manh.vumanh.mvparse.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.manh.vumanh.mvparse.R;
import com.manh.vumanh.mvparse.adapter.ImageAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class GalleryFragment extends BaseFragment {
    private Cursor cursor;
    private int column;
    ArrayList<String> filespath;
    GridView gridgallery;
    ImageView imghinh;
    ImageAdapter imageAdapter;
    int RESULT_IMAGE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.gallery, container, false);
        gridgallery = view.findViewById(R.id.gridgallery);
        imghinh = view.findViewById(R.id.imghinh);
        filespath = new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        int collumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

        while (cursor.moveToNext()) {
            //absolutePathOfImage = cursor.getString(column_index_data);
            filespath.add(cursor.getString(collumn));
            //listOfAllImages.add(absolutePathOfImage);
        }
        imageAdapter = new ImageAdapter(getActivity(), R.layout.hinh, filespath);
        gridgallery.setAdapter(imageAdapter);
        return view;
    }
}



