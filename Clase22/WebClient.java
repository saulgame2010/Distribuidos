  /*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
/*Almacena la clase webClient */
package networking;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
public class WebClient {
    //Instancia clientes http (provisto por java)
    private HttpClient client;
    public WebClient() {
        //Objeto http que usa el protocolo v1.1
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }
    //Parametros: Direccion con la que se establece la conexion,
    //Datos a enviar hacia el servidor
    //Retorna CompletableFuture de tipo string
    public CompletableFuture<String> sendTask(String url, byte[] requestPayload) {
        //Se crea un objeto que permite construir una solicitud POST
        //Y la direccion de destino
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofByteArray(requestPayload))
                .uri(URI.create(url))
                .header("X-Debug","true")
                .build();
        //Se llama al metodo para enviar la solicitud request de forma asincrona
        
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(respuesta -> {return respuesta.body()+""+respuesta.headers();});
    }
}