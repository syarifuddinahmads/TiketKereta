/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author USER
 */
public class Kereta {
    private int id_kereta;
    private String nama_kereta;
    private String jadwal;
    private int nomor_kursi;

    public int getId_kereta() {
        return id_kereta;
    }

    public void setId_kereta(int id_kereta) {
        this.id_kereta = id_kereta;
    }

    public String getNama_kereta() {
        return nama_kereta;
    }

    public void setNama_kereta(String nama_kereta) {
        this.nama_kereta = nama_kereta;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public int getNomor_kursi() {
        return nomor_kursi;
    }

    public void setNomor_kursi(int nomor_kursi) {
        this.nomor_kursi = nomor_kursi;
    }
    
    
}
