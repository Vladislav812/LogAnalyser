import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static java.lang.Math.*;

public class DrawingSpots extends JFrame {
  double multiplicator = 1;
  double frameX = multiplicator * 22 * 3 * 4, frameY = multiplicator * 22 * 4 * 4;
  ArrayList<Spot> spots;

  public DrawingSpots(ArrayList<Spot> spots) {
    setTitle("Irradiation Session ID:");
    setSize(1000, 500);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.spots = spots;
  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    Rectangle2D.Double frame = new Rectangle2D.Double(0, 0, frameX, frameY);
    g2D.draw(frame);
    for (int i = 0; i < spots.size(); i++) {
      double spotX = frameX - multiplicator * spots.get(i).MSIC_X / 1000;
      double spotY = frameY - multiplicator * spots.get(i).MSIC_Y / 1000;
      double widthX = multiplicator * (spots.get(i).minusXBeamWidth + spots.get(i).plusXBeamWidth) / 1000;
      double widthY = multiplicator * (spots.get(i).minusYBeamWidth + spots.get(i).plusYBeamWidth) / 1000;

      g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      Ellipse2D.Double spot = new Ellipse2D.Double(spotX, spotY, widthX, widthY);
      g2D.draw(spot);
      g2D.setColor(getLayerColor(spots.get(i)));
      //g2D.fill(spot);



     // int ISOx =








      try {
        Thread.currentThread().sleep(Math.round(1000 * spots.get(i).irradiationDuration));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  Color getLayerColor(Spot spot) {
    Spot lastSpot = spots.get(spots.size() - 1);
    int layersQuantity = lastSpot.layer;
    int red = round(255 * (spot.layer - 1) / (layersQuantity));
    return new Color(red, 30, 30);
  }
}
