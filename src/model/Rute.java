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
public class Rute {
    private int id_rute;
    private String tujuan; 
    private String stasiun_persinggahan;

    public int getId_rute() {
        return id_rute;
    }

    public void setId_rute(int id_rute) {
        this.id_rute = id_rute;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getStasiun_persinggahan() {
        return stasiun_persinggahan;
    }

    public void setStasiun_persinggahan(String stasiun_persinggahan) {
        this.stasiun_persinggahan = stasiun_persinggahan;
    }
    
    
}
