package ejb3;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the article database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
		@NamedQuery(name = "Article.findByTitle", query = "SELECT a from Article a WHERE a.title = :title") })
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private Section section;

	public Article() {
	}

	@Id
	@SequenceGenerator(name = "ARTICLE_ID_GENERATOR", sequenceName = "ARTICLE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_ID_GENERATOR")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// bi-directional many-to-one association to Section
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "ArticleSection", joinColumns = {
			@JoinColumn(name = "articleId", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "sectionId", referencedColumnName = "ID") })
	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

}