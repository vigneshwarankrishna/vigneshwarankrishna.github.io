package com.example.inventoryservice.services;

import com.example.inventoryservice.controllers.ProductException;
import com.example.inventoryservice.dtos.IProductMapper;
import com.example.inventoryservice.dtos.ProductCreateDto;
import com.example.inventoryservice.dtos.ProductDto;
import com.example.inventoryservice.dtos.ProductStockQuantityDto;
import com.example.inventoryservice.models.Product;
import com.example.inventoryservice.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService
        implements IProductService {

    private final IProductMapper productMapper;
    private final IProductRepository productRepository;

    @Override
    public ProductDto addOne(final ProductCreateDto productCreateDto) {

        var exists = this.productRepository.existsByName(productCreateDto.name());

        if (exists) {
            throw new ProductException(ProductException.ALREADY_EXISTS_PRODUCT);
        }

        var product = this.productMapper.toProduct(productCreateDto);

        var productSaved = this.productRepository.save(product);

        return this.productMapper.toDto(productSaved);
    }

    @Override
    public List<ProductDto> getAll() {
        var productsList = this.productRepository.findAll();

        return this.productMapper.toDto(productsList);
    }

    @Override
    public ProductDto getOneById(final Long id) {
        var product = this.productRepository.findById(id)
                                            .orElseThrow(() -> new ProductException(ProductException.PRODUCT_DOES_NOT_EXIST));

        return this.productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllByIds(final List<Long> ids) {
        var products = this.productRepository.findAllById(ids);
        return this.productMapper.toDto(products);
    }

    @Override
    public void increaseStock(final List<ProductStockQuantityDto> productsQuantities) {

        var productsMap = this.createProductsMapFromQuantities(productsQuantities);

        productsQuantities
                .forEach(productQuantity -> {
                    var product = productsMap.get(productQuantity.productId());
                    var newQuantity = product.getQuantity() + productQuantity.quantity();

                    product.setQuantity(newQuantity);
                });

        var updatedProducts = productsMap.values()
                                         .stream()
                                         .toList();

        this.productRepository.saveAll(updatedProducts);
    }

    @Override
    public void decreaseStock(final List<ProductStockQuantityDto> productsQuantities) {

        var productsMap = this.createProductsMapFromQuantities(productsQuantities);

        productsQuantities
                .forEach(productQuantity -> {
                    var product = productsMap.get(productQuantity.productId());
                    var newQuantity = product.getQuantity() - productQuantity.quantity();

                    if (newQuantity < 0) {
                        throw new ProductException(ProductException.QUANTITY_LOWER_ZERO + ", " + product.getName() + ", " + product.getQuantity());
                    }

                    product.setQuantity(newQuantity);
                });

        var updatedProducts = productsMap.values()
                                         .stream()
                                         .toList();

        this.productRepository.saveAll(updatedProducts);
    }

    private Map<Long, Product> createProductsMapFromQuantities(final List<ProductStockQuantityDto> productsQuantities) {
        var productIds = productsQuantities.stream()
                                           .map(ProductStockQuantityDto::productId)
                                           .toList();

        var productsMap = this.createProductsMap(productIds);

        if (productsMap.size() != productIds.size()) {
            throw new ProductException(ProductException.PRODUCT_DOES_NOT_EXIST);
        }
        return productsMap;
    }

    private Map<Long, Product> createProductsMap(final List<Long> productIds) {
        return this.productRepository.findAllById(productIds)
                                     .stream()
                                     .collect(Collectors.toMap(Product::getId, Function.identity()));
    }

    @Override
    public List<ProductDto> getProductsByCategory(String category, String sortBy) {

        var products = productRepository.findByCategoryAndQuantityGreaterThan(category, 0);

        if (products.isEmpty()) {
            throw new ProductException("No products available with sufficient inventory for category: " + category);
        }

        List<Product> sorted = switch (sortBy.toLowerCase()) {
            case "price" -> products.stream()
                    .sorted(Comparator.comparing(Product::getPrice))
                    .toList();
            case "inventory" -> products.stream()
                    .sorted(Comparator.comparing(Product::getQuantity).reversed())
                    .toList();
            default -> throw new ProductException("Invalid sort option. Use 'price' or 'inventory'");
        };

        return productMapper.toDto(sorted);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductCreateDto dto) {
        var existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found"));

        existing.setName(dto.name());
        existing.setPrice(dto.price());
        existing.setQuantity(dto.quantity());
        existing.setCategory(dto.category());

        return productMapper.toDto(productRepository.save(existing));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductException("Product not found");
        }
        productRepository.deleteById(id);
    }



}
