package com.mars.Explorer;

import java.io.Console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.mars.Explorer.dal.ExplorerException;
import com.mars.Explorer.dal.Game;
import com.mars.Explorer.domain.Explorer;
import com.mars.Explorer.domain.TableSurface;


@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ComponentScan("com.mars.Explorer.*")
public class MySpringTraningApplication 
{

	
	
	public static void main(String[] args) 
	{
		SpringApplication.run(MySpringTraningApplication.class, args);
		
		// Obtaining a reference to the console.
				Console con = System.console();
				// Checking If there is no console available, then exit.
				if (con == null) {
					System.err.println("No console available");
					System.exit(1);
				}
				System.out.println("Enter the size of TableSurface (since it is considered as square surface enter a valid value):");
				int n = Integer.parseInt(con.readLine(": "));
				TableSurface ts = new TableSurface(n, n);
				Explorer mes = new Explorer();
				Game game = new Game(ts, mes);
				
				System.out.println("Mars Explorer Simulator");
		        System.out.println("Enter a command, Valid commands are:");
		        System.out.println("\'PLACE X,Y\', BLOCK, REPORT, \'EXPLORE X,Y\' and EXIT");
		        
		        boolean keepRunning = true;
		        
		        while (keepRunning) {
		            String inputString = con.readLine(": ");
		            if ("EXIT".equals(inputString)) {
		                keepRunning = false;
		            } else {
		                try {
		                    String outputVal = game.eval(inputString);
		                    System.out.println(outputVal);
		                } catch (ExplorerException e) {
		                    System.out.println(e.getMessage());
		                }
		            }
		        }

			}
	
	
	
}
