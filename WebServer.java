/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WebServer {

    //Rutas relativas a las que accede el usuario
    private static final String TASK_ENDPOINT = "/task";
    private static final String STATUS_ENDPOINT = "/status";
    private static final String SEARCH_ENDPOINT = "/search";

    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        
        //Si el usuario especifica un puerto entonces se toma el dado por el usuario
        //caso contrario, el servidor inicia en el puerto 8080
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        //Se instancia la clase WebServer y se inicia el servidor
        WebServer webServer = new WebServer(serverPort);
        webServer.startServer();

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

    public WebServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {

            //Se crea una instancia de HttpServer, indicando el puerto donde estara escuchando y el numero de 
            //peticiones que pueden pasar a una cola de espera (0)
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //Los HttpContex asocian un identificador uniforme de recursos a un httphandler, es decir,
        //en terminios sencillos son los encargados de que el servidor sepa que metodo ejecutar
        //cuando se ingresa a un endpoint.
        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext taskContext = server.createContext(TASK_ENDPOINT);
        HttpContext searchContext = server.createContext(SEARCH_ENDPOINT);

        //Aqui se especifican los metodos a ejecutar por cada endpoint.
        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
        searchContext.setHandler(this::handleSearch);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void handleTaskRequest(HttpExchange exchange) throws IOException {
            
            //Se verifica que la peticion sea tipo post, si no se rechaza
            if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
                exchange.close();
                return;
            }

            //Se obtienen los headers que esta pasando el usuario
            Headers headers = exchange.getRequestHeaders();
            //Si contiene X-Test debe regresar la cadena 123
            if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
                String dummyResponse = "123\n";
                sendResponse(dummyResponse.getBytes(), exchange);
                return;
            }

            //Se establece que el modo de depuracion no esta activado
            boolean isDebugMode = false;
            //Si los headers contienen X-Debug se activa el modo depuracion
            if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
                isDebugMode = true;
            }

            //Iniciamos a medir el tiempo
            long startTime = System.nanoTime();

            //Obtenemos los bytes de la solicitud
            byte[] requestBytes = exchange.getRequestBody().readAllBytes();
            //Pasamos los bytes al metodo calculareResponse, que hace la multiplicacion de todos los numeros 
            byte[] responseBytes = calculateResponse(requestBytes);

            //Dejamos de medir el tiempo
            long finishTime = System.nanoTime();

            //Si el modo depuracion esta activado, agrega a la respuesta el tiempo que se midio anteriormente
            if (isDebugMode) {
                String debugMessage = String.format("La operación tomó %d nanosegundos", finishTime - startTime);
                //Se agrega el tiempo como un header de la respuesta
                exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
            }

            //Se envia la respuesta al cliente
            sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponse(byte[] requestBytes) {
        
        String bodyString = new String(requestBytes);
        //Convertimos los bytes recibidos a una cadea y esa cadena se parte cada vez que aparezca ','
        String[] stringNumbers = bodyString.split(",");

        BigInteger result = BigInteger.ONE;

        //Se multiplican los numeros contenidos en el arreglo de cadenas
        for (String number : stringNumbers) {
            BigInteger bigInteger = new BigInteger(number);
            result = result.multiply(bigInteger);
        }

        //Se devuelve el resultado como bytes
        return String.format("El resultado de la multiplicación es %s\n", result).getBytes();
    }

    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        
        //Si el metodo no es get, no se atiende 
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        //Retornamos unicamente una string que indica el estado del servidor
        String responseMessage = "El servidor está vivo\n";
        sendResponse(responseMessage.getBytes(), exchange);
    }
	
	//Este es el método que busca las palabras
    private byte[] searchWord(byte[] requestBytes){
        
        String req = new String(requestBytes);        
        String[] params = req.split(",");

        int n = Integer.parseInt(params[0]);
        int index = 0, cta = 0;
        //ACD_
        StringBuilder cadenota = new StringBuilder(n*4);
        for(int i = 0; i < n; i++){
            cadenota.append(getRandom());
            cadenota.append(getRandom());
            cadenota.append(getRandom());
            cadenota.append(" ");
        }
        
        long ini = System.nanoTime();

        index = cadenota.indexOf(params[1]);
        while(index >= 0){
            cta++;
            index = cadenota.indexOf(params[1],index+1);
        }

        long fin = System.nanoTime();

        String ip = "";
        try{
            ip = InetAddress.getLocalHost().getHostAddress();
        }catch(UnknownHostException e){
            ip = "127.0.0.1";
        }

        return String.format("Palabra: %s IP:%s Coincidencias:%d Tiempo:%dns\n",params[1],ip,cta,(fin-ini)).getBytes();
        
    }

    private static char getRandom(){
        return (char)((Math.random()*(90-65))+65);
    }

    //Creamos un metodo para realizar la busqueda, la peticion debe ser post
    private void handleSearch(HttpExchange exchange) throws IOException{

        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        Headers headers = exchange.getRequestHeaders();
        

        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }

        long startTime = System.nanoTime();

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = searchWord(requestBytes);

        long finishTime = System.nanoTime();

        if (isDebugMode) {
            String debugMessage = String.format("La operación tomó %d nanosegundos", finishTime - startTime);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }

        sendResponse(responseBytes, exchange);

    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        //Se agrega a los headers el status de la respuesta y la longitud de la misma
        exchange.sendResponseHeaders(200, responseBytes.length);
        //Se crea un flujo de salida para enviar al cliente
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        //Se escribe la respuesta de la solicitud en el flujo y se envia
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
}
