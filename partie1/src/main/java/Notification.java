public abstract class Notification {
    protected final CanalEnvoi canal;

    public Notification(CanalEnvoi canal) {
        this.canal = canal;
    }

    public abstract void notifier(String destinataire, String contenu);
}
