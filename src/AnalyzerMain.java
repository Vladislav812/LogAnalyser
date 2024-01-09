import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalyzerMain {
  public static void main(String[] args) throws IOException {
    JFrame window = new JFrame();
    FileDialog fileDialog = new FileDialog(window, "Choose a file", FileDialog.LOAD);
    fileDialog.setVisible(true);
    //fileDialog.setDirectory("C:\\");
    String fullName = fileDialog.getDirectory() + fileDialog.getFile();
    window.dispose();
    fileDialog.dispose();
    BufferedReader reader = new BufferedReader(new FileReader(fullName));
    ArrayList<Spot> spots = new ArrayList<>();
    int counter = 0;
    while (true) {
      String s = reader.readLine();
      counter++;
      if (counter < 6) continue;
      if (s == null) break;
      String[] str = s.split(",");
      Spot newSpot = new Spot();
      newSpot.layer = Integer.parseInt(str[0]);
      newSpot.energy = Double.parseDouble(str[1]);
      newSpot.ISO_X = Double.parseDouble(str[5]);
      newSpot.ISO_Y = Double.parseDouble(str[6]);
      newSpot.MSIC_X = Double.parseDouble(str[9]);
      newSpot.MSIC_Y = Double.parseDouble(str[10]);

      double minusXBeamWidth = Double.parseDouble(str[17]);
      newSpot.minusXBeamWidth = minusXBeamWidth < 10000 ? minusXBeamWidth : 4400;
      double plusXBeamWidth = Double.parseDouble(str[18]);
      newSpot.plusXBeamWidth = plusXBeamWidth < 10000 ? plusXBeamWidth : 4400;
      double minusYBeamWidth = Double.parseDouble(str[19]);
      newSpot.minusYBeamWidth = minusYBeamWidth < 10000 ? minusYBeamWidth : 3700;
      double plusYBeamWidth = Double.parseDouble(str[20]);
      newSpot.plusYBeamWidth = plusYBeamWidth < 10000 ? plusYBeamWidth : 3700;

      newSpot.irradiationDuration = Double.parseDouble(str[27]);
      spots.add(newSpot);
    }
    new DrawingSpots(spots);
    System.out.println();
  }
}
