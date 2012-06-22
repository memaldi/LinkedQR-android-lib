package eu.deustotech.internet.linkedqr.android.lib;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openrdf.model.Statement;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.helpers.StatementCollector;
import org.openrdf.rio.turtle.TurtleParser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class HTTPURILinkedQRManager extends Activity implements LinkedQRManager {

	/**
	 * The HttpClient instance to connect to server.
	 */
	private HttpClient httpClient;
	private HttpGet httpGet;
	private StatementCollector statementCollector;
	private RDFParser rdfParser;
	private PackageInfo packageInfo;
	private String packageURI;
	private String packageAction;
	private Map<String, String> packageParams;
	private static final int QR_CODE_REQUEST_CODE = 5678;
	/**
	 * Constructor of HTTPURIImpl class.
	 * @throws NameNotFoundException 
	 * 
	 */
	public HTTPURILinkedQRManager(String packageURI, String packageAction, Context context, Map<String, String> packageParams) throws NameNotFoundException {
		this.httpClient = new DefaultHttpClient();
		this.httpGet = new HttpGet();
		this.httpGet.addHeader("Accept", "text/turtle");
		this.statementCollector = new StatementCollector();
		this.rdfParser = new TurtleParser();
		this.rdfParser.setRDFHandler(this.statementCollector);
		this.packageURI = packageURI;
		this.packageAction = packageAction;
		//TODO: If null, launch market
		this.packageInfo = searchPackage(packageURI, context);
		this.packageParams = packageParams;
	}
	
	private PackageInfo searchPackage(String packageURI, Context context) throws NameNotFoundException {
		PackageManager packageManager = context.getPackageManager();
		PackageInfo pi = packageManager.getPackageInfo(packageURI, PackageManager.GET_ACTIVITIES);
		return pi;
	}
	
	public URI getURIfromScanner() {
		Intent scannerIntent = new Intent(this.packageURI + "." + this.packageAction);
		for (String key : this.packageParams.keySet()) {
			String param = this.packageParams.get(key);
			scannerIntent.putExtra(key, param);
		}
		startActivityForResult(scannerIntent, QR_CODE_REQUEST_CODE);
		return null;
	}

	@Override
	public List<Statement> describeResource(URI uri) throws ClientProtocolException, IOException, RDFParseException, RDFHandlerException {
		InputStream inputStream = this.getHTTPData(uri);
		this.rdfParser.parse(inputStream, uri.toString());
		return (List<Statement>) this.statementCollector.getStatements();
	}

	private InputStream getHTTPData(URI uri) throws ClientProtocolException, IOException {
		this.httpGet.setURI(uri);
		HttpResponse response = this.httpClient.execute(this.httpGet);
		return response.getEntity().getContent();
	}
}
