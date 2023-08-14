package com.example.laptopstore.controllers;

import com.example.laptopstore.DTO.LaptopDTO;
import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.services.LaptopService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/laptop")
@RequiredArgsConstructor
public class Controller {
    private final LaptopService laptopService;

    @GetMapping
    public ResponseEntity<List<Laptop>> getAllLaptops(
                                        @RequestParam(required = false, defaultValue = "0") int page,
                                        @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(laptopService.getAllLaptops(PageRequest.of(page, size)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }


    @PostMapping("/hibernate")
    public ResponseEntity<List<Laptop>>
    getLaptopPostFilters(@RequestBody LaptopDTO laptopDTO,
                         @RequestParam(required = false, defaultValue = "0") int page,
                         @RequestParam(required = false, defaultValue = "10") int size)
                                                            throws IllegalAccessException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(laptopService.findbyDTOWithHibernate(laptopDTO,PageRequest.of(page,size)));
    }



//    @GetMapping(params = { "page", "size" })
//    public List<Foo> findPaginated(@RequestParam("page") int page,
//                                   @RequestParam("size") int size, UriComponentsBuilder uriBuilder,
//                                   HttpServletResponse response) {
//        Page<Foo> resultPage = service.findPaginated(page, size);
//        if (page > resultPage.getTotalPages()) {
//            throw new MyResourceNotFoundException();
//        }
//        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Foo>(
//                Foo.class, uriBuilder, response, page, resultPage.getTotalPages(), size));
//
//        return resultPage.getContent();
//    }

}




