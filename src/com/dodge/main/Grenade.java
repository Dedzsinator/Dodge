package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Grenade extends GameObject{
	private Handler handler;
	Random r = new Random();
	
	public Grenade(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		this.handler = handler;

        //velX = (r.nextInt(5 - -5) + -5);
        //velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        for(int i = 255; i >= 0; i--) {
        	g.setColor(new Color(255, i, 0));
        	g.fillRect(x, y, 20, 20);
        }
    }
}
