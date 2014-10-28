package com.ookamijin.tasterly;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.ookamijin.tasterly.PrimaryAdapter.PriHolder;

public class SecondaryAdapter extends ArrayAdapter<SecondaryFlavor> implements
		OnClickListener, OnSeekBarChangeListener {

	private int layoutResourceId;
	private Context context;
	private SecondaryFlavor[] data;
	private long beerId;

	class SecHolder extends PriHolder {
		ImageButton secParentButton;
		RelativeLayout layout;
	}

	public SecondaryAdapter(Context context, int layoutResourceId,
			SecondaryFlavor[] data, long beerId) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		this.beerId = beerId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		SecHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new SecHolder();

			holder.fLabel = (TextView) row.findViewById(R.id.secLabel);
			holder.fBar = (SeekBar) row.findViewById(R.id.secBar);
			holder.fBtn = (Button) row.findViewById(R.id.secChildButton);
			holder.layout = (RelativeLayout) row.findViewById(R.id.sec_layout);
			holder.secParentButton = (ImageButton) row
					.findViewById(R.id.secParentButton);

			row.setTag(holder);
		} else {
			holder = (SecHolder) row.getTag();
		}

		// set view attributes
		SecondaryFlavor flavor = data[position];
		holder.fLabel.setText(flavor.label);

		if (flavor.color != 0)
			holder.layout.setBackgroundColor(flavor.color);
		if (flavor.textColor != 0) 
			holder.fLabel.setTextColor(flavor.textColor);

		//if (flavor.buttonColor != 0)
			holder.secParentButton.setBackgroundColor(Color.BLACK); //TODO not working!!!!1!
		//else
		//	holder.secParentButton.getBackground().setColorFilter(
		//			Color.parseColor("#ffffffff"), PorterDuff.Mode.DARKEN);

		holder.fBar.setProgress(flavor.seekbar);
		holder.fBar.setTag(flavor.fId);
		holder.fBar.setOnSeekBarChangeListener(this);

		holder.fBtn.setOnClickListener(this);
		holder.fBtn.setTag(flavor.fId);
		holder.fBtn.setVisibility(flavor.getVisibility());

		return row;
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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
