import java.sql.DriverManager;
import java.util.Scanner;

public class DeleteData extends Connect {
    static int nim;
    Scanner sc = new Scanner(System.in);

    public void keluar() {
        System.out.print("Apakah Anda benar benar yakin ? (ya/tidak) ");
        String input = sc.nextLine();

        if (input.equalsIgnoreCase("ya")) {
            try {
                Class.forName(jdbc);
                con = DriverManager.getConnection(url, username, password);
                String query = "delete from tbl_mahasiswa where nim = (?) ";
                ps = con.prepareStatement(query);
                ps.setInt(1, nim);

                if (ps.executeUpdate() > 0) {
                    System.out.println("Mengundurkan diri Berhasil, Dadah...");
                } else {
                    System.out.println("Data tidak ditemukan");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (input.equalsIgnoreCase("tidak")) {
            System.out.println("Semangat yaa kuliahnya jangan ngundurin diri.");
        } else {
            System.out.println("Input tidak valid.");
        }
    }
}