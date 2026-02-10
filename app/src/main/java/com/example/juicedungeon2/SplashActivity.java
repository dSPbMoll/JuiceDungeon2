package com.example.juicedungeon2;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Screen config
        setContentView(R.layout.splash_activity);

        // Hide system bars (Status Bar & Navigation Bar)
        WindowInsetsControllerCompat controller =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        controller.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
        controller.hide(WindowInsetsCompat.Type.systemBars());

        // Load animation
        Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Listener
        fade1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Verify !isFinishing()
                if (!isFinishing()) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        // View reference
        TextView title = findViewById(R.id.TextViewTopTitle);
        TableLayout table = findViewById(R.id.TableLayout01);
        TextView botTtitle = findViewById(R.id.TextViewBottomTitle);
        TextView botVersion = findViewById(R.id.TextViewBottomVersion);

        // Start animations
        title.startAnimation(fade1);
        table.startAnimation(fade1);
        botTtitle.startAnimation(fade1);
        botVersion.startAnimation(fade1);
    }
}