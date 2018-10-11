package util;

/**
 *
 * @author Manuel
 */
public class ServletUtil {
    
    public static final String filter(String text) {
        if(text == null)
        {
            return "";
        }
        StringBuilder filterdText = new StringBuilder("");
        for (char c : text.toCharArray()) {
            switch(c)
            {
                case '<': filterdText.append("&lt;"); break;
                case '>': filterdText.append("&gt;"); break;
                case '"': filterdText.append("&quot;"); break;
                case '&': filterdText.append("&amp;"); break;
                default: filterdText.append(c); break;
            }
        }
        return filterdText.toString();
    }
}
