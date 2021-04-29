# Music Visualiser Project

Name: Yushun Zeng

Student Number: D18130495

# Description of the assignment
Wellcome to YushunVisual! In this assignment You will experience four different visual effects. There is a controller that can help you easily switch between each visual.

The first music use for visual 1 - 3. ![An music](java/data/music.mp3)

The second music use for visual 4. ![An music](java/data/music1.mp3)

1. First visual: In this visual, there are two big circles represent two different channels, inner circle for left channel and outer for right channel.
				 Also, there are some polygons as background and they can shake, the width of the edges of each polygon can become more width depend on the amplitude of the audio.

2. Second visual: In this visual, there is a big circle in the center of the screen, and this circle is constructed by many sticks, under the sticks will have many 					  circle that has been synchronized to the music, it can become bigger and have different color.
				  At top and bottom will have some sticks to make this visual more beautiful and also synchronized to the music.

3. Third visual: In this visual, many circles are generated from the center of the four sides of the screen, and diverge to the middle of the screen.
				 This circles have different size and color, both of this has been synchronized to the music.

4. Fourth visual: This is the most amazing visual in this assignment.
				  In this visual, you can see how the thousand of pixels form a picture. This visual has been appropriately synchronized to the music, the number of pixels drawn per second is related to the amplitude of the audio.
				  In this visual I decided to use an instrumental music which can make it more enjoyable.

# Instructions
When you run this program, it will generate two black screens, one for controller and another is to show the visual.
- This is what the controller looks like, and there are 5 buttons on it, press first button to start the visual and press again for pause.

![An image](images/controller.PNG)
- This is the start screen to show the visual.
![An image](images/screen.PNG)
1. Press start buttons to start the visual, it will automatically jump to the first visual(The first visual botton "Two-channel dance" will light up).
![An image](images/first.PNG)
2. Press the thrid button switch to the second visual "Rain".
![An image](images/second.PNG)
3. Press the fourth button switch to the third visual "Pop-out".
![An image](images/third.PNG)
4. Press the fifth button switch to the fourth visual "Draw", there is a special music use for this visual, and should go to java\src\D18130495 modify YushunVisual.java loadAudio("music1.mp3");, this visual is especially synchronized with the music.
- The start of this visual will looks like:
![An image](images/fourth1.PNG)
- The end of the visual will looks like:
![An image](images/fourth2.PNG)
- The original image of this visual:
![An image](images/original.PNG)
# How it works
First of all, I have to say thanks to last year students' assignment which inspire me about the controller idea!
## Controller
1. At the start of this assignment, I created two classes, one is YushunVisual.java, this is the subclass of ie\tudublin\Visual.java which mean I extends Visual.java. This is used as main class of this project, it will call the other class and show the different visuals. The second class I created is Controller.java, which is used to switch each visual.
2. The main difficulty of this part is how to link each class today and make the controller work. The main idea is in the ie\tudublin\Main.java new Controller object and use this Controller object to new YushunVisual object. The usage of (this.) is the key to connect each object together.
## Fiest visual: Two-channel dance
1. This visual have two elements in it. The first element is two big circles, inner circle synchronized to the left channel of the music and outer circle is synchronized to the right channel of the music. The second element is the multiple polygons as the background, all the polygons can shake and the width of each polygon are synchronized to the music.
2. In this visual, I used lots of sin and cos to make circle and find position of vertex to make polygons. For the two circles simply use sin to find the position of startX and endX, use cos to find startY and endY. For the second background polygons, it has same concept to find the position of polygon, but this time is to find the vertex of it, and use beginShape() endShape(CLOSE) to link each vertex to shape a polygon.
3. The main difficulty of the first element is how to fill the color and make it looks beautiful. If I just fill this circle with rainbow color, the start color is red and end color is blue, this two color will link together and looks uncomfortable.
![An image](images/how1.PNG)
So, I try two combine two rainbow color together, make two rainbows red color link together and blue color link together. 
Start with red and end with blue(left) range(3pi / 2 - pi / 2), start with red and end with blue(right) range(- pi / 2, pi / 2).
![An image](images/how2.PNG)
## Second visual: Rain
## Third visual: Pop-out
## Fourth visual: Draw
# What I am most proud of in the assignment

# Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

This is a table:

| Heading 1 | Heading 2 |
|-----------|-----------|
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |

