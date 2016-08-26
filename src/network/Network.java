package network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmlib4j.images.ColorImage;
import mmlib4j.images.impl.ImageFactory;

import datastructure.HashMapCustom;

import utils.Point;


public class Network {
	
	/* 
	 * 
	 * c = quantidade de vizinhos do individuo 
	 * 
	 * r = raio que indica a distância máxima para vizinhos do nó
	 * 
	 * layers  = lista com os nós, somatório dos nós e as probabilidades calculadas 
	 * 
	 * positionsLayer = mapa que contém todas as posições possíveis calculadas a partir do raio r
	 * 
	 * 
	 * */
	
	private int c;
	
	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}

	public HashMapCustom<Integer, Point> getPositionsLayer() {
		return positionsLayer;
	}

	public void setPositionsLayer(HashMapCustom<Integer, Point> positionsLayer) {
		this.positionsLayer = positionsLayer;
	}

	private int r;
	
	private List<Layer> layers;
	
	private HashMapCustom<Integer, Point> positionsLayer;


	public Network( int c, int r ) {
		
		this.c = c;
		this.r = r;
	
	}
	
	public void run() {
		
		
		layers = new ArrayList<>( r );
		
		positionsLayer = new HashMapCustom<>();
		
		layers.add( new Layer( c , 0, 0 ) );
		
		
		int sum = 1;
		
		for( int ray = 1 ; ray < r ; ray++ ){
			
			sum += ( ray + 1 );
			
		}
	
		
		for( int ray = 1 ; ray < r ; ray++ ) {
			
			Layer l = layers.get( ray - 1 );
			
			layers.add( new Layer( c * ( ray + 1 ), 
								   ( ray - 1 ) * c + l.getSumNodes(),
								   ( float )( ( float ) ( r + 1 - ray ) / ( float ) ( r * r + r  - sum ) ) + l.getConexProbability() ) );
			
		}
		
		
		int cont=0;
		
		
		for( int layer = 1 ; layer <= r ; layer++ ) {
			
			for( int x = -layer ; x <= layer ; x++ ) {
				
				for( int y = -layer ; y <= layer ; y++ ) {
					
					if( ( x == layer || x == -layer || y == layer || y == -layer ) && layer !=0 ) {
						
						positionsLayer.put( cont++, new Point( x, y ) );
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
