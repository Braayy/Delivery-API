package io.github.braayy.Delivery.feature.product;

import io.github.braayy.Delivery.feature.product.dto.RegisterProductDTO;
import io.github.braayy.Delivery.feature.product.dto.UpdateProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product register(RegisterProductDTO dto) {
        if (dto.quantityType() == QuantityType.WEIGHT && dto.weightMultiplier() == null) {
            throw new IllegalArgumentException("Quantity Type was set to WEIGHT, but Weight Multiplier was not set");
        }

        Product product = Product.builder()
            .name(dto.name())
            .description(dto.description())
            .imageUrl(dto.imageUrl())
            .price(dto.price())
            .quantityType(dto.quantityType())
            .weightMultiplier(dto.weightMultiplier())
            .maxAdditionals(dto.maxAdditionals())
            .build();

        return this.productRepository.save(product);
    }

    public Product getById(Long productId) {
        return this.productRepository.getReferenceById(productId);
    }

    public Page<Product> listAll(String name, Pageable pageable) {
        if (name != null && !name.isEmpty()) {
            return this.productRepository.findByNameIgnoreCaseContaining(name, pageable);
        }

        return this.productRepository.findAll(pageable);
    }

    public Product update(Long productId, UpdateProductDTO dto) {
        Product product = this.productRepository.getReferenceById(productId);

        if (dto.name() != null) {
            product.setName(dto.name());
        }

        if (dto.description() != null) {
            product.setDescription(dto.description());
        }

        if (dto.imageUrl() != null) {
            product.setImageUrl(dto.imageUrl());
        }

        if (dto.price() != null) {
            product.setPrice(dto.price());
        }

        if (dto.quantityType() != null) {
            product.setQuantityType(dto.quantityType());
        }

        if (dto.weightMultiplier() != null) {
            product.setWeightMultiplier(dto.weightMultiplier());
        }

        if (dto.maxAdditionals() != null) {
            product.setMaxAdditionals(dto.maxAdditionals());
        }

        if (product.getQuantityType() == QuantityType.WEIGHT && product.getWeightMultiplier() == null) {
            throw new IllegalArgumentException("Quantity Type was set to WEIGHT, but Weight Multiplier was not set");
        }

        return product;
    }

    public void deleteById(Long productId) {
        this.productRepository.deleteById(productId);
    }

}
