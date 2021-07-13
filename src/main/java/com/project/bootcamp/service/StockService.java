package com.project.bootcamp.service;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StockService {

    @Autowired                                    //gerencia automaticamente as instancias do repository
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    @Transactional                                  //Transactional faz o insert/update/delete tudo automaticamente no banco de dados
    public StockDTO save(StockDTO dto) {            //O object é StockDTO devido que na camada StockContoller, a entidade retorna StockDTO, portanto ela deve possuir essa classe como objeto aqui.
        
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);                     //ele vai criar na base de dados a entidade, incrementando o id (pois até nesta etapa, o id pode ser null. Será preciso mandar isso de volta para o controller.
        return mapper.toDTO(stock);
    }
}
