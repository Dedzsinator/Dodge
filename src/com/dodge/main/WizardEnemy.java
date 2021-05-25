package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class WizardEnemy extends GameObject{
	private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    Random r = new Random();

    public WizardEnemy(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        
        this.handler = handler;
        this.hud = hud;

        velX = 3;
        velY = 3;
        
    }
    
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, 25, 25);
    }

    public void tick() {
    	scoreKeep++;
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.orange, 25, 25, 0.02f, handler));
        if(scoreKeep == 80) {
        	scoreKeep = 0;
        	 if(scoreKeep == 0)
         		handler.addObject(new Grenade(x+25, y+25, ID.Grenade, handler));
        }
    }
	
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, 25, 25);
	}
}
