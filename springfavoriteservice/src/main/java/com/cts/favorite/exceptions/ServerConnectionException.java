package com.cts.favorite.exceptions;

import java.io.Serial;

public class ServerConnectionException  extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public ServerConnectionException(String message) {
        super(message);
    }
}
