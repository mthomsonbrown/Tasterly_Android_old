package com.ookamijin.tasterly;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class Uts {
	// convert filenames to store in fridge
	public static String convertArrayToString(String[] array) {
		String str = "";
		for (int i = 0; i < array.length; i++) {
			str = str + array[i];
			if (i < array.length - 1) {
				str = str + ",";
			}
		}
		return str;
	}

	public static String[] appendStringToArray(String str, String[] array) {
		String[] newArray;
		String list = convertArrayToString(array);
		list = list + ", " + str;
		newArray = convertStringToStringArray(list);
		return newArray;
	}

	public static String[] convertStringToStringArray(String str) {
		String[] arr = str.split(",");
		return arr;
	}

	// convert int arrays to store in fridge
	public static String convertArrayToString(int[] array) {
		String str = "";
		for (int i = 0; i < array.length; i++) {
			str = str + array[i];
			if (i < array.length - 1) {
				str = str + ",";
			}
		}
		return str;
	}

	public static int[] convertStringToIntArray(String str) {
		String num[] = convertStringToStringArray(str);
		int[] arr = new int[num.length];

		for (int i = 0; i < num.length; i++) {
			try {
				arr[i] = Integer.parseInt(num[i]);
			} catch (NumberFormatException nfe) {
			}
			;
		}
		return arr;
	}

	public static String translateToString(Context context, String name) {
		int id = context.getResources().getIdentifier(name, "string",
				context.getPackageName());
		if (id <= 0)
			return "Null";

		return context.getString(id);
	}

	// returns a string of all (currently implemented) fIds
	public static String[] allFID() {

		
		String priList[] = priFID();
		String secList[] = secFID();

		String strList[] = new String[priList.length + secList.length];
		System.arraycopy(priList, 0, strList, 0, priList.length);
		System.arraycopy(secList, 0, strList, priList.length, secList.length);
		return strList;
	}
	
	public static String[] priFID() {
		ArrayList<String> list = new ArrayList<String>();
		String str;

		// fill all primary fId
		for (int i = 0; i < 14; ++i) {
			str = "f";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "0000";
			list.add(str);
		}

		String priList[] = list.toArray(new String[list.size()]);
		return priList;
	}

	// returns a string of all Secondary fIds
	public static String[] secFID() {
		ArrayList<String> list = new ArrayList<String>();
		String str;

		// fill f01 secondary fId
		for (int i = 0; i < 7; ++i) {
			str = "f01";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f02 secondary fId
		for (int i = 0; i < 3; ++i) {
			str = "f02";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f03 secondary fId
		for (int i = 0; i < 3; ++i) {
			str = "f03";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f04 secondary fId
		for (int i = 0; i < 2; ++i) {
			str = "f04";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f05 secondary fId
		for (int i = 0; i < 1; ++i) {
			str = "f05";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f06 secondary fId
		for (int i = 0; i < 4; ++i) {
			str = "f06";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f07 secondary fId
		for (int i = 0; i < 4; ++i) {
			str = "f07";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f08 secondary fId
		for (int i = 0; i < 5; ++i) {
			str = "f08";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f09 secondary fId
		for (int i = 0; i < 1; ++i) {
			str = "f09";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f10 secondary fId
		for (int i = 0; i < 1; ++i) {
			str = "f10";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f11 secondary fId
		for (int i = 0; i < 1; ++i) {
			str = "f11";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f12 secondary fId
		for (int i = 0; i < 1; ++i) {
			str = "f12";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f13 secondary fId
		for (int i = 0; i < 7; ++i) {
			str = "f13";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		// fill f14 secondary fId
		for (int i = 0; i < 1; ++i) {
			str = "f14";
			int d = i + 1;
			if (d < 10)
				str = str + 0;
			str = str + d + "00";
			list.add(str);
		}

		String strList[] = list.toArray(new String[list.size()]);
		return strList;

	}

	public static int gradiate(int firstColor, int secondColor, int thirdColor,
			int steps, int position) {
		
		final int transparency = 0x88000000;
		
		if (steps == 1) 
			return secondColor;
		
		int R[] = new int[4];
		R[0] = (firstColor & 0x00FF0000) >> 16;
		R[1] = (secondColor & 0x00FF0000) >> 16;
		R[2] = (thirdColor & 0x00FF0000) >> 16;

		int G[] = new int[4];
		G[0] = (firstColor & 0x0000FF00) >> 8;
		G[1] = (secondColor & 0x0000FF00) >> 8;
		G[2] = (thirdColor & 0x0000FF00) >> 8;

		int B[] = new int[4];
		B[0] = (firstColor & 0x000000FF);
		B[1] = (secondColor & 0x000000FF);
		B[2] = (thirdColor & 0x000000FF);

		// first half of gradient
		if (position < steps / 2) {

			int difference = R[1] - R[0];
			R[3] = R[1] - difference + (difference / steps) * position;

			difference = G[1] - G[0];
			G[3] = G[1] - difference + (difference / steps) * position;

			difference = B[1] - B[0];
			B[3] = B[1] - difference + (difference / steps) * position;

			int color = transparency | (R[3] << 16) | (G[3] << 8) | (B[3]);

			Log.d("DEBUG", " color is this ->" + color);

			return color;

		} else if (position == steps / 2) {
			int color = secondColor & (transparency | 0x00FFFFFF);
			return color;
		}
			

		// second half of gradient
		else {

			int difference = R[2] - R[1];
			R[3] = R[2] - difference + (difference / steps) * position;

			difference = G[2] - G[1];
			G[3] = G[2] - difference + (difference / steps) * position;

			difference = B[2] - B[1];
			B[3] = B[2] - difference + (difference / steps) * position;

			int color = transparency | (R[3] << 16) | (G[3] << 8) | (B[3]);

			Log.d("DEBUG", " color is this ->" + color);

			return color;
		}

	}

	

}
