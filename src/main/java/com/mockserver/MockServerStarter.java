package com.mockserver;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.Header;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

public class MockServerStarter {

    private static ClientAndServer mockServer;

    public static void main(String[] args) throws IOException {
        startMockServer();
    }

    public static void startMockServer() throws IOException {
        mockServer = startClientAndServer(1080);

        // Load the configuration from the JSON file
        String configuration = new String(Files.readAllBytes(Paths.get("mockserver-configuration.json")));

        // Parse the configuration
        Gson gson = new Gson();
        List<MockExpectation> expectations = gson.fromJson(configuration, new TypeToken<List<MockExpectation>>(){}.getType());

        // Apply the configuration to MockServer
        for (MockExpectation expectation : expectations) {
            HttpRequest httpRequest = HttpRequest.request()
                    .withMethod(expectation.getHttpRequest().getMethod())
                    .withPath(expectation.getHttpRequest().getPath());

            HttpResponse httpResponse = HttpResponse.response()
                    .withStatusCode(expectation.getHttpResponse().getStatusCode())
                    .withHeaders(
                            new Header("Content-Type", expectation.getHttpResponse().getHeaders().getContentType())
                    );

            // Check if the body is a string or a JSON object
            if (expectation.getHttpResponse().getBody() instanceof String) {
                httpResponse.withBody((String) expectation.getHttpResponse().getBody());
            } else {
                httpResponse.withBody(gson.toJson(expectation.getHttpResponse().getBody()));
            }

            mockServer.when(httpRequest).respond(httpResponse);
        }

        System.out.println("MockServer started on port 1080");
    }

    static class MockExpectation {
        private HttpRequestDTO httpRequest;
        private HttpResponseDTO httpResponse;

        public HttpRequestDTO getHttpRequest() {
            return httpRequest;
        }

        public HttpResponseDTO getHttpResponse() {
            return httpResponse;
        }
    }

    static class HttpRequestDTO {
        private String method;
        private String path;

        public String getMethod() {
            return method;
        }

        public String getPath() {
            return path;
        }
    }

    static class HttpResponseDTO {
        private int statusCode;
        private Object body; // Changed type to Object
        private Headers headers;

        public int getStatusCode() {
            return statusCode;
        }

        public Object getBody() { // Changed return type to Object
            return body;
        }

        public Headers getHeaders() {
            return headers;
        }
    }

    static class Headers {
        private String contentType;

        public String getContentType() {
            return contentType;
        }
    }
}
