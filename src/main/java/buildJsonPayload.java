public class buildJsonPayload {
    private StringBuilder payloadHead = new StringBuilder("{\"model\": \"gpt-3.5-turbo\",\"messages\": [");
    private String historyMessage = "{\"role\": \"user\", \"content\":\""+ ChatInterface.input +"\"},";
    private String historyReply = "{\"role\": \"assistant\", \"content\":\"" + chatApiHttpClient.outputMessage + "\"},";


}
