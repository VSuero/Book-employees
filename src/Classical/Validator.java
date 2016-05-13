package Classical;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String EXPREG = "(\\d{3})[-]?(\\d{7})[-]?(\\d)";
    private static final Pattern PATTERN = Pattern.compile(EXPREG);
    
    private Validator() {}
    
    public static String validate(String _id) {
        String id;
        
        Matcher matcher = PATTERN.matcher(_id);
        
        if(matcher.find()) {
            id = matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3);
        }else {
            id = "000-0000000-0";
        }
        
        return id;
    }
}