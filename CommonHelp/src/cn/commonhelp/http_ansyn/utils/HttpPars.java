package cn.commonhelp.http_ansyn.utils;

public class HttpPars
{

	public static final String LOG_TAG = "AsyncHttpClient";

	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HEADER_CONTENT_RANGE = "Content-Range";
	public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
	public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
	public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
	public static final String ENCODING_GZIP = "gzip";

	public static final int DEFAULT_MAX_CONNECTIONS = 10;
	public static final int DEFAULT_SOCKET_TIMEOUT = 10 * 1000;
	public static final int DEFAULT_MAX_RETRIES = 5;
	public static final int DEFAULT_RETRY_SLEEP_TIME_MILLIS = 1500;
	public static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;
	@SuppressWarnings("unused")
	private int maxConnections = DEFAULT_MAX_CONNECTIONS;
	@SuppressWarnings("unused")
	private int connectTimeout = DEFAULT_SOCKET_TIMEOUT;
	@SuppressWarnings("unused")
	private int responseTimeout = DEFAULT_SOCKET_TIMEOUT;
	@SuppressWarnings("unused")
	private boolean isUrlEncodingEnabled = true;

	private HttpPars()
	{
	}
}
