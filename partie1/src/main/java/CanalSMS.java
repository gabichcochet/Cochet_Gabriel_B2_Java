public class CanalSMS implements CanalEnvoi {
    @Override
    public void envoyer(String destinataire, String message) {
        System.out.println("[SMS]   -> " + destinataire + " : " + message);
    }
}
