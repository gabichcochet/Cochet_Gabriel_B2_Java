import java.util.concurrent.atomic.AtomicInteger;

public class GameServer {
    private static volatile GameServer instance;
    private final int port;
    private final AtomicInteger connectedPlayers = new AtomicInteger();

    private GameServer() {
        this.port = 8080;
    }

    public static GameServer getInstance() {
        GameServer result = instance;
        if (result == null) {
            synchronized (GameServer.class) {
                result = instance;
                if (result == null) {
                    result = new GameServer();
                    instance = result;
                }
            }
        }
        return result;
    }

    public int connect() {
        return connectedPlayers.incrementAndGet();
    }

    public int getConnectedPlayers() {
        return connectedPlayers.get();
    }

    public int getPort() {
        return port;
    }
}
