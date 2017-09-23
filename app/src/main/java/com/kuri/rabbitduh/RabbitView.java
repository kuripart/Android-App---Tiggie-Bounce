package com.kuri.rabbitduh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class RabbitView extends SurfaceView implements SurfaceHolder.Callback {

    TestThread testThread;
    Bitmap img;
    Bitmap mask_one;
    Bitmap mask_two;
    Bitmap mask_three;
    Bitmap mask_four;
    Bitmap tigiee1;
    Bitmap tigiee2;
    Bitmap tigiee3;
    Bitmap tigiee4;
    int tiger1X;
    int tiger2X;
    int tiger3X;
    int tiger4X;
    int tiger1Y;
    int tiger2Y;
    int tiger3Y;
    int tiger4Y;
    int downTiger;
    float tigerNumberFloat;
    int tigerNumber;
    int tigerJumpFactor;
    Boolean running = true;
    Boolean titleShow = true;
    SurfaceHolder surfaceHolder;
    Context myContext;
    Boolean tigerRise;
    Boolean tigerFall;
    Boolean tigerDown;
    SoundPool soundPool;
    int soundCount;
    int sound;

    public RabbitView(Context context) {
        super(context);
        myContext = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        testThread = new TestThread(myContext, surfaceHolder);
        tiger1X = 165;
        tiger2X = 593;
        tiger3X = 1008;
        tiger4X = 1423;
        tiger1Y = 445;
        tiger2Y = 445;
        tiger3Y = 445;
        tiger4Y = 445;
        downTiger = 445;
        tigerJumpFactor = 10;
        tigerRise = false;
        tigerFall = false;
        tigerDown = true;
        soundCount = 0;
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        sound = soundPool.load(context, R.raw.jump,1);
    }

    public class TestThread extends Thread{
        public TestThread(Context context, SurfaceHolder surfaceHolder){
            img = BitmapFactory.decodeResource(context.getResources(),R.drawable.gameplay);
            mask_one = BitmapFactory.decodeResource(context.getResources(),R.drawable.bin_mask_one);
            mask_two = BitmapFactory.decodeResource(context.getResources(),R.drawable.bin_mask_two);
            mask_three = BitmapFactory.decodeResource(context.getResources(),R.drawable.bin_mask_three);
            mask_four = BitmapFactory.decodeResource(context.getResources(),R.drawable.bin_mask_four);
            tigiee1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.tigee);
            tigiee2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.tigee);
            tigiee3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.tigee);
            tigiee4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.tigee);
        }

        @Override
        public void run() {
            Canvas canvas = null;
            while(running){
                try{
                    canvas = surfaceHolder.lockCanvas();
                    draw(canvas);
                    canvas.drawBitmap(img,0,0,null);
                    if(!titleShow){ //draw only when title screen is not shown
                        //if any of the tiger is in initial position
                        if(tigerDown){
                            tigerNumberFloat = (float)Math.random();
                            tigerNumber = (int)(tigerNumberFloat*5); //to increase randomness
                            if(tigerNumber == 0){
                                tigerNumber = 1;
                            }
                            if(tigerNumber == 5){
                                tigerNumber = 4;
                            }
                            tigerRise = true; //tiger rises
                        }
                        if(tigerNumber == 1 && tigerRise){ //if tiger 1 and should rise
                            soundCount++;
                            if(soundCount<=1){
                                soundPool.play(sound,1,1,1,0,1);
                            }
                            tigerDown = false;
                            tiger1Y -= tigerJumpFactor; //go up, y decreases
                            canvas.drawBitmap(tigiee1,tiger1X,tiger1Y,null);
                            if(tiger1Y <= 50){ //has reached maximum
                                tigerFall = true; //fall
                                tigerRise = false; //don't rise
                            }
                        }if(tigerNumber == 1 && tigerFall){
                            tiger1Y += tigerJumpFactor; //go down, y decreases
                            canvas.drawBitmap(tigiee1,tiger1X,tiger1Y,null);
                            if(tiger1Y == downTiger){ //if reached initial level
                                tigerDown = true; //is down
                                tigerFall = false; //don'r fall more
                                soundCount = 0;
                            }
                        }
                        if(tigerNumber == 2 && tigerRise){
                            soundCount++;
                            if(soundCount<=1){
                                soundPool.play(sound,1,1,1,0,1);
                            }
                            tigerDown = false;
                            tiger2Y -= tigerJumpFactor;
                            canvas.drawBitmap(tigiee2,tiger2X,tiger2Y,null);
                            if(tiger2Y <= 50){
                                tigerRise = false;
                                tigerFall = true;
                            }
                        }if(tigerNumber == 2 && tigerFall){
                            tiger2Y += tigerJumpFactor;
                            canvas.drawBitmap(tigiee2,tiger2X,tiger2Y,null);
                            if(tiger2Y == downTiger){
                                tigerDown = true;
                                tigerFall = false;
                            }
                        }
                        if(tigerNumber == 3 && tigerRise){
                            soundCount++;
                            if(soundCount<=1){
                                soundPool.play(sound,1,1,1,0,1);
                            }
                            tigerDown = false;
                            tiger3Y -= tigerJumpFactor;
                            canvas.drawBitmap(tigiee3,tiger3X,tiger3Y,null);
                            if(tiger3Y <= 50){
                                tigerRise = false;
                                tigerFall = true;
                            }
                        }if(tigerNumber == 3 && tigerFall){
                            tiger3Y += tigerJumpFactor;
                            canvas.drawBitmap(tigiee3,tiger3X,tiger3Y,null);
                            if(tiger3Y == downTiger){
                                tigerDown = true;
                                tigerFall = false;
                                soundCount = 0;
                            }
                        }
                        if(tigerNumber == 4 && tigerRise){
                            if(soundCount<=1){
                                //soundPool.play(sound,1,1,1,0,1);
                            }
                            tigerDown = false;
                            tiger4Y -= tigerJumpFactor;
                            canvas.drawBitmap(tigiee4,tiger4X,tiger4Y,null);
                            if(tiger4Y <= 50){
                                tigerRise = false;
                                tigerFall = true;
                            }
                        }if(tigerNumber == 4 && tigerFall){
                            tiger4Y += tigerJumpFactor;
                            canvas.drawBitmap(tigiee4,tiger4X,tiger4Y,null);
                            if(tiger4Y == downTiger){
                                tigerDown = true;
                                tigerFall = false;
                                soundCount = 0;
                            }
                        }
                        //masks to hide tigers
                        canvas.drawBitmap(mask_one,155,450,null);
                        canvas.drawBitmap(mask_two, 585,450,null);
                        canvas.drawBitmap(mask_three,1000,450,null);
                        canvas.drawBitmap(mask_four,1415,450,null);
                    }
                } finally{
                    if(canvas != null){
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }

        /*public void draw(Canvas canvas){

        }*/

        //NOT USED
       public void setScale(int height, int width){
                //img = Bitmap.createScaledBitmap(img,width,height,true);
        }

        //DOESN'T RUN WHEN CALLED INSIDE draw method?
        /*public void randomTiger(){
            tigerNumberFloat = (float)(Math.random());
            tigerNumber = (int)(tigerNumberFloat*2);
            if(tigerNumber == 0){
                tigerNumber = 1;
            }
        }*/

        public boolean touch(MotionEvent event){
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        if (titleShow) { //when touch and title screen initially shown, draw game screen
                            img = BitmapFactory.decodeResource(myContext.getResources(), R.drawable.gameplay);
                            titleShow = false; //no more title
                        }
                        break;
                }
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return testThread.touch(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(testThread.getState() == Thread.State.NEW){
            testThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        testThread.setScale(height,width);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        running = false;
    }


}