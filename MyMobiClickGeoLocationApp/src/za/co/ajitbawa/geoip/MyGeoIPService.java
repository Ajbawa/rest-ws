package za.co.ajitbawa.geoip;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.maxmind.geoip.Country;

@Path("/myGeoIpService") 
public class MyGeoIPService {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String geoLocateIpList(
					@QueryParam("ip-list") final List<String> ipList ) {
		
		try {
		StringBuffer geoLocationResults = new StringBuffer();
		if(!ipList.isEmpty()) {
			Iterator<String> ipIterator = ipList.iterator();
			String resultSeparator = " | ";
				while (ipIterator.hasNext()) {
					String ip = ipIterator.next();
					Country countryForIP = lookupCountryForIp(ip);

					String locationInfoForIp = "IP Address: " + ip
							+ " is from Country: " + countryForIP.getName()
							+ " code: " + countryForIP.getCode() + " .";
					geoLocationResults.append(locationInfoForIp);
					geoLocationResults.append(resultSeparator);
				}
		}
		return geoLocationResults.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "myGeoIpService has experienced and error.";
	}
	
	public Country lookupCountryForIp(String ipAddress) {
		
		return CountryLookupServiceFactory.getCountryLookupService().getCountry(ipAddress);
	}

}
