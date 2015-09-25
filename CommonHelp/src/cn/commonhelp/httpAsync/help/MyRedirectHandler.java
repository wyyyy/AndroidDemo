/*
    Android Asynchronous Http Client
    Copyright (c) 2014 Aymon Fournier <aymon.fournier@gmail.com>
    https://loopj.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

package cn.commonhelp.httpAsync.help;

import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.impl.client.RedirectLocations;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

/**
 * Taken from StackOverflow
 * 
 * @author Aymon Fournier, aymon.fournier@gmail.com
 * @see <a
 *      href="https://stackoverflow.com/questions/3420767/httpclient-redirecting-to-url-with-spaces-throwing-exception">https://stackoverflow.com/questions/3420767/httpclient-redirecting-to-url-with-spaces-throwing-exception</a>
 */
class MyRedirectHandler extends DefaultRedirectHandler
{

	private static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
	private final boolean enableRedirects;

	public MyRedirectHandler(final boolean allowRedirects)
	{
		super();
		this.enableRedirects = allowRedirects;
	}

	@Override
	public boolean isRedirectRequested(final HttpResponse response,
			final HttpContext context)
	{
		if (!enableRedirects)
		{
			return false;
		}
		if (response == null)
		{
			throw new IllegalArgumentException("HTTP response may not be null");
		}
		int statusCode = response.getStatusLine().getStatusCode();
		switch (statusCode)
		{
		case HttpStatus.SC_MOVED_TEMPORARILY:
		case HttpStatus.SC_MOVED_PERMANENTLY:
		case HttpStatus.SC_SEE_OTHER:
		case HttpStatus.SC_TEMPORARY_REDIRECT:
			return true;
		default:
			return false;
		} // end of switch
	}

	@Override
	public URI getLocationURI(final HttpResponse response,
			final HttpContext context)
	{
		if (response == null)
		{
			throw new IllegalArgumentException("HTTP response may not be null");
		}
		// get the location header to find out where to redirect to
		Header locationHeader = response.getFirstHeader("location");
		if (locationHeader == null)
		{
			// got a redirect response, but no location header
			try
			{
				throw new ProtocolException("Received redirect response "
						+ response.getStatusLine() + " but no location header");
			} catch (ProtocolException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// HERE IS THE MODIFIED LINE OF CODE
		String location = locationHeader.getValue().replaceAll(" ", "%20");

		URI uri = null;
		try
		{
			uri = new URI(location);
		} catch (URISyntaxException ex)
		{
			try
			{
				throw new ProtocolException();
			} catch (ProtocolException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		HttpParams params = response.getParams();
		// rfc2616 demands the location value be a complete URI
		// Location = "Location" ":" absoluteURI
		// Relative redirect location not allowed
		if (!uri.isAbsolute())
		{
			if (params.isParameterTrue(ClientPNames.REJECT_RELATIVE_REDIRECT))
			{
				try
				{
					throw new ProtocolException("Relative redirect location '"
							+ uri + "' not allowed");
				} catch (ProtocolException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// Adjust location URI
			HttpHost target = (HttpHost) context
					.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
			if (target == null)
			{
				throw new IllegalStateException("Target host not available "
						+ "in the HTTP context");
			}

			HttpRequest request = (HttpRequest) context
					.getAttribute(ExecutionContext.HTTP_REQUEST);

			try
			{
				URI requestURI = new URI(request.getRequestLine().getUri());
				URI absoluteRequestURI = URIUtils.rewriteURI(requestURI,
						target, true);
				uri = URIUtils.resolve(absoluteRequestURI, uri);
			} catch (URISyntaxException ex)
			{
				try
				{
					throw ex;
				} catch (URISyntaxException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (params.isParameterFalse(ClientPNames.ALLOW_CIRCULAR_REDIRECTS))
		{

			RedirectLocations redirectLocations = (RedirectLocations) context
					.getAttribute(REDIRECT_LOCATIONS);

			if (redirectLocations == null)
			{
				redirectLocations = new RedirectLocations();
				context.setAttribute(REDIRECT_LOCATIONS, redirectLocations);
			}

			URI redirectURI = null;
			if (uri.getFragment() != null)
			{
				try
				{
					HttpHost target = new HttpHost(uri.getHost(),
							uri.getPort(), uri.getScheme());
					redirectURI = URIUtils.rewriteURI(uri, target, true);
				} catch (URISyntaxException ex)
				{

				}
			} else
			{
				redirectURI = uri;
			}

			if (redirectLocations.contains(redirectURI))
			{
				try
				{
					throw new CircularRedirectException(
							"Circular redirect to '" + redirectURI + "'");
				} catch (CircularRedirectException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else
			{
				redirectLocations.add(redirectURI);
			}
		}

		return uri;
	}
}