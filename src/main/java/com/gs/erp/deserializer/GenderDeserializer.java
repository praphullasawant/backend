package com.gs.erp.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gs.erp.models.Gender;

public class GenderDeserializer extends StdDeserializer<Gender>{

	public GenderDeserializer() {
        this(null);
    }
    
    public GenderDeserializer(Class<?> vc) {
        super(vc);
    }
    
    @Override
    public Gender deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String genders = node.asText();
        Integer id = node.asInt();
        Boolean checkIfActive = node.asBoolean();
        return new Gender(id, genders, checkIfActive);
    }
    
}
