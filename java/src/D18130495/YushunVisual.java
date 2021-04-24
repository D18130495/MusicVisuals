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
        loadAudio("music.mp3");
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

    public void wai(int x, int y, int num, int br, int ro) {
        beginShape();
        stroke(random(100, 220), random(150, 180), 200, 90);

        for (int d = 0; d < num; d++) {
        float ping = cos(radians(millis()/10+20*d));
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
                getFFT().forward(getAudioPlayer().mix);
                pushMatrix();
                translate(width/2, height/2);
                setBands(getFFT().getSpectrumReal());


                popMatrix();

                for (int d = 120; d <= 540; d +=60) {
                    int vertexx = (int)map(d, 0, 300, 3, 15);
                    wai(width/2, height/2, vertexx, d, d/15);
                }
            break;
            }
            case 2:
            {
                getFFT().forward(getAudioPlayer().mix);
                pushMatrix();
                translate(width/2, height/2);
                setBands(getFFT().getSpectrumReal());

                float angle = 0;
                float cir = 180;
                
                float ang = 0;
                int thick = 15;

                for (int i = 0; i < 360; i++) {
                float dist = getBands()[i] / 2;
                lerpedBuffer[i] = lerp(lerpedBuffer[i], getBands()[i], 0.1f);
                noStroke();
                fill(random(dist, 200), random(dist, 255), 200);
                ellipse(sin(angle) * (cir + lerpedBuffer[i]), -cos(angle) * (cir + dist), abs(lerpedBuffer[i] * 4), abs(lerpedBuffer[i] * 4));
                angle += PI / 20;
                }
                
                for (int j = 0; j < 360; j++) {
                float dist = getBands()[j];
                stroke(random(dist, 200), random(dist, 150), 200, 180);
                strokeWeight(3);
                line(-cos(ang) * (cir + dist + thick), sin(ang) * (cir + dist + thick), -cos(ang) * (cir - dist - thick), sin(ang) * (cir - dist - thick));
                ang += PI / 40;
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