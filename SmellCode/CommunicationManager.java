import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class CommunicationManager {
    public static void main(String[] args) {
        MessagingService messagingService = new MessagingService();

        messagingService.sendMessage("Hello, tenant!", "Property Manager", "Tenant A");
        messagingService.sendMessage("Important notice: Rent due next week.", "Property Owner", "Tenant A");
        messagingService.sendMessage("Maintenance request received.", "Tenant A", "Property Manager");

        System.out.println("Messages for Tenant A:");
        messagingService.printMessagesForRecipient("Tenant A");

        System.out.println("Messages for Property Owner:");
        messagingService.printMessagesForRecipient("Property Owner");

        System.out.println("Messages for Property Manager:");
        messagingService.printMessagesForRecipient("Property Manager");

        System.out.println("\nAll Messages:");
        messagingService.printAllMessages();
    }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Message {
    private final String content;
    private final String sender;
    private final String recipient;

    public Message(String content, String sender, String recipient) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;

    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    @Override
    public String toString() {
        return String.format("From: %s | To: %s  | Content: %s",
                sender, recipient, content);
    }
}


class MessagingService {
    private final Map<String, List<Message>> inbox = new HashMap<>();

    public void sendMessage(String content, String sender, String recipient) {
        Message message = new Message(content, sender, recipient);
        inbox.computeIfAbsent(recipient, k -> new ArrayList<>()).add(message);
    }

    public List<Message> getMessagesForRecipient(String recipient) {
        return Collections.unmodifiableList(inbox.getOrDefault(recipient, new ArrayList<>()));
    }

    public void printMessagesForRecipient(String recipient) {
        List<Message> messages = inbox.getOrDefault(recipient, Collections.emptyList());
        if (messages.isEmpty()) {
            System.out.println("No messages found for " + recipient);
            return;
        }
        messages.forEach(System.out::println);
    }

    public void printAllMessages() {
        inbox.forEach((recipient, messages) -> {
            System.out.println("Messages for: " + recipient);
            messages.forEach(System.out::println);
            System.out.println("---------------");
        });
    }
}