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

    //use for the second visual the smooth the shape
    float[] lerpedBuffer;
    float[] lerpedBuffer2;

    //create four visuals
    TwoChannelDance tcd = new TwoChannelDance(this);
    Draw dr = new Draw(this);
    Popout po = new Popout(this);
    Rain ra = new Rain(this);

    //use for switch the visuals
    public int which = 0;

    public int getWhich() {
        return which;
    }

    public void setWhich(int which) {
        this.which = which;
    }
    
    public void settings() {
        size(1024, 640);
    }    

    public void setup() {
        colorMode(RGB);
        startMinim();
        loadAudio("music.mp3");
        frameRate(60);
        smooth();
        lerpedBuffer = new float[width];
        lerpedBuffer2 = new float[width];

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
                ra.render();
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