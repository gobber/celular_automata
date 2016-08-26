package model.sir;

import network.Network;
import mmlib4j.images.GrayScaleImage;
import model.States;

public class StatesImplementation {
	
	private States state;
	
	
	public static int S = 0;
	
	public static int I = 1;
	
	public static int R = 2;
	
	
	/*  */
	
	
	public StatesImplementation( States state ) {
		
		this.state = state;
		
	}
	
	public StatesImplementation tradeStates( int x, int y, int size, int iteration, GrayScaleImage states, GrayScaleImage statesCopy, Network network ) {
		
		state.tradeStates( x, y, size, iteration, states, statesCopy, network );
		
		return this;
		
	}
	
	public void calculateCoeficients( int it, int size ) {
		
		Report r = Report.getInstance(); 
		
		r.a[ it ] = ( float ) r.ssi[ it ] / ( r.statesReport.db[ it ] * r.statesReport.da[ it ] );
		
		r.b[ it ] = ( float ) r.sir[ it ] / ( float ) r.statesReport.db[ it ];
		
		r.c[ it ] = ( 1 - r.b[ it ] ) * ( ( float ) r.sis[ it ] / ( float ) r.statesReport.db[ it ] ); 
		
		r.e[ it ] = ( float ) r.srs[ it ] / ( float ) r.statesReport.dc[ it ] ;
		
		r.r0[ it ] = r.a[ it ] * size * size / ( r.b[ it ] + r.c[ it ] ); 
		
	}

}
