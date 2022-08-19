/**
 * «Калькулятор счёта» — консольное приложение, которое будет задавать вопросы в консоли,
 * считывать пользовательский ввод, и в результате показывать, список товаров и сумму для каждого из людей.
 * Важно. Приложение считает сумму, которую должен заплатить каждый поровну.
 *
 * Пояснение. В текущей задаче не несёт большой ценности каждый конкретный товар, важнее их общая стоимость,
 * поэтому решено создать объект со всем списком товаров через класс ItemList.
 * При этом каждый товар в формате "Название товара [рубли.копейки]" сохраняется в массиве товаров списка.
 *
 * Много проблем было с классом Scanner. Хотелось сделать нормальный ввод для пользователя, но методы nextLine()
 * и nextDouble(), размещенные последовательно, не давали желаемый результат. Пришлось вынести их в отдельные методы.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final double INPUT_ERROR = -1.0; // константа для обработки ошибки ввода числовых значений

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        ItemsList itemsForCheck = new ItemsList();  // создаём новый объект списка товаров
        int peoplesInCheck;                         // количество человек в счёте
        String itemName;                            // название товара
        double itemPrice;                           // стоимость товара

        System.out.println("На сколько человек разделить счёт? (введите целое число от 2 и больше)");

        /* Ввод пользователем вручную количества человек в счёте. Допустимое значение - от 2 и больше */
        while (true) {
            peoplesInCheck = scanPeoplesInCheck();
            if (peoplesInCheck != -1) {
                break;
            }
        }

        /* Ввод пользователем вручную товаров и их стоимости */
        while (true) {
            System.out.println("Введите название товара: ");
            itemName = scanItemName();
            itemPrice = scanItemPrice();

            if (itemPrice != -1.0) {
                itemsForCheck.addItem(itemName, itemPrice);
                System.out.println("Товар добавлен. Хотите добавить ещё один товар? Да - наберите любой символ, Нет - наберите \"Завершить\"");
                String command = scanner.next();
                if (command.equalsIgnoreCase("Завершить")) {
                    break;
                }
            }
            else {
                String command = scanner.next();
                if (command.equalsIgnoreCase("Завершить")) {
                        break;
                    }
                }
        }

        /* Построчный вывод всех добавленных товаров */
        for (int i = 0; i < itemsForCheck.items.size(); i++) {
            System.out.println("Добавленные товары: " + itemsForCheck.items.get(i));
        }

        double splitWise = itemsForCheck.totalPrice / peoplesInCheck;       // сумма к оплате каждым человеком
        int digit = (int) Math.round(splitWise);                            // оставляем целое число для определения склоенения

        String messageSummary = "Каждый человек должен заплатить %.2f " + roubleCase(digit) + ".";
        System.out.println(String.format(messageSummary, splitWise));
   }

    /* Метод для правильного склонения слова "рубль", исходная переменная не несёт ценности, поэтому смело меняем её */
    /* Склонения слова при значениях от 0 до 19 рублей имеют отдельные правила */
    /* Для чисел больше 100 правила зависят от последних двух разрядов, т.е. можем сразу работать с остатком от деления на 100 */

    public static String roubleCase(int digit) {
        if (digit >= 100) {                                 //оставляем 2 значащих разряда для чисел больше 100
            digit = digit % 100;
        }
        if (digit >= 20) {                                  //оставляем 1 значащий разряд для чисел больше 20
            digit = digit % 10;
        }
        if (digit == 0 || digit >= 5) {    //числа 0, 5-19, для которых правильный вариант "рублей"
            return "рублей";
        }
        else if (digit >= 2) {                //числа 2-4, для которых правильный вариант "рубля"
            return "рубля";
        }
        else {                                              //число 1, для которого правильный вариант "рубль"
            return "рубль";
        }
    }

    /* Метод для ввода количества человек в счёте */
    public static int scanPeoplesInCheck() {
            Scanner scanner = new Scanner(System.in);

            try {
                int peoples = scanner.nextInt();
                if (peoples > 1) {
                    return peoples;
                }
                else if (peoples == 1) {
                    System.out.println("Ошибка! В вашем случае незачем делить счёт. Введите целое число от 2 и больше.");
                    return (int) INPUT_ERROR;
                }
                else {
                    System.out.println("Ошибка! Введите целое число от 2 и больше.");
                    return (int) INPUT_ERROR;
                }
            }

            catch (InputMismatchException e) {
                System.out.println("Ошибка! Введите целое число от 2 и больше.");
                return (int) INPUT_ERROR;
            }
        }

    /* Метод для ввода названия товара */
    public static String scanItemName() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /* Метод для ввода стоимости товара */
    public static double scanItemPrice() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите стоимость товара (между рублями и копейками поставьте запятую!): ");
            return scanner.nextDouble();
        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка! Данные не сохранены. Хотите добавить ещё товар? Да - наберите любой символ, Нет - наберите \"Завершить\"");
            return INPUT_ERROR;
        }
    }
}

