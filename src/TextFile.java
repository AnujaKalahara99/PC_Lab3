import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class TextFile extends Thread{
    private String fileName;
    private String content;
    private PrintJob currentJob;

    public TextFile(String fileName){
        this.fileName = fileName;
        readAFile();
    }

    public void readAFile(){
        Scanner scanFile = null;
        try {
            File file = new File(new File("").getAbsolutePath() + "/src/" + fileName);
            scanFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String fileData = "";
        while (scanFile.hasNextLine())
            fileData += scanFile.nextLine() + " ";
        System.out.println(fileName +  " Content Read : " + fileData);
    }

    public String getFileName(){
        return fileName;
    }
    public String getContent(){
        return content;
    }

    public synchronized void addToQueue(){

            currentJob = new PrintJob(this.fileName);
            try {
                SharedQueue.getInstance().AddJob(currentJob);
                this.fileName=null;
            } catch (TypeNotSupportedException e){
                System.out.println("Web : File type not supported");
            }

    }

    public void run() {
        while (true){
            if(SharedQueue.getInstance().isFull() || this.fileName== null){
                try{
                    Thread.sleep(1000);

                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
            else{
                addToQueue();

            }
        }
    }
}
