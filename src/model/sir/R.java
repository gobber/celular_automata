package model.sir;

import utils.Utils;
import network.Network;
import mmlib4j.images.GrayScaleImage;
import model.States;

public class R implements States {

	@Override
	public void tradeStates(int x, int y, int size, int iteration, GrayScaleImage states,
			GrayScaleImage statesCopy, Network network) {
		
		Report.getInstance().statesReport.dc[ iteration ]++;
		
		double paux = Utils.random.nextDouble();
		
		if( paux < Probability.PN ) {
			
			statesCopy.setPixel( x , y, StatesImplementation.S );
			
			Report.getInstance().srs[ iteration ]++;
			
			
		} else {
			
			statesCopy.setPixel( x , y, StatesImplementation.R );
			
		}
		
		
	}

}
