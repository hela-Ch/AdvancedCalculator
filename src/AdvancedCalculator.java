import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;




public class AdvancedCalculator extends JFrame{
	 
    private String [] buttonname = {"7","8","9","/","√","x²","sin","cos","4","5","6","*","%","x^y","log","tan",
    		                            "1","2","3","-","1/x","n!","EXP","π","+/-","0",".","+","=","DEL","AC","OFF"};
    JButton []buttons = new JButton[buttonname.length];
	
	private JTextArea text=new JTextArea("0");
	private JPanel j = new JPanel() ;
	private double chiffre1;
	private boolean update =false;
	private boolean clicOperator = false ;
	private String operateur = "";
	public AdvancedCalculator () {
		this.setTitle("Calculator");
		this.setSize(600,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(Color.GRAY);
		
         init();
		this.setVisible(true);
		}
	 void init() {
		 j.setLayout(new GridLayout(4,8,0,0));
			
		   for(int i=0 ; i< buttonname.length;++i) {
			   buttons[i]= new JButton(buttonname[i]);
			   j.add(buttons[i]);
		   }
			this.setLayout(null);
			//settings textField
			this.add(text);
			text.setFont(new Font("cal",Font.BOLD,20));
			text.setBounds(10, 10,580, 140);
			//settings 3 buttons on the top
			
			this.add(j);
			j.setBounds(0, 150, 600, 330);
			
			for(int i=0;i<buttons.length;++i) {
				String command =buttons[i].getActionCommand();
				try {
					int number =Integer.parseInt(command);
					buttons[i].addActionListener(new NumberListener());
					buttons[i].setForeground(Color.blue);
					buttons[i].setFont(new Font("number",Font.CENTER_BASELINE,20));
				} catch(Exception e) {
					
				   if(command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/" )
						   ||command.equals("=") ) {
						buttons[i].addActionListener(new OperatorListener());
						buttons[i].setForeground(Color.ORANGE);
						buttons[i].setFont(new Font("number",Font.BOLD,20));
					}else { 
						switch(command) {
						case "x^y" : buttons[i].addActionListener(new OperatorListener());
							break;
						   case "." : buttons[i].addActionListener(ae -> {
								if(text.getText().indexOf('.')== -1) {
									text.setText(text.getText()+".");
								}
							});
						   buttons[i].setForeground(Color.blue);
						   buttons[i].setFont(new Font("number",Font.CENTER_BASELINE,20));
						   break;
						   case "+/-" :buttons[i].addActionListener(ae -> {
							   text.setText(-Double.parseDouble(text.getText()) +"");
							   update = true;
						   });
						   buttons[i].setForeground(Color.ORANGE);
							buttons[i].setFont(new Font("number",Font.BOLD,20));
						   break;
							case "cos":	buttons[i].addActionListener(ae ->{
								 text.setText( Math.cos(Double.parseDouble(text.getText()))+"");
								 update = true;
							});
							break;
							
							case "sin":	buttons[i].addActionListener(ae ->{
								 text.setText( Math.sin(Double.parseDouble(text.getText()))+"");
								 update = true;
							});
							break;
							case "tan":	buttons[i].addActionListener(ae ->{
								 text.setText( Math.tan(Double.parseDouble(text.getText()))+"");
								 update = true;
							});
							break;
							case "log":	buttons[i].addActionListener(ae ->{
								try {
									double x = Math.log(Double.parseDouble(text.getText()));
									if (x>0) {
								 text.setText( x+"");
									}else{ text.setText("Error");
									}
									update = true;
								}catch(Exception exception) {};
							});
							break;
							case "EXP":	buttons[i].addActionListener(ae ->{
								 text.setText( Math.exp(Double.parseDouble(text.getText()))+"");
								 update = true;
							});
							break;
							case "√":	buttons[i].addActionListener(ae ->{
								 text.setText( Math.sqrt(Double.parseDouble(text.getText()))+"");
								 update = true;
							});
							break;
							case "x²":	buttons[i].addActionListener(ae ->{
								 text.setText( String.valueOf(Math.pow(Double.parseDouble(text.getText()),2)));
								 update = true;
							});
							break;
							case "1/x":	buttons[i].addActionListener(ae ->{
								if(Double.parseDouble(text.getText()) == 0) {
									text.setText("Error");
								}else {
								 text.setText(""+1/(Double.parseDouble(text.getText())));}
								
								update = true;
							});
							break;
							case "n!":	buttons[i].addActionListener(ae -> {
								try {
									int n =Integer. parseInt(text.getText());
									 text.setText(factorialUsingRecursion(n ) +"");	
									 
							}catch(Exception excep) { text.setText("Error");}
								update = true;
							});
							  break;
							case "%" :  buttons[i].addActionListener(ae ->{
								text.setText(Double.parseDouble(text.getText())/100+"");
								update = true;
							});
							 break;
						    case "π" : buttons[i].addActionListener(ae -> {
						    	text.setText(Math.PI +"");
						    	update =true;
						    });
						    break;
						    case"AC" : buttons[i].addActionListener(ae ->{
						    	  clicOperator = false;
							      update = true;
							      chiffre1 = 0;
							      operateur = "";
							      text.setText("0");
							   
						    });
						    buttons[i].setForeground(Color.RED);
						    break;
						    case "DEL" : buttons[i].addActionListener(ae -> {
						    	String s = text.getText();
								if(s.length() ==1) {
									 clicOperator = false;
								      update = true;
								      chiffre1 = 0;
								      operateur = "";
								      text.setText("0");
								}else {
								s =s.substring(0, s.length() - 1);
								text.setText(s);
								}
						    });
						    buttons[i].setForeground(Color.RED);
						    break;
						    case "OFF" : buttons[i].addActionListener(ae -> {
						    	System.exit(0);;
						    });
						    buttons[i].setForeground(Color.RED);
							 break;
						}}}}
			
	 }
	class NumberListener implements ActionListener {
	    public void actionPerformed(ActionEvent e){
	      String str = e.getActionCommand();
	      if(update){
	        update = false;
	      }
	      else{
	        if(!text.getText().equals("0"))
	          str = text.getText() + str;
	      }
	      text.setText(str);
	    }
	  }
	private void calcul(){
	    if(operateur.equals("+")){
	      chiffre1 = chiffre1 + 
	            Double.valueOf(text.getText()).doubleValue();
	      text.setText(String.valueOf(chiffre1));
	    }
	    if(operateur.equals("-")){
	      chiffre1 = chiffre1 - 
	            Double.valueOf(text.getText()).doubleValue();
	      text.setText(String.valueOf(chiffre1));
	    }          
	    if(operateur.equals("*")){
	      chiffre1 = chiffre1 * 
	            Double.valueOf(text.getText()).doubleValue();
	      text.setText(String.valueOf(chiffre1));
	    }     
	    if(operateur.equals("/")){
	      try{
	        chiffre1 = chiffre1 / 
	              Double.valueOf(text.getText()).doubleValue();
	        text.setText(String.valueOf(chiffre1));
	      } catch(ArithmeticException e) {
	        text.setText("Error");
	      }
	    }
	    if(operateur.equals("x^y")) {
	    	chiffre1 = Math.pow(Double.parseDouble(text.getText()),Double.parseDouble(text.getText()));
	    	text.setText( String.valueOf(chiffre1));
	    }
	}


	  class OperatorListener implements ActionListener {
	    public void actionPerformed(ActionEvent e){
	    	switch (e.getActionCommand()) {
	    	
	       case "+": 
	    		      if(clicOperator){
	                   calcul();
	                   text.setText(String.valueOf(chiffre1));
	      
	    		      }else{
	                   chiffre1 = Double.valueOf(text.getText()).doubleValue();
	                   clicOperator = true;
	                  }
	                  operateur = "+";
	                  update = true;
	       break;
   
	       case "-":
	                 if(clicOperator){
	                  calcul();
	                  text.setText(String.valueOf(chiffre1));
	                 }else{
	                  chiffre1 = Double.valueOf(text.getText()).doubleValue();
	                  clicOperator = true;
	                 }
	                 operateur = "-";
	                 update = true;
	  
            break;
	  
	        case "*":
	                 if(clicOperator){
	                  calcul();
	                  text.setText(String.valueOf(chiffre1));
	                 }else{
	                  chiffre1 = Double.valueOf(text.getText()).doubleValue();
	                  clicOperator = true;
	                 }
	                 operateur = "*";
	                 update = true;
	                 
	      break;

	       case "/":
	                 if(clicOperator){
	                  calcul();
	                  text.setText(String.valueOf(chiffre1));
	                 }else{
	                  chiffre1 = Double.valueOf(text.getText()).doubleValue();
	                  clicOperator = true;
	                 }
	                operateur = "/";
	                update = true;
	     break;  
	     
	       case "=" :
	    	        calcul();
	 	             update = true;
	 	             clicOperator = false;
	 	 break;
	       case "x^y" : 
	    	   if(clicOperator){
	                  calcul();
	                  text.setText(String.valueOf(chiffre1));
               }else{
            	   chiffre1 = Double.valueOf(text.getText()).doubleValue();
	                  clicOperator = true;
	                 }
	                 operateur = "x^y";
	                 update = true;
	    	   
				 
				
			
			break;
	      
	  }}}
private long factorialUsingRecursion(int n) {
if (n <= 1) {
return 1;
}
return n * factorialUsingRecursion(n - 1);}
	public static void main(String []args) {
		new AdvancedCalculator();
	}}
