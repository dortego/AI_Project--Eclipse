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
  
//  public Ellipse2D getBounds() {
//    return new Ellipse2D() {
//      @Override
//      public double getX() {
//        return (int)x + 4;
//      }
//
//      @Override
//      public double getY() {
//        return (int)y + 4;
//      }
//
//      @Override
//      public double getWidth() {
//        return 40;
//      }
//
//      @Override
//      public double getHeight() {
//        return 40;
//      }
//
//      @Override
//      public boolean isEmpty() {
//        return false;
//      }
//
//      @Override
//      public void setFrame(double x, double y, double w, double h) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//      }
//
//      @Override
//      public Rectangle2D getBounds2D() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//      }
//    };
//  } 

  public void collision() {
    r = new SecureRandom();
    for (int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      if (tempObject.getId() == ID.Player) { // tempObject is now Player
        if (getBounds().intersects(tempObject.getBounds())) {
          count++;
          // collision action:
          if(count % 2 == 0) {
            this.setX(r.nextInt(WIDTH/2) - 15);
            this.setY(r.nextInt((WIDTH / 12 * 9) - 64));
            tempObject.tick();
//            System.out.println("right = true");
          } else {
            this.setX(r.nextInt(WIDTH/2) - 25 + (WIDTH/2));
            this.setY(r.nextInt((WIDTH / 12 * 9) - 64));
            tempObject.tick();
//            System.out.println("right = false");
          }
//          System.out.println(count);
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
//    g.fillRect((int)x, (int)y, 48, 48);
  }
}
