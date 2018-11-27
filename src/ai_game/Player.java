package ai_game;

import java.awt.*;
import java.util.Iterator;

public class Player extends GameObject {

//  Random r = new Random();
  Handler handler;
  GameObject goal;
  GameObject ricochet;
  GameObject ricochet2;
  GameObject ricochet3;
//  Ricochet ricochet;
  GameObject smartenemy;
  Color color = new Color(75, 0, 130);

  public Player(float x, float y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;

    // to id goal:
    for (int i = 0; i < handler.object.size(); i++) {
      if (handler.object.get(i).getId() == ID.Goal)
        goal = handler.object.get(i);
    }

//    Iterator<GameObject> iterator = handler.object.iterator();
//    while(iterator.hasNext()) {
//      if (handler.object.iterator().next().getId() == ID.Goal) {
//        goal = iterator.next();
//      } else if(handler.object.iterator().next().getId() == ID.Ricochet) {
//        ricochet = iterator.next();
//      } else if(handler.object.iterator().next().getId() == ID.SmartEnemy) {
//        smartenemy = iterator.next();
//      }
//    }

//    // to id ricochet:
//    for (int i = 0; i < handler.object.size(); i++) {
//      if (handler.object.get(i).getId() == ID.Ricochet)
//        ricochet = handler.object.get(i);
//    }
//
////    // to id smartenemy:
//    for (int i = 0; i < handler.object.size(); i++) {
//      if (handler.object.get(i).getId() == ID.SmartEnemy)
//        smartenemy = handler.object.get(i);
//    }
  }

  // for collision detection of goal
  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 48, 48);
