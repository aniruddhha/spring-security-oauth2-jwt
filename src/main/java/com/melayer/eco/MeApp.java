/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco;

import com.melayer.eco.db.MeMongoFactoryMultiTenenacy;
import com.melayer.eco.model.MeArticle;
import com.melayer.eco.model.MeUser;
import com.melayer.eco.repository.MeRepoArticle;
import com.melayer.eco.repository.MeUserRepo;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author melayer
 */
@SpringBootApplication
public class MeApp extends SpringBootServletInitializer{

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(MeApp.class, args);

        for (String name : context.getBeanDefinitionNames()) {

            System.out.println("-> " + name);
        }

        MeMongoFactoryMultiTenenacy factory = context.getBean(MeMongoFactoryMultiTenenacy.class);
        factory.setDataBase("ecokrypt");

        MeUserRepo repo = context.getBean(MeUserRepo.class);
         
         MeUser user1 = new MeUser();
         user1.setPassword("android");
         user1.setUserName("android");
         user1.addRole("ANDROID");
         repo.save(user1);
         
        
        /*MeRepoArticle repoArticle = context.getBean(MeRepoArticle.class);
        
        MeArticle article1 = new MeArticle();
        article1.setArticleAuthor("Samsung");
        article1.setArticleDescription("Samsung was founded by Lee Byung-chul in 1938 as a trading company. Over the next three decades, the group diversified into areas including food processing, textiles, insurance, securities and retail. Samsung entered the electronics industry in the late 1960s and the construction and shipbuilding industries in the mid-1970s; these areas would drive its subsequent growth. Following Lee's death in 1987, Samsung was separated into four business groups – Samsung Group, Shinsegae Group, CJ Group and Hansol Group. Since 1990s, Samsung has increasingly globalized its activities and electronics, particularly mobile phones and semiconductors, have become its most important source of income.");
        article1.setArticleName("Samsung to Samsung");
        article1.setCategory("Food");
        repoArticle.save(article1);

        MeArticle article2 = new MeArticle();
        article2.setArticleAuthor("Motorola");
        article2.setArticleDescription("Motorola designed and sold wireless network equipment such as cellular transmission base stations and signal amplifiers. Motorola's home and broadcast network products included set-top boxes, digital video recorders, and network equipment used to enable video broadcasting, computer telephony, and high-definition television. Its business and government customers consisted mainly of wireless voice and broadband systems (used to build private networks), and, public safety communications systems like Astro and Dimetra. These businesses (except for set-top boxes and cable modems) are now part of Motorola Solutions. Google sold Motorola Home (the former General Instrument cable businesses) to the Arris Group in December 2012 for US$2.35 billion.[7]");
        article2.setArticleName("In the hands of Google");
        article2.setCategory("Travel");
        repoArticle.save(article2);
        
        MeArticle article3 = new MeArticle();
        article3.setArticleAuthor("Windows");
        article3.setArticleDescription("Microsoft Windows (or simply Windows) is a metafamily of graphical operating systems developed, marketed, and sold by Microsoft. It consists of several families of operating systems, each of which cater to a certain sector of the computing industry. Active Windows families include Windows NT, Windows Embedded and Windows Phone; these may encompass subfamilies, e.g. Windows Embedded Compact (Windows CE) or Windows Server. Defunct Windows families include Windows 9x and Windows Mobile.");
        article3.setArticleName("Satya Nadela");
        article3.setCategory("Sport");
        repoArticle.save(article3);

        MeArticle article4 = new MeArticle();
        article4.setArticleAuthor("Apple");
        article4.setArticleDescription("Apple Inc. is an American multinational technology company headquartered in Cupertino, California, that designs, develops, and sells consumer electronics, computer software, and online services. Its hardware products include the iPhone smartphone, the iPad tablet computer, the Mac personal computer, the iPod portable media player, and the Apple Watch smartwatch. Apple's consumer software includes the OS X and iOS operating systems, the iTunes media player, the Safari web browser, and the iLife and iWork creativity and productivity suites. Its online services include the iTunes Store, the iOS App Store and Mac App Store, and iCloud.");
        article4.setArticleName("Steve we want apple to beat android");
        article4.setCategory("Social");
        repoArticle.save(article4);*/
           
    }
}
