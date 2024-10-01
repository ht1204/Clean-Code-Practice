import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataFetcher {

    public void fetchData(String url, SuccessCallback success, ErrorCallback error) {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                success.onSuccess(content.toString());
            } else {
                error.onError(status, conn.getResponseMessage());
            }

        } catch (Exception e) {
            error.onError(500, e.getMessage());
        }
    }

    public Object parseData(String response, String type) {
        if (type.equals("json")) {
            // Simulate JSON parsing
            return response;
        } else if (type.equals("xml")) {
            // Simulate XML parsing
            return response;
        } else {
            return response;
        }
    }

    public void processData(Object data) {
        // Simulate processing the data
        System.out.println("Processing data: " + data.toString());
    }

    public static void main(String[] args) {
        DataFetcher fetcher = new DataFetcher();
        fetcher.fetchData("https://example.com/api", new SuccessCallback() {
            @Override
            public void onSuccess(String response) {
                Object parsedData = fetcher.parseData(response, "json");
                fetcher.processData(parsedData);
            }
        }, new ErrorCallback() {
            @Override
            public void onError(int status, String message) {
                System.err.println("Error: " + status + " - " + message);
            }
        });
    }
}

interface SuccessCallback {
    void onSuccess(String response);
}

interface ErrorCallback {
    void onError(int status, String message);
}
