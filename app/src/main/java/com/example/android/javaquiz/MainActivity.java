package com.example.android.javaquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private int marks;
    private String resultFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setQuestions();
    }

//    call on clicking the submit button to show result
    public void showResult(View view){

        if(isAllSelected()){
            getMarks();

            if(this.marks <5 )
                resultFeed = getResources().getString(R.string.poor);
            else if(this.marks >=5 & this.marks<8)
                resultFeed = getResources().getString(R.string.good);
            else
                resultFeed = getResources().getString(R.string.excellent);
            Toast.makeText(getApplicationContext(),"SCORE = "+marks+"/10 - "+resultFeed, Toast.LENGTH_LONG).show();
            this.marks = 0;
        } else{
            Toast.makeText(getApplicationContext(),"Please Do all questions", Toast.LENGTH_LONG).show();
            return;
        }
    }

//    get total marks
    public void getMarks(){

        for(int i=1;i<=10;i++){
            switch (i){
                case 1 :
                    RadioButton rb1 = findViewById(R.id.q1_option2);
                    if(rb1.isChecked())
                        this.marks++;
                    else
                        rb1.setTextColor(Color.BLUE);
                    break;

                case 2 :
                    RadioButton rb2 = findViewById(R.id.q2_option2);
                    if(rb2.isChecked())
                        this.marks++;
                    else
                        rb2.setTextColor(Color.BLUE);
                    break;

                case 3 :
                    RadioButton rb3 = findViewById(R.id.q3_option1);
                    if(rb3.isChecked())
                        this.marks++;
                    else
                        rb3.setTextColor(Color.BLUE);
                    break;

                case 4 :
                    RadioButton rb4 = findViewById(R.id.q4_option3);
                    if(rb4.isChecked())
                        this.marks++;
                    else
                        rb4.setTextColor(Color.BLUE);
                    break;

                case 5 :
                    EditText n1 = findViewById(R.id.start_number);
                    EditText n2 = findViewById(R.id.end_number);

                    if(Integer.parseInt(n1.getText().toString()) == 0 && Integer.parseInt(n2.getText().toString()) == 65535)
                        this.marks++;
                    break;

                case 6 :
                    RadioButton rb6 = findViewById(R.id.q6_option3);
                    if(rb6.isChecked())
                        this.marks++;
                    else
                        rb6.setTextColor(Color.BLUE);
                    break;

                case 7 :
                    RadioButton rb7 = findViewById(R.id.q7_option4);
                    if(rb7.isChecked())
                        this.marks++;
                    else
                        rb7.setTextColor(Color.BLUE);
                    break;

                case 8 :
                    RadioButton rb8 = findViewById(R.id.q8_option4);
                    if(rb8.isChecked())
                        this.marks++;
                    else
                        rb8.setTextColor(Color.BLUE);
                    break;

                case 9 :
                    CheckBox cb1 = findViewById(R.id.q9_option1);
                    CheckBox cb2 = findViewById(R.id.q9_option2);
                    CheckBox cb3 = findViewById(R.id.q9_option3);
                    CheckBox cb4 = findViewById(R.id.q9_option4);

                    if(cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && !cb4.isChecked())
                        this.marks++;
                    else{
                        TextView q = findViewById(R.id.question9);
                        q.setTextColor(Color.RED);
                    }
                    break;

                case 10 :
                    RadioButton rb10 = findViewById(R.id.q10_option2);
                    if(rb10.isChecked())
                        this.marks++;
                    else
                        rb10.setTextColor(Color.BLUE);
                    break;
            }
        }
    }

//    checking all questions are done or not
    public boolean isAllSelected(){

        for(int i=1;i<=10;i++){

            String rgStringId = "question"+i+"_options";
            int rgId = getResources().getIdentifier(rgStringId,"id",getPackageName());

//            checking for checkbox...
            if(i == 9 ){
                CheckBox cb1 = findViewById(R.id.q9_option1);
                CheckBox cb2 = findViewById(R.id.q9_option2);
                CheckBox cb3 = findViewById(R.id.q9_option3);
                CheckBox cb4 = findViewById(R.id.q9_option4);
                if(!cb1.isChecked() & !cb2.isChecked() & !cb3.isChecked() & !cb4.isChecked()){
                    return false;
                }
            }

//            checking for texfield...
            else if(i == 5 ){
                EditText n1 = findViewById(R.id.start_number);
                EditText n2 = findViewById(R.id.end_number);

                if(n1.getText().toString().isEmpty() | n2.getText().toString().isEmpty() ){
                    return false;
                }
            }
            else{
                RadioGroup rg = findViewById(rgId);

                if(rg.getCheckedRadioButtonId() == -1){
                    return false;
                }
            }
        }
        return true;
    }

//    set all questions to proper question numbers
    public void setQuestions(){

        for(int i=1;i<=10;i++){

            String viewId = "question"+i;
            int resViewId = getResources().getIdentifier(viewId,"id",getPackageName());
            TextView ques = findViewById(resViewId);
            int resStringId = getResources().getIdentifier(viewId,"string",getPackageName());
            ques.setText(resStringId);

            if(i == 5 )
                continue;

            for(int j=1;j<=4;j++){
                String optionId = "q"+i+"_option"+j;
                int resOptionViewId = getResources().getIdentifier(optionId,"id",getPackageName());
                TextView opt = findViewById(resOptionViewId);

                int resOptionStringId = getResources().getIdentifier(optionId,"string",getPackageName());
                opt.setText(resOptionStringId);
            }
        }
    }

//    Reset all answers...
    public void reset(View view){

        for(int i=1;i<=10;i++){

            if(i == 5){
                EditText n1 = findViewById(R.id.start_number);
                EditText n2 = findViewById(R.id.end_number);
                n1.setText(null);
                n2.setText(null);
                continue;
            }
            if(i == 9){
                for(int k=1;k<=4;k++){
                    String sId = "q9_option"+k;
                    int cbId = getResources().getIdentifier(sId,"id",getPackageName());
                    CheckBox cb = findViewById(cbId);
                    cb.setChecked(false);
                }
                continue;
            }
            String rgStringId = "question"+i+"_options";
            int rgId = getResources().getIdentifier(rgStringId,"id",getPackageName());
            RadioGroup rg = findViewById(rgId);
            rg.clearCheck();
        }
    }
}
