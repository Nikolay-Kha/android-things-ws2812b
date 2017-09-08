package com.example.nikolay.androidthings_ws2812b;

/*
Android Things demo project which controls WS2812B LED strip.
Written in 2017 by Nikolay Khabarov <2xl@mail.ru>

 To the extent possible under law, the author(s) have dedicated all copyright and related and
neighboring rights to this software to the public domain worldwide. This software is distributed
without any warranty.
 You should have received a copy of the CC0 Public Domain Dedication along with this software. If
not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
*/

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private WS2812B strip;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Random rnd = new Random();
        strip = new WS2812B();
        // generate random colors for some amount of LEDs.
        for(int i = 0; i < 300; i++) {
            // using HSV palette to generate bright colors.
            strip.add(0, Color.valueOf(Color.HSVToColor(new float[]{
                    rnd.nextFloat() * 360.0f, 1.0f, 0.1f
            })));
        }
        // run in loop
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // take last color and move it to first position
                strip.addFirst(strip.pollLast());
                strip.commit();
            }
        }, 500, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        strip.closeSPI();
    }
}
