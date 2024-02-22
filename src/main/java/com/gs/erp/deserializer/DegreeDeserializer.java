package com.gs.erp.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gs.erp.models.Degree;

public class DegreeDeserializer extends StdDeserializer<Degree>{

	public DegreeDeserializer() {
        this(null);
    }
    
    public DegreeDeserializer(Class<?> vc) {
        super(vc);
    }
    
    @Override
    public Degree deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Integer id = node.asInt();
        String degreeDiploma = node.asText();
        String degreeDiplomaName = node.asText();
        Boolean checkIfActive = node.asBoolean();
        return new Degree(id, degreeDiploma, degreeDiplomaName, checkIfActive);
    }
}
