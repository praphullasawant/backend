package com.gs.erp.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gs.erp.models.CoursePattern;

public class CoursePatternDeserializer extends StdDeserializer<CoursePattern>{
	public CoursePatternDeserializer() {
        this(null);
    }
    
    public CoursePatternDeserializer(Class<?> vc) {
        super(vc);
    }
    @Override
    public CoursePattern deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Integer id = node.asInt();
        String coursePattern = node.asText();
        String coursePatternName = node.asText();
        Integer noOfSemester = node.asInt();
        Boolean checkIfActive = node.asBoolean();
        return new CoursePattern(id, coursePattern,coursePatternName,noOfSemester, checkIfActive);
    }
}
