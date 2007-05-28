import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class Conway extends PApplet {/**
 * Conway's Game of Life 
 * by Mike Davis. 
 * 
 * This program is a simple version of Conway's 
 * game of Life.  A lit point turns off if there 
 * are fewer than two or more than three surrounding 
 * lit points.  An unlit point turns on if there 
 * are exactly three lit neighbors.  The 'density' 
 * parameter determines how much of the board will 
 * start out lit.  
 * 
 * Created 3 August 2002
 */
 
int sx, sy; 
float density = 0.5f; 
int[][][] world;
 
public void setup() 
{ 
  size(200, 200);
  frameRate(12);
  sx = width;
  sy = height;
  world = new int[sx][sy][2]; 
  stroke(255); 
   
  // Set random cells to 'on' 
  for (int i = 0; i < sx * sy * density; i++) { 
    world[(int)random(sx)][(int)random(sy)][1] = 1; 
  } 
} 
 
public void draw() 
{ 
  background(0); 
  
  // Drawing and update cycle 
  for (int x = 0; x < sx; x=x+1) { 
    for (int y = 0; y < sy; y=y+1) { 
      //if (world[x][y][1] == 1) 
      // Change recommended by The.Lucky.Mutt
      if ((world[x][y][1] == 1) || (world[x][y][1] == 0 && world[x][y][0] == 1)) 
      { 
        world[x][y][0] = 1; 
        point(x, y); 
      } 
      if (world[x][y][1] == -1) 
      { 
        world[x][y][0] = 0; 
      } 
      world[x][y][1] = 0; 
    } 
  } 
  // Birth and death cycle 
  for (int x = 0; x < sx; x=x+1) { 
    for (int y = 0; y < sy; y=y+1) { 
      int count = neighbors(x, y); 
      if (count == 3 && world[x][y][0] == 0) 
      { 
        world[x][y][1] = 1; 
      } 
      if ((count < 2 || count > 3) && world[x][y][0] == 1) 
     { 
        world[x][y][1] = -1; 
      } 
    } 
  } 
} 
 
// Count the number of adjacent cells 'on' 
public int neighbors(int x, int y) 
{ 
  return world[(x + 1) % sx][y][0] + 
         world[x][(y + 1) % sy][0] + 
         world[(x + sx - 1) % sx][y][0] + 
         world[x][(y + sy - 1) % sy][0] + 
         world[(x + 1) % sx][(y + 1) % sy][0] + 
         world[(x + sx - 1) % sx][(y + 1) % sy][0] + 
         world[(x + sx - 1) % sx][(y + sy - 1) % sy][0] + 
         world[(x + 1) % sx][(y + sy - 1) % sy][0]; 
} 
static public void main(String args[]) {   PApplet.main(new String[] { "Conway" });}}