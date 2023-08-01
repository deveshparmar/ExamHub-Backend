package com.examhub.backend.controller;

import com.examhub.backend.model.Category;
import com.examhub.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        if(category.getDescription()!=null && category.getTitle()!=null) {
            Category category1 = this.categoryService.addCategory(category);
            if(category1!=null) return ResponseEntity.ok(this.categoryService.addCategory(category));
            else return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    @PutMapping("/")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        return ResponseEntity.ok(this.categoryService.updateCategory(category));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?>deleteCategory(@PathVariable("categoryId") Long categoryId){
        this.categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category Deleted");
    }
}
