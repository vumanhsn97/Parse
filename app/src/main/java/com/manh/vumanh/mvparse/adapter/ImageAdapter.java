package com.manh.vumanh.mvparse.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.manh.vumanh.mvparse.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends ArrayAdapter{

    Activity context;
    int resource;
    @NonNull List objects;
    @BindView(R.id.imghinh)
    ImageView imghinh;
    public ImageAdapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layout = this.context.getLayoutInflater();
        View view = layout.inflate(this.resource, null);
        ButterKnife.bind(this, view);
        final String path = (String) this.objects.get(position);

        Bitmap bitmap = BitmapFactory.decodeFile(path);
        imghinh.setImageBitmap(bitmap);
        imghinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(path);
                final ParseFile parseFile = new ParseFile(file, file.getName());
                parseFile.saveInBackground();
                ParseUser user = ParseUser.getCurrentUser();
                ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
                parseQuery.getInBackground(user.getObjectId(), new GetCallback<ParseUser>() {
                    @Override
                    public void done(ParseUser object, ParseException e) {
                        object.put("image", parseFile);
                        object.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e == null){
                                    Log.i("save", "ok");
                                } else {
                                    Log.i("save", "no");
                                }
                            }
                        });
                    }
                });

            }
        });
        return view;
    }
}
