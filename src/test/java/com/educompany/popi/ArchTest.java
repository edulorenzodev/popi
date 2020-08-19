package com.educompany.popi;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.educompany.popi");

        noClasses()
            .that()
                .resideInAnyPackage("com.educompany.popi.service..")
            .or()
                .resideInAnyPackage("com.educompany.popi.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.educompany.popi.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
