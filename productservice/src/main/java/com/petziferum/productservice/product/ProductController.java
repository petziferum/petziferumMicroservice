package com.petziferum.productservice.product;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Slf4j
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Schalte WLED Strip 1 ein",
            description = "Schalte WLED Strip 1 ein",
            externalDocs = @ExternalDocumentation(
                    description = "OpenAPI Annotations",
                    url = "https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations"))
    @PostMapping("/wled")
    public ResponseEntity<String> wledOne() {
        String url = "http://192.168.178.26/json/state";
        String state = "{'on':'t', 'v':'true', 'bri':'255', 'transition':'0'}";
        RestTemplate restTemplate = new RestTemplate();
        try {
            log.debug("Schalte WLED Strip 1 ein aut IP: " + url + " und State: " + state);
        ResponseEntity<String> response = restTemplate.postForEntity(url, state, String.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            return response;
        } else {
            return ResponseEntity.badRequest().body("Fehler beim Einschalten der WLED");
        }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fehler" + e);
        }
    }

    @Operation(summary = "Textdatein auslesen", description = "Liest alle Textdatein aus dem Ordner datafolder aus.", externalDocs = @ExternalDocumentation(
            description = "OpenAPI Annotations",
            url = "https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType ="application/json",
                            schema = @Schema(
                                    implementation = FileData.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/text")
    public ResponseEntity<ArrayList<FileData>> getFile() throws Exception {
        return ResponseEntity.ok(productService.readData());
    }
}
