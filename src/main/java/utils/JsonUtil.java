package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.logging.Logger;

public class JsonUtil {

    private static Logger logger = Logger.getLogger(JsonUtil.class.getName());

    public static String objToJson(Object o){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.severe("failed to convert object to json");
        }
        return null;
    }
}
