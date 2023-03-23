package io.github.linktosriram.s3lite.core.mapper;

import javax.xml.stream.XMLInputFactory;
import java.io.InputStream;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.Boolean.TRUE;
import static javax.xml.stream.XMLInputFactory.IS_COALESCING;

/**
 * Maps the bytes from XML response to a POJO using the Cursor based StAX API.
 *
 * @param <T> the type of POJO
 */
@FunctionalInterface
public interface ResponseMapper<T> extends Function<InputStream, T> {

    static Supplier<XMLInputFactory> newFactory() {
        return () -> {
            final XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(IS_COALESCING, TRUE);
            return factory;
        };
    }
}
