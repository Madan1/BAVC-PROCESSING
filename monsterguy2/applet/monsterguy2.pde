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

void setup()
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

void mouseDragged() {
  clickX = mouseX;
  clickY = mouseY;
  target = num_of_BodyPart-1;
}

void draw()
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

