/******************************************************************************
 Copyright (c) 2020.                                                          *
 Created by Zhbert                                                            *
 ******************************************************************************/

package ru.zhbert.lorcleaner.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @version 0.0.1
 * @brief Дополнительный класс, необходимый для проверки ошибок валидации
 * @copyright © Konstantin "Zhbert" Nezhbert
 * @date 2021
 * @details Содержит методы, извлекающие из контекста спринга ошибки валидации
 */
public class ControllerUtils {
    /**
     * @brief Метод, возвращающий ошибки валидации
     * @param bindingResult Результат валидации
     * @return Ошибки в виде мапы
     */
    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}
