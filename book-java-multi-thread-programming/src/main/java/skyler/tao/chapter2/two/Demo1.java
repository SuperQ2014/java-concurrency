package skyler.tao.chapter2.two;

public class Demo1 {
    public static void main(String[] args) {
        ObjectService service = new ObjectService();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("a");
        threadA.start();
        ThreadB threadB = new ThreadB(service);
        threadB.setName("b");
        threadB.start();
    }

    static private class ObjectService {
        private void serviceMethod() {
            try {
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName() + " begin time = " + System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " end time = " + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadA extends Thread {
        private ObjectService service;

        private ThreadA(ObjectService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.serviceMethod();
        }
    }

    static class ThreadB extends Thread {
        private ObjectService service;

        private ThreadB(ObjectService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.serviceMethod();
        }
    }
}
