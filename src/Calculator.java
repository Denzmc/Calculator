

import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Calculator {


    public static void main(String[] args) throws Exception {

        boolean arabic1 = false;
        boolean arabic2 = false;
        boolean roman1 = false;
        boolean roman2 = false;

        int num1 ;
        int num2 ;
        int result;

        String [] numArabic = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        String[] numRoman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        System.out.println("Введите выражение [2+2] или два римских числа от I до X:[V+V] + Enter ");

        Scanner scanner = new Scanner(System.in);

        String userInput1 = scanner.nextLine();

        String userInput = userInput1.replaceAll("\\s","");


        char[] under_char = userInput.toCharArray();

        char operation = signOfOperation(under_char );

        String [] arrString = userInput.split("[+-/*]");

        if (arrString.length > 2) throw new Exception("Формат математической операции " +
                "не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        if (arrString.length < 2) throw new Exception("Строка не является математической операцией");



        for (String a: numArabic) {
            if (a.equals(arrString[0]))
                arabic1 = true;

            if (a.equals(arrString[1]))
                arabic2 = true;

        }
        for (String b: numRoman){
            if (b.equals(arrString[0]))
                roman1 = true;

            if (b.equals(arrString[1]))
                roman2 = true;

        }
        if ((arabic1 && roman2) || (arabic2 && roman1)) throw new Exception(
                "Используются одновременно разные системы счисления"        );

        if (!arabic1 && !arabic2 && !roman1 && !roman2) throw new Exception(
                "Неверный формат данных"        );

        if (arabic1 && arabic2){
            num1 = Integer.parseInt(arrString[0]);
            num2 = Integer.parseInt(arrString[1]);
            result = calculated(num1, num2, operation);
            System.out.println("--Результат для арабских цифр----");
            System.out.println(result);
        }
        if (roman1 && roman2){
            num1 = romanToNumber(arrString[0]);
            num2 = romanToNumber(arrString[1]);
            result = calculated(num1, num2, operation);
            if (result < 0) throw new Exception("В римской системе нет отрицательных чисел");

            System.out.println("---Результат для римских цифр----");
            String resultRoman = convertNumToRoman(result);
            System.out.println(resultRoman);
        }

    }

    private static int romanToNumber (String roman) {
        try {
            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        return -1;
    }

    public static int calculated (int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");

                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }

    private static String convertNumToRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }

    private static char signOfOperation(char [] arr){

        char operator = ' ';

        for (char i : arr) {

            if (i == '+') {
                operator = '+';
            }
            if (i == '-') {
                operator = '-';
            }
            if (i == '*') {
                operator = '*';
            }
            if (i == '/') {
                operator = '/';
            }
        }
        return operator;
    }


}
