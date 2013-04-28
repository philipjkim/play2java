package controllers;

import java.io.File;

import models.Archive;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Archives extends Controller {

	static Form<Archive> archiveForm = Form.form(Archive.class);

	public static Result index() {
		return ok(views.html.archives.render(Archive.all(), archiveForm));
	}

	public static Result show(Long id) {
		String filename = Archive.get(id).path;
		response().setContentType("application/x-download");
		response().setHeader("Content-disposition","attachment; filename=" + filename);
		return ok(new File("public/archives/" + filename));
	}

	public static Result create() {
		Form<Archive> filledForm = archiveForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.archives.render(Archive.all(),
					filledForm));
		} else {
			Archive.create(filledForm.get());
			return redirect(routes.Archives.index());
		}
	}

	public static Result delete(Long id) {
		Archive.delete(id);
		flash("success", "Successfully deleted.");
		return redirect(routes.Archives.index());
	}
}
