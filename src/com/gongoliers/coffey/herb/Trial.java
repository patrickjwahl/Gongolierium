package com.gongoliers.coffey.herb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

public class Trial {
	
	private int gameWidth;
	private int gameHeight;
	
	Context context;
	GameView view;
	
	private int score;
	
	public enum State {
		PLAYING, DEAD, READY
	}
	
	private State state;
	
	private int highScore;
	
	private Gondola gondola;
	private ArrayList<Obstacle> obstacles;
	
	private Explosion exp;
	private ScorePanel sp;
	
	private Random random;
	
	private int addCounter; 
	private int easyCounter;
	
	private int gameX;
	
	private int strY = 75;
	
	public Trial(Context context, GameView view, int gameWidth, int gameHeight, int highScore) {
		score = 0;
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		this.context = context;
		this.view = view;
		state = State.PLAYING;
		gondola = new Gondola(context, view, gameWidth, gameHeight);
		obstacles = new ArrayList<Obstacle>();
		exp = new Explosion(context, view, gameHeight);
		sp = null;
		random = new Random();
		addCounter = 0;
		easyCounter = 10;
		gameX = (view.getWidth() - gameWidth) / 2;
		strY = gondola.getY() + Gondola.getHeight() + 40;
		this.highScore = highScore;
	}
	
	public static Trial start(Context context, GameView view, int gameWidth, int gameHeight, int highScore) {
		return new Trial(context, view, gameWidth, gameHeight, highScore);
	}
	
	public void left() {
		gondola.left();
	}
	
	public void right() {
		gondola.right();
	}
	
	public State getState() {
		return state;
	}
	
	public void generateRandom() {
		int x1 = random.nextInt((gameWidth - (Gondola.getWidth() + 5 * easyCounter + 150))) + (gameX + Gondola.getWidth() + 5 * easyCounter + 75);
		obstacles.add(new Obstacle(context, view, gameHeight, gameWidth, x1));
		int x2 = x1 + Obstacle.getWidth() + 5;
		obstacles.add(new Obstacle(context, view, gameHeight, gameWidth, x2));
		int x3 = x1 - (Obstacle.getWidth() + Gondola.getWidth() + 5 * easyCounter);
		obstacles.add(new Obstacle(context, view, gameHeight, gameWidth, x3));
		int x4 = x3 - (Obstacle.getWidth() + 5);
		obstacles.add(new Obstacle(context, view, gameHeight, gameWidth, x4));
		if (easyCounter > -1) easyCounter--;
	}
	
	public void draw(Canvas g) {
		if (state == State.PLAYING) {
			if (addCounter == 45) {
				generateRandom();
				addCounter = 0;
			} else addCounter++;
			
			Paint paint = new Paint();
			paint.setColor(Color.BLACK);
			gondola.update();
			gondola.draw(g);
			
			ArrayList<Obstacle> newObstacles = new ArrayList<Obstacle>();
			for (Obstacle e : obstacles) {
				e.update(true);
				e.draw(g);
				if (Rect.intersects(e.getRect(), gondola.getRect())) {
					state = State.DEAD;
					draw(g);
					return;
				}
				if (e.getY() > gondola.getY() && !e.isPassed()) {
					score++;
					e.pass();
				}
				if (e.getY() < view.getHeight()) newObstacles.add(e);
			}
			obstacles = newObstacles;
		
			paint.setColor(Color.BLACK);
			paint.setTextSize(48 * gameWidth / 750);
			String phrase = String.valueOf(score / 4);
			Rect bounds = new Rect();
			paint.getTextBounds(phrase, 0, phrase.length(), bounds);
			int strX = (gameWidth - bounds.width()) / 2;
			g.drawText(phrase, strX - 2, strY, paint);
			g.drawText(phrase, strX + 2, strY, paint);
			g.drawText(phrase, strX, strY + 2, paint);
			g.drawText(phrase, strX, strY - 2, paint);
			paint.setColor(Color.WHITE);
			g.drawText(phrase, strX, strY, paint);
			
		} else {
			for (Obstacle e : obstacles) {
				e.update(false);
				e.draw(g);
			}
			if (!exp.isFinished()) {
					exp.setX(gondola.getX() - 250 * gameWidth / 750);
					exp.update();
					exp.draw(g);
			} else {
				
				if (sp == null) {
					sp = new ScorePanel(gameWidth, gameHeight, score / 4);
					state = State.READY;
				}
				sp.update();
				sp.draw(g);
			}
		}
	}
	
