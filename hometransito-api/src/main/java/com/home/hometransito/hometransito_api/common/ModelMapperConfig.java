package com.home.hometransito.hometransito_api.common;

import com.home.hometransito.hometransito_api.domain.model.Veiculo;
import com.home.hometransito.hometransito_api.model.VeiculoRepresentationModel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// configura beans
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Veiculo.class, VeiculoRepresentationModel.class)
                .addMappings(mapper -> mapper.map(Veiculo::getPlaca,
                        VeiculoRepresentationModel::setPlaca)); //usar caso tenha alguma variavel que difere em nome - nesse caso não tinha

        return new ModelMapper();
    }

}
