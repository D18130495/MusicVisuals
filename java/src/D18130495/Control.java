package D18130495;

import processing.core.PApplet;

public class Control extends PApplet {

    public int controlNum = 0;

    YushunVisual visual = new YushunVisual();

    public void settings() {
        size(156, 512);
    }

    public void setup() {
       colorMode(RGB);
    }

    void drawGrid()
    {
        float lh;
        stroke(128, 128, 128); //gray dividing line
        textAlign(CENTER, CENTER);
        for(int x = 1 ; x <= 4; x++)
        {
            lh = map(x, 0, 5, 0, height);
            line(0, lh, width, lh); 
        }
        textSize(16);
        fill(255, 255, 255);
        text("Start Visual", width / 2, height / 10);
        text("Start Visual", width / 2, height / 10 * 3);
        text("Start Visual", width / 2, height / 10 * 5);
        text("Start Visual", width / 2, height / 10 * 7);
        text("Start Visual", width / 2, height / 10 * 9);
    }

    public void mousePressed()
	{
        if(mouseY < map(1, 0, 5, 0, height)) {
            this.visual.keyPressed();
        }
		else if(mouseY < map(2, 0, 5, 0, height)) {
            this.visual.setWhich(0);
        }
        else if(mouseY < map(3, 0, 5, 0, height)) {
            this.visual.setWhich(1);
        }
        else if(mouseY < map(4, 0, 5, 0, height)) {
            this.visual.setWhich(2);
        }
        else if(mouseY < map(5, 0, 5, 0, height)) {
            this.visual.setWhich(3);
        }	
	}

    public void draw() {
        background(0); //black background
        drawGrid();
    }

    //call the YushunVisual and run it
    public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch(a, this.visual);		
	}
}