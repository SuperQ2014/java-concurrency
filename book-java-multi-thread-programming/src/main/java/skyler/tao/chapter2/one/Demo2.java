package skyler.tao.chapter2.one;

/**
 * 两个线程同时访问一个没有同步的方法，如果两个线程同时操作业务对象中的实例变量，则有可能出现"非线程安全"问题。
 * 只需要在 addI 方法前加上 synchronized 即可
 */
public class Demo2 {
    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(numRef);
        ThreadB threadB = new ThreadB(numRef);
        threadA.start();
        threadB.start();
    }

    static private class HasSelfPrivateNum {
        private int num = 0;

        synchronized private void addI(String userName) {
            try {
                if ("a".equals(userName)) {
                    num = 100;
                    System.out.println("a set over!");
                    Thread.sleep(2000);
                } else {
                    num = 200;
                    System.out.println("b set over!");
                }
                System.out.println(userName + " num=" + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadA extends Thread {
        private HasSelfPrivateNum numRef;

        private ThreadA(HasSelfPrivateNum numRef) {
            super();
            this.numRef = numRef;
        }

        @Override
        public void run() {
            super.run();
            numRef.addI("a");
        }
    }

    static class ThreadB extends Thread {
        private HasSelfPrivateNum numRef;

        private ThreadB(HasSelfPrivateNum numRef) {
            super();
            this.numRef = numRef;
        }

        @Override
        public void run() {
            super.run();
            numRef.addI("b");
        }
    }
}
