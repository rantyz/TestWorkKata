import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println(calc(userInput));
    }

    public static String calc(String input) throws Exception {
        String operator, result;
        int num1, num2;
        boolean isRoman;

        input = input.replaceAll("\\s", "");
        input = input.toUpperCase();
        String[] operands = input.split("[+\\-*/]");

        if (operands.length != 2) throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)"); {
            operator = getOperator(input);
        }

        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            num1 = Roman.convertToArabic(operands[0]);
            num2 = Roman.convertToArabic(operands[1]);
            isRoman = true;
        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }

        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int arabic = getResult(num1,num2,operator);
        if (isRoman) {
            if (arabic <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            result = Roman.convertToRoman(arabic);
        } else {
            result = String.valueOf(arabic);
        }
        return result;
    }

    public static int getResult(int num1, int num2, String operator)  {
        if (operator.equals("+")) {
            return num1 + num2;
        }
        if (operator.equals("-")) {
            return num1 - num2;
        }
        if (operator.equals("*")) {
            return num1 * num2;
        }
        else {
            return num1 / num2;
        }
    }

    static String getOperator(String input) {
        if (input.contains("+")) {
            return "+";
        }
        else if (input.contains("-")) {
            return "-";
        }
        else if (input.contains("*")) {
            return "*";
        }
        else if (input.contains("/")) {
            return "/";
        }
        else {
            return null;
        }
    }
}

class Roman {
    static String[] romanArray = new String[] {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
            "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
            "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};


    public static boolean isRoman(String input) {
        for(int i = 0; i < romanArray.length; i++){
            if (input.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabic(String roman) {
        for(int i = 0; i < romanArray.length; i++){
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabic) {
        return romanArray[arabic];
    }
}