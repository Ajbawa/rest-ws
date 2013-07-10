/**
 * 
 */
package za.co.ajitbawa.geoip;

import java.io.IOException;
import java.net.URL;

import com.maxmind.geoip.LookupService;

/**
 * @author Ajit
 *
 */

public class CountryLookupServiceFactory {

	private static LookupService COUNTRY_LOOKUP_SERVICE = null;
	
	public static LookupService getCountryLookupService() {
		
		if(COUNTRY_LOOKUP_SERVICE == null) {
			setupCountryLookupService();
		}
		
		return COUNTRY_LOOKUP_SERVICE; 

	}

	private static void setupCountryLookupService() {
		try {
			String dbfile = "/GeoIPLite.dat";
			URL urlToDB = CountryLookupServiceFactory.class.getClassLoader().getResource(dbfile);
			System.out.println("Found dbfile : " + urlToDB.getPath());
			
			COUNTRY_LOOKUP_SERVICE = new LookupService(urlToDB.getPath(), LookupService.GEOIP_MEMORY_CACHE);
			
		} catch (IOException ioe) {
			System.out.println("IO Exception occurred: " + ioe.getMessage());
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}

	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		COUNTRY_LOOKUP_SERVICE.close();
	}
	
}
