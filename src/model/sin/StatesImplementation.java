package model.sin;

import network.Network;
import mmlib4j.images.GrayScaleImage;
import model.States;

public class StatesImplementation {
	
	private States state;
	
	
	public static int S = 0;
	
	public static int I = 1;
	
	public static int N = 2;
	
	
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
		
		r.b[ it ] = ( float ) r.sin[ it ] / ( float ) r.statesReport.db[ it ];
		
		r.c[ it ] = ( float ) r.ssn[ it ] / ( float ) r.statesReport.da[ it ]; 
		
		r.e[ it ] = 0; //( float ) r.sns[ it ] / ( float ) r.statesReport.dc[ it ] ;
		
		r.r0[ it ] = 0; //r.a[ it ] * size * size / r.b[ it ]; 
		
	}

}
