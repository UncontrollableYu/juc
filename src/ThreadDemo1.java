//第一步 创建资源类，定义属性和操作方法
class Share{
    //初始值
    private int number= 0;
    //+1的方法
    public synchronized void incr() throws InterruptedException {
        //第二步 判断  执行   通知
        while (number != 0){//判断number是否等于0，等于0就执行，不等于就等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+" :: "+ number);
        this.notify();//通知其他线程
    }
    //-1的方法
    public  synchronized void decr() throws InterruptedException {
        //判断number是否等于1，是就执行，不是则等待
        while (number != 1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+" :: "+ number);
        this.notify();
    }
}
public class ThreadDemo1 {
    //第三步 创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        //创建对象
        Share share = new Share();
        //创建线程
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share.incr();//调用+1方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share.decr();//调用-1方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share.incr();//调用+1方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "CC").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share.decr();//调用-1方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "DD").start();
    }

}
