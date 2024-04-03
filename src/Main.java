public class Main{
    public static void main(String[] args) {
        Computer computerA = new Computer("Com A");
        Computer computerB = new Computer("Com B");
        Computer computerC = new Computer("Com C");

        Printer printerA = new Printer("Printer A");
        Printer printerB = new Printer("Printer B");

        TextFile t1 = new TextFile("Mora.txt");


        computerA.start();
        computerB.start();
        computerC.start();

        printerA.start();
        printerB.start();

        t1.start();

            computerA.createPrintJob("CS1040.txt");
            computerB.createPrintJob("CS1030.jpeg");
            computerC.createPrintJob("CS1010.doc");
            computerA.createPrintJob("CS1020.doc");
            computerB.createPrintJob("CS1080.pdf");
            computerB.createPrintJob("CS1060.pdf");
            computerC.createPrintJob("CS1000.pdf");
            computerA.createPrintJob("CS1095.doc");
            computerC.createPrintJob("CS1099.txt");
            computerA.createPrintJob("CS1100.pdf");
    }
}