

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    public JTextField id;
    public JTextField password;
    private JButton exitbuttton;
    private JButton forgetPasswordButton;
    private JButton loginButton;
    public JPanel mainpanel;
    private JLabel idlabel;
    private JLabel namelabel;
    private JLabel agelabel;
    private JLabel salarylabel;
    private JLabel positionlabel;
    private JLabel mobilelabel;
    private JLabel passwordlabel;
    private JLabel idd;
    private JLabel namee;
    private JLabel agee;
    private JLabel salaryy;
    private JLabel positionn;
    private JLabel mobilee;
    private JLabel passwordd;
    private JLabel pass;
    private JLabel emp;
    private JButton exitButton;
    private JButton backToLoginButton;
    private JLabel welcome;
    private JLabel Employeewelcome;


  static int n =0 ;
  static JFrame frame;

   static void Scamwindow(){
        if(n==0) {
            frame = new JFrame("Employee Management System");
            frame.setContentPane(new Login().mainpanel);
            frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);
        }
        else{
            n=0;
            frame.dispose();
        }
    }

    void newwindow(){
       Scamwindow();
    }

    Connection con;
    PreparedStatement pst;


    void connection(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDB?useSSL=false", "root", "Ashok@123");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public Login() {
        loginButton.setMnemonic(KeyEvent.VK_L);
        exitbuttton.setMnemonic(KeyEvent.VK_E);
        forgetPasswordButton.setMnemonic(KeyEvent.VK_F);
        exitButton.setMnemonic(KeyEvent.VK_E);
        backToLoginButton.setMnemonic(KeyEvent.VK_L);
        idd.setVisible(false);
        namee.setVisible(false);
        agee.setVisible(false);
        passwordd.setVisible(false);
        positionn.setVisible(false);
        mobilee.setVisible(false);
        salaryy.setVisible(false);
        exitButton.setVisible(false);
        backToLoginButton.setVisible(false);
        Employeewelcome.setVisible(false);
        connection();


        forgetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Forget forget = new Forget();
                forget.newwindow();
                n=1;
                newwindow();
            }
        });



        exitbuttton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans=JOptionPane.showConfirmDialog(null,"Do you want to exit ?","Warning",JOptionPane.YES_NO_OPTION,0);
                if(ans==0){
                    System.exit(0);
                }
            }
        });



        loginButton.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                boolean isvalid = true;
                if(id.getText().isBlank()&&password.getText().isBlank()){
                    JOptionPane.showMessageDialog(null,"ID and password is empty","Warning",JOptionPane.WARNING_MESSAGE);
                    isvalid=false;
                }
                if(isvalid&&id.getText().isBlank()){
                    JOptionPane.showMessageDialog(null,"ID is empty","Warning",2);
                    isvalid=false;
                }
                if(isvalid&&password.getText().isBlank()){
                    JOptionPane.showMessageDialog(null,"Password is empty","Warning",2);
                    isvalid=false;
                }
                if(isvalid&&password.getText().isBlank()){
                    JOptionPane.showMessageDialog(null,"Password is empty","Warning",2);
                }
                if (isvalid) {
                    Employeeportal employeeportal=new Employeeportal();
                    try {
                        pst = con.prepareStatement("select id, name,age,salary,position,mobile,password from employee where id=?");
                        pst.setString(1, id.getText());
                        ResultSet rs = pst.executeQuery();
                        if (rs.next()) {
                            String passwordtable = rs.getString(7);
                            String passwordtext = password.getText();
                            if (passwordtable.equals(passwordtext)) {
                                if (rs.getString(5).equals("HR")) {
                                    n=1;
                                    newwindow();
                                    employeeportal.newwindow();
                                    JOptionPane.showMessageDialog(null, "Welcome to HR portal");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Welcome to Employee portal");
                                    id.setVisible(false);
                                    welcome.setVisible(false);
                                    forgetPasswordButton.setVisible(false);
                                    loginButton.setVisible(false);
                                    pass.setVisible(false);
                                    emp.setVisible(false);
                                    password.setVisible(false);
                                    exitbuttton.setVisible(false);
                                    Employeewelcome.setVisible(true);
                                    idd.setVisible(true);
                                    namee.setVisible(true);
                                    agee.setVisible(true);
                                    passwordd.setVisible(true);
                                    positionn.setVisible(true);
                                    mobilee.setVisible(true);
                                    salaryy.setVisible(true);
                                    exitButton.setVisible(true);
                                    backToLoginButton.setVisible(true);
                                    try {
                                        pst=employeeportal.con.prepareStatement("select id,name,age,salary,position,mobile,password from employee where id=?");
                                        pst.setString(1,id.getText());
                                        rs= pst.executeQuery();
                                        if(rs.next()){
                                            id.setText(rs.getString(1));
                                            idlabel.setText(rs.getString(1));
                                            namelabel.setText(rs.getString(2));
                                            agelabel.setText(rs.getString(3));
                                            salarylabel.setText(rs.getString(4));
                                            positionlabel.setText(rs.getString(5));
                                            mobilelabel.setText(rs.getString(6));
                                            passwordlabel.setText(rs.getString(7));
                                        }
                                    }catch (Exception ex){
                                       JOptionPane.showMessageDialog(null,"Please create new password");
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Password is incorrect","Warning",2);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Entered id doesn't exist","Warning",2);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"You're need to create password");
                    }
                }
            }
        });



        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans = JOptionPane.showConfirmDialog(null, "Do you want to Logout ?", "Warning", JOptionPane.YES_NO_OPTION, 0);
                if (ans == 0) {
                    welcome.setVisible(true);
                    id.setVisible(true);
                    forgetPasswordButton.setVisible(true);
                    loginButton.setVisible(true);
                    pass.setVisible(true);
                    emp.setVisible(true);
                    password.setVisible(true);
                    exitbuttton.setVisible(true);

                     Employeewelcome.setVisible(false);
                    idd.setVisible(false);
                    namee.setVisible(false);
                    agee.setVisible(false);
                    passwordd.setVisible(false);
                    positionn.setVisible(false);
                    mobilee.setVisible(false);
                    salaryy.setVisible(false);
                    exitButton.setVisible(false);
                    backToLoginButton.setVisible(false);

                    id.setText("");
                    password.setText("");

                    idlabel.setText("");
                    namelabel.setText("");
                    agelabel.setText("");
                    salarylabel.setText("");
                    positionlabel.setText("");
                    passwordlabel.setText("");
                    mobilelabel.setText("");
                }
            }
        });



        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans=JOptionPane.showConfirmDialog(null,"Do you you want to exit ?","Warning",JOptionPane.YES_NO_OPTION,0);
                if(ans==0){
                    System.exit(0);
                }
            }
        });
    }

}

