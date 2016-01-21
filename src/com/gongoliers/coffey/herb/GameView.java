package com.gongoliers.coffey.herb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	
	public static final String[] PHRASES = {"Now with 15% less fat!", "Now in Technicolor!", "As seen on TV!", "Caution: Graphic content", "Not Y2K-compliant", 
		"Filmed before a live audience", "Now a major motion picture!", "Based on a true story!", "Closed caption where available",
		"Made in China", "Still not as good as the book", "Soon to be a major religion!", "FDA Approved*", "Sentient version coming soon!",
		"Fun at parties!", "Swedish version now available!", "Probably carrying viruses", "Because you're worth it", "Almost famous", 
		"Official video game of the NFL", "Not a replacement for your daily vitamin supplement", "Not labeled for retail sale", 
		"Programmed entirely in LabView", "Free shipping for Gold members", "Don't read this, just start playing!", "Like your game is any better",
		"Almost passed the Turing test!", "The stuff that dreams are made of", "Solid and hand-crafted!", "A highly overrated experience",
		"If you play it, he will come", "Stop all the downloadin'!", "A masterpiece of Shakespearean proportions!", "The best you can is good enough",
		"Characterizations by Microsoft Sam", "Magnus Carlsen, play this, you scrub", "Like Skyrim but with gondolas!", "In IMAX 3-D", "Nintendo 64 version coming next summer",
		"Try it. Just once ;)", "A home-spun video game", "It'll be famous in the future, trust me!", "Copyright 3014", "Live from Times Square",
		"No time wasted with story, production value, and feelings!", "A high-seas battle of wit and skill!", "I jumped in the river what did I see?", 
		"No gondolas were harmed in the making of this game", "All gondola mechanics are accurate", "Based on true events!", "Even Watson can't beat this!",
		"You want me to rip it open?", "Bears no likeness to real life rivers", "Best played underwater", "Scratch 'n' Sniff only available in Pro version",
		"Only play while the kids are in bed", "More fun than real gondolas!", "Unlock more taglines for only 99 cents!", "Prevents government shutdowns!*",
		"Placating the masses since 2001", "Prepare for glory!", "More real than Vegas!", "Delightfully tacky, yet unrefined", "An empowered and informed member of society", 
		"Not yet a Schedule 1 substance!", "Tune in next week for the thrilling conclusion!", "Not liable for carpal tunnel", "Make millions from home!", "Do not play with concussions", 
		"For best results, uninstall game", "Life is like a gondola... well, not really",
		"Made possible thanks to an anonymous donor", "Dozens have played it! Literally dozens!", "Offering the best players in the league!", "Like a low-budget horror movie", "404 File Not Found",
		"Consultation provided by actual gondoliers", "Quit now while you still can!", "Accurately portrays real physics!",
		"Action figures sold separately", "Celebrity-endorsed!", "An undiscovered element", "Teeming with excitement!", "Exercise caution while playing", "Wear protective equipment while playing",
		"-<]]]]]]]]]]>* See? It's a gondola!", "#7 in gondola-based entertainment!", "See what prison labor can do!", "Based on the Ayn Rand novel", "Loading, please hold on",
		"From the makers of \"Gongolierium!\"", "Coming soon to own on video and DVD", "Ranked some number in ambiguity!", "From the folks that brought you \"Homie Rollerz\"",
		"The pinnacle of human achievement", "World high score: 175,368", "Please turn off all electronic devices while playing", "Some assembly required"};

	
	private GameThread gt; 
	private SurfaceHolder holder;
	
	private int gameWidth;
	private int gameHeight;
	
	private enum Mode {
		HOME,
		PLAYING
	}
	
	private Mode mode;
	
	private boolean trans;
	
	private Trial trial;
	
	private Turtle title;
	private Home home;
	
	private String phrase;
	private Paint phraseF; 
	
	private int strX;
	private int strY;
	private int initY;
	private int strDy; 
	
	private int alpha;
	private int dTrans;
	
	private Random random;
	
	private int highScore;
	
	public GameView(Context context) {
		
		super(context);
		holder = getHolder();
		holder.addCallback(new Callback() {

			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void surfaceCreated(SurfaceHolder arg0) {
				if ((double)getWidth() / (double)getHeight() >= 750d / 900d) {
					gameHeight = getHeight();
					gameWidth = (int)(gameHeight * (750d / 900d));
				} else {
					gameWidth = getWidth();
					gameHeight = (gameWidth * 900) / 750;
				}
				
				mode = Mode.HOME;
				random = new Random();
				phrase = getSubtitle();
				phraseF = new Paint();
				phraseF.setTextSize(24 * gameWidth / 750);
				strX = -1;
				strY = 200 * gameHeight / 900;
				initY = strY;
				strDy = 1;
				trans = false;
				alpha = 0;
				dTrans = 50;
				highScore = getHighScore();
				initHomeItems(); 
				start();
				
				phraseF = new Paint();
				phraseF.setTypeface(Typeface.create("Droid Sans Pro Bold", Typeface.BOLD));
				phraseF.setTextSize(24 * gameWidth / 750);
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
				
			}
			
		});
	}
	
	public void initHomeItems() {
		title = new Turtle(getContext(), this, gameWidth, gameHeight);
		home = new Home(getContext(), this);
	}
	
	public int getHighScore() {
		File f = new File(getContext().getFilesDir(), "3jf31.glm");
		try {
			if (f.createNewFile()) {
				PrintWriter pw = new PrintWriter(new FileWriter(f));
				pw.println(0);
				pw.flush();
				pw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		int score = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			score = Integer.parseInt(br.readLine(), 5);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return score;
	}
	
	public void stop() {
		gt.kill();
	}
	
	public void kill() {
		mode = Mode.HOME;
		trial = null;
	}
	
	public void showHome() {
		phrase = getSubtitle();
		trans = true;
		highScore = getHighScore();
	}
	
	public String getSubtitle() {
		strX = -1;
		return PHRASES[random.nextInt(PHRASES.length)];
	}
	
	public void strYUpdate() {
		if (strY > initY + 5 || strY < initY - 5) strDy = -strDy;
		strY += strDy;
	}
	
	public boolean onTouchEvent(MotionEvent evt) {
		if (evt.getActionMasked() == MotionEvent.ACTION_DOWN) {
			if (mode == Mode.PLAYING) {
				if (trial == null) return false;
				if ((int)evt.getX() > gameWidth / 2) {
					trial.right();
				} else {
					trial.left();
				}
			}
		} else if (evt.getActionMasked() == MotionEvent.ACTION_UP) {
			if (mode == Mode.HOME) {
				trans = true;
			} else if (mode == Mode.PLAYING) {
				if (trial == null) return false;
				if (trial.getState() == Trial.State.READY) {
					showHome();
				}
			}
		}
		return true;
	}
	
	public void start() {
		gt = new GameThread(this);
		gt.start();
	}
	
	public void draw(Canvas g) {
		g.drawARGB(255, 65, 196, 185);
		if (mode == Mode.PLAYING) {
			trial.draw(g);
			if (trans) {
				if (alpha < 250 && dTrans > 0) {
					alpha += dTrans;
				} else if (alpha == 250 && dTrans > 0) {
					mode = Mode.HOME;
					trial = null;
					dTrans = -dTrans;
				} else if (alpha > 0) {
					alpha += dTrans;
				} else {
					dTrans = -dTrans;
					trans = false;
				}
				g.drawARGB(alpha, 0, 0, 0);
			}
		} else if (mode == Mode.HOME) {
			title.update();
			title.draw(g);
			home.update();
			home.draw(g);
			if (strX == -1) {
				Rect bounds = new Rect();
				phraseF.getTextBounds(phrase, 0, phrase.length(), bounds);
				strX = (gameWidth - bounds.width()) / 2;
			}
			strYUpdate();
			phraseF.setStyle(Style.FILL_AND_STROKE);
			phraseF.setColor(Color.BLACK);
			g.drawText(phrase, strX - 2, strY, phraseF);
			g.drawText(phrase, strX + 2, strY, phraseF);
			g.drawText(phrase, strX, strY + 2, phraseF);
			g.drawText(phrase, strX, strY - 2, phraseF);
			phraseF.setColor(Color.WHITE);
			g.drawText(phrase, strX, strY, phraseF);
			if (trans) {
				if (alpha < 250 && dTrans > 0) {
					alpha += dTrans;
				} else if (alpha == 250 && dTrans > 0) {
					mode = Mode.PLAYING;
					trial = Trial.start(getContext(), this, gameWidth, gameHeight, highScore);
					dTrans = -dTrans;
				} else if (alpha > 0) {
					alpha += dTrans;
				} else {
					dTrans = -dTrans;
					trans = false;
				}
				g.drawARGB(alpha, 0, 0, 0);
			}
		}
		if (getWidth() - gameWidth > 0) {
			int blackSize = (getWidth() - gameWidth) / 2;
			phraseF.setStyle(Style.FILL);
			phraseF.setColor(Color.BLACK);
			g.drawRect(0, 0, blackSize, getHeight(), phraseF);
			g.drawRect(getWidth() - blackSize, 0, getWidth(), getHeight(), phraseF);
		}
	}
	
}
