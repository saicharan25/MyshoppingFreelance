package info.devexchanges.navvp.Utils;

/**
 * Created by sandeep on 18/5/17.
 */

public class Urls {


    // public static String service_url = "https://letmecall.com";
    public static String service_url = "http://honestshoppie.com/services/index.php/";
    //  http://192.168.211.93/imax/api/create

    public static final int DEFAULT_TIMEOUT_MS = 10000;

    /**
     * The default number of retries
     */
    public static final int DEFAULT_MAX_RETRIES = 0;

    /**
     * The default backoff multiplier
     */
    public static final int DEFAULT_BACKOFF_MULT = 0;

    public static String header = "IMAX_API_ACCESS_KEY";
    public static String adminheader = "IMAX_API_ADMIN_ACCESS_KEY";

    public static class Login {
        public static String URL = Urls.service_url + "/api/login";
        // public static String URL = "http://192.168.3.246:1985/MyService.svc/GetMembersInfo/";
        // public static RequestSender.METHOD METHOD = RequestSender.METHOD.GET;
        public static String email = "loginEmail";
        public static String password = "loginPassword";
    }
    // name,email,mobile,password,accountype .
    // if it is normal registration accountype as 4, or facebook accountype as 5, or google accountype as 6

    public static class signup {
        public static String URL = Urls.service_url + "/api/create";
        // public static String URL = "http://192.168.3.246:1985/MyService.svc/GetMembersInfo/";
        // public static RequestSender.METHOD METHOD = RequestSender.METHOD.GET;
        public static String userName = "name";
        public static String password = "password";
        public static String mobile = "mobile";
        public static String email = "email";
        public static String accountype = "accountype";
        public static String source = "source";
    }

    public static class dashboard {
        public static String URL = Urls.service_url + "/api/allcategories";

        // public static RequestSender.METHOD METHOD = RequestSender.METHOD.GET;
        public static String status = "status";

    }

    public static class dashboardlist {
        public static String URL = Urls.service_url + "/api/";

        // public static RequestSender.METHOD METHOD = RequestSender.METHOD.GET;
        public static String status = "status";

    }

    // facebook
    public static class facebook {
        public static String URL = Urls.service_url + "/Api/facebook";
        // public static String URL = "http://192.168.3.246:1985/MyService.svc/GetMembersInfo/";
        // public static RequestSender.METHOD METHOD = RequestSender.METHOD.GET;
        public static String name = "name";
        public static String email = "email";
    }

    public static class ProfileDetails {
        //  http://192.168.211.144/imax/api/getPersonalInfo
        public static String URL = Urls.service_url + "/api/getPersonalInfo";
    }
    //http://192.168.211.144/imax/Api/getUpdatePersonalInfo,getMovieDetails

    public static class UpdatedProfileDetails {
        //  http://192.168.211.144/imax/api/getPersonalInfo
        public static String URL = Urls.service_url + "/api/getUpdatePersonalInfo";
        public static String firstname = "firstname";
        public static String mobile = "mobile";
        public static String address = "address";
        public static String photoimage = "photoimage";
        /*firstname,mobile,address,photoimage
         --> header->IMAX_API_ACCESS_KEY*/
    }


    public static class GetDownloadData {
        //  http://192.168.211.144/imax/api/getPersonalInfo
        public static String URL = Urls.service_url + "/api/getDownloadData";
        public static String moviename = "name";
        public static String video = "video";
        public static String audio = "audio";
        public static String subtitle = "subtitle";

    }

    public static class ForgotPasswword {
        public static String URL = Urls.service_url + "/api/forgotPasswordSend";
        public static String email = "email";

    }

    public static class ChangePassword {
        //  http://192.168.211.144/imax/api/getPersonalInfo
        public static String URL = Urls.service_url + "/api/getPasswordCheck";
        public static String email = "email";


    }

    public static class cities {
        /* "movie":"kesava",
                 "type":"Android"*/
        //  http://192.168.211.144/imax/api/getPersonalInfo
        public static String URL = Urls.service_url + "/api/cities";
        public static String state = "state";


    }

    public static class states {
        /* "movie":"kesava",
                 "type":"Android"*/
        //  http://192.168.211.144/imax/api/getPersonalInfo
        public static String URL = Urls.service_url + "/api/states";
        public static String country = "country";

    }


    public static class getInsertServer {

        public static String URL = Urls.service_url + "/api/getInsertServer";
        public static String user = "user";
        public static String username = "username";
        public static String password = "password";
        public static String serverip = "serverip";
        public static String address = "address";
        public static String latitude = "latitude";
        public static String longitude = "longitude";
        public static String country = "country";
        public static String state = "state";
        public static String city = "city";
        public static String pin = "pin";
        public static String mobile = "mobile";
        public static String email = "email";
        public static String screen = "screen";
        public static String seats = "seats";
        public static String audio = "audio";
        public static String dimentionlength = "dimentionlength";
        public static String dimentionheight = "dimentionheight";
        public static String dimentionwidth = "dimentionwidth";
        public static String imaxunique = "imaxunique";
        public static String model = "model";

    }

    public static class getUpdateServer {

        public static String URL = Urls.service_url + "/api/getUpdateServer";
        public static String user = "user";
        public static String username = "username";
        public static String password = "password";
        public static String serverip = "serverip";
        public static String address = "address";
        public static String latitude = "latitude";
        public static String longitude = "longitude";
        public static String country = "country";
        public static String state = "state";
        public static String city = "city";
        public static String pin = "pin";
        public static String mobile = "mobile";
        public static String email = "email";
        public static String screen = "screen";
        public static String seats = "seats";
        public static String audio = "audio";
        public static String dimentionlength = "dimentionlength";
        public static String dimentionheight = "dimentionheight";
        public static String dimentionwidth = "dimentionwidth";
        public static String imaxunique = "imaxunique";
        public static String model = "model";
        public static String snovalue = "snovalue";

    }
}
