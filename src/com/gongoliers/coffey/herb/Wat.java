package com.gongoliers.coffey.herb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Wat {
	
	private Bitmap origImage;
	
	private Bitmap image;
	
	private GameView view;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Wat(Context context, GameView view, int type) {
		this.view = view;
		if (type == 1) {
			origImage = SkonkWorks.getCoffey(view);
		} else if (type == 2) {
			origImage = SkonkWorks.getGongoleski(view);
		} else if (type == 3) {
			origImage = SkonkWorks.getMazzone(view);
		} else if (type == 4) {
			origImage = SkonkWorks.getBrad(view);
		} else {
			origImage = SkonkWorks.getDavid(view);
		}
		height = 1;
		width = 1;
		image = Bitmap.createScaledBitmap(origImage, width, height, false);
		x = (view.getWidth() - width) / 2;
		y = view.getHeight() - 300 * view.getHeight() / 900;
	}
	
	public int getY() {
		return y;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void update() {
		if (image.getWidth() < 301 * (double)view.getWidth() / 750d) {
			width += 20;
			height += 20;
			image = Bitmap.createScaledBitmap(origImage, width, height, false);
			x -= 10;
			y -= 10;
		}
	}
	
	public void draw(Canvas g) {
		g.drawBitmap(image, x, y, null);
	}
	
}
