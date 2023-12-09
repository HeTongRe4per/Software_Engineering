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
    private String Url;
    private String ApiKey;

    public chatApiHttpClient() {
        callChatApi();
    }

    private void callChatApi() {
        Url = settingWindow.Url;
        ApiKey = settingWindow.ApiKey;

        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(Url + "/v1/chat/completions");

            // 设置请求头
            httpPost.setHeader("Authorization", "Bearer " + ApiKey);
            httpPost.setHeader("Content-Type", "application/json");

            // 添加用户输入
            buildJsonPayload.refreshInputMessage();
            buildJsonPayload.payload.append(buildJsonPayload.historyMessage);

            // 设置请求体
            String jsonPayload = buildJsonPayload.payload.deleteCharAt( buildJsonPayload.payload.length() - 1).toString() + "]}";
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
                buildJsonPayload.payload.append(",");
                buildJsonPayload.getOutputMessage();
                // 添加助手输出
                buildJsonPayload.payload.append(buildJsonPayload.historyReply);
                // 删除尾随逗号并重载 JSON
                buildJsonPayload.payload.deleteCharAt(  buildJsonPayload.payload.length() - 1);
                buildJsonPayload.payload.append(",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
