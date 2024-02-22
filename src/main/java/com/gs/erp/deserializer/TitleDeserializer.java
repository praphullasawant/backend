package com.gs.erp.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gs.erp.models.Title;

public class TitleDeserializer extends StdDeserializer<Title>{
	public TitleDeserializer() {
        this(null);
    }
    
    public TitleDeserializer(Class<?> vc) {
        super(vc);
    }
    
    @Override
    public Title deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String nametitle = node.asText();
        Integer id = node.asInt();
        Boolean checkIfActive = node.asBoolean();
        return new Title(id, nametitle, checkIfActive);
    }
}
