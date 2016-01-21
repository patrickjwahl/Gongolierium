package com.gongoliers.coffey.herb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Obstacle {
	
	private static Bitmap[] image;
	
	private static int width;
	private static int height;
	
	private int rectWidth;
	private int rectHeight;
	
	private int x;
	private int y; 
	private int rectYMult;
	
	private int keyframe;
	private int counter;
	
	private int speed;
	
	private boolean passed;
	
	public Obstacle(Context context, GameView view, int gameWidth, int gameHeight, int x) {
		width = 250 * gameWidth / 750;
		height = width * 75 / 250;
		if (image == null) {
			image = SkonkWorks.getObstacle(context);
			for (int i = 0; i < image.length; i++) {
				image[i] = Bitmap.createScaledBitmap(image[i], width, height, false);
			}
		}
		this.x = x;
		rectWidth = 245 * width / 250;
		rectHeight = 10 * height / 75;
		y = -height;
		rectYMult = (60 * height) / 75;
		keyframe = 0;
		counter = 0;
		speed = 11 * gameHeight / 900;
		passed = false;
	}
	
	public void update(boolean shouldMove) {
		if (counter == 4) {
			counter = 0;
			if (keyframe == 0) keyframe = 1;
			else keyframe = 0;
		} else {
			counter++;
		}
		
		if (shouldMove) y += speed;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Rect getRect() {
		return new Rect(x, y + rectYMult, x + rectWidth, y + rectYMult + rectHeight);
	}
	
	public boolean isPassed() {
		return passed;
	}
	
	public void pass() {
		passed = true;
	}
	
	public void draw(Canvas g) {
		g.drawBitmap(image[keyframe], x, y, null);
	}
}
