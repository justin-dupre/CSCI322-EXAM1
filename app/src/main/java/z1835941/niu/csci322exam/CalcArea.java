package z1835941.niu.csci322exam;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*****************************************
 * Justin Dupre CSCI 322 Exam
 * March 25th 2019
 * Calculate Area of various shapes
 * Using Radio Groups and two activities
 *****************************************/

public class CalcArea extends Activity {

    TextView num1TV, num2TV, titleTV;
    EditText num1ET, num2ET;
    Button calcBTN;


    private double num1, num2, area;

    private int shape;

    private String shape_str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_area);

        //connect views
        num1TV = findViewById(R.id.num1Text);
        num2TV = findViewById(R.id.num2Text);
        titleTV = findViewById(R.id.shapeTitle);

        num1ET = findViewById(R.id.num1Edit);
        num2ET = findViewById(R.id.num2Edit);

        calcBTN = findViewById(R.id.calcButton);



        //get intent from main activity
        Intent intent = getIntent();
        shape = intent.getIntExtra("id", -1);

        //see what shape was sent to this activity, set text views according
        if (shape == 1) { //Triangle
            shape_str = "Area of Triangle: ";
            titleTV.setText("Triangle Area");
            num1TV.setText("Base:");
            num2TV.setText("Height:");
            calcBTN.setText("Calculate the Triangle Area");
        }
        else if (shape == 2) { //Circle
            shape_str = "Area of Circle: ";
            titleTV.setText("Circle Area");
            num1TV.setText("Radius:");
            num2TV.setVisibility(View.INVISIBLE);
            num2ET.setVisibility(View.INVISIBLE);
            calcBTN.setText("Calculate the Circle Area");
        }
        else if (shape == 3) { //Ellipse
            shape_str = "Area of Ellipse: ";
            titleTV.setText("Ellipse Area");
            num1TV.setText("semi-major:");
            num2TV.setText("semi-minor:");
            calcBTN.setText("Calculate the Ellipse Area");
        }

    }

    //calculate the area of a triangle, circle, or ellipse
    public void calcArea(View view) {

        Intent calcIntent = new Intent(CalcArea.this, MainActivity.class);


        if (shape == 1) { //Triangle
            if(num1ET.getText().toString().matches("") || num2ET.getText().toString().matches("")){
                Toast.makeText(getApplicationContext(),"Please enter all data.", Toast.LENGTH_SHORT).show();
            }else {
                num1 = Double.parseDouble(num1ET.getText().toString());
                num2 = Double.parseDouble(num2ET.getText().toString());
                area = .5 * num1 * num2;
                calcIntent.putExtra("area", area);
                calcIntent.putExtra("shape", shape_str);
                startActivity(calcIntent);
            }

        }
        else if (shape == 2) { //Circle
            if(num1ET.getText().toString().matches("") ){
                Toast.makeText(getApplicationContext(),"Please enter all data.", Toast.LENGTH_SHORT).show();
            }else {
                num1 = Double.parseDouble(num1ET.getText().toString());
                num2 = 0;
                area = 3.14159 * Math.pow(num1, 2);
                calcIntent.putExtra("area", area);
                calcIntent.putExtra("shape", shape_str);
                startActivity(calcIntent);
            }
        }
        else if (shape == 3) { //Ellipse
            if(num1ET.getText().toString().matches("") || num1ET.getText().toString().matches("") ){
                Toast.makeText(getApplicationContext(),"Please enter all data.", Toast.LENGTH_SHORT).show();
            }else {
                num1 = Double.parseDouble(num1ET.getText().toString());
                num2 = Double.parseDouble(num2ET.getText().toString());

                area = 3.14159 * num1 * num2;

                calcIntent.putExtra("area", area);
                calcIntent.putExtra("shape", shape_str);
                startActivity(calcIntent);
            }
        }




        //create an intent back to main activity, put the area and shape name as extras.

    }

}
