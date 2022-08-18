import java.util.ArrayList;

public class ItemsList {

    ArrayList<String> items = new ArrayList<String>();  // создаём список строк с товарами в формате "Название товара [рубли.копейки]"
    double totalPrice = 0.0;                            // общая стоимость товаров

    /* Метод добавляет очередной товар в список и считает общую текущую стоимость товаров */
    void addItem (String itemName, double itemPrice) {
        String wholeItem = itemName + " [" + itemPrice + "]";   // формируем строку с товаром в нужном формате
        items.add(wholeItem);                                   // добавляем полученную строку в список товаров
        totalPrice += itemPrice;                                // считаем общую стоимость товаров
    }

}