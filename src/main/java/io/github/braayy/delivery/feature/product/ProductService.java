package io.github.braayy.delivery.feature.product;

import io.github.braayy.delivery.feature.product.dto.RegisterProductDTO;
import io.github.braayy.delivery.feature.product.dto.UpdateProductDTO;
import io.github.braayy.delivery.feature.productgroup.ProductGroup;
import io.github.braayy.delivery.feature.productgroup.ProductGroupService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductGroupService productGroupService;

    public Product register(RegisterProductDTO dto) {
        if (dto.quantityType() == QuantityType.WEIGHT && dto.weightMultiplier() == null) {
            throw new IllegalArgumentException("Quantity Type was set to WEIGHT, but Weight Multiplier was not set");
        }

        ProductGroup group = this.productGroupService.getById(dto.group());

        Product product = Product.builder()
            .name(dto.name())
            .description(dto.description())
            .imageUrl(dto.imageUrl())
            .group(group)
            .price(dto.price())
            .quantityType(dto.quantityType())
            .weightMultiplier(dto.weightMultiplier())
            .maxAdditionals(dto.maxAdditionals())
            .build();

        return this.productRepository.save(product);
    }

    public Product getById(Long productId) {
        if (!this.productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product with id " + productId + " could not be found");
        }

        return this.productRepository.getReferenceById(productId);
    }

    public Page<Product> listAll(String name, Long groupId, Pageable pageable) {
        ProductGroup group = groupId != null ? this.productGroupService.getById(groupId) : null;
        System.out.println(name + " " + groupId);

        return this.productRepository.findByNameContainingIgnoreCaseAndGroup(name, group, pageable);
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


        if (dto.group() != null) {
            ProductGroup group = this.productGroupService.getById(dto.group());

            product.setGroup(group);
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
