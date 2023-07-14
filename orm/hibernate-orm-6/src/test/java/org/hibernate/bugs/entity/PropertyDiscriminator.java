package org.hibernate.bugs.entity;

import jakarta.persistence.DiscriminatorType;
import org.hibernate.annotations.AnyDiscriminator;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyKeyJavaClass;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@AnyDiscriminator(DiscriminatorType.STRING)
@AnyDiscriminatorValue(discriminator = "S", entity = StringProperty.class)
@AnyDiscriminatorValue(discriminator = "I", entity = IntegerProperty.class)
@AnyKeyJavaClass(Long.class)
public @interface PropertyDiscriminator {

}
