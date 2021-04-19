package D18130495;

import processing.core.PApplet;

public class Control extends PApplet {

    public int controlNum = 0;

    public int getControlNum() {
        return controlNum;
    }

    public void setControlNum(int controlNum) {
        this.controlNum = controlNum;
    }

    YushunVisual visual = new YushunVisual();

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
            println("Mouse 1");
            this.visual.setWhich(1);
        }
		else if(mouseY < map(2, 0, 5, 0, height)) {
            println("Mouse 2");
            this.visual.setWhich(2);
        }
        else if(mouseY < map(3, 0, 5, 0, height)) {
            setControlNum(3);
            println("Mouse 3");
        }
        else if(mouseY < map(4, 0, 5, 0, height)) {
            setControlNum(4);
            println("Mouse 4");
        }
        else if(mouseY < map(5, 0, 5, 0, height)) {
            setControlNum(5);
            println("Mouse 5");
        }	
	}

    public void draw() {
        background(255, 100, 0);
        drawGrid();
    }

    public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch(a, this.visual);		
	}
}