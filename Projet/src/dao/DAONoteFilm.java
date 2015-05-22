package dao;

import film.NoteFilm;

public interface DAONoteFilm {	
	void save(NoteFilm  note);	
	double getMoyenne(int code);

}
