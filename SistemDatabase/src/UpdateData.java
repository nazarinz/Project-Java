import java.util.Scanner;
import java.sql.DriverManager;

public class UpdateData extends Connect {
    Scanner sc = new Scanner(System.in);
    static int nim;

    public void gantiPassword() {
        System.out.print("Masukan Password Terbaru anda : ");
        String newpass = sc.nextLine();

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);
            String query = "UPDATE tbl_mahasiswa SET ypass = ? WHERE nim = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, newpass);
            ps.setInt(2, nim);

            if (ps.executeUpdate() > 0) {
                System.out.println("Kamu Berhasil Mengganti Password");
            } else {
                System.out.println("Data tidak ditemukan");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
