package info.devexchanges.navvp.model;

/**
 * Created by sandeep on 18/5/17.
 */

public class UpcomingMovies {
    private Result[] result;

    public Result[] getResult ()
    {
        return result;
    }

    public void setResult (Result[] result)
    {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+"]";
    }
}
