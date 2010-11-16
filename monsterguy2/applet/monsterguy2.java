import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class monsterguy2 extends PApplet {

/*
    Copyright (C) <year>  Madan Rana

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/

BodyPart parts[]; 
int num_of_BodyPart = 120;
int clickX = width;
int clickY = height;
float target = num_of_BodyPart-1;

BlinkingEye eye1;

public void setup()
{
  size(800,500);
  frameRate(25);
  smooth();
  background(5, 204, 0);
  
  
  parts = new BodyPart[num_of_BodyPart];
  for(int i=0;i<num_of_BodyPart;i++)
  {
    parts[i] = new BodyPart(width,height, (num_of_BodyPart - i)/4 + 10);
  }
  eye1 = new BlinkingEye(width,height);
}

public void mouseDragged() {
  clickX = mouseX;
  clickY = mouseY;
  target = num_of_BodyPart-1;
}

public void draw()
{
  background(125, 150, 10);
  noStroke();
  
  if (mousePressed == false) {
    if ( (abs(eye1.x - clickX) < 5) && (abs(eye1.y - clickY) < 5)) {
      clickX = round(parts[round(target)].x);
      clickY = round(parts[round(target)].y);
      target = max(target-1, 0);
    }
  } 
    
  eye1.update(clickX ,clickY);
  for(int i=0;i<=round(target);i++)
  {
    if(i == 0)
    {
      parts[i].update(eye1.x,eye1.y);
    }
    else parts[i].update(parts[i-1].x,parts[i-1].y);
    
    parts[i].draw();
  }
  
  eye1.draw();
}

class BodyPart
{
  float x,y;
  float radius;
  
  BodyPart(float _x, float _y, float _radius)
  {
    x = _x;
    y = _y;
    radius = _radius;
  }
  
  public void update(float _x, float _y)
  {
    if(dist(x,y,_x,_y)> 5)
    {
      float d = dist(x,y,_x,_y);
      float angle = atan2((y-_y),(x-_x));
      
      x = _x + cos(angle) * 5;
      y = _y + sin(angle) * 5;
    }
    else
    {
      float d = dist(x,y,_x,_y);

      float angle = atan2((y-_y),(x-_x));
      
      x = _x + cos(angle) * d;
      y = _y + sin(angle) * d;
    }
    //draw();
  }
  
  public void draw()
  {
    fill(225);
    ellipse(x,y,radius+1,radius+1);
    fill(200, 0, 50);
    ellipse(x,y,radius,radius);
  }
}
class BlinkingEye
{
  float x,y,angle;
  float px,py;
  int age;
  
  BlinkingEye(float _x, float _y)
  {
    x = _x;
    y = _y;
    px = x;
    py = y;
    angle = atan2(py-y,px-x);
    age = 0;
  }
  
  public void update(float _x, float _y)
  {
    px = x;
    py = y;
    x += (_x - x)*0.05f;
    y += (_y - y)*0.05f;
    angle = atan2(py-y,px-x);
    
    age++;
    //draw();
  }
  
  public void draw()
  {
    pushMatrix();
    translate(x,y);
    rotate(angle);
    fill(5, 220, 10);
    ellipse(0,0,60,45);
    pushMatrix();
    translate(-8,0);
    if(age %100 > 95)
    {
      scale(.2f,1); 
    }
    fill(150, 0, 150);
    ellipse(0,-10,25,15);
    ellipse(0,10,25,15);
    fill(0, 225, 50);
    ellipse(50,-10,25,15);
    ellipse(50,10,25,15);
    popMatrix();
    fill(150);
    ellipse(-15,10,5,-5);
    ellipse(-15,-8,5,-5);
    popMatrix();
    }
  }
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "monsterguy2" });
  }
}
