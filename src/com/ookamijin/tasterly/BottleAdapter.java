package com.ookamijin.tasterly;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BottleAdapter extends ArrayAdapter<Bottle> {

	Context context;
	int layoutResourceId;
	Bottle data[] = null;

	public BottleAdapter(Context context, int layoutResourceId, Bottle[] data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		BeerHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new BeerHolder();
			holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
			holder.txtName = (TextView) row.findViewById(R.id.txtTitle);

			row.setTag(holder);
		} else {
			holder = (BeerHolder) row.getTag();
		}

		Bottle beer = data[position];
		holder.txtName.setText(beer.name);
		
		//set the default icon until custom icon loads
		holder.imgIcon.setImageResource(beer.icon);
		// find out if image is drawable or bitmap
		if (beer.icon == 0) {

			BitmapLoader loader = new BitmapLoader(holder.imgIcon);
			loader.execute(beer.photoPath);
		}

		return row;
	}

	static class BeerHolder {
		ImageView imgIcon;
		TextView txtName;
	}
}
