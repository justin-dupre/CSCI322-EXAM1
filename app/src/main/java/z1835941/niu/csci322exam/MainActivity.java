package z1835941.niu.csci322exam;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


/*****************************************
 * Justin Dupre CSCI 322 Exam
 * March 25th 2019
 * Calculate Area of various shapes
 * Using Radio Groups and two activities
 *****************************************/

public class MainActivity extends Activity {

    private RadioGroup shapeRG;
    private String currentShape = "test";
    private TextView areaTV, shapeStrTV;
    private double area;

    final DecimalFormat df = new DecimalFormat("#.0000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect views
        shapeRG = findViewById(R.id.shapeRadioGroup);
        areaTV = findViewById(R.id.areaTextView);
        shapeStrTV = findViewById(R.id.shapeStr);

        //on change listener for radio group
        shapeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.circleRadioButton) {
                    currentShape = "circle";
                } else  if (checkedId == R.id.ellipseRadioButton) {
                    currentShape = "ellipse";
                } else  if (checkedId == R.id.triangleRadioButton) {
                    currentShape = "triangle";
                }

            }
        });

        //grab intent from CalcArea.class
        Intent intent = getIntent();
        area = intent.getDoubleExtra("area", -1);

        //if the id is not the default value, display area and shape name
        if (area != -1) {
            shapeStrTV.setText(intent.getStringExtra("shape"));
            areaTV.setText(df.format(area));
        }


    }




    //on click for button
    public void calcArea(View v) {
        Intent intent = new Intent(getBaseContext(), CalcArea.class);
        if(currentShape == "test"){
            Toast.makeText(getApplicationContext(),"Please select a shape.", Toast.LENGTH_SHORT).show();
        } else {
            int id = 0;
            if (currentShape == "triangle") {
                id = 1;
            } else if (currentShape == "circle"){
                id = 2;
            } else if (currentShape == "ellipse") {
                id = 3;
            }
            //send an id corresponding to the shape
            intent.putExtra("currentShape", currentShape);
            intent.putExtra("id", id);
            startActivity(intent);
        }

    }
}
