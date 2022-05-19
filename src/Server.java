import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Server {
    public static void main(String[] args) throws IOException {
        //ServerSocket è l'implementazione dei socket tcp del server, che aspetta
        //di ricevere una richiesta di connessione
        ServerSocket serverSocket = new ServerSocket(4999);

        //Loop per l'esecuzione continua del programma
        do {
            //Ottenere data e orario correnti
            Date date = Calendar.getInstance().getTime();
            //Formattare data nel formato anno/mese/giorno ora/minuto/secodni
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //conversione da date a String
            String dataString = dateFormat.format(date);

            //Ascolto per una connessione sulla serverSocket e accettazione
            Socket socket = serverSocket.accept();

            //InputStreamReader collegato alla socket per ricevere input dal client
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            //Ricezione input dal client e stampa
            String msg = bf.readLine();
            System.out.println("CLIENT: " + msg);

            //PrintWriter è la classe per la stampa di una stream, in questo caso
            //di una stream di output della socket
            PrintWriter pr = new PrintWriter(socket.getOutputStream());
            //Invio della data in formato stringa
            pr.println(dataString);
            pr.flush(); //Svuota il flusso di output e forza la scrittura dei byte memorizzati nel buffer
        } while (true);
    }
}
