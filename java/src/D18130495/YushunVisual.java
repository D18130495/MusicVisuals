package D18130495;

import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.*;
import java.util.ArrayList;
import ie.tudublin.Visual;


public class YushunVisual extends Visual {

    //this two arraylist use for the fourth visual
    ArrayList<Rect> rect = new ArrayList<Rect>();
    ArrayList<PVector> pixel = new ArrayList<PVector>();

    //this image is use of the fourth visual
    private PImage image;

    float[] lerpedBuffer;

    TwoChannelDance tcd = new TwoChannelDance(this);
    Draw dr = new Draw(this);
    Popout po = new Popout(this);

    public void settings() {
        size(1024, 640);
    }

    public int which = 0;

    public int getWhich() {
        return which;
    }

    public void setWhich(int which) {
        this.which = which;
    }

    public void setup() {
        colorMode(RGB);
        startMinim();
        loadAudio("music1.mp3");
        lerpedBuffer = new float[width];
        frameRate(60);
        smooth();

        //This is use for the fourth visual to parsing the image
        //Then store the pixel position in the pixel arraylist
        image = loadImage("1.png");
        for(int x = 0; x < image.width; x++)
        {
            for(int y = 0; y < image.height; y++)
            {
                int index = x + y * image.width;
                float  b = brightness(image.pixels[index]);
                if(b == 255) {
                    pixel.add(new PVector(x, y));
                }
            }
        }
        rect.add(new Rect(1, 1));
    }

    //use to pause the visual
    public void keyPressed() {
        if(getAudioPlayer().isPlaying()) {
            getAudioPlayer().pause();
        } else {
            getAudioPlayer().play();
            if(which == 0)
            {
                which = 1;
            }
        }
    }

    public void draw() {
        background(0);

        switch (which)
        {
            case 0:
            {
                textSize(30);
                text("Welcome to YushunVisual", 150, 200);
                text("Press 'Start Visual' to start journey", 150, 300);
                break;
            }
            case 1:
            {
                tcd.render();
                break;
            }
            case 2:
            {
                getFFT().forward(getAudioPlayer().mix);
                pushMatrix();
                translate(width/2, height/2);
                setBands(getFFT().getSpectrumReal());

                //Circle
                float angle = 0;
                float cir = 180;

                for (int i = 0; i < 360; i++) {
                    float dist = getBands()[i] / 2;
                    noStroke();
                    fill(random(dist, 200), random(dist, 255), 200);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], getBands()[i], 0.08f);
                    ellipse(sin(angle) * (cir + lerpedBuffer[i]), -cos(angle) * (cir + dist), abs(lerpedBuffer[i] * 4), abs(lerpedBuffer[i] * 4));
                    angle += PI / 20;
                }

                //Ring
                float ang = 0;
                int thick = 15;

                for (int j = 0; j < 360; j++) {
                    float dist = getBands()[j];
                    stroke(random(dist, 200), random(dist, 150), 200, 180);
                    strokeWeight(3);
                    line(-cos(ang) * (cir + dist + thick), sin(ang) * (cir + dist + thick), -cos(ang) * (cir - dist - thick), sin(ang) * (cir - dist - thick));
                    ang += PI / 40;
                }

                //top line
                for (int x = 0; x < 60; x += 5) {
                    float dist = getBands()[x];
                    float position = random(dist, width);
                    stroke(random(dist, 200), random(dist, 150), 200);
                    strokeWeight(5);
                    line(position - width / 2, -(height / 2), position - width / 2, dist * 2 + 40 - height / 2);
                }

                //bottom line
                for (int x = 0; x < 60; x += 5) {
                    float dist = getBands()[x];
                    float position = random(dist, width);
                    stroke(random(dist, 200), random(dist, 150), 200);
                    strokeWeight(5);
                    line(position - width / 2, height / 2, position - width / 2, -(dist * 2) - 40 + height / 2);
                }

                popMatrix();

                break;
            }
            case 3:
            {
                po.render();
                break;
            }
            case 4:
            {
                dr.render();
                break;
            }
        }
    }
} 