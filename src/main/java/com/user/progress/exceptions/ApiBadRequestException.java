package com.user.progress.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ApiBadRequestException extends AbstractThrowableProblem {

    private static final URI TYPE
            = URI.create("https://locahost");

    public ApiBadRequestException(String message) {
        super(
                TYPE,
                Status.BAD_REQUEST.name(),
                Status.BAD_REQUEST,
                message);
    }
}
