package com.gongoliers.coffey.herb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Home {

	private int width; 
	private int height;
	
	private Bitmap[] image;
	
	private int x;
	private int y; 
	
	private int keyframe;
	private int counter;
	
	public Home(Context context, GameView view) {
		image = SkonkWorks.getHome(view);
		if ((double)view.getWidth() / (double)view.getHeight() > 750d / 900d) {
			height = view.getHeight();
			width = (height * 750) / 900;
		} else {
			width = view.getWidth();
			height = (width * 900) / 750;
		}
		for (int i = 0; i < image.length; i++) {
			image[i] = Bitmap.createScaledBitmap(image[i], width, height, false);
		}
		x = 0;
		y = (view.getHeight() - height) - 175 * view.getHeight() / 900;
		keyframe = 1;
		counter = 0;
	}
	
	public void update() {
		if (counter == 15) {
			counter = 0;
			if (keyframe == 0) keyframe = 1;
			else keyframe = 0;
		} else {
			counter++;
		}
	}
	
	public void draw(Canvas g) {
		g.drawBitmap(image[keyframe], x, y, null);
	}
	
}
