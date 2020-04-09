package rpc.kx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 　　* @description: TODO
 * 　　* @author kx
 * 　　* @date 2020/03/16 21:04
 *
 */
public class App {
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        applicationContext.start();
    }

}
