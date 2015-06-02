package socket.server;

import java.io.*;
import java.net.*;

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
            readQuestion();
        }
        catch(InterruptedException | IOException exception)
        {
            exception.printStackTrace();
        }
    }
    
    private void readQuestion() throws InterruptedException, IOException
    {
        DataInputStream fromClient = new DataInputStream(this.server.getInputStream());
        if(fromClient.readUTF().equals("Choice?"))
            sendChoice();
    }
    
    private void sendChoice() throws IOException, InterruptedException
    {
        DataOutputStream out = new DataOutputStream(this.server.getOutputStream());
        out.writeUTF("Paper");
        out.flush();
    }
}