/*
 * Copyright and related rights waived via CC0
 *
 * You should have received a copy of the CC0 legalcode along with this
 * work. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package org_hibernate_orm.hibernate_core;

import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.boot.registry.selector.internal.StrategySelectorImpl;
import org.hibernate.dialect.Dialect;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class HibernateDialectTest {

    @ParameterizedTest
    @MethodSource("provideHibernateDialects")
    void testHibernateDialects(String hibernateDialect) {
        StrategySelectorImpl strategySelector = new StrategySelectorImpl(new ClassLoaderServiceImpl());
        Dialect dialect = strategySelector.resolveStrategy(Dialect.class, hibernateDialect);
        assertThat(dialect).isNotNull();
    }

    private static Stream<Arguments> provideHibernateDialects() {
        String dialectPackage = "org.hibernate.dialect.";

        List<String> hibernateDialects = Arrays.asList(
                "MariaDB103Dialect",
                "MariaDB106Dialect",
                "MariaDBDialect",
                "MySQL8Dialect",
                "MySQL57Dialect",
                "MySQLDialect",
                "Oracle12cDialect",
                "OracleDialect",
                "PostgresPlusDialect",
                "PostgreSQL10Dialect",
                "PostgreSQLDialect",
                "SQLServer2008Dialect",
                "SQLServer2012Dialect",
                "SQLServer2016Dialect",
                "SQLServerDialect"
        );

        return hibernateDialects.stream().map(dialect -> Arguments.of(dialectPackage + dialect));
    }
}
