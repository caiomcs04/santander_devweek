package com.project.bootcamp.controller;

import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired                          //faz a conexão de de pontos ligando Stockservice com Stockcontroller -> passando a responsabilidade de instanciar as classses por lá
    private StockService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)       //recebe e devolve os objetos em json
    public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto) {                                     //@ResponseEntity comunica com o banco e @RequestBody recebe o parametro definido no banco de dados pelo DTO e @Valid valida o body que está vindo (não aceita os valores que estão vindo)
        return ResponseEntity.ok(service.save(dto));                                                                           //dto será o objeto a ser transferido novamente para o bd, após ele ser modificado
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> update(@RequestBody StockDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> findAll() {
        List<StockDTO> list = new ArrayList<>();
        StockDTO dto = new StockDTO();
        dto.setId(1L);
        dto.setName("Magazine Luiza");
        dto.setPrice(100D);
        dto.setVariation(10D);
        dto.setDate(LocalDate.now());
        list.add(dto);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> findById(@PathVariable Long id) {                   //@PathVariable pega o valor dp objeto passado pela URL
        List<StockDTO> list = new ArrayList<>();
        StockDTO stock1 = new StockDTO();
        stock1.setId(1L);
        stock1.setName("Magazine Luiza");
        stock1.setPrice(100D);
        stock1.setVariation(10D);
        stock1.setDate(LocalDate.now());
        StockDTO stock2 = new StockDTO();
        stock2.setId(2L);
        stock2.setName("Ponto frio");
        stock2.setPrice(200d);
        stock2.setVariation(5D);
        stock2.setDate(LocalDate.now());
        list.add(stock1);
        list.add(stock2);

        StockDTO dtoSelect = list.stream().filter(x -> x.getId().compareTo(id) == 0).findFirst().get();
        return ResponseEntity.ok(dtoSelect);
    }
}
