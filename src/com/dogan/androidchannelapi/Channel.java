package com.dogan.androidchannelapi;

import java.net.URL;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Channel {

	private String mChannelId;
	private String mToken;
	private WebView mWrappedWebView;

	/**
	 * Create a channel object using the token returned by the
	 * create_channel() call on the server.
	 *
	 * @param context the context
	 * @param proxyUrl on your app engine application
	 * @param token channel will be created with
	 */
	public Channel(Context context, URL proxyUrl, String token){
		
		mToken = token;
		mChannelId = Utils.random("channel");
		
		mWrappedWebView = new WebView(context);
		mWrappedWebView.getSettings().setJavaScriptEnabled(true);
		mWrappedWebView.loadUrl(proxyUrl.toString());
		mWrappedWebView.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){
		    	Utils.newChannel(view, mChannelId, mToken);
		    }
		});
	}
	
	/**
	 * Open a socket on this channel. This method returns a Socket
	 * object. You can set the callback methods using a SocketHandler
	 * implementation.
	 *
	 * @param optionalHandler the optional handler
	 * @return the opened socket
	 */
	public Socket open(SocketHandler optionalHandler){
		Socket socket = new Socket(this, mWrappedWebView, optionalHandler);
		socket.open();
		return socket;
	}
	
	
	/**
	 * Opens a socket on this channel. This method 
	 * returns a Socket object.
	 *
	 * @return the opened socket
	 */
	public Socket open(){
		return open(null);
	}
	
	
	/**
	 * Returns the randomly generated channel id.
	 *
	 * @return the channel id
	 */
	public String getChannelId(){
		return mChannelId;
	}
	
	/**
	 * Returns the wrapped web view that works as a
	 * proxy between this lib and Channel API.
	 *
	 * @return the wrapped web view
	 */
	public WebView getWrappedWebView(){
		return mWrappedWebView;
	}
}
