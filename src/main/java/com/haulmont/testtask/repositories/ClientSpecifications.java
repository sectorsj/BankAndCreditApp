package com.haulmont.testtask.repositories;

import com.haulmont.testtask.models.Client;
import com.haulmont.testtask.models.Credit;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;


public class ClientSpecifications {

    private static Specification<Client> lastNameLike(String lastNamePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("lastName"), String.format("%%%s%%", lastNamePart));
    }

    private static Specification<Client> nameLike(String namePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart));
    }

    private static Specification<Client> patronymicLike(String patronymicPart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("patronymic"), String.format("%%%s%%", patronymicPart));
    }
//    public static Specification<Client> bankIs(Long bankId) {
//        return (Specification<Client>) (root, criteriaQuery, criteriaBuilder) ->
////                criteriaBuilder.createQuery()
//                criteriaBuilder.joi
//        //equal(root.get("banksList").get("bank").get("id"), bankId);
//    }


    public static Specification<Client> build(MultiValueMap<String, String> params) {
        Specification<Client> spec = Specification.where(null);

        if (params.containsKey("lastName") && !params.getFirst("lastName").isBlank()) {
            spec = spec.and(ClientSpecifications.lastNameLike(params.getFirst("lastName")));
        }
        if (params.containsKey("name") && !params.getFirst("name").isBlank()) {
            spec = spec.and(ClientSpecifications.nameLike(params.getFirst("name")));
        }
        if (params.containsKey("patronymic") && !params.getFirst("patronymic").isBlank()) {
            spec = spec.and(ClientSpecifications.patronymicLike(params.getFirst("patronymic")));
        }
//        if (params.containsKey("bankId") && !params.get("bankId").isEmpty()) {
//                spec = spec.or(ClientSpecifications.bankIs(1L));
//        }
        return spec;
    }

}
