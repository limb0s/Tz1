import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Objects;
import java.util.Scanner;

public class tz {
    public static void main(String [] args) throws Exp {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите ваш пример(Каждое из чисел от 1 до 10): ");
        String str1 = scan.nextLine();
        calc(str1);
    }
    public static String calc(String input) throws Exp {
        Conv conv = new Conv();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "\\-", "\\/", "\\*"};
        int index = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new Exp("строка не является математической операцией");
        }
        String[] d = input.split(regexActions[index]);
        if(d.length < 2){
            throw new Exp("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        d[0] = d[0].trim();
        d[1] = d[1].trim();
        Pattern p = Pattern.compile("[\\p{InCyrillic}\\sa-zA-H[J-U][W-W][Y-Z]!@#$%&()_=|<>?{}.\\[\\]~]");
        Matcher m = p.matcher(d[0]);
        Matcher g = p.matcher(d[1]);
        if (m.find() || g.find()) {
            throw new Exp("Укажите корректное выражение используя числа от 1 до 10");
        }
        if (Objects.equals(d[0], "")){
            throw new Exp("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (d.length == 2) {
            if (conv.isRoman(d[0]) == conv.isRoman(d[1])) {
                int a, b;
                boolean isRoman = conv.isRoman(d[0]);
                if (isRoman) {
                    a = conv.romanToInt(d[0]);
                    b = conv.romanToInt(d[1]);
                } else {
                    a = Integer.parseInt(d[0]);
                    b = Integer.parseInt(d[1]);
                }
                if (a >= 1 && a <= 10 && b >= 1 && b <= 10) {
                    int res;
                    switch (actions[index]) {
                        case "+":
                            if (isRoman) {
                                res = a + b;
                                System.out.println("Результат: " + conv.intToRoman(res));
                                break;
                            } else {
                                res = a + b;
                                System.out.println("Результат: " + res);
                                break;
                            }
                        case "-":
                            if (isRoman) {
                                res = a - b;
                                if (res < 1) {
                                    throw new Exp("в римской системе нет нуля и отрицательных чисел");
                                } else {
                                    System.out.println("Результат: " + conv.intToRoman(res));
                                    break;
                                }
                            } else {
                                res = a - b;
                                System.out.println("Результат: " + res);
                                break;
                            }
                        case "*":
                            if (isRoman) {
                                res = a * b;
                                System.out.println("Результат: " + conv.intToRoman(res));
                                break;
                            } else {
                                res = a * b;
                                System.out.println("Результат: " + res);
                                break;
                            }
                        case "/":
                            if (isRoman) {
                                res = a / b;
                                if (res < 1) {
                                    throw new Exp("Результат при рассчёте римских цифр должен быть >= 1");
                                } else {
                                    System.out.println("Результат: " + conv.intToRoman(res));
                                    break;
                                }
                            } else {
                                res = a / b;
                                System.out.println("Результат: " + res);
                                break;
                            }
                    }
                } else {
                    throw new Exp("Используйте числа от 1 до 10");
                }
            } else {
                throw new Exp("используются одновременно разные системы счисления");
            }
        }
        else {
            throw new Exp("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        return input;
    }
}
