package utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonUtils {

    @SneakyThrows
    public static <T> String convertObjectToJson(T obj) {
        String jsonStringReq = null;

        ObjectMapper objMapper = new ObjectMapper();
        jsonStringReq = objMapper.writeValueAsString(obj);
        return jsonStringReq;

    }
}
