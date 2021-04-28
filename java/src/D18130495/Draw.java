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
        yv.getFFT().forward(yv.getAudioPlayer().mix);
        yv.setBands(yv.getFFT().getSpectrumReal());                
        yv.noStroke();
       
        for(int j = 0; j < yv.rect.size(); j++) {
            yv.fill(255);
            yv.rect(yv.rect.get(j).getX(), yv.rect.get(j).getY(), 3, 3);

            if(j == (yv.rect.size() - 1))
            {
                
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
        int p = (int)yv.random(0, yv.pixel.size());
        PVector r = yv.pixel.get(p);
        int x = (int)r.x;
        int y = (int)r.y;
        yv.rect.add(new Rect(x, y));
        yv.pixel.remove(p);
    }    
}