package io.github.braayy.delivery.feature.productadditional;

import io.github.braayy.delivery.dto.ApiResponse;
import io.github.braayy.delivery.feature.productadditional.dto.ListProductAdditionalDTO;
import io.github.braayy.delivery.feature.productadditional.dto.RegisterProductAdditionalDTO;
import io.github.braayy.delivery.feature.productadditional.dto.ShowProductAdditionalDTO;
import io.github.braayy.delivery.feature.productadditional.dto.UpdateProductAdditionalDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product-additionals")
@RequiredArgsConstructor
public class ProductAdditionalController {

    private final ProductAdditionalService productAdditionalService;

    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse<ShowProductAdditionalDTO>> register(
        @Valid @RequestBody RegisterProductAdditionalDTO body,
        UriComponentsBuilder uriBuilder
    ) {
        ProductAdditional productAdditional = this.productAdditionalService.register(body);

        URI uri = uriBuilder
            .path("/product-additionals/{id}")
            .build(productAdditional.getId());

        return ResponseEntity.created(uri)
            .body(ApiResponse.data(new ShowProductAdditionalDTO(productAdditional)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShowProductAdditionalDTO>> show(
        @PathVariable Long id
    ) {
        ProductAdditional productAdditional = this.productAdditionalService.getById(id);

        return ApiResponse.dataResponse(new ShowProductAdditionalDTO(productAdditional));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ListProductAdditionalDTO>>> list(
        @RequestParam Long product
    ) {
        List<ProductAdditional> all = this.productAdditionalService.listAll(product);

        return ApiResponse.dataResponse(all.stream().map(ListProductAdditionalDTO::new).toList());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<ShowProductAdditionalDTO>> update(
        @PathVariable Long id,
        @Valid @RequestBody UpdateProductAdditionalDTO body
    ) {
        ProductAdditional productAdditional = this.productAdditionalService.update(id, body);

        return ApiResponse.dataResponse(new ShowProductAdditionalDTO(productAdditional));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(
        @PathVariable Long id
    ) {
        this.productAdditionalService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}

