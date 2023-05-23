package com.nttdata.loadimageapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Variant Not Found")
public class VariantNotFoundException extends RuntimeException{
}