	class ScorePanel {
		
		public final String CONGRATS = "New high score!";
		public final String[] COFFEY = {"Better to row a gondola than live for nothing.", "Courage is rowing on a minute longer.", "You can make a throne of gondolas, but you can't sit on it for long.",
				"The real and lasting victories are those of gondolas, and not of war.", "Gondolas, at first though sweet, bitter ere long back on thee recoil.",
				"Our gondolas are never dead to us, until we have forgotten them.", "Hope is a moving gondola.", "The gondola of true love never did run smooth",
				"The empty gondola makes the loudest sound.", "Pleasure and gondolas make the hours seem short.", "Length of life matters not so much as length of gondola.",
				"In life, as in gondola.", "Seek out, respect, and remember the balance.", "When you are one with the gondola, you are ready.", 
				"Timing *is* everything.", "With age comes wisdom; with gondolas, balance.", "The experienced moves the boat, the master is the boat.",
				"Don't try to move the walls, move yourself.", "Balance is the key to gondola, to life.", "Hats guard minds, minds guard gondolas.", "Take all advice into consideration, keep only the best.",
				"Move more, it's good for you.", "When given the choice, choose gondola.", "Patiently progress toward your goal; you might reach it.", 
				"Wars have been won and lost, gondolas persist.", "Know where to harbor your gondola before storms hit.", 
				"Many parts, one gondola.", "Gondolas are rowed, not punted.", "A little impatience will spoil great gondolas.", "Talk does not row boats.",
				"Experience is a remo that you're given when you lose your forcola."};
		public final String[] GONGOLESKI = {"Insert quarter to continue...", "If we don't end gondolas, gondolas will end us.",
				"The valiant never explode in a gondola but once.", "It is better to row on your feet than to live on your knees.",
				"In gondoliering there is no substitute for victory.", "Gondoliering is a series of catastrophes which result in victory.", "So long as there are men, there will be gondolas.",
				"A man may die, nations may rise and fall, but a gondola lives on.", 
				"Cost of a single gondola: $1,753,342,952", "Never forget that your gondola was made by the lowest bidder.",
				"I only regret that I have but one gondola to give for my country.",
				"Strong men also cry", "I am become death, the destroyer of gondolas.", 
				"When the rich wage war, it's the gondolas who die.", "Hell is other gondolas.", 
				"Please stop, we're running out of gondolas.", 
				"I will see you in the next life", "Failure apparently was an option", "New world record! Just kidding hahahahaha", "Story mode 80% complete", "Please throw phone at wall in anger - sincerly, Phone Company",
				"Like it? Download the radio version!", "Try virtual reality for better experience", "Advertisement goes here", "I love the smell of burning gondolas in the morning.",
				"We'll always have Venice.", "Mother of mercy, is this the end of Gongolierium?", "For a few gondolas more...", "It's not me, it's the gondola!", "You're gonna need a bigger boat.",
				"A boy's best friend is his gondola.", "Women and children first!", "Oh, the tears of unfathomable sadness!", "Gondola is love. Gondola is life.",
				"Genius is 1% inspiration, 99% gondolas.", "Thank you for flying GondolAir, enjoy your day!",
				"*COMMENT CENSORED BY FCC*", "I am the master of my fate, I am the captain of my gondola.", "Row, row, row your boat...", 
				"In WWII, the Venetian Theatre saw little conflict.", "One in the gondola is worth two in the river.", "Gondolas are asymmetric, like your abilities.",
				"Gondolas can carry plenty of cargo, including your shame.", "I'm not letting you row me around Providence or Venice.", "Show me a gondola and I'll show you someone who can't row it.",
				"Don't try this with the family gondola.", "Gondolas make great pets!", "No rudder, no keel, no wonder you crashed!", "Four score and seven gondolas ago...",
				"Gondolas do not determine who is right - only who is left.", "The best weapon against an enemy is this game.",
				"8 types of wood and 280 pieces make one gondola.", "A fall into the river makes you wiser."};
		public final String MAZZONE = "I'm not even in this game!";
		public final String BRAD = "I paid $50 for this cameo!";
		public final String DAVID = "Check yourself before you wreck yourself.";

