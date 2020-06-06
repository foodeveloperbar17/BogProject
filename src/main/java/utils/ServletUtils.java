package utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class ServletUtils {
    public static void safePrint(String message, HttpServletResponse resp, Logger logger) {
        logger.info(message);
        try {
            resp.getOutputStream().println(message);
        } catch (IOException e) {
            logger.severe("IO Exception in response output stream");
        }
    }
}
