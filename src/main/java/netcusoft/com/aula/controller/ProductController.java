package netcusoft.com.aula.controller;


import netcusoft.com.aula.exception.RecursoNaoEncontrado;
import netcusoft.com.aula.model.entity.ProductModel;
import netcusoft.com.aula.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/produtos")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductModel> findAll() {
        if (productService.findAllProducts().isEmpty()) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("Lista vazia");

        }
        return productService.findAllProducts();

    }
    /*
    =================Melhor abordagem
    @GetMapping
public ResponseEntity<?> findAll() {
    List<ProductModel> produtos = productService.findAllProducts();
    if(produtos.isEmpty()){
        return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(produtos);
}
     */

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProductModel productModel = productService.findById(id);
        return ResponseEntity.ok(productModel);

    }

    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody ProductModel productModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RecursoNaoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto com id " + id + "nao encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductModel productModel) {

        try {
            ProductModel productModel1 = productService.updateProduct(id, productModel);

            return ResponseEntity.ok().body(productModel);
        } catch (RecursoNaoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto com id: " + id + "nao encontrado");

        }
    }
}
