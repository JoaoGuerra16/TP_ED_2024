package tp_ed_2024.Collections.Stacks;


import java.util.Scanner;

public class PostFix {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a expressão que deseja:(tem que por espaços) ");
        String expressao = scanner.nextLine();

        Double resultado = avaliarPostFix(expressao);
        System.out.println("Resultado: " + resultado);

        scanner.close();

    }

    public static double avaliarPostFix(String expressao) {
        double result = 0;
        LinkedStack<Double> stack = new LinkedStack<>();

        String[] expressaoSplit = expressao.split(" "); ;

        for (String token : expressaoSplit) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));

            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Expressão postfix inválida.");

                }
                Double num2 = stack.pop();
                Double num1 = stack.pop();

                switch (token) {
                    case "+":
                        result = num1 + num2;
                        break;

                    case "-":
                        result = num1 - num2;
                        break;

                    case "*":
                        result = num1 * num2;
                        break;

                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Divisão por zero.");
                        }
                        result = num1 / num2;
                        break;

                    default:
                        System.out.println("Operação desconhecida");
                        break;
                }
                stack.push(result);
            }

        }

      
        return stack.pop();

    }

    public static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {

            return false;

        }
    }

}
