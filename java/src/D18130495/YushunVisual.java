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
                    ellipse(sin(angle) * (cir + lerpedBuffer[i]), -cos(angle) * (cir + dist), lerpedBuffer[i] * 5, lerpedBuffer[i] * 5);
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

// for (int i = 0; i < ab.size(); i++) {
//     float c = map(i, 0, ab.size(), 0, 255);
//     stroke(c, 255, 255);
//     //println(lerpedBuffer[i]);
//     lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
//     line(0, i, lerpedBuffer[i] * halfHeight * 8, i);
//     line(i, 0, i, lerpedBuffer[i] * halfHeight * 8);
//     line(width, i, width - (lerpedBuffer[i] * halfHeight * 8), i);
//     line(i, height, i, height - (lerpedBuffer[i] * halfHeight * 8));
// }
                // for (int i = 0; i < ab.size(); i++) {
                //     sum = sum + abs(ab.get(i));
                //     average = sum / (float)ab.size();
                //     float c = map(i, 0, ab.size(), 0, 255);
                //     stroke(c, 255, 255);
                //     noFill();
                //     //println(lerpedBuffer[i]);
                //     lerpedBuffer[i] = lerp(lerpedBuffer[i], average, 0.1f);
                //     ellipse(width / 2, height / 2, lerpedBuffer[i] * 2000, lerpedBuffer[i] * 2000);
                // }
//            average = sum / (float)ab.size();
//            lerpedAverage = lerp(lerpedAverage, average, 0.1f);
//            noStroke();
//            ellipse(width / 2, 100, lerpedAverage * 500, lerpedAverage * 500);

//            ellipse(200, y, 30, 30);
//            ellipse(300, lerpedY, 30, 30);
//            y += random(-10, 10);
//            lerpedY = lerp(lerpedY, y, 0.1f);
    }
} 