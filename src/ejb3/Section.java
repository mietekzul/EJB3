package ejb3;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the section database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Section.findAll", query = "SELECT s FROM Section s"),
		@NamedQuery(name = "Section.findBySectionName", query = "SELECT s from Section s WHERE s.sectionName =:section") })
public class Section implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String sectionName;
	private List<Article> articles;
	private Edition edition;

	public Section() {
	}

	@Id
	@SequenceGenerator(name = "SECTION_ID_GENERATOR", sequenceName = "SECTION_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECTION_ID_GENERATOR")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	// bi-directional many-to-one association to Article
	@OneToMany(mappedBy = "section", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "SectionArticles", joinColumns = {
			@JoinColumn(name = "sectionId", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "articleId", referencedColumnName = "ID") })
	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	// bi-directional many-to-one association to Edition
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "SectionEdition", joinColumns = {
			@JoinColumn(name = "sectionId", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "editionId", referencedColumnName = "ID") })
	public Edition getEdition() {
		return this.edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}

	public Article addArticle(Article article) {
		getArticles().add(article);
		article.setSection(this);

		return article;
	}

	public Article removeArticle(Article article) {
		getArticles().remove(article);
		article.setSection(null);

		return article;
	}

}