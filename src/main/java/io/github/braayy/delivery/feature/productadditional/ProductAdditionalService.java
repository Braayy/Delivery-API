package io.github.braayy.delivery.feature.productadditional;

import io.github.braayy.delivery.feature.product.Product;
import io.github.braayy.delivery.feature.product.ProductService;
import io.github.braayy.delivery.feature.productadditional.dto.RegisterProductAdditionalDTO;
import io.github.braayy.delivery.feature.productadditional.dto.UpdateProductAdditionalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAdditionalService {

    private final ProductAdditionalRepository productAdditionalRepository;
    private final ProductService productService;

    public ProductAdditional register(RegisterProductAdditionalDTO dto) {
        Product product = this.productService.getById(dto.productId());

        ProductAdditional additional = ProductAdditional.builder()
            .product(product)
            .name(dto.name())
            .description(dto.description())
            .price(dto.price())
            .build();

        return this.productAdditionalRepository.save(additional);
    }

    public ProductAdditional getById(Long additionalId) {
        return this.productAdditionalRepository.getReferenceById(additionalId);
    }

    public List<ProductAdditional> listAll(Long productId) {
        Product product = this.productService.getById(productId);

        return this.productAdditionalRepository.findAllByProduct(product);
    }

    public ProductAdditional update(Long additionalId, UpdateProductAdditionalDTO dto) {
        ProductAdditional additional = this.productAdditionalRepository.getReferenceById(additionalId);

        if (dto.name() != null) {
            additional.setName(dto.name());
        }

        if (dto.description() != null) {
            additional.setDescription(dto.description());
        }

        if (dto.price() != null) {
            additional.setPrice(dto.price());
        }

        return additional;
    }

    public void deleteById(Long additionalId) {
        this.productAdditionalRepository.deleteById(additionalId);
    }

}
