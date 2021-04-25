package D18130495;

import ddf.minim.*;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import processing.core.PApplet;
import ddf.minim.analysis.*;
import ie.tudublin.Visual;

public class YushunVisual extends Visual {

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
                lerpedBuffer[i] = lerp(lerpedBuffer[i], getBands()[i], 0.08f);
                noStroke();
                fill(random(dist, 200), random(dist, 255), 200);
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

                //Ring
                for (int x = 0; x < 60; x += 5) {
                float dist = getBands()[x];
                float position = random(0, width);
                stroke(random(dist, 200), random(dist, 150), 200, 180);
                strokeWeight(3);
                line(position - width / 2, -(height / 2), position - width / 2, x + 20 - height / 2);
                }

                popMatrix();

                break;
            }
            case 3:
            {
                for (int i = 0; i < getAudioBuffer().size(); i++) {
                    strokeWeight(1);
                    float c = map(i, 0, getAudioBuffer().size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], getAudioBuffer().get(i), 0.1f);
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, i, halfHeight + lerpedBuffer[i] * halfHeight * 4);
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