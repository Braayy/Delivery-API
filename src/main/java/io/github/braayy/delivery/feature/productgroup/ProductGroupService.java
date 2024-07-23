package io.github.braayy.delivery.feature.productgroup;

import io.github.braayy.delivery.feature.product.ProductService;
import io.github.braayy.delivery.feature.productgroup.dto.RegisterProductGroupDTO;
import io.github.braayy.delivery.feature.productgroup.dto.UpdateProductGroupDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductGroupService {

    private final ProductGroupRepository productGroupRepository;

    public ProductGroup register(RegisterProductGroupDTO dto) {
        ProductGroup productGroup = ProductGroup.builder()
            .name(dto.name())
            .build();

        return this.productGroupRepository.save(productGroup);
    }

    public ProductGroup getById(Long groupId) {
        if (!this.productGroupRepository.existsById(groupId)) {
            throw new EntityNotFoundException("Product Group with id " + groupId + " could not be found");
        }

        return this.productGroupRepository.getReferenceById(groupId);
    }

    public Page<ProductGroup> listAll(Pageable pageable) {
        return this.productGroupRepository.findAll(pageable);
    }

    public ProductGroup update(Long groupId, UpdateProductGroupDTO dto) {
        ProductGroup group = this.productGroupRepository.getReferenceById(groupId);

        group.setName(dto.name());

        return group;
    }

    public void deleteById(Long groupId) {
        this.productGroupRepository.deleteById(groupId);
    }

}
