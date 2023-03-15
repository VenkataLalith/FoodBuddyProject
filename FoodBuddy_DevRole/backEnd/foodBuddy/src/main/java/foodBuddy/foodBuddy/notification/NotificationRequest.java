package foodBuddy.foodBuddy.notification;

import lombok.*;


@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
public class NotificationRequest {
    private String groupCode = "";
    private String itemName;

    public NotificationRequest(String groupCode) {
        this.groupCode = groupCode;
    }
}
