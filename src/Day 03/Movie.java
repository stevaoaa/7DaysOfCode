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

    public String getTitle() {
        return title;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public int getYear() {
        return year;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getCrew() {
        return crew;
    }

    public float getImDbRating() {
        return imDbRating;
    }

    public int getImDbRatingCount() {
        return imDbRatingCount;
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
