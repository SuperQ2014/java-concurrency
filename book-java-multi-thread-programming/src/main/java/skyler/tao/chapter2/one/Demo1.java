package skyler.tao.chapter2.one;

/**
 * "非线程安全"问题存在于"实例变量"中，如果是方法内部的私有变量，则不存在"非线程安全"问题，所得结果也就是"线程安全"的了
 *
 * @author chaoqiang
 */
public class Demo1 {
    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(numRef);
        ThreadB threadB = new ThreadB(numRef);
        threadA.start();
        threadB.start();
    }

    static private class HasSelfPrivateNum {
        private void addI(String userName) {
            try {
                int num;
                if ("a".equals(userName)) {
                    num = 100;
                    System.out.println("a set over!");
                    Thread.sleep(2000);
                } else {
                    num = 200;
                    System.out.println("b set over!");
                }
                System.out.println(userName + " num= " + num);
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
