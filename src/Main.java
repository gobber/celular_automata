import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

import model.sin.SIN;
import model.sir.SIR;


public class Main {
	
	
	private static int MODEL_SIR = 1;
	
	private static int MODEL_SIN = 2;
	
	
	private static int modelChoosed = MODEL_SIR; 	
	
		
	public static void main( String args [] ) {				
		
		mmlib4j.utils.Utils.debug = false;
				
		
		/* Create the window */
		
		JFrame view = new JFrame( "Modelo de propagação de doenças" );
		view.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		view.setSize( 430, 300 );

		
		/* Create the panel to the objects */
				
		final JPanel panel = new JPanel();
		panel.setBorder( BorderFactory.createEmptyBorder( 7, 10, 7, 7 ) );
		panel.setLayout( new GridLayout( 10, 2 ) );
				
		
		final JTextField textFieldInfPopulation = new JTextField( "0.005", 20 ); // Population infected
		final JTextField textFieldSize = new JTextField( "200", 20 ); // Size of reticulated
		final JTextField textFieldIterations = new JTextField( "100", 20 ); // Iterations
		final JTextField textFieldC = new JTextField( "8", 20 ); // Parameter C
		final JTextField textFieldR = new JTextField( "1", 20 ); // Parameter R
		final JTextField textFieldPc = new JTextField( "0.6", 20 ); // Parameter PC
		final JTextField textFieldPd = new JTextField( "0.3", 20 ); // Parameter PD
		final JTextField textFieldPn = new JTextField( "0.1", 20 ); // Parameter PN
		
		
		textFieldInfPopulation.setHorizontalAlignment( SwingConstants.RIGHT );
		textFieldSize.setHorizontalAlignment( SwingConstants.RIGHT );
		textFieldIterations.setHorizontalAlignment( SwingConstants.RIGHT );
		textFieldC.setHorizontalAlignment( SwingConstants.RIGHT );
		textFieldR.setHorizontalAlignment( SwingConstants.RIGHT );
		textFieldPc.setHorizontalAlignment( SwingConstants.RIGHT );
		textFieldPd.setHorizontalAlignment( SwingConstants.RIGHT );
		textFieldPn.setHorizontalAlignment( SwingConstants.RIGHT );				
		
		/* Radio Buttons */
		
		
		JRadioButton radioButtonSIR = new JRadioButton();
		JRadioButton radioButtonSIN = new JRadioButton();
		
		radioButtonSIR.setText( "Modelo SIR" );
		radioButtonSIN.setText( "Modelo SIN" );
		radioButtonSIR.setBorder( BorderFactory.createEmptyBorder( 5, 0, 5, 0 ) );
		radioButtonSIN.setBorder( BorderFactory.createEmptyBorder( 5, 0, 7, 0 ) );
		
		panel.add( new JLabel( "Pop. Infectada:" ) );
		panel.add( textFieldInfPopulation );
		
		
		panel.add( new JLabel( "Tamanho do Reticulado:" ) );
		panel.add( textFieldSize );
		
		
		panel.add( new JLabel( "Iterações:" ) );
		panel.add( textFieldIterations );
		
		
		panel.add( new JLabel( "Vizinhança (C):" ) );
		panel.add( textFieldC );
		
		
		panel.add( new JLabel( "Raio (R):" ) );
		panel.add( textFieldR );
		
		
		panel.add( new JLabel( "Probabilidade (Pc):" ) );
		panel.add( textFieldPc );
		
		
		panel.add( new JLabel( "Probabilidade (Pd):" ) );
		panel.add( textFieldPd );
		
		
		final JLabel jlabelPn = new JLabel( "Probabilidade (Pn):" );
		panel.add( jlabelPn );
		panel.add( textFieldPn );
			
		radioButtonSIR.setSelected( true );

		
		radioButtonSIR.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				modelChoosed = MODEL_SIR;
											
				panel.setLayout( new GridLayout( 10, 2 ) );		
				
				panel.add( jlabelPn, 14 );
				
				panel.add( textFieldPn, 15 );
				
				panel.validate();

			}
		});
		
		
		radioButtonSIN.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {						
				
				modelChoosed = MODEL_SIN;		
				
				panel.remove( jlabelPn );
				
				panel.remove( textFieldPn );
				
				panel.setLayout( new GridLayout( 9, 2 ) );		
				
				panel.validate();
				
			}
			
		});		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add( radioButtonSIR );
		bg.add( radioButtonSIN );
		
		
		panel.add( radioButtonSIR );
		
		panel.add( radioButtonSIN );
		
		
		/* Button */
		
		panel.add( new JLabel() );
		
		
		JButton buttonCalculate = new JButton();
		
		buttonCalculate.setText( "Calcular" );
		
		
		buttonCalculate.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( modelChoosed == MODEL_SIR ) {
					
					try {
						SIR.run( Float.parseFloat( textFieldInfPopulation.getText() ),
								 Integer.parseInt( textFieldSize.getText() ),
								 Integer.parseInt( textFieldC.getText() ),
								 Integer.parseInt( textFieldR.getText() ),
								 Integer.parseInt( textFieldIterations.getText() ),
								 Float.parseFloat( textFieldPc.getText() ),
								 Float.parseFloat( textFieldPd.getText() ),
								 Float.parseFloat( textFieldPn.getText() ) );
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} else {
					
					try {
						SIN.run( Float.parseFloat( textFieldInfPopulation.getText() ),
								 Integer.parseInt( textFieldSize.getText() ),
								 Integer.parseInt( textFieldC.getText() ),
								 Integer.parseInt( textFieldR.getText() ),
								 Integer.parseInt( textFieldIterations.getText() ),
								 Float.parseFloat( textFieldPc.getText() ),
								 Float.parseFloat( textFieldPd.getText() ));
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		} );
				
		
		panel.add( buttonCalculate );
						
		/* Show window */
		
		
		view.add( panel );
		view.setResizable( false );
		view.setLocationRelativeTo( null );	
		view.setVisible( true );
	}

}
