package ar.com.bago.exception;

import java.text.MessageFormat;
import java.util.List;

public class SendMailException extends RuntimeException {

    private static final long serialVersionUID = 7842675593812304057L;

    private static final String ERROR_MESSAGE_FORMAT = "ERROR trying to send mail to {0} - Subject: {1} - Body: {2}";
    private final List<String> to;
    private final String subject;
    private final String text;

    public SendMailException(Exception e, List<String> to, String subject, String text) {

        super(MessageFormat.format(ERROR_MESSAGE_FORMAT, to, subject, text), e);

        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public List<String> getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

}
