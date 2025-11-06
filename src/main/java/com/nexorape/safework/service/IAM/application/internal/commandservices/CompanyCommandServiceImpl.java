package com.nexorape.safework.service.IAM.application.internal.commandservices;


import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.domain.model.commands.CreateCompanyCommand;
import com.nexorape.safework.service.IAM.domain.model.commands.UpdateCompanyNameCommand;
import com.nexorape.safework.service.IAM.domain.model.commands.UpdateCompanyRegistrationCodeCommand;
import com.nexorape.safework.service.IAM.domain.model.valueobjects.RegistrationCode;
import com.nexorape.safework.service.IAM.domain.services.CompanyCommandService;
import com.nexorape.safework.service.IAM.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyCommandServiceImpl implements CompanyCommandService {

    private final CompanyRepository companyRepository;

    public CompanyCommandServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Company> handle(CreateCompanyCommand command){

        // 1. VALIDACIÓN (Lógica de Aplicación)
        // Usamos el Value Object para validar el formato (que no sea nulo/vacío)
        var registrationCode = new RegistrationCode(command.registrationCode());

        // Regla de negocio: No pueden existir dos compañías con el mismo código
        // Hacemos esta comprobación aquí para dar un error amigable,
        // aunque la BD también nos protege con la restricción UNIQUE.
        if (companyRepository.existsByRegistrationCode(registrationCode.companyCode())) {
            throw new IllegalArgumentException("El código de registro '" + command.registrationCode() + "' ya está en uso.");
        }

        // 2. CREAR AGREGADO (Lógica de Dominio)
        // Llama al constructor de tu entidad Company
        var company = new Company(
                command.name(),
                registrationCode.companyCode()
        );

        // 3. GUARDAR
        companyRepository.save(company);

        // 4. RETORNAR
        // Devuelves el objeto 'Company' completo, como acordamos.
        return Optional.of(company);
    }

    @Override
    public void handle(UpdateCompanyNameCommand command){
        // 1. BUSCAR el agregado
        var company = companyRepository.findById(command.companyId())
                .orElseThrow(() -> new IllegalArgumentException("Compañía no encontrada."));

        // 2. LLAMAR A LA LÓGICA DE DOMINIO
        // (Deberías crear un método 'updateName' en tu entidad 'Company')
        company.updateName(command.newName());

        // 3. GUARDAR
        companyRepository.save(company);
    }

    @Override
    public void handle(UpdateCompanyRegistrationCodeCommand command){
        // 1. TRADUCCIÓN (Validar el nuevo código)
        var newRegistrationCode = new RegistrationCode(command.newCompanyRegistrationCode());

        // 2. VALIDACIÓN DE NEGOCIO (Que el nuevo código no exista ya)
        if (companyRepository.existsByRegistrationCode(newRegistrationCode.companyCode())) {
            throw new IllegalArgumentException("El código de registro '" + command.newCompanyRegistrationCode() + "' ya está en uso.");
        }

        // 3. BUSCAR el agregado
        var company = companyRepository.findById(command.companyId())
                .orElseThrow(() -> new IllegalArgumentException("Compañía no encontrada."));

        // 4. LLAMAR A LA LÓGICA DE DOMINIO
        company.updateRegistrationCode(newRegistrationCode.companyCode());

        // 5. GUARDAR
        companyRepository.save(company);
    }

}
