package exercise.service;

import exercise.dto.DataType;

public interface DataGenerator {
    Object generate(DataType object, String filename);

    Object getDataType(DataType type, String filename);
}
