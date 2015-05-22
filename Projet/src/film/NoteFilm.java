package film;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import listes.Listes;

@Entity
@Table(name="note_film")
public class NoteFilm {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_note_film")
	private int idNotaFilm = -1;	
			
	@Column(name="note")
	private int note;
	
	@ManyToOne
	@JoinColumn(name="id_Film")	
	private Film filmNote = null;	
	
	public NoteFilm() {	super(); }	

	public NoteFilm(int note, Film film) {
		super();
		this.note = note;
		this.filmNote = film;
		Listes.add(filmNote.getLstNoteFilm(),this);
	}

	public int getIdNotaFilm() {
		return idNotaFilm;
	}
	
	public void setIdNotaFilm(int idNotaFilm) {
		this.idNotaFilm = idNotaFilm;
	}
	
	public int getNote() {
		return note;
	}
	
	public void setNote(int note) {
		this.note = note;
	}
	
	public Film getFilmNote() {
		return filmNote;
	}
	
	public void setFilmNote(Film film) {
		this.filmNote = film;
	}
	

}
