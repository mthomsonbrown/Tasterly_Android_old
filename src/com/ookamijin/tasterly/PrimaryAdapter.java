package com.ookamijin.tasterly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class PrimaryAdapter extends ArrayAdapter<PrimaryFlavor> implements
		OnClickListener, OnSeekBarChangeListener {

	Context context;
	int layoutResourceId;
	PrimaryFlavor data[] = null;
	int position;
	long beerId;

	static class PriHolder {
		TextView fLabel;
		SeekBar fBar;
		Button fBtn;
		LinearLayout layout;
		int flavId;
	}

	public PrimaryAdapter(Context context, int layoutResourceId, PrimaryFlavor[] data,
			long beerId) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		this.beerId = beerId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		PriHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new PriHolder();

			holder.fLabel = (TextView) row.findViewById(R.id.majLabel);
			holder.fBar = (SeekBar) row.findViewById(R.id.majBar);
			holder.fBtn = (Button) row.findViewById(R.id.priChildBtn);
			holder.layout = (LinearLayout) row.findViewById(R.id.maj_layout);

			row.setTag(holder);
		} else {
			holder = (PriHolder) row.getTag();
		}

		// set view attributes
		PrimaryFlavor primaryFlavor = data[position];
		holder.fLabel.setText(primaryFlavor.label);
		if (primaryFlavor.color != 0) 
			holder.layout.setBackgroundColor(primaryFlavor.color);
		if (primaryFlavor.textColor != 0) 
			holder.fLabel.setTextColor(primaryFlavor.textColor);
		

		holder.fBar.setProgress(primaryFlavor.seekbar);
		holder.fBar.setTag(primaryFlavor.fId);
		holder.fBar.setOnSeekBarChangeListener(this);

		holder.fBtn.setOnClickListener(this);
		holder.fBtn.setTag(primaryFlavor.fId);
		holder.fBtn.setVisibility(primaryFlavor.getVisibility());

		return row;
	}

	@Override
	public void onClick(View v) {
		String fId = (String) v.getTag();
		Intent i = new Intent(this.context, SecondaryList.class);
		i.putExtra("beerId", beerId);
		i.putExtra("fId", fId);
		this.context.startActivity(i);

	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar v) {
		// this updates the db with newly entered seekbar value
		String flavId = (String) v.getTag();
		int value = v.getProgress();

		Fridge mFridge = new Fridge(this.getContext());
		mFridge.open();
		mFridge.updateFlavor(beerId, flavId, value);
		mFridge.close();

	}

}
