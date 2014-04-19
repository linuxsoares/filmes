package controllers;

import java.util.List;

import models.Diretor;
import models.Filme;
import play.api.mvc.Result;
import play.mvc.Controller;
import play.data.Form;

/**
 * Created by gilmar on 18/04/14.
 */
public class FilmeCRUD extends Controller{

    private static final Form<Filme> filmeForm = Form.form(Filme.class);

    public static Result lista(){
        List<Filme> filmes = Filme.find.where().orderBy("ano").findList();
        return ok(views.html.filme.render(filmes));
    }

    public static Result gravar() {

        Form<Filme> form = filmeForm.bindFromRequest();
        if (form.hasErrors()) {

            flash("erro","Foram identificados problemas no cadastro de filme");

            List<Diretor> diretores = Diretor.find.findList();

            return ok(views.html.novoFilme.render(filmeForm,diretores));
        }

        form.get().save();

        flash("sucesso","Registro gravado com sucesso");

        return redirect(routes.FilmeCRUD.lista());

    }

    public static Result remover(Long id) {
        Filme.find.ref(id).delete();
        flash("sucesso","Filme removido com sucesso");
        return lista();
    }

}
