package mele.fangbemi.example.edith.entity;

public enum TypeCompte {
    COURANT("COURANT"),
    EPARGNE("EPARGNE");

    private final String type;

    TypeCompte(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
