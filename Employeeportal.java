import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;

public class Employeeportal {
    public JPanel main;
    private JTextField id;
    private JTextField position;
    private JTextField salary;
    private JTextField age;
    private JTextField mobile;
    private JTextField name;
    private JTable table1;
    private JButton createButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;
    private JLabel label_name;
    private JLabel label_age;
    private JLabel label_salary;
    private JLabel label_posistion;
    private JLabel label_mobile;
    private JScrollPane table_1;
    private JButton exitButton;
    private JButton clearAllButton;
    private JComboBox comboBox1;
    private JProgressBar progress;
    private JButton deleteAllButton;
    private JButton filter;
    private JButton logoutButton;
    private JTable table2;
    private JComboBox sort;
    private JButton loginButton;
    private JTextField password;
    private JPanel panel;

    Connection con;
    PreparedStatement pst;
    Login login = new Login();
    static JFrame frame;


    static int n=0;
    static void Scamwindow() {
        if(n==0) {
            frame = new JFrame("Employee Management System");
            frame.setContentPane(new Employeeportal().main );
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



    void connection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDB?useSSL=false", "root", "Ashok@123");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    void tableload() {
        try {
            pst = con.prepareStatement("select id,name,age,salary,position,mobile from employee");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception Ex) {
            Ex.printStackTrace();
        }
    }

        public Employeeportal() {

         
        createButton.setMnemonic(KeyEvent.VK_C);
        updateButton.setMnemonic(KeyEvent.VK_U);
        deleteButton.setMnemonic(KeyEvent.VK_D);
        searchButton.setMnemonic(KeyEvent.VK_S);
        deleteAllButton.setMnemonic(KeyEvent.VK_A);
        clearAllButton.setMnemonic(KeyEvent.VK_R);
        exitButton.setMnemonic(KeyEvent.VK_E);
        filter.setMnemonic(KeyEvent.VK_F);
        logoutButton.setMnemonic(KeyEvent.VK_L);

        progress.setVisible(false);
        progress.setValue(100);

        connection();
        tableload();

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isvalidsave()) {
                        pst = con.prepareStatement("insert into employee(name,age,salary,position,mobile) values(?,?,?,?,?)");
                        pst.setString(1, name.getText());
                        pst.setString(2, age.getText());
                        pst.setString(3, salary.getText());
                        pst.setString(4, (String) comboBox1.getSelectedItem());
                        pst.setString(5, mobile.getText());
                        if (pst.executeUpdate() != 0) {
                            progress.setVisible(true);
                            JOptionPane.showMessageDialog(table1, "Employee Added Successfully !");
                            clearall();
                            tableload();
                            name.requestFocus();
                        }
                    }
                } catch (SQLException ex) {
                }
            }
        });




        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pst = con.prepareStatement("select Name,Age,Salary,position,Mobile from employee where id=?");
                    pst.setString(1, id.getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        id.setText(id.getText());
                        name.setText(rs.getString(1));
                        age.setText(rs.getString(2));
                        salary.setText(rs.getString(3));
                        comboBox1.setSelectedItem(rs.getString(4));
                        mobile.setText(rs.getString(5));
                    } else {
                        JOptionPane.showMessageDialog(null, "Entered id doesn't exist.Please recheck the id");
                        clearall();
                        id.requestFocus();
                    }
                } catch (Exception Ex) {
                }
            }
        });





        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean idthere=true;
                try {
                    int idd= Integer.parseInt(id.getText());
                }catch (Exception exception){
                    idthere=false;
                    JOptionPane.showMessageDialog(null,"Please enter the valid id");
                }
                if(idthere) {
                    try {
                        pst = con.prepareStatement("select id,name,age,salary,position,mobile from employee where id =?");
                        pst.setString(1, id.getText());
                        ResultSet rs = pst.executeQuery();
                        if (rs.next()) {

                        } else {
                            idthere = false;
                            JOptionPane.showMessageDialog(null, "Entered id doesn't exist","Warning",2);
                        }
                        if (idthere) {
                            pst = con.prepareStatement("delete from employee where id=?");
                            pst.setString(1, id.getText());
                            int ans = JOptionPane.showConfirmDialog(null, "You want to delete this details", "Warning", JOptionPane.YES_NO_OPTION, 1);
                            if (ans == 0&&pst.executeUpdate()!=0) {
                                progress.setVisible(true);
                                JOptionPane.showMessageDialog(null, "ID  " + id.getText() + " is deleted !");
                                tableload();
                                clearall();
                            }
                        }
                        } catch(SQLException ex){
                            JOptionPane.showMessageDialog(null, "Enter the valid id", "Warning", 2);
                        }
                    }
            }
        });





        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans = JOptionPane.showConfirmDialog(null, "Do you you want to exit application?", "Warning", JOptionPane.YES_NO_OPTION,2);
                if (ans == 0) {
                    System.exit(0);
                }

            }
        });




        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearall();
            }
        });




        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name, Age, Salary, Combobox1, Mobile;
                try {
                    if (isvalidupdate()) {
                        pst = con.prepareStatement("select name,age,salary,position,mobile from employee where id=?");
                        pst.setInt(1, Integer.parseInt(id.getText()));
                        ResultSet rs = pst.executeQuery();
                        if (rs.next()) {
                            Name = rs.getString(1);
                            Age = rs.getString(2);
                            Salary = rs.getString(3);
                            Combobox1 = rs.getString(4);
                            Mobile = rs.getString(5);

                            pst = con.prepareStatement("update employee set Name=?,age=?,salary=?,position=?,mobile=? where id=?");
                            pst.setString(1, name.getText());
                            pst.setString(2, age.getText());
                            pst.setString(3, salary.getText());
                            pst.setString(4, (String) comboBox1.getSelectedItem());
                            pst.setString(5, mobile.getText());
                            pst.setString(6, id.getText());

                            if (Name.equals(name.getText()) && Age.equals(age.getText()) && Salary.equals(salary.getText()) && Combobox1.equals(comboBox1.getSelectedItem()) && Mobile.equals(mobile.getText()) && pst.executeUpdate() != 0) {
                                JOptionPane.showMessageDialog(null, "No changes in update");
                                tableload();
                            } else if (pst.executeUpdate() != 0) {
                                JOptionPane.showMessageDialog(null, "updated successfully");
                                tableload();
                                progress.setVisible(true);
                            }
                            clearall();
                        }
                    }
                } catch (SQLException ex) {
                }
            }
        });


           deleteAllButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {

                   try {
                       int ans=JOptionPane.showConfirmDialog(null,"Do you want to delete all employee ?","Warning",2);
                       if(ans==0) {
                           pst = con.prepareStatement("truncate table employee");
                           pst.executeUpdate();
                           tableload();
                       }
                   } catch (SQLException ex) {
                       throw new RuntimeException(ex);
                   }
               }
           });



           filter.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   filter f = new filter();
                   f.newwindow();
                   n=1;
                   newwindow();
               }

           });



            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int ans = JOptionPane.showConfirmDialog(null, "Do you you want to Logout?", "Warning", JOptionPane.YES_NO_OPTION,2);
                    if (ans == 0) {
                        n=1;
                        newwindow();
                        login.newwindow();
                    }
                }
            });
        }



        //methods calling

        boolean isvalidsave () {
            boolean iserror = false;
            String pos = (String) comboBox1.getSelectedItem();
            try {
                pst = con.prepareStatement("select ID,age,salary,position,mobile from employee where name=?");
                pst.setString(1, name.getText());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    Long Mobilech = Long.valueOf(rs.getString(5));
                    Long mobilee = Long.valueOf(mobile.getText());
                    if (Mobilech.equals(mobilee)) {
                        iserror = true;
                        JOptionPane.showMessageDialog(null, "Entered details already exist.check the id " + rs.getInt(1) + " in employee data");
                    }
                }
            } catch (Exception ex) {
            }
            try {
                pst = con.prepareStatement("select id,name,age,salary,position,mobile from employee where mobile=?");
                pst.setLong(1, Long.parseLong(mobile.getText()));
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    Long Mobilech = Long.valueOf(rs.getString(6));
                    if (Long.valueOf(mobile.getText()).equals(Mobilech)) {
                        iserror = true;
                        JOptionPane.showMessageDialog(null, "Entered mobile number already exist in id number " + rs.getInt(1));
                    }
                }
            } catch (Exception ex) {
            }
            if (name.getText().isBlank()) {
                iserror = true;
                JOptionPane.showMessageDialog(null, "Enter the name");
            }
            String nam=name.getText();
            char[]ch=nam.toCharArray();
            for (int i = 0; i <nam.length()&&!iserror; i++) {
                if(!Character.isAlphabetic(ch[i])){
                    iserror=true;
                    JOptionPane.showMessageDialog(null,"only alphabets are allowed in name","Warning",2);
                }
            }
            if (pos.isBlank()) {
                iserror = true;
                JOptionPane.showMessageDialog(null, "Enter the valid position");
            }
            try {
                int agech = Integer.parseInt(age.getText());
                if(agech>100){
                    iserror=true;
                    JOptionPane.showMessageDialog(null, "Enter the age less dhan 100");
                }
            } catch (Exception ex) {
                iserror = true;
                JOptionPane.showMessageDialog(null, "Enter the valid age");
            }
            try {
                int salarych = Integer.parseInt(salary.getText());
            } catch (Exception ex) {
                iserror = true;
                JOptionPane.showMessageDialog(null, "Enter the valid salary");
            }
            try {
                long mobilech= Long.parseLong(mobile.getText());
                if (mobile.getText().length() != 10) {
                    iserror = true;
                    JOptionPane.showMessageDialog(null, "Enter the 10 digit mobile number");
                }
            } catch (Exception ex) {
                iserror = true;
                JOptionPane.showMessageDialog(null, "Enter the valid mobile no");
            }
            return !iserror;
        }





        boolean isvalidupdate () {
            boolean iserror = false;
            String pos = (String) comboBox1.getSelectedItem();
            if (name.getText().length() == 0) {
                iserror = true;
                JOptionPane.showMessageDialog(null, "Enter the name");
            }
            String nam=name.getText();
            char[]ch=nam.toCharArray();
            for (int i = 0; i <nam.length()&&!iserror; i++) {
                if(!Character.isAlphabetic(ch[i])){
                    iserror=true;
                    JOptionPane.showMessageDialog(null,"only alphabets are allowed in name","Warning",2);
                }
            }
            if (pos.isBlank()) {
                iserror = true;
                JOptionPane.showMessageDialog(null, "Position not mentioned");
            }
            try {
                int agech = Integer.parseInt(age.getText());
                if(agech>100){
                    iserror=true;
                    JOptionPane.showMessageDialog(null, "Enter the age less dhan 100");
                }
            } catch (Exception ex) {
                iserror = true;
                JOptionPane.showMessageDialog(null, "Enter the valid age");
            }
            try {
                int salarych = Integer.parseInt(salary.getText());
            } catch (Exception ex) {
                iserror = true;
                JOptionPane.showMessageDialog(null, "Enter the valid salary");
            }
            try {
               long mobilech= Long.parseLong(mobile.getText());
                if (mobile.getText().length() != 10) {
                    iserror = true;
                    JOptionPane.showMessageDialog(null, "Enter the 10 digit mobile number");
                }
            } catch (Exception ex) {
                iserror = true;
                JOptionPane.showMessageDialog(null, "Enter the valid mobile no");
            }
            return !iserror;
        }


        public void clearall () {
            id.setText("");
            name.setText("");
            age.setText("");
            salary.setText("");
            comboBox1.setSelectedIndex(0);
            mobile.setText("");
            progress.setVisible(false);
            id.requestFocus();
        }

    }
