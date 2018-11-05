package ejercicio.banco.search;

import ejercicio.banco.dto.DataType;

import java.util.List;

public interface SearchEngine {
    List<Object> search(DataType type, String stuff);
}
