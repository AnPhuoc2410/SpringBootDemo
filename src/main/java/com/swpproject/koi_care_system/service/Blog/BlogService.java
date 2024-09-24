package com.swpproject.koi_care_system.service.Blog;

import com.swpproject.koi_care_system.dto.BlogDto;
import com.swpproject.koi_care_system.mapper.BlogMapper;
import com.swpproject.koi_care_system.models.Blog;
import com.swpproject.koi_care_system.models.Tag;
import com.swpproject.koi_care_system.models.User;
import com.swpproject.koi_care_system.payload.request.BlogCreateRequest;
import com.swpproject.koi_care_system.payload.request.BlogUpdateRequest;
import com.swpproject.koi_care_system.repository.BlogRepository;
import com.swpproject.koi_care_system.repository.TagRepository;
import com.swpproject.koi_care_system.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogService implements IBlogService {
    BlogRepository blogRepository;
    BlogMapper blogMapper;
    UserRepository userRepository;
    TagRepository tagRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('SHOP')")
    public BlogDto createBlog(BlogCreateRequest blogCreateRequest, String username) {
        if (blogRepository.existsByBlogTitle(blogCreateRequest.getBlogTitle())) {
            throw new RuntimeException("Blog already exists");
        }
        Blog blog = blogMapper.mapToBlog(blogCreateRequest);
        blog.setBlogImage("default.jpg");
        blog.setBlogDate(java.time.LocalDate.now());

        Set<Tag> tags = new HashSet<>();
        for (int tagId : blogCreateRequest.getTagIds()) {
            Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
            tags.add(tag);
        }
        blog.setTags(tags);
        blog.setUser(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")));

        return blogMapper.mapToBlogDto(blogRepository.save(blog));

    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('SHOP')")
    public BlogDto updateBlog(int id, BlogUpdateRequest blogUpdateRequest) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
        if (!blogUpdateRequest.getBlogTitle().equals(blog.getBlogTitle())) {
            if (blogRepository.existsByBlogTitle(blogUpdateRequest.getBlogTitle())) {
                throw new RuntimeException("Blog title already exists");
            }
        }
        blogMapper.updateBlog(blog, blogUpdateRequest);
        Set<Tag> tags = new HashSet<>();
        for (int tagId : blogUpdateRequest.getTagIds()) {
            Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
            tags.add(tag);
        }
        blog.setTags(tags);
        return blogMapper.mapToBlogDto(blogRepository.save(blog));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('SHOP')")
    public void deleteBlog(int id) {
        blogRepository.findById(id).ifPresentOrElse(blogRepository::delete, () -> {
            throw new RuntimeException("Blog not found");
        });
    }

    @Override
    public List<BlogDto> getAllBlogs(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Blog> blogs = blogRepository.findAll(pageable);
        return blogs.map(blogMapper::mapToBlogDto).toList();
    }

    @Override
    public BlogDto getBlogById(int id) {
        return blogRepository.findById(id).map(blogMapper::mapToBlogDto).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    @Override
    public List<BlogDto> getBlogByTag(int tagId) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
        List<Blog> blogs = blogRepository.findByTags(tag);
        return blogs.stream().map(blogMapper::mapToBlogDto).toList();
    }

    @Override
    public List<BlogDto> getBlogByUsername(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Blog> blogs = blogRepository.findByUser(user);
        return blogs.stream().map(blogMapper::mapToBlogDto).toList();
    }

    @Override
    public List<BlogDto> searchBlogs(String keyword) {
        List<Blog> blogs = blogRepository.findByBlogTitleContaining(keyword);
        return blogs.stream().map(blogMapper::mapToBlogDto).toList();
    }
}
