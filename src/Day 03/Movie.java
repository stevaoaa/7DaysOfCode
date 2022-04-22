public class Movie {

    private String id;
    private String rank;
    private String title;
    private String fullTitle;
    private int year;
    private String urlImage;
    private String crew;
    private float imDbRating;
    private int imDbRatingCount;

    public Movie(String id, String rank, String title, String fullTitle, int year, String urlImage, String crew, float imDbRating, int imDbRatingCount) {
        this.id = id;
        this.rank = rank;
        this.title = title;
        this.fullTitle = fullTitle;
        this.year = year;
        this.urlImage = urlImage;
        this.crew = crew;
        this.imDbRating = imDbRating;
        this.imDbRatingCount = imDbRatingCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public float getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(float imDbRating) {
        this.imDbRating = imDbRating;
    }

    public int getImDbRatingCount() {
        return imDbRatingCount;
    }

    public void setImDbRatingCount(int imDbRatingCount) {
        this.imDbRatingCount = imDbRatingCount;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", rank='" + rank + '\'' +
                ", title='" + title + '\'' +
                ", fullTitle='" + fullTitle + '\'' +
                ", year=" + year +
                ", urlImage='" + urlImage + '\'' +
                ", crew='" + crew + '\'' +
                ", imDbRating=" + imDbRating +
                ", imDbRatingCount=" + imDbRatingCount +
                '}';
    }
}
