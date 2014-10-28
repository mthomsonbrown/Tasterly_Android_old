package com.ookamijin.tasterly;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

public class EditBeer extends Activity implements OnClickListener {

	// constants
	final int cameraReturn = 1; // id of camera intent
	final int flavReturn = 2;
	final String JPEG_FILE_SUFFIX = ".jpg";
	final String JPEG_FILE_PREFIX = "Tasterly_";

	// views
	EditText beerName;
	RatingBar rating;
	Button finish;
	Button flavBtn;
	ImageButton icon;
	FlavorWheel mWheel;

	// globals
	Fridge mFridge = new Fridge(this);
	Bitmap beerPic;
	String[] filenames;
	String photoPath = null;
	int hasPicture = 0;
	boolean fillLock = false;
	long beerId = 0;
	boolean newBeer = true;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.beer_edit);

		rating = (RatingBar) findViewById(R.id.ratingBar);
		finish = (Button) findViewById(R.id.finish);
		finish.setOnClickListener(this);

		flavBtn = (Button) findViewById(R.id.flavBtn);
		flavBtn.setOnClickListener(this);

		beerName = (EditText) findViewById(R.id.txtTitle);
		icon = (ImageButton) findViewById(R.id.imgIcon);
		icon.setOnClickListener(this);
		hasPicture = 0;

		mWheel = (FlavorWheel) findViewById(R.id.flavorWheel);

		// see if beer exists, fill fields, shut off fillLock\
		// this is for old beer
		beerId = (long) getIntent().getIntExtra("position", -1);
		if (beerId != -1) {
			fillFields();
			fillLock = true;
		}

		// db initialization
		if (!fillLock) {

			// checked, is correct.
			String id[] = Uts.allFID();

			int array[] = new int[id.length];
			for (int i = 0; i < id.length; ++i)
				array[i] = 0;

			String numbers = Uts.convertArrayToString(array);
			mFridge.open();
			// returns KEY_ROWID from fridge
			beerId = mFridge.fillBeer("a", "Beer", 0, null, numbers, id);
			mFridge.close();
			fillLock = true;
		}
		buildGraph();
	}

	private void fillFields() {
		mFridge.open();
		Cursor curse = mFridge.getBeer(beerId);
		beerName.setText(curse.getString(1));
		rating.setRating(curse.getInt(3));

		mFridge.close();
	}

	@Override
	public void onSaveInstanceState(Bundle icicle) {
		icicle.putString("photoPath", photoPath);
		super.onSaveInstanceState(icicle);
	}

	@Override
	public void onRestoreInstanceState(Bundle icicle) {
		super.onRestoreInstanceState(icicle);
		photoPath = icicle.getString("photoPath");

	}

	private int fillBeer() {
		if (beerId > 0) {
			mFridge.open();
			mFridge.refillBeer(beerName.getText().toString(), "Beer",
					rating.getRating(), photoPath, beerId);
			mFridge.close();
			return 0;
		} else {
			Toast.makeText(this, "Error BeerId Null", Toast.LENGTH_SHORT)
					.show();
			return -1;
		}

	}

	@Override
	public void onClick(View v) {
		if (v == finish) {
			if (beerName.getText().toString().matches("")) {
				Toast.makeText(this, "Please add a name!", Toast.LENGTH_SHORT)
						.show();
			} else {
				fillBeer();
				finish();
			}
		}

		if (v == icon && hasPicture == 0) {
			try {
				takePicture();

			} catch (IOException e) {
				Toast.makeText(this, "ioe exception", Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
			hasPicture = 1;
		}

		if (v == flavBtn) {

			fillBeer();
			Intent i = new Intent(this, PrimaryList.class);
			i.putExtra("id", beerId);
			startActivityForResult(i, flavReturn);

		}
	}

	private File createImageFile() throws IOException {
		// build name. located in bin folder on sd, i believe
		String imageFileName = JPEG_FILE_PREFIX + System.currentTimeMillis()
				+ "_" + JPEG_FILE_SUFFIX;
		File image = new File(getExternalFilesDir(null), imageFileName);

		photoPath = image.getAbsolutePath();
		return image;
	}

	private void takePicture() throws IOException {
		// check if camera available
		if (!getPackageManager()
				.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
					.show();
		} else {
			// call camera application
			Intent takePictureIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			File f = createImageFile();
			takePictureIntent
					.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
			startActivityForResult(takePictureIntent, cameraReturn);

		}
	}

	public void onActivityResult(int request, int result, Intent data) {
		if (request == cameraReturn && result == RESULT_OK) {
			Bitmap beerPic = BitmapFactory.decodeFile(photoPath);
			beerPic = Bitmap.createScaledBitmap(beerPic, 128, 128, false);
			icon.setImageBitmap(beerPic);
		}
		if (request == flavReturn && result == RESULT_OK) {

			buildGraph();
		}
	}

	private void buildGraph() {

		String pFID[] = Uts.priFID();
		String sFID[] = Uts.secFID();
		Cursor curse;

		mFridge.open();
		curse = mFridge.getSeekValues(beerId, pFID);

		// create array of primary seekbar values
		int p[] = new int[pFID.length];
		for (int i = 0; i < pFID.length; ++i)
			p[i] = curse.getInt(i);

		curse = mFridge.getSeekValues(beerId, sFID);
		mFridge.close();

		// create array of secondary seekbar values
		int s[] = new int[sFID.length];
		for (int i = 0; i < sFID.length; ++i)
			s[i] = curse.getInt(i);

		mWheel.buildGraph(p, s, PrimaryFlavor.P_CNUM);

	}

}
