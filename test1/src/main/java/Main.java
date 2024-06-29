public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, world!");
        Main mainInstance = new Main();
        mainInstance.doSomething();
    }

    public void doSomething() {
        // Przykładowa metoda do testowania w SonarQube
        int a = 5;
        int b = 10;
        int sum = a + b;
        System.out.println("Sum: " + sum);
    }
}
