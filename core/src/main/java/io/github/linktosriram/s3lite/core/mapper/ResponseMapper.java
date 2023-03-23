package io.github.linktosriram.s3lite.core.mapper;

import javax.xml.stream.XMLInputFactory;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.xml.stream.XMLInputFactory.IS_COALESCING;
import static javax.xml.stream.XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES;
import static javax.xml.stream.XMLInputFactory.SUPPORT_DTD;

/**
 * Maps the bytes from XML response to a POJO using the Cursor based StAX API.
 *
 * @param <T> the type of POJO
 */
@FunctionalInterface
public interface ResponseMapper<T> extends Function<byte[], T> {

    static Supplier<XMLInputFactory> newFactory() {
        return () -> {
            final XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(IS_COALESCING, TRUE);
            factory.setProperty(SUPPORT_DTD, FALSE);
            factory.setProperty(IS_SUPPORTING_EXTERNAL_ENTITIES, FALSE);
            return factory;
        };
    }
}
