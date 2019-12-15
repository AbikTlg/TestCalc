import java.util.HashMap;

public class CalcRomanConv {
    private HashMap<String,Integer> romanNum;

    public CalcRomanConv(){
        romanNum = new HashMap<>();
        romanNum.put("I",1);
        romanNum.put("II",2);
        romanNum.put("III",3);
        romanNum.put("IV",4);
        romanNum.put("V",5);
        romanNum.put("VI",6);
        romanNum.put("VII",7);
        romanNum.put("VIII",8);
        romanNum.put("IX",9);
        romanNum.put("X",10);
    }

    
    public int romanToArabic(String romanNumber){
        return romanNum.get(romanNumber);
    }
      public String arabicToRoman(int arabicNumber) {
        String romanNumber = "";

        if (arabicNumber==0){
            return "0";
        }
        if (arabicNumber<0){
            arabicNumber *= -1;
            romanNumber += "-";
        }

        while (arabicNumber == 100) {
            romanNumber += "C";
            arabicNumber -= 100;
        }
        while (arabicNumber >= 90) {
            romanNumber += "XC";
            arabicNumber -= 90;
        }
        while (arabicNumber >= 50) {
            romanNumber+= "L";
            arabicNumber -= 50;
        }
        while (arabicNumber >= 40) {
            romanNumber += "XL";
            arabicNumber -= 40;
        }
        while (arabicNumber >= 10) {
            romanNumber += "X";
            arabicNumber -= 10;
        }
        while (arabicNumber >= 9) {
            romanNumber += "IX";
            arabicNumber -= 9;
        }
        while (arabicNumber >= 5) {
            romanNumber += "V";
            arabicNumber -= 5;
        }
        while (arabicNumber >= 4) {
            romanNumber += "IV";
            arabicNumber -= 4;
        }
        while (arabicNumber >= 1) {
            romanNumber += "I";
            arabicNumber -= 1;
        }
        return romanNumber;
    }

    public HashMap<String,Integer> getRomanNumbers(){
        return romanNum;
    }

}