package models;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class ArchiveTest {

	@Test
	public void testFilename() {
		Archive a = new Archive();

		a.path = "public/archives/1.zip";
		assertThat(a.filename().get()).isEqualTo("1.zip");

		a.path = "1.zip";
		assertThat(a.filename().get()).isEqualTo("1.zip");

		a.path = null;
		assertThat(a.filename().isPresent()).isFalse();
	}

}
