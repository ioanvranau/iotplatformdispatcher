package com.platform.iot.dispatcher.ws;

public class DeviceEchoService implements EchoService {


	private final String echoFormat;

	public DeviceEchoService(String echoFormat) {
		this.echoFormat = (echoFormat != null) ? echoFormat : "%s";
	}

	@Override
	public String getMessage(String message) {
		return String.format(this.echoFormat, message);
	}

}
