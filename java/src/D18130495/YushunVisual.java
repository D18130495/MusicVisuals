package D18130495;

import ddf.minim.*;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import processing.core.PApplet;
import ddf.minim.analysis.*;
import java.util.ArrayList;
import ie.tudublin.Visual;

public class YushunVisual extends Visual {

    ArrayList<Circle> circle1 = new ArrayList<Circle>();
    ArrayList<Circle> circle2 = new ArrayList<Circle>();
    ArrayList<Circle> circle3 = new ArrayList<Circle>();
    ArrayList<Circle> circle4 = new ArrayList<Circle>();
    float[] lerpedBuffer;

    public void settings() {
        size(1024, 640);
    }

    float y = 200;
    float lerpedY = y;
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

    //Background for first visual
    public void dance1(int x, int y, int num, int br, int ro) {
        beginShape();
        for (int d = 0; d < num; d++) {
            stroke(100, 150, 220, 110);
            float ping = cos(radians(millis()/10 + 20 * d));
            float r = br + map(ping, -1, 1, -ro, ro);
            int p = (int)map(abs(getBands()[d]), 0, 1, 3, 3.1f);
            strokeWeight(p);
            fill(random(100, 200), random(100, 200), 200, 4);
            vertex(x + cos(radians(d * 360 / num)) * r * 1.1f, y + sin(radians(d * 360 / num)) * r * 1.1f);
        }
        endShape(CLOSE);
    }

    float lerpedAverage = 0;

    public void draw() {
        backgroundImage();
        stroke(255);
        float halfHeight = height / 2;
        float average = 0;
        float sum = 0;
        float lastX = width / 2, lastY = height / 2;

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
                //Draw the background
                translate(width/2, height/2);
                getFFT().forward(getAudioPlayer().mix);
                pushMatrix();
                setBands(getFFT().getSpectrumReal());
                for (int d = 120; d <= 540; d += 60) {
                    int vertex1 = (int)map(d, 0, 300, 3, 15);
                    dance1(0, 0, vertex1, d, d / 15);
                }
                
                //Two-channel dance, inner use left channel, outer use right channel
                int size = 150;
                int range = 130;
                
                for (int i = 0; i < getAudioPlayer().left.size(); i+=4){
                    stroke(color(255 - sin(map(i,0, getAudioPlayer().left.size(),0, 1) * PI) * 255, 0, sin(map(i,0, getAudioPlayer().left.size(),0, 1) * PI) * 255));
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], getBands()[i], 0.08f);
                    float r = map(i, 0, getAudioPlayer().left.size(), 0, 2 * PI);
                    float s = abs(getAudioPlayer().left.get(i))*range;
                    line(sin(r) * (size), cos(r) * (size), sin(r) * (s + size), cos(r) * (s + size));
                }

                size = 300;

                for (int i=0;i<getAudioPlayer().right.size();i+=4){
                    stroke(color(255 - sin(map(i,0, getAudioPlayer().right.size(),0, 1) * PI) * 255, 0, sin(map(i,0, getAudioPlayer().right.size(),0, 1) * PI) * 255));
                    float r = map(i, 0, getAudioPlayer().right.size(), 0, 2 * PI);
                    float s = abs(getAudioPlayer().right.get(i))*range;
                    line(sin(r) * (size), cos(r) * (size), sin(r) * (s + size), cos(r) * (s + size));
                  }
                popMatrix();

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
                for(int i = 0; i < 1; i++) {
                    Circle c1 = new Circle(width / 2, 0, (int)random(5, 15));
                    circle1.add(c1);
                    Circle c2 = new Circle(width / 2, height, (int)random(5, 15));
                    circle2.add(c2);
                    Circle c3 = new Circle(0, height / 2, (int)random(5, 15));
                    circle3.add(c3);
                    Circle c4 = new Circle(width, height / 2, (int)random(5, 15));
                    circle4.add(c4);
                    // noStroke();
                    // fill(113, 99, 174);
                    // ellipse(c.getX(), c.getY(), c.getRadius(), c.getRadius());
                    for(int j = 0; j < circle1.size(); j++) {
                        noStroke();
                        fill(113, 99, 174);
                        circle1.get(j).update1();
                        ellipse(circle1.get(j).getX(), circle1.get(j).getY(), circle1.get(j).getRadius(), circle1.get(j).getRadius());
                        circle2.get(j).update2();
                        ellipse(circle2.get(j).getX(), circle2.get(j).getY(), circle2.get(j).getRadius(), circle2.get(j).getRadius());
                        circle3.get(j).update3();
                        ellipse(circle3.get(j).getX(), circle3.get(j).getY(), circle3.get(j).getRadius(), circle3.get(j).getRadius());
                        circle4.get(j).update4();
                        ellipse(circle4.get(j).getX(), circle4.get(j).getY(), circle4.get(j).getRadius(), circle4.get(j).getRadius());
                    }
                }
                break;
            }
            case 4:
            {
                for (int i = 0; i < getAudioBuffer().size() - 1; i++) {

                    stroke(i / 5, 255, 255);
                
                    strokeWeight(abs(getAudioPlayer().left.get(i)*20));
                
                    line(i, 200+getAudioPlayer().left.get(i)*50, i+1, 200+getAudioPlayer().left.get(i+1)*50);
                
                    line(i, 400+getAudioPlayer().right.get(i)*50, i+1, 400+getAudioPlayer().right.get(i+1)*50);
                  }
                break;
            }
        }
    }
} 