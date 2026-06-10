/**
 * Fabrique de notifications pour le projet de jeu.
 */
public final class NotificationFactory {
    private NotificationFactory() {
    }

    public static Notification urgenceParEmail() {
        return new NotificationUrgente(new CanalEmail());
    }

    public static Notification urgenceParSMS() {
        return new NotificationUrgente(new CanalSMS());
    }

    public static Notification normaleParEmail() {
        return new NotificationNormale(new CanalEmail());
    }

    public static Notification normaleParSMS() {
        return new NotificationNormale(new CanalSMS());
    }
}
