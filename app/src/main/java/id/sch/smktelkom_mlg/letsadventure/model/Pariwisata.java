package id.sch.smktelkom_mlg.letsadventure.model;

/**
 * Created by Rehan on 26/04/2017.
 */

public class Pariwisata {

    private String _id, _rev, judul_wisata, lokasi, deskripsi, detail_wisata, gambar, koordinat;

    public Pariwisata(String _id, String _rev, String judul_wisata, String lokasi, String deskripsi, String detail_wisata, String gambar, String koordinat) {
        this._id = _id;
        this._rev = _rev;
        this.judul_wisata = judul_wisata;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
        this.detail_wisata = detail_wisata;
        this.gambar = gambar;
        this.koordinat = koordinat;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public String getJudul_wisata() {
        return judul_wisata;
    }

    public void setJudul_wisata(String judul_wisata) {
        this.judul_wisata = judul_wisata;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDetail_wisata() {
        return detail_wisata;
    }

    public void setDetail_wisata(String detail_wisata) {
        this.detail_wisata = detail_wisata;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }
}