		private String jestSackal;
		
		private int width;
		private int height;
		
		private Wat head;
		
		private int x; 
		private int y;
		
		private int strX;
		private int strY;
		private int scoreStrY;
		private int bestStrY;
		private int continueStrY;
		private int jestY;
		private int dy;
		
		private int scoreStrX;
		private int scoreX;
		private int continueX;
		private int jestX;
		
		private Paint font;
		private Paint scoreFont;
		private Paint textFont;
		private Paint continueFont;
		private Paint jestFont;
		private int score;
		private int nowScore;
		private String scoreStr;
		
		
		private boolean drawingStr;
		private boolean drawingPanel;
		
		public ScorePanel(int gameWidth, int gameHeight, int score) {
			width = gameWidth;
			height = view.getHeight();
			x = (view.getWidth() - width) / 2;
			y = view.getHeight();
			font = new Paint();
			font.setTextSize((int)(72 * ((double)gameWidth / 750d)));
			scoreFont = new Paint();
			scoreFont.setTextSize((int)(44 * ((double)gameWidth / 750d)));
			textFont = new Paint();
			textFont.setTextSize((int)(48 * ((double)gameWidth / 750d)));
			continueFont = new Paint();
			continueFont.setTextSize((int)(36 * ((double)gameWidth / 750d)));
			jestFont = new Paint();
			jestFont.setTextSize((int)(24 * ((double)gameWidth / 750d)));
			this.score = score;
			nowScore = 0;
			scoreStr = "";
			String phrase = "GAME OVER";
			Rect bounds = new Rect();
			font.getTextBounds(phrase, 0, phrase.length(), bounds);
			strX = (view.getWidth() - bounds.width()) / 2;
			strY = 225 * gameHeight / 900;
			scoreStrX = 275 * gameWidth / 750;
			scoreStrY = 345 * gameHeight / 900;
			bestStrY = 415 * gameHeight / 900; 
			continueStrY = 485 * gameHeight / 900;
			String scoreText = "Score:";
			textFont.getTextBounds(scoreText, 0, scoreText.length(), bounds);
			scoreX = scoreStrX + bounds.width() + 20;
			String text = "Tap the screen to continue";
			continueFont.getTextBounds(text, 0, text.length(), bounds);
			continueX = (view.getWidth() - bounds.width()) / 2;
			dy = -3;
			drawingStr = true;
			drawingPanel = false;
			int num = random.nextInt(103);
			int type = 1;
			if (num < 50) type = 1;
			else if (num > 53) type = 2;
			else if (num == 51) type = 3;
			else if (num == 52) type = 4;
			else if (num == 53) type = 5;
			head = new Wat(view.getContext(), view, type);
			if (score > highScore) {
				jestSackal = CONGRATS;
			} else if (type == 1) {
				jestSackal = COFFEY[random.nextInt(COFFEY.length)];
			} else if (type == 2) {
				jestSackal = GONGOLESKI[random.nextInt(GONGOLESKI.length)];
			} else if (type == 3) {
				jestSackal = MAZZONE;
			} else if (type == 4) {
				jestSackal = BRAD;
			} else if (type == 5) {
				jestSackal = DAVID;
			}
			jestFont.getTextBounds(jestSackal, 0, jestSackal.length(), bounds);
			jestX = (view.getWidth() - bounds.width()) / 2;
			jestY = -1;
		}
		
