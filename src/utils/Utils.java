package utils;

import java.util.Random;


public class Utils {
	
	public static Random random = new Random();
	
	public static void normalizeByMaxMin( StatesReport statesReport ) {
		
		
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, size = statesReport.db.length;
		
		
		for( int cont = 0 ; cont < size ; cont++ ) {
			
			if( statesReport.db[ cont ] < min ) {
				
				min = ( int ) statesReport.db[ cont ];
				
			}
			
			if( statesReport.da[ cont ] < min ) {
				
				min = ( int ) statesReport.da[ cont ];
				
			}
			
			if ( statesReport.dc[ cont ] < min ) {
				
				min = ( int ) statesReport.dc[ cont ];
				
			}
			
			if( statesReport.db[ cont ] > max ) {
				
				max = ( int ) statesReport.db[ cont ];
				
			}
			
			if( statesReport.da[ cont ] > max ) {
				
				max = ( int ) statesReport.da[ cont ];
				
			}
			
			if ( statesReport.dc[ cont ] > max ) {
				
				max = ( int ) statesReport.dc[ cont ];
				
			}
			
		}
		
		for( int cont = 0 ; cont < size ; cont++ ) {
			
			statesReport.db[ cont ] = ( statesReport.db[ cont ] - min ) / ( float ) ( max - min );
			
			statesReport.da[ cont ] = ( statesReport.da[ cont ] - min ) / ( float ) ( max - min );
			
			statesReport.dc[ cont ] = ( statesReport.dc[ cont ] - min ) / ( float ) ( max - min );
			
		}
		
		
	}

}
