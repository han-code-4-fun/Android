package popularmovies.examlple.com.popularmovies.Data;

public class Movie {


    private String original_title;
    private String imagePath;
    private String overview;

    //this property is saved for future development
    private double popularity;
    private double vote_average;
    private String release_date;

    public Movie(
                 String original_title,
                 String imagePath,
                 String overview,

                 double popularity,
                 double vote_average,
                 String release_date)
    {
        this.original_title = original_title;
        this.imagePath = imagePath;
        this.overview = overview;
        this.popularity = popularity;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getOverview() {
        return overview;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public double getPopularity() {
        return popularity;
    }

}
