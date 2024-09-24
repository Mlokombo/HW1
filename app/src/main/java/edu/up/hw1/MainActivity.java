//Makengo Lokombio
package edu.up.hw1;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity class manages the UI and functionality of the Snowman drawing app.
 */
public class MainActivity extends AppCompatActivity {
    private SnowmanView snowmanView; // Custom drawing view
    private TextView currentDrawingElement; // Displays current drawable element
    private int clickCount = 0; // Button click counter
    private String lastTappedElement = null; // Last selected element

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        // snowmanView = findViewById(R.id.drawingSurface);
        currentDrawingElement = findViewById(R.id.currentDrawingElement);
        currentDrawingElement.setText("Current Element: Snowman");

        setupColorSeekBars(); // Set up SeekBars for RGB adjustment
    }

    public void updateButtonClicked(View view) {
        clickCount++;
        currentDrawingElement.setText("Button Clicked: " + clickCount + " times");
        snowmanView.invalidate(); // Redraw the view
    }

    private void setupColorSeekBars() {
        SeekBar redSeekBar = findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar = findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar = findViewById(R.id.blueSeekBar);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (lastTappedElement != null) {
                    switch (lastTappedElement) {
                        case "snowman":
                            snowmanView.setColor(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress());
                            break;
                        // Additional cases can be added here
                    }
                    snowmanView.invalidate(); // Redraw to apply color changes
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
        currentDrawingElement.setText("Current Element: " + elementName);
    }
}