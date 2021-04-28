package D18130495;

import processing.core.*;

public class Rain
{
    YushunVisual yv;

    public Rain(YushunVisual yv) {
        this.yv = yv;
    }

    public void render()
    {
        yv.translate(yv.width/2, yv.height/2); //translate to the center of the screen
        yv.getFFT().forward(yv.getAudioPlayer().mix); //load the audio to the fft
        yv.pushMatrix();
        yv.setBands(yv.getFFT().getSpectrumReal()); //load the fft to bands

        //Circle
        float angle = 0;
        float cir = 180; //the radius of big circle

        for (int i = 0; i < 360; i++) {
            float color = yv.getBands()[i] / 2; //get the current fft use for color
            yv.noStroke();
            yv.fill(yv.random(color, 200), yv.random(color, 255), 200);
            yv.lerpedBuffer[i] = yv.lerp(yv.lerpedBuffer[i], yv.getBands()[i], 0.08f); //smooth the circle
            yv.ellipse(yv.sin(angle) * (cir + yv.lerpedBuffer[i]), -yv.cos(angle) * (cir + yv.lerpedBuffer[i]), yv.abs(yv.lerpedBuffer[i] * 4), yv.abs(yv.lerpedBuffer[i] * 4));
            angle += yv.PI / 20; //draw 20 circles on the screen to form a big circle
        }

        //Ring
        float ang = 0;
        int stick = 15; //the length of the stick

        for (int j = 0; j < 360; j++) {
            float color = yv.getBands()[j]; //get the current fft us for color
            yv.stroke(yv.random(color, 200), yv.random(color, 150), 200, 180);
            yv.strokeWeight(3);
            yv.lerpedBuffer2[j] = yv.lerp(yv.lerpedBuffer2[j], yv.getBands()[j], 0.5f); //smooth the circle
            yv.line(-yv.cos(ang) * (cir + yv.lerpedBuffer2[j] + stick), yv.sin(ang) * (cir + yv.lerpedBuffer2[j] + stick), -yv.cos(ang) * (cir - yv.lerpedBuffer2[j] - stick), yv.sin(ang) * (cir - yv.lerpedBuffer2[j] - stick));
            ang += yv.PI / 40; //draw 40 lines on the screen to form a big circle
        }

        //top line
        for (int x = 0; x < 60; x += 5) {
            float dist = yv.getBands()[x];
            float position = yv.random(dist, yv.width);
            yv.stroke(yv.random(dist, 200), yv.random(dist, 150), 200);
            yv.strokeWeight(5);
            yv.line(position - yv.width / 2, -(yv.height / 2), position - yv.width / 2, dist * 2 + 40 - yv.height / 2);
        }

        //bottom line
        for (int x = 0; x < 60; x += 5) {
            float dist = yv.getBands()[x];
            float position = yv.random(dist, yv.width);
            yv.stroke(yv.random(dist, 200), yv.random(dist, 150), 200);
            yv.strokeWeight(5);
            yv.line(position - yv.width / 2, yv.height / 2, position - yv.width / 2, -(dist * 2) - 40 + yv.height / 2);
        }
        yv.popMatrix();
    }
}