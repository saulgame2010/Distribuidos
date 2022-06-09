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

//Librerias para construir un servidor en Java
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import networking.Datos;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;

public class WebServer {
    private static final String TASK_ENDPOINT = "/task";
    private static final String STATUS_ENDPOINT = "/status";
    private static final String SEARCHPIN_ENDPOINT = "/search";
    private static final String SEARCHPHRASE_ENDPOINT = "/searchphrase";

    private static final String FILE_NAME = "BIBLIA_COMPLETA.txt";

    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        WebServer webServer = new WebServer(serverPort);
        webServer.startServer();

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

     public WebServer(int port) {
        this.port = port;
    }


    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext taskContext = server.createContext(TASK_ENDPOINT);
        HttpContext searchpinContext = server.createContext(SEARCHPIN_ENDPOINT);
        HttpContext searchphraseContext = server.createContext(SEARCHPHRASE_ENDPOINT);

        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
        searchpinContext.setHandler(this::handleSearchpinRequest);
        searchphraseContext.setHandler(this::handleSearchPhraseRequest);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void handleTaskRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        Headers headers = exchange.getRequestHeaders();
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "123\n"; 
            sendResponse(dummyResponse.getBytes(), exchange);
            return;
        }

        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }

        long startTime = System.nanoTime();

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateResponse(requestBytes);

        long finishTime = System.nanoTime();

        if (isDebugMode) {
            String debugMessage = String.format("La operación tomó %d nanosegundos", finishTime - startTime);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }
        
        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponse(byte[] requestBytes) {
        String bodyString = new String(requestBytes);
        String[] stringNumbers = bodyString.split(",");
        BigInteger result = BigInteger.ONE;

        for (String number : stringNumbers) {
            BigInteger bigInteger = new BigInteger(number);
            result = result.multiply(bigInteger);
        }

        return String.format("El resultado de la multiplicación es %s\n", result).getBytes();
    }

    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String responseMessage = "El servidor está vivo\n";
        sendResponse(responseMessage.getBytes(), exchange);
    }

    //HANDLE PARA ENDPOINT DE BUSQUEDA DE FRASE
    private void handleSearchPhraseRequest(HttpExchange exchange) throws IOException{

        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateResponseMulti(requestBytes);

        sendResponse(responseBytes, exchange);

    }

    //METODO PARA BUSCAR LA FRASE
    private byte[] calculateResponseMulti(byte[] requestBytes) {
        System.out.println("Objeto serializado");
        System.out.println(requestBytes);

        Datos objeto = (Datos)SerializationUtils.deserialize(requestBytes);
        System.out.println("Objeto deserializado");
        System.out.println("frase: " + objeto.frase + "\nip: " + objeto.ip);
        String frase = new String(objeto.frase.getBytes(), StandardCharsets.UTF_8);
        Busqueda buscaGeneral = new Busqueda(frase, objeto.libros, objeto.libros.length);

        Map<String,Double> map = buscaGeneral.haceBusqueda();
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            String key = (String)it.next();
            System.out.println("Resultado: " + key + " -> Libro: " + map.get(key));
        }

        objeto.numero = 1;
        objeto.setResult(map);

        byte[] serializado = SerializationUtils.serialize(objeto);
        System.out.println("Objeto serializado again");

        return serializado;
    }

    private void handleSearchpinRequest(HttpExchange exchange) throws IOException{

        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        Headers headers = exchange.getRequestHeaders();
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "0\n";
            sendResponse(dummyResponse.getBytes(), exchange);
            return;
        }

        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }

        long startTime = System.nanoTime();

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateResponseSearchpin(requestBytes);

        long finishTime = System.nanoTime();

        if (isDebugMode) {
            String debugMessage = String.format("La operación tomó %d nanosegundos", finishTime - startTime);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }
        
        sendResponse(responseBytes, exchange);

    }

    private byte[] calculateResponseSearchpin(byte[] requestBytes) {

        String palabra = new String(requestBytes);
        int cuenta = 0;
        System.out.println("Se recibio la palabra: " + palabra);
        
        try {
            File doc = new File(FILE_NAME);

            BufferedReader obj = new BufferedReader(new FileReader(doc));
        
            String strng;
            while ((strng = obj.readLine()) != null){
                String separada[] = strng.split("[\\s,.;:¿?¡!-0123456789]+");
                for(int i = 0; i<separada.length; i++){
                    if(separada[i].equalsIgnoreCase(palabra)){
                        cuenta++;
                    }
                }
            }

            obj.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }catch(IOException e){
            e.getMessage();
        }

        System.out.println("Se encontraron "+cuenta+" coincidencias de la palabra " + palabra);
        System.out.println("");

        return String.format("Se encontraron "+cuenta+" coincidencias de la palabra " + palabra).getBytes();

    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
}