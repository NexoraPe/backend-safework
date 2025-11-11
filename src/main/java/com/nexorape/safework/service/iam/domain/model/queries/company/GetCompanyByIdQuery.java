package com.nexorape.safework.service.iam.domain.model.queries.company;

public record GetCompanyByIdQuery(Long companyId) {
   // public GetCompanyByIdQuery {
   //     if (companyId == null || companyId <= 0) {
   //         throw new IllegalArgumentException("companyId is null or companyId <= 0");
   //     }
   // }
}
