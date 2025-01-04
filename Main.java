// File: main/Main.java
package main;

import java.util.Scanner;
import crud.Inventaris;


public class Main {
    public static void main(String[] args) {
        Inventaris inventaris = new Inventaris();
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        while (true) {
            System.out.println("=== Sistem Manajemen Barang Tambang ===");
            System.out.println("1. Tambah Barang Tambang");
            System.out.println("2. Lihat Semua Barang");
            System.out.println("3. Update Barang Tambang");
            System.out.println("4. Hapus Barang Tambang");
            System.out.println("5. Hitung Total Nilai Barang");
            System.out.println("6. Keluar");
            System.out.print("Pilih opsi (1-6): ");

            try {
                pilihan = scanner.nextInt();
                switch (pilihan) {
                    case 1 -> inventaris.tambahData();
                    case 2 -> inventaris.lihatData();
                    case 3 -> inventaris.updateData();
                    case 4 -> inventaris.hapusData();
                    case 5 -> inventaris.hitungTotal();
                    case 6 -> {
                        System.out.println("Terima kasih telah menggunakan sistem!");
                        System.exit(0);
                    }
                    default -> System.out.println("Pilihan tidak valid. Silakan coba lagi!");
                }
            } catch (Exception e) {
                System.out.println("Error: Input tidak valid! Silakan coba lagi.");
                scanner.nextLine(); // Clear input buffer
            }
        }
    }
}
