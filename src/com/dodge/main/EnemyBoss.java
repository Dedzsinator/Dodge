package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject {

    private Handler handler;
    Random r = new Random();

    private int timer = 80;
    private int timer2 = 50;

    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 96, 96);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(timer <= 0) velY = 0;
        else timer--;

        if(timer <= 0) timer2--;
        if(timer2 <= 0) {
        	
            if(velX == 0) velX = 2;
            
            if(velX > 0)
            velX += 0.005f;
            else if(velX < 0)
            velX -= 0.005f;
            
            velX = Game.clamp(velX, -10, 10);
            	
            int spawn = r.nextInt(5);
            if(spawn == 0) handler.addObject(new EnemyBossBullet(x+48, y+48, ID.BasicEnemy, handler));
            
        }
        
        //if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 50) velX *= -1;

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 96, 96);
    }
}