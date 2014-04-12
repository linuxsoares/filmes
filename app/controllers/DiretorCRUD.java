package controllers;

import java.util.List;

import models.Diretor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;


public class DiretorCRUD extends Controller{
	
	private static final Form<Diretor> diretorForm = Form.form(Diretor.class);
	
	public static Result lista() {
		List<Diretor> diretores = Diretor.find.findList();
		return ok(views.html.diretor.render(diretores));
	}
	
	public static Result novoDiretor(){
		if (diretorForm.hasErrors()) {
		    flash("erro","Foram identificados problemas no cadastro");
		    return ok(views.html.novoDiretor.render(diretorForm));
		}
		return ok(views.html.novoDiretor.render(diretorForm));
	}
	
	public static Result gravar(){
		Form<Diretor> form = diretorForm.bindFromRequest();
		Diretor diretor = form.get();
		diretor.save();
		flash("sucesso","Registro gravado com sucesso");
		return redirect(routes.DiretorCRUD.lista());
	}
	
	public static Result detalhar(Long id){
		Form<Diretor> dirForm = diretorForm.fill(Diretor.find.byId(id));
		return ok(views.html.alterarDiretor.render(id, dirForm));
	}
	
	public static Result alterar(Long id){
		diretorForm.fill(Diretor.find.byId(id));
	    Form<Diretor> alterarForm = diretorForm.bindFromRequest();
	    if (alterarForm.hasErrors()) {
	    	return badRequest(views.html.alterarDiretor.render(id,alterarForm));
	    }
	    alterarForm.get().update(id);
	    flash("sucesso","Diretor "+ alterarForm.get().nome + " alterado com sucesso");
	    
	    return redirect(routes.DiretorCRUD.lista());
	}
	
	public static Result remover(Long id) {
	    Diretor.find.ref(id).delete();
	    flash("sucesso","Diretor removido com sucesso");
	    return lista();
	}
}
