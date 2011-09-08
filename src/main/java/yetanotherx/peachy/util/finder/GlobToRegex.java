package yetanotherx.peachy.util.finder;

public class GlobToRegex {

    protected static boolean strict_leading_dot = true;
    protected static boolean strict_wildcard_slash = true;

    public static void setStrictLeadingDot(boolean strict_leading_dot) {
        GlobToRegex.strict_leading_dot = strict_leading_dot;
    }

    public static void setStrictWildcardSlash(boolean strict_wildcard_slash) {
        GlobToRegex.strict_wildcard_slash = strict_wildcard_slash;
    }

    public static String parse(String glob) {
        boolean first_byte = true;
        boolean escaping = false;
        int in_curlies = 0;
        String regex = "";
        int sizeGlob = glob.length();

        for (int i = 0; i < sizeGlob; i++) {
            char car = glob.charAt(i);

            if (first_byte) {
                if (strict_leading_dot && car != '.') {
                    regex += "(?=[^\\.])";
                }
                first_byte = false;
            }

            if (car == '/') {
                first_byte = true;
            }

            if (car == '.' || car == '(' || car == ')' || car == '|' || car == '+' || car == '^' || car == '$') {
                regex += "\\" + String.valueOf(car);
            } else if (car == '*') {
                regex += (escaping ? "\\*" : (strict_wildcard_slash ? "[^/]*" : ".*"));
            } else if (car == '?') {
                regex += (escaping ? "\\?" : (strict_wildcard_slash ? "[^/]" : "."));
            } else if (car == '{') {
                regex += (escaping ? "\\{" : "(");
                if (!escaping) {
                    ++in_curlies;
                }
            } else if (car == '}' && in_curlies != 0) {
                regex += (escaping ? '}' : ')');
                if (!escaping) {
                    --in_curlies;
                }
            } else if (car == ',' && in_curlies != 0) {
                regex += (escaping ? ',' : '|');
            } else if (car == '\\') {
                if (escaping) {
                    regex += "\\\\";
                    escaping = false;
                } else {
                    escaping = true;
                }

                continue;
            } else {
                regex += car;
            }
            escaping = false;
        }
        
        return "#^"+regex+"$#";
    }
    
}
