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

import java.util.Arrays;
import java.util.List;

public class Application {
    private static final String WORKER_ADDRESS_1 = "http://localhost:8081/task";

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();
        PoligonoIrreg poligono = new PoligonoIrreg();
        System.out.println("Objeto del cliente");
        System.out.println("Vertices del poligono\n" + poligono.toString());

        byte[] serializado = SerializationUtils.serialize(poligono);
        System.out.println("Objeto serializado del cliente");
        System.out.println("Objeto serializado = " + serializado);
        List<Object> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1),
                Arrays.asList(serializado));

        while (true) {
            for (Object result : results) {                
                PoligonoIrreg poligonoRes = (PoligonoIrreg) result;
                // (PoligonoIrreg) SerializationUtils.deserialize(result);
                System.out.println("Se ha recibido el poligono del servidor");
                System.out.println("Vertices del poligono recibidos del servidor\n" + poligonoRes.toString());
                System.out.println("Agregando vertice");
                poligonoRes.anadeVertice(new Coordenada(Math.random() * (200) - 100, Math.random() * (200) - 100));
                System.out.println("Vertice anadido en el cliente\nVertices actuales:\n" + poligonoRes.toString());
                serializado = SerializationUtils.serialize(poligonoRes);                
            }
            results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1), Arrays.asList(serializado));
        }
    }
}
