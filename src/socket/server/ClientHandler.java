package socket.server;

import java.io.*;
import java.net.*;
import java.util.Date;

/**
 *
 * @author Alex
 */
class ClientHandler implements Runnable
{
    private Socket server;
    
    public ClientHandler(Socket server)
    {
        this.server = server;
    }

    @Override public void run()
    {
        try
        {
            DataInputStream inFromClient = new DataInputStream(this.server.getInputStream());
            if(inFromClient.readUTF().equals("Time?"))
            {
                System.out.print(this.server.getInetAddress().getCanonicalHostName() + " asks for time...");
                this.sendTimeToClient();
            }
        }
        catch(InterruptedException | IOException exception)
        {
            exception.printStackTrace();
        }
    }
    
    private void sendTimeToClient() throws InterruptedException, IOException
    {
        DataOutputStream outToClient = new DataOutputStream(this.server.getOutputStream());
        outToClient.writeUTF(new Date().toString());
        outToClient.flush();
        outToClient.close();
    }
}