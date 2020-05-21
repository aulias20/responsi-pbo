import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Control {
    Model model;
    View view;

    public Control (Model model, View view) {
        this.model = model;
        this.view = view;

        if (model.getBanyakData2() != 0) {
            String dataTiket[][] = model.readTiket();
            view.tabel2.setModel((new JTable(dataTiket, view.namaKolom2)).getModel());
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }

        view.btnCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) view.cb.getItemAt(view.cb.getSelectedIndex());
                String dataKereta[][] = model.readKereta(s);
                view.tabel.setModel(new JTable(dataKereta, view.namaKolom).getModel());
            }
        });

        view.btnBeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = view.tfNamaC.getText();
                String jk=null;
                if(view.rbL.isSelected()){
                    jk = "L";
                }
                else if(view.rbP.isSelected()){
                    jk = "P";
                }
                String id = view.tfIdBeli.getText();
                model.insertTiket(nama, jk, id); // insert data pake method di Model

                String dataTiket[][] = model.readTiket(); // masukin + nampilin insert data ke array database
                view.tabel2.setModel(new JTable(dataTiket, view.namaKolom2).getModel());
            }
        });

        view.tabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int baris = view.tabel2.getSelectedRow();
                //+-------------------------------------------+ mengambil data dari tabel ke textfield/label
                String kolom1 = (String) view.tabel2.getValueAt(baris,1);
                view.tfNamaC.setText(kolom1);
                String kolom3 = (String) view.tabel2.getValueAt(baris,3);
                view.tfIdBeli.setText(kolom3);
            }
        });

        view.btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = view.tfNamaC.getText();
                String jk=null;
                if(view.rbL.isSelected()){
                    jk = "L";
                }
                else if(view.rbP.isSelected()){
                    jk = "P";
                }
                String id = view.tfIdBeli.getText();
                model.updateTiket(nama,jk,id);
                String dataTiket[][] = model.readTiket(); // masukin + nampilin insert data ke array database
                view.tabel2.setModel(new JTable(dataTiket, view.namaKolom2).getModel());
            }
        });

        view.btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = view.tfNamaC.getText();
                model.deleteTiket(nama); // delete data with method from Model
                String dataTiket[][] = model.readTiket(); // tampilkan lagi tabel yang sudah diperbaru
                view.tabel2.setModel(new JTable(dataTiket, view.namaKolom2).getModel());
            }
        });
    }
}
