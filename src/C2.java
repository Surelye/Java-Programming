public class C2 {

    private final String randomSymbols;

    {
        int lowerBound = 33;
        int boundDifference = 126 - lowerBound + 1;
        byte[] bytes = new byte[10];

        for (int i = 0; i < 10; ++i) {
            bytes[i] = (byte)((Math.random() * boundDifference) + lowerBound);
        }

        randomSymbols = new String(bytes);
    }

    private void testUsualString() {
        String testString = "";

        for (int i = 0; i < 100_000; ++i) {
            testString += randomSymbols;
        }
    }

    private void testUsualStringWithRepeat() {
        String testString = "";
        testString = randomSymbols.repeat(100_000);
    }

    private void testStringBuilder() {
        StringBuilder testStringBuilder = new StringBuilder();

        for (int i = 0; i < 100_000; ++i) {
            testStringBuilder.append(randomSymbols);
        }
    }

    private void testStringBuilderWithCapacityInit() {
        StringBuilder testStringBuilder = new StringBuilder((int)10e6);

        for (int i = 0; i < 100_000; ++i) {
            testStringBuilder.append(randomSymbols);
        }
    }

    private void testStringBuilderWithRepeat() {
        StringBuilder testStringBuilder = new StringBuilder();
        testStringBuilder.append(randomSymbols.repeat(100_000));
    }

    private void testStringBuilderWithCapacityInitWithRepeat() {
        StringBuilder testStringBuilder = new StringBuilder((int)10e6);
        testStringBuilder.append(randomSymbols.repeat(100_000));
    }

    private void testStringBuffer() {
        StringBuffer testStringBuffer = new StringBuffer();

        for (int i = 0; i < 100_000; ++i) {
            testStringBuffer.append(randomSymbols);
        }
    }

    private void testStringBufferWithCapacityInit() {
        StringBuffer testStringBuffer = new StringBuffer((int)10e6);

        for (int i = 0; i < 100_000; ++i) {
            testStringBuffer.append(randomSymbols);
        }
    }

    private double getSecondsPassed(long startTime, long endTime) {
        return (endTime - startTime) / 1000.0;
    }

    public void testStringImplementations() {
        System.out.printf("> В качестве случайных используются символы: %s.%n", randomSymbols);

        System.out.println("> Запуск метода testUsualString().");
        long startTime = System.currentTimeMillis();
        testUsualString();
        long endTime = System.currentTimeMillis();
        double secondsPassed = getSecondsPassed(startTime, endTime);
        System.out.printf("> Метод: testUsualString(). Время: %s.%n", secondsPassed);

        startTime = System.currentTimeMillis();
        testUsualStringWithRepeat();
        endTime = System.currentTimeMillis();
        secondsPassed = getSecondsPassed(startTime, endTime);
        System.out.printf("> Метод: testUsualStringWithRepeat(). Время: %s.%n", secondsPassed);

        startTime = System.currentTimeMillis();
        testStringBuilder();
        endTime = System.currentTimeMillis();
        secondsPassed = getSecondsPassed(startTime, endTime);
        System.out.printf("> Метод: testStringBuilder(). Время: %s.%n", secondsPassed);

        startTime = System.currentTimeMillis();
        testStringBuilderWithCapacityInit();
        endTime = System.currentTimeMillis();
        secondsPassed = getSecondsPassed(startTime, endTime);
        System.out.printf("> Метод: testStringBuilderWithCapacityInit(). Время: %s.%n",
                secondsPassed);

        startTime = System.currentTimeMillis();
        testStringBuilderWithRepeat();
        endTime = System.currentTimeMillis();
        secondsPassed = getSecondsPassed(startTime, endTime);
        System.out.printf("> Метод: testStringBuilderWithRepeat(). Время: %s.%n",
                secondsPassed);

        startTime = System.currentTimeMillis();
        testStringBuilderWithCapacityInitWithRepeat();
        endTime = System.currentTimeMillis();
        secondsPassed = getSecondsPassed(startTime, endTime);
        System.out.printf("> Метод: testStringBuilderWithCapacityInitWithRepeat(). Время: %s.%n",
                secondsPassed);

        startTime = System.currentTimeMillis();
        testStringBuffer();
        endTime = System.currentTimeMillis();
        secondsPassed = getSecondsPassed(startTime, endTime);
        System.out.printf("> Метод: testStringBuffer(). Время: %s.%n", secondsPassed);

        startTime = System.currentTimeMillis();
        testStringBufferWithCapacityInit();
        endTime = System.currentTimeMillis();
        secondsPassed = getSecondsPassed(startTime, endTime);
        System.out.printf("> Метод: testStringBufferWithCapacityInit(). Время: %s.%n",
                secondsPassed);
    }
}
