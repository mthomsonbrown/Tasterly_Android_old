package com.ookamijin.tasterly;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FlavorWheel extends View {

	private Paint paint;
	int originX;
	int originY;

	int primary[];
	int secondary[];
	// pri_num is an array with length of primary array, with numbers
	// representing the
	// number of secondary flavors associated to each primary number
	int pri_num[];

	// function flags

	boolean buildGraph = false;

	public FlavorWheel(Context context) {
		super(context);

		init();
	}

	public FlavorWheel(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	// called by the constructors
	private void init() {

		paint = new Paint();
		paint.setColor(Color.GREEN);

	}

	protected void onDraw(Canvas canvas) {
		originX = canvas.getWidth() / 2;
		originY = canvas.getHeight() / 2;

		canvas.drawPoint(originX, originY, paint);

		if (buildGraph) {
			doBuildGraph(canvas);
			buildGraph = false;
		}
	}

	public void buildGraph(int primary[], int secondary[], int pri_num[]) {
		buildGraph = true;
		this.primary = primary;
		this.secondary = secondary;
		this.pri_num = pri_num;
		invalidate();
	}

	private void doBuildGraph(Canvas canvas) {
		int max = canvas.getWidth();
		if (max > canvas.getHeight())
			max = canvas.getHeight();
		Log.d("DEBUG", " canvas height = " + canvas.getHeight());
		Log.d("DEBUG", " canvas width = " + canvas.getWidth());
		Log.d("DEBUG", " max = " + max);
		float radius = max / 2;
		
		float scaler = radius / 200; // TODO change for tertiary
		
		ArrayList<Float> pointsList = new ArrayList<Float>();
		float points[];
		float lines[];

		int angleCount = 0;
		
		for (int i = 0; i < primary.length; ++i) {
			for (int j = 0; j < pri_num[i]; ++j) {

				double angle = ((2 * Math.PI) / secondary.length)
						* (angleCount);
				
				float mag = (primary[i] + secondary[angleCount]) * scaler;

				
				float x = originX + mag * (float) Math.cos(angle);
				float y = originY + mag * (float) Math.sin(angle);
				pointsList.add(x);
				pointsList.add(y);
				++angleCount;
			}
		}

		// convert float list to array
		points = new float[pointsList.size()];

		for (int i = 0; i < pointsList.size(); i++) {
			Float f = pointsList.get(i);
			points[i] = (f != null ? f : 0);
		}

		lines = pointsToLines(points);

		canvas.drawLines(lines, paint);
		canvas.drawPoints(points, paint);

	}

	private float[] pointsToLines(float[] points) {
		float lines[] = new float[points.length * 2];
		int lineCount = 0;
		int pointCount = 0;

		
		for (int i = 0; i < lines.length - 2; ++i) {
			lines[i] = points[pointCount];
			++lineCount;
			++pointCount;

			if (lineCount == 4) {
				lineCount = 0;
				pointCount = pointCount - 2;
			}
		}
		if (lines.length > 4) {
			lines[lines.length - 2] = lines[0];
			lines[lines.length - 1] = lines[1];
		} else {
			lines[lines.length - 2] = originX;
			lines[lines.length - 1] = originY;
		}

		return lines;
	}

}
