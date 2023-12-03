import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
  ChatGPIAPI调用
**/

/**
 * @auhtor zhang
**/

public class chatApiHttpClient {

    private  String message;
    static String outputMessage = "";
    private String Url;
    private String ApiKey;

    public chatApiHttpClient() {
        message = ChatInterface.input;
        Url = settingWindow.Url;
        ApiKey = settingWindow.ApiKey;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(Url + "/v1/chat/completions");

            // 设置请求头
            httpPost.setHeader("Authorization", "Bearer " + ApiKey );
            httpPost.setHeader("Content-Type", "application/json");

            // 设置请求体
            String jsonPayload = "{\"model\": \"gpt-3.5-turbo\",\"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            StringEntity entity = new StringEntity(jsonPayload, "UTF-8");
            httpPost.setEntity(entity);

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpPost);

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 处理传出Json
            String json = String.valueOf(output);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(json);

                // 获取"content"字段的内容
                String content = jsonNode
                        .path("choices")
                        .path(0)
                        .path("message")
                        .path("content")
                        .asText();
                // 传出响应内容
                outputMessage = content;
                System.out.println("Content: " + content);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
