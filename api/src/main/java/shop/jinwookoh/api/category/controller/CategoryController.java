package shop.jinwookoh.api.category.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.jinwookoh.api.category.domain.CategoryDto;
import shop.jinwookoh.api.category.service.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl service;

    @GetMapping("/findall")
    public ResponseEntity<List<CategoryDto>> findAllCategory() {

        return ResponseEntity.ok(service.findAllCategory());
    }
}
