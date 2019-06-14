package android.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.example.calculator.LogicBackend.calcLogic;

public class LogicFrontend {

    public static Boolean frontendLogic(View view, TextView result, Button[] btn, Button btnPlus, Button btnMinus,
                                     Button btnDiv, Button btnMult, Button btnDel, Button btnResult, Boolean opetatWasChosed, ArrayList<String> operations, String[] varsArray){
        String mystr;
        //в цикле проверяется нажатие на цифру
        for(int i = 0; i < 9; i++){
            if(view == btn[i]){
                opetatWasChosed = false;
                result.append(Integer.toString(i+1));
            }
        }
        if(view == btnPlus && !opetatWasChosed) {
            operations.add("plus");
            opetatWasChosed = true;
            result.append("+");
        }
        if(view == btnMinus && !opetatWasChosed) {
            operations.add("minus");
            opetatWasChosed = true;
            result.append("-");
        }
        if(view == btnDiv && !opetatWasChosed){
            operations.add("division");
            opetatWasChosed = true;
            result.append(":");
        }
        if(view == btnMult && !opetatWasChosed){
            operations.add("multiplication");
            opetatWasChosed = true;
            result.append("x");
        }
        if(view == btnDel){
            opetatWasChosed = false;
            result.setText("");
            mystr = "";
            operations.clear();
        }
        if(view == btnResult && !opetatWasChosed) {
            opetatWasChosed = true;
            mystr = result.getText().toString();
            mystr = mystr.replaceAll("[^0-9]", "_");
            varsArray = mystr.split("_");
            result.setText("");
            if(varsArray.length == 1)
            {
                //если введено только одно число и нажато "=", без этого будет исключение
                result.setText(varsArray[0]);
                return true;
            }
            Double[] vars = new Double[varsArray.length];
            for(int i = 0; i < varsArray.length; i++){
                vars[i] = Double.parseDouble(varsArray[i]);
            }
            result.append(calcLogic(operations, vars).toString());

        }
        return  opetatWasChosed;
    }
}
