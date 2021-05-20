package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Pellet extends GameObject {

    private Handler handler;
    Random r = new Random();

    public Pellet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = (r.nextInt(5 - -5) + -5) + 4;
        velY = (r.nextInt(5 - -5) + -5) + 2;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y >= Game.HEIGHT) handler.removeObject(this);

        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }
}
