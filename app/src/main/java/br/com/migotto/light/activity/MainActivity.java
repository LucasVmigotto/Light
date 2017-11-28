package br.com.migotto.light.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import br.com.migotto.light.R;
import br.com.migotto.light.model.Light;

public class MainActivity extends AppCompatActivity {

    private int count;
    private TextInputLayout inputLayout;
    private TextInputEditText editText;
    private Button button;
    private String todaysPassword;

    private void initComponents(){
        inputLayout=(TextInputLayout)findViewById(R.id.login_layout);
        editText=(TextInputEditText)findViewById(R.id.login_input);
        button=(Button)findViewById(R.id.validate_button);
        count=0;
    }

    private void setTodaysPassword(){
        Calendar c=Calendar.getInstance();
        int day=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH)+1;
        int year=c.get(Calendar.YEAR);
        todaysPassword=""+month+""+year+""+day+"10070200";
    }

    private void setUsable(){
        inputLayout.setVisibility(View.VISIBLE);
        inputLayout.setClickable(true);
        editText.setVisibility(View.VISIBLE);
        editText.setClickable(true);
        button.setVisibility(View.VISIBLE);
        button.setClickable(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        setTodaysPassword();
    }

    public void checkThisMofo(View view){
        count++;
        testThisMofo(view);
    }

    public void testThisMofo(View view) {
        if(count==14){
            setUsable();
        }
    }

    public void validate(View view) {
        if (editText.getEditableText().toString().trim().equals(todaysPassword)){
            startActivity(new Intent(this, LightActivity.class));
        }
    }
}
