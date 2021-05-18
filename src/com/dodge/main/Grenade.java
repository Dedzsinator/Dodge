package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Grenade extends GameObject {
	private Handler handler;
	Random r = new Random();
	
	public Grenade(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 10, 10);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail(x,y, ID.Trail, Color.red, 10, 10, 0.02f, handler));
	}
	
	public void render(Graphics g) {
		int green = 255;
		for(int i = 1; i <= 3; i++) {
			g.setColor(new Color(255, green, 0));
			g.fillRect(x, y, 10, 10);
			green = green / 2;
		}
	}
}
