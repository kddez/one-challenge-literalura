package tech.kddez.literalura.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IDataConverter {


    private final ObjectMapper mapper;

    public DataConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> T extractData(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
