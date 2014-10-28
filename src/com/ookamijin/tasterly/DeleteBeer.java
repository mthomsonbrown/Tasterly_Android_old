package com.ookamijin.tasterly;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DeleteBeer extends Activity implements OnClickListener {

	Button deleteButton;
	Button cancelButton;
	int pos = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.beer_delete);

		deleteButton = (Button) findViewById(R.id.deleteButton);
		deleteButton.setOnClickListener(this);
		cancelButton = (Button) findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(this);

		pos = getIntent().getIntExtra("position", -1);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == deleteButton.getId() && pos != -1) {
			Fridge mFridge = new Fridge(this);
			mFridge.open();
			mFridge.drinkBeer(pos);
			mFridge.close();

		}
		finish();

	}

}
