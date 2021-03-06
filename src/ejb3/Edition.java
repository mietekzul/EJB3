package ejb3;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the edition database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Edition.findAll", query = "SELECT e FROM Edition e"),
		@NamedQuery(name = "Edition.findByEdition", query = "SELECT e from Edition e WHERE e.edition = :edition") })
public class Edition implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String edition;
	private Catalog catalog;
	private List<Section> sections;

	public Edition() {
	}

	@Id
	@SequenceGenerator(name = "EDITION_ID_GENERATOR", sequenceName = "EDITION_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EDITION_ID_GENERATOR")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// bi-directional many-to-one association to Catalog
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "EditionCatalog", joinColumns = {
			@JoinColumn(name = "editionId", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "catalogId", referencedColumnName = "ID") })
	public Catalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	// bi-directional many-to-one association to Section
	@OneToMany(mappedBy = "edition", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "EditionSections", joinColumns = {
			@JoinColumn(name = "editionId", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "sectionId", referencedColumnName = "ID") })
	public List<Section> getSections() {
		return this.sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Section addSection(Section section) {
		getSections().add(section);
		section.setEdition(this);

		return section;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public Section removeSection(Section section) {
		getSections().remove(section);
		section.setEdition(null);

		return section;
	}

}