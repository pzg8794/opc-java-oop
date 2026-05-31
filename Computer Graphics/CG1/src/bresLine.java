import java.awt.Graphics2D;

public class bresLine {

private float xStart, yStart, xEnd, yEnd;

//sets start point
public void setStartPoint(float x, float y) {
this.xStart = x;
this.yStart = y;
}

//sets end point
public void setEndPoint(float x, float y) {
this.xEnd = x;
this.yEnd = y;
}

//draw on fractal panel
public void drawBresenham(Graphics2D f2Buffer) {

float dy = yEnd - yStart;
float dx = xEnd - xStart;
int stepx, stepy;

if (dy < 0) {
dy = -dy;
stepy = -1;
} else {
stepy = 1;
}
if (dx < 0) {
dx = -dx;
stepx = -1;
} else {
stepx = 1;
}
dy = dy * 2;
dx = dx * 2;

f2Buffer.drawLine((int) xStart, (int) yStart, (int) xStart, (int) yStart);
if (dx > dy) {
float fraction = (2 * dy) - dx;
while (xStart != xEnd) {
if (fraction >= 0) {
yStart += stepy;
fraction -= dx; // same as fraction -= 2*dx
}
xStart += stepx;
fraction += dy; // same as fraction -= 2*dy
f2Buffer.drawLine((int) xStart, (int) yStart, (int) xStart, (int) yStart);
}
} else {
float fraction = (2 * dx) - dy;
while (yStart != yEnd) {
if (fraction >= 0) {
xStart += stepx;
fraction -= dy;
}
yStart += stepy;
fraction += dx;
f2Buffer.drawLine((int) xStart, (int) yStart, (int) xStart, (int) yStart);
}
}

}
}