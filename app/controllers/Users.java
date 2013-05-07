package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Users extends Controller {

	static Form<User> userForm = Form.form(User.class);

	public static Result index() {
		return ok(views.html.users.index.render(User.findAll()));
	}

	public static Result show(String email) {
		return ok(views.html.users.show.render(User.findByEmail(email)));
	}

	public static Result delete(String email) {
		User.remove(email);
		flash("success", "Successfully deleted.");
		return redirect(routes.Users.index());
	}

	public static Result newUser() {
		return ok(views.html.users.newuser.render(userForm));
	}

	public static Result create() {
		Form<User> filledForm = userForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.users.newuser.render(filledForm));
		} else {
			User.create(filledForm.get());
			flash("success", "Successfully added.");
			return redirect(routes.Users.newUser());
		}
	}

	public static Result login() {
		return ok(views.html.users.login.render(userForm));
	}

	public static Result authenticate() {
		Form<User> filledForm = userForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.users.login.render(filledForm));
		} else {
			return ok(filledForm.get().toString());
		}
	}
}
