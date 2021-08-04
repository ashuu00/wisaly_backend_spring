package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.service.Product.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/v1/product")
class ProductController(private val productService: ProductService) {

    @GetMapping("/all")
    fun getAllProducts(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int
    ) {

    }

    @PostMapping("/create")
    fun createNewProduct(
        @RequestAttribute("user_id") user_id: Long?,
        @RequestBody productBody: Any
    ) {

    }

    @GetMapping("/{product-link}")
    fun getProduct(
        @PathVariable("product-link") link: String,
        @RequestAttribute("user_id") user_id: Long?
    ) {

    }

    @PutMapping("/update/{product-link}")
    fun updateProduct(
        @PathVariable("product-link") link: String,
        @RequestAttribute("user_id") user_id: Long?
    ) {

    }

    @PatchMapping("/add-stock/{product-link}")
    fun updateProductStock() {

    }

    @GetMapping("/{business}")
    fun getBusinessProduct(
        @PathVariable("business") business: String,
        @RequestParam("size") size: Int,
        @RequestParam("page") page: Int
    ): ResponseEntity<String> {
        return ResponseEntity.ok("Get the users products")
    }

}