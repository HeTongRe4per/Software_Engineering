public class buildJsonPayload {
    public static StringBuilder payloadHead = new StringBuilder("{\"model\": \"gpt-3.5-turbo\",\"messages\": [");
    public static String historyMessage = "{\"role\": \"user\", \"content\":\""+ ChatInterface.input +"\"},";
    public static String historyReply = "{\"role\": \"assistant\", \"content\":\"" + chatApiHttpClient.outputMessage + "\"},";

    //public static String historyAll = "";

    public buildJsonPayload() {
        payloadHead.append(historyMessage).append(historyReply);
    }
}
