import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Rest(path = "/api/scores")
public class ScoreController {
    private static final String SCORE_FILE = "scores.csv";

    @Get(path = "/top")
    public String getTop(@QueryParam(name = "limit") int limit) {
        try {
            return ScoreManager.charger(SCORE_FILE).stream()
                    .sorted(Comparator.<Paire<String, Integer>, Integer>comparing(Paire::getSecond).reversed())
                    .limit(limit)
                    .map(score -> score.getFirst() + ":" + score.getSecond())
                    .collect(Collectors.joining(","));
        } catch (IOException e) {
            return "";
        }
    }

    @Post(path = "/add")
    public String add(ScoreDTO dto) {
        try {
            List<Paire<String, Integer>> scores = ScoreManager.charger(SCORE_FILE);
            scores.add(new Paire<>(dto.getPseudo(), dto.getScore()));
            ScoreManager.sauvegarder(SCORE_FILE, scores);
            return "OK";
        } catch (IOException e) {
            return "ERROR";
        }
    }
}
