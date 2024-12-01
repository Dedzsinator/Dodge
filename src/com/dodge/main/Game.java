package com.dodge.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;
    private ExecutorService executorService;
    private boolean running = false;
    public static boolean paused;
    public int diff = 0;
    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Shop shop;

    public enum STATE {
        Menu,
        Select,
        Help,
        Shop,
        Game,
        Credits,
        Skin,
        Musics,
        End
    }

    public static STATE gameState = STATE.Menu;

    public Game() {
        handler = new Handler();
        hud = new HUD();
        shop = new Shop(handler, hud);
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        this.addMouseListener(shop);

        AudioPlayer.load();
        AudioPlayer.getMusic("music").loop();
        new Window(WIDTH, HEIGHT, "Dodge!", this);

        spawner = new Spawn(handler, hud, this);
        r = new Random();

        if (gameState == STATE.Game) {
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
        } else {
            for (int i = 0; i < 20; i++) {
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        }
    }

    public synchronized void start() {
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(this);
        running = true;
    }

    public synchronized void stop() {
        try {
            executorService.shutdown();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println("FPS: " + frames);
            }
        }
        stop();
    }

    private void tick() {
        if (gameState == STATE.Game) {
            if (!paused) {
                hud.tick();
                spawner.tick();
                handler.tick();

                if (HUD.HEALTH <= 0) {
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.clearEnemys();
                    for (int i = 0; i < 20; i++) {
                        handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                    }
                }
            }
        } else if (List.of(STATE.Menu, STATE.End, STATE.Select, STATE.Credits, STATE.Help, STATE.Skin, STATE.Musics).contains(gameState)) {
            menu.tick();
            handler.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (paused) {
            g.setColor(Color.WHITE);
            g.drawString("PAUSED", 270, 200);
        }

        if (gameState == STATE.Game) {
            hud.render(g);
            handler.render(g);
        } else if (gameState == STATE.Shop) {
            shop.render(g);
        } else if (List.of(STATE.Menu, STATE.Help, STATE.End, STATE.Select, STATE.Credits, STATE.Skin, STATE.Musics).contains(gameState)) {
            menu.render(g);
            handler.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        return Math.max(min, Math.min(max, var));
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public void startGame() {
        gameState = STATE.Game;
        handler.clearEnemys();
        handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
    }
    
    public static void main(String args[]) {
        /**
        //! Ki itt belépsz hagyj fell minden reménnyel, a kódot nem bántsd, bármiféle plusz módosítási kisérlet felesleges.
        //! Elvesztegetett órák: ~60 
         */
        new Game();
    }
}