package fr.grabuges.api.command.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String name();
    String permission() default "";
    String noPermissionMessage() default "&cVous n'avez pas la permission d'utiliser cette commande.";
    String helpMessage() default "";
    String[] aliases() default {};

}
