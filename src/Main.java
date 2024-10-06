public class Main {
    public static void main(String[] args) throws InterruptedException {
        PingPong pingPong = new PingPong();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        pingPong.ping();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        pingPong.pong();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}


class PingPong {
    private final String lock = "lock";
    private boolean flag = false;

    public void ping() throws InterruptedException {
        synchronized (lock) {
            System.out.println("ping");
            flag = true;
            lock.notify();
            lock.wait();
        }
    }

    public void pong() throws InterruptedException {
        if (!flag) return;

        synchronized (lock) {
            System.out.println("pong");
            lock.notify();
            lock.wait();
        }
    }

}