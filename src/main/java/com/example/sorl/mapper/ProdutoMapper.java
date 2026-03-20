package com.example.sorl.mapper;


import com.example.sorl.dto.ProdutoRequestDTO;
import com.example.sorl.dto.ProdutoResponseDTO;
import com.example.sorl.dto.ProdutoSolrDTO;
import com.example.sorl.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "mapId")
    @Mapping(source = "nome", target = "nome", qualifiedByName = "mapNome")
    @Mapping(source = "preco", target = "preco", qualifiedByName = "mapPreco")
    ProdutoResponseDTO solrToDTO(ProdutoSolrDTO solr);

    ProdutoResponseDTO toDTO(Produto entity);

    Produto toEntity(ProdutoRequestDTO dto);

    @Named("mapId")
    default Long mapId(String id){
        return id != null ? Long.valueOf(id) : null;
    }

    @Named("mapNome")
    default String mapNome(List<String> nomes){
        return (nomes != null && !nomes.isEmpty()) ? nomes.get(0) : null;
    }

    @Named("mapPreco")
    default Double mapPreco(List<Double> precos){
        return (precos != null && !precos.isEmpty()) ? precos.get(0) : null;
    }

//    public ProdutoResponseDTO toDTO(Produto entity) {
//        return new ProdutoResponseDTO(
//                entity.getId(),
//                entity.getNome(),
//                entity.getPreco()
//        );
//    }
//
//    public ProdutoResponseDTO solrToDTO(ProdutoSolrDTO solr) {
//        return new ProdutoResponseDTO(
//                solr.getId() !=null ? Long.valueOf(solr.getId()): null,
//                solr.getNomePrincipal(),
//                solr.getPrecoPrincipal()
//        );
//    }
//
//    public Produto toEntity(ProdutoResponseDTO dto) {
//        return new Produto(
//                dto.id(),
//                dto.nome(),
//                dto.preco()
//        );
//    }
}
