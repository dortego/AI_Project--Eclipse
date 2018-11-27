package ai_game;

import java.awt.*;
import java.security.SecureRandom;

public class Ricochet extends GameObject{

  private Handler handler;
  public int counter;
  private SecureRandom r;

  public Ricochet(float x, float y, ID id, Handler handler) {
    super(x, y, id);

    this.handler = handler;

    r = new SecureRandom();

    velX = 1f + r.nextFloat() * 2;
    velY = 1f + r.nextFloat() * 2;

//     velX = 1f;
//     velY = 2f;
  }

  @Override
  // larger bounds than appearance to insure agent won't touch it
  public Rectangle getBounds() {
//    return new Rectangle((int)x-95, (int)y-100, 200, 200);
    return new Rectangle((int)x-45, (int)y-45, 100, 100);
//    return new Rectangle((int)x, (int)y, 16, 16);
//    return new Rectangle((int)x-8, (int)y-8, 32, 32);
//    return new Rectangle((int) x-16, (int) y-16, 48, 48);

  }

  @Override
  public void tick() {
    x += velX;
    y += velY;

    if (x >= Game.WIDTH - 20) {
    	  velX = -1 * (1f + r.nextFloat() * 2);
//      velX = -velX;
      System.out.println("velX: " + velX);
    }
    if (x <= 0) {
      velX = 1f + r.nextFloat() * 2;
      System.out.println("velX: " + velX);
    }
    if (y >= Game.HEIGHT - 32) {
      velY = -1 * (1f + r.nextFloat() * 2);
//      velY = -velY;
      System.out.println("velY: " + velY);
    }
    if (y <= 0) {
      velY = 1f + r.nextFloat() * 2;
      System.out.println("velY: " + velY);
    }

    handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 16, 16, 0.07f, handler));
  }

  @Override
  public void render(Graphics g) {
    // show bounding box for collision detection:
    Graphics2D g2d = (Graphics2D) g;
    g.setColor(Color.blue);
//    g2d.draw(getBounds());


    g.setColor(Color.RED);
    g.fillOval((int)x, (int)y, 16, 16);
  }
}
