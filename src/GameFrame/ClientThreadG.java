package GameFrame;


import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.Vector;

public class ClientThreadG implements Runnable{
    
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    GameFormC main;
    StringTokenizer st;
    protected DecimalFormat df = new DecimalFormat("##,#00");
    

    public ClientThreadG(Socket socket, GameFormC main){
        this.main = main;
        this.socket = socket;
        try {
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            main.appendMessage("[IOException]: "+ e.getMessage(), "Lỗi", Color.RED, Color.RED);
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()){
                String data = dis.readUTF();
                st = new StringTokenizer(data);
                /** Get Message CMD **/
                String CMD = st.nextToken();
                switch(CMD){
                    case "CMD_MESSAGE":
//                      SoundEffect.MessageReceive.play();   Play Audio clip
                        String msg = "";
                        String frm = st.nextToken();
                        while(st.hasMoreTokens()){
                            msg = msg +" "+ st.nextToken();
                        }
                        main.appendMessage(msg, frm, Color.MAGENTA, Color.BLUE);
                        break;
                        
                    case "CMD_ONLINE":
                        Vector online = new Vector();
                        while(st.hasMoreTokens()){
                            String list = st.nextToken();
                            if(!list.equalsIgnoreCase(main.username)){
                                online.add(list);
                            }
                        }
                        main.appendOnlineList(online);
                        break;
                    default: 
                        main.appendMessage("[CMDException]: Không rõ lệnh "+ CMD, "CMDException", Color.RED, Color.RED);
                    break;
                }
            }
        } catch(IOException e){
            main.appendMessage(" Bị mất kết nối đến Máy chũ, vui lòng thử lại.!", "Lỗi", Color.RED, Color.RED);
        }
    }
}