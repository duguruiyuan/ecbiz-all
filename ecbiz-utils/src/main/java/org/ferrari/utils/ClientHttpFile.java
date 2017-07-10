package org.ferrari.utils;

import java.util.*;
import java.io.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.MethodRetryHandler;
import org.apache.commons.httpclient.DefaultMethodRetryHandler;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;


public class ClientHttpFile
{
	private static final Log log = LogFactory.getLog( ClientHttpFile.class );

	public static String wgetString( String weburl, Map params )
	{
		HttpClientParams clientParams = new HttpClientParams();
		clientParams.setSoTimeout( 2000 );

		HttpClient client = new HttpClient( clientParams );
		client.getHttpConnectionManager().getParams().setConnectionTimeout(2000);
		PostMethod method = new PostMethod( weburl );

		String ret = null;
		int status = -1;
		try
		{  
			DefaultMethodRetryHandler retryHandler = new DefaultMethodRetryHandler();
			retryHandler.setRetryCount( 3 );
			method.setMethodRetryHandler( retryHandler );

			Iterator itr = params.keySet().iterator();
			NameValuePair[] nvps = new NameValuePair[ params.size() ];

			int c = 0;
			while( itr.hasNext() )
			{
				String name = (String)itr.next();
				String value = (String)params.get( name );
				NameValuePair nvp = new NameValuePair( name, value );
				nvps[c++] = nvp;
			}
			method.setRequestBody( nvps );
			status = client.executeMethod( method );
			if( status!=HttpStatus.SC_OK )
			{
				log.warn("wgetString not expected -- url="+weburl+" status="+status);
				return null;
			}
			return method.getResponseBodyAsString();
		}catch(Exception e){
			log.warn("wgetString not expected -- url="+weburl+" status="+status);
			return null;
		}finally {
			method.releaseConnection();
		}
	}	
	
	/**
	 *
	 * @param weburl
	 * @param params
	 * @param soTimeout
	 * @return
	 */
	public static String wgetIfcString( String weburl, Map params,int soTimeout )
	{
		HttpClientParams clientParams = new HttpClientParams();
		clientParams.setSoTimeout(soTimeout);

		HttpClient client = new HttpClient( clientParams );
		client.getHttpConnectionManager().getParams().setConnectionTimeout(2000);
		PostMethod method = new PostMethod( weburl );

		String ret = null;
		int status = -1;
		try
		{
			Iterator itr = params.keySet().iterator();
			NameValuePair[] nvps = new NameValuePair[ params.size() ];

			int c = 0;
			while( itr.hasNext() )
			{
				String name = (String)itr.next();
				String value = (String)params.get( name );
				NameValuePair nvp = new NameValuePair( name, value );
				nvps[c++] = nvp;
			}
			method.setRequestBody( nvps );
			
			long timeStart = System.currentTimeMillis();
			status = client.executeMethod( method );
			long timeEnd = System.currentTimeMillis();
			log.info("Cost of client.executeMethod: " + (timeEnd - timeStart) + ". (url=" + weburl + ")");
			
			if( status!=HttpStatus.SC_OK ) {
				log.warn("wgetIfcString status error. url="+weburl+"  status="+status);
				return null;
			}
			return method.getResponseBodyAsString();
		}catch(Exception e){
			log.error("wgetIfcString exception. url="+weburl+" ......status="+status+":"+e.getMessage());
			return null;
		}finally {
			method.releaseConnection();
		}
	}

		//new
	public static String wgetIfcString( String weburl, int soTimeout )
	{
		HttpClientParams clientParams = new HttpClientParams();
		clientParams.setSoTimeout(soTimeout);

		HttpClient client = new HttpClient( clientParams );
		client.getHttpConnectionManager().getParams().setConnectionTimeout(2000);
		GetMethod method = new GetMethod( weburl );

		String ret = null;
		int status = -1;
		try
		{
			status = client.executeMethod( method );
			if( status!=HttpStatus.SC_OK )
			{
				log.warn("wgetIfcString status error. url="+weburl+"  status="+status);
				return null;
			}
			return method.getResponseBodyAsString();
		}catch(Exception e){
			log.error("wgetIfcString exception. url="+weburl+" ......status="+status+":"+e.getMessage());
			return null;
		}finally {
			method.releaseConnection();
		}
	}

}

