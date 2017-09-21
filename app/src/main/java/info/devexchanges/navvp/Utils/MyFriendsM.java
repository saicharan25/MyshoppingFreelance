package info.devexchanges.navvp.Utils;

import java.util.ArrayList;
import java.util.List;

import info.devexchanges.navvp.model.Result;

/**
 * Created by Logicnests-Android on 19-10-2015.
 */
public class MyFriendsM {
    private String STATUS;

    private List<Result> Result = new ArrayList<Result>();

    /**
     *
     * @return
     * The STATUS
     */
    public String getSTATUS() {
        return STATUS;
    }

    /**
     *
     * @param STATUS
     * The STATUS
     */
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    /**
     *
     * @return
     * The Result
     */
    public List<Result> getResult() {
        return Result;
    }

    /**
     *
     * @param Result
     * The Result
     */
    public void setResult(List<Result> Result) {
        this.Result = Result;
    }

}
