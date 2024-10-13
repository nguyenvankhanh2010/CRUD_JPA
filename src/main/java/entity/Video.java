package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Entity
@Table(name="videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="VideoID")
	private String videoId;
	
	@Column(name="Description", columnDefinition="NVARCHAR(MAX)")
	private String description;
	

	@Column(name="Poster")
	private String poster;

	@Column(name="Title", columnDefinition="NVARCHAR(MAX)")
	private String title;

	@Column(name="Views")
	private int views;
	
	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category category;

	public Video() {
		super();
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
