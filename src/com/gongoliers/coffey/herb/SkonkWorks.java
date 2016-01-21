package com.gongoliers.coffey.herb;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SkonkWorks {
	
	public static Bitmap[] getHome(GameView view) {
		AssetManager am = view.getContext().getAssets();
		InputStream open = null;
		Bitmap[] bmp = new Bitmap[2];
		try {
			open = am.open("home1.png");
			bmp[0] = BitmapFactory.decodeStream(open);
			open = am.open("home3.png");
			bmp[1] = BitmapFactory.decodeStream(open);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (open != null) {
		        try {
		          open.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		    }
		}
		return bmp;
	}
	
	public static Bitmap getTitle(GameView view) {
		AssetManager am = view.getContext().getAssets();
		InputStream open = null;
		Bitmap bmp = null;
		try {
			open = am.open("title.png");
			bmp = BitmapFactory.decodeStream(open);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (open != null) {
		        try {
		          open.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		    }
		}
		return bmp;
	}
	
	public static Bitmap getCoffey(GameView view) {
		AssetManager am = view.getContext().getAssets();
		InputStream open = null;
		Bitmap bmp = null;
		try {
			open = am.open("waves.png");
			bmp = BitmapFactory.decodeStream(open);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (open != null) {
		        try {
		          open.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		    }
		}
		return bmp;
	}
	
	public static Bitmap getMazzone(GameView view) {
		AssetManager am = view.getContext().getAssets();
		InputStream open = null;
		Bitmap bmp = null;
		try {
			open = am.open("mazzone.png");
			bmp = BitmapFactory.decodeStream(open);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (open != null) {
		        try {
		          open.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		    }
		}
		return bmp;
	}
	
	public static Bitmap getGongoleski(GameView view) {
		AssetManager am = view.getContext().getAssets();
		InputStream open = null;
		Bitmap bmp = null;
		try {
			open = am.open("gongoleski.png");
			bmp = BitmapFactory.decodeStream(open);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (open != null) {
		        try {
		          open.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		    }
		}
		return bmp;
	}
	
	public static Bitmap getBrad(GameView view) {
		AssetManager am = view.getContext().getAssets();
		InputStream open = null;
		Bitmap bmp = null;
		try {
			open = am.open("brad.png");
			bmp = BitmapFactory.decodeStream(open);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (open != null) {
		        try {
		          open.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		    }
		}
		return bmp;
	}
	
	public static Bitmap getDavid(GameView view) {
		AssetManager am = view.getContext().getAssets();
		InputStream open = null;
		Bitmap bmp = null;
		try {
			open = am.open("david.png");
			bmp = BitmapFactory.decodeStream(open);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (open != null) {
		        try {
		          open.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		    }
		}
		return bmp;
	}
	
	public static Bitmap[] getRightGondola(Context c) {
		AssetManager ass = c.getAssets();
		InputStream open = null;
		Bitmap[] bmp = new Bitmap[7];
		try {
			for (int i = 0; i < bmp.length; i++) {
				String path = "right" + (i + 1) + ".png";
				open = ass.open(path);
				bmp[i] = BitmapFactory.decodeStream(open);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (open != null) {
		        try {
		          open.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		    }
		}
		return bmp;
	}
	
	public static Bitmap[] getLeftGondola(Context c) {
		AssetManager ass = c.getAssets();
		InputStream open = null;
		Bitmap[] bmp = new Bitmap[7];
		try {
			for (int i = 0; i < bmp.length; i++) {
				String path = "left" + (i + 1) + ".png";
				open = ass.open(path);
				bmp[i] = BitmapFactory.decodeStream(open);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (open != null) {
		        try {
		          open.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		    }
		}
		return bmp;
	}
	
	public static Bitmap[] getExplosion(Context c) {
		AssetManager ass = c.getAssets();
		InputStream open = null;
		Bitmap[] bmp = new Bitmap[10];
		try {
			for (int i = 0; i < bmp.length; i++) {
				String path = "exp" + (i + 1) + ".png";
				open = ass.open(path);
				bmp[i] = BitmapFactory.decodeStream(open);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (open != null) {
		        try {
		          open.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		    }
		}
		return bmp;
	}
	
	public static Bitmap[] getObstacle(Context c) {
		AssetManager ass = c.getAssets();
		InputStream open = null;
		Bitmap[] bmp = new Bitmap[2];
		try {
			open = ass.open("obstacle1.png");
			bmp[0] = BitmapFactory.decodeStream(open);
			open = ass.open("obstacle2.png");
			bmp[1] = BitmapFactory.decodeStream(open);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (open != null) {
		        try {
		          open.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		    }
		}
		return bmp;
	}
	
}
