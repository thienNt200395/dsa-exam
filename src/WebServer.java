import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Small web server for trying the three exam questions in a browser.
 * Run from the repository root: java -cp out WebServer [port].
 * Starts at port 9000 by default and tries the next port if it is busy.
 */
public class WebServer {

    private static final int DEFAULT_PORT = 9000;
    private static final int MAX_ARRAY_LENGTH = 1000;
    private static final Path INDEX_PAGE = Path.of("web", "index.html");
    /** Source files shown on the page, in the order of questions 1, 2, 3. */
    private static final List<Path> SOURCE_FILES = List.of(
            Path.of("src", "LinearSearch.java"),
            Path.of("src", "BubbleSort.java"),
            Path.of("src", "Factorial.java"));

    private static final String TEXT = "text/plain; charset=utf-8";
    private static final String HTML = "text/html; charset=utf-8";
    private static final String JSON = "application/json; charset=utf-8";

    public static void main(String[] args) throws IOException {
        if (!Files.isRegularFile(INDEX_PAGE)) {
            System.err.println("Cannot find " + INDEX_PAGE + ". Run the server from the repository root.");
            System.exit(1);
        }
        int startPort = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;
        HttpServer server = createServer(startPort);

        server.createContext("/", WebServer::handlePage);
        server.createContext("/source/", WebServer::handleSource);
        server.createContext("/api/", WebServer::handleApi);
        server.start();

        System.out.println("Open http://localhost:" + server.getAddress().getPort());
    }

    /** Creates a server that accepts local connections only; tries the next port if busy. */
    private static HttpServer createServer(int startPort) throws IOException {
        for (int port = startPort; ; port++) {
            try {
                return HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), port), 0);
            } catch (BindException busy) {
                // try the next port
            }
        }
    }

    private static void handlePage(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestURI().getPath().equals("/")) {
            send(exchange, 404, TEXT, "Not found");
            return;
        }
        send(exchange, 200, HTML, Files.readString(INDEX_PAGE, StandardCharsets.UTF_8));
    }

    private static void handleSource(HttpExchange exchange) throws IOException {
        String id = exchange.getRequestURI().getPath().substring("/source/".length());
        if (!id.matches("[1-" + SOURCE_FILES.size() + "]")) {
            send(exchange, 404, TEXT, "Not found");
            return;
        }
        Path file = SOURCE_FILES.get(Integer.parseInt(id) - 1);
        send(exchange, 200, TEXT, Files.readString(file, StandardCharsets.UTF_8));
    }

    private static void handleApi(HttpExchange exchange) throws IOException {
        String name = exchange.getRequestURI().getPath().substring("/api/".length());
        Map<String, String> query = parseQuery(exchange.getRequestURI().getRawQuery());
        try {
            send(exchange, 200, JSON, runAlgorithm(name, query));
        } catch (IllegalArgumentException e) {
            send(exchange, 400, JSON, "{\"error\":" + quote(e.getMessage()) + "}");
        }
    }

    private static String runAlgorithm(String name, Map<String, String> query) {
        switch (name) {
            case "linear": {
                int[] values = parseArray(query.get("arr"));
                int target = parseInt(query.get("x"), "x");
                return "{\"index\":" + LinearSearch.search(values, target) + "}";
            }
            case "bubble": {
                int[] values = parseArray(query.get("arr"));
                BubbleSort.bubbleSort(values);
                return "{\"result\":" + Arrays.toString(values) + "}";
            }
            case "factorial": {
                int n = parseInt(query.get("n"), "n");
                return "{\"result\":" + Factorial.factorial(n) + "}";
            }
            default:
                throw new IllegalArgumentException("Unknown API: " + name);
        }
    }

    private static int[] parseArray(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Enter an array, for example: 5,3,8,1");
        }
        String[] parts = text.trim().split("\\s*,\\s*|\\s+");
        if (parts.length > MAX_ARRAY_LENGTH) {
            throw new IllegalArgumentException("The array can have at most " + MAX_ARRAY_LENGTH + " elements");
        }
        int[] values = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            values[i] = parseInt(parts[i], "element '" + parts[i] + "'");
        }
        return values;
    }

    private static int parseInt(String text, String label) {
        if (text == null) {
            throw new IllegalArgumentException("Missing value for " + label);
        }
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The value of " + label + " is not a valid integer");
        }
    }

    private static Map<String, String> parseQuery(String rawQuery) {
        Map<String, String> query = new HashMap<>();
        if (rawQuery == null) {
            return query;
        }
        for (String pair : rawQuery.split("&")) {
            String[] keyValue = pair.split("=", 2);
            String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
            String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8) : "";
            query.put(key, value);
        }
        return query;
    }

    private static String quote(String text) {
        return "\"" + text.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }

    private static void send(HttpExchange exchange, int status, String contentType, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream out = exchange.getResponseBody()) {
            out.write(bytes);
        }
    }
}
