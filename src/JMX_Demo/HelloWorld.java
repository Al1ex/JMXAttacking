package JMX_Demo;

public class HelloWorld implements HelloWorldMBean{
    private String name = "Al1ex";

    @Override
    public void sayhello() {
        System.out.println("hello world " + this.name);
    }

    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public String getName() {
        return this.name;
    }
}