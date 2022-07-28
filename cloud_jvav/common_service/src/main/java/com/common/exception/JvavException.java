package com.common.exception;

public class JvavException extends Exception{
    public JvavException() {
        super();
    }

    public JvavException(String s) {
        super(s);
    }

    public JvavException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public JvavException(Throwable throwable) {
        super(throwable);
    }

    public JvavException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
