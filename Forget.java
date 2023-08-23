

import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Forget extends JFrame{


    private JTextField mobileforget;
    private JButton getOTPButton;
    private JTextField idforget;
    private JTextField newpassword;
    private JTextField confirmpassword;
    private JButton savePasswordButton;
    private JLabel l_newpassword;
    private JLabel l_confirmpassword;
    private JPanel updatepanel;
    private JTextField OTP;
    private JButton validateOTPButton;
    private JLabel l_otp;
    private JLabel l_idfor;
    private JLabel l_mobfor;
    private JButton exitButton;
    private JLabel note;
    private JLabel note1;
    private JLabel note2;
    private JLabel note3;
    private JLabel note4;
    private JLabel note5;
    private JButton loginPageButton;
    private JLabel note6;
    public static JFrame frame;
    private JButton button;

    PreparedStatement pst;
    Login login = new Login();
     static int n=0;
    static void Scamwindow() {
        if(n==0) {
            frame = new JFrame("Employee Management System");
            frame.setContentPane(new Forget().updatepanel);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        }
        if (n != 0) {
            n=0;
            frame.dispose();
        }
    }

       void newwindow () {
           Scamwindow();
       }

    public Forget() {


        newpassword.setVisible(false);
        confirmpassword.setVisible(false);
        savePasswordButton.setVisible(false);
        l_newpassword.setVisible(false);
        l_confirmpassword.setVisible(false);
        l_otp.setVisible(false);
        OTP.setVisible(false);
        validateOTPButton.setVisible(false);
        note.setVisible(false);
        note1.setVisible(false);
        note2.setVisible(false);
        note3.setVisible(false);
        note4.setVisible(false);
        note5.setVisible(false);
        note6.setVisible(false);

        loginPageButton.setMnemonic(KeyEvent.VK_L);
        exitButton.setMnemonic(KeyEvent.VK_E);
        validateOTPButton.setMnemonic(KeyEvent.VK_V);
        savePasswordButton.setMnemonic(KeyEvent.VK_S);
        getOTPButton.setMnemonic(KeyEvent.VK_G);

        loginPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int ans=  JOptionPane.showConfirmDialog(null,"Do you want to go login page","Warning",JOptionPane.YES_NO_OPTION,2);
                if(ans==0) {
                    n=1;
                    login.newwindow();
                    newwindow();
                }
            }
        });


        getOTPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isvalid = true;
                if (idforget.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "ID field is empty.Please enter your valid id");
                    isvalid = false;
                }
                if (mobileforget.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Mobile number field is empty.Please enter your mobile number");
                    isvalid = false;
                }
                if (isvalid) {
                    try {
                        int id = Integer.parseInt(idforget.getText());
                    } catch (Exception ex) {
                        isvalid = false;
                        JOptionPane.showMessageDialog(null, "Entered id is invalid");
                    }
                    try {
                        Long mobile = Long.valueOf(mobileforget.getText());
                    } catch (Exception ex) {
                        isvalid = false;
                        JOptionPane.showMessageDialog(null, "Entered mobile number is invalid");
                    }

                }
                if (isvalid) {
                    try {
                        pst = login.con.prepareStatement("select id,name,age,salary,position,mobile from employee where id=?");
                        pst.setString(1, idforget.getText());
                        ResultSet rs = pst.executeQuery();
                        if (rs.next()) {
                            Long mobiletable = rs.getLong(6);
                            Long mobiletext = Long.valueOf(mobileforget.getText());
                            if (mobiletable.equals(mobiletext)) {
                                JOptionPane.showMessageDialog(null, "OTP has been sent to your mobile");
                               // JOptionPane.showMessageDialog(null, "OTP is 2305");
                                l_otp.setVisible(true);
                                OTP.setVisible(true);
                                validateOTPButton.setVisible(true);
                                mobileforget.setText("");
                                idforget.setVisible(false);
                                mobileforget.setVisible(false);
                                getOTPButton.setVisible(false);
                                l_idfor.setVisible(false);
                                l_mobfor.setVisible(false);
                                OTP.requestFocus();
                            } else {
                                JOptionPane.showMessageDialog(null, "Entered mobile no is incorrect");
                                mobileforget.setText("");
                                mobileforget.requestFocus();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Entered ID is invalid");
                            idforget.setText("");
                            idforget.requestFocus();
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });



        savePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isvalid=true;
                int caps=0,small=0,digit=0,special=0;
                String newpasswordstr=newpassword.getText();
                char[]ch=newpasswordstr.toCharArray();
                if(newpassword.getText().isBlank()){
                    JOptionPane.showMessageDialog(null,"New password field is empty","warning",0);
                    isvalid=false;
                }
                if(isvalid&&ch.length<8){
                    JOptionPane.showMessageDialog(null,"Enter password length minimum 8 characters");
                    isvalid=false;
                }
                for (int i = 0; i < ch.length && isvalid; i++) {
                    if(Character.isUpperCase(ch[i])){
                        caps++;
                    }
                    else if(Character.isLowerCase(ch[i])){
                        small++;
                    }
                    else if(Character.isDigit(ch[i])){
                        digit++;
                    }
                    else if(ch[i]==' '){
                        JOptionPane.showMessageDialog(null,"Space is not allowed");
                        isvalid=false;
                        break;
                    }
                    else{
                        special++;
                    }
                }
                if( caps<=0 &&isvalid ){
                    isvalid=false;
                    JOptionPane.showMessageDialog(null,"Enter minimum one capital letter");
                }
                if( small<=0 &&isvalid ){
                    isvalid=false;
                    JOptionPane.showMessageDialog(null,"Enter minimum one small letter");
                }
                if( digit<=0 &&isvalid ){
                    isvalid=false;
                    JOptionPane.showMessageDialog(null,"Enter minimum one numeric");
                }
                if( special<=0 &&isvalid ){
                    isvalid=false;
                    JOptionPane.showMessageDialog(null,"Enter minimum one special character");
                }
                if(isvalid&&confirmpassword.getText().isBlank()){
                    JOptionPane.showMessageDialog(null,"confirm password field is empty","warning",2);
                    isvalid=false;
                }
                if(isvalid){
                    if(newpassword.getText().equals(confirmpassword.getText())){
                        try {
                            pst= login.con.prepareStatement("update employee set password=? where id=?");
                            pst.setString(1,newpassword.getText());
                            pst.setString(2, idforget.getText());
                            if(pst.executeUpdate()!=0) {
                                JOptionPane.showMessageDialog(null, "Password updated Successfully");
                                n=1;
                                newwindow();
                                login.newwindow();
                            }
                            else{
                                newpassword.setText("");
                                confirmpassword.setText("");
                                JOptionPane.showMessageDialog(null,"please re-enter the password");
                            }
                        }
                        catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Password doesn't match");
                    }
                }
            }
        });



        validateOTPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isvalid=true;
                if(OTP.getText().length()!=4||OTP.getText().isBlank()){
                    isvalid=false;
                    JOptionPane.showMessageDialog(null,"Enter Four digit OTP");
                }
              if(isvalid) {
                  try {
                      int otp = Integer.parseInt(OTP.getText());
                  } catch (Exception ex) {
                      isvalid = false;
                      JOptionPane.showMessageDialog(null, "OTP is invalid");
                  }
              }
                if(isvalid){
                    JOptionPane.showMessageDialog(null,"OTP is successfully validated");
                    note.setVisible(true);
                    note1.setVisible(true);
                    note2.setVisible(true);
                    note3.setVisible(true);
                    note4.setVisible(true);
                    note5.setVisible(true);
                    note6.setVisible(true);
                    newpassword.setVisible(true);
                    confirmpassword.setVisible(true);
                    savePasswordButton.setVisible(true);
                    l_newpassword.setVisible(true);
                    l_confirmpassword.setVisible(true);
                    l_otp.setVisible(false);
                    OTP.setVisible(false);
                    validateOTPButton.setVisible(false);
                    OTP.setText("");
                    newpassword.requestFocus();
                }
            }
        });



        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans=JOptionPane.showConfirmDialog(null,"Do you want to exit application ?","Warning",2);
                if(ans==0){
                    System.exit(0);
                }
            }
        });
    }
}

