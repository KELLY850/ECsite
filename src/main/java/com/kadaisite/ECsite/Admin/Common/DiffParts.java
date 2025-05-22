package com.kadaisite.ECsite.Admin.Common;

import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.function.BiConsumer;


public class DiffParts {

    public static <T> boolean diff(T oldDB, T newForm, BiConsumer<DiffDB,T>comparer){
    DiffDB diffDB =  new DiffDB(oldDB, newForm);
    comparer.accept(diffDB,newForm);
    return diffDB.build().getNumberOfDiffs()>0;
    }

    public static class DiffDB{
        private final DiffBuilder diffBuilder;

        public <T> DiffDB(T oldDB, T newForm) {
            // Builderで設定してからbuild()でDiffBuilderを取得
            this.diffBuilder = DiffBuilder.builder()
                    .setLeft(oldDB)
                    .setRight(newForm)
                    .setStyle(ToStringStyle.SHORT_PREFIX_STYLE)
                    .build();
        }

        public <T> void append(String field,T oldDB,T newForm){
            diffBuilder.append(field,oldDB,newForm);
        }
        public DiffResult build(){
            return diffBuilder.build();
        }
    }
}
