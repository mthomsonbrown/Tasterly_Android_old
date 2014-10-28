package com.ookamijin.tasterly;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.ookamijin.tasterly.R.drawable;

public class BeerList extends Activity implements OnItemClickListener,
		OnItemLongClickListener {

	private ListView beerList;
	private ListView addBeer;
	BottleAdapter listAdapter;
	Bottle bottleData[];
	final int editReturn = 1;
	final int deleteReturn = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.beer_list);

		addBeer = (ListView) findViewById(R.id.addBeer);
		Bottle addBottle[] = { new Bottle(drawable.beer_logo, "Add-a-Pint!", 0) };
		listAdapter = new BottleAdapter(this, R.layout.beerlist_bottle,
				addBottle);
		addBeer.setAdapter(listAdapter);
		addBeer.setOnItemClickListener(this);
		addBeer.setOnItemLongClickListener(this);

		beerList = (ListView) findViewById(R.id.beerList);
		beerList.setOnItemClickListener(this);
		beerList.setOnItemLongClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		fillList();
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int pos,
			long arg3) {
		if (adapter.getId() == addBeer.getId()) {
			Intent i = new Intent(this, EditBeer.class);
			startActivityForResult(i, editReturn);
			
		}
		if (adapter.getId() == beerList.getId()) {
			Intent edit = new Intent(this, EditBeer.class);
			edit.putExtra("position", bottleData[pos].id);
			startActivityForResult(edit, editReturn);
		}

	}
	
	public void onActivityResult(int request, int result, Intent data) {
			//may need case statement later on
			fillList();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos,
			long arg3) {
		
		if (adapter.getId() == beerList.getId()) {
			
			//call delete confirmation activity
			//TODO later should be contained in a dialog
			Intent delete = new Intent(this, DeleteBeer.class);
			delete.putExtra("position", bottleData[pos].id);
			startActivityForResult(delete, deleteReturn);
			
			
			
		}
		//consumes click
		return true;
	}

	private void fillList() {
		Fridge mFridge = new Fridge(this);

		// this all rebuilds the beer list when the activity is called
		mFridge.open();
		Cursor curse = mFridge.getList();
		if (curse != null && curse.moveToFirst()) {
			
			bottleData = new Bottle[curse.getCount()];
			for (int i = 0; i < curse.getCount(); ++i) {

				// find if picture taken
				if (curse.getString(4) != null)
					bottleData[i] = new Bottle(curse.getString(4),
							curse.getString(1), curse.getInt(0));
				else
					bottleData[i] = new Bottle(drawable.beer_logo,
							curse.getString(1), curse.getInt(0));
				curse.moveToNext();

			}
			BottleAdapter bottleList = new BottleAdapter(this,
					R.layout.beerlist_bottle, bottleData);
			beerList.setAdapter(bottleList);
			bottleList.notifyDataSetChanged();

			mFridge.close();
		} else {
			beerList.setAdapter(null);
			mFridge.close();
		}
	}
	
	
}