		public void update() {
			if (score > highScore) {
				highScore = score;
				writeHighScore();
			}
			if (drawingStr) {
				strY += dy;
				dy++; 
				if (dy == 5) {
					drawingStr = false;
					drawingPanel = true;
				}
			} else if (drawingPanel) {
				if (y > 100) y -= 100;
				else if (y <= 100 && y > 0) y = 0;
				else drawingPanel = false;
			} else {
				scoreStr = String.valueOf(nowScore);
				if (nowScore < score) nowScore++;
				head.update();
				jestY = head.getY() + head.getHeight() + 30;
			}
		}
		
		public void writeHighScore() {
			File f = new File(context.getFilesDir(), "3jf31.glm");
			try {
				PrintWriter pw = new PrintWriter(new FileWriter(f));
				pw.println(Integer.toString(highScore, 5));
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void draw(Canvas g) {
			
			font.setStyle(Style.FILL);
			font.setColor(Color.rgb(210, 180, 140));
			g.drawRect(x, y, x + width, y + height, font);
			font.setColor(Color.rgb(167, 85, 2));
			g.drawRect(x + 10, y + 10, x + width - 10, y + height - 10, font);
			font.setStyle(Style.STROKE);
			font.setColor(Color.BLACK);
			g.drawRect(x, y, x + width, y + height, font);
			g.drawRect(x + 12, y + 12, x + width - 12, y + height - 12, font);
			
			font.setColor(Color.BLACK);
			font.setStyle(Style.FILL_AND_STROKE);
			g.drawText("GAME OVER", strX - 2, strY, font);
			g.drawText("GAME OVER", strX + 2, strY, font);
			g.drawText("GAME OVER", strX, strY - 2, font);
			g.drawText("GAME OVER", strX, strY + 2, font);
			font.setColor(Color.GREEN);
			g.drawText("GAME OVER", strX, strY, font);
			
			g.drawText("Score:", scoreStrX, y + scoreStrY, textFont);
			g.drawText("Best:", 285 * gameWidth / 750, y + bestStrY, textFont);
			
			continueFont.setColor(Color.BLACK);
			g.drawText("Tap the screen to continue", continueX, y + continueStrY, continueFont);
			
			scoreFont.setColor(Color.BLACK);
			g.drawText(scoreStr, scoreX - 2, y + scoreStrY, scoreFont);
			g.drawText(scoreStr, scoreX + 2, y + scoreStrY, scoreFont);
			g.drawText(scoreStr, scoreX, y + scoreStrY, scoreFont);
			g.drawText(scoreStr, scoreX, y + scoreStrY, scoreFont);
			scoreFont.setColor(Color.WHITE);
			g.drawText(scoreStr, scoreX, y + scoreStrY, scoreFont);
			
			scoreFont.setColor(Color.BLACK);
			g.drawText(String.valueOf(highScore), scoreX - 2, y + bestStrY, scoreFont);
			g.drawText(String.valueOf(highScore), scoreX + 2, y + bestStrY, scoreFont);
			g.drawText(String.valueOf(highScore), scoreX, y + bestStrY, scoreFont);
			g.drawText(String.valueOf(highScore), scoreX, y + bestStrY, scoreFont);
			scoreFont.setColor(Color.WHITE);
			g.drawText(String.valueOf(highScore), scoreX, y + bestStrY, scoreFont);
			
			jestFont.setColor(Color.WHITE);
			head.draw(g);
			g.drawText(jestSackal, jestX, jestY, jestFont);
		}
		
	}
}
