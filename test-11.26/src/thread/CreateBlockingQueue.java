package thread;

/**
 * @BelongsProject: test-11.26
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/26 22:14
 * @Description: 阻塞队列的实现
 * @Author: code_hlb
 */
public class CreateBlockingQueue {
    public static void main(String[] args) {
        // 1、基于一个普通的队列
        // 2、加上线程安全
        // 3、加上阻塞
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue();
        myBlockingQueue.put(1);
        myBlockingQueue.put(2);
        myBlockingQueue.put(3);
        System.out.println(myBlockingQueue.take());
    }
}

class MyBlockingQueue {
    // 1、创建一个数组的循环队列
    private static int[] elems;
    private static final int DEFAULT_SIZE = 10;
    public MyBlockingQueue() {
        elems = new int[DEFAULT_SIZE];
    }
    private int head;
    private int tail;
    private int usedSize;
    // 入队列
    public void put(int elem) {
        if (usedSize == elems.length) {
            return;
        }else {
            elems[tail++] = elem;
            if (tail == elems.length) {
                tail = 0;
            }
            usedSize++;
        }
    }
    // 出队列
    public int take() {
        if (usedSize == 0) {
            return -1;
        }
        int res = elems[head++];
        if (head == elems.length) {
            head = 0;
        }
        usedSize--;
        return res;
    }
}
