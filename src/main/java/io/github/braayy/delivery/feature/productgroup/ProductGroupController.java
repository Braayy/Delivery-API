package io.github.braayy.delivery.feature.productgroup;

import io.github.braayy.delivery.dto.ApiResponse;
import io.github.braayy.delivery.feature.productgroup.dto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/product-groups")
@RequiredArgsConstructor
public class ProductGroupController {

    private final ProductGroupService productGroupService;

    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse<ShowProductGroupDTO>> register(
        @Valid @RequestBody RegisterProductGroupDTO body,
        UriComponentsBuilder uriBuilder
    ) {
        ProductGroup productGroup = this.productGroupService.register(body);

        URI uri = uriBuilder
            .path("/product-groups/{id}")
            .build(productGroup.getId());

        return ResponseEntity.created(uri)
            .body(ApiResponse.data(new ShowProductGroupDTO(productGroup)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShowProductGroupDTO>> show(
        @PathVariable Long id
    ) {
        ProductGroup productGroup = this.productGroupService.getById(id);

        return ApiResponse.dataResponse(new ShowProductGroupDTO(productGroup));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ListProductGroupDTO>>> list(
        @PageableDefault Pageable pageable
    ) {
        Page<ProductGroup> page = productGroupService.listAll(pageable);

        return ApiResponse.dataResponse(page.map(ListProductGroupDTO::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<ShowProductGroupDTO>> update(
        @PathVariable Long id,
        @Valid @RequestBody UpdateProductGroupDTO body
    ) {
        ProductGroup productGroup = productGroupService.update(id, body);

        return ApiResponse.dataResponse(new ShowProductGroupDTO(productGroup));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(
        @PathVariable Long id
    ) {
        productGroupService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
