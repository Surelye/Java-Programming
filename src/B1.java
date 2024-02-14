import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class B1 {

    private static BigInteger getOpResult(BigInteger left, BigInteger right, String op) {
        return switch (op) {
            case "ADD" -> left.add(right);
            case "SUB" -> left.subtract(right);
            case "MULT" -> left.multiply(right);
            case "DIV" -> left.divide(right);
            case "REM" -> left.remainder(right);
            case "POW" -> {
                if (right.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0) {
                    yield left.pow(right.intValue());
                } else {
                    throw new ArithmeticException("Слишком большой показатель степени");
                }
            }
            default -> throw new ArithmeticException("Неподдерживаемый тип операции");
        };
    }

    private static BigDecimal BDPow(BigDecimal base, int pow) {
        if (base.compareTo(BigDecimal.ZERO) == 0 || base.compareTo(BigDecimal.ONE) == 0) {
            return base;
        } else if (pow == 0) {
            return BigDecimal.ONE;
        } else if (pow == 1) {
            return base;
        } else if (pow == -1) {
            return BigDecimal.ONE.divide(base, RoundingMode.HALF_EVEN);
        }
        BigDecimal multer = base;
        BigDecimal product = BigDecimal.ONE;
        int absPow = Math.abs(pow);

        while (absPow != 0) {
            while ((absPow & 1) == 0) {
                multer = multer.multiply(multer).setScale(13, RoundingMode.HALF_EVEN);
                absPow >>= 1;
            }
            product = product.multiply(multer).setScale(13, RoundingMode.HALF_EVEN);
            absPow -= 1;
        }

        return (pow < 0) ? BigDecimal.ONE.divide(product, RoundingMode.HALF_EVEN) : product;
    }

    private static BigDecimal getOpResult(BigDecimal left, BigDecimal right, String op) {
        return switch (op) {
            case "ADD" -> left.add(right);
            case "SUB" -> left.subtract(right);
            case "MULT" -> left.multiply(right);
            case "DIV" -> left.divide(right, RoundingMode.HALF_EVEN);
            case "REM" -> left.remainder(right);
            case "POW" -> {
                if (right.subtract(new BigDecimal(right.toBigInteger()))
                        .compareTo(BigDecimal.ZERO) == 0) {
                    if (right.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) <= 0) {
                        yield BDPow(left, right.intValue());
                    } else {
                        throw new ArithmeticException("Слишком большой показатель степени");
                    }
                } else {
                    throw new ArithmeticException("Нецелочисленный показатель степени");
                }
            }
            default -> throw new ArithmeticException("Неподдерживаемый тип операции");
        };
    }

    public static void binaryOp() {
        Scanner scanner = new Scanner(System.in);
        String[] paramsArray;

        System.out.println("> Для выхода при вводе параметров напишите 'exit'.\n");
        while (true) {
            System.out.print("> Введите два вещественных числа и тип операции через пробел: ");
            paramsArray = scanner.nextLine().split("\\s+");
            if (paramsArray.length == 1 && paramsArray[0].equalsIgnoreCase("exit")) {
                break;
            } else if (paramsArray.length != 3) {
                System.out.println("[ERROR] Некорректное число параметров. Попробуйте снова.\n");
                continue;
            }
            paramsArray[2] = paramsArray[2].toUpperCase();

            try {
                BigInteger fNum = new BigInteger(paramsArray[0]);
                BigInteger sNum = new BigInteger(paramsArray[1]);
                BigInteger res = getOpResult(fNum, sNum, paramsArray[2]);
                System.out.printf("> Полученный результат: %s.%n%n", res);
            } catch (NumberFormatException outerNFE) {
                try {
                    BigDecimal fNum = new BigDecimal(paramsArray[0]);
                    BigDecimal sNum = new BigDecimal(paramsArray[1]);
                    BigDecimal res = getOpResult(fNum, sNum, paramsArray[2]);
                    System.out.printf("> Полученный результат: %s.%n%n", res);
                } catch (NumberFormatException innerNFE) {
                    System.out.println("[ERROR] Некорректный формат входных данных." +
                            " Попробуйте снова.\n");
                } catch (ArithmeticException innerAE) {
                    System.out.printf("[WARNING] В ходе работы программы было получено исключение:" +
                            " \"%s\".%n%n", innerAE.getMessage());
                }
            } catch (ArithmeticException outerAE) {
                System.out.printf("[WARNING] В ходе работы программы было получено исключение:" +
                                " \"%s\".%n%n", outerAE.getMessage());
            }
        }
	scanner.close();
    }
}
