package com.user.progress.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ApiNotAcceptableException extends AbstractThrowableProblem {

    private static final URI TYPE
            = URI.create("https://locahost");

    public ApiNotAcceptableException(String message) {
        super(
                TYPE,
                Status.NOT_ACCEPTABLE.name(),
                Status.NOT_ACCEPTABLE,
                message);
    }
}
