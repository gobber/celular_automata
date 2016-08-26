package model.sin;

import utils.Utils;
import network.Network;
import mmlib4j.images.GrayScaleImage;
import model.States;

public class I implements States{

	@Override
	public void tradeStates(int x, int y, int size, int iteration, GrayScaleImage states,
			GrayScaleImage statesCopy, Network network) {
		
		
		Report.getInstance().statesReport.db[ iteration ]++;
		
		double paux1 = Utils.random.nextDouble();
		
		
		statesCopy.setPixel( x , y, StatesImplementation.I );
		
		
		if( paux1 < Probability.PC ) {
			
			statesCopy.setPixel( x, y, StatesImplementation.N );
			
			Report.getInstance().sin[ iteration ]++;
			
		} 
 		
		
	}

}
