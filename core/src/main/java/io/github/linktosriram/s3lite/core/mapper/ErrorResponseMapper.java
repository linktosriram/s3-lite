package io.github.linktosriram.s3lite.core.mapper;

import io.github.linktosriram.s3lite.api.exception.ErrorResponse;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

public class ErrorResponseMapper implements ResponseMapper<ErrorResponse> {

    @Override
    public ErrorResponse apply(final InputStream is) {
        final XMLInputFactory factory = ResponseMapper.newFactory().get();

        ErrorResponse.Builder errorResponse = null;
        boolean bCode, bMessage, bRequestId, bResource;
        bCode = bMessage = bRequestId = bResource = false;

        try {
            final XMLStreamReader reader = factory.createXMLStreamReader(is);
            while (reader.hasNext()) {
                switch (reader.next()) {
                    case XMLStreamConstants.START_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "Error":
                                errorResponse = ErrorResponse.builder();
                                break;
                            case "Code":
                                bCode = true;
                                break;
                            case "Message":
                                bMessage = true;
                                break;
                            case "RequestId":
                                bRequestId = true;
                                break;
                            case "Resource":
                                bResource = true;
                                break;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (errorResponse != null) {
                            if (bCode) {
                                errorResponse.code(reader.getText());
                            } else if (bMessage) {
                                errorResponse.message(reader.getText());
                            } else if (bRequestId) {
                                errorResponse.requestId(reader.getText());
                            } else if (bResource) {
                                errorResponse.resource(reader.getText());
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "Code":
                                bCode = false;
                                break;
                            case "Message":
                                bMessage = false;
                                break;
                            case "RequestId":
                                bRequestId = false;
                                break;
                            case "Resource":
                                bResource = false;
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        reader.close();
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }

        return errorResponse != null ? errorResponse.build() : null;
    }
}
