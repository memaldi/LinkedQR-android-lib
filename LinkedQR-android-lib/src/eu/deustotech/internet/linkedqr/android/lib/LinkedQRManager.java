package eu.deustotech.internet.linkedqr.android.lib;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.openrdf.model.Statement;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;

public interface LinkedQRManager {
		
	/***
	 * Describes the resource identified by an URI
	 * 
	 * @return List of retrieved statements.
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws RDFHandlerException 
	 * @throws RDFParseException 
	 */
	public List<Statement> describeResource(URI uri) throws ClientProtocolException, IOException, RDFParseException, RDFHandlerException;
	public URI getURIfromScanner();
}
