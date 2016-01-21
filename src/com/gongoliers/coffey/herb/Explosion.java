package com.gongoliers.coffey.herb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Explosion {
	
	private Bitmap[] image;
	
	private int x;
	private int y; 
	
	private int width;
	private int height;
	
	private int keyframe;
	private int counter;
	
	private boolean finished;
	
	public Explosion(Context context, GameView view, int gameHeight) {
		image = SkonkWorks.getExplosion(context);
		height = 750 * gameHeight / 900;
		width = height;
		for (int i = 0; i < image.length; i++) {
			image[i] = Bitmap.createScaledBitmap(image[i], width, height, false);
		}
		x = 0;
		y = 25 * gameHeight / 900;
		keyframe = 0;
		counter = 0;
		finished = false;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void update() {
		if (counter == 2) {
			if (keyframe < 9) keyframe++;
			counter = 0;
		} else counter++;
		if (keyframe == 9) finished = true;
	}
	
	public void draw(Canvas g) {
		g.drawBitmap(image[keyframe], x, y, null);
	}
}
