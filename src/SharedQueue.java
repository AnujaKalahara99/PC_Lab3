import java.util.LinkedList;
import java.util.Queue;

//Uses singleton pattern
public class SharedQueue {
    private static SharedQueue instance = null;
    private Queue<PrintJob> jobQueue = new LinkedList<>();
    private final int maxSize = 5;
    private int queueSize;

    public synchronized static SharedQueue getInstance() {
        if (instance == null) {
            instance = new SharedQueue();
        }
        return instance;
    }

    public synchronized void AddJob(PrintJob job) throws TypeNotSupportedException {
        if(jobQueue.size() < maxSize) {
            queueSize++;
            jobQueue.add(job);
        }
    }

    public synchronized PrintJob getJob() throws  TypeNotSupportedException {
        if(queueSize < 1) return null;
        queueSize--;
        return jobQueue.poll();
    }

    public boolean isEmpty(){
        return queueSize < 1;
    }

    public  boolean isFull(){
        return queueSize >= maxSize;
    }
}
