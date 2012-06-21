package eu.deustotech.internet.linkedqr.android.lib;

import java.net.URI;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.openrdf.model.Statement;

public interface LinkedQRManager {
		
	/***
	 * Describes the resource identified by an URI
	 * 
	 * @return List of retrieved statements.
	 */
	public List<Statement> describeResource(URI uri);
	public HttpClient createHTTPClient();
	public HttpGet createHTTPGet();
	
}
