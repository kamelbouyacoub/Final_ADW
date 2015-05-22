package dao;

import film.NoteFilm;

public class DAONoteFilmJPA extends DAOJPA implements DAONoteFilm {

	static private DAONoteFilmJPA instance = null;

	private DAONoteFilmJPA() {
	}

	static public DAONoteFilmJPA getInstance() {
		if (instance == null)
			instance = new DAONoteFilmJPA();
		return instance;
	}

	@Override
	public void save(NoteFilm note) {
		// if(note.getIdNotaFilm()!=-1)
		// throw new IllegalArgumentException("Note Film existant!!!");
		DAOJPA.getManager().persist(note);
		DAOJPA.commit(); /* discutable de commiter ici */

	}

	@Override
	public double getMoyenne(int code) {
		try {
			double avg = (double) DAOJPA
					.getManager()
					.createQuery(
							"SELECT AVG(n.note)  FROM NoteFilm n WHERE n.filmNote.idFilm = \'"
									+ code + "\'").getSingleResult();
			return avg;
		} catch (Exception e) {

		}
		return 0;

	}

}
