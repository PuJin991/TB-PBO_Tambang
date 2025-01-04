// File: crud/Inventaris.java
package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import models.*;
import utils.Database;

public class Inventaris implements TambahData, LihatData, UpdateData, HapusData, Perhitungan {
    private final TreeMap<String, BarangTambang> dataBarang = new TreeMap<>();
    private final Scanner scanner = new Scanner(System.in);

   
public void tambahData() {
    System.out.println("Pilih jenis barang:");
    System.out.println("1. Emas\n2. Batu Bara\n3. Besi\n4. Timah\n5. Perak\n6. Nikel");
    System.out.print("Pilih opsi (1-6): ");
    int pilihan = scanner.nextInt();
    System.out.print("Masukkan ID Barang: ");
    String id = scanner.next();
    System.out.print("Masukkan Harga per Kg: ");
    double harga = scanner.nextDouble();
    System.out.print("Masukkan Jumlah Kg: ");
    double jumlah = scanner.nextDouble();

    String nama = switch (pilihan) {
        case 1 -> "Emas";
        case 2 -> "Batu Bara";
        case 3 -> "Besi";
        case 4 -> "Timah";
        case 5 -> "Perak";
        case 6 -> "Nikel";
        default -> null;
    };

    if (nama == null) {
        System.out.println("Pilihan tidak valid.");
        return;
    }

    try (Connection connection = Database.getConnection()) {
        String query = "INSERT INTO barang_tambang (id, nama, harga_per_kg, jumlah_kg) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, nama);
        statement.setDouble(3, harga);
        statement.setDouble(4, jumlah);
        statement.executeUpdate();
        System.out.println("Data berhasil ditambahkan!");
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    
    public void lihatData() {
    try (Connection connection = Database.getConnection()) {
        String query = "SELECT * FROM barang_tambang";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        System.out.println("=== Data Barang Tambang ===");
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getString("id"));
            System.out.println("Nama: " + resultSet.getString("nama"));
            System.out.println("Harga/Kg: Rp. " + resultSet.getDouble("harga_per_kg"));
            System.out.println("Jumlah: " + resultSet.getDouble("jumlah_kg") + " Kg");
            System.out.println("---------------------------");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

public void updateData() {
    System.out.print("Masukkan ID Barang yang ingin diupdate: ");
    String id = scanner.next();
    System.out.print("Masukkan Jumlah Kg Baru: ");
    double jumlahBaru = scanner.nextDouble();

    try (Connection connection = Database.getConnection()) {
        String query = "UPDATE barang_tambang SET jumlah_kg = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, jumlahBaru);
        statement.setString(2, id);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Data berhasil diupdate!");
        } else {
            System.out.println("Data tidak ditemukan.");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}


    public void hapusData() {
        System.out.print("Masukkan ID Barang yang ingin dihapus: ");
        String id = scanner.next();
    
        try (Connection connection = Database.getConnection()) {
            String query = "DELETE FROM barang_tambang WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data berhasil dihapus!");
            } else {
                System.out.println("Data tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void hitungTotal() {
        double total = dataBarang.values().stream().mapToDouble(BarangTambang::totalHarga).sum();
        System.out.println("Total nilai barang tambang: Rp. " + total);
    }
}
