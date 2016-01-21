package com.gongoliers.coffey.herb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Turtle {
	
	private int width;
	private int height;
	
	private Bitmap image;
	
	private int x;
	private int y; 
	private int initY;
	
	private int dy;
	
	public Turtle(Context context, GameView view, int gameWidth, int gameHeight) {
		image = SkonkWorks.getTitle(view);
		width = gameWidth;
		height = gameHeight;
		image = Bitmap.createScaledBitmap(image, width, height, false);
		x = 0;
		y = -height / 9;
		initY = y;
		dy = 1;
	}
	
	public void update() {
		if (y > initY + 5 || y < initY - 5) dy = -dy;
		y += dy;
	}
	
	public void draw(Canvas g) {
		g.drawBitmap(image, x, y, null);
	}
}
