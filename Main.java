package hw_3_4_v_2;

public class Main {

    public static class Task1{
        static volatile char c = 'A';
        static Object mon = new Object();

        static class WaitNotifyClass implements Runnable{
            private char currentLetter;
            private char nextLetter;

            public WaitNotifyClass(char currentLetter, char nextLetter) {
                this.currentLetter = currentLetter;
                this.nextLetter = nextLetter;
        }

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon){
                        try {
                            while (c != currentLetter)
                            mon.wait();
                            System.out.println(currentLetter);
                            c = nextLetter;
                            mon.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                }
            }
        }

    public static void main(String[] args) {
        new Thread(new Task1.WaitNotifyClass('A', 'B')).start();
        new Thread(new Task1.WaitNotifyClass('B', 'C')).start();
        new Thread(new Task1.WaitNotifyClass('C', 'A')).start();
    }
}
