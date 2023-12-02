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

    private  String message = "测试";
    private String Url = "https://api.chatanywhere.com.cn";
    private String ApiKey = "sk-bvhVMDkimbCNOeIemOS5giGyCa2CAiXIXKHq0t6ho5TrmBnY";

    public chatApiHttpClient() {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(Url + "/v1/chat/completions");

            // 设置请求头
            httpPost.setHeader("Authorization", "Bearer " + ApiKey );
            httpPost.setHeader("Content-Type", "application/json");

            // 设置请求体
            String jsonPayload = "{\"model\": \"gpt-3.5-turbo\",\"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            StringEntity entity = new StringEntity(jsonPayload);
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

            // 打印响应内容
            System.out.println("Response:\n" + output.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
