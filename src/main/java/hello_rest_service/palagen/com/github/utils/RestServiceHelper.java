package hello_rest_service.palagen.com.github.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestServiceHelper {

    public static boolean isStringMatches(String value, String regEx) {

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();

    }

}
