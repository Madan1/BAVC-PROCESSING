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
  
  void update(float _x, float _y)
  {
    px = x;
    py = y;
    x += (_x - x)*0.05;
    y += (_y - y)*0.05;
    angle = atan2(py-y,px-x);
    
    age++;
    //draw();
  }
  
  void draw()
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
      scale(.2,1); 
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
