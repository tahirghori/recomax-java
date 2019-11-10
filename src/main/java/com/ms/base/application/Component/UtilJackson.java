package com.ms.base.application.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.base.beam.BaseModel;
import org.springframework.stereotype.Component;

@Component
public class UtilJackson<I extends BaseModel> {

    public I requestCast(Class<?> T, JsonNode jsonNode){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        I item = null;
        try {
            item = (I) mapper.treeToValue(jsonNode, T);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return item;

    }
}
