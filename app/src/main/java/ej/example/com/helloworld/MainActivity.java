package ej.example.com.helloworld;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private CalculatorFragment calculatorFragment = new CalculatorFragment();
    private HelloWorldFragment helloWorldFragment = new HelloWorldFragment();
    private ImageEditorFragment imageEditorFragment = new ImageEditorFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button helloButton = (Button) findViewById(R.id.helloButton);
        final Button calcButton = (Button) findViewById(R.id.calcButton);
        final Button imageEditorButton = (Button) findViewById(R.id.imageEditorButton);

        helloButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                helloButton.setVisibility(View.GONE);
                calcButton.setVisibility(View.GONE);
                imageEditorButton.setVisibility(View.GONE);
                startHelloWorld();
            }
        });

        calcButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                helloButton.setVisibility(View.GONE);
                calcButton.setVisibility(View.GONE);
                imageEditorButton.setVisibility(View.GONE);
                startCalc();
            }
        });

        imageEditorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                helloButton.setVisibility(View.GONE);
                calcButton.setVisibility(View.GONE);
                imageEditorButton.setVisibility(View.GONE);
                startImageEditor();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.hello:
                startHelloWorld();
                return true;
            case R.id.calc:
                startCalc();
                return true;
            case R.id.imageEditor:
                startImageEditor();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startHelloWorld(){
        try {
            fragmentTransaction  = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainLayout, helloWorldFragment, "hello");
            fragmentTransaction.addToBackStack("hello");
            fragmentTransaction.commit();
        }catch(Exception e){
            Log.d("EJ", "Fragment Hello Error: " + e.toString());
        }
    }

    private void startCalc(){
        try {
            fragmentTransaction  = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainLayout, calculatorFragment, "calc");
            fragmentTransaction.addToBackStack("calc");
            fragmentTransaction.commit();
        }catch (Exception e){
            Log.d("EJ", "Fragment Calc Error: " + e.toString());
        }
    }

    private void startImageEditor(){
        try {
            fragmentTransaction  = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainLayout, imageEditorFragment, "filterEditor");
            fragmentTransaction.addToBackStack("filterEditor");
            fragmentTransaction.commit();
        }catch(Exception e){
            Log.d("EJ", "Fragment FilterEditor Error: " + e.toString());
        }
    }
}
