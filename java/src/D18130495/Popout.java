package D18130495;

import processing.core.*;
import java.util.ArrayList;

public class Popout
{
    YushunVisual yv;

    ArrayList<Circle> circle1 = new ArrayList<Circle>();
    ArrayList<Circle> circle2 = new ArrayList<Circle>();
    ArrayList<Circle> circle3 = new ArrayList<Circle>();
    ArrayList<Circle> circle4 = new ArrayList<Circle>();

    public Popout(YushunVisual yv) {
        this.yv = yv;
    }

    public void render()
    {
        yv.getFFT().forward(yv.getAudioPlayer().mix); //load the audio to the fft
        yv.pushMatrix();
        yv.setBands(yv.getFFT().getSpectrumReal()); //load the fft to bands

        //create circle from four different direction and add them to four arraylists
        for(int i = 0; i < 1; i++) {
            if(yv.getBands()[i] != 0) {
                Circle c1 = new Circle(yv.width / 2, 0, (int)yv.random(yv.getBands()[i], yv.getBands()[i] * 1.2f));
                circle1.add(c1);
                Circle c2 = new Circle(yv.width / 2, yv.height, (int)yv.random(yv.getBands()[i], yv.getBands()[i] * 1.2f));
                circle2.add(c2);
                Circle c3 = new Circle(0, yv.height / 2, (int)yv.random(yv.getBands()[i], yv.getBands()[i] * 1.2f));
                circle3.add(c3);
                Circle c4 = new Circle(yv.width, yv.height / 2, (int)yv.random(yv.getBands()[i], yv.getBands()[i] * 1.2f));
                circle4.add(c4);
            }
            
            //update circles X and Y, make it move
            for(int j = 0; j < circle1.size(); j++) {
                yv.noStroke();
                yv.fill(113, yv.random(yv.getBands()[1], 200), 174);
                circle1.get(j).update1();
                yv.ellipse(circle1.get(j).getX(), circle1.get(j).getY(), circle1.get(j).getRadius(), circle1.get(j).getRadius());
                circle2.get(j).update2();
                yv.ellipse(circle2.get(j).getX(), circle2.get(j).getY(), circle2.get(j).getRadius(), circle2.get(j).getRadius());
                circle3.get(j).update3();
                yv.ellipse(circle3.get(j).getX(), circle3.get(j).getY(), circle3.get(j).getRadius(), circle3.get(j).getRadius());
                circle4.get(j).update4();
                yv.ellipse(circle4.get(j).getX(), circle4.get(j).getY(), circle4.get(j).getRadius(), circle4.get(j).getRadius());
            }
        }
        yv.popMatrix();
    }
}