package com.example.yin.service.impl;
import com.example.yin.domain.Journaling;
import com.example.yin.service.JournalingService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
public class JournalingServiceImpl implements JournalingService  {
   private static Journaling journaling ;
   private int  Accumulates ;
   private int  Onlines ;

    public Journaling getJournaling() {
        return journaling;
    }

    public void setJournaling(Journaling journaling) {
        this.journaling = journaling;
    }

    static {
        journaling = new Journaling();
        journaling.setId(1);
        journaling.setAccumulate(10000);
        journaling.setOnline(66);
        journaling.visitList = new ArrayList();
        journaling.visitList.add(58);
        journaling.visitList.add(63);
        journaling.visitList.add(74);
        journaling.visitList.add(63);
        journaling.visitList.add(25);
        journaling.visitList.add(25);
    }
    @Override
    public  Journaling SelectJournaling() {
           addOnline();
           addAccumulate();
        return journaling;
    }
//   今日访问
    public void addOnline(){
        int flar = journaling.getOnline();
        flar++;
        journaling.setOnline(flar);
    }
//    积累访问
    public void addAccumulate(){
        int flar = journaling.getAccumulate();
        flar++;
        journaling.setAccumulate(flar);
    }
}
