package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {
    EntityClassMetaData<T> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectByIdSql() {
        return String.format("select %s from %s where %s = ?",
                entityClassMetaData.getAllFields()
                        .stream()
                        .map(Field::getName)
                        .collect(Collectors.joining(", ")),
                entityClassMetaData.getName(),
                entityClassMetaData.getIdField().getName());
    }

    @Override
    public String getSelectAllSql() {
        return String.format("select * from %s", entityClassMetaData.getName());
    }

    @Override
    public String getInsertSql() {
        List<Field> fieldList = entityClassMetaData.getFieldsWithoutId();

        String[] array = new String[fieldList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = "?";
        }
        String join = String.join(", ", array);
        return String.format("insert into %s(%s) values (%s)",
                entityClassMetaData.getName(),
                fieldList.stream()
                        .map(Field::getName)
                        .collect(Collectors.joining(", ")),
                join);
    }

    @Override
    public String getUpdateSql() {
        return String.format("update %s set %s = ? where %s = ?",
                entityClassMetaData.getName(),
                entityClassMetaData.getFieldsWithoutId().get(0),
                entityClassMetaData.getIdField());
    }
}