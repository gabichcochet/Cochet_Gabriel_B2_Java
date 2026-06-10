public class CanalEmail implements CanalEnvoi {
    @Override
    public void envoyer(String destinataire, String message) {
        System.out.println("[EMAIL] -> " + destinataire + " : " + message);
    }
}
