package com.ookamijin.tasterly;

import android.util.Log;
import android.view.View;

public class PrimaryFlavor {

	static int F01_CNUM = 7;
	static int F02_CNUM = 3;
	static int F03_CNUM = 3;
	static int F04_CNUM = 2;
	static int F05_CNUM = 1;
	static int F06_CNUM = 4;
	static int F07_CNUM = 4;
	static int F08_CNUM = 5;
	static int F09_CNUM = 1;
	static int F10_CNUM = 1;
	static int F11_CNUM = 1;
	static int F12_CNUM = 1;
	static int F13_CNUM = 7;
	static int F14_CNUM = 1;
	static int P_CNUM[] = { 7, 3, 3, 2, 1, 4, 4, 5, 1, 1, 1, 1, 7, 1 };

	static int F01_COL = 0xffffb000;
	static int F02_COL = 0xff61ff00;
	static int F03_COL = 0xff00ff03;
	static int F04_COL = 0xff00ff4f;
	static int F05_COL = 0xff00ff9b;
	static int F06_COL = 0xff00ffff;
	static int F07_COL = 0xff0047ff;
	static int F08_COL = 0xff5900ff;
	static int F09_COL = 0xff8f00ff;
	static int F10_COL = 0xffcc00ff;
	static int F11_COL = 0xffff00fd;
	static int F12_COL = 0xffff00ed;
	static int F13_COL = 0xffff0091;
	static int F14_COL = 0xffff001f;

	int seekbar;
	int subFlavId; // drawable id for subflavor thumbnail thumbnail
	String label;
	int FLAV_ID;
	int color;
	int textColor;
	boolean passThrough = false; // set true to remove button and aquire seekbar
									// position from parent object
	String fId;

	PrimaryFlavor() {
		super();
	}

	PrimaryFlavor(int FLAV_ID) {
		super();
		this.FLAV_ID = FLAV_ID;
	}

	PrimaryFlavor(String label) {
		super();
		this.label = label;
	}

	PrimaryFlavor(int FLAV_ID, String label, int seekbar, int subFlavId) {
		super();
		this.seekbar = seekbar;
		this.subFlavId = subFlavId;
		this.label = label;
		this.FLAV_ID = FLAV_ID;
	}

	public int getFLAV_ID() {
		return FLAV_ID;
	}

	public String getId() {
		return fId;
	}

	public void setId(String id) {
		fId = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getFlavId() {
		return FLAV_ID;
	}

	public void setSeek(int i) {
		seekbar = i;

	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {

		return color;
	}

	public int getVisibility() {
		if (FLAV_ID == 5)
			return View.GONE;
		return View.VISIBLE;
	}

	public void parseColor(int i) {
		switch (i) {
		case 1:
			color = F01_COL;
			break;
		case 2:
			color = F02_COL;
			break;
		case 3:
			color = F03_COL;
			break;
		case 4:
			color = F04_COL;
			break;
		case 5:
			color = F05_COL;
			break;
		case 6:
			color = F06_COL;
			break;
		case 7:
			color = F07_COL;
			break;
		case 8:
			color = F08_COL;
			break;
		case 9:
			color = F09_COL;
			break;
		case 10:
			color = F10_COL;
			break;
		case 11:
			color = F11_COL;
			break;
		case 12:
			color = F12_COL;
			break;
		case 13:
			color = F13_COL;
			break;
		case 14:
			color = F14_COL;
			break;
		default:
			break;
		}
		
		textColor = ~color | 0xFF000000;

	}

	public static int idToInt(String fId) {
		String primaryId = fId.substring(1, 1);
		if (primaryId == "0")
			primaryId = fId.substring(2, 3);
		else
			primaryId = fId.substring(1, 3);
		int key = Integer.parseInt(primaryId);
		return key;
	}
}
