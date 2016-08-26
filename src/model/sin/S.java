package model.sin;

import java.util.List;

import utils.Point;
import utils.Utils;

import network.Layer;
import network.Network;
import mmlib4j.images.GrayScaleImage;
import model.States;

public class S implements States {
	
	
	private static final int K = 1;
	

	@Override
	public void tradeStates( int x, int y , int size, int iteration, GrayScaleImage states, GrayScaleImage statesCopy, Network network ) {
		
		
		Report.getInstance().statesReport.da[ iteration ]++;
		
	
		List<Layer> layers = network.getLayers();
		
		
		int neighborInfectedsum = 0;
		
		
		for( int c = 0 ; c < network.getC() ; c++  ) {
			
			
			double probability = Utils.random.nextDouble();
			
			int p = 0;
			
			
			for( p = 0 ; p < network.getR() ; p++ ) {
				
				if( probability <= layers.get( p ).getConexProbability() ) {
					
					break;
					
				}
				
			}
			
			
			p--;
			
			
			int node = ( Utils.random.nextInt( layers.get( p ).getNodes() ) ) + 1 + layers.get( p ).getSumNodes();
			
			Point point  = network.getPositionsLayer().getOrDefault( node, new Point( 0, 0 ) ); 
			
			
			int tempx = x + point.getX();
			
			int tempy = y + point.getY();
			
			
			if( x + point.getX() < 0 ) {
				
				tempx = size + x + point.getX();
				
			}
			
			if( y + point.getY() < 0 ) {
				
				tempy = size + y + point.getY();
				
			}
			
			if( x + point.getX() >= size ) {
				
				tempx = point.getX() + x - size;
				
			}
			
			if( y + point.getY() >= size ) {
				
				tempy = point.getY() + y - size;
				
			}
			
			if( states.getPixel( tempx, tempy ) == StatesImplementation.I ) neighborInfectedsum++;
			
		}
		
		
		double pi = 1 - Math.pow( Math.E, -neighborInfectedsum*K );
		
		double paux = Utils.random.nextDouble();
		
		double paux2 = Utils.random.nextDouble();
		
		
		if( paux < pi ) {
			
			statesCopy.setPixel( x, y , StatesImplementation.I );
			
			Report.getInstance().ssi[ iteration ]++;
			
		} else if( paux2 < Probability.PD ) {
			
			statesCopy.setPixel( x, y, StatesImplementation.N );
			
			Report.getInstance().ssn[ iteration ]++;
			
		} else {
			
			statesCopy.setPixel( x, y , StatesImplementation.S );
			
		}
	
		
	}

}
