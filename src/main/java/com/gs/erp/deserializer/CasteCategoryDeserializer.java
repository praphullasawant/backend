package com.gs.erp.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gs.erp.models.CasteCategory;

public class CasteCategoryDeserializer extends StdDeserializer<CasteCategory>{
	
	public CasteCategoryDeserializer() {
        this(null);
    }
    
    public CasteCategoryDeserializer(Class<?> vc) {
        super(vc);
    }
    
    @Override
    public CasteCategory deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Integer id = node.asInt();
        String casteCategory = node.asText();
        String description = node.asText();
        String code = node.asText();
        Integer serialNumber = node.asInt();
        Boolean checkIfActive = node.asBoolean();
        Boolean checkIfReserved = node.asBoolean();
        Boolean checkIfOpenCategory = node.asBoolean();
        return new CasteCategory(id, casteCategory, description, code, serialNumber, checkIfActive, checkIfReserved, checkIfOpenCategory);
    }
}
