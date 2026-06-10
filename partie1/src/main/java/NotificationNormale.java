public class NotificationNormale extends Notification {
    public NotificationNormale(CanalEnvoi canal) {
        super(canal);
    }

    @Override
    public void notifier(String destinataire, String contenu) {
        canal.envoyer(destinataire, contenu);
    }
}
