import javax.swing.*;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;

public class filter {

    private JTable table2;
    private JPanel panel1;
    private JScrollPane table_2;
    private JComboBox position;
    private JTextField salary1;
    private JTextField age1;
    private JTextField name;
    private JButton searchButton;
    private JTextField id1;
    private JTextField id2;
    private JTextField mobile;
    private JTextField age2;
    private JTextField salary2;
    private JButton deleteButton;
    // private JComboBox comboBox1;
    private JButton clearallButton;
    private JButton viewAllButton;
    //   private JButton logoutButton;
    private JButton exitButton;
    private JButton backButton;
    private JButton updateButton;
    private JButton maxButton;
    private JButton minValuesButton;
    PreparedStatement pst;


    Login login = new Login();
    Employeeportal emp = new Employeeportal();
    static JFrame frame;
    static int n = 0;

    static void Scamwindow() {
        if (n == 0) {
            frame = new JFrame("Employee Management System");
            frame.setContentPane(new filter().panel1);
            frame.pack();
            frame.setVisible(true);
        } else {
            n = 0;
            frame.dispose();
        }
    }

    void newwindow() {
        Scamwindow();
    }


    void tableload() {
        try {
            pst = emp.con.prepareStatement("select id,name,age,salary,position,mobile from employee");
            ResultSet rs = pst.executeQuery();
            table2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception Ex) {
            Ex.printStackTrace();
        }
    }


