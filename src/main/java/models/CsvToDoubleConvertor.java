package models;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class CsvToDoubleConvertor <T> extends AbstractBeanField<T, Double> {
    @Override
    protected Double convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return Double.parseDouble(value.replace(',', '.'));
    }
}
