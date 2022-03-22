import java.io.*;

public class Hilo extends Thread {
    private String url;

    public Hilo(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            conectar();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void conectar() throws IOException {
        Runtime rt = Runtime.getRuntime();
        String[] commands = { "curl", "-v", "--header", "X-debug:true", "--data", "1757600,IPN", url };
        Process proc = rt.exec(commands);
        //curl -v --header X-debug:true --data 1757600,IPN 34.125.179.82:8080/search
        

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        // Read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String s = null;        
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }        

        // Read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");        
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }
}
