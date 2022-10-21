public class Main {

    static class Param {
        String a;
        String symbol;
        String b;
    }

    static class Status {
        int a;
        int b;
    }

    static Param parser(String input) {
        Param param = new Param();
        int count = 0;

        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '/' ) {
                param.a = input.substring(0, i).trim();
                param.symbol = input.substring(i, i + 1);
                param.b = input.substring(i + 1).trim();
                count++;
            }
        }
        if (count != 1) {
            throw new RuntimeException();
        }
        return param;
    }

    static String calculateArabic(Param param) {
        int a = Integer.parseInt(param.a);
        int b = Integer.parseInt(param.b);

        if ((a < 1 || a > 10) || (b < 1 || b > 10)) {
            throw new RuntimeException();
        }
        switch (param.symbol) {
            case "+": return String.valueOf(a + b);
            case "-": return String.valueOf(a - b);
            case "*": return String.valueOf(a * b);
            case "/": return String.valueOf(a / b);
            default:  return null;
        }
    }

    static int romanToArabic(String str) {
        int[] arabic = new int[] {1, 4, 5, 9, 10};
        String[] roman = new String[] {"I", "IV", "V", "IX", "X"};
        int ret = 0;
        int i = arabic.length - 1;
        int pos = 0;

        for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) != 'I' && str.charAt(j) != 'V' && str.charAt(j) != 'X') {
                throw new RuntimeException();
            }
        }
        while (i >= 0 && pos < str.length()) {
            if (str.startsWith(roman[i], pos)) {
                ret += arabic[i];
                pos += roman[i].length();
            } else {
                i--;
            }
        }
        return (ret);
    }

    static String arabicToRoman(int number) {
        int[] arabic = new int[] {1, 4, 5, 9, 10, 40, 50, 90, 100};
        String[] roman = new String[] {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        String ret = "";
        int i = arabic.length - 1;

        while (number > 0) {
            if (number >= arabic[i]) {
                ret = ret + roman[i];
                number -= arabic[i];
            } else {
                i--;
            }
        }
        return (ret);
    }

    static String calculateRoman(Param param) {
        int a = romanToArabic(param.a);
        int b = romanToArabic(param.b);

        if ((a < 1 || a > 10) || (b < 1 || b > 10)) {
            throw new RuntimeException();
        }
        switch (param.symbol) {
            case "+": return arabicToRoman(a + b);
            case "-":
                if (a - b < 1)
                    throw new RuntimeException();
                else
                    return arabicToRoman(a - b);
            case "*": return arabicToRoman(a * b);
            case "/": return arabicToRoman(a / b);
            default:  return null;
        }
    }

    static int getStatus(String str) {
        int flag = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                flag += 1;
            }
        }
        if (flag != str.length() && flag != 0) {
            throw new RuntimeException();
        }
        return (flag);
    }

    public static String calc(String input) {
        Param param = parser(input);
        Status status = new Status();
        status.a = getStatus(param.a);
        status.b = getStatus(param.b);
        if (status.a == 0 && status.b == 0) {
            return (calculateRoman(param));
        } else if (status.a == 0 && status.b > 0) {
            throw new RuntimeException();
        } else if (status.a > 0 && status.b == 0) {
            throw new RuntimeException();
        } else if (status.a > 0 && status.b > 0) {
            return (calculateArabic(param));
        }
        return input;
    }
}

