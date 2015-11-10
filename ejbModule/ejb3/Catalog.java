package ejb3;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the catalog database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Catalog.findAll", query = "SELECT c FROM Catalog c"),
		@NamedQuery(name = "Catalog.findByJournal", query = "SELECT c FROM Catalog c WHERE c.journal = :journal") })
public class Catalog implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String journal;
	private List<Edition> editions;

	public Catalog() {
	}

	@Id
	@SequenceGenerator(name = "CATALOG_ID_GENERATOR", sequenceName = "CATALOG_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATALOG_ID_GENERATOR")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJournal() {
		return this.journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	// bi-directional many-to-one association to Edition
	@OneToMany(mappedBy = "catalog", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "CATALOGEDITIONS", joinColumns = @JoinColumn(name = "catalogId", referencedColumnName = "ID") , inverseJoinColumns = @JoinColumn(name = "editionId", referencedColumnName = "ID") )
	public List<Edition> getEditions() {
		return this.editions;
	}

	public void setEditions(List<Edition> editions) {
		this.editions = editions;
	}

	public Edition addEdition(Edition edition) {
		getEditions().add(edition);
		edition.setCatalog(this);

		return edition;
	}

	public Edition removeEdition(Edition edition) {
		getEditions().remove(edition);
		edition.setCatalog(null);

		return edition;
	}

}