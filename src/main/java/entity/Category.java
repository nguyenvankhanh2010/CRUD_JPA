package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@AllArgsConstructor
@Data
@Table(name="categories")
@NamedQuery(name="Category.findAll", query = "SELECT c FROM Category c")

public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CategoryID")
	private int categoryId;
	
	@Column(name ="Categoryname",columnDefinition="NVARCHAR(200) NOT NULL")
	@NotEmpty(message = "Khong duoc bo trong")
	private String categoryname;
	
	@Column(name ="Images",columnDefinition="NVARCHAR(MAX) NOT NULL")
	private String images;
	
	@Column(name ="Status")
	private int status;
	
	//bi-direction many-to-one association to Video
	@OneToMany(mappedBy="category")
	private List<Video> videos;
	
	public Category() {
		
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int statuss) {
		this.status = statuss;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	public Video addVideo(Video video) {
		getVideos().add(video);
		video.setCategory(this);
		
		return video;
	}
	public Video removerVideo(Video video) {
		getVideos().remove(video);
		video.setCategory(null);
		
		return video;
	}
}
