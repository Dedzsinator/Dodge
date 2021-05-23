package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Pellet extends GameObject {

    private Handler handler;
    Random r = new Random();
    private int randX = (r.nextInt(5 - -5) + -5) + 2;
    private int randY = (r.nextInt(5 - -5) + -5) + 2;

    public Pellet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        if(randX == 0 || randY == 0) {
        	randX += 2;
        	randY += 2;	
        }
        velX = randX;
        velY = randY;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y >= Game.HEIGHT) handler.removeObject(this);

        handler.addObject(new Trail(x, y, ID.Trail, new Color(250, 142, 47), 16, 16, 0.02f, handler));
    }

    public void render(Graphics g) {
        g.setColor(new Color(250, 142, 47));
        g.fillRect(x, y, 16, 16);
    }
}
