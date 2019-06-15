package android.example.calculator;

import java.util.ArrayList;

public class LogicBackend {
    public static Double calcLogic(ArrayList<String> operat, Double[] varsArr){
        while(varsArr.length != 1) {
            for (int i = 0; i < operat.size(); i++) {
                System.out.println("________iamincycle" + operat.size() + "__" + i);
                if (operat.get(i) == "multiplication") {
                    System.out.println("________iamhere");
                            varsArr[i] = varsArr[i] * varsArr[i + 1];
                    varsArr[i + 1] = 0.0;
                    operat.set(i, "");
                    varsArr = Shift(varsArr, operat, i + 1);
                    operat = Shift2(varsArr, operat, operat.size(), i);
                    i--;//т.к. мы только что уменьшили operat на 1
                }

            }
            for (int i = 0; i < operat.size(); i++) {
                if (operat.get(i) == "division") {
                    varsArr[i] = varsArr[i] / varsArr[i + 1];
                    varsArr[i + 1] = 0.0;
                    operat.set(i, "");
                    varsArr = Shift(varsArr, operat, i + 1);
                    operat = Shift2(varsArr, operat, operat.size(), i);
                    i--;//т.к. мы только что уменьшили operat на 1
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
    private static Double[] Shift(Double[] varsArr, ArrayList<String> operat, int index) {
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
    private static ArrayList<String> Shift2(Double[] varsArr, ArrayList<String> operat, int size, int index) {
        ArrayList<String> oper = new ArrayList<String>();
        if(size != 1) {
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
}
