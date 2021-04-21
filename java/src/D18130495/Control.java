package D18130495;

import processing.core.*;

public class Control extends PApplet {

    int count;
    int rectLh = 20;
    public int controlNum = 0; //use to display the menu text colour

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
            if(count == 0) {
                controlNum = 1;
            }
            count++;
        }
		else if(mouseY < map(2, 0, 5, 0, height)) {
            this.visual.setWhich(1);
            controlNum = 1;
        }
        else if(mouseY < map(3, 0, 5, 0, height)) {
            this.visual.setWhich(2);
            controlNum = 2;
        }
        else if(mouseY < map(4, 0, 5, 0, height)) {
            this.visual.setWhich(3);
            controlNum = 3;
        }
        else if(mouseY < map(5, 0, 5, 0, height)) {
            this.visual.setWhich(4);
            controlNum = 4;
        }	
	}

    public void mouseDragged() 
    {
        if(mouseY <= 333 && mouseY >= 308)
            if(mouseX <= (rectLh + 20) && mouseX >= (rectLh - 20))
                rectLh = mouseX + 1;
    } 

    //use to indicate which options have been chosen
    public void menuColour() {
        if(controlNum == 1) {
            fill(255, 0, 0);
            text("Start Visual", width / 2, height / 10 * 3);
        }
        else if(controlNum == 2) {
            fill(255, 0, 0);
            text("Start Visual", width / 2, height / 10 * 5);
        }
        else if(controlNum == 3) {
            fill(255, 0, 0);
            text("Start Visual", width / 2, height / 10 * 7);
        }
        else if(controlNum == 4) {
            fill(255, 0, 0);
            text("Start Visual", width / 2, height / 10 * 9);
        }
    }

    public void controlBar() {
        noStroke();
        fill(255, 255, 255);
        rect(0, 308, rectLh, 25, 0, 7, 7, 0);
    }

    public void draw() {
        background(0); //black background
        drawGrid();
        menuColour();
        controlBar();
    }

    //call the YushunVisual and run it
    public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch(a, this.visual);		
	}
}