/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Kereta;
import model.Pembeli;
import model.Rute;
import model.Tiket;

/**
 *
 * @author USER
 */
public class TransaksiController {

    Koneksi koneksi;
    ArrayList<Kereta> arrKereta;
    ArrayList<Tiket> arrTiket;
    ArrayList<Rute> arrRute;

    public TransaksiController() {
        this.koneksi = new Koneksi();
        this.arrKereta = new ArrayList<>();
        this.arrTiket = new ArrayList<>();
        this.arrRute = new ArrayList<>();
    }

    public ArrayList<Tiket> getDataTiket(int idTiket) throws SQLException {
        this.arrTiket.clear();
        ResultSet rs;
        if (idTiket != 0) {
            rs = this.koneksi.GetData("select * from tiket join pembeli on pembeli.id_pembeli = tiket.id_pembeli join kereta on kereta.id_kereta = tiket.id_kereta join rute on rute.id_rute = tiket.id_rute where tiket.id_tiket =" + idTiket);
        } else {
            rs = this.koneksi.GetData("select * from tiket join pembeli on pembeli.id_pembeli = tiket.id_pembeli join kereta on kereta.id_kereta = tiket.id_kereta join rute on rute.id_rute = tiket.id_rute");
        }

        while (rs.next()) {

            Tiket t = new Tiket();
            t.setId_tiket(rs.getInt("id_tiket"));
            t.setHarga_tiket(rs.getInt("harga_tiket"));
            t.setId_kereta(rs.getInt("id_kereta"));
            t.setId_rute(rs.getInt("id_rute"));
            t.setId_pembeli(rs.getInt("id_pembeli"));
            t.setTanggal_keberangkatan(rs.getString("tanggal_keberangkatan"));

            Pembeli p = new Pembeli();
            p.setId_pembeli(rs.getInt("id_pembeli"));
            p.setNama_pembeli(rs.getString("nama_pembeli"));
            p.setEmail(rs.getString("email"));
            p.setTanggal_lahir(rs.getString("tanggal_lahir"));
            p.setNo_telp(rs.getInt("no_telp"));
            t.setPembeli(p);

            Rute r = new Rute();
            r.setId_rute(rs.getInt("id_rute"));
            r.setStasiun_persinggahan(rs.getString("stasiun_persinggahan"));
            r.setTujuan(rs.getString("tujuan"));
            t.setRute(r);

            Kereta k = new Kereta();
            k.setId_kereta(rs.getInt("id_kereta"));
            k.setJadwal(rs.getString("jadwal"));
            k.setNama_kereta(rs.getString("nama_kereta"));
            k.setNomor_kursi(rs.getInt("nomor_kursi"));
            t.setKereta(k);
            this.arrTiket.add(t);

        }
        return this.arrTiket;
    }

    public ArrayList<Rute> getDataRute(int idRute) throws SQLException {
        this.arrRute.clear();
        ResultSet rs;
        if (idRute != 0) {
            rs = this.koneksi.GetData("select * from rute where id_rute =" + idRute);
        } else {
            rs = this.koneksi.GetData("select * from rute");
        }

        while (rs.next()) {

            Rute r = new Rute();
            r.setId_rute(rs.getInt("id_rute"));
            r.setStasiun_persinggahan(rs.getString("stasiun_persinggahan"));
            r.setTujuan(rs.getString("tujuan"));

            this.arrRute.add(r);

        }
        return this.arrRute;
    }

    public ArrayList<Kereta> getDataKereta(int idKereta) throws SQLException {
        this.arrKereta.clear();
        ResultSet rs;
        if (idKereta != 0) {
            rs = this.koneksi.GetData("select * from kereta where id_kereta =" + idKereta);
        } else {
            rs = this.koneksi.GetData("select * from kereta");
        }

        while (rs.next()) {

            Kereta k = new Kereta();
            k.setId_kereta(rs.getInt("id_kereta"));
            k.setJadwal(rs.getString("jadwal"));
            k.setNama_kereta(rs.getString("nama_kereta"));
            k.setNomor_kursi(rs.getInt("nomor_kursi"));
            this.arrKereta.add(k);

        }
        return this.arrKereta;
    }

    public void deleteKereta(int id) {
        this.koneksi.ManipulasiData("delete kereta where id_kereta=" + id);
    }

    public void deleteRute(int id) {
        this.koneksi.ManipulasiData("delete rute where id_rute=" + id);
    }

    public void deleteTiket(int id) {
        this.koneksi.ManipulasiData("delete tiket where id_tiet=" + id);
    }

    public void insertTiket(Tiket t) {
        try {
            if (t.getId_tiket() > 0) {
                System.out.println("UPDATE");
                this.koneksi.ManipulasiData("UPDATE RYVANA_07014.PEMBELI SET NAMA_PEMBELI='"+t.getPembeli().getNama_pembeli()+"', TANGGAL_LAHIR='"+t.getPembeli().getTanggal_lahir()+"', EMAIL='"+t.getPembeli().getEmail()+"', NO_TELP="+t.getPembeli().getNo_telp()+" WHERE ID_PEMBELI="+t.getId_pembeli());
                
                this.koneksi.ManipulasiData("UPDATE RYVANA_07014.TIKET SET ID_KERETA="+t.getId_kereta()+", ID_RUTE="+t.getId_rute()+", ID_PEMBELI="+t.getId_pembeli()+", HARGA_TIKET="+t.getHarga_tiket()+", TANGGAL_KEBERANGKATAN='"+t.getTanggal_keberangkatan()+"' WHERE ID_TIKET="+t.getId_tiket());
                
            } else {
                System.out.println("INSERT");
                
                this.koneksi.ManipulasiData("INSERT INTO RYVANA_07014.PEMBELI VALUES(ID_PEMBELI.NEXTVAL, '"+t.getPembeli().getNama_pembeli()+"', '"+t.getPembeli().getTanggal_lahir()+"', '"+t.getPembeli().getEmail()+"', "+t.getPembeli().getNo_telp()+")");
                ResultSet rsPembeli = this.koneksi.GetData("SELECT MAX(ID_PEMBELI) AS ID_PEMBELI FROM PEMBELI");
                rsPembeli.next();
                int idPembeli = rsPembeli.getInt("ID_PEMBELI");
                System.out.println("ID_PEMBELI = "+idPembeli);
                
                this.koneksi.ManipulasiData("INSERT INTO RYVANA_07014.TIKET VALUES(ID_TIKET.NEXTVAL, "+t.getId_kereta()+", "+t.getId_rute()+", "+idPembeli+", "+t.getHarga_tiket()+", '"+t.getTanggal_keberangkatan()+"')");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertRute(Rute r) {
        try {
            if (r.getId_rute() > 0) {
                System.out.println("UPDATE");
                this.koneksi.ManipulasiData("UPDATE RYVANA_07014.RUTE SET TUJUAN='" + r.getTujuan() + "', STASIUN_PERSINGGAHAN='" + r.getStasiun_persinggahan() + "' WHERE ID_RUTE=" + r.getId_rute());
            } else {
                System.out.println("INSERT");
                this.koneksi.ManipulasiData("INSERT INTO RYVANA_07014.RUTE VALUES(ID_RUTE.NEXTVAL, '" + r.getTujuan() + "', '" + r.getStasiun_persinggahan() + "')");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertKereta(Kereta k) {
        try {
            if (k.getId_kereta() > 0) {
                System.out.println("UPDATE");
                this.koneksi.ManipulasiData("UPDATE RYVANA_07014.KERETA SET NAMA_KERETA='" + k.getNama_kereta() + "', JADWAL='" + k.getJadwal() + "', NOMOR_KURSI=" + k.getNomor_kursi() + " WHERE ID_KERETA=" + k.getId_kereta());
            } else {
                System.out.println("INSERT");
                this.koneksi.ManipulasiData("INSERT INTO RYVANA_07014.KERETA VALUES(ID_KERETA.NEXTVAL, '" + k.getNama_kereta() + "', '" + k.getJadwal() + "', " + k.getNomor_kursi() + ")");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
