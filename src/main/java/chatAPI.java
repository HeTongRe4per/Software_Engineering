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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author zhang
 */
public class chatAPI {

    public chatAPI() {
        if (messageCount < settingWindow.rememberLength) { // 防止token一次消耗过多导致报错，限制一次发送消息数量
            try {
                httpClient();
            } catch (IOException e) { throw new RuntimeException(e); }
        } else {
            outMessageInputString();
            try { httpClient(); } catch (IOException e) { throw new RuntimeException(e); }
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
        //System.out.println("未\"-1\"输入" + inputString);

        // 设置请求体
        String jsonPayload = inputString.deleteCharAt(inputString.length() - 1) + "]}";
        //System.out.println("真正输入" + jsonPayload);
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
            //System.out.println("原始输出：" + jsonNode);

            // 获取"content"字段的内容并传出响应内容
            answer = jsonNode
                    .path("choices")
                    .path(0)
                    .path("message")
                    .path("content")
                    .asText();
            inputString.append(",");

            // 添加回答到inputMessage
            buildOutputMessage();

            // 删掉"}"添加","
            inputString.deleteCharAt(inputString.length() - 1);
            inputString.append(",");
            messageCount++;
            //System.out.println("最终状态" + inputString);
            //System.out.println("会话数：" + messageCount);

        } catch (Exception e) { e.printStackTrace(); }
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

    static void resetInputString() {
        inputString.setLength(0);
        inputString.append("{\"model\": \"gpt-3.5-turbo\",\"messages\": [").append(ChatInterface.promptContent);
        messageCount = 0;
    }


    static void outMessageInputString() {
        // 通过查找第二个“,{"role": "user", "content”前的逗号来移除最早的对话
        //String message = inputString.toString();
        Pattern pattern = Pattern.compile(",\\{\"role\": \"user\", \"content\":\".*?\"}");
        Matcher matcher = pattern.matcher(inputString);
        //int secondCommaIndex = inputString.indexOf(",{\"role\": \"user\", \"content\"");

        int secondCommaIndex = -1;
        int count = 0;

        while (matcher.find()) {
            count++;
            if (count == 2) {
                secondCommaIndex = matcher.end();
                break;
            }
        }

        // 如果找到第二个逗号，删除逗号及其之前的内容
        if (secondCommaIndex != -1) {
            inputString.delete(0, secondCommaIndex + 1);
        } else {
            // 如果未找到第二个逗号，直接清除整个 inputString
            inputString.setLength(0);
        }

        // 在 inputString 前插入初始部分
        inputString.insert(0, "{\"model\": \"gpt-3.5-turbo\",\"messages\": [" + ChatInterface.promptContent);
        //System.out.println("超过后处理：" + inputString);
    }

    private static final StringBuffer inputString = new StringBuffer("{\"model\": \"gpt-3.5-turbo\",\"messages\": [" + ChatInterface.promptContent);
    private final String API_KEY = settingWindow.ApiKey;
    private final String API_URL = settingWindow.Url;
    private final String question = ChatInterface.inputMessage;
    static String answer = "";
    private static Integer  messageCount = 0;
}
