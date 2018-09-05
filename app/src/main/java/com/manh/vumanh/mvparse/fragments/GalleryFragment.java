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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class GalleryFragment extends BaseFragment{
    private Cursor cursor;
    private int column;
    ArrayList<String> filespath;
    @BindView(R.id.gridgallery)
    GridView gridgallery;
    @BindView(R.id.imghinh)
    ImageView imghinh;
    ImageAdapter adapter;
    int RESULT_IMAGE = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.gallery, container, false);

        filespath = new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        int collumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

        while (cursor.moveToNext()) {
            //absolutePathOfImage = cursor.getString(column_index_data);
            filespath.add(cursor.getString(collumn));
            //listOfAllImages.add(absolutePathOfImage);
        }
        adapter = new ImageAdapter(getActivity(), R.layout.hinh, filespath);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        int collumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

        while (cursor.moveToNext()) {
            //absolutePathOfImage = cursor.getString(column_index_data);
            Toast.makeText(getActivity(), "leng" + cursor.getString(collumn), Toast.LENGTH_LONG).show();
            //listOfAllImages.add(absolutePathOfImage);
        }


        cursor.close();
        changeFragment(new MemberFragment(), R.id.main, false);

    }

    private class ImageAdapter extends BaseAdapter{
        private Context context;
        private int layout;
        private List<String> hinhanh;

        public ImageAdapter(Context context, int layout, List<String> hinhanh) {
            this.context = context;
            this.layout = layout;
            this.hinhanh = hinhanh;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(layout, null);
            }

            String path = hinhanh.get(i);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            imghinh.setImageBitmap(bitmap);
            return null;
        }
    }
}


