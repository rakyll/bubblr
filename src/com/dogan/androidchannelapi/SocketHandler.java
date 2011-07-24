package com.dogan.androidchannelapi;

public interface SocketHandler {
	public void onOpen();
	public void onMessage(String message);
	public void onError(String error);
	public void onClose();
}
