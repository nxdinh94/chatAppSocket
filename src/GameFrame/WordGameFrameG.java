
package GameFrame;

import JDBC.DAO;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class WordGameFrameG extends javax.swing.JFrame {
    ArrayList<String> listWord ;
   
    public WordGameFrameG() {
        initComponents();
        DefaultListModel model = new DefaultListModel();
        this.listWord=  new ArrayList<>();
        this.listWord=new DAO().listWord();
        for(String o : listWord){
            model.addElement(o);
        }
        jList.setModel(model);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();

        jList.setBackground(new java.awt.Color(248, 134, 172));
        jList.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "1 2" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jList);

        jLabel1.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel1.setText("Word");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(211, 211, 211))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


}