    public filter() {

//        maxButton.setMnemonic(KeyEvent.VK_X);
//        minValuesButton.setMnemonic(KeyEvent.VK_N);
        searchButton.setMnemonic(KeyEvent.VK_S);
        deleteButton.setMnemonic(KeyEvent.VK_D);
        viewAllButton.setMnemonic(KeyEvent.VK_F);
        clearallButton.setMnemonic(KeyEvent.VK_C);
        //   logoutButton.setMnemonic(KeyEvent.VK_L);
        exitButton.setMnemonic(KeyEvent.VK_E);
        backButton.setMnemonic(KeyEvent.VK_B);

        tableload();
        table2.setVisible(true);
        panel1.setVisible(true);
        emp.connection();
        table2.setAutoCreateRowSorter(true);
        table2.setCellSelectionEnabled(true);
        table2.setAutoCreateColumnsFromModel(true);
        table2.setEditingColumn(2);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nullid = null, nullid2 = nullid, nullname = null, nullage1 = null, nullage2 = null, nullsalary = null, nullsalary2 = null, nullposition, nullmobile = mobile.getText();
                String idd1 = id1.getText(), idd2 = id2.getText(), agee1 = age1.getText(), agee2 = age2.getText(), salary11 = salary1.getText(), salary22 = salary2.getText();
                try {
                    pst = emp.con.prepareStatement("Select min(id),max(id),min(age),max(age),min(salary),max(salary) from employee");
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        nullid = rs.getString(1);
                        nullid2 = rs.getString(2);
                        nullage1 = rs.getString(3);
                        nullage2 = rs.getString(4);
                        nullsalary = rs.getString(5);
                        nullsalary2 = rs.getString(6);
                    }
                    if (id1.getText().isBlank()) {
                        idd1 = nullid;
                        idd2 = nullid2;
                    }
                    if (age1.getText().isBlank()) {
                        agee1 = nullage1;
                        agee2 = nullage2;
                    }
                    if (salary1.getText().isBlank()) {
                        salary11 = nullsalary;
                        salary22 = nullsalary2;
                    }
                    if (name.getText().isBlank() && position.getSelectedIndex() == 0 && mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("select id,name,age,salary,position,salary,mobile from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?)");
                        pst.setString(1, idd1);
                        pst.setString(2, idd2);
                        pst.setString(3, agee1);
                        pst.setString(4, agee2);
                        pst.setString(5, salary11);
                        pst.setString(6, salary22);
                    }
                    if (!name.getText().isBlank() && position.getSelectedIndex() == 0 && mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("select id,name,age,salary,position,salary,mobile from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=?");
                        pst.setString(1, idd1);
                        pst.setString(2, idd2);
                        pst.setString(3, agee1);
                        pst.setString(4, agee2);
                        pst.setString(5, salary11);
                        pst.setString(6, salary22);
                        pst.setString(7, name.getText());
                    }
                    if (!name.getText().isBlank() && position.getSelectedIndex() != 0 && mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("select id,name,age,salary,position,salary,mobile from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=? and position=?");
                        pst.setString(1, idd1);
                        pst.setString(2, idd2);
                        pst.setString(3, agee1);
                        pst.setString(4, agee2);
                        pst.setString(5, salary11);
                        pst.setString(6, salary22);
                        pst.setString(7, name.getText());
                        pst.setString(8, (String) position.getSelectedItem());
                    }
                    if (!name.getText().isBlank() && position.getSelectedIndex() != 0 && !mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("select id,name,age,salary,position,salary,mobile from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=? and position=? and mobile=?");
                        pst.setString(1, idd1);
                        pst.setString(2, idd2);
                        pst.setString(3, agee1);
                        pst.setString(4, agee2);
                        pst.setString(5, salary11);
                        pst.setString(6, salary22);
                        pst.setString(7, name.getText());
                        pst.setString(8, (String) position.getSelectedItem());
                        pst.setString(9, mobile.getText());
                    }
                    if (name.getText().isBlank() && position.getSelectedIndex() != 0 && mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("select id,name,age,salary,position,salary,mobile from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and position=?");
                        pst.setString(1, idd1);
                        pst.setString(2, idd2);
                        pst.setString(3, agee1);
                        pst.setString(4, agee2);
                        pst.setString(5, salary11);
                        pst.setString(6, salary22);
                        pst.setString(7, (String) position.getSelectedItem());
                    }
                    if (name.getText().isBlank() && position.getSelectedIndex() != 0 && !mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("select id,name,age,salary,position,salary,mobile from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and position=? and mobile=?");
                        pst.setString(1, idd1);
                        pst.setString(2, idd2);
                        pst.setString(3, agee1);
                        pst.setString(4, agee2);
                        pst.setString(5, salary11);
                        pst.setString(6, salary22);
                        pst.setString(7, (String) position.getSelectedItem());
                        pst.setString(8, mobile.getText());
                    }
                    if (name.getText().isBlank() && position.getSelectedIndex() == 0 && !mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("select id,name,age,salary,position,salary,mobile from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and mobile=?");
                        pst.setString(1, idd1);
                        pst.setString(2, idd2);
                        pst.setString(3, agee1);
                        pst.setString(4, agee2);
                        pst.setString(5, salary11);
                        pst.setString(6, salary22);
                        pst.setString(7, mobile.getText());
                    }
                    if (!name.getText().isBlank() && position.getSelectedIndex() == 0 && !mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("select id,name,age,salary,position,salary,mobile from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=? and mobile=?");
                        pst.setString(1, idd1);
                        pst.setString(2, idd2);
                        pst.setString(3, agee1);
                        pst.setString(4, agee2);
                        pst.setString(5, salary11);
                        pst.setString(6, salary22);
                        pst.setString(7, name.getText());
                        pst.setString(8, mobile.getText());
                    }
                    rs = pst.executeQuery();
                    table2.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException exception) {
                    throw new RuntimeException(exception);
                }
            }
        });


                /*try {
                     if(!id1.getText().isBlank()||!id2.getText().isBlank()) {
                        if (!id1.getText().isBlank() && !id2.getText().isBlank()) {
                            pst = emp.con.prepareStatement("select * from  employee where id between ? and ?");
                            pst.setString(1, id1.getText());
                            pst.setString(2, id2.getText());
                        }
                         else if(!id1.getText().isBlank()){
                            pst = emp.con.prepareStatement("select * from  employee where id=?");
                            pst.setString(1, id1.getText());
                        }
                        else{
                            pst = emp.con.prepareStatement("select * from  employee where id=?");
                            pst.setString(1, id2.getText());
                        }
                    }
                     if(!age1.getText().isBlank()||!age2.getText().isBlank()){
                        if (!age1.getText().isBlank() && !age2.getText().isBlank()) {
                            pst = emp.con.prepareStatement("select * from  employee where age between ? and ?");
                            pst.setString(1, age1.getText());
                            pst.setString(2, age2.getText());
                        }

                        else if(!age1.getText().isBlank()){
                            pst = emp.con.prepareStatement("select * from  employee where age=?");
                            pst.setString(1, age1.getText());
                        }
                        else{
                            pst = emp.con.prepareStatement("select * from  employee where age=?");
                            pst.setString(1, age2.getText());
                        }
                    }
                      if(!salary1.getText().isBlank()||!salary2.getText().isBlank()){
                        if (!salary1.getText().isBlank() && !salary2.getText().isBlank()) {
                            pst = emp.con.prepareStatement("select * from  employee where salary between ? and ?");
                            pst.setString(1, salary1.getText());
                            pst.setString(2, salary2.getText());
                        }
                        else if(!salary1.getText().isBlank()){
                            pst = emp.con.prepareStatement("select * from  employee where salary=?");
                            pst.setString(1, salary1.getText());
                        }
                        else if(!salary2.getText().isBlank()){
                            pst = emp.con.prepareStatement("select * from  employee where salary=?");
                            pst.setString(1, salary2.getText());
                        }
                    }
                     if(!name.getText().isBlank()) {
                         pst = emp.con.prepareStatement("select * from employee where name=?");
                         pst.setString(1, name.getText());
                     }
                     if((position.getSelectedIndex() !=0)){
                         pst = emp.con.prepareStatement("select * from employee where position=?");
                         pst.setString(1, (String) position.getSelectedItem());
                     }
                     if(!mobile.getText().isBlank()){
                         pst = emp.con.prepareStatement("select * from employee where mobile=?");
                         pst.setString(1, mobile.getText());
                     }*/
                    /*ResultSet rs = pst.executeQuery();
                    table2.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Enter the valid value");
                    ex.printStackTrace();
                }
            }*/

        /*updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/


       /* updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
                pst=emp.con.prepareStatement("update employee set name=? or age=? or salary=? or position=? or mobile=?");
                pst.setString(1, name.getText());
                pst.setString(2, age1.getText());
                pst.setString(3, salary1.getText());
                pst.setString(4, (String) position.getSelectedItem());
                pst.setString(5, mobile.getText());
               *//* pst.setString(6, id1.getText());
                pst.setString(7, id2.getText());
                pst.setString(8, name.getText());
                pst.setString(9, (String) position.getSelectedItem());
                pst.setString(10, age1.getText());
                pst.setString(11, age2.getText());
                pst.setString(12, salary1.getText());
                pst.setString(13, salary2.getText());
                pst.setString(14, mobile.getText());*//*
                if (pst.executeUpdate()!=0){
                    JOptionPane.showMessageDialog(null,"Updated successfully");
                    tableload();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Enter valid details");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            }
        });*/
             /*   String nullid = null, nullid2 = nullid, nullname = null, nullage1 = null, nullage2 = null, nullsalary = null, nullsalary2 = null, nullposition, nullmobile = mobile.getText();
                String idd1 = id1.getText(), idd2 = id2.getText(), agee1 = age1.getText(), agee2 = age2.getText(), salary11 = salary1.getText(), salary22 = salary2.getText();
                try {
                    pst = emp.con.prepareStatement("Select min(id),max(id),min(age),max(age),min(salary),max(salary) from employee");
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        nullid = rs.getString(1);
                        nullid2 = rs.getString(2);
                        nullage1 = rs.getString(3);
                        nullage2 = rs.getString(4);
                        nullsalary = rs.getString(5);
                        nullsalary2 = rs.getString(6);
                    }
                    if (id1.getText().isBlank()) {
                        idd1 = nullid;
                        idd2 = nullid2;
                    }
                    if (age1.getText().isBlank()) {
                        agee1 = nullage1;
                        agee2 = nullage2;
                    }
                    if (salary1.getText().isBlank()) {
                        salary11 = nullsalary;
                        salary22 = nullsalary2;
                    }
                    if (name.getText().isBlank() && position.getSelectedIndex() == 0 && mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("update employee set name=? or age=? or salary=? or position=? or mobile=? where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?)");
                        pst.setString(1, name.getText());
                        pst.setString(2, age1.getText());
                        pst.setString(3, salary1.getText());
                        pst.setString(4, (String) position.getSelectedItem());
                        pst.setString(5, mobile.getText());
                        pst.setString(6, idd1);
                        pst.setString(7, idd2);
                        pst.setString(8, agee1);
                        pst.setString(9, agee2);
                        pst.setString(10, salary11);
                        pst.setString(11, salary22);
                    }
                    if (!name.getText().isBlank() && position.getSelectedIndex() == 0 && mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("update employee set name=? or age=? or salary=? or position=? or mobile=? where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=?");
                        pst.setString(1, name.getText());
                        pst.setString(2, age1.getText());
                        pst.setString(3, salary1.getText());
                        pst.setString(4, (String) position.getSelectedItem());
                        pst.setString(5, mobile.getText());
                        pst.setString(6, idd1);
                        pst.setString(7, idd2);
                        pst.setString(8, agee1);
                        pst.setString(9, agee2);
                        pst.setString(10, salary11);
                        pst.setString(11, salary22);
                        pst.setString(12, name.getText());
                    }
                    if (!name.getText().isBlank() && position.getSelectedIndex() != 0 && mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("update employee set name=? or age=? or salary=? or position=? or mobile=?  where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=? and position=?");
                        pst.setString(1, name.getText());
                        pst.setString(2, age1.getText());
                        pst.setString(3, salary1.getText());
                        pst.setString(4, (String) position.getSelectedItem());
                        pst.setString(5, mobile.getText()); pst.setString(1, idd1);
                        pst.setString(6, idd2);
                        pst.setString(7, agee1);
                        pst.setString(8, agee2);
                        pst.setString(9, salary11);
                        pst.setString(10, salary22);
                        pst.setString(11, name.getText());
                        pst.setString(12, (String) position.getSelectedItem());
                    }
                    if (!name.getText().isBlank() && position.getSelectedIndex() != 0 && !mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("update employee set name=? or age=? or salary=? or position=? or mobile=? where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=? and position=? and mobile=?");
                        pst.setString(1, name.getText());
                        pst.setString(2, age1.getText());
                        pst.setString(3, salary1.getText());
                        pst.setString(4, (String) position.getSelectedItem());
                        pst.setString(5, mobile.getText());
                        pst.setString(6, idd1);
                        pst.setString(7, idd2);
                        pst.setString(8, agee1);
                        pst.setString(9, agee2);
                        pst.setString(10, salary11);
                        pst.setString(11, salary22);
                        pst.setString(12, name.getText());
                        pst.setString(13, (String) position.getSelectedItem());
                        pst.setString(14, mobile.getText());
                    }
                    if (name.getText().isBlank() && position.getSelectedIndex() != 0 && mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("update employee set name=? or age=? or salary=? or position=? or mobile=? where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and position=?");
                        pst.setString(1, name.getText());
                        pst.setString(2, age1.getText());
                        pst.setString(3, salary1.getText());
                        pst.setString(4, (String) position.getSelectedItem());
                        pst.setString(5, mobile.getText());
                        pst.setString(6, idd1);
                        pst.setString(7, idd2);
                        pst.setString(8, agee1);
                        pst.setString(9, agee2);
                        pst.setString(10, salary11);
                        pst.setString(11, salary22);
                        pst.setString(12, (String) position.getSelectedItem());
                    }
                    if (name.getText().isBlank() && position.getSelectedIndex() != 0 && !mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("update employee set name=? or age=? or salary=? or position=? or mobile=? employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and position=? and mobile=?");
                        pst.setString(1, name.getText());
                        pst.setString(2, age1.getText());
                        pst.setString(3, salary1.getText());
                        pst.setString(4, (String) position.getSelectedItem());
                        pst.setString(5, mobile.getText());
                        pst.setString(6, idd1);
                        pst.setString(7, idd2);
                        pst.setString(8, agee1);
                        pst.setString(9, agee2);
                        pst.setString(10, salary11);
                        pst.setString(11, salary22);
                        pst.setString(12, (String) position.getSelectedItem());
                        pst.setString(13, mobile.getText());
                    }
                    if (name.getText().isBlank() && position.getSelectedIndex() == 0 && !mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("update employee set name=? or age=? or salary=? or position=? or mobile=? where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and mobile=?");
                        pst.setString(1, name.getText());
                        pst.setString(2, age1.getText());
                        pst.setString(3, salary1.getText());
                        pst.setString(4, (String) position.getSelectedItem());
                        pst.setString(5, mobile.getText());
                        pst.setString(6, idd1);
                        pst.setString(7, idd2);
                        pst.setString(8, agee1);
                        pst.setString(9, agee2);
                        pst.setString(10, salary11);
                        pst.setString(11, salary22);
                        pst.setString(12, mobile.getText());
                    }
                    if (!name.getText().isBlank() && position.getSelectedIndex() == 0 && !mobile.getText().isBlank()) {
                        pst = emp.con.prepareStatement("update employee set name=? or age=? or salary=? or position=? or mobile=? employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=? and mobile=?");
                        pst.setString(1, name.getText());
                        pst.setString(2, age1.getText());
                        pst.setString(3, salary1.getText());
                        pst.setString(4, (String) position.getSelectedItem());
                        pst.setString(5, mobile.getText());
                        pst.setString(6, idd1);
                        pst.setString(7, idd2);
                        pst.setString(8, agee1);
                        pst.setString(9, agee2);
                        pst.setString(10, salary11);
                        pst.setString(11, salary22);
                        pst.setString(12, name.getText());
                        pst.setString(13, mobile.getText());
                    }
                    if(pst.executeUpdate()!=0){
                        JOptionPane.showMessageDialog(null,"Updated successfully ");
                        tableload();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Enter the valid details");
                    }
                } catch (SQLException exception) {
                    throw new RuntimeException(exception);
                }
            }*/
        //});


                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int ans = JOptionPane.showConfirmDialog(null, "Do you want to delete ?", "Warning", 2);
                        if (ans == 0) {
                            String nullid = null, nullid2 = nullid, nullname = null, nullage1 = null, nullage2 = null, nullsalary = null, nullsalary2 = null, nullposition, nullmobile = mobile.getText();
                            String idd1 = id1.getText(), idd2 = id2.getText(), agee1 = age1.getText(), agee2 = age2.getText(), salary11 = salary1.getText(), salary22 = salary2.getText();
                            try {
                                pst = emp.con.prepareStatement("Select min(id),max(id),min(age),max(age),min(salary),max(salary) from employee");
                                ResultSet rs = pst.executeQuery();
                                if (rs.next()) {
                                    nullid = rs.getString(1);
                                    nullid2 = rs.getString(2);
                                    nullage1 = rs.getString(3);
                                    nullage2 = rs.getString(4);
                                    nullsalary = rs.getString(5);
                                    nullsalary2 = rs.getString(6);
                                }
                                if (id1.getText().isBlank()) {
                                    idd1 = nullid;
                                    idd2 = nullid2;
                                }
                                if (age1.getText().isBlank()) {
                                    agee1 = nullage1;
                                    agee2 = nullage2;
                                }
                                if (salary1.getText().isBlank()) {
                                    salary11 = nullsalary;
                                    salary22 = nullsalary2;
                                }
                                if (name.getText().isBlank() && position.getSelectedIndex() == 0 && mobile.getText().isBlank()) {
                                    pst = emp.con.prepareStatement("delete from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?)");
                                    pst.setString(1, idd1);
                                    pst.setString(2, idd2);
                                    pst.setString(3, agee1);
                                    pst.setString(4, agee2);
                                    pst.setString(5, salary11);
                                    pst.setString(6, salary22);
                                }
                                if (!name.getText().isBlank() && position.getSelectedIndex() == 0 && mobile.getText().isBlank()) {
                                    pst = emp.con.prepareStatement("delete from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=?");
                                    pst.setString(1, idd1);
                                    pst.setString(2, idd2);
                                    pst.setString(3, agee1);
                                    pst.setString(4, agee2);
                                    pst.setString(5, salary11);
                                    pst.setString(6, salary22);
                                    pst.setString(7, name.getText());
                                }
                                if (!name.getText().isBlank() && position.getSelectedIndex() != 0 && mobile.getText().isBlank()) {
                                    pst = emp.con.prepareStatement("delete from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=? and position=?");
                                    pst.setString(1, idd1);
                                    pst.setString(2, idd2);
                                    pst.setString(3, agee1);
                                    pst.setString(4, agee2);
                                    pst.setString(5, salary11);
                                    pst.setString(6, salary22);
                                    pst.setString(7, name.getText());
                                    pst.setString(8, (String) position.getSelectedItem());
                                }
                                if (!name.getText().isBlank() && position.getSelectedIndex() != 0 && !mobile.getText().isBlank()) {
                                    pst = emp.con.prepareStatement("delete from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=? and position=? and mobile=?");
                                    pst.setString(1, idd1);
                                    pst.setString(2, idd2);
                                    pst.setString(3, agee1);
                                    pst.setString(4, agee2);
                                    pst.setString(5, salary11);
                                    pst.setString(6, salary22);
                                    pst.setString(7, name.getText());
                                    pst.setString(8, (String) position.getSelectedItem());
                                    pst.setString(9, mobile.getText());
                                }
                                if (name.getText().isBlank() && position.getSelectedIndex() != 0 && mobile.getText().isBlank()) {
                                    pst = emp.con.prepareStatement("delete from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and position=?");
                                    pst.setString(1, idd1);
                                    pst.setString(2, idd2);
                                    pst.setString(3, agee1);
                                    pst.setString(4, agee2);
                                    pst.setString(5, salary11);
                                    pst.setString(6, salary22);
                                    pst.setString(7, (String) position.getSelectedItem());
                                }
                                if (name.getText().isBlank() && position.getSelectedIndex() != 0 && !mobile.getText().isBlank()) {
                                    pst = emp.con.prepareStatement("delete from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and position=? and mobile=?");
                                    pst.setString(1, idd1);
                                    pst.setString(2, idd2);
                                    pst.setString(3, agee1);
                                    pst.setString(4, agee2);
                                    pst.setString(5, salary11);
                                    pst.setString(6, salary22);
                                    pst.setString(7, (String) position.getSelectedItem());
                                    pst.setString(8, mobile.getText());
                                }
                                if (name.getText().isBlank() && position.getSelectedIndex() == 0 && !mobile.getText().isBlank()) {
                                    pst = emp.con.prepareStatement("delete from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and mobile=?");
                                    pst.setString(1, idd1);
                                    pst.setString(2, idd2);
                                    pst.setString(3, agee1);
                                    pst.setString(4, agee2);
                                    pst.setString(5, salary11);
                                    pst.setString(6, salary22);
                                    pst.setString(7, mobile.getText());
                                }
                                if (!name.getText().isBlank() && position.getSelectedIndex() == 0 && !mobile.getText().isBlank()) {
                                    pst = emp.con.prepareStatement("delete from employee where (id between ? and ?)  and (age between ? and ?) and (salary between ? and ?) and name=? and mobile=?");
                                    pst.setString(1, idd1);
                                    pst.setString(2, idd2);
                                    pst.setString(3, agee1);
                                    pst.setString(4, agee2);
                                    pst.setString(5, salary11);
                                    pst.setString(6, salary22);
                                    pst.setString(7, name.getText());
                                    pst.setString(8, mobile.getText());
                                }
                                if (pst.executeUpdate() != 0) {
                                    tableload();
                                    JOptionPane.showMessageDialog(null, "deleted successfully");
                                } else {
                                    JOptionPane.showMessageDialog(null, " Please enter valid details");
                                }
                            } catch (SQLException exception) {
                                throw new RuntimeException(exception);
                            }
                        }
                    }
                /*String pos = (String) position.getSelectedItem();
                try {
                    if (!id1.getText().isBlank() || !id2.getText().isBlank()) {
                        if (!id1.getText().isBlank() && !id2.getText().isBlank()) {
                            pst = emp.con.prepareStatement("delete from  employee where id between ? and ?");
                            pst.setString(1, id1.getText());
                            pst.setString(2, id2.getText());
                        } else if (!id1.getText().isBlank()) {
                            pst = emp.con.prepareStatement("delete from  employee where id=?");
                            pst.setString(1, id1.getText());
                        } else {
                            pst = emp.con.prepareStatement("delete * from  employee where id=?");
                            pst.setString(1, id2.getText());
                        }
                    } else if (!age1.getText().isBlank() || !age2.getText().isBlank()) {
                        if (!age1.getText().isBlank() && !age2.getText().isBlank()) {
                            pst = emp.con.prepareStatement("delete from  employee where age between ? and ?");
                            pst.setString(1, age1.getText());
                            pst.setString(2, age2.getText());
                        } else if (!age1.getText().isBlank()) {
                            pst = emp.con.prepareStatement("delete from  employee where age=?");
                            pst.setString(1, age1.getText());
                        } else {
                            pst = emp.con.prepareStatement("delete from  employee where age=?");
                            pst.setString(1, age2.getText());
                        }
                    } else if (!salary1.getText().isBlank() || !salary2.getText().isBlank()) {
                        if (!salary1.getText().isBlank() && !salary2.getText().isBlank()) {
                            pst = emp.con.prepareStatement("delete from  employee where salary between ? and ?");
                            pst.setString(1, salary1.getText());
                            pst.setString(2, salary2.getText());
                        } else if (!salary1.getText().isBlank()) {
                            pst = emp.con.prepareStatement("delete from  employee where salary=?");
                            pst.setString(1, salary1.getText());
                        } else if (!salary2.getText().isBlank()) {
                            pst = emp.con.prepareStatement("delete from  employee where salary=?");
                            pst.setString(1, salary2.getText());
                        }
                    }*/
                /*int ans=JOptionPane.showConfirmDialog(null,"do you want to delete?","Warning",JOptionPane.YES_NO_OPTION,2);
                if(ans==0) {
                    try {
                        pst = emp.con.prepareStatement("delete from employee where (id between ? and ?) or (name=?) or (age between ? and ?) or (salary between ? and ?) or (position=?) or (mobile=?)");
                        pst.setString(1, id1.getText());
                        pst.setString(2, id2.getText());
                        pst.setString(3, name.getText());
                        pst.setString(4, age1.getText());
                        pst.setString(5, age2.getText());
                        pst.setString(6, salary1.getText());
                        pst.setString(7, salary2.getText());
                        pst.setString(8, (String) position.getSelectedItem());
                        pst.setString(9, mobile.getText());
                        if(pst.executeUpdate()!=0){
                            JOptionPane.showMessageDialog(null,"Successfully deleted");
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Please enter valid details");
                        }
//                        table2.setModel(DbUtils.resultSetToTableModel(rs));
                        tableload();
                        clearall();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Enter the valid value");
                        ex.printStackTrace();
                    }
                }*/
                });


