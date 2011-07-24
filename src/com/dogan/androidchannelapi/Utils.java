package com.dogan.androidchannelapi;

import java.util.UUID;

import android.util.Log;
import android.webkit.WebView;

public class Utils {
	
	/**
	 * Create a new channel on Web proxy.
	 *
	 * @param webView the web view
	 * @param channelId identifies the newly created channel.
	 * @param token
	 */
	public static void newChannel(
			WebView webView, String channelId, String token){
		
		String command = String.format("javascript:new ChannelProxy('%s', '%s');", 
				channelId, token);
		Log.d("channelapi", command);
		webView.loadUrl(command);	
	}
	
	/**
	 * Open a socket on Web proxy.
	 *
	 * @param webView the web view
	 * @param channelId the channel socket will be opened on.
	 * @param socketId identifies the socket that will be opened.
	 */
	public static void openSocket(
			WebView webView, String channelId, String socketId){
		String command = String.format("javascript:channels['%s'].open('%s')", 
				channelId, socketId);
		Log.d("channelapi", command);
		webView.loadUrl(command);
	}
	
	/**
	 * Close socket on Web proxy.
	 *
	 * @param webView the web view
	 * @param socketId identifies the socket identifier that
	 * will be closed
	 */
	public static void closeSocket(
			WebView webView, String socketId){
		String command = String.format("javascript:sockets['%s'].close()", 
				socketId);
		Log.d("channelapi", command);
		webView.loadUrl(command);
	}
	
	/**
	 * Generates a random string with given prefix.
	 *
	 * @param prefix the prefix
	 * @return generated random string
	 */
	public static String random(String prefix){
		String uuidWithoutDashes = UUID.randomUUID().toString().replaceAll("-", "");
		return prefix + uuidWithoutDashes;
	}

}
