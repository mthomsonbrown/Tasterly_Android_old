package com.ookamijin.tasterly;

import android.util.Log;




public class SecondaryFlavor extends PrimaryFlavor {

	int buttonColor;

	SecondaryFlavor() {
		super();
	}
	
	SecondaryFlavor(String label) {
		super();
		this.label = label;
		color = 0xff00ffff;
		buttonColor = 0xffd82800;
	}
	
	public int getButtonColor() {
		return buttonColor;
	}

	public void setButtonColor(int buttonColor) {
		this.buttonColor = buttonColor;
	}

	public void parseColor(int i) {
		int key = PrimaryFlavor.idToInt(fId);
		switch (key) {
		case 1:
			color = Uts.gradiate(F14_COL, F01_COL, F02_COL, F01_CNUM, idToInt(fId));
			
			break;
		case 2:
			color = Uts.gradiate(F01_COL, F02_COL, F03_COL, F02_CNUM, idToInt(fId));
			
			break;
		case 3:
			color = Uts.gradiate(F02_COL, F03_COL, F04_COL, F02_CNUM, idToInt(fId));
			break;
		case 4:
			color = Uts.gradiate(F03_COL, F04_COL, F05_COL, F02_CNUM, idToInt(fId));
			break;
		case 5:
			color = Uts.gradiate(F04_COL, F05_COL, F06_COL, F02_CNUM, idToInt(fId));
			break;
		case 6:
			color = Uts.gradiate(F05_COL, F06_COL, F07_COL, F02_CNUM, idToInt(fId));
			break;
		case 7:
			color = Uts.gradiate(F06_COL, F07_COL, F08_COL, F02_CNUM, idToInt(fId));
			break;
		case 8:
			color = Uts.gradiate(F07_COL, F08_COL, F09_COL, F02_CNUM, idToInt(fId));
			break;
		case 9:
			color = Uts.gradiate(F08_COL, F09_COL, F10_COL, F02_CNUM, idToInt(fId));
			break;
		case 10:
			color = Uts.gradiate(F09_COL, F10_COL, F11_COL, F02_CNUM, idToInt(fId));
			break;
		case 11:
			color = Uts.gradiate(F10_COL, F11_COL, F12_COL, F02_CNUM, idToInt(fId));
			break;
		case 12:
			color = Uts.gradiate(F11_COL, F12_COL, F13_COL, F02_CNUM, idToInt(fId));
			break;
		case 13:
			color = Uts.gradiate(F12_COL, F13_COL, F14_COL, F02_CNUM, idToInt(fId));
			break;
		case 14:
			color = Uts.gradiate(F13_COL, F14_COL, F01_COL, F02_CNUM, idToInt(fId));
			break;
		
		default:
			break;
		}
		
		textColor = ~color | 0xFF000000;
	}
	
	public static int idToInt(String fId) {
		String secondaryId = fId.substring(3, 3);
		if (secondaryId == "0")
			secondaryId = fId.substring(4, 5);
		else
			secondaryId = fId.substring(3, 5);
		int key = Integer.parseInt(secondaryId);
		Log.d("DEBUG", "idToInt returns " + key + "secondary id is " + secondaryId);
		return key;
	}
	
	
	
}
