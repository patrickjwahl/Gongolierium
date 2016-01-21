package com.gongoliers.coffey.herb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Gondola {
	
	private static int width;
	private static int height;
	private int rectWidth; 
	private int rectHeight;
	
	public enum Dir {
		LEFT, RIGHT
	}
	
	private Dir dir;

	private int gameWidth;
	private int gameX;
	
	private Bitmap[] right;
	private Bitmap[] left;
	
	private int x;
	private int y;
	private int rectXMult;
	private int rectYMult;
	
	private int speed;
	private int baseSpeed;
	
	private int keyframe;
	private int counter;
	
	public Gondola(Context context, GameView view, int gameWidth, int gameHeight) {
		this.gameWidth = gameWidth;
		gameX = (view.getWidth() - gameWidth) / 2;
		left = SkonkWorks.getLeftGondola(context);
		right = SkonkWorks.getRightGondola(context);
		width = gameWidth / 5;
		height = width * 275 / 150;
		for (int i = 0; i < left.length; i++) {
			left[i] = Bitmap.createScaledBitmap(left[i], width, height, false);
			right[i] = Bitmap.createScaledBitmap(right[i], width, height, false);
		}
		dir = Dir.LEFT;
		x = (view.getWidth() / 2) - (width / 2);
		y = view.getHeight() * 425 / 900;
		rectXMult = 45 * gameWidth / 750;
		rectYMult = 35 * gameHeight / 900;
		rectWidth = 100 * gameWidth / 750;
		rectHeight = 200 * gameHeight / 900;
		keyframe = 6;
		counter = 0;
		baseSpeed = 9 * gameWidth / 750;
		speed = baseSpeed;
	}
	
	public void left() {
		if (dir == Dir.LEFT) {
			speed += 14 * gameWidth / 750;
		} else {
			dir = Dir.LEFT;
			speed = baseSpeed;
		}
	}

	public void right() {
		if (dir == Dir.RIGHT) {
			speed += 14 * gameWidth / 750;
		} else {
			dir = Dir.RIGHT;
			speed = baseSpeed;
		}
	}
	
	public Rect getRect() {
		return new Rect(x + rectXMult, y + rectYMult, x + rectWidth, y + rectHeight);
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
	
	public void update() {
		if (counter == 1) {
			if (keyframe == 0) {
				keyframe = 6;
			} else {
				keyframe--;
			}
			counter = 0;
		} else counter++;
		
		if (dir == Dir.LEFT && x > gameX) {
			x -= speed;
		} else if (dir == Dir.RIGHT && x < gameWidth - width) {
			x += speed;
		}
	}
	
	public void draw(Canvas g) {
		if (dir == Dir.RIGHT) g.drawBitmap(right[keyframe], x, y, null);
		else g.drawBitmap(left[keyframe], x, y, null);
	}
	
}
