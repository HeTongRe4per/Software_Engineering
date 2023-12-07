import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class chatApiHttpClient {

    public static String outputMessage = "";
    private String message = ChatInterface.input;
    private String Url;
    private String ApiKey;

    public chatApiHttpClient() {
        callChatApi();
    }

    public void callChatApi() {
        Url = settingWindow.Url;
        ApiKey = settingWindow.ApiKey;

        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(Url + "/v1/chat/completions");

            // 设置请求头
            httpPost.setHeader("Authorization", "Bearer " + ApiKey);
            httpPost.setHeader("Content-Type", "application/json");

            StringBuilder payload = new StringBuilder("{\"model\": \"gpt-3.5-turbo\",\"messages\": [");

            // 添加用户输入
            payload.append("{\"role\": \"user\", \"content\":\"").append(message).append("\"},");
            // 设置请求体
            String jsonPayload = payload.deleteCharAt(payload.length() - 1).toString() + "]}";
            StringEntity entity = new StringEntity(jsonPayload, "UTF-8");
            httpPost.setEntity(entity);

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpPost);

            // 读取响应内容
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                // 处理传出Json
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(output.toString());

                // 获取"content"字段的内容并传出响应内容
                outputMessage = jsonNode
                        .path("choices")
                        .path(0)
                        .path("message")
                        .path("content")
                        .asText();

                // 添加助手输出
                payload.append("{\"role\": \"assistant\", \"content\":\"").append(outputMessage).append("\"},");
                // 删除尾随逗号并重载 JSON
                payload.deleteCharAt(payload.length() - 1);
                payload.append("]}");
                System.out.println(payload.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
