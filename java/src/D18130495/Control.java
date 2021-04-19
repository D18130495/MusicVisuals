package D18130495;

import processing.core.PApplet;

public class Control extends PApplet {

    int controlNum = 0;
    
    public int getControlNum() {
        return controlNum;
    }

    public void setControlNum(int controlNum) {
        this.controlNum = controlNum;
    }

    public void settings() {
        size(128, 512);
    }

    public void setup() {
       colorMode(RGB);
    }

    void drawGrid()
    {
        for(int x = 1 ; x <= 4; x++)
        {
            float lh = map(x, 0, 5, 0, height);
            line(0, lh, width, lh); 
        }
    }

    public void mousePressed()
	{
        if(mouseY < map(1, 0, 5, 0, height)) {
            controlNum = 1;
            println("Mouse 1");
        }
		else if(mouseY < map(2, 0, 5, 0, height)) {
            controlNum = 2;
            println("Mouse 2");
        }
        else if(mouseY < map(3, 0, 5, 0, height)) {
            controlNum = 3;
            println("Mouse 3");
        }
        else if(mouseY < map(4, 0, 5, 0, height)) {
            controlNum = 4;
            println("Mouse 4");
        }
        else if(mouseY < map(5, 0, 5, 0, height)) {
            controlNum = 5;
            println("Mouse 5");
        }	
	}

    public void draw() {
        background(255, 100, 0);
        drawGrid();
    }
}