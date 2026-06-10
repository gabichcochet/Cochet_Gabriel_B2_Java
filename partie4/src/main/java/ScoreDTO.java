public class ScoreDTO {
    private final String pseudo;
    private final int score;

    public ScoreDTO(String pseudo, int score) {
        this.pseudo = pseudo;
        this.score = score;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getScore() {
        return score;
    }
}
