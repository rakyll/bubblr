Android Channel API
===================
android-channel-api is an experimental project that aims to be a native Android client for 
App Engine's Channel API. It wraps a WebView to act as a proxy between regular JavaScript 
API and the native Java implementation.

How to begin
------------
1. You need to upload proxy.html to your App Engine application. You can rename it
to a hard-to-guess name.
2. Create a channel and open a socket on that channel.
```
    Channel channel = new Channel(MainActivity.this, new URL("http://....appspot.com/proxy/proxy.html"), "ef32521");
    Socket socket = channel.open(new SocketHandler() {
        @Override
        public void onOpen() {
            Log.d("channelapi", "Socket is opened");
        }		

        @Override
        public void onMessage(String message) {
            Log.d("channelapi", "Recieved message: " + message);
        }
		
        @Override
        public void onError(String error) {
            Log.d("channelapi", "Error occured: " + error);
        }
				
        @Override
        public void onClose() {
            Log.d("channelapi", "Socket is closed");
        }
    }); ````
