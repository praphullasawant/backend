package com.gs.erp.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gs.erp.models.Cashbook;
import com.gs.erp.models.CasteCategory;

public class CashbookDeserializer extends StdDeserializer<Cashbook>{

	public CashbookDeserializer() {
        this(null);
    }
    
    public CashbookDeserializer(Class<?> vc) {
        super(vc);
    }
    
    @Override
    public Cashbook deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Integer id = node.asInt();
        String receiptBookCode = node.asText();
        String receiptBookName = node.asText();
        String cashReceiptNo = node.asText();
        String bankReceiptNo = node.asText();
        Integer documentReceiptNo = node.asInt();
        String hsnsacNo = node.asText();
        String receiptBookType = node.asText();
        Integer srNo = node.asInt();
        Boolean checkIfGstApplicable = node.asBoolean();
        Boolean checkIfActive = node.asBoolean();
        return new Cashbook(id, receiptBookCode,receiptBookName,cashReceiptNo,bankReceiptNo,documentReceiptNo, hsnsacNo, receiptBookType, srNo, checkIfGstApplicable, checkIfActive);
    }
}
