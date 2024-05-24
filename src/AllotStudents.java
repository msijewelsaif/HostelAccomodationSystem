/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.sql.*;
import dbms.ConnectDB;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class AllotStudents extends javax.swing.JFrame {
 private void allot_tableRowSelected(ListSelectionEvent event) {
        
        if (!event.getValueIsAdjusting() && allot_table.getSelectedRow() != -1) {
            int selectedRowIndex = allot_table.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) allot_table.getModel();

  
            String studentID = model.getValueAt(selectedRowIndex, 0).toString();
            String studentName = model.getValueAt(selectedRowIndex, 1).toString();
            String userName = model.getValueAt(selectedRowIndex, 2).toString();
            String phoneNumber = model.getValueAt(selectedRowIndex, 3).toString();
            String email = model.getValueAt(selectedRowIndex, 4).toString();
            String roomNumber = model.getValueAt(selectedRowIndex, 5).toString();
            String guardianContact = model.getValueAt(selectedRowIndex, 6).toString();

            // Populate text fields with the data
            student_id.setText(studentID);
            student_name.setText(studentName);
            username.setText(userName);
            phone_number.setText(phoneNumber);
            Email.setText(email);
            room_number.setText(roomNumber);
            guardian_contact.setText(guardianContact);
        }
    }

    /**
     * Creates new form AllotStudents
     */
     private void displayRoomDetails() {
        DefaultTableModel model = (DefaultTableModel) room_table.getModel();
        model.setRowCount(0); 

        try {
            Connection conn = ConnectDB.getCon();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT roomNumber, isActive, status FROM manage_room");

            while (rs.next()) {
                String roomNumber = rs.getString("roomNumber");
                String isActive = rs.getString("isActive");
                String status = rs.getString("status");

               
                model.addRow(new Object[]{roomNumber, isActive, status});
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching room details: " + e.getMessage());
        }
    }
    private void clearFields() {
    student_id.setEditable(true);
    student_name.setEditable(true);
    username.setEditable(true);
    phone_number.setEditable(true);
    Email.setEditable(true);
    room_number.setEditable(true);
    guardian_contact.setEditable(true);
    student_id.setText("");
    student_name.setText("");
    username.setText("");
    phone_number.setText("");
    Email.setText("");
    room_number.setText("");
    guardian_contact.setText("");
}
    
    private void leftStudent(){
        String studentID = student_id.getText();
        String studentName = student_name.getText();
        String userName = username.getText();
        String phoneNumber = phone_number.getText();
        String email = Email.getText();
        String roomNumber = room_number.getText();
        String guardianContact = guardian_contact.getText();
        try {
            Connection conn = ConnectDB.getCon();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO hostel_left (student_id, student_name, username, phone_number, email, room_number, guardian_contact) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, studentID);
            pstmt.setString(2, studentName);
            pstmt.setString(3, userName);
            pstmt.setString(4, phoneNumber);
            pstmt.setString(5, email);
            pstmt.setString(6, roomNumber);
            pstmt.setString(7, guardianContact);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Student Left successfully.");
           
            } else {
                JOptionPane.showMessageDialog(null, "Failed to remove the student.");
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in leaving : " + e.getMessage());
        }
        
    }
    
     private void allotStudent() {
        String studentID = student_id.getText();
        String studentName = student_name.getText();
        String userName = username.getText();
        String phoneNumber = phone_number.getText();
        String email = Email.getText();
        String roomNumber = room_number.getText();
        String guardianContact = guardian_contact.getText();

        try {
            Connection conn = ConnectDB.getCon();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO students (student_id, student_name, username, phone_number, email, room_number, guardian_contact) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, studentID);
            pstmt.setString(2, studentName);
            pstmt.setString(3, userName);
            pstmt.setString(4, phoneNumber);
            pstmt.setString(5, email);
            pstmt.setString(6, roomNumber);
            pstmt.setString(7, guardianContact);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Student allotted successfully.");
                
            } else {
                JOptionPane.showMessageDialog(null, "Failed to allot student.");
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error allotting student: " + e.getMessage());
        }
    }

    public AllotStudents() {
        initComponents();
        setLocationRelativeTo(null);
        displayRoomDetails();
        displayStudentDetails();
        
    }

    
    
     private void displayStudentDetails() {
        DefaultTableModel model = (DefaultTableModel) allot_table.getModel();
        model.setRowCount(0); 

        try {
            Connection conn = ConnectDB.getCon();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
             
                String studentID = rs.getString("student_id");
                String studentName = rs.getString("student_name");
                String userName = rs.getString("username");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String roomNumber = rs.getString("room_number");
                String guardianContact = rs.getString("guardian_contact");

               
                model.addRow(new Object[]{ studentID, studentName, userName, phoneNumber, email, roomNumber, guardianContact});
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching student details: " + e.getMessage());
        }
    }
        private void updateStudentDetails() {
    int selectedRowIndex = allot_table.getSelectedRow();
    if (selectedRowIndex == -1) {
        JOptionPane.showMessageDialog(null, "Please select a student to update.");
        return;
    }
    
    String updatedStudentID = student_id.getText(); 
    String studentName = student_name.getText();
    String userName = username.getText();
    String phoneNumber = phone_number.getText();
    String email = Email.getText();
    String roomNumber = room_number.getText();
    String guardianContact = guardian_contact.getText();
    
   int studentIDToUpdate = Integer.parseInt((String) allot_table.getValueAt(selectedRowIndex, 0));

    
    try {
        Connection conn = ConnectDB.getCon();
        PreparedStatement pstmt = conn.prepareStatement("UPDATE students SET  student_name=?, username=?, phone_number=?, email=?, room_number=?, guardian_contact=? WHERE student_id=?");
       
        pstmt.setString(1, studentName);
        pstmt.setString(2, userName);
        pstmt.setString(3, phoneNumber);
        pstmt.setString(4, email);
        pstmt.setString(5, roomNumber);
        pstmt.setString(6, guardianContact);
        pstmt.setInt(7, studentIDToUpdate);

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Student details updated successfully.");
            displayStudentDetails(); 
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update student details.");
        }

        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error updating student details: " + e.getMessage());
    }
}
        private void deleteStudent() {
    int selectedRowIndex = allot_table.getSelectedRow();
    if (selectedRowIndex == -1) {
        JOptionPane.showMessageDialog(null, "Please select a student to delete.");
        return;
    }
    
    int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.YES_OPTION) {
        String studentIDToDelete = (String) allot_table.getValueAt(selectedRowIndex, 0);
        try {
            Connection conn = ConnectDB.getCon();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM students WHERE student_id = ?");
            pstmt.setString(1, studentIDToDelete);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Student deleted successfully.");
                DefaultTableModel model = (DefaultTableModel) allot_table.getModel();
                model.removeRow(selectedRowIndex); // Remove the deleted row from the table
                clearFields(); 
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete student.");
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting student: " + e.getMessage());
        }
    }
}




  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_close = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        allot_table = new javax.swing.JTable();
        student_id = new javax.swing.JTextField();
        student_name = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        phone_number = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        room_number = new javax.swing.JTextField();
        guardian_contact = new javax.swing.JTextField();
        btn_allot = new javax.swing.JButton();
        btn_update_student_details = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        room_table = new javax.swing.JTable();
        btn_booking = new javax.swing.JButton();
        update_room = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        btn_left = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(500, 600));

        btn_close.setBackground(new java.awt.Color(255, 0, 51));
        btn_close.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_close.setForeground(new java.awt.Color(255, 255, 255));
        btn_close.setText("X");
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });

        jLabel1.setText("Name");

        jLabel2.setText("Student ID ");

        jLabel3.setText("Phone Number");

        jLabel4.setText("Username");

        jLabel5.setText("Email");

        jLabel6.setText("Room Number");

        jLabel7.setText("Gurdian Contact");

        allot_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Name", "Username", "Phone Number", "Email", "Room Number", "Guardian Contact"
            }
        ));
        jScrollPane1.setViewportView(allot_table);

        student_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_idActionPerformed(evt);
            }
        });

        student_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_nameActionPerformed(evt);
            }
        });

        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        phone_number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phone_numberActionPerformed(evt);
            }
        });

        Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailActionPerformed(evt);
            }
        });

        room_number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                room_numberActionPerformed(evt);
            }
        });

        guardian_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardian_contactActionPerformed(evt);
            }
        });

        btn_allot.setBackground(new java.awt.Color(153, 255, 153));
        btn_allot.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_allot.setText("Allot");
        btn_allot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_allotActionPerformed(evt);
            }
        });

        btn_update_student_details.setBackground(new java.awt.Color(255, 255, 102));
        btn_update_student_details.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_update_student_details.setText("Update");
        btn_update_student_details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_student_detailsActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(255, 102, 102));
        btn_delete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        room_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Room_Number", "Status", "Booking Status"
            }
        ));
        jScrollPane2.setViewportView(room_table);

        btn_booking.setBackground(new java.awt.Color(153, 255, 153));
        btn_booking.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_booking.setText("Update Booking");
        btn_booking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bookingActionPerformed(evt);
            }
        });

        jLabel8.setText("Room Number");

        btn_back.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_back.setText("Back");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        btn_left.setBackground(new java.awt.Color(204, 204, 255));
        btn_left.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_left.setText("Left");
        btn_left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_leftActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_close))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(phone_number, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(room_number, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(student_name, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(student_id, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(guardian_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btn_back)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_allot)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_update_student_details)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_delete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton4))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(122, 122, 122)
                                        .addComponent(btn_booking))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(update_room, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_left))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_close)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_booking)
                            .addComponent(update_room, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(student_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(student_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(phone_number, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(room_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(guardian_contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_allot)
                    .addComponent(btn_update_student_details)
                    .addComponent(btn_delete)
                    .addComponent(jButton4)
                    .addComponent(btn_left))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btn_closeActionPerformed

    private void student_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_idActionPerformed

    private void student_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_nameActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void phone_numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phone_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phone_numberActionPerformed

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailActionPerformed

    private void room_numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_room_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_room_numberActionPerformed

    private void guardian_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardian_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardian_contactActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        clearFields();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_allotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_allotActionPerformed
allotStudent();        // TODO add your handling code here:
  
    }//GEN-LAST:event_btn_allotActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        deleteStudent();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_bookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bookingActionPerformed
        // TODO add your handling code here:
        String roomToUpdate = update_room.getText(); // Get the room number to update
    String newBookingStatus = "BOOKED"; // Set the new booking status

    try {
        Connection conn = ConnectDB.getCon();
        PreparedStatement pstmt = conn.prepareStatement("UPDATE manage_room SET status = ? WHERE roomNumber = ?");
        pstmt.setString(1, newBookingStatus);
        pstmt.setString(2, roomToUpdate);

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Room booking status updated successfully.");
            displayRoomDetails(); // Update the displayed room details
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update room booking status.");
        }

        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error updating room booking status: " + e.getMessage());
    }
    }//GEN-LAST:event_btn_bookingActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        setVisible(false);
       new adminstudent().setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_update_student_detailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_student_detailsActionPerformed
updateStudentDetails();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_update_student_detailsActionPerformed

    private void btn_leftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_leftActionPerformed
        // TODO add your handling code here:
        leftStudent();
    }//GEN-LAST:event_btn_leftActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AllotStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllotStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllotStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllotStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllotStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JTable allot_table;
    private javax.swing.JButton btn_allot;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_booking;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_left;
    private javax.swing.JButton btn_update_student_details;
    private javax.swing.JTextField guardian_contact;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField phone_number;
    private javax.swing.JTextField room_number;
    private javax.swing.JTable room_table;
    private javax.swing.JTextField student_id;
    private javax.swing.JTextField student_name;
    private javax.swing.JTextField update_room;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables

}
