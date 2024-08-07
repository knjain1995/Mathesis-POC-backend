package com.example.demo.mappers;

import com.example.demo.dto.SignUpInformationDTO;
import com.example.demo.entities.SignUpInformation;

public class SignUpInformationMapper {

    public SignUpInformation mapToEntity(SignUpInformationDTO signUpInformationDTO){

        if (signUpInformationDTO == null) {
            return null;
        }

        SignUpInformation signUpInformationEntity = new SignUpInformation();
        signUpInformationEntity.setFirstname(signUpInformationDTO.getFirstname());
        signUpInformationEntity.setLastname(signUpInformationDTO.getLastname());
        signUpInformationEntity.setEmail(signUpInformationDTO.getEmail());
        signUpInformationEntity.setPhone(signUpInformationDTO.getPhone());
        signUpInformationEntity.setDateofbirth(signUpInformationDTO.getDateofbirth());
        signUpInformationEntity.setPassword(signUpInformationDTO.getPassword());
        signUpInformationEntity.setNewsletterintent(signUpInformationDTO.isNewsletterintent());

        return signUpInformationEntity;
    }

    public SignUpInformationDTO mapToDTO(SignUpInformation signUpInformationEntity){

        if (signUpInformationEntity == null) {
            return null;
        }

        SignUpInformationDTO signUpInformationDTO = new SignUpInformationDTO();

        signUpInformationDTO.setFirstname(signUpInformationEntity.getFirstname());
        signUpInformationDTO.setLastname(signUpInformationEntity.getLastname());
        signUpInformationDTO.setEmail(signUpInformationEntity.getEmail());
        signUpInformationDTO.setPhone(signUpInformationEntity.getPhone());
        signUpInformationDTO.setDateofbirth(signUpInformationEntity.getDateofbirth());
        signUpInformationDTO.setPassword(signUpInformationEntity.getPassword());
        signUpInformationDTO.setNewsletterintent(signUpInformationEntity.isNewsletterintent());

        return signUpInformationDTO;
    }
}
