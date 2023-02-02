import java.util.Scanner;
import java.sql.DriverManager;
import java.util.Random;

public class InsertData extends Connect {
    Scanner sc = new Scanner(System.in);
    Random randomkeun = new Random();
    static int nim;
    static String nama;

    public void registMhs() {

        int z = randomkeun.nextInt(1000);
        System.out.print("NIM : ");
        int nim = sc.nextInt();
        System.out.print("Nama : ");
        String nama = sc.next();
        System.out.print("Password : ");
        String ypass = sc.next();

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);
            String query = "insert into tbl_mahasiswa values(?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, nim);
            ps.setString(2, nama);
            ps.setString(3, ypass);

            if (ps.executeUpdate() > 0) {
                System.out.println("--------------------------------");
                System.out.println("Pendaftaran Berhasil");
                System.out.println("Ini Data untuk login kamu, mohon diingat ya!");
                System.out.println("--------------------------------");
                System.out.println("NIM : " + nim);
                System.out.println("Nama : " + nama);
                System.out.println("Password : " + ypass);
                System.out.println("--------------------------------");

            } else {
                System.out.println("Pendaftaran gagal");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertSks() {
        int i = 0;
        int z = randomkeun.nextInt(10000);

        try {
            System.out.print("Semester Berapa Kamu Sekarang ? : ");
            int smt = sc.nextInt();
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);
            if (!cekSmt(smt)) {
                return;
            }
            String querySemester = "select * from tbl_matakuliah where matkul_smt like ?";
            ps = con.prepareStatement(querySemester);
            ps.setString(1, "%" + smt + "%");
            rs = ps.executeQuery();

            System.out.println("--------------------------------");
            System.out.println("Daftar Matakuliah yang bisa kamu ambil di Semester " + smt + " adalah : ");
            System.out.println("--------------------------------");

            while (rs.next()) {
                i++;
                System.out.println("No : " + i);
                System.out.println("ID Matakuliah : " + rs.getString("id_matkul"));
                System.out.println("Nama Matakuliah : " + rs.getString("nama_matkul"));
                System.out.println("Besaran SKS : " + rs.getInt("sks"));
                System.out.println("-------------------------------------------");
            }

            System.out.println("--------------------------------");
            System.out.println("Form Pengambilan SKS");
            System.out.println("--------------------------------");
            System.out.print("Masukan ID Matkul : ");
            int id_matkul = sc.nextInt();
            System.out.print("Jumlah SKS : ");
            int jumlah_sks = sc.nextInt();

            String queryKrs = "insert into tbl_detailkrs values(?,?,?,?,?)";

            ps = con.prepareStatement(queryKrs);
            ps.setInt(1, z);
            ps.setInt(2, nim);
            ps.setString(3, nama);
            ps.setInt(4, id_matkul);
            ps.setInt(5, jumlah_sks);

            if (ps.executeUpdate() > 0) {
                System.out.println("--------------------------------");
                System.out.println("Pengambilan KRS berhasil, kuliah yang rajin yaa :D");
            } else {
                System.out.println("Proses Insert gagal");
            }
            Menu menu = new Menu();
            menu.menu();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean cekSmt(int smt) {

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);

            String querySemester = "select * from tbl_matakuliah where matkul_smt like ?";
            ps = con.prepareStatement(querySemester);
            ps.setString(1, "%" + smt + "%");
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
            System.out.println("Maaf untuk Semester " + smt + " Belum tersedia ");
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
