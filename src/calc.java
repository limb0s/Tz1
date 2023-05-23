import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Objects;
import java.util.Scanner;

public class calc {
    public static void main(String [] args) throws Exp {
        Conv conv = new Conv();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите ваш пример(Каждое из чисел от 1 до 10): ");
        String num = scan.nextLine();
        int index = -1;
        for (int i = 0; i < actions.length; i++) {
            if (num.contains(actions[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new Exp("Кажется вы ошиблись. Укажите полноценный пример");
        }
        String[] d = num.split(regexActions[index]);
        if(d.length < 2){
            throw new Exp("Укажите корректное выражение используя числа от 1 до 10");
        }
        Pattern p = Pattern.compile("[\\p{InCyrillic}\\sa-zA-H[J-U][W-W][Y-Z]!@#$%&()_=|<>?{}.\\[\\]~]");
        Matcher m = p.matcher(d[0]);
        Matcher g = p.matcher(d[1]);
        if (m.find() || g.find()) {
            throw new Exp("Укажите корректное выражение используя числа от 1 до 10");
        }
        if (Objects.equals(d[0], "")){
            throw new Exp("Укажите корректное выражение используя числа от 1 до 10");
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
                                    throw new Exp("Результат при рассчёте римских цифр должен быть >= 1");
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
                throw new Exp("Числа должны быть одного формата");
            }
        }
        else {
            throw new Exp("Введите действие между двумя числами");
        }
    }
}
