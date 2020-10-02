/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author LENOVO
 */
public class Contacto {
    private int pk_contacto,fk_sexo,fk_provincia;
    private String nome_contacto,tipo_sexo,telefone,nomeProvincia;
    
    public Contacto(){
        
    }
    
    
    public static boolean isValid(String nome_contacto, String telefone){
        /*if(nome_contacto == null || telefone == null) return false;
        if(nome_contacto.isEmpty() || telefone.isEmpty()) return false;*/
        return false;
    }
    
    public Contacto(int pk_contacto, String nome_contacto, int fk_sexo, String telefone, int fk_provincia){
        this.pk_contacto = pk_contacto;
        this.nome_contacto = nome_contacto;
        this.fk_sexo = fk_sexo;
        this.telefone = telefone;   
        this.fk_provincia = fk_provincia;
    }
    
    public Contacto(String nome_contacto, int fk_sexo, String telefone, int fk_provincia){
        this.nome_contacto = nome_contacto;
        this.fk_sexo = fk_sexo;
        this.telefone = telefone;   
        this.fk_provincia = fk_provincia;
    }
    
    public int getPk_contacto() {
        return pk_contacto;
    }

    public void setPk_contacto(int pk_contacto) {
        this.pk_contacto = pk_contacto;
    }

    public int getFk_sexo() {
        return fk_sexo;
    }

    public void setFk_sexo(int fk_sexo) {
        this.fk_sexo = fk_sexo;
    }

    public int getFk_provincia() {
        return fk_provincia;
    }

    public void setFk_provincia(int fk_provincia) {
        this.fk_provincia = fk_provincia;
    }

    public String getNome_contacto() {
        return nome_contacto;
    }

    public void setNome_contacto(String nome_contacto) {
        this.nome_contacto = nome_contacto;
    }

    public String getTipo_sexo() {
        return tipo_sexo;
    }

    public void setTipo_sexo(String tipo_sexo) {
        this.tipo_sexo = tipo_sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia) {
        this.nomeProvincia = nomeProvincia;
    }
}
