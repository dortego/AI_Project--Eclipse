package ai_game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.security.SecureRandom;

public class Game extends Canvas implements Runnable {

 public final static int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
  // public final static int WIDTH = 400, HEIGHT = WIDTH / 12 * 9;

  private Thread thread;
  private boolean running = false;

  private SecureRandom r;
  private Handler handler;
//  private HeadsUpDisplay hud;

  public Game() {
    handler = new Handler();
    this.addKeyListener(new KeyInput(handler));

    new Window(WIDTH, HEIGHT, "Game Frame", this);

//    hud = new HeadsUpDisplay();

    r = new SecureRandom();

    handler.addObject(new Goal(r.nextInt((WIDTH/2) - 50), r.nextInt(HEIGHT - 50), ID.Goal, handler));
//    handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
    handler.addObject(new Player(WIDTH/4-32, HEIGHT/2-32, ID.Player, handler));
//    for (int i = 0; i < 2; i++)
//      handler.addObject(new Ricochet(r.nextInt((WIDTH/2) - 50), r.nextInt(HEIGHT - 50), ID.Ricochet, handler));
//    for (int i = 0; i < 3; i++)
//     handler.addObject(new SmartEnemy(WIDTH-64, HEIGHT-64, ID.SmartEnemy, handler));
    handler.addObject(new Ricochet(WIDTH/3, HEIGHT/3, ID.Ricochet1, handler));
    handler.addObject(new Ricochet(WIDTH/2, HEIGHT/3, ID.Ricochet2, handler));
    handler.addObject(new Ricochet(WIDTH/4, HEIGHT/3, ID.Ricochet3, handler));
  }

  public synchronized void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public synchronized void stop() {
    try {
      thread.join();
      running = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 100.0;
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
      if (running)
        render();
      frames++;

      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
//        System.out.println("FPS: " + frames);
        frames = 0;
      }
    }
    stop();
  }

  private void tick() {
    handler.tick();
//    hud.tick();
  }

  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();

    g.setColor(Color.black);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    handler.render(g);

//    hud.render(g);

    g.dispose();
    bs.show();
  }

  // set bounds for the agent's movement
  public static float clamp(float var, float min, float max) {
    if (var <= min)
      return var = min;
    else if (var >= max)
      return var = max;
    else
      return var;
  }

  public static void main(String[] args) {
    System.setProperty("sun.java2d.opengl", "true");
    new Game();
  }
}
