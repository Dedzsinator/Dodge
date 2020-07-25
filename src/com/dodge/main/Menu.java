package com.dodge.main;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.dodge.main.Game.STATE;

public class Menu extends MouseAdapter{
    
    private Game game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == Game.STATE.Menu) {
        	
        	//PLAY BUTTON
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                game.gameState = STATE.Select;
            	
                return;
            }
            
            
            //HELP BUTTON
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = Game.STATE.Help;
                

            }
            
            
            //EXIT BUTTON
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(1);
                
            }         
            
            
            //END GAME
            if (game.gameState == Game.STATE.End) {
                if (mouseOver(mx, my, 210, 350, 200, 64)) {
                    game.gameState = Game.STATE.Menu;
                    hud.setLevel(1);
                    hud.setLevel(0);

                }
            }
        }
        
        if(game.gameState == Game.STATE.Select) {
        	
        	//NORMAL BUTTON
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,handler));
            	
                game.diff = 0;
                
            }
            
            
            //HARD BUTTON
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
            	game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,handler));
            	
                game.diff = 1;
                

            }
            
            
            //BACK BUTTON
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
            	game.gameState = Game.STATE.Menu;
                return;
            	}         
            
            
            //END GAME
            if (game.gameState == Game.STATE.End) {
                if (mouseOver(mx, my, 210, 350, 200, 64)) {
                    game.gameState = Game.STATE.Game;
                    hud.setLevel(1);
                    hud.setLevel(0);
                    handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                    handler.clearEnemys();
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy, handler));

                }
            }
        }
        
        //BACK HELP BUTTON
        if (game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Menu;
                return;
            }
        }
        
        
      //TRY AGAIN BUTTON
        if (game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,handler));

            }
        }

       
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if(mx > x && mx < x + width) {
            if(my > y && my < y + height) {
                return true;
            }else return false;
        }else return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
    	
        if(game.gameState == Game.STATE.Menu) {

        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);

        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Dodge!", 240, 70);


        g.setFont(fnt2);
        g.drawRect(210, 150, 200, 64);
        g.drawString("Play", 270, 190);

        
        g.drawRect(210, 250, 200, 64);
        g.drawString("Help", 270, 290);


        g.drawRect(210, 350, 200, 64);
        g.drawString("Quit", 270, 390);

        }else if(game.gameState == Game.STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 70);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies", 50, 300);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
            
        } else if (game.gameState == Game.STATE.End) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 180, 70);

            g.setFont(fnt3);
            g.drawString("You lost with a score of: " + hud.getScore(), 175, 300);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 245, 390);
        } else if(game.gameState == Game.STATE.Select) {

            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("SELECT DIFFICULTY", 60, 70);


            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 260, 190);

            
            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 275, 290);


            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);

            }
    }
}  
