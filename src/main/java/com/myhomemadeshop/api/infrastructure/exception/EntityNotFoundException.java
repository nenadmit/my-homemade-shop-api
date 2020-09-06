package com.myhomemadeshop.api.infrastructure.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Class entityClass,Long id){
        super(String.format("%s with id: %s, not found",entityClass.getSimpleName(),id));
    }

    public EntityNotFoundException(Class entityClass,String name){
        super(String.format("%s with name: %s, not found",entityClass.getSimpleName(),name));
    }
}
