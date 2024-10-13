package controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import entity.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import services.ICategoryService;
import services.impl.CategoryService;
import utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("/categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));

			Category category = cateService.findById(id);

			req.setAttribute("cate", category);

			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("/admin/category/insert")) {
			// lấy dữ liệu từ view
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = req.getParameter("images");
			// đưa vào Model
			Category category = new Category();
			category.setCategoryname(categoryname);
			category.setStatus(status);
			// xử lý upload file
			String fname = "";
			String uploadPath = "D:\\upload";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images1");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// up load file
					part.write(uploadPath + "/" + fname);
					// ghi tên file vào data
					category.setImages(fname);
				} else if (images != null) {
					category.setImages(images);
				} else {
					category.setImages("avata.png");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			// truyền model vào insert
			cateService.insert(category);
			// trả về view
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("update")) {
			// lấy dữ liệu từ view
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = req.getParameter("images");
			// đưa vào Model
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			Category category = cateService.findById(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(status);
			// lưu hình cũ
			String fileold = category.getImages();
			// xử lý upload file
			String fname = "";
			String uploadPath = "D:\\upload2";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images1");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// up load file
					part.write(uploadPath + "/" + fname);
					// ghi tên file vào data
					category.setImages(fname);
				} else if (images != null) {
					category.setImages(images);
				} else {
					category.setImages(fileold);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// truyền model vào insert
			cateService.update(category);
			// trả về view
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
