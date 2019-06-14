package android.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import static android.example.calculator.LogicBackend.calcLogic;
import static android.example.calculator.LogicFrontend.frontendLogic;
//можно поставить несколько операций подряд - нужно исправить

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
    Boolean opetatWasChosed = true;
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

    }
    View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //при нажатии на кнопку вызывается метод frontendLogic из LogicFrontend.java
            //сохраняем результат вызова в булевую переменную для регулирования повторения операций
            //внутри метода frontendLogic происходит обращение к методу logicCalc из LogicBackend.java
            opetatWasChosed = frontendLogic(view, result, btn, btnPlus, btnMinus,
                    btnDiv, btnMult,  btnDel, btnResult, opetatWasChosed, operations, varsArray);
        }
    };


}
