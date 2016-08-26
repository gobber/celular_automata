package model.sir;

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
		
		double paux2 = Utils.random.nextDouble();
		
		statesCopy.setPixel( x , y, StatesImplementation.I );
		
		
		if( paux1 < Probability.PC ) {
			
			statesCopy.setPixel( x, y, StatesImplementation.R );
			
			Report.getInstance().sir[ iteration ]++;
			
			
		} else if( paux2 < Probability.PD ) {
			
			statesCopy.setPixel( x, y, StatesImplementation.S );
			
			Report.getInstance().sis[ iteration ]++;
			
		}
 		
		
	}

}
