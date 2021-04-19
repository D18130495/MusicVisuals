package D18130495;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class YushunVisual extends PApplet {

    Minim minim;
    AudioInput ai; //Connect to mic
    AudioBuffer ab; //Samples
    AudioPlayer ap;

    float[] lerpedBuffer;

    public void settings() {
        size(512, 512);
        //fullScreen(P3D, SPAN);
    }

    float y = 200;
    float lerpedY = y;
    public int which;

    public int getWhich() {
        return which;
    }

    public void setWhich(int which) {
        this.which = which;
    }

    public void setup() {
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        ap = minim.loadFile("heroplanet.mp3", width);
        ap.play();
        ab = ai.mix; //buffer to the mic
        ab = ap.mix; //buffer to the file
        colorMode(HSB);
        lerpedBuffer = new float[width];
    }

    // public void keyPressed() {
    //     if(keyCode >= '0' && keyCode <= '5') {
    //         which = keyCode - '0';
    //     }
    //     if(keyCode == ' ') {
    //         if(ap.isPlaying()) {
    //             ap.pause();
    //         } else {
    //             //ap.rewind();
    //             ap.play();
    //         }
    //     }
    // }

    float lerpedAverage = 0;

    public void draw() {
        background(0);
        stroke(255);
        float halfHeight = height / 2;
        float average = 0;
        float sum = 0;
        float lastX = width / 2, lastY = height / 2;
        print(which);

        switch (getWhich())
        {
            case 0:
            {
                for (int i = 0; i < ab.size(); i++) {
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    //println(lerpedBuffer[i]);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, halfHeight + lerpedBuffer[i] * halfHeight * 4, i);
                }
                break;
            }
            case 1:
            {
                for (int i = 0; i < ab.size(); i++) {
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    //println(lerpedBuffer[i]);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, i, halfHeight + lerpedBuffer[i] * halfHeight * 4);
                }
                break;
            }
            case 2:
            {
                for (int i = 0; i < ab.size(); i++) {
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    //println(lerpedBuffer[i]);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    line(0, i, lerpedBuffer[i] * halfHeight * 8, i);
                    line(i, 0, i, lerpedBuffer[i] * halfHeight * 8);
                    line(width, i, width - (lerpedBuffer[i] * halfHeight * 8), i);
                    line(i, height, i, height - (lerpedBuffer[i] * halfHeight * 8));
                }
                break;
            }
            case 3:
            {
                for (int i = 0; i < ab.size(); i++) {
                    sum = sum + abs(ab.get(i));
                    average = sum / (float)ab.size();
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    noFill();
                    //println(lerpedBuffer[i]);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], average, 0.1f);
                    ellipse(width / 2, height / 2, lerpedBuffer[i] * 2000, lerpedBuffer[i] * 2000);
                }
                break;
            }
            case 4:
            {
                for (int i = 0; i < ab.size(); i++) {
                    sum = sum + abs(ab.get(i));
                    average = sum / (float)ab.size();
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    noFill();
                    rectMode(CENTER);
                    //println(lerpedBuffer[i]);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], average, 0.1f);
                    rect(width / 2, height / 2, lerpedBuffer[i] * 2000, lerpedBuffer[i] * 2000);
                }
                break;
            }
            case 5:
            {
                float r = 1f;
                int numPoints = 10;
                float thetaInc = TWO_PI / (float)numPoints;
                strokeWeight(2);
                for(int i = 0; i < 512; i++)
                {
                    float c = map(i, 0, 300, 0, 255) % 150.0f;
                    stroke(c, 255, 255, 100);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    float theta = i * (thetaInc + lerpedBuffer[i]);
                    float x = width / 2 + sin(theta) * r;
                    float y = height / 2 - cos(theta) * r;
                    r += 0.5f + lerpedBuffer[i];
                    line(lastX, lastY, x, y);
                    lastX = x;
                    lastY = y;
                }
                break;
            }
        }

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