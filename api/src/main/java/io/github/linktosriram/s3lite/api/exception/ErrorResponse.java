package io.github.linktosriram.s3lite.api.exception;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Reference: https://docs.aws.amazon.com/AmazonS3/latest/API/ErrorResponses.html
 */
@XmlRootElement(name = "Error")
public class ErrorResponse {

    private String code;

    private String message;

    private String requestId;

    private String hostId;

    @XmlElement(name = "Code", required = true)
    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @XmlElement(name = "Message", required = true)
    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @XmlElement(name = "RequestId", required = true)
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }

    @XmlElement(name = "HostId", required = true)
    public String getHostId() {
        return hostId;
    }

    public void setHostId(final String hostId) {
        this.hostId = hostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(code, that.code) &&
            Objects.equals(message, that.message) &&
            Objects.equals(requestId, that.requestId) &&
            Objects.equals(hostId, that.hostId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, requestId, hostId);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            ", requestId='" + requestId + '\'' +
            ", hostId='" + hostId + '\'' +
            '}';
    }
}
