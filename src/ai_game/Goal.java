package ai_game;

import static ai_game.Game.HEIGHT;
import static ai_game.Game.WIDTH;
import java.awt.*;
import java.security.SecureRandom;

public class Goal extends GameObject {
  
  private Handler handler;
  private SecureRandom r;
  private int count;
  Color color = new Color(255, 155, 0);
  
  public Goal(float x, float y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
  }
  
  // for collision detection
  public Rectangle getBounds() {
    return new Rectangle((int) x + 23, (int) y + 23, 1, 1);
  }  
 
  public void collision() {
    r = new SecureRandom();
    for (int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      if (tempObject.getId() == ID.Player) { // tempObject is now Player
        if (getBounds().intersects(tempObject.getBounds())) {
          count++;
          // collision action (regen on right or left side of window):
          if(count % 2 == 0) {
            this.setX(r.nextInt(WIDTH/2) - 15);
            this.setY(r.nextInt((WIDTH / 12 * 9) - 64));
            tempObject.tick();
          } else {
            this.setX(r.nextInt(WIDTH/2) - 25 + (WIDTH/2));
            this.setY(r.nextInt((WIDTH / 12 * 9) - 64));
            tempObject.tick();
          }
        }
      }
    }  
  }
  
  @Override
  public void tick() {
    collision();
  }

  @Override
  public void render(Graphics g) {
// show bounding box for collision detection:
//    Graphics2D g2d = (Graphics2D) g;
//    g.setColor(Color.white);
//    g2d.draw(getBounds());    
    
    g.setColor(color);
    g.fillOval((int)x, (int)y, 48, 48);
  }
}
