package za.co.ajitbawa.test;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class MyGeoIPTestClient {


    public static void main(String[] args) {
    	
        List<String> list = new ArrayList<String>();
        list = Arrays.asList(new String[]{"151.38.39.114","64.81.104.131", "12.25.205.51","200.21.225.82"});

        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			params.add("ip-list", iterator.next());
		}

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource resource = client.resource(getBaseURI());
        String result =  resource.path("rest")
        						 .path("myGeoIpService")
        						 .queryParams(params)
        						 .type(MediaType.TEXT_PLAIN)
        						 .get(String.class);
        System.out.println("<Results>");
        System.out.println(result);
    }
    
    private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/MyMobiClickGeoLocationApp").build();
	}

}
