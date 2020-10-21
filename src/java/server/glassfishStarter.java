package server;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import server.automations.JobOrdemCompra;

/**
 *
 * @author Haison
 */
@WebListener
public class glassfishStarter implements ServletContextListener {

    //Funçâo iniciada quando o servidor inicia
    //Método comentado para evitar execução desnecessaria
    public void contextInitialized(ServletContextEvent e) {
        JobDetail job = JobBuilder.newJob(JobOrdemCompra.class).build();
        Trigger t1 = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

        Scheduler sc;
        try {
            sc = StdSchedulerFactory.getDefaultScheduler();
            sc.start();
            sc.scheduleJob(job, t1);
        } catch (SchedulerException ex) {
            Logger.getLogger(glassfishStarter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void contextDestroyed(ServletContextEvent e) {
        // implementation code
    }
}
