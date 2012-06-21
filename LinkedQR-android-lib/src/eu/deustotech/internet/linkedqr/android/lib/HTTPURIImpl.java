package eu.deustotech.internet.linkedqr.android.lib;

import java.net.URI;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openrdf.model.Statement;

public class HTTPURIImpl implements LinkedQRManager {

	/**
	 * The namespace of the dataset that contains the URIs.
	 */
	private URI namespace;
	/**
	 * The HttpClient instance to connect to server.
	 */
	private HttpClient httpClient;
	private HttpGet httpGet;

	/**
	 * Constructor of HTTPURIImpl class.
	 * 
	 * @param namespace
	 */
	public HTTPURIImpl(URI namespace) {
		this.namespace = namespace;
		this.httpClient = createHTTPClient();
	}

	/**
	 * Opens the HTTP Connection to the server.
	 */
	@Override
	public HttpClient createHTTPClient() {
		if (this.httpClient == null) {
			return  new DefaultHttpClient();
		} else {
			return this.httpClient;
		}
	}

	@Override
	public List<Statement> describeResource(URI uri) {
		
		
		return null;
	}

	private void getHTTPData(URI uri) {
		
	}

	@Override
	public HttpGet createHTTPGet() {
		if (this.httpGet == null) {
			return new HttpGet();
		} else {
			return this.httpGet;
		}
	}
}
