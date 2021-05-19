package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Grenade extends GameObject{
	private Handler handler;
	private int scoreKeep = 0;
	Random r = new Random();
	
	public Grenade(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		this.handler = handler;

        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }

    public void tick() {
    	scoreKeep++;
        x += velX;
        y += velY;
        if(scoreKeep / 15 == 0) {
        	scoreKeep = 0;
        	handler.removeObject(this);
        	for(int i = 1; i <= 8; i++) {
        	handler.addObject(new EnemyBossBullet(x+48, y+48, ID.BasicEnemy, handler));
        	}
        }
        
        handler.addObject(new Trail(x, y, ID.Trail, Color.pink, 16, 16, 0.02f, handler));
    }

    public void render(Graphics g) {
       // for(int i = 255; i >= 0; i--) {
        	g.setColor(Color.pink);
        	g.fillRect(x, y, 20, 20);
        //}
    }
}
