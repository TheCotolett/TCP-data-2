import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //La classe Socket è l'implementazione delle socket tcp
        //Quando si instanzia bisogna specificare l'indirizzo e la porta
        Socket socket = new Socket("localhost", 4999);

        //PrintWriter è la classe per la stampa di una stream, in questo caso
        //di una stream di output della socket
        PrintWriter pr = new PrintWriter(socket.getOutputStream());

        //Invio del messaggio al server attraverso la socket
        pr.println("Client connesso");
        pr.flush(); //Svuota il flusso di output e forza la scrittura dei byte memorizzati nel buffer

        //Instanziamento di InputStreamReader e BufferedReader
        //con l'input dalla socket
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        //Con il BufferedReader legge la riga ricevuta in tcp dal server
        //e la stampa
        String msg = bf.readLine();
        System.out.println("SERVER: "+msg);
    }
}
