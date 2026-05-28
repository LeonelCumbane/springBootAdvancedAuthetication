package netcusoft.com.aula.service;


import netcusoft.com.aula.exception.RecursoNaoEncontrado;
import netcusoft.com.aula.model.entity.ProductModel;
import netcusoft.com.aula.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> findAllProducts() {
        // boa pratica

        List<ProductModel> produtos = productRepository.findAll();
        if(produtos.isEmpty()) {
            throw new RuntimeException("Lista vazia");
        }
        return produtos;
            }

    public ProductModel findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado( "Produto nao encontrado!"));
    }

    public ProductModel saveProduct(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public void deleteById(Long id) {
        ProductModel  productModel = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto nao encontrado!"));

        productRepository.deleteById(id);
    }

    public ProductModel updateProduct( Long id,ProductModel productModel) {
       ProductModel productModel1=productRepository.findById(id)
               .orElseThrow(()->new RuntimeException("Produto nao encontrado!"));

        productModel1.setNome(productModel.getNome());
        productModel1.setDescricao(productModel.getDescricao());
        productModel1.setPreco(productModel.getPreco());


        return productRepository.save(productModel1);

    }

    //================ Buscar pelo UUID==============

    public ProductModel findProductByUuid(UUID uuid) {
        return productRepository.findByUuid(uuid)
                .orElseThrow(()->new RuntimeException("Produto nao encontrado!"));
    }
}
