package com.home.hometransito.hometransito_api.mapper;

import com.home.hometransito.hometransito_api.domain.model.Veiculo;
import com.home.hometransito.hometransito_api.model.VeiculoRepresentationModel;
import com.home.hometransito.hometransito_api.model.input.VeiculoModelInput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VeiculoMapper {

    private final ModelMapper modelMapper;

    public Veiculo toEntity(VeiculoModelInput veiculoModelInput){
        return modelMapper.map(veiculoModelInput, Veiculo.class);
    }

    public VeiculoRepresentationModel toModel(Veiculo veiculo){
        return modelMapper.map(veiculo, VeiculoRepresentationModel.class);
    }

    public List<VeiculoRepresentationModel> toCollectionModel(List<Veiculo> veiculos) {
        return veiculos.stream()
                .map(this::toModel)
                .toList();
    }
}
