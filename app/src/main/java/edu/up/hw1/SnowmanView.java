//Makengo LOkombo
package edu.up.hw1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

@SuppressLint("ViewConstructor")
public class SnowmanView extends SurfaceView {

    private final Paint headColor; // Paint for the snowman head
    private final Paint middleColor; // Paint for the snowman middle
    private final Paint bottomColor; // Paint for the snowman bottom
    private final Paint blackPaint; // Paint for the eyes
    private final Paint orangePaint; // Paint for the carrot nose
    public final Paint hatColor; // Paint for the hat
    private final Paint skyPaint; // Paint for the sky
    public final Paint sunColor; // Paint for the sun

    private String lastTappedElement = null; // Track the last tapped element

    public SnowmanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false); // Allow custom drawing

        // Setup paint for different components of the snowman
        headColor = new Paint();
        headColor.setColor(Color.WHITE); // Initial head color
        headColor.setStyle(Paint.Style.FILL);

        middleColor = new Paint();
        middleColor.setColor(Color.WHITE); // Initial middle color
        middleColor.setStyle(Paint.Style.FILL);

        bottomColor = new Paint();
        bottomColor.setColor(Color.WHITE); // Initial bottom color
        bottomColor.setStyle(Paint.Style.FILL);

        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.FILL);

        orangePaint = new Paint();
        orangePaint.setColor(Color.rgb(255, 165, 0)); // Carrot nose color
        orangePaint.setStyle(Paint.Style.FILL);

        hatColor = new Paint();
        hatColor.setColor(Color.BLACK); // Initial hat color
        hatColor.setStyle(Paint.Style.FILL);

        skyPaint = new Paint();
        skyPaint.setColor(Color.CYAN); // Sky color

        sunColor = new Paint();
        sunColor.setColor(Color.YELLOW); // Initial sun color
        sunColor.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.BLUE); // Set the background to blue
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas); // Call the superclass method first

        // Draw the sky
        canvas.drawRect(0, 0, getWidth(), getHeight() / 2, skyPaint); // Sky in the top half

        // Draw the sun
        canvas.drawCircle(100, 100, 40, sunColor); // Sun at the top-left corner

        // Draw each part of the snowman body using separate colors
        canvas.drawCircle(400, 650, 200, bottomColor); // Bottom
        canvas.drawCircle(400, 450, 150, middleColor); // Middle
        canvas.drawCircle(400, 350, 100, headColor); // Head

        // Draw eyes
        canvas.drawCircle(375, 340, 15, blackPaint); // Left eye
        canvas.drawCircle(425, 340, 15, blackPaint); // Right eye

        // Draw the carrot nose
        float[] nosePath = new float[]{400, 355, 440, 370, 400, 375}; // Carrot nose path
        canvas.drawPath(createPath(nosePath), orangePaint);

        // Draw the hat
        float hatBrimWidth = 180; // Width of the brim
        float hatHeight = 50; // Height of the brim
        float hatBrimY = 200; // Y coordinate for the brim
        float hatTopY = 160; // Y coordinate for the top of the hat

        // Hat brim
        canvas.drawRect(400 - hatBrimWidth / 2, hatBrimY,
                400 + hatBrimWidth / 2, hatBrimY + hatHeight, hatColor);

        // Hat top
        canvas.drawRect(375, hatTopY, 425, hatTopY + 50, hatColor); // Hat top
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            String elementTapped = detectTouchedElement(x, y);
            if (elementTapped != null) {
                lastTappedElement = elementTapped; // Save last tapped element

                if (getContext() instanceof MainActivity) {
                    ((MainActivity) getContext()).onElementTapped(elementTapped); // Notify MainActivity
                }
            }
            return true; // Event handled
        }
        return super.onTouchEvent(event); // Event not handled
    }

    private String detectTouchedElement(float x, float y) {
        // Define the boundaries for each element
        if (isInCircle(x, y, 400, 350, 100)) {
            return "Head"; // Head element
        } else if (isInCircle(x, y, 400, 450, 150)) {
            return "Middle"; // Middle element
        } else if (isInCircle(x, y, 400, 650, 200)) {
            return "Bottom"; // Bottom element
        } else if (isInHatBounds(x, y)) {
            return "Hat"; // Hat element
        } else if (isInSunBounds(x, y)) {
            return "Sun"; // Sun element
        }
        return null; // No elements touched
    }

    private boolean isInCircle(float x, float y, float centerX, float centerY, float radius) {
        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
    }

    private boolean isInHatBounds(float x, float y) {
        return x >= 375 && x <= 425 && y >= 160 && y <= 210; // Top of the hat
    }

    private boolean isInSunBounds(float x, float y) {
        return Math.pow(x - 100, 2) + Math.pow(y - 100, 2) <= Math.pow(40, 2); // Sun bounds
    }

    // Set color for specific elements
    public void setColorForElement(String element, int red, int green, int blue) {
        switch (element) {
            case "Head":
                headColor.setColor(Color.rgb(red, green, blue)); // Change head color
                break;
            case "Middle":
                middleColor.setColor(Color.rgb(red, green, blue)); // Change middle color
                break;
            case "Bottom":
                bottomColor.setColor(Color.rgb(red, green, blue)); // Change bottom color
                break;
            case "Hat":
                hatColor.setColor(Color.rgb(red, green, blue)); // Change hat color
                break;
            case "Sun":
                sunColor.setColor(Color.rgb(red, green, blue)); // Change sun color
                break;
        }
        invalidate(); // Redraw the view
    }

    // Method to get RGB values for specific elements with help of chat gbt
    public int[] getRGBForElement(String element) {
        switch (element) {
            case "Head":
                return getRgbValues(headColor.getColor());
            case "Middle":
                return getRgbValues(middleColor.getColor());
            case "Bottom":
                return getRgbValues(bottomColor.getColor());
            case "Hat":
                return getRgbValues(hatColor.getColor());
            case "Sun":
                return getRgbValues(sunColor.getColor());
            default:
                return new int[]{0, 0, 0}; // Return black as default
        }
    }

    // Helper method to extract RGB values from a color
    private int[] getRgbValues(int color) {
        return new int[]{
                Color.red(color),
                Color.green(color),
                Color.blue(color)
        };
    }

    public int getHeadColor() {
        return headColor.getColor(); // Get head color
    }

    public int getMiddleColor() {
        return middleColor.getColor(); // Get middle color
    }

    public int getBottomColor() {
        return bottomColor.getColor(); // Get bottom color
    }

    public int getHatColor() {
        return hatColor.getColor(); // Get hat color
    }

    public int getSunColor() {
        return sunColor.getColor(); // Get sun color
    }
}