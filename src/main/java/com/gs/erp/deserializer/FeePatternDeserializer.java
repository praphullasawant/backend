package com.gs.erp.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gs.erp.models.Faculty;
import com.gs.erp.models.FeePattern;

public class FeePatternDeserializer extends StdDeserializer<FeePattern>{

	public FeePatternDeserializer() {
        this(null);
    }
    
    public FeePatternDeserializer(Class<?> vc) {
        super(vc);
    }
    
    @Override
    public FeePattern deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Integer id = node.asInt();
        String feePattern = node.asText();
        String feePatternName = node.asText();
        Integer noOfSemester = node.asInt();
        return new FeePattern(id, feePattern, feePatternName,noOfSemester);
    }
}