//    return new Rectangle((int)x - 16, (int)y - 16, 200, 200);
  }

  // for collision detection of enemies
  public Rectangle getTooCloseBounds() {
    return new Rectangle((int) x, (int) y, 48, 48);
//    return new Rectangle((int)x - 75, (int)y - 80, 200, 200);
//    return new Rectangle((int)x-25, (int)y-30, 100, 100);
  }

  public void tick() {
    x += velX;
    y += velY;

    // **** agent to goal AI:
    float diffX = x - goal.getX() - 0.5f;
    float diffY = y - goal.getY() - 0.5f;
    float distance = (float) Math.sqrt((x - goal.getX())
                                     * (x - goal.getX())
                                     + (y - goal.getY())
                                     * (y - goal.getY()));

    velX = ((-1 / distance) * diffX);
    velY = ((-1 / distance) * diffY);
    // ****

    x = Game.clamp(x, 0, Game.WIDTH - 48);
    y = Game.clamp(y, 0, Game.HEIGHT - 70);

    // trails:
//    handler.addObject(new Trail(x, y, ID.Trail, color, 48, 48, 0.04f, handler));

    tooClose();
//    tooClose2();
  }

  // avoid ricochet:
  public void tooClose() {

    x += velX;
    y += velY;

    ricochet = handler.object.get(2);
    if (getTooCloseBounds().intersects(ricochet.getBounds())) {
      // too close action:
      float diffX = x - ricochet.getX() - 1f;
      float diffY = y - ricochet.getY() - 1f;
      float distance = (float) Math.sqrt((x - ricochet.getX())
                                       * (x - ricochet.getX())
                                       + (y - ricochet.getY())
                                       * (y - ricochet.getY()));

      velX = ((3 / distance) * diffX);
      velY = ((3 / distance) * diffY);
      this.setVelX(velX);
      this.setVelX(velY);
    }

    ricochet2 = handler.object.get(3);
    if (getTooCloseBounds().intersects(ricochet2.getBounds())) {
      // too close action:
      float diffX = x - ricochet2.getX() - 1f;
      float diffY = y - ricochet2.getY() - 1f;
      float distance = (float) Math.sqrt((x - ricochet2.getX())
                                       * (x - ricochet2.getX())
                                       + (y - ricochet2.getY())
                                       * (y - ricochet2.getY()));

      velX = ((3 / distance) * diffX);
      velY = ((3 / distance) * diffY);
      this.setVelX(velX);
      this.setVelX(velY);
    }

    ricochet3 = handler.object.get(4);
    if (getTooCloseBounds().intersects(ricochet3.getBounds())) {
      // too close action:
      float diffX = x - ricochet3.getX() - 1f;
      float diffY = y - ricochet3.getY() - 1f;
      float distance = (float) Math.sqrt((x - ricochet3.getX())
                                       * (x - ricochet3.getX())
                                       + (y - ricochet3.getY())
                                       * (y - ricochet3.getY()));

      velX = ((3 / distance) * diffX);
      velY = ((3 / distance) * diffY);
      this.setVelX(velX);
      this.setVelX(velY);
    }

//    smartenemy = handler.object.get(0);
//    if (getTooCloseBounds().intersects(smartenemy.getBounds())) {
//      // too close action:
//      float diffX = x - smartenemy.getX() - 0.5f;
//      float diffY = y - smartenemy.getY() - 0.5f;
//      float distance = (float) Math.sqrt((x - smartenemy.getX()) * (x - smartenemy.getX())
//                                       + (y - smartenemy.getY()) * (y - smartenemy.getY()));
//
//      velX = ((1 / distance) * diffX);
//      velY = ((1 / distance) * diffY);
//    }

//    smartenemy = handler.object.get(0);
//    if (getTooCloseBounds().intersects(smartenemy.getBounds())) {
//      // too close action:
//      float diffX = x - smartenemy.getX() - 0.5f;
//      float diffY = y - smartenemy.getY() - 0.5f;
//      float distance = (float) Math.sqrt((x - smartenemy.getX()) * (x - smartenemy.getX())
//                                       + (y - smartenemy.getY()) * (y - smartenemy.getY()));
//
//      velX = ((1 / distance) * diffX);
//      velY = ((1 / distance) * diffY);
//    }

//    for (int i = 0; i < handler.object.size(); i++) {
//      GameObject tempObject = handler.object.get(i);
//      if (tempObject.getId() == ID.Ricochet) { // tempObject is now Ricochet
//        if (getBounds().intersects(tempObject.getBounds())) {
//          // too close action:
//          float diffX = x - ricochet.getX() - 0.5f;
//          float diffY = y - ricochet.getY() - 0.5f;
//          float distance = (float) Math.sqrt((x - ricochet.getX()) * (x - ricochet.getX())
//                                           + (y - ricochet.getY()) * (y - ricochet.getY()));
//
//          velX = ((1 / distance) * diffX);
//          velY = ((1 / distance) * diffY);
//        }
//      }
//    } // end Ricochet reaction

//    for (int i = 0; i < handler.object.size(); i++) {
//      GameObject tempObject = handler.object.get(i);
//      if (tempObject.getId() == ID.SmartEnemy) { // tempObject is now SmartEnemy
//        if (getBounds().intersects(tempObject.getBounds())) {
//          // too close action:
//          float diffX = x - smartenemy.getX() - 0.5f;
//          float diffY = y - smartenemy.getY() - 0.5f;
//          float distance = (float) Math.sqrt((x - smartenemy.getX()) * (x - smartenemy.getX())
//                                           + (y - smartenemy.getY()) * (y - smartenemy.getY()));
//
//          velX = ((1 / distance) * diffX);
//          velY = ((1 / distance) * diffY);
//        }
//      }
//    } // end SmartEnemy reaction
  }

  // avoid smartenemy AI:
  public void tooClose2() {
    x += velX;
    y += velY;

    smartenemy = handler.object.get(0);
    if (getTooCloseBounds().intersects(smartenemy.getBounds())) {
      // too close action:
      float diffX = x - smartenemy.getX() - 1f;
      float diffY = y - smartenemy.getY() - 1f;
      float distance = (float) Math.sqrt((x - smartenemy.getX()) * (x - smartenemy.getX())
                                       + (y - smartenemy.getY()) * (y - smartenemy.getY()));

      velX = ((1 / distance) * diffX);
      velY = ((1 / distance) * diffY);
      this.setVelX(velX);
      this.setVelX(velY);
    }
  }

//  public void collision() {
//    for (int i = 0; i < handler.object.size(); i++) {
//      GameObject tempObject = handler.object.get(i);
//      if (tempObject.getId() == ID.Goal) { // tempObject is now BasicEnemy
//        if (getBounds().intersects(tempObject.getBounds())) {
//          // collision code:
////          HeadsUpDisplay.HEALTH -= 2;
//          handler.removeObject(tempObject);
////          handler.addObject(new Player(this.getX(), this.getY(), ID.Player, handler));
//        }
//      }
//    }
//  }

  public void render(Graphics g) {
    // show bounding box for collision detection:
    Graphics2D g2d = (Graphics2D) g;
    g.setColor(Color.yellow);
//    g2d.draw(getTooCloseBounds());

    g.setColor(color);
    g.fillOval((int) x,(int) y, 48, 48);
  }
}
