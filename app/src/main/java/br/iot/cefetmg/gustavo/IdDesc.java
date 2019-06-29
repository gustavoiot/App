package br.iot.cefetmg.gustavo;

public class IdDesc {
    private String descricao;
    private int id;

    public IdDesc(String desc, int id) {
        descricao = desc;
        this.id = id;
    }

    public String getDescricao() { return descricao; }
    public int getId() { return id; }
}
