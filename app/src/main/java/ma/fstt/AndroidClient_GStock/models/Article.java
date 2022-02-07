package ma.fstt.AndroidClient_GStock.models;


public class Article {

    private Long id;
    private String libelle;
    private double pu;
    private long id_ctg;
    public Article(double pu,long id_ctg,String libelle) {
        this.libelle = libelle;
        this.pu = pu;
        this.id_ctg = id_ctg;
    }
    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPu() {
        return pu;
    }

    public long getId_ctg() {
        return id_ctg;
    }

    public void setId_ctg(long id_ctg) {
        this.id_ctg = id_ctg;
    }

    public void setPu(double pu) {
         this.pu= pu;
    }
}
