package com.dogan.androidchannelapi;

import android.webkit.WebView;

public class Socket {

	private String mSocketId;
	private SocketHandler mSocketHandler;
	private WebView mWrappedWebView;
	private Channel mChannel;

	/**
	 * Creates a new socket on given socket.
	 *
	 * @param channel new socket is attached to.
	 * @param wrappedWebView
	 */
	public Socket(Channel channel, WebView wrappedWebView){
		mChannel = channel;
		mWrappedWebView = wrappedWebView;
		mSocketId = Utils.random("socket");
	}

	/**
	 * Creates a new socket with callbacks.
	 *
	 * @param channel new socket is attached to.
	 * @param wrappedWebView
	 * @param socketHandler the callback handler.
	 */
	public Socket(Channel channel, WebView wrappedWebView,
			SocketHandler socketHandler){

		this(channel, wrappedWebView);
		this.mSocketHandler = socketHandler;
	}

	/**
	 * Opens this socket.
	 */
	void open(){
		// navigate to JS socket open and
		// append the callback handler, so this socket's
		// SocketHandler will be
		Utils.openSocket(mWrappedWebView,
				mChannel.getChannelId(), mSocketId);

		if(mSocketHandler != null){
			mWrappedWebView.addJavascriptInterface(
				mSocketHandler, "Proxy" + mSocketId);
		}
	}

	/**
	 * Close this socket.
	 */
	public void close(){
		// navigate to JS socket close
		Utils.closeSocket(mWrappedWebView, mSocketId);
	}
}
