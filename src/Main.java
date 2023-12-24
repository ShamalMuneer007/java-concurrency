public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread  =  new Thread( () -> {
            System.out.println("Hello im in thread : "+Thread.currentThread().getName());
        });
        thread.setName("Custom thread");
        System.out.println("This is the thread : "+Thread.currentThread().getName()+" before running Custom thread");
        thread.start();
        System.out.println("This is the thread : "+Thread.currentThread().getName()+" after running Custom thread");
        Thread.sleep(1000);



    }
}