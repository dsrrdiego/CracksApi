package com.cracks.api.controlers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cracks.api.modelos.Events;
import com.cracks.api.modelos.Goals;
import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.InterestEvent;
import com.cracks.api.modelos.Sports;
import com.cracks.api.modelos.User;
import com.cracks.api.modelos.aux.ClimateSports;
import com.cracks.api.modelos.aux.DifficultySports;
import com.cracks.api.modelos.aux.RoleParticipants;
import com.cracks.api.modelos.aux.StatusEvents;
import com.cracks.api.modelos.aux.StatusParticipants;
import com.cracks.api.modelos.aux.TypeNotification;
import com.cracks.api.repos.RepoEvents;
import com.cracks.api.repos.RepoInterest;
import com.cracks.api.repos.RepoInterestEvents;
import com.cracks.api.repos.RepoSports;
import com.cracks.api.repos.RepoUser;
import com.cracks.api.repos.aux.RepoCategoryEvents;
import com.cracks.api.repos.aux.RepoCategoryGoals;
import com.cracks.api.repos.aux.RepoClimateSports;
import com.cracks.api.repos.aux.RepoCommunityGoals;
import com.cracks.api.repos.aux.RepoDifficultySports;
import com.cracks.api.repos.aux.RepoGoals;
import com.cracks.api.repos.aux.RepoNameUserScore;
import com.cracks.api.repos.aux.RepoRoleParticipants;
import com.cracks.api.repos.aux.RepoStatusEvents;
import com.cracks.api.repos.aux.RepoStatusNotification;
import com.cracks.api.repos.aux.RepoStatusParticipants;
import com.cracks.api.repos.aux.RepoTypeNotification;

import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unused")
@Component
public class runController implements CommandLineRunner {

    @Autowired
    RepoDifficultySports repoDifficultySports;

    @Autowired
    private RepoClimateSports repoClimate;

    @Autowired
    private RepoStatusParticipants repoStatusParticipants;

    @Autowired
    private RepoRoleParticipants repoRoleParticipants;

    @Autowired
    private RepoStatusEvents repoStatusEvents;

    @Autowired
    private RepoCategoryEvents repoCategoryEvents;

    @Autowired
    private RepoStatusNotification repoStatusNotification;

    @Autowired
    private RepoTypeNotification repoTypeNotification;

    @Autowired
    private RepoNameUserScore repoNameUserScore;

    @Autowired
    private RepoCategoryGoals repoCategoryGoals;

    @Autowired
    private RepoCommunityGoals repoCommunityGoals;

    @Override
    public void run(String... args) throws Exception {

        String[] dificultades = { "Facil", "Dificil" };
        cargar("DifficultySports", repoDifficultySports, dificultades);

        String[] climas = { "Verano", "Otoño", "Invierno", "Primavera" };
        cargar("ClimateSports", repoClimate, climas);

        String[] status = { "Inscripto", "Aceptado", "Rechazado", "Partipado", "Faltado" };
        cargar("StatusParticipants", repoStatusParticipants, status);

        String[] role = { "Creador", "Participante", "Espectador" };
        cargar("RoleParticipants", repoRoleParticipants, role);

        String[] statusE = { "Registrado", "En Curso", "Finalizado" };
        cargar("StatusEvents", repoStatusEvents, statusE);

        String[] categorys = { "Cultural", "Deportivo", "Social" };
        cargar("CategoryEvents", repoCategoryEvents, categorys);

        String[] notif = { "Recibida", "Leida", "No Leida", "Archivada" };
        cargar("StatusNotification", repoStatusNotification, notif);

        String[] typeNot = { "SCORE_OWNER","SCORE_PARTICIPANT","Recordatorio", "Aceptación", "Calificación", "General", "Tips", "Faqs" };
        cargar("TypeNotification", repoTypeNotification, typeNot);

        String[] nameUserS = { "Puntualidad", "Participacion", "Compañerismo" };
        cargar("NameUserScore", repoNameUserScore, nameUserS);

        String[] categoryG = { "Categoria1", "Categoria2" };
        cargar("CategoryGoals", repoCategoryGoals, categoryG);

        String[] communityGoals = { "Personal", "Social", "Grupo1" };
        cargar("CommunityGoals", repoCommunityGoals, communityGoals);

        cargaABorrar();

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T, R extends JpaRepository<T, ?>> void cargar(String tabla, R repo, String[] lista)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            T t = (T) Class.forName("com.cracks.api.modelos.aux." + tabla);
            Constructor<?> constructor = ((Class) t).getConstructor(String.class);
            for (String i : lista) {
                T obj = (T) constructor.newInstance(i);
                repo.save(obj);
            }
        } catch (Exception e) {
            System.out.println("\nLos datos iniciales ya estan cargados para " + tabla);
        }
    }

    @Autowired
    private RepoEvents re;

    @Autowired
    private RepoUser ru;

    @Autowired
    private RepoGoals rg;

    @Autowired
    private RepoSports rs;

    @Autowired
    private RepoInterestEvents repoInterestEvents;
    
    @Autowired
    private RepoInterest repoInterest;

    private void cargaABorrar() {
        try {
            Events e = new Events();
            e.setTitle("Juntarnos a correr");
            LocalDateTime fecha = LocalDateTime.of(2025, 1, 1, 15, 0);
            e.setDateInit(fecha);
            re.save(e);

            Events e2 = new Events();
            e2.setTitle("Nadar");
            LocalDateTime fecha2 = LocalDateTime.of(2020, 1, 1, 15, 0);
            e2.setDateInit(fecha2);
            re.save(e2);

            // for (int i=3;i<10;i++){
            //     Events a=new Events();
            //     a.setTitle("e"+i);
            //     a.setDateInit(fecha);
            //     re.save(a);
            // }

            // Guarda con este!!!
            // User u = new User();
            // u.setName("Pepe");
            // ru.save(u);

            Goals g = new Goals();
            g.setTitle("Adelgazar");
            rg.save(g);

            Goals g2 = new Goals();
            g2.setTitle("Engordar");
            rg.save(g2);

            Sports s = new Sports();
            s.setTitle("futbol");
            rs.save(s);

            Sports s2 = new Sports();
            s2.setTitle("Rugby");
            rs.save(s2);

            // Intereses
            InterestEvent ie=new InterestEvent();
            ie.setEvent(e2);
            repoInterestEvents.save(ie);

            Interest i1 = new Interest();
            i1.setOwner(ie);
            i1.setGoal_sport_interest(g);
            repoInterest.save(i1);

            Interest i2 = new Interest();
            i2.setOwner(ie);
            i2.setGoal_sport_interest(g2);
            repoInterest.save(i2);
           
            Interest i3 = new Interest();
            i3.setOwner(ie);
            i3.setGoal_sport_interest(s);
            repoInterest.save(i3);

            Interest i4 = new Interest();
            i4.setOwner(ie);
            i4.setGoal_sport_interest(s2);
            repoInterest.save(i4);


            

        } catch (Exception e) {
        }

    }
}
