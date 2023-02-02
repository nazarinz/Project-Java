import java.sql.DriverManager;

public class ShowData extends Connect {
    static int nim;

    // Method Menampilkan data mahasiswa yang telah mengambil sks
    public void showMhs() {
        int i = 0;
        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);
            state = con.createStatement();
            String query = "select * from tbl_mahasiswa";
            rs = state.executeQuery(query);

            while (rs.next()) {
                i++;
                System.out.println("-------------------------------------------");
                System.out.println("No : " + i);
                System.out.println("NIM : " + rs.getInt("nim"));
                System.out.println("Nama : " + rs.getString("nama"));
                System.out.println("-------------------------------------------");
            }
            Menu menu = new Menu();
            menu.menu();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method Menampilkan MataKuliah
    public void showMatkul() {
        int i = 0;
        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);
            state = con.createStatement();
            String query = "select * from tbl_matakuliah";
            rs = state.executeQuery(query);

            while (rs.next()) {
                i++;
                System.out.println("No : " + i);
                System.out.println("Kode Matakuliah : " + rs.getInt("id_matkul"));
                System.out.println("Nama Matakuliah : " + rs.getString("nama_matkul"));
                System.out.println("Besaran SKS : " + rs.getInt("sks"));
                System.out.println("-------------------------------------------");
            }
            Menu menu = new Menu();
            menu.menu();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showdetail() {
        int i = 0;

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);
            state = con.createStatement();

            String query = "select * from tbl_detailkrs where nim = (?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, nim);
            rs = ps.executeQuery();

            int jumlah = 0;
            int jumlahnya = 0;
            while (rs.next()) {
                i++;
                System.out.println("No : " + i);
                System.out.println("ID Pengambilan : " + rs.getInt("id_pengambilan"));
                System.out.println("NIM : " + rs.getInt("nim"));
                System.out.println("Nama : " + rs.getString("nama"));
                System.out.println("ID Matakuliah : " + rs.getInt("id_matkul"));
                System.out.println("Jumlah SKS : " + rs.getInt("jumlah_sks"));
                System.out.println("-------------------------------------------");
                jumlah = rs.getInt("jumlah_sks");
                jumlahnya += jumlah;
            }
            System.out.println("Jumlah SKS : " + jumlahnya);

            Menu menu = new Menu();
            menu.menu();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
