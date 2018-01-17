package de.L118.game;

public class World {
	
	public static final int TILESIZE = 100;
	private Blocks[][][] blocks; // [ebene][x][y]
	private float xPos;
	private short[][] types = {
			{0,0,0,0,2,1,1,1,1,1,1,3,0,0,0,0,0,0,0,0,0,0,0,0},	
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
			{0,0,0,0,0,0,0,0,2,1,1,3,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,0,0,0,2,1,3,0,2}
	};
	
	public World() {
		blocks = new Blocks[1][types[0].length][types.length];
		xPos = (float) 0;

		for(int i = 0; i < blocks[0].length; i++) {
			for(int j = 0; j < blocks[0][0].length; j++) {
				blocks[0][i][j] = new Blocks(i,j, types[types.length-(j+1)][i]);
			}
		}
	}
	
	public boolean isObject(int x, int y) {
		//TODO
		return false;
	}
	
	void moveLeft(double distance) {
		xPos -= distance;
	}
	
	public void moveRight(double d) {
		xPos += d;
	}
	
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}
	
	public float getxPos() {
		return xPos;
	}
	
	public void loadWorld() {
		//TODO
	}
	
	public void update() {
		
	}
	
	public void render() {
		for(int i = 0; i < blocks[0].length; i++) {
			for(int j = 0; j < blocks[0][0].length; j++) {
				blocks[0][i][j].draw(xPos - Player.DISPLAY_POS);
			}
		}
	}
	
	public Blocks blockAt(int x, int y) {
		if(x>= blocks[0].length || y>= blocks[0][0].length || x<0 || y<0) {
			return new Blocks();
		}else {
			return blocks[0][x][y];
		}
	}
}
