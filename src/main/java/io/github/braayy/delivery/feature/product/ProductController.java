package io.github.braayy.delivery.feature.product;

import io.github.braayy.delivery.dto.ApiResponse;
import io.github.braayy.delivery.feature.product.dto.ListProductDTO;
import io.github.braayy.delivery.feature.product.dto.RegisterProductDTO;
import io.github.braayy.delivery.feature.product.dto.ShowProductDTO;
import io.github.braayy.delivery.feature.product.dto.UpdateProductDTO;
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
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse<ShowProductDTO>> register(
        @Valid @RequestBody RegisterProductDTO body,
        UriComponentsBuilder uriBuilder
    ) {
        Product product = this.productService.register(body);

        URI uri = uriBuilder
            .path("/products/{id}")
            .build(product.getId());

        return ResponseEntity.created(uri)
            .body(ApiResponse.data(new ShowProductDTO(product)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShowProductDTO>> show(
        @PathVariable Long id
    ) {
        Product product = this.productService.getById(id);

        return ApiResponse.dataResponse(new ShowProductDTO(product));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ListProductDTO>>> list(
        @RequestParam(required = false) String name,
        @PageableDefault Pageable pageable
    ) {
        Page<Product> page = this.productService.listAll(name, pageable);

        return ApiResponse.dataResponse(page.map(ListProductDTO::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<ShowProductDTO>> update(
        @PathVariable Long id,
        @Valid @RequestBody UpdateProductDTO body
    ) {
        Product product = this.productService.update(id, body);

        return ApiResponse.dataResponse(new ShowProductDTO(product));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(
        @PathVariable Long id
    ) {
        this.productService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
