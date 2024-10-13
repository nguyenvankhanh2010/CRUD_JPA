package services;

import java.util.List;

import entity.Video;

public interface IVideoService {
	void insert(Video video);

	void update(Video video);

	void delete(String videoId) throws Exception;

	Video findById(String videoId);

	List<Video> findByTitle(String title);

	List<Video> findAll();

	List<Video> findAll(int page, int pagesize);

	int count();

}
