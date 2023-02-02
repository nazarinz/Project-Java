import java.util.Scanner;
import java.sql.DriverManager;

public class Login extends Connect {
    Scanner sc = new Scanner(System.in);

    public void regis() {
        Login login = new Login();
        InsertData regisMaba = new InsertData();

        System.out.println("--------------------------------");
        System.out.println("Sistem Pengambilan SKS Mahasiswa");
        System.out.println("--------------------------------");
        System.out.println("1. Login");
        System.out.println("2. Daftar");
        System.out.println("3. Keluar");
        System.out.print("Pilih Menu : ");
        int input = sc.nextInt();

        if (input == 1) {
            login.toLogin();
        } else if (input == 2) {
            regisMaba.registMhs();
            login.regis();
        } else if (input == 3) {
            System.exit(0);
        } else {
            System.out.println("Input tidak valid");
        }
    }

    public void toLogin() {
        System.out.println("--------------------------------");
        System.out.println("Silakan Login terlebih dahulu");
        System.out.println("--------------------------------");
        System.out.print("NIM : ");
        int nim = sc.nextInt();
        System.out.print("Nama : ");
        String nama = sc.next();
        System.out.print("Password : ");
        String ypass = sc.next();

        ShowData.nim = nim;
        InsertData.nim = nim;
        InsertData.nama = nama;
        DeleteData.nim = nim;
        UpdateData.nim = nim;

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM tbl_mahasiswa WHERE nim = ? AND nama = ? AND ypass = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, nim);
            ps.setString(2, nama);
            ps.setString(3, ypass);
            rs = ps.executeQuery();

            if (rs.next()) {
                Menu menu = new Menu();
                menu.menu();
            } else {
                System.out.println("Data yang kamu masukan salah / Kamu Belum Terdaftar Menjadi Mahasiswa");
                Login login = new Login();
                login.regis();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