/*
          comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pos=(String)position.getSelectedItem();
                if(Objects.equals(comboBox1.getSelectedItem(), "position")){
                    try {
                        if(!id1.getText().isBlank()||!id2.getText().isBlank()) {
                            if (!id1.getText().isBlank() && !id2.getText().isBlank()) {
                                pst = emp.con.prepareStatement("update employee set position=? where id between ? and ?");
                                pst.setString(1, (String) position.getSelectedItem());
                                pst.setString(2,id1.getText());
                                pst.setString(3,id2.getText());
                            }
                            else if(!id1.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set position=? where id=?");
                                pst.setString(1, (String) position.getSelectedItem());
                                pst.setString(2,id1.getText());
                            }
                            else{
                                pst = emp.con.prepareStatement("update employee set position=? where id=?");
                                pst.setString(1, (String) position.getSelectedItem());
                                pst.setString(2,id2.getText());
                            }
                        }
                        else if(!age1.getText().isBlank()||!age2.getText().isBlank()){
                            if (!age1.getText().isBlank() && !age2.getText().isBlank()) {
                                pst = emp.con.prepareStatement("update employee set position=? where age between ? and ?");
                                pst.setString(1, (String) position.getSelectedItem());
                                pst.setString(2,age1.getText());
                                pst.setString(3, age2.getText());
                            }
                            else if(!age1.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set position=?  where age=?");
                                pst.setString(1, (String) position.getSelectedItem());
                                pst.setString(2,age1.getText());
                            }
                            else{
                                pst = emp.con.prepareStatement("update employee set position=?  where age=?");
                                pst.setString(1, (String) position.getSelectedItem());
                                pst.setString(2,id2.getText());                        }
                        }
                        else if(!salary1.getText().isBlank()||!salary2.getText().isBlank()){
                            if (!salary1.getText().isBlank() && !salary2.getText().isBlank()) {
                                pst = emp.con.prepareStatement("update employee set position=? where salary between ? and ?");
                                pst.setString(1, (String) position.getSelectedItem());
                                pst.setString(2,salary1.getText());
                                pst.setString(3,salary2.getText());
                            }
                            else if(!salary1.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set position=? where salary=?");
                                pst.setString(1,(String) position.getSelectedItem());
                                pst.setString(2,salary1.getText());
                            }
                            else if(!salary2.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set position=? where salary=?");
                                pst.setString(1, (String) position.getSelectedItem());
                                pst.setString(2,salary2.getText());
                            }
                        }
                        else if(!pos.isBlank()) {
                                pst = emp.con.prepareStatement("update employee set position=? where position=?");
                                pst.setString(1, (String) position.getSelectedItem());
                                pst.setString(2, (String) position.getSelectedItem());
                        }
                        else if(!name.getText().isBlank()){
                            pst = emp.con.prepareStatement("update employee set position=? where name=?");
                            pst.setString(1, (String) position.getSelectedItem());
                            pst.setString(2, name.getText());
                        }
                        else if(!mobile.getText().isBlank()){
                            pst = emp.con.prepareStatement("update employee set position=? where mobile=?");
                            pst.setString(1, (String) position.getSelectedItem());
                            pst.setString(2, mobile.getText());
                        }
                        if (pst.executeUpdate()!=0) {
                            JOptionPane.showMessageDialog(null, "Success");
                            panel1.setVisible(true);
                            table2.setVisible(true);
                            tableload();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"Enter the valid value");
                        ex.printStackTrace();
                    }
                }
                else if(comboBox1.getSelectedItem().equals("salary")){
                    try {
                        if(!id1.getText().isBlank()||!id2.getText().isBlank()) {
                            if (!id1.getText().isBlank() && !id2.getText().isBlank()) {
                                pst = emp.con.prepareStatement("update employee set salary=?  where id between ? and ?");
                                pst.setString(1, salary1.getText());
                                pst.setString(2,id1.getText());
                                pst.setString(3,id2.getText());
                            }
                            else if(!id1.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set salary=? where id=?");
                                pst.setString(1, salary1.getText());
                                pst.setString(2,id1.getText());
                            }
                            else{
                                pst = emp.con.prepareStatement("update employee set salary=? where id=?");
                                pst.setString(1, salary1.getText());
                                pst.setString(2,id2.getText());
                            }
                        }
                        else if(!age1.getText().isBlank()||!age2.getText().isBlank()){
                            if (!age1.getText().isBlank() && !age2.getText().isBlank()) {
                                pst = emp.con.prepareStatement("update employee set salary=? where age between ? and ?");
                                pst.setString(1, salary1.getText());
                                pst.setString(2,age1.getText());
                                pst.setString(3, age2.getText());
                            }
                            else if(!age1.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set salary =?  where age=?");
                                pst.setString(1, salary1.getText());
                                pst.setString(2,age1.getText());
                            }
                            else{
                                pst = emp.con.prepareStatement("update employee set salary=? where age=?");
                                pst.setString(1,salary1.getText());
                                pst.setString(2,id2.getText());                        }
                        }
                        else if(!salary1.getText().isBlank()||!salary2.getText().isBlank()){
                            if (!salary1.getText().isBlank() && !salary2.getText().isBlank()) {
                                pst = emp.con.prepareStatement("update employee set salary=? where salary between ? and ?");
                                pst.setString(1, salary1.getText());
                                pst.setString(2,salary1.getText());
                                pst.setString(3,salary2.getText());
                            }
                            else if(!salary1.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set salary=? where salary=?");
                                pst.setString(1,salary1.getText());
                                pst.setString(2,salary1.getText());
                            }
                            else if(!salary2.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set salary=? where salary=?");
                                pst.setString(1, salary1.getText());
                                pst.setString(2,salary2.getText());
                            }
                        }
                        else if(!name.getText().isBlank()|| position.getSelectedIndex()!=0||!mobile.getText().isBlank()||!id1.getText().isBlank()||!age1.getText().isBlank()||!salary1.getText().isBlank()) {
                            pst = emp.con.prepareStatement("update employee set salary=? where name=? or position=? or mobile=?");
                            pst.setString(1, salary1.getText());
                            pst.setString(2,name.getText());
                            pst.setString(3, String.valueOf(position.getSelectedIndex()));
                            pst.setString(4, mobile.getText());
                        }
                        if (pst.executeUpdate()!=0) {
                            JOptionPane.showMessageDialog(null, "Success");
                            panel1.setVisible(true);
                            table2.setVisible(true);
                            tableload();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"Enter the valid value");
                        ex.printStackTrace();
                    }
                }
                else if(comboBox1.getSelectedItem().equals("age")){
                try {
                    if(!id1.getText().isBlank()||!id2.getText().isBlank()) {
                        if (!id1.getText().isBlank() && !id2.getText().isBlank()) {
                            pst = emp.con.prepareStatement("update employee set age=?  where id between ? and ?");
                            pst.setString(1, age1.getText());
                            pst.setString(2,id1.getText());
                            pst.setString(3,id2.getText());
                        }
                        else if(!id1.getText().isBlank()){
                            pst = emp.con.prepareStatement("update employee set age=? where id=?");
                            pst.setString(1, age1.getText());
                            pst.setString(2,id1.getText());
                        }
                        else{
                            pst = emp.con.prepareStatement("update employee set age=? where id=?");
                            pst.setString(1, age1.getText());
                            pst.setString(2,id2.getText());
                        }
                    }
                    else if(!age1.getText().isBlank()||!age2.getText().isBlank()){
                        if (!age1.getText().isBlank() && !age2.getText().isBlank()) {
                            pst = emp.con.prepareStatement("update employee set age=? where age between ? and ?");
                            pst.setString(1, age1.getText());
                            pst.setString(2,age1.getText());
                            pst.setString(3, age2.getText());
                        }
                        else if(!age1.getText().isBlank()){
                            pst = emp.con.prepareStatement("update employee set age =?  where age=?");
                            pst.setString(1, age1.getText());
                            pst.setString(2,age1.getText());
                        }
                        else{
                            pst = emp.con.prepareStatement("update employee set salary=? where age=?");
                            pst.setString(1,age1.getText());
                            pst.setString(2,id2.getText());                        }
                    }
                    else if(!salary1.getText().isBlank()||!salary2.getText().isBlank()){
                        if (!salary1.getText().isBlank() && !salary2.getText().isBlank()) {
                            pst = emp.con.prepareStatement("update employee set age=? where salary between ? and ?");
                            pst.setString(1, age1.getText());
                            pst.setString(2,salary1.getText());
                            pst.setString(3,salary2.getText());
                        }
                        else if(!salary1.getText().isBlank()){
                            pst = emp.con.prepareStatement("update employee set age=? where salary=?");
                            pst.setString(1,age1.getText());
                            pst.setString(2,salary1.getText());
                        }
                        else if(!salary2.getText().isBlank()){
                            pst = emp.con.prepareStatement("update employee set age=? where salary=?");
                            pst.setString(1, age1.getText());
                            pst.setString(2,salary2.getText());
                        }
                    }
                    else if(!name.getText().isBlank()|| position.getSelectedIndex()!=0||!mobile.getText().isBlank()||!id1.getText().isBlank()||!age1.getText().isBlank()||!salary1.getText().isBlank()) {
                        pst = emp.con.prepareStatement("update employee set age=? where name=? or position=? or mobile=?");
                        pst.setString(1,age1.getText());
                        pst.setString(2,name.getText());
                        pst.setString(3, String.valueOf(position.getSelectedIndex()));
                        pst.setString(4, mobile.getText());
                    }
                    if (pst.executeUpdate()!=0) {
                        JOptionPane.showMessageDialog(null, "Success");
                        panel1.setVisible(true);
                        table2.setVisible(true);
                        tableload();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Enter the valid value");
                    ex.printStackTrace();
                }
            }
                else if(comboBox1.getSelectedItem().equals("name")){
                    try {
                        if(!id1.getText().isBlank()||!id2.getText().isBlank()) {
                            if (!id1.getText().isBlank() && !id2.getText().isBlank()) {
                                pst = emp.con.prepareStatement("update employee set name=?  where id between ? and ?");
                                pst.setString(1, name.getText());
                                pst.setString(2,id1.getText());
                                pst.setString(3,id2.getText());
                            }
                            else if(!id1.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set name=? where id=?");
                                pst.setString(1, name.getText());
                                pst.setString(2,id1.getText());
                            }
                            else{
                                pst = emp.con.prepareStatement("update employee set name=? where id=?");
                                pst.setString(1, name.getText());
                                pst.setString(2,id2.getText());
                            }
                        }
                        else if(!age1.getText().isBlank()||!age2.getText().isBlank()){
                            if (!age1.getText().isBlank() && !age2.getText().isBlank()) {
                                pst = emp.con.prepareStatement("update employee set name=? where age between ? and ?");
                                pst.setString(1, name.getText());
                                pst.setString(2,age1.getText());
                                pst.setString(3, age2.getText());
                            }
                            else if(!age1.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set name =?  where age=?");
                                pst.setString(1,name.getText());
                                pst.setString(2,age1.getText());
                            }
                            else{
                                pst = emp.con.prepareStatement("update employee set name=? where age=?");
                                pst.setString(1,name.getText());
                                pst.setString(2,id2.getText());                        }
                        }
                        else if(!salary1.getText().isBlank()||!salary2.getText().isBlank()){
                            if (!salary1.getText().isBlank() && !salary2.getText().isBlank()) {
                                pst = emp.con.prepareStatement("update employee set name=? where salary between ? and ?");
                                pst.setString(1, name.getText());
                                pst.setString(2,salary1.getText());
                                pst.setString(3,salary2.getText());
                            }
                            else if(!salary1.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set name=? where salary=?");
                                pst.setString(1,name.getText());
                                pst.setString(2,salary1.getText());
                            }
                            else if(!salary2.getText().isBlank()){
                                pst = emp.con.prepareStatement("update employee set name=? where salary=?");
                                pst.setString(1,name.getText());
                                pst.setString(2,salary2.getText());
                            }
                        }
                        else if(!name.getText().isBlank()|| position.getSelectedIndex()!=0||!mobile.getText().isBlank()||!id1.getText().isBlank()||!age1.getText().isBlank()||!salary1.getText().isBlank()) {
                            pst = emp.con.prepareStatement("update employee set name=? where name=? or position=? or mobile=?");
                            pst.setString(1,name.getText());
                            pst.setString(2,name.getText());
                            pst.setString(3, String.valueOf(position.getSelectedIndex()));
                            pst.setString(4, mobile.getText());
                        }
                        if (pst.executeUpdate()!=0) {
                            JOptionPane.showMessageDialog(null, "Success");
                            panel1.setVisible(true);
                            table2.setVisible(true);
                            tableload();
                            clearall();
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"Enter the valid value");
                        ex.printStackTrace();
                    }
                }
            }
        });*/


                clearallButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clearall();
                    }
                });


                viewAllButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tableload();
                    }
                });



       /* logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans = JOptionPane.showConfirmDialog(null, "Do you you want to Logout?", "Warning", JOptionPane.YES_NO_OPTION,2);
                if (ans == 0) {
                    emp.main.setVisible(false);
                    panel1.setVisible(false);
                    login.log();
                }
            }
        });*/

                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        n = 1;
                        newwindow();
                        emp.newwindow();
                    }
                });


                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int ans = JOptionPane.showConfirmDialog(null, "Do you you want to exit application?", "Warning", JOptionPane.YES_NO_OPTION, 2);
                        if (ans == 0) {
                            System.exit(0);
                        }
                    }
                });


       /* maxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id, age, salary;
                try {
                    pst = emp.con.prepareStatement("select max(id),max(salary),max(age) from employee");
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        id = rs.getInt(1);
                        age = rs.getInt(3);
                        salary = rs.getInt(2);
                        JOptionPane.showMessageDialog(null, "Minimum id = " + id + "\nMinimum age = " + age + "\nMinimum salary = " + salary);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        minValuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id, age, salary;
                try {
                    pst = emp.con.prepareStatement("select min(id),min(salary),min(age) from employee");
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        id = rs.getInt(1);
                        age = rs.getInt(3);
                        salary = rs.getInt(2);
                        JOptionPane.showMessageDialog(null, "Minimum id = " + id + "\nMinimum age = " + age + "\nMinimum salary = " + salary);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });*/
            }

            void clearall() {
                id1.setText("");
                id2.setText("");
                name.setText("");
                age1.setText("");
                age2.setText("");
                salary1.setText("");
                salary2.setText("");
                position.setSelectedIndex(0);
                //  comboBox1.setSelectedIndex(0);
                mobile.setText("");
            }
        }



