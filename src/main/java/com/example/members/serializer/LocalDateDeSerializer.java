package com.example.members.serializer;

import com.example.members.exceptions.DateIncorrectException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class LocalDateDeSerializer  extends StdDeserializer<LocalDate> {

    public static String DATE_FORMAT = "yyyy-MM-dd";

    public LocalDateDeSerializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException, JacksonException, DateIncorrectException {
        String value = jsonParser.readValueAs(String.class);
        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            log.info("Error formato fecha " + value );
            throw new DateIncorrectException(value);
        }
    }
}
