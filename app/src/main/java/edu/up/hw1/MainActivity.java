//Makengo Lokombo
package edu.up.hw1;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SnowmanView snowmanView; // Custom drawing view
    private TextView currentDrawingElement; // Displays current drawable element
    private String lastTappedElement = null; // Last selected element
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        snowmanView = findViewById(R.id.drawingSurface);
        currentDrawingElement = findViewById(R.id.currentDrawingElement);
        currentDrawingElement.setText("Current Element: Snowman");

        setupColorSeekBars(); // Set up SeekBars for RGB adjustment
    }

    private void setupColorSeekBars() {
        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (lastTappedElement != null) {
                    int red = redSeekBar.getProgress();
                    int green = greenSeekBar.getProgress();
                    int blue = blueSeekBar.getProgress();
                    snowmanView.setColorForElement(lastTappedElement, red, green, blue);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        redSeekBar.setOnSeekBarChangeListener(listener);
        greenSeekBar.setOnSeekBarChangeListener(listener);
        blueSeekBar.setOnSeekBarChangeListener(listener);
    }

    public void onElementTapped(String elementName) {
        lastTappedElement = elementName;
        int color = Color.WHITE; // Default color
        switch (elementName) {
            case "Head":
                color = snowmanView.getHeadColor(); // Get head's color
                break;
            case "Middle":
                color = snowmanView.getMiddleColor(); // Get middle color
                break;
            case "Bottom":
                color = snowmanView.getBottomColor(); // Get bottom color
                break;
            case "Hat":
                color = snowmanView.hatColor.getColor(); // Get hat color
                break;
            case "Sun":
                color = snowmanView.sunColor.getColor(); // Get sun color
                break;
        }

        currentDrawingElement.setText("Current Element: " + elementName);

        // Update SeekBars based on the color
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));
    }
}
