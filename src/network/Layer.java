package network;

public class Layer {
	
	
	private int nodes;
	
	public int getNodes() {
		return nodes;
	}


	public void setNode(int nodes) {
		this.nodes = nodes;
	}


	public int getSumNodes() {
		return sumNodes;
	}


	public void setSumNodes(int sumNodes) {
		this.sumNodes = sumNodes;
	}


	public float getConexProbability() {
		return conexProbability;
	}


	public void setConexProbability(float conexProbability) {
		this.conexProbability = conexProbability;
	}


	private int sumNodes;
	
	private float conexProbability;
	
	
	Layer( int nodes, int sumNodes, float conexProbability ) {
		
		this.nodes = nodes;
		
		this.sumNodes = sumNodes;
		
		this.conexProbability = conexProbability;
		
	}
	
	
}
