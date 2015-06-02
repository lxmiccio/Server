package socket.server;

import java.io.*;
import java.net.*;

/**
 *
 * @author Alex
 */
public class Server
{
    private ServerSocket serverSocket;
    private int port;

    public Server(int port)
    {
        this.port = port;
    }
    
    public void start() throws IOException
    {
        System.out.println("Starting the server at port " + this.port + "...");
        this.serverSocket = new ServerSocket(this.port);
        System.out.println("Waiting for client on port " + this.serverSocket.getLocalPort() + "...");
        while(true)
        {
            Socket server = this.serverSocket.accept();
            System.out.println("Client " + server.getInetAddress().getCanonicalHostName() + " has connected");
            Thread thread  = new Thread(new ClientHandler(server));
            thread.start();
        }
    }
    
    public static void main(String[] args)
    {
        try
        {
            Server serverSocket = new Server(9999);
            serverSocket.start();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}