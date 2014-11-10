package com.wallops.java;

public class TableFlipException extends RuntimeException {

	private static final long serialVersionUID = 2352454129112623463L;

	public TableFlipException(Throwable e) {
		super("Goddamit Jason >.>", e);
	}
}
