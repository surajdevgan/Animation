package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView ball;
    TextView Sensors;

    private int screenWidth;
    private int screenHeight;

    private float x_pos = 0.0f;
    private float y_pos = 0.0f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ball = findViewById(R.id.basket_ball);
        Sensors = findViewById(R.id.sensord);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager!=null)
        {

            Sensor gyrosensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(gyrosensor!=null)
            {
                sensorManager.registerListener(this,gyrosensor, SensorManager.SENSOR_DELAY_GAME);
            }
        }

        ball.getLayoutParams().height = 200;
        ball.getLayoutParams().width = 200;

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        ball.setX(x_pos);
        ball.setY(y_pos);


    }



    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensors.setText("X: "+event.values[0]+", Y: "+event.values[1]);


if(screenWidth + 700  >  y_pos)
{

    y_pos += event.values[1];

    ball.setY(y_pos);


    Log.w("Xsensor", ""+event.values[0]);
    Log.w("Ysensor", ""+event.values[1]);
}





if(screenWidth + 700 < y_pos)
{

    y_pos -= event.values[1];

    ball.setY(y_pos);

}

        if(screenWidth  > x_pos)
        {

            x_pos += event.values[0];
            ball.setX(x_pos);

        }

        if(screenWidth - 200 < x_pos)
        {

            x_pos -= event.values[0];
            ball.setX(x_pos);


        }





    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}