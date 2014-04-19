package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by gilmar on 18/04/14.
 */

@Entity
public class Filme extends Model{

    @Id
    public Long id;
    public String nome;
    public Long tipo;
    public Long nota;
    public Long duracao;
    public Long ano;
    public String genero;
    public Long votos;
    public String url;
    public Long diretor_id;

    public static Model.Finder<Long, Filme> find;

    static {
        find = new Finder<Long, Filme>(Long.class, Filme.class);
    }

}
