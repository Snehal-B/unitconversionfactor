package com.mms.api.unitconversionfactor.exception;

/**
 * @author snehal.bachchhav
 *
 */
public class UnitConversionNotFoundException extends Exception {

	private static final long serialVersionUID = -7118790206151347691L;

	public UnitConversionNotFoundException(String from, String to) {
		super(String.format("Can not Convert from %s to %s", from, to));
	}

	public UnitConversionNotFoundException(String message) {
		super(message);
	}

}
