package task4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class C4 {

    private final Product[] products = new Product[]{
            new Product("Грибы: белые жареные", 162),
            new Product("Колбаса: варёная Докторская", 257),
            new Product("Крупы и каши: булгур", 342),
            new Product("Масла и жиры: оливковое", 898),
            new Product("Молочные продукты: Actimel Вишня", 74),
            new Product("Мука и мучные изделия: блины", 233),
            new Product("Мясные продукты: баранина", 209),
            new Product("Овощи и зелень: баклажан", 24),
            new Product("Орехи и сухофрукты: арахис", 622),
            new Product("Рыба и морепродукты: амур чёрный", 88),
            new Product("Снэки: попкорн солёный", 407),
            new Product("Сыры и творог: сыр Brie Les Chateaux De France 32%", 364),
            new Product("Сырье и приправы: аджика", 59),
            new Product("Фрукты: персик", 46),
            new Product("Хлебобулочные изделия: брецель", 300),
            new Product("Ягоды: виноград", 65),
            new Product("Яйца: яйцо черепашье", 155),
            new Product("Напитки алкогольные: бурбон", 235),
            new Product("Напитки безалкогольные: вода", 0),
            new Product("Соки и компоты: томатный сок", 21),
            new Product("McDonald's: десерт Маффин с чёрной смородиной", 370)
    };
    private final int numProducts = products.length;
    private final Random random = new Random();

    class Product {

        private final String name;
        private final int calorieContent;

        public Product(String productName, int calorieContent) {
            if (calorieContent < 0) {
                throw new RuntimeException("Некорректная калорийность продукта");
            }
            this.name = productName;
            this.calorieContent = calorieContent;
        }

        public String getName() {
            return name;
        }

        public int getCalorieContent() {
            return calorieContent;
        }
    }

    class ProductQueue {

        private final Queue<Product> productQueue = new LinkedList<>();
        private final int queueSize;
        private final Object IS_NOT_FULL = new Object();
        private final Object IS_NOT_EMPTY = new Object();

        ProductQueue(int queueSize) {
            this.queueSize = queueSize;
        }

        public boolean isEmpty() {
            return productQueue.isEmpty();
        }

        public boolean isFull() {
            return productQueue.size() == queueSize;
        }

        public void waitIsNotFull() throws InterruptedException {
            synchronized (IS_NOT_FULL) {
                IS_NOT_FULL.wait();
            }
        }

        private void notifyIsNotFull() {
            synchronized (IS_NOT_FULL) {
                IS_NOT_FULL.notify();
            }
        }

        public void waitIsNotEmpty() throws InterruptedException {
            synchronized (IS_NOT_EMPTY) {
                IS_NOT_EMPTY.wait();
            }
        }

        public void notifyIsNotEmpty() {
            synchronized (IS_NOT_EMPTY) {
                IS_NOT_EMPTY.notify();
            }
        }

        public void add(Product product) {
            productQueue.add(product);
            notifyIsNotEmpty();
        }

        public Product poll() {
            Product product = productQueue.poll();
            notifyIsNotFull();

            return product;
        }
    }

    class Producer implements Runnable {

        private final ProductQueue productQueue;
        private boolean running = false;

        public Producer(ProductQueue productQueue) {
            this.productQueue = productQueue;
        }

        @Override
        public void run() {
            running = true;
            produce();
        }

        public void produce() {
            while (running) {
                if (productQueue.isFull()) {
                    try {
                        productQueue.waitIsNotFull();
                    } catch (InterruptedException e) {
                        System.out.printf("[ERROR] Во время ожидания потребителей произошла " +
                                "ошибка: %s", e.getMessage());
                        break;
                    }
                }
                if (!running) {
                    break;
                }
                productQueue.add(generateProduct());
            }
        }

        private Product generateProduct() {
            return products[random.nextInt(numProducts)];
        }

        public void stop() {
            running = false;
            productQueue.notifyIsNotFull();
        }
    }

    class Consumer implements Runnable {

        private final String name;
        private final ProductQueue productQueue;
        private final Semaphore semaphore;
        private boolean running = false;
        private int totalCaloriesConsumed = 0;

        Consumer(String name, ProductQueue productQueue, Semaphore semaphore) {
            this.name = name;
            this.productQueue = productQueue;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            running = true;
            consume();
        }

        public void consume() {
            while (running) {
                if (productQueue.isEmpty()) {
                    try {
                        productQueue.waitIsNotEmpty();
                    } catch (InterruptedException e) {
                        System.out.printf("[ERROR] Во время ожидания новых продуктов произошла " +
                                "ошибка: %s", e.getMessage());
                        break;
                    }
                }
                if (!running) {
                    break;
                }

                try {
                    semaphore.acquire();
                    Product product = productQueue.poll();
                    consumeProduct(product);
                } catch (InterruptedException e) {
                    System.out.println("[WARNING] Не удалось захватить значение семафора");
                }
                semaphore.release();
            }
        }

        private void consumeProduct(Product product) {
            System.out.printf("> Потребитель \"%s\" употребляет продукт: \"%s\" (калорийность: %d).%n",
                    name, product.getName(), product.getCalorieContent());
            totalCaloriesConsumed += product.getCalorieContent();
            System.out.printf("> Потребитель \"%s\" суммарно употребил %s килокалорий.%n%n",
                    name, totalCaloriesConsumed / 1000.);
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                System.out.println("[ERROR] Не удалось приостановить поток");
                running = false;
            }
        }

        public void stop() {
            running = false;
            productQueue.notifyIsNotEmpty();
        }
    }

    public void run() {
        ProductQueue productQueue = new ProductQueue(50);
        Thread producerThread = new Thread(new Producer(productQueue));
        Semaphore semaphore = new Semaphore(1);
        Thread fConsumerThread = new Thread(new Consumer("Рита", productQueue, semaphore));
        Thread sConsumerThread = new Thread(new Consumer("Катя", productQueue, semaphore));
        Thread tConsumerThread = new Thread(new Consumer("Алина", productQueue, semaphore));

        producerThread.start();
        fConsumerThread.start();
        sConsumerThread.start();
        tConsumerThread.start();
    }

    public static void main(String[] args) {
        new C4().run();
    }
}
