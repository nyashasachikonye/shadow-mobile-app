//Author: Kuziwa Sachikonye
//Student Number: SCHKUZ002
//Date: 4.08.13
//Program Description: This is a program that runs a picture slide-show with limited functionality on an android device.
//Tutorial 1 - Phase 1

package com.CSC2002S.shadow14;

//relevant import statements
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity  implements OnClickListener{
	 TextView imageTitle;
	 int image_index = 0; // image index is a counter to iterate through the images
	 private static final Integer[] imageArray = { R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4}; // this is the array that contains all the images
	 private static final int MAX_IMAGE_COUNT = imageArray.length; // variable that shows that number of images in the image array
	 private static final String [] imageTitles = {"image1","image2", "image3", "image4"}; // array that indicates the titles of the images
	 final Handler mHandler = new Handler();
	 Timer timer;
	 TimerTask task;
	 Boolean animated = false;
	 
//On Create Bundle
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        imageTitle = (TextView)findViewById(R.id.imageTitle); 
	        showImage();
}
	 
//onClick method that handles the different clicks of the different buttons in the interface
	@Override
	public void onClick(View view) {		
		switch (view.getId()) {		
// next image block : simply shows the next image in the image array but increasing the image_index
		case (R.id.nextimagebutton):
         image_index++;
         if (image_index == MAX_IMAGE_COUNT) {image_index = 0;} // checks that we have not reached the end of the image array, if so restart the image loop
         showImage(); // showImage() method displays the relevant image associated with the image index
         break;
// this is the play slide-show block : the handler contained in here will run a timer that will allow the pictures to loop through the 
// picture array
		case (R.id.playbutton):
// runnable exchanges messages with the timer and create the dialogue that makes the iteration through the image array
		final Runnable mUpdateResults = new Runnable() {public void run() {runSlideshow();}};
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){ public void run() {mHandler.post(mUpdateResults);}}, 1500, 1500);
		break;
     
// previous image block : simply shows the last image in the image array but decreasing the image_index
	case (R.id.previmagebutton):
        image_index--;
	if (image_index == -1) {image_index = MAX_IMAGE_COUNT - 1;} // checks that we have not reached the beginning of the image array, if so restart the image loop
// at the farthest end.
	showImage(); 
// showImage() method displays the relevant image associated with the image index
	break;	
		}
}
// pause Click to stop the slide-show when the pause button is clicked
	public void pauseClick(View v)
	{
		Button pausebutton = (Button)findViewById(R.id.pausebutton);
        pausebutton.setOnClickListener(new OnClickListener(){public void onClick(View v) {timer.cancel();}		
	});}


	
//show image method take in no parameters but simply goes through the array and displays the images as the index suggests
    private void showImage() {
        ImageView imgView = (ImageView) findViewById(R.id.displayedimage);             
        imgView.setImageResource(imageArray[image_index]); // this displays the image
        imageTitle.setText(imageTitles[image_index]); // this sets the image title
    }
    
// slide-show method to run the slide-show
	private void runSlideshow() {
		{
		image_index++;
        if (image_index == MAX_IMAGE_COUNT) {image_index = 0;} // checks that we have not reached the end of the image array, if so restart the image loop
        showImage(); // showImage() method displays the relevant image associated with the image index
	}
	}
	
}