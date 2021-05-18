package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EnemyGrenader extends GameObject{
	private Handler handler;
	
	public EnemyGrenader(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 5;
		velY = 5;
		
	}
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, 25, 25);
    }
	
	public void tick() {
		 x += velX;
	     y += velY;

	     if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
	     if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

	     handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
	     
	     handler.addObject(new Grenade(x+48, y+48, ID.Grenade, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, 25, 25);
	}
}
