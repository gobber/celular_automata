package model.sir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import utils.StatesReport;

public class Report {
	
	
	/* Transições */
	
	public int ssi[];
	
	public int sir[];
	
	public int sis[];
	
	public int srs[];
	
	
	/* Estados */
	
	public StatesReport statesReport;
	
	
	/* Coeficientes */
	
	public float a[];
	
	public float b[];
	
	public float c[];
	
	public float e[];
	
	public float r0[];
	
	
	FileWriter writer;
	
	
	private static Report instance;
	
	
	public static Report createInstance(){
		
		instance = new Report();
		
		return instance;
		
	}
	
	
	public static Report getInstance(){
		
		if( instance == null ) {
			
			instance = new Report();
			
		}
		
		return instance;
		
	}
	
	
	public void update( int iteration ) throws IOException {
		
		if( writer == null ) {
			
			writer = new FileWriter( new File("reports/sir.csv") );
			writer.append( "S;I;R;S->I;I->R;I->S;R->S;a;b;c;e;R0\n" );
			
		}
		

		writer.append( Float.toString( statesReport.da[ iteration ] ) );
		
		writer.append( ";" );
		
		writer.append( Float.toString( statesReport.db[ iteration ] ) );
		
		writer.append( ";" );
		
		writer.append( Float.toString( statesReport.dc[ iteration ] ) );
		
		writer.append( ";" );
		
		writer.append( Integer.toString( ssi[ iteration ] ) );
		
		writer.append( ";" );
		
		writer.append( Integer.toString( sir[ iteration ] ) );
		
		writer.append( ";" );
		
		writer.append( Integer.toString( sis[ iteration ] ) );
		
		writer.append( ";" );
		
		writer.append( Integer.toString( srs[ iteration ] ) );
		
		writer.append( ";" );
		
		writer.append( Float.toString( a[ iteration ] ) );
		
		writer.append( ";" );
		
		writer.append( Float.toString( b[ iteration ] ) );
		
		writer.append( ";" );
		
		writer.append( Float.toString( c[ iteration ] ) );
		
		writer.append( ";" );
		
		writer.append( Float.toString( e[ iteration ] ) );
		
		writer.append( ";" );
		
		writer.append( Float.toString( r0[ iteration ] ) );
		
		writer.append( "\n" );
		
	}
	
	public void allocate( int iterations ) {
		
		ssi = new int[ iterations ];
		sir = new int[ iterations ];
		sis = new int[ iterations ];
		srs = new int[ iterations ];
		
		statesReport = new StatesReport( iterations ); 
		
		a = new float[ iterations ];
		b = new float[ iterations ];
		c = new float[ iterations ];
		e = new float[ iterations ];
		r0 = new float[ iterations ];
	
	}
	
	public int getSsi( int i ) {
		return ssi[ i ];
	}


	public void setSsi( int i, int v ) {
		this.ssi[ i ] = v;
	}


	public int getSir( int i ) {
		return sir[ i ];
	}


	public void setSir( int i, int v ) {
		this.sir[ i ] = v;
	}


	public int getSis( int i ) {
		return sis[ i ];
	}


	public void setSis( int i, int v ) {
		this.sis[ i ] = v;
	}


	public int getSrs( int i ) {
		return srs[ i ];
	}


	public void setSrs( int i, int v ) {
		this.srs[ i ] = v;
	}


	public float getDs( int i ) {
		return statesReport.da[ i ];
	}


	public void setDs( int i, float v ) {
		statesReport.da[ i ] = v;
	}


	public float getDi( int i ) {
		return statesReport.db[ i ];
	}


	public void setDi( int i, float v ) {
		statesReport.db[ i ] = v;
	}


	public float getDr( int i ) {
		return statesReport.dc[ i ];
	}


	public void setDr( int i, float v ) {
		statesReport.dc[ i ] = v;
	}


	public FileWriter getWriter() {
		return writer;
	}


	public void setWriter( FileWriter writer ) {
		this.writer = writer;
	}


	public void save() throws IOException {
		
		writer.close();
		
	}

}
