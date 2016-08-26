package model;

import network.Network;
import mmlib4j.images.GrayScaleImage;

public interface States {

	public void tradeStates( int x, int y, int size, int iteration, GrayScaleImage states, GrayScaleImage statesCopy, Network network );
	
}
