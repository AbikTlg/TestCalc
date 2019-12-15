import java.util.InputMismatchException;
import java.util.Scanner;

public class CalcDataR  {
    private int number1,number2;
    private char operation;
    private double result;
    private boolean isArabicNumber;
    private CalcRomanConv converter = new CalcRomanConv();

    public void readData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input: ");
        String data = scanner.nextLine();
        data = data.toUpperCase();

        if (data.isEmpty()) {
            throw new NullPointerException("Пустой ввод");
        }
        if (checkOperation(data)) {
            if (checkNumber1(data)) {
                if (checkNumber2(data)) {
                    calculate();
                }
                else {
                    throw new NumberFormatException("Error.Numbers must be integers in 1..10 or I..X range ");
                }
            }
            else {
                throw new NumberFormatException("Error.Numbers must be integers in 1..10 or I..X range ");
            }
        }
        else {
             throw new InputMismatchException("Incorrect");
        }

    }

    private boolean checkOperation(String data) {
         if (data.contains("+") && !data.startsWith("+") && !data.endsWith("+")) {
            data = data.replaceFirst("[+]"," ");
            if (!data.contains("+") && !data.contains("-") && !data.contains("*") && !data.contains("/")) {
                data = data.replace(" ", "+");
                operation = '+';
                return true;
            }
        }
        else if (data.contains("-") && !data.startsWith("-") && !data.endsWith("-")) {
            data = data.replaceFirst("[-]"," ");
            if (!data.contains("+") && !data.contains("-") && !data.contains("*") && !data.contains("/")) {
                data = data.replace("", "-");
                operation = '-';
                return true;
            }
        }
        else if (data.contains("*") && !data.startsWith("*") && !data.endsWith("*")) {
            data = data.replaceFirst("[*]"," ");
            if (!data.contains("+") && !data.contains("-") && !data.contains("*") && !data.contains("/")) {
                data = data.replace(" ", "*");
                operation = '*';
                return true;
            }
        }
        else if (data.contains("/") && !data.startsWith("/") && !data.endsWith("/")) {
            data = data.replaceFirst("[/]"," ");
            if (!data.contains("+") && !data.contains("-") && !data.contains("*") && !data.contains("/")) {
                data = data.replace(" ", "/");
                operation = '/';
                return true;
            }
        }
            return false;
    }


    private boolean checkNumber1(String data){
        String currentNumber = data.substring(0,data.indexOf(operation));
        for (int i = 1;i <= 10; i++){
            if (currentNumber.equals(String.valueOf(i))){
                number1 = Integer.valueOf(currentNumber);
                isArabicNumber = true;
                return true;
            }
        }
        for (String romanNumber : converter.getRomanNumbers().keySet()){
            if (currentNumber.equals(romanNumber)){
                number1 = converter.romanToArabic(currentNumber);
                isArabicNumber = false;
                return true;
            }
        }
        return false;
    }

    private boolean checkNumber2(String data){
         String currentNumber = data.substring(data.indexOf(operation)+1);
        for (int i = 1;i <= 10; i++){
            if (currentNumber.equals(String.valueOf(i))){
                if (isArabicNumber) {
                    number2 = Integer.valueOf(currentNumber);
                    return true;
                }
                else {
                     throw new NumberFormatException("Numbers must be in the same format");
                }
            }
        }

        for (String romanNumber : converter.getRomanNumbers().keySet()){
            if (currentNumber.equals(romanNumber)){
                if (!isArabicNumber) {
                    number2 = converter.romanToArabic(currentNumber);
                    return true;
                }
                else {
                    throw new NumberFormatException("Numbers must be the same format");
                }
            }
        }
        return false;
    }

    private void calculate(){
       switch (operation){
           case '+' : result = number1 + number2; break;
           case '-' : result = number1 - number2; break;
           case '*' : result = number1 * number2; break;
           case '/' : result = (double) number1 / number2; break;
       }
       if (result%1==0){
           if (isArabicNumber) {
               System.out.println("Output: \n"+ (int)result);
           }
           else{
               System.out.println("Output: \n "+ converter.arabicToRoman((int)result));
           }
       }
       else {
           if (isArabicNumber) {
               System.out.println("Output: \n"+ result);
           }
           else {
               throw new NumberFormatException("It can't be a fractional number");
           }
       }
    }
}