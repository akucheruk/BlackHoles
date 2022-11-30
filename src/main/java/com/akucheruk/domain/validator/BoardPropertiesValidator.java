package com.akucheruk.domain.validator;

import com.akucheruk.domain.board.BoardProperties;

import javax.validation.*;
import java.util.Set;

/**
 * Validator for BoardProperties, which checks board width, height and black holes count
 */
public class BoardPropertiesValidator {

    private final Validator validator;
    public final float MAX_BLACK_HOLE_CAPACITY = 0.8f;

    public BoardPropertiesValidator() {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        this.validator = factory.getValidator();
        factory.close();
    }

    public <T> Set<ConstraintViolation<T>> validate(T object) {
        return this.validator.validate(object);
    }

    public void validateBlackHoleCapacity(BoardProperties boardProperties) {
        int cells = boardProperties.getWidth() * boardProperties.getHeight();
        int maxBlackHolesForCurrentBoard = (int) (cells * MAX_BLACK_HOLE_CAPACITY);
        if (boardProperties.getBlackHolesCount() >= maxBlackHolesForCurrentBoard) {
            throw new ValidationException("Black hole capacity should not be greater than: " + maxBlackHolesForCurrentBoard);
        }
    }

}
