/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa_encyption;

import java.io.IOException;
import java.math.BigInteger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author hagarmohamad75
 */
public class RSA_Encyption extends Application {
  
      RSA rsa=new RSA();
    String Message;
      byte[] encrypted;
    @Override
    public void start(Stage primaryStage) {
        Button btn1 = new Button();
        TextField txt1=new  TextField();
         Button btn2 = new Button();
        TextField txt3=new  TextField();
          Text txt2=new Text("");
           Text txt4=new Text("");
             
        btn1.setText("Encrypt!");
        btn2.setText("Decrypt!");
        HBox hbox = new HBox(5);
         HBox hbox2=new HBox(5);// 5 is the spacing between elements in the VBox
         hbox.getChildren().addAll(txt1, btn1);
         hbox2.getChildren().addAll(txt3, btn2);
          VBox vbox=new VBox(5);
           vbox.getChildren().addAll(hbox, txt2,hbox2,txt4);
           btn1.setOnAction(new EventHandler<ActionEvent>() {
               
           
            @Override
            public void handle(ActionEvent event) {
               try{
                Message=txt1.getText().toString();
                encrypted = encrypt(Message.getBytes());
                 txt3.setText(new String(encrypted.toString()));
                 txt3.setEditable(false);
                System.out.println(bytesToString(encrypted));
            }
            catch(Exception e){
                txt1.setPromptText("Please Enter A message!");
            }}
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
               
           
            @Override
            public void handle(ActionEvent event) {
               
                Message=txt1.getText().toString();
                byte[] decrypted =decrypt(encrypted);
                txt4.setText(new String(decrypted));
                
            }
        });
        StackPane root = new StackPane();
       // root.getChildren().add(btn);
       // root.getChildren().add(txt);
           root.getChildren().add(vbox);
           root.setAlignment(vbox, Pos.CENTER);
             
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("RSA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
     launch(args);
    

}
 //   Chinese Remainder Theorem to calculate m^key(mod pq)
    // decryption key is d
    //encryption  key is e
    
    public static BigInteger crt(BigInteger key, BigInteger p, BigInteger q, BigInteger m){
  BigInteger dp, dq, qInverse, m1, m2, h;

   dp = key.mod(p.subtract(BigInteger.ONE));
   dq = key.mod(q.subtract(BigInteger.ONE));
   qInverse =q.modInverse(p);
   
   m1 = m.modPow(dp,p);
   m2 = m.modPow(dq,q);
   h = qInverse.multiply(m1.subtract(m2)).mod(p);
   m = m2.add(h.multiply(q));

   return m;
}
 private static String bytesToString(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b);
        }
        return test;
    }
 
    // Encrypt message
    public byte[] encrypt(byte[] message)
    {
        BigInteger mm=new BigInteger(message);
        return crt(RSA.e,RSA.p,RSA.q,mm).toByteArray();
        
    }
 
    // Decrypt message
    public byte[] decrypt(byte[] message)
    {  BigInteger mm=new BigInteger(message);
         return crt(RSA.d,RSA.p,RSA.q,mm).toByteArray();
         
    }
    public int SquareAndMultiplyCalc(int baseElement,int exponent,int modulus) {
		byte[] expBinaryArray=Integer.toBinaryString(exponent).getBytes();
		int result=baseElement;
		for(int i=1;i<expBinaryArray.length;++i) {
			result=(result*result)%modulus;
			if(expBinaryArray[i]=='1') {
				result=(result*baseElement)%modulus;
			}
		}
		return result;
    }}
	
	
   

