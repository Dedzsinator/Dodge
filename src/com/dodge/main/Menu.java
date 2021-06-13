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
            if (mouseOver(mx, my, 420, 200, 400, 64)) {
                game.gameState = STATE.Select;
                AudioPlayer.getSound("menu_sound").play();
                return;
            }
            
            //SKIN BUTTON
            if(mouseOver(mx, my,500, 100, 200, 64)) {
            	game.gameState = STATE.Skin;
            }
            
            //HELP BUTTON
            if (mouseOver(mx, my, 420, 330, 400, 64)) {
                game.gameState = Game.STATE.Help;
            }
            
            //SKINS BUTTON
            if(mouseOver(mx, my, 100, 690, 100, 32)) {
            	game.gameState = Game.STATE.Skin;
            }
            
            //MUSIC BUTTON
            if(mouseOver(mx, my, 100, 730, 300, 64)) {
            	game.gameState = Game.STATE.Musics;
            }
            
            //CREDITS BUTTON
            if (mouseOver(mx, my, 420, 460, 400, 64)) {
            	game.gameState = Game.STATE.Credits;
            }
            
            
            //EXIT BUTTON
            if (mouseOver(mx, my, 420, 590, 400, 64)) {
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
            
            AudioPlayer.getSound("menu_sound").play();
        }
        
        //DIFFICULTY SELECTION
        if(game.gameState == Game.STATE.Select) {
        	
        	//NORMAL BUTTON
            if (mouseOver(mx, my, 420, 200, 400, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,handler));
            	
                game.diff = 0;
                AudioPlayer.getSound("menu_sound").play();
                
            }
            
            
            //HARD BUTTON
            if (mouseOver(mx, my, 420, 330, 400, 64)) {
            	game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,handler));
            	
                game.diff = 1;
                AudioPlayer.getSound("menu_sound").play();
            }
            
            
            //BACK BUTTON
            if (mouseOver(mx, my, 460, 660, 300, 64)) {
            	game.gameState = Game.STATE.Menu;
            	AudioPlayer.getSound("menu_sound").play();
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
                    AudioPlayer.getSound("menu_sound").play();
                }
            }
        }
        
        
        //BACK SKINS BUTTON
        if(game.gameState == Game.STATE.Skin) {
          if (mouseOver(mx, my, 460, 660, 300, 64)) {
            game.gameState = Game.STATE.Menu;
            AudioPlayer.getSound("menu_sound").play();
            return;
            }    
        }
        
        //BACK CREDITS BUTTON
        if (game.gameState == Game.STATE.Credits) {
        	if (mouseOver(mx, my, 460, 660, 300, 64)) {
        		game.gameState = Game.STATE.Menu;
        		AudioPlayer.getSound("menu_sound").play();
        		return;
        	}
        }
        //BACK MUSICS BUTTON
        if (game.gameState == Game.STATE.Musics) {
        	if (mouseOver(mx, my, 460, 660, 300, 64)) {
        		game.gameState = Game.STATE.Menu;
        		AudioPlayer.getSound("menu_sound").play();
        		return;
        	}
        }
        
        //MUSIC CHOOSING - alap
        if (game.gameState == Game.STATE.Musics) {
        	if (mouseOver(mx, my,480, 160, 280, 42)) {
        		AudioPlayer.getMusic("music2").loop();
        		return;
        	}
        }
        
        //MUSIC CHOOSING - formidable
        if (game.gameState == Game.STATE.Musics) {
        	if (mouseOver(mx, my, 480, 260, 280, 42)) {
        		AudioPlayer.getMusic("music").loop();
        		return;
        	}
        }
        
        //MUSIC CHOOSING - japan
        if (game.gameState == Game.STATE.Musics) {
        	if (mouseOver(mx, my, 480, 360, 280, 42)) {
        		AudioPlayer.getMusic("music1").loop();
        		return;
        	}
        }
        
        
        //BACK HELP BUTTON
        if (game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 460, 660, 300, 64)) {
                game.gameState = Game.STATE.Menu;
                AudioPlayer.getSound("menu_sound").play();
                return;
            }
        }
        
        
      //TRY AGAIN BUTTON
        if (game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, 460, 410, 300, 64)) {
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,handler));
                AudioPlayer.getSound("menu_sound").play();
            }
        }
        
      //END TO MENU BUTTON
        if (game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, 460, 660, 300, 64)) {
                game.gameState = Game.STATE.Menu;
                AudioPlayer.getSound("menu_sound").play();
                return;
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
        Font fnt3 = new Font("arial", 1, 20);

        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Dodge!", 540, 90);


        g.setFont(fnt2);
        g.drawRect(420, 200, 400, 64);
        g.drawString("J�t�k", 580, 240);
        
        g.drawRect(420, 330, 400, 64);
        g.drawString("Seg�s�g", 565, 370);

        g.drawRect(420, 460, 400, 64);
        g.drawString("K�sz�tette", 550, 500);
        
        g.drawRect(420, 590, 400, 64);
        g.drawString("Kil�p�s", 570, 630);
        
        g.setFont(fnt3);
        g.drawRect(100, 730, 100, 32);
        g.drawString("Zen�k", 120, 753);
        
        g.drawRect(100, 690, 100, 32);
        g.drawString("Kin�zetek", 105, 713);
        
        }else if(game.gameState == Game.STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Seg�ts�g", 510, 90);

            g.setFont(fnt3);
            g.drawString("Haszn�ld a WASD gombokat, hogy mozogj", 415, 360);

            g.setFont(fnt2);
            g.drawRect(460, 660, 300, 64);
            g.drawString("Vissza", 560, 700);
            
        }
        else if(game.gameState == Game.STATE.Musics) {
        	Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Zen�k", 560, 90);

            //alap
            g.setFont(fnt3);
            g.drawRect(480, 160, 280, 42);
            g.drawString("Alap", 595, 190);
            
            //formidable
            g.setFont(fnt3);
            g.drawRect(480, 260, 280, 42);
            g.drawString("Formidable", 565, 290);
            //japanese
            g.setFont(fnt3);
            g.drawRect(480, 360, 280, 42);
            g.drawString("Jap�n style", 565, 390);
            

            g.setFont(fnt2);
            g.drawRect(460, 660, 300, 64);
            g.drawString("Vissza", 560, 700);
        }
        else if(game.gameState == Game.STATE.Skin) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Kin�zetek", 540, 90);

            g.setFont(fnt3);
            g.drawString("V�laszthat� kin�zetek", 50, 300);

            g.setFont(fnt2);
            g.drawRect(460, 660, 300, 64);
            g.drawString("Vissza", 560, 700);
            
        }else if(game.gameState == Game.STATE.Credits) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("K�sz�tette", 500, 90);

            g.setFont(fnt3);
            g.drawString("Program: D�gi N�ndor", 520, 300);

            g.setFont(fnt2);
            g.drawRect(460, 660, 300, 64);
            g.drawString("Vissza", 560, 700);
            
        }else if (game.gameState == Game.STATE.End) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Meghalt�l", 500, 90);

            g.setFont(fnt3);
            g.drawString("Pontsz�mod: " + hud.getScore(), 520, 300);

            g.setFont(fnt2);
            g.drawRect(460, 410, 300, 64);
            g.drawString("Pr�b�ld �jra", 520, 455);
            
            g.setFont(fnt2);
            g.drawRect(460, 660, 300, 64);
            g.drawString("Vissza a men�be", 490, 700);
        } else if(game.gameState == Game.STATE.Select) {

            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("V�lassz neh�zs�get", 370, 90);


            g.setFont(fnt2);
            g.drawRect(420, 200, 400, 64);
            g.drawString("Norm�l", 565, 240);

            
            g.drawRect(420, 330, 400, 64);
            g.drawString("Neh�z", 568, 370);


            g.drawRect(460, 660, 300, 64);
            g.drawString("Vissza", 560, 700);

            }
    }
}  
