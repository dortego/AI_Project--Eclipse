package ai_game;

import java.awt.*;
import java.util.Iterator;

public class SmartEnemy extends GameObject {
  
  private Handler handler;
  private GameObject player;
  
  SmartEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    
    this.handler = handler;
    
//    player = handler.object.get(1);
    // to id agent
    for (int i = 0; i < handler.object.size(); i++) {
      if (handler.object.get(i).getId() == ID.Player)
        player = handler.object.get(i);
    }
  }
  
//  @Override
//  public Rectangle getBounds() {
//    return new Rectangle((int)x, (int)y, 32, 32);
//  } 

  @Override
  public Rectangle getBounds() {
//    return new Rectangle((int)x-80, (int)y-80, 200, 200);
//    return new Rectangle((int)x-35, (int)y-35, 100, 100);
    return new Rectangle((int)x, (int)y, 32, 32);
  } 
  
  @Override
  public void tick() {
    x += velX;
    y += velY;
    
    float diffX = x - player.getX() - 0.8f;
    float diffY = y - player.getY() - 0.8f;
    float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) 
                                     + (y - player.getY()) * (y - player.getY()));
    
    velX = ((-1 / distance) * diffX) ;
    velY = ((-1 / distance) * diffY) ;
    
//    if (x <= 0 || x >= Game.WIDTH - 32)
//      velX = -velX;
//    if (y <= 0 || y >= Game.HEIGHT - 32)
//      velY = -velY;
    
    handler.addObject(new Trail(x, y, ID.Trail, Color.green, 32, 32, 0.02f, handler));
  }

  @Override
  public void render(Graphics g) {
    // show bounding box for collision detection:
    Graphics2D g2d = (Graphics2D) g;
    g.setColor(Color.yellow);
    g2d.draw(getBounds());
    
    g.setColor(Color.green);
    g.fillOval((int)x, (int)y, 32, 32);
//    g.fillRect((int)x, (int)y, 32, 32);
  }
}
