package android.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

//неправильно считает числа
//нужно переделать приоритет подсчета * и /

public class MainActivity extends AppCompatActivity {

    Button[] btn = new Button[9];
    int [] ID = new int[]{R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8};
    Button btnPlus, btnMinus, btnResult, btnDiv, btnMult, btnDel;
    TextView result;
    String[] varsArray;
    ArrayList<Double> varsList = new ArrayList<Double>();
    ArrayList<String> operations = new ArrayList<String>();
    String mystr;
    String[] calcArray;
    ArrayList<String> calcOper = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textViewResult);

        btnPlus = findViewById(R.id.buttonPlus);
        btnPlus.setOnClickListener(btnClick);

        btnMinus = findViewById(R.id.buttonMinus);
        btnMinus.setOnClickListener(btnClick);

        btnResult = findViewById(R.id.buttonResult);
        btnResult.setOnClickListener(btnClick);

        btnDiv = findViewById(R.id.buttonDiv);
        btnDiv.setOnClickListener(btnClick);

        btnMult = findViewById(R.id.buttonMult);
        btnMult.setOnClickListener(btnClick);

        btnDel = findViewById(R.id.buttonDel);
        btnDel.setOnClickListener(btnClick);

        for(int i = 0; i < 9; i++) {
            btn[i] = findViewById(ID[i]);
            btn[i].setOnClickListener(btnClick);
        }

        //setOnClick(btn[0],0);


    }

    View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btn[0]){
                result.append("1");
            }
            if(view == btn[1]){
                result.append("2");
            }
            if(view == btn[2]){
                result.append("3");
            }
            if(view == btn[3]){
                result.append("4");
            }
            if(view == btn[4]){
                result.append("5");
            }
            if(view == btn[5]){
                result.append("6");
            }
            if(view == btn[6]){
                result.append("7");
            }
            if(view == btn[7]){
                result.append("8");
            }
            if(view == btn[8]){
                result.append("9");
            }
            if(view == btnPlus) {
                //var1 = Double.parseDouble(result.getText().toString());
                //mystr = result.getText().toString();
                //mystr += "_";
                operations.add("plus");
                result.append("+");
            }
            if(view == btnMinus) {
                operations.add("minus");
                result.append("-");
            }
            if(view == btnDiv){
                operations.add("division");
                result.append(":");
            }
            if(view == btnMult){
                operations.add("multiplication");
                result.append("x");
            }
            if(view == btnDel){
                result.setText("");
                mystr = "";
                operations.clear();
            }
            if(view == btnResult) {
                mystr = result.getText().toString();
                mystr = mystr.replaceAll("[^0-9]", "_");
                varsArray = mystr.split("_");
                result.setText("");
                Double[] vars = new Double[varsArray.length];
                for(int i = 0; i < varsArray.length; i++){
                    vars[i] = Double.parseDouble(varsArray[i]);
                }
                //Sort(operations,varsArray);
                //result.append(Calculation(calcOper,calcArray));

                //result.append(Calculation(operations,varsArray));
                result.append(calcLogic(operations, vars).toString());
            }

        }
    };
    private Double calcLogic(ArrayList<String> operat, Double[] varsArr){
        String[] newArr = new String[varsArr.length-2];//уменьшаем исходный массив на 1, поэтому -2
        double result = 0;
        while(varsArr.length != 1) {
            for (int i = 0; i < operat.size(); i++) {
                if (operat.get(i) == "multiplication") {
                    varsArr[i] = varsArr[i] * varsArr[i + 1];
                    varsArr[i + 1] = 0.0;
                    operat.set(i, "");
                    varsArr = Shift(varsArr, operat, i + 1);
                    operat = Shift2(varsArr, operat, operat.size(), i);
                }
            }
            for (int i = 0; i < operat.size(); i++) {
                if (operat.get(i) == "division") {
                    varsArr[i] = varsArr[i] / varsArr[i + 1];
                    varsArr[i + 1] = 0.0;
                    operat.set(i, "");
                    varsArr = Shift(varsArr, operat, i + 1);
                    operat = Shift2(varsArr, operat, operat.size(), i);
                }
            }
            if(operat.get(0) != ""){
                //если убрать проверку, то будет исключение
                for (int i = 0; i < operat.size(); i++) {
                    if (operat.get(i) == "plus") {
                        varsArr[i] = varsArr[i] + varsArr[i + 1];
                        varsArr[i + 1] = 0.0;
                        operat.set(i, "");
                        varsArr = Shift(varsArr, operat, i + 1);
                        operat = Shift2(varsArr, operat, operat.size(), i);
                    }
                    else {
                        varsArr[i] = varsArr[i] - varsArr[i + 1];
                        varsArr[i + 1] = 0.0;
                        operat.set(i, "");
                        varsArr = Shift(varsArr, operat, i + 1);
                        operat = Shift2(varsArr, operat, operat.size(), i);
                    }
                }
            }
        }
        return varsArr[0];
    }
    private Double[] Shift(Double[] varsArr, ArrayList<String> operat, int index) {
        System.out.println("say hi");
        Double[] newArr = new Double[varsArr.length-1];
        if (index != 0) {
            for (int i = index; i < varsArr.length - 1; i++) {
                varsArr[i] = varsArr[i + 1];
            }
            for (int i = 0; i < newArr.length; i++){
                newArr[i] = varsArr[i];
            }
            return newArr;
        }
        else
            return varsArr;
    }
    private ArrayList<String> Shift2(Double[] varsArr, ArrayList<String> operat, int size, int index) {
        //Double[] newArr = new Double[varsArr.length-1];
            ArrayList<String> oper = new ArrayList<String>();
            if(size != 1) {
                //for (int i = index; i < size - 1; i++) {
                //    if(i < oper.size())
                //        oper.set(i, operat.get(i + 1));
                //    else
                //        oper.add(i, operat.get(i + 1));
                //}
                for(int i = index; i < operat.size()-1;i++){
                    operat.set(i, operat.get(i+1));
                }
                for(int i = 0; i < operat.size()-1;i++){
                    oper.add(operat.get(i));
                }
                return oper;
            }
            else{
                oper.add(operat.set(size-1,""));
            }
            return oper;
    }
    private String Calculate(ArrayList<String> operat, String[] varsArr){
        double result = 0;
        result = Double.parseDouble(varsArr[0]);
        for(int i = 0; i < operat.size(); i++) {
            if (operat.get(i) == "plus")
                result += Double.parseDouble(varsArr[i+1]);
            if (operat.get(i) == "minus")
                result -= Double.parseDouble(varsArr[i+1]);
            if (operat.get(i) == "multiplication")
                result *= Double.parseDouble(varsArr[i+1]);
            if (operat.get(i) == "division")
                result /= Double.parseDouble(varsArr[i+1]);
        }
        return Double.toString(result);
    }
    private void Sort(ArrayList<String> operat, String[] varsArr){
        for(int i = 0; i < operat.size()-1; i++ ){
            if(operat.get(i).equals("multiplication")){
                calcArray[i]=Double.toString((Double.parseDouble(varsArr[i])*Double.parseDouble(varsArr[i+1])));
            }
            if(operat.get(i).equals("division")){
                calcArray[i]=Double.toString((Double.parseDouble(varsArr[i])/Double.parseDouble(varsArr[i+1])));
            }
            if((operat.get(i).equals("plus") || operat.get(i).equals("minus") )& (!operat.get(i + 1).equals("multiplication") & !operat.get(i + 1).equals("division"))) {
                calcArray[i]=Double.toString(Double.parseDouble(varsArr[i+1]));
            }
        }
        for(int i = 0; i<operat.size();i++){
            if(operat.get(i) == "plus" || operat.get(i) == "minus")
                calcOper.add(operat.get(i));
        }
    }
    private String Calculation(ArrayList<String> operat, String[] varsArr){
        double result = 0;
        result = Double.parseDouble(varsArr[0]);
        for(int i = 0; i < operat.size(); i++) {
            if(operat.get(i) == "multiplication"){
                result = Double.parseDouble(varsArr[i]) *  Double.parseDouble(varsArr[i+1]);
                varsArr[i] = Double.toString(result);
                varsArr[i+1] = Double.toString(result);
            }
            if(operat.get(i) == "division"){
                result = Double.parseDouble(varsArr[i]) /  Double.parseDouble(varsArr[i+1]);
                varsArr[i] = Double.toString(result);
                varsArr[i+1] = Double.toString(result);
            }
        }
        for(int i = 0; i < operat.size(); i++) {
            if (operat.get(i) == "plus"){
                result = Double.parseDouble(varsArr[i]) + Double.parseDouble(varsArr[i+1]);
                varsArr[i] = Double.toString(result);
                varsArr[i+1] = Double.toString(result);
                if(operat.size()- i > 1)
                    varsArr[i+2] = Double.toString(result);
            }
            if (operat.get(i) == "minus"){
                result = Double.parseDouble(varsArr[i]) - Double.parseDouble(varsArr[i+1]);
                varsArr[i] = Double.toString(result);
                varsArr[i+1] = Double.toString(result);
                if(operat.size()- i > 1)
                    varsArr[i+2] = Double.toString(result);
            }
        }
        System.out.println( "       " + operat.size());
        return Double.toString(result);
    }
    ;
    /*private void setOnClick(final Button btn, final int number){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setText(number);
            }
        });
    }*/
}
