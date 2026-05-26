package in.tech_camp.ajax_app_java.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.tech_camp.ajax_app_java.entity.PostEntity;
import in.tech_camp.ajax_app_java.form.PostForm;
import in.tech_camp.ajax_app_java.repository.PostRepository;

@Controller
public class PostController {

  private final PostRepository postRepository;

  // ★これが必要
  public PostController(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @GetMapping("/")
  public String showList(Model model) {
    var postList = postRepository.findAll();
    model.addAttribute("postList", postList);
    model.addAttribute("postForm", new PostForm());
    return "posts/index";
  }

  @PostMapping("/posts")
  public String savePost(@ModelAttribute("postForm") PostForm form) {
    PostEntity post = new PostEntity();
    post.setContent(form.getContent());
    postRepository.insert(post);
    return "redirect:/";
  }
}