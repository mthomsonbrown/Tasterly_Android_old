package com.ookamijin.tasterly;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class SecondaryList extends Activity {

	SecondaryFlavor flavorList[];
	private ListView mList;
	SecondaryAdapter mAdapter;
	long beerId;
	String parentFlavor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sec_list);
		Intent intent = getIntent();
		beerId = intent.getLongExtra("beerId", -1);
		parentFlavor = intent.getStringExtra("fId");

		Fridge mFridge = new Fridge(this);
		String id[] = Uts.secFID();
		flavorList = new SecondaryFlavor[id.length];
		for (int i = 0; i < id.length; ++i) {
			
			flavorList[i] = new SecondaryFlavor(Uts.translateToString(this, id[i]));
	
		}

		mFridge.open();
		Cursor curse;
		curse = mFridge.getSeekValues(beerId, id);
		mFridge.close();

		if (curse.getCount() > 0) {
			for (int i = 0; i < id.length; ++i) {
				flavorList[i].setSeek(curse.getInt(i));
				flavorList[i].setId(id[i]);
				flavorList[i].parseColor(i + 1);
			}
		}

		mList = (ListView) findViewById(R.id.secList);
		mAdapter = new SecondaryAdapter(this, R.layout.sec_item, flavorList,
				beerId);
		mList.setAdapter(mAdapter);
	}

}
