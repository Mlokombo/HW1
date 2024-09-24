//Makengo Lokombio
package edu.up.hw1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

@SuppressLint("ViewConstructor")
public class SnowmanView extends SurfaceView {

    private final Paint whitePaint;
    private final Paint blackPaint;
    private final Paint orangePaint;
    private final Paint blackHatPaint;

    public SnowmanView(Context context, AttributeSet attrs, Bitmap ignoredSnowmanBmp) {
        super(context, attrs);
        // If you decide to use an image of a snowman

        // Tell the app that this view actually DOES draw something
        setWillNotDraw(false);

        // Setup paint for different components of the snowman
        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStyle(Paint.Style.FILL);

        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.FILL);

        orangePaint = new Paint();
        orangePaint.setColor(Color.rgb(255, 165, 0)); // Carrot nose color
        orangePaint.setStyle(Paint.Style.FILL);

        blackHatPaint = new Paint();
        blackHatPaint.setColor(Color.BLACK);
        blackHatPaint.setStyle(Paint.Style.FILL);

        // If you want to load an image for the snowman (optional)

        setBackgroundColor(Color.WHITE); // Set the background to white
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas); // Call the superclass method first

        // Draw the snowman body
        canvas.drawCircle(400, 600, 100, whitePaint); // Bottom
        canvas.drawCircle(400, 450, 75, whitePaint);  // Middle
        canvas.drawCircle(400, 375, 50, whitePaint);  // Head

        // Draw eyes
        canvas.drawCircle(385, 365, 5, blackPaint);   // Left eye
        canvas.drawCircle(415, 365, 5, blackPaint);   // Right eye

        // Draw carrot nose
        @SuppressLint("DrawAllocation") float[] nosePath = new float[]{400, 370, 420, 375, 400, 380};
        canvas.drawPath(createPath(nosePath), orangePaint);

        // Draw the hat
        canvas.drawRect(360, 320, 440, 350, blackHatPaint); // Hat brim
        canvas.drawRect(385, 290, 415, 320, blackHatPaint); // Hat top

        // Optionally draw a bitmap of a snowman if added
        // canvas.drawBitmap(snowmanBmp, 200, 300, null);
    }

    // Method to create a path for the carrot nose
    private android.graphics.Path createPath(float[] points) {
        android.graphics.Path path = new android.graphics.Path();
        path.moveTo(points[0], points[1]);
        path.lineTo(points[2], points[3]);
        path.lineTo(points[4], points[5]);
        path.close();
        return path;
    }

    public void setColor(int ignoredR, int ignoredG, int ignoredB) {
        // This method can be used if you want to change snowman color dynamically
        // Example: Set the body's color based on RGB parameters
    }
}