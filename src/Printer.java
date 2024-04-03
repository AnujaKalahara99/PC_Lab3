import java.util.Random;
import java.util.random.RandomGenerator;

public class Printer extends Thread{
    private final String printerName;
    Random random = new Random();

    public Printer(String printerName) {
        this.printerName = printerName;
    }

    public synchronized void print(){
        PrintJob currentJob = null;
        try {
            currentJob = SharedQueue.getInstance().getJob();
        } catch (TypeNotSupportedException e) {
//            throw new RuntimeException(e);
        }
        if(currentJob != null) {
            System.out.println("                    " + printerName + " print Done on " + currentJob.getFile());
        }
    }

    public void run(){
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            print();
        }
    }
}
