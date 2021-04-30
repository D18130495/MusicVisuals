package D18130495;

import processing.core.*;

public class TwoChannelDance
{
    YushunVisual yv;

    public TwoChannelDance(YushunVisual yv) {
        this.yv = yv;
    }

    public void render()
    {
       //Draw the background
       yv.translate(yv.width/2, yv.height/2); //translate to the center of the screen
       yv.getFFT().forward(yv.getAudioPlayer().mix); //load the audio to the fft
       yv.pushMatrix();
       yv.setBands(yv.getFFT().getSpectrumReal()); //load the fft to bands
       for (int d = 120; d <= 540; d += 60) {
           int vertex1 = (int)yv.map(d, 0, 300, 3, 15); //use to find how many vertexs should on the each polygon 
           dance(0, 0, vertex1, d, d / 15); //call dance function to draw the polygon. (start x, start y, how many vertexs, width of the polygon, the magnitude of the change)
       }
       
       //Two-channel dance, inner use left channel, outer use right channel, this shape is for left channel
       int radius = 150; //the size of the circle (radius)
       int stick = 150; //length of the line
       
       for (int i = 0; i < yv.getAudioPlayer().left.size(); i += 8){
            float cirRadius = yv.map(i, 0, yv.getAudioPlayer().left.size(), 0, 2 * yv.PI); //the place of each line of the circle
            float stickLong = yv.abs(yv.getAudioPlayer().left.get(i)) * stick; //length of the line
            yv.stroke(yv.color(255 - yv.sin(yv.map(i, 0, yv.getAudioPlayer().left.size(), 0, 1) * yv.PI) * 200, 0, yv.sin(yv.map(i, 0, yv.getAudioPlayer().left.size(), 0, 1) * yv.PI) * 200));
            yv.line(yv.sin(cirRadius) * radius, yv.cos(cirRadius) * radius, yv.sin(cirRadius) * (stickLong + radius), yv.cos(cirRadius) * (stickLong + radius));
       }

       radius = 300;

       //This shape is for right channel 
       for (int j = 0; j < yv.getAudioPlayer().right.size(); j += 4){
            float cirRadius = yv.map(j, 0, yv.getAudioPlayer().right.size(), 0, 2 * yv.PI); //the place of each line of the circle
            float stickLong = yv.abs(yv.getAudioPlayer().right.get(j)) * stick; //length of the line
            yv.stroke(yv.sin(yv.map(j, 0, yv.getAudioPlayer().right.size(), 0, 1) * yv.PI) * 200, 0, 255 - yv.sin(yv.map(j, 0, yv.getAudioPlayer().right.size(), 1, 0) * yv.PI) * 200);
            yv.line(yv.sin(cirRadius) * (radius), yv.cos(cirRadius) * (radius), yv.sin(cirRadius) * (stickLong + radius), yv.cos(cirRadius) * (stickLong + radius));
         }
         yv.popMatrix();
    }

    int change = 0; //change the additional length of radius
    
    //Background for first visual
    public void dance(int x, int y, int num, int radius, int changeRange) {
        yv.beginShape(); //link earch vertices
        for (int d = 0; d < num; d++) {
            yv.stroke(100, 150, 220, 110);
            yv.fill(yv.random(100, 200), yv.random(100, 200), 200, 4);
            float radian = yv.cos(yv.radians(change / 100 + 20 * d)); //find out the additional length of radian for each vertices
            float r = radius + yv.map(radian, -1, 1, -changeRange, changeRange); //width of the polygon, add  additional length to the radius, make polygon can move
            int p = (int)yv.map(yv.abs(yv.getBands()[d]), 0, 1, 3, 3.1f); //use to make stroke width synchronize to music
            yv.strokeWeight(p);
            yv.vertex(x + yv.cos(yv.radians(d * 360 / num)) * r * 1.1f, y + yv.sin(yv.radians(d * 360 / num)) * r * 1.1f);
            change += 4;
        }
        yv.endShape(yv.CLOSE); // finish shape
    }
}