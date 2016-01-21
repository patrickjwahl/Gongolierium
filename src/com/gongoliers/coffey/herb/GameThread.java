package com.gongoliers.coffey.herb;

import android.graphics.Canvas;

public class GameThread extends Thread {

	private GameView view;
	private boolean running;
	static final long FPS = 30;
	
	public GameThread(GameView view) {
		this.view = view;
		running = true;
	}
	
	public void kill() {
		running = false;
	}
	
	public void run() {
		while (running) {
			long startTime = System.nanoTime();
			Canvas c = null; 
			try {
				c = view.getHolder().lockCanvas();
				if (c == null) break;
				synchronized(view.getHolder()) {
					view.draw(c);
				}
			} finally {
				if (c != null) {
					view.getHolder().unlockCanvasAndPost(c);
				}
				try {
					sleep(1000 / FPS - ((System.nanoTime() - startTime) / 1000000));
				} catch (Exception e) {
					
				}
			}
		}
		view.kill();
	}
	
}
