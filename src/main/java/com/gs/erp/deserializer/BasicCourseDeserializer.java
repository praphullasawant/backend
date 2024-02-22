package com.gs.erp.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gs.erp.models.BasicCourse;
import com.gs.erp.models.Cashbook;
import com.gs.erp.models.Degree;
import com.gs.erp.models.Faculty;

public class BasicCourseDeserializer extends StdDeserializer<BasicCourse> {

    public BasicCourseDeserializer() {
        this(null);
    }

    public BasicCourseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public BasicCourse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        Integer id = node.has("id") ? node.get("id").asInt() : null;
        Faculty faculty = node.has("faculty") ? ctxt.readValue(node.get("faculty").traverse(), Faculty.class) : null;
        Degree degree = node.has("degree") ? ctxt.readValue(node.get("degree").traverse(), Degree.class) : null;
        Cashbook cashbook = node.has("cashbook") ? ctxt.readValue(node.get("cashbook").traverse(), Cashbook.class) : null;
        String courseLevel = node.has("courseLevel") ? node.get("courseLevel").asText() : null;
        String basicCourse = node.has("basicCourse") ? node.get("basicCourse").asText() : null;
        String shortName = node.has("shortName") ? node.get("shortName").asText() : null;
        Long serialNumber = node.has("serialNumber") ? node.get("serialNumber").asLong() : null;
        Boolean active = node.has("active") ? node.get("active").asBoolean() : null;

        return new BasicCourse(id, faculty, degree, cashbook, courseLevel, basicCourse, shortName, serialNumber, active);
    }
}
