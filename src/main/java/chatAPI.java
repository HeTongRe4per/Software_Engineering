import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class chatAPI {

    private static StringBuffer inputString = new StringBuffer("{\"model\": \"gpt-3.5-turbo\",\"messages\": [");
    private String API_KEY = settingWindow.ApiKey;
    private String API_URL = settingWindow.Url;
    private String question = ChatInterface.inputMessage;
    public static String answer = "";

    public chatAPI() {
        try {
            httpClient();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void httpClient () throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_URL + "/v1/chat/completions");

        // 设置请求头
        httpPost.setHeader("Authorization", "Bearer " + API_KEY);
        httpPost.setHeader("Content-Type", "application/json");

        // 添加用户输入
        buildInputMessages();
        System.out.println("未\"-1\"输入" + inputString);

        // 设置请求体
        String jsonPayload = inputString.deleteCharAt(inputString.length() - 1) + "]}";
        System.out.println("真正输入" + jsonPayload);
        StringEntity entity = new StringEntity(jsonPayload, "UTF-8");
        httpPost.setEntity(entity);

        // 发送请求
        HttpResponse response = httpClient.execute(httpPost);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))) {
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 处理传出Json
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(output.toString());
            System.out.println("原始输出：" + jsonNode);

            // 获取"content"字段的内容并传出响应内容
            answer = jsonNode
                    .path("choices")
                    .path(0)
                    .path("message")
                    .path("content")
                    .asText();
            inputString.append(",");

            buildOutputMessage();

            inputString.deleteCharAt(inputString.length() - 1);
            inputString.append(",");
            System.out.println("最终状态" + inputString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildInputMessages() {
        String escapedQuestion = StringEscapeUtils.escapeJson(question);
        String inputFormWork = "{\"role\": \"user\", \"content\":\"" + escapedQuestion + "\"},";
        inputString.append(inputFormWork);
    }

    private void buildOutputMessage() {
        String escapedAnswer = StringEscapeUtils.escapeJson(answer);
        String outputFormWork = "{\"role\": \"assistant\", \"content\":\"" + escapedAnswer + "\"},";
        inputString.append(outputFormWork);
    }

    public static void resetInputString() {
        inputString.setLength(0);
        inputString.append("{\"model\": \"gpt-3.5-turbo\",\"messages\": [");
    }
}
