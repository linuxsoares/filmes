package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result sobre(){
    	return ok(views.html.sobre.render("", play.core.PlayVersion.current()));
    	
    }
    
}
