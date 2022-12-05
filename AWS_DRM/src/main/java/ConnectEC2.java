import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;

public class ConnectEC2 {
    private static String key = "/Users/kangmin/Desktop/4-2/DRM_CloudComputing.pem";
    private static String publicDNS = "3.35.26.49";
    private static String user = "ec2-user";
    private static int port = 22;

    public void condorStatus(){
        JSch jsch = new JSch();
        Session session = null;
        try {
            jsch.addIdentity(key);
            session = jsch.getSession(user, publicDNS, port);
            session.setConfig("StrictHostKeyChecking","no");
            session.setConfig("GSSAPIAuthentication","no");
            session.setServerAliveInterval(120 * 1000);
            session.setServerAliveCountMax(1000);
            session.setConfig("TCPKeepAlive","yes");

            session.connect();

            Channel channel = session.openChannel("exec");
            ChannelExec channelExec = (ChannelExec) channel;

            channelExec.setCommand("condor_status");
            InputStream inputStream = channelExec.getInputStream();
            channelExec.connect();
            byte[] buffer = new byte[8192];
            int decodedLength;
            StringBuilder response = new StringBuilder();
            while ((decodedLength = inputStream.read(buffer, 0, buffer.length)) > 0){
                response.append(new String(buffer, 0, decodedLength));
            }
            System.out.println("Print Condor Status...");
            System.out.println(response.toString());
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }
    }
}
