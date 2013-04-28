package models;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.google.common.base.Optional;

@Entity
public class Archive extends Model {

	private static final long serialVersionUID = 7253775925342170801L;

	@Id
	public Long id;

	@Required
	public String title;

	@Required
	public String path;

	public static Finder<Long, Archive> finder = new Finder<Long, Archive>(
			Long.class, Archive.class);

	public static List<Archive> all() {
		return finder.all();
	}

	public static Archive get(Long id) {
		return finder.byId(id);
	}

	public static void create(Archive archive) {
		archive.save();
	}

	public static void delete(Long id) {
		finder.ref(id).delete();
	}

	public Optional<String> filename() {
		if (isNullOrEmpty(path))
			return Optional.absent();

		String filename = path.substring(path.lastIndexOf('/') + 1);
		return Optional.of(filename);
	}
}
