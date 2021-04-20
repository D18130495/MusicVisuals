package D18130495;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
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
        colorMode(HSB);
        startMinim();
        loadAudio("heroplanet.mp3");
        lerpedBuffer = new float[width];
    }

    //use to pause the visual
    public void keyPressed() {
        if(getAudioPlayer().isPlaying()) {
            getAudioPlayer().pause();
        } else {
            getAudioPlayer().play();
        }
    }

    float lerpedAverage = 0;

    public void draw() {
        background(0);
        stroke(255);
        float halfHeight = height / 2;
        float average = 0;
        float sum = 0;
        float lastX = width / 2, lastY = height / 2;

        switch (which)
        {
            case 0:
            {
                for (int i = 0; i < getAudioBuffer().size(); i++) {
                    float c = map(i, 0, getAudioBuffer().size(), 0, 255);
                    stroke(c, 255, 255);
                    //println(lerpedBuffer[i]);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], getAudioBuffer().get(i), 0.1f);
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, halfHeight + lerpedBuffer[i] * halfHeight * 4, i);
                }
                break;
            }
            case 1:
            {
                for (int i = 0; i < getAudioBuffer().size(); i++) {
                    float c = map(i, 0, getAudioBuffer().size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], getAudioBuffer().get(i), 0.1f);
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, i, halfHeight + lerpedBuffer[i] * halfHeight * 4);
                }
                break;
            }
            case 2:
            {
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
                break;
            }
            case 3:
            {
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