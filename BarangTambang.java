// File: models/BarangTambang.java
package models;

public abstract class BarangTambang {
    protected String id;
    protected String nama;
    protected double hargaPerKg;
    protected double jumlahKg;

    public BarangTambang(String id, String nama, double hargaPerKg, double jumlahKg) {
        this.id = id;
        this.nama = nama;
        this.hargaPerKg = hargaPerKg;
        this.jumlahKg = jumlahKg;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHargaPerKg() {
        return hargaPerKg;
    }

    public double getJumlahKg() {
        return jumlahKg;
    }

    public void setJumlahKg(double jumlahKg) {
        this.jumlahKg = jumlahKg;
    }

    public double totalHarga() {
        return hargaPerKg * jumlahKg;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNama: " + nama + "\nHarga/Kg: Rp. " + hargaPerKg + "\nJumlah: " + jumlahKg + " Kg";
    }
}
