import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;

public class View extends JFrame implements MenuListener {
    JButton btnTiket = new JButton("BELI TIKET");
    JButton btnKereta = new JButton("DAFTAR KERETA");
    JButton btnCari = new JButton("Cari");
    JButton btnBeli = new JButton("Beli");
    JButton btnHapus = new JButton("Hapus");
    JButton btnEdit = new JButton("Edit");

    JLabel judul = new JLabel("KERETA API DARI STATIUN TUGU YOGYAKARTA");
    JLabel judul2 = new JLabel("PEMBELIAN TIKET");
    //JLabel welcome = new JLabel("123180169");
    JLabel namaC = new JLabel("Nama Pembeli");
    JLabel jK = new JLabel("Jenis Kelamin");
    JLabel idBeli = new JLabel("ID Kereta");
    JLabel staTjn = new JLabel("Stasiun Tujuan");

    String stasiun[] = {"Bandung", "Surabaya", "Malang"};
    public final JComboBox cb = new JComboBox(stasiun);

    JTextField tfNamaC = new JTextField();
    JTextField tfIdBeli = new JTextField();

    JRadioButton rbL = new JRadioButton("L");
    JRadioButton rbP = new JRadioButton("P");

    JMenu menu1, menu2, menu3;
    JMenuBar mb = new JMenuBar();

    //JPanel
    JPanel panel = new JPanel();
    JPanel pan = new JPanel();

    //JTabel 1
    JTable tabel;
    DefaultTableModel tableModel; // untuk tabel
    JScrollPane scrollPane;  // untuk scroll
    Object namaKolom[] = {"ID", "Kereta", "Tujuan", "Kelas"}; // membuat kolom dalam array

    //JTabel 2
    JTable tabel2;
    DefaultTableModel tableModel2; // untuk tabel
    JScrollPane scrollPane2;  // untuk scroll
    Object namaKolom2[] = {"ID", "Nama Pembeli", "Jenis Kelamin", "ID Kereta"};

    public View(){
        // bikin scrollpane + tabel 1
        tableModel = new DefaultTableModel(namaKolom, 0); //0 baris
        tabel = new JTable(tableModel);
        scrollPane = new JScrollPane(tabel);
        // bikin scrollpane + tabel 2
        tableModel2 = new DefaultTableModel(namaKolom, 0); //0 baris
        tabel2 = new JTable(tableModel);
        scrollPane2 = new JScrollPane(tabel2);
        //menu bar, menu, submenu
        menu1 = getReadMenu();
        menu1.addMenuListener( this); // ada action jika diklik
        mb.add(menu1); // masukin menu1 ke menubar
        menu2 = getTambahMenu();
        menu2.addMenuListener( this); // ada action jika diklik
        mb.add(menu2); // masukin menu2 ke menubar

//        welcome.setBounds(100,50,100,30);
//        add(welcome);

        //make jframe visible
        setJMenuBar(mb);
        setSize(500, 500);
        setVisible(true);
    }
    // bikin menu
    private JMenu getReadMenu(){
        menu1 = new JMenu("Beli Tiket");
        return menu1;
    }
    private JMenu getTambahMenu(){
        menu2 = new JMenu("Daftar Kereta");
        return menu2;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        if(e.getSource()==menu1){
            //hapus panel menu Beli Tiket
            panel.setVisible(true);
            remove(pan);
            pan.setVisible(false);
            revalidate(); repaint();

            //bikin panel baru
            judul2.setBounds(90,10,300,20);
            namaC.setBounds(30,40,120,20);
            tfNamaC.setBounds(150,40,120,20);
            jK.setBounds(30,70,100,20);
            rbL.setBounds(150,70,50,20);
            rbP.setBounds(210,70,500,20);
            idBeli.setBounds(30,100,100,20);
            tfIdBeli.setBounds(150,100,120,20);
            btnBeli.setBounds(150,130,80,20);
            btnEdit.setBounds(240,130,80,20);
            btnHapus.setBounds(330,130,80,20);
            panel.add(judul2);
            panel.add(namaC);
            panel.add(tfNamaC);
            panel.add(jK);
            panel.add(rbL);
            panel.add(rbP);
            panel.add(idBeli);
            panel.add(tfIdBeli);
            panel.add(btnBeli);
            panel.add(btnEdit);
            panel.add(btnHapus);

            // TABEL
            scrollPane2.setBounds(30, 180, 400, 200);
            scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panel.add(scrollPane2);
            setBounds(0, 0, 500, 500);
            panel.setBounds(0,0,500,500);

            //munculin panel ke jframe
            add(panel);
        }
        else if(e.getSource()==menu2){
            //hapus panel menu Daftar Tiket
            pan.setVisible(true);
            remove(panel);
            panel.setVisible(false);
            revalidate(); repaint();

            //bikin panel baru
            judul.setBounds(90,10,300,20);
            pan.add(judul);
            staTjn.setBounds(70,50,120,20);
            pan.add(staTjn);
            cb.setBounds(190,50,90,20);
            pan.add(cb);
            btnCari.setBounds(290,50,80,20);
            pan.add(btnCari);
            setBounds(0, 0, 500, 500);
            pan.setBounds(0,0,500,500);

            // TABEL
            //scrollPane.setVisible(false);
            scrollPane.setBounds(30, 100, 400, 200);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            pan.add(scrollPane);

            //munculin panel ke jframe
            add(pan);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {
    }
    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
