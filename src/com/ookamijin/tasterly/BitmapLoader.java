package com.ookamijin.tasterly;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapLoader extends AsyncTask<String, Void, Bitmap> {
	private WeakReference<ImageView> imageViewReference;
	private int data = 0; 
	private String photoPath = null;

	public BitmapLoader(ImageView imageView) {
		//weak reference allows garbage collection of non-ui threads
		imageViewReference = new WeakReference<ImageView>(imageView);
	}

	//TODO i think this is the slow part of the icon loading
	@Override
    protected Bitmap doInBackground(String... params) {
        photoPath = params[0];
		Bitmap beerPic = BitmapFactory.decodeFile(photoPath);
		beerPic = Bitmap.createScaledBitmap(beerPic, 80, 80, false);
		return beerPic;
    }
	

	//set bitmap in image view
	@Override
	protected void onPostExecute(Bitmap bitmap) {
		if (imageViewReference != null && bitmap != null) {
			final ImageView imageView = imageViewReference.get();
			if (imageView != null) {
				imageView.setImageBitmap(bitmap);
			}
		}
	}

}
