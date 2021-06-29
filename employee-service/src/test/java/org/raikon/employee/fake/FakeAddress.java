package org.raikon.employee.fake;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FakeAddress {

    static class ClasseA {
        private Long id;
        private BigDecimal valor;

        public ClasseA(Long id, BigDecimal valor) {
            this.id = id;
            this.valor = valor;
        }

        public Long getId() {
            return this.id;
        }

        public BigDecimal getValor() {
            return this.valor;
        }
    }

    static class ClasseB {
        private Long id;
        private BigDecimal valor;

        public ClasseB(Long id, BigDecimal valor) {
            this.id = id;
            this.valor = valor;
        }

        public Long getId() {
            return this.id;
        }

        public BigDecimal getValor() {
            return this.valor;
        }

    }

    @Test
    void test() {
        ClasseA aAux = new ClasseA(1L, BigDecimal.valueOf(2L));
        ClasseA a1Aux = new ClasseA(2L, BigDecimal.valueOf(2L));

        ClasseB bAux = new ClasseB(1L, BigDecimal.valueOf(2L));
        ClasseB b1Aux = new ClasseB(1L, BigDecimal.valueOf(2L));
        ClasseB b2Aux = new ClasseB(2L, BigDecimal.valueOf(2L));
        ClasseB b3Aux = new ClasseB(2L, BigDecimal.valueOf(2L));
        ClasseB b4Aux = new ClasseB(2L, BigDecimal.valueOf(2L));
        ClasseB b5Aux = new ClasseB(2L, BigDecimal.valueOf(2L));
        ClasseB b6Aux = new ClasseB(2L, BigDecimal.valueOf(2L));
        ClasseB b7Aux = new ClasseB(2L, BigDecimal.valueOf(2L));

        List<ClasseA> listA = new ArrayList<>();
        listA.add(aAux);
        listA.add(a1Aux);

        Map<Long, BigDecimal> map = new HashMap<>();

        List<ClasseB> listB = new ArrayList<>();
        listB.add(bAux);
        listB.add(b1Aux);
        listB.add(b2Aux);
        listB.add(b3Aux);
        listB.add(b4Aux);
        listB.add(b5Aux);
        listB.add(b6Aux);
        listB.add(b7Aux);

        listA.forEach(a -> map.put(a.getId(), a.getValor()));

         listB.forEach(bTemp ->  {
            if (!map.containsKey(bTemp.getId())) {
                map.put(bTemp.getId(), bTemp.getValor());
            } else {
                map.replace(bTemp.getId(), map.get(bTemp.getId()).add(bTemp.getValor()));
            }
        });

        System.out.println(map);
    }
}
