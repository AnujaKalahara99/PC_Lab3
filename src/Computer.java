import java.util.ArrayList;

public class Computer extends Thread {
    private final String computerName;
    private ArrayList<PrintJob> jobs = new ArrayList<>();

    public Computer(String computerName) {
        this.computerName = computerName;
    }

    public void createPrintJob(String fileName) {
        try {
            if (isSupportedFile(fileName)) {
                System.out.println(computerName + " : user input " + fileName);
                PrintJob pJob = new PrintJob(fileName);
                jobs.add(pJob);
            }
        } catch (TypeNotSupportedException e) {
            System.out.println(e.getMessage());
        }

    }

    private boolean isSupportedFile(String fileName) throws TypeNotSupportedException {
        String[] fileNames = fileName.split("\\.", 2);
        if (fileNames.length < 2) {
            throw new TypeNotSupportedException("File name does not contain an extension.");
        }
        String extension = fileNames[1].toLowerCase();
        if (extension.equals("pdf") || extension.equals("doc") || extension.equals("txt")) {
            return true;
        } else {
            throw new TypeNotSupportedException(fileName + " Unsupported file type: " + extension);
        }
    }

    public synchronized void pushJob() {
        if (!SharedQueue.getInstance().isFull() && !jobs.isEmpty()) {
            try {
                System.out.println(computerName + " : Job pushed " + jobs.getFirst().getFile());
                SharedQueue.getInstance().AddJob(jobs.getFirst());
                jobs.removeFirst();

            } catch (TypeNotSupportedException e) {
                System.out.println(computerName + " : Unsupported file type " + jobs.getFirst().getFile());
                //Empty Catch
            }
        }
    }

    public void run() {
        while (true) {
            pushJob();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}