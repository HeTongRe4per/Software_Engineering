/*
public class buildJsonPayload {
    public static StringBuilder payload = new StringBuilder("{\"model\": \"gpt-3.5-turbo\",\"messages\": [");
    public static String historyMessage;
    public static String historyReply;

    public static void getOutputMessage() {
        String output = chatApiHttpClient.outputMessage;
        historyReply = "{\"role\": \"assistant\", \"content\":\"" + output + "\"},";
    }

    public static void refreshInputMessage() {
        String input = ChatInterface.inputMessage;
        historyMessage = "{\"role\": \"user\", \"content\":\"" + ChatInterface.inputMessage + "\"},";
    }
}
*/
