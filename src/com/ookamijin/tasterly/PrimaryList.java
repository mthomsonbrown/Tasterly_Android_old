package com.ookamijin.tasterly;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class PrimaryList extends Activity {

	//TODO There has GOT to be a better way to do this!
	PrimaryFlavor flavorList[] = new PrimaryFlavor[14];
	private ListView mList;
	PrimaryAdapter mAdapter;
	
	long beerId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pri_list);
		Intent intent = getIntent();
		beerId = intent.getLongExtra("id", -1);
		

		// get beerId
		Fridge mFridge = new Fridge(this);
		String id[] = new String[14];
		
		// create id array
		for (int i = 0; i < 14; ++i) {
			id[i] = "f";
			int d = i + 1;
			if (d < 10)
				id[i] = id[i] + 0;
			id[i] = id[i] + d + "0000";
			flavorList[i] = new PrimaryFlavor(Uts.translateToString(this, id[i]));
			setResult(RESULT_OK);
		}

		// fill flavorList seekbar values
		mFridge.open();
		mFridge.getBeer(beerId);
		Cursor curse;
		curse = mFridge.getSeekValues(beerId, id);
		mFridge.close();

		for (int i = 0; i < 14; ++i) {
			flavorList[i].setSeek(curse.getInt(i));
			flavorList[i].setId(id[i]);
			flavorList[i].parseColor(i+1);
		}

		for (int i = 0; i < 14; ++i) {
			Log.d("DEBUG", "color is " + flavorList[i].color);
		}
		
		mList = (ListView) findViewById(R.id.flavMajList);
		mAdapter = new PrimaryAdapter(this, R.layout.pri_item, flavorList,
				beerId);
		mList.setAdapter(mAdapter);
	}

	public void finish(View v) {
		finish();
	}
}
