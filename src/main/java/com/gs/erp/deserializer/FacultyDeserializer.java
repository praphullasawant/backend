package com.gs.erp.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gs.erp.models.Faculty;

public class FacultyDeserializer extends StdDeserializer<Faculty>{

	private static final long serialVersionUID = 1L;

	public FacultyDeserializer() {
        this(null);
    }
    
    public FacultyDeserializer(Class<?> vc) {
        super(vc);
    }
    
    @Override
    public Faculty deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Integer id = node.asInt();
        String facultyStream = node.asText();
        Boolean checkIfActive = node.asBoolean();
        return new Faculty(id, facultyStream, checkIfActive);
    }
}
