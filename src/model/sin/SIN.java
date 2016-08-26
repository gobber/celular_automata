package model.sin;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import utils.Utils;
import network.Network;

import mmlib4j.gui.WindowImages;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.images.impl.ImageFactory;

public class SIN {
	
	
	/* População infectada *

	
	/* Tamanho do reticulado */
	
	
	/* Vizinhança e raio */
	
	
	public static void run ( float initInfected, int size, int c, int r, int iterations, float pc, float pd ) throws IOException {
		
		
		/* Probability */
		
		Probability.PC = pc;
		
		Probability.PD = pd;			
		
		
		/* Modelo */
		
		List<StatesImplementation> modelSIN = new ArrayList<>();
		
		modelSIN.add( new StatesImplementation( new S() ) );
		
		modelSIN.add( new StatesImplementation( new I() ) );
		
		modelSIN.add( new StatesImplementation( new N() ) );
	
		
		/* Imagem para representar o espaço de estados */
		
		GrayScaleImage states = ImageFactory.createGrayScaleImage( ImageFactory.DEPTH_8BITS, size, size );
		
		Network network = new Network( c, r );
		
		
		for( int p = 0 ; p < states.getSize() ; p++ ) {
			
			states.setPixel( p, StatesImplementation.S );
			
		}
		
		
		/* População infectada em 5% */
		
		int cont = 0;
		
		while( cont < states.getSize() * initInfected ) {
			
			int i = Utils.random.nextInt( size ), j = Utils.random.nextInt( size );
			
			if( states.getPixel( i, j ) == StatesImplementation.S ){
			
				states.setPixel( i, j, StatesImplementation.I );
		
				cont++;
				
			}
			
		}
		
		
		/* Alocando vetores do report */
		
		Report.createInstance().allocate( iterations );
			
		
		/* Cria uma nova rede */
		
		network.run();
		
		
		/* Copia do espaço de estados */
		
		GrayScaleImage statesCopy;
		
		statesCopy = states.duplicate();
		
		long init = 0, end = 0;
		
		
		for( int it = 0 ; it < iterations ; it ++ ) {
			
			
			init = System.currentTimeMillis();
			
			
			System.out.printf( "Percentage: %d%%\n", ( ( it+1 ) * 100 / iterations ) );
			
			
			for( int x = 0; x < size ; x++ ) {
				
				for( int y = 0 ; y < size ; y++ ) {
										
					modelSIN.get( states.getPixel( x, y  ) )
									.tradeStates( x, y, size, it, states, statesCopy, network )
									.calculateCoeficients( it, size );
					
				}
				
			}
			
			Report.getInstance().update( it );
			
			for( int pixel = 0 ; pixel < states.getSize() ; pixel ++ ) {
				
				states.setPixel( pixel, statesCopy.getPixel( pixel ) );
				
			}
		
			
		}
		
		end = System.currentTimeMillis();
		
		System.out.printf( "\nfinished in %fs", ( double ) ( end - init ) / 1000 );
		
		Report.getInstance().save();
		
		
		/* Normalize values */
		
		
		Utils.normalizeByMaxMin( Report.getInstance().statesReport );
		
		
		/* Plot data */
		
		XYSeries s1 = new XYSeries("s");
		XYSeries s2 = new XYSeries("i");
		XYSeries s3 = new XYSeries("n");
		
		cont = -1;
		
		while( ++cont < iterations ) {
			
			s1.add( cont, Report.getInstance().getDs( cont ) );
			s2.add( cont, Report.getInstance().getDi( cont ) );
			s3.add( cont, Report.getInstance().getDn( cont ) );
			
		}
		
		XYSeriesCollection data = new XYSeriesCollection();
		
		data.addSeries( s1 );
		data.addSeries( s2 );
		data.addSeries( s3 );
		
		JFreeChart graph = ChartFactory.createXYLineChart("Evolução temporal do sistema", 
														  "Iterações", 
														  "Concentrações Normalizadas", 
														  data,PlotOrientation.VERTICAL, true, true, false);
	
		
		graph.getPlot().setBackgroundPaint( Color.white );
		
		graph.setAntiAlias( true );
		
		
		RectangleInsets padding = new RectangleInsets( 10, 0, 10, 0 );
		
		graph.setPadding( padding );
		
		
		ChartFrame frame = new ChartFrame( "Resultados", graph );
		
		frame.pack();
		
		frame.setSize( 800, 600 );
		
		frame.setLocationRelativeTo( null );
		
		frame.setVisible( true );
		
		// WindowImages.show( states );
		
		// Stack imageJ
		
	}

}
