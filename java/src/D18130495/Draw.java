package D18130495;

import processing.core.*;
import java.util.ArrayList;

public class Draw
{
    YushunVisual yv;

    public Draw(YushunVisual yv) {
        this.yv = yv;
    }

    public void render()
    {
        yv.getFFT().forward(yv.getAudioPlayer().mix); //load audio to fft
        yv.setBands(yv.getFFT().getSpectrumReal()); //load fft to band         
        yv.noStroke();
       
        for(int j = 0; j < yv.rect.size(); j++) {
            yv.fill(255);
            yv.rect(yv.rect.get(j).getX(), yv.rect.get(j).getY(), 3, 3); //draw the rectangle(pixel)

            if(j == (yv.rect.size() - 1)) //after draw all the rectangle from the buffer add new rectangle to the buffer
            {
                //Because the screen of my visual is 1024 X 640, so there are 655360 pixels on the screen.
                //If I want make the image more clear, I have to draw as many pixels as possible(up tp 655360)
                //The below if else statement use to speed up the drawing.
                //When j less than 1024 it will more clearly see how the process of drawing is synchronized to the music.

                if(j < 256 && yv.abs(yv.getBands()[1]) * 50 > 500) {
                    newRect();
                    yv.fill(255);
                    yv.ellipse(yv.rect.get(j).getX(), yv.rect.get(j).getY(), 20, 20);
                    break;
                } else if(j >= 256 && yv.abs(yv.getBands()[1]) * 50 > 400) {
                    newRect();
                    yv.fill(255);
                    yv.ellipse(yv.rect.get(j).getX(), yv.rect.get(j).getY(), 20, 20);
                    break;
                } else if(j >= 512 && yv.abs(yv.getBands()[1]) * 50 > 300) {
                    newRect();
                    yv.fill(255);
                    yv.ellipse(yv.rect.get(j).getX(), yv.rect.get(j).getY(), 20, 20);
                    break;
                } else if(j >= 1024 && yv.abs(yv.getBands()[1]) * 50 > 200) {
                    newRect();
                    yv.fill(255);
                    yv.ellipse(yv.rect.get(j).getX(), yv.rect.get(j).getY(), 20, 20);
                    break;
                } else if(j >= 2048 && yv.abs(yv.getBands()[1]) * 50 > 100) {
                    newRect();
                    yv.fill(255);
                    yv.ellipse(yv.rect.get(j).getX(), yv.rect.get(j).getY(), 20, 20);
                    break;
                } else if(j >= 3072 && yv.abs(yv.getBands()[1]) * 50 > 5) {
                    newRect();
                    newRect();
                    newRect();
                    newRect();
                    newRect();
                    newRect();
                    newRect();
                    newRect();
                    newRect();
                    yv.fill(255);
                    yv.ellipse(yv.rect.get(j).getX(), yv.rect.get(j).getY(), 20, 20);
                    break;
                }
            }
        }
    }

    public void newRect() {
        int p = (int)yv.random(0, yv.pixel.size()); //pick a random pixel from pixel arraylist
        PVector r = yv.pixel.get(p);
        int x = (int)r.x;
        int y = (int)r.y;
        yv.rect.add(new Rect(x, y)); //add this pixel to the rect arraylist as a new  rectangle
        yv.pixel.remove(p); //remove this pixel from pixel arraylist for not pick it again
    }    
}