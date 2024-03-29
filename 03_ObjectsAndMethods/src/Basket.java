public class Basket {

    private static int count = 0;
    private String items = "";
    private int totalPrice = 0;
    private int limit;
    // Общая масса всех товаров
    private double totalWeight;

    // Общее количество всех товаров во всех корзинах
    private static int totalBasketsItemsCount = 0;
    // Общая стоимость всех товаров во всех корзинах
    private static int totalBasketsPrice = 0;

    public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
        // Начальное значение массы всех товаров равно 0
        this.totalWeight = 0;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static void incTotalBasketsItemsCount(int count) {
        Basket.totalBasketsItemsCount += count;
    }

    public static void incTotalBasketsPrice(int price) {
        Basket.totalBasketsPrice += price;
    }

    // Средняя цена товара во всех корзинах
    public static double getAveragePriceItemAllBaskets() {
        return Basket.totalBasketsPrice / Basket.totalBasketsItemsCount;
    }

    // Средняя стоимости корзины
    public static double getAveragePriceBasket() {
        return Basket.totalBasketsPrice / Basket.count;
    }

    public static int getCount() {
        return count;
    }

    public static void increaseCount(int count) {
        Basket.count = Basket.count + count;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " + count + " шт. - " + price;
        totalPrice = totalPrice + count * price;

        Basket.incTotalBasketsItemsCount(count);
        Basket.incTotalBasketsPrice(totalPrice);
    }

    public void add(String name, int price, int count, double weight) {
        add(name, price, count);
        this.totalWeight += weight;
    }

    public double getTotalWeight() {
        return this.totalWeight;
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}
