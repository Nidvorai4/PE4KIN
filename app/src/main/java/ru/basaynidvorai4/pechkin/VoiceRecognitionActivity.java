package ru.basaynidvorai4.pechkin;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.basai4.pe4kinator;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
//import com.basai4.pe4kinator.VoiceRecognitionActivity.1;
//import com.basai4.pe4kinator.VoiceRecognitionActivity.2;
//import com.basai4.pe4kinator.VoiceRecognitionActivity.3;
//import com.basai4.pe4kinator.VoiceRecognitionActivity.4;
import com.truiton.customspeechrecognizer.R;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;



class Home {
    int ChisloHL;
    int Uchastok;
    PosOfDiap DiapLR;
    char ChetNe4et;

    Home(int CHL, int Uch, PosOfDiap LR, char ChN4) {
        this.ChisloHL = CHL;
        this.Uchastok = Uch;
        this.DiapLR = LR;
        this.ChetNe4et = ChN4;
    }
}


enum PosOfDiap {
    L,
    R;

    private PosOfDiap() {
    }
}



class SortByChislo implements Comparator<Home> {
    SortByChislo() {
    }

    public int compare(Home a, Home b) {
        return a.ChisloHL - b.ChisloHL;
    }
}




class CUL {
    String Street = "HUI";
    ArrayList<Home> Houmis = new ArrayList();

    CUL(String Name) {
        this.Street = Name;
        this.Houmis.clear();
    }

    public void AddDiap(Bsk NewD) {
        int TempCh = NewD.HouseStart * 1000;
        if (NewD.Est_LitStartD()) {
            TempCh += NewD.LiterStartDec;
        }

        this.Houmis.add(new Home(TempCh, NewD.Uchastok, PosOfDiap.L, NewD.ChetNechet));
        TempCh = NewD.HouseStop * 1000;
        if (NewD.Est_LitStopD()) {
            TempCh += NewD.LiterStopDec;
        }

        if (TempCh != 0) {
            this.Houmis.add(new Home(TempCh, NewD.Uchastok, PosOfDiap.R, NewD.ChetNechet));
        }

    }

    public void SortAtTheAddingEnd() {
        Collections.sort(this.Houmis, new SortByChislo());
    }
}

class Bsk {
    private final int NOOL_SHIT_INT = 999;
    String Street;
    int HouseStart;
    char LiterStart;
    int LiterStartDec;
    int HouseStop;
    char LiterStop;
    int LiterStopDec;
    int Uchastok;
    char ChetNechet;

    Bsk(String Str, int HoSta, char LitSta, int LitStaD, int HoSto, char LitSto, int LitStoD, int Uch, char ChN) {
        this.Street = Str;
        this.HouseStart = HoSta;
        this.LiterStart = LitSta;
        this.LiterStartDec = LitStaD;
        this.HouseStop = HoSto;
        this.LiterStop = LitSto;
        this.LiterStopDec = LitStoD;
        this.Uchastok = Uch;
        this.ChetNechet = ChN;
    }

    Bsk() {
        this.Forget();
    }

    void Forget() {
        this.Street = "HUI";
        this.HouseStart = 999;
        this.LiterStart = 'L';
        this.LiterStartDec = 999;
        this.HouseStop = 999;
        this.LiterStop = 'L';
        this.LiterStopDec = 999;
        this.Uchastok = 999;
        this.ChetNechet = 'L';
    }

    String StrokoyOdnoyu() {
        return this.Street + "_" + this.HouseStart + "_" + this.LiterStart + "_" + this.LiterStartDec + "_" + this.HouseStop + "_" + this.LiterStop + "_" + this.LiterStopDec + "_" + this.Uchastok + "_" + this.ChetNechet;
    }

    boolean Est_Street() {
        return !this.Street.equals("HUI");
    }

    boolean Est_LitStartD() {
        return this.LiterStartDec != 999;
    }

    boolean Est_LitStopD() {
        return this.LiterStopDec != 999;
    }

    boolean Est_LitStart() {
        return this.LiterStart != 'L';
    }

    boolean Est_LitStop() {
        return this.LiterStop != 'L';
    }
}


public class VoiceRecognitionActivity extends AppCompatActivity implements RecognitionListener, View.OnTouchListener {
    private static final int REQUEST_RECORD_PERMISSION = 100;
    private TextView returnedText;
    private TextView AKYNText;
    private TextView UchastokText;
    private ProgressBar progressBar;
    private ImageView Pic;
    private SpeechRecognizer speech = null;
    private boolean OnLine =false;
    private LinearLayout ALLscreen;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    private String GolosIsGoogla = "";
    private Bsk G = new Bsk();
    private ArrayList<CUL> ULT = new ArrayList();
    private boolean Zapolneno=false;
    private int CurrPic = 0;
    private long PressedTime=0;
    private Handler handler = new Handler();
    String Alp = "#абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    boolean AutoListen = false;
    private static Orient Rotate =Orient.VH;
    private Bsk[] Piska = new Bsk[]{
                                    new Bsk("белинского", 0, 'L', 999, 0, 'L', 999, 1, 'ч'), 
                                    new Bsk("береговая", 0, 'L', 999, 0, 'L', 999, 2, 'ч'), 
                                    new Bsk("бланская", 1, 'L', 999, 25, 'L', 999, 9, 'н'), 
                                    new Bsk("бланская", 2, 'L', 999, 28, 'L', 999, 9, 'ч'), 
                                    new Bsk("бланская", 27, 'L', 999, 65, 'L', 999, 7, 'н'), 
                                    new Bsk("бланская", 30, 'L', 999, 64, 'L', 999, 7, 'ч'), 
                                    new Bsk("бланская", 73, 'L', 999, 107, 'L', 999, 5, 'н'), 
                                    new Bsk("бланская", 72, 'L', 999, 152, 'L', 999, 5, 'ч'), 
                                    new Bsk("гагарина", 1, 'L', 999, 29, 'L', 999, 3, 'н'), 
                                    new Bsk("гагарина", 2, 'L', 999, 24, 'L', 999, 3, 'ч'), 
                                    new Bsk("гагарина", 31, 'L', 999, 73, 'L', 999, 9, 'н'), 
                                    new Bsk("гагарина", 26, 'L', 999, 80, 'L', 999, 9, 'ч'), 
                                    new Bsk("гагарина", 77, 'L', 999, 79, 'L', 999, 4, 'н'), 
                                    new Bsk("гагарина", 84, 'L', 999, 100, 'L', 999, 4, 'ч'), 
                                    new Bsk("горная", 0, 'L', 999, 0, 'L', 999, 3, 'ч'), 
                                    new Bsk("горный переулок", 0, 'L', 999, 0, 'L', 999, 3, 'ч'), 
                                    new Bsk("горный малый переулок", 0, 'L', 999, 0, 'L', 999, 3, 'ч'), 
                                    new Bsk("депутатский переулок", 0, 'L', 999, 0, 'L', 999, 1, 'ч'), 
                                    new Bsk("дубровинская", 1, 'L', 999, 55, 'L', 999, 3, 'н'), 
                                    new Bsk("дубровинская", 2, 'L', 999, 36, 'L', 999, 3, 'ч'), 
                                    new Bsk("дубровинская", 57, 'L', 999, 73, 'L', 999, 2, 'н'), 
                                    new Bsk("дубровинская", 38, 'L', 999, 44, 'L', 999, 2, 'ч'), 
                                    new Bsk("дубровинская", 75, 'L', 999, 185, 'L', 999, 1, 'н'), 
                                    new Bsk("дубровинская", 48, 'а', 1, 144, 'L', 999, 1, 'ч'), 
                                    new Bsk("217 стрелковой дивизии", 1, 'L', 999, 21, 'L', 999, 5, 'н'), 
                                    new Bsk("217 стрелковой дивизии", 2, 'L', 999, 66, 'L', 999, 5, 'ч'), 
                                    new Bsk("карла маркса", 5, 'L', 999, 91, 'L', 999, 3, 'н'), 
                                    new Bsk("карла маркса", 8, 'L', 999, 84, 'L', 999, 3, 'ч'), 
                                    new Bsk("карла маркса", 93, 'L', 999, 229, 'L', 999, 8, 'н'), 
                                    new Bsk("карла маркса", 86, 'L', 999, 218, 'L', 999, 8, 'ч'), 
                                    new Bsk("королёва", 0, 'L', 999, 0, 'L', 999, 8, 'ч'), 
                                    new Bsk("кузнечный буерак переулок", 0, 'L', 999, 0, 'L', 999, 8, 'ч'), 
                                    new Bsk("ленинская", 1, 'L', 999, 49, 'L', 999, 3, 'н'), 
                                    new Bsk("ленинская", 2, 'L', 999, 68, 'L', 999, 3, 'ч'), 
                                    new Bsk("ленинская", 51, 'L', 999, 59, 'L', 999, 2, 'н'), 
                                    new Bsk("ленинская", 72, 'L', 999, 82, 'L', 999, 2, 'ч'), 
                                    new Bsk("ленинская", 61, 'L', 999, 81, 'L', 999, 1, 'н'), 
                                    new Bsk("ленинская", 90, 'L', 999, 112, 'L', 999, 1, 'ч'), 
                                    new Bsk("ленинская", 83, 'L', 999, 149, 'L', 999, 8, 'н'), 
                                    new Bsk("ленинская", 116, 'L', 999, 194, 'L', 999, 8, 'ч'), 
                                    new Bsk("луговая", 0, 'L', 999, 0, 'L', 999, 3, 'ч'), 
                                    new Bsk("матросовская", 1, 'L', 999, 23, 'L', 999, 1, 'н'), 
                                    new Bsk("матросовская", 2, 'L', 999, 26, 'L', 999, 1, 'ч'), 
                                    new Bsk("матросовская", 25, 'L', 999, 53, 'L', 999, 8, 'н'), 
                                    new Bsk("матросовская", 32, 'L', 999, 64, 'L', 999, 8, 'ч'), 
                                    new Bsk("матросовская", 59, 'L', 999, 59, 'L', 999, 5, 'н'), 
                                    new Bsk("матросовская", 68, 'L', 999, 78, 'L', 999, 5, 'ч'), 
                                    new Bsk("матросовский проезд", 0, 'L', 999, 0, 'L', 999, 8, 'ч'), 
                                    new Bsk("матросовский переулок", 0, 'L', 999, 0, 'L', 999, 5, 'ч'), 
                                    new Bsk("набережная", 1, 'L', 999, 15, 'L', 999, 2, 'н'), 
                                    new Bsk("набережная", 2, 'L', 999, 24, 'L', 999, 2, 'ч'), 
                                    new Bsk("набережная", 19, 'L', 999, 115, 'L', 999, 1, 'н'), 
                                    new Bsk("набережная", 26, 'L', 999, 118, 'L', 999, 1, 'ч'), 
                                    new Bsk("набережный проезд", 0, 'L', 999, 0, 'L', 999, 1, 'ч'), 
                                    new Bsk("народная", 1, 'L', 999, 23, 'L', 999, 2, 'н'), 
                                    new Bsk("народная", 2, 'L', 999, 24, 'L', 999, 2, 'ч'), 
                                    new Bsk("народная", 26, 'L', 999, 38, 'а', 1, 3, 'ч'), 
                                    new Bsk("народная", 29, 'L', 999, 39, 'L', 999, 3, 'н'), 
                                    new Bsk("народная", 41, 'L', 999, 45, 'L', 999, 9, 'н'), 
                                    new Bsk("народная", 40, 'L', 999, 56, 'L', 999, 9, 'ч'), 
                                    new Bsk("народная", 47, 'L', 999, 85, 'L', 999, 7, 'н'), 
                                    new Bsk("народная", 58, 'L', 999, 128, 'L', 999, 7, 'ч'), 
                                    new Bsk("народная", 87, 'L', 999, 89, 'L', 999, 6, 'н'), 
                                    new Bsk("народный переулок", 0, 'L', 999, 0, 'L', 999, 2, 'ч'), 
                                    new Bsk("октябрьская", 1, 'L', 999, 37, 'L', 999, 1, 'н'), 
                                    new Bsk("октябрьская", 2, 'L', 999, 42, 'L', 999, 1, 'ч'), 
                                    new Bsk("октябрьская", 89, 'L', 999, 143, 'L', 999, 8, 'н'), 
                                    new Bsk("октябрьская", 44, 'L', 999, 84, 'L', 999, 8, 'ч'), 
                                    new Bsk("октябрьский переулок", 0, 'L', 999, 0, 'L', 999, 8, 'ч'), 
                                    new Bsk("павловского", 1, 'L', 999, 17, 'L', 999, 2, 'н'), 
                                    new Bsk("павловского", 2, 'L', 999, 18, 'L', 999, 2, 'ч'), 
                                    new Bsk("павловского", 19, 'L', 999, 43, 'L', 999, 1, 'н'), 
                                    new Bsk("павловского", 24, 'L', 999, 40, 'L', 999, 1, 'ч'), 
                                    new Bsk("павловского", 45, 'L', 999, 81, 'L', 999, 8, 'н'), 
                                    new Bsk("павловского", 42, 'L', 999, 66, 'L', 999, 8, 'ч'), 
                                    new Bsk("павловского", 85, 'L', 999, 87, 'L', 999, 2, 'н'), 
                                    new Bsk("павловского", 84, 'L', 999, 92, 'L', 999, 2, 'ч'), 
                                    new Bsk("павловского", 91, 'L', 999, 125, 'L', 999, 5, 'н'), 
                                    new Bsk("павловского", 98, 'L', 999, 132, 'L', 999, 5, 'ч'), 
                                    new Bsk("первомайская", 33, 'L', 999, 67, 'L', 999, 9, 'н'), 
                                    new Bsk("первомайская", 60, 'L', 999, 66, 'L', 999, 9, 'ч'), 
                                    new Bsk("первомайская", 69, 'L', 999, 83, 'L', 999, 4, 'н'), 
                                    new Bsk("первомайская", 68, 'L', 999, 110, 'L', 999, 4, 'ч'), 
                                    new Bsk("петровского", 0, 'L', 999, 0, 'L', 999, 2, 'ч'), 
                                    new Bsk("печковского", 1, 'L', 999, 11, 'L', 999, 2, 'н'), 
                                    new Bsk("печковского", 2, 'L', 999, 10, 'L', 999, 2, 'ч'), 
                                    new Bsk("печковского", 13, 'L', 999, 47, 'L', 999, 3, 'н'), 
                                    new Bsk("печковского", 16, 'L', 999, 42, 'L', 999, 3, 'ч'),
                                    new Bsk("пешкова", 1, 'L', 999, 87, 'L', 999, 4, 'н'),
                                    new Bsk("пешкова", 2, 'L', 999, 84, 'L', 999, 4, 'ч'), 
                                    new Bsk("пешкова", 89, 'L', 999, 133, 'L', 999, 5, 'н'), 
                                    new Bsk("пешкова", 86, 'L', 999, 132, 'L', 999, 5, 'ч'), 
                                    new Bsk("победы", 1, 'L', 999, 29, 'L', 999, 4, 'н'), 
                                    new Bsk("победы", 2, 'L', 999, 26, 'L', 999, 4, 'ч'), 
                                    new Bsk("победы", 28, 'L', 999, 48, 'L', 999, 7, 'ч'), 
                                    new Bsk("победы", 31, 'L', 999, 49, 'L', 999, 7, 'н'), 
                                    new Bsk("победы", 51, 'L', 999, 133, 'L', 999, 6, 'н'), 
                                    new Bsk("победы", 50, 'L', 999, 106, 'L', 999, 6, 'ч'), 
                                    new Bsk("проезжий переулок", 0, 'L', 999, 0, 'L', 999, 5, 'ч'), 
                                    new Bsk("пролетарская", 1, 'L', 999, 21, 'L', 999, 7, 'н'), 
                                    new Bsk("пролетарская", 2, 'L', 999, 18, 'L', 999, 7, 'ч'), 
                                    new Bsk("пролетарская", 65, 'а', 1, 65, 'б', 2, 7, 'н'), 
                                    new Bsk("пролетарская", 2, 'а', 1, 2, 'а', 1, 9, 'ч'), 
                                    new Bsk("пролетарская", 23, 'L', 999, 29, 'L', 999, 9, 'н'), 
                                    new Bsk("пролетарская", 20, 'L', 999, 34, 'L', 999, 9, 'ч'), 
                                    new Bsk("пролетарская", 33, 'L', 999, 53, 'а', 1, 4, 'н'), 
                                    new Bsk("пролетарская", 36, 'L', 999, 56, 'L', 999, 4, 'ч'), 
                                    new Bsk("пролетарская", 55, 'L', 999, 59, 'а', 1, 10, 'н'), 
                                    new Bsk("пролетарская", 58, 'L', 999, 78, 'б', 2, 10, 'ч'), 
                                    new Bsk("пролетарский переулок", 1, 'L', 999, 19, 'L', 999, 10, 'н'), 
                                    new Bsk("пролетарский переулок", 2, 'L', 999, 20, 'L', 999, 10, 'ч'), 
                                    new Bsk("пролетарский переулок", 21, 'L', 999, 39, 'L', 999, 7, 'н'), 
                                    new Bsk("пролетарский переулок", 22, 'L', 999, 38, 'L', 999, 7, 'ч'), 
                                    new Bsk("поворинский проезд", 0, 'L', 999, 0, 'L', 999, 4, 'ч'), 
                                    new Bsk("речной проезд", 0, 'L', 999, 0, 'L', 999, 1, 'ч'), 
                                    new Bsk("рябиновая", 0, 'L', 999, 0, 'L', 999, 9, 'ч'), 
                                    new Bsk("садовая", 1, 'L', 999, 57, 'L', 999, 2, 'н'), 
                                    new Bsk("садовая", 2, 'L', 999, 66, 'L', 999, 2, 'ч'), 
                                    new Bsk("садовая", 59, 'L', 999, 141, 'L', 999, 1, 'н'), 
                                    new Bsk("садовая", 72, 'L', 999, 144, 'L', 999, 1, 'ч'), 
                                    new Bsk("садовое кольцо", 0, 'L', 999, 0, 'L', 999, 1, 'ч'), 
                                    new Bsk("садовый переулок", 0, 'L', 999, 0, 'L', 999, 2, 'ч'), 
                                    new Bsk("свободы", 107, 'L', 999, 163, 'L', 999, 9, 'н'), 
                                    new Bsk("свободы", 120, 'L', 999, 178, 'L', 999, 9, 'ч'), 
                                    new Bsk("свободы", 165, 'L', 999, 213, 'L', 999, 3, 'н'), 
                                    new Bsk("свободы", 215, 'L', 999, 231, 'L', 999, 2, 'н'), 
                                    new Bsk("свободы", 182, 'L', 999, 190, 'б', 2, 2, 'ч'), 
                                    new Bsk("свободы", 233, 'L', 999, 313, 'L', 999, 5, 'н'), 
                                    new Bsk("свободы", 192, 'L', 999, 202, 'L', 999, 5, 'ч'), 
                                    new Bsk("свободы переулок", 0, 'L', 999, 0, 'L', 999, 3, 'ч'), 
                                    new Bsk("советская", 25, 'L', 999, 37, 'L', 999, 2, 'н'), 
                                    new Bsk("советская", 2, 'L', 999, 28, 'L', 999, 2, 'ч'), 
                                    new Bsk("советская", 15, 'L', 999, 25, 'L', 999, 1, 'н'), 
                                    new Bsk("советская", 32, 'L', 999, 32, 'L', 999, 9, 'ч'), 
                                    new Bsk("советская", 39, 'L', 999, 71, 'L', 999, 5, 'н'), 
                                    new Bsk("советская", 34, 'L', 999, 56, 'L', 999, 5, 'ч'), 
                                    new Bsk("советская", 73, 'L', 999, 113, 'L', 999, 6, 'н'), 
                                    new Bsk("советская", 58, 'L', 999, 82, 'L', 999, 6, 'ч'), 
                                    new Bsk("студенческий переулок", 0, 'L', 999, 0, 'L', 999, 1, 'ч'), 
                                    new Bsk("суровикина переулок", 0, 'L', 999, 0, 'L', 999, 2, 'ч'),
                                    //new Bsk("суровикино переулок", 0, 'L', 999, 0, 'L', 999, 2, 'ч'),
                                    new Bsk("сенная", 1, 'L', 999, 29, 'L', 999, 4, 'н'), 
                                    new Bsk("сенная", 2, 'L', 999, 34, 'L', 999, 4, 'ч'), 
                                    new Bsk("сенная", 31, 'L', 999, 55, 'L', 999, 10, 'н'), 
                                    new Bsk("сенная", 36, 'L', 999, 58, 'L', 999, 10, 'ч'), 
                                    new Bsk("сенная", 168, 'L', 999, 168, 'L', 999, 6, 'ч'), 
                                    new Bsk("сенная", 57, 'L', 999, 71, 'L', 999, 6, 'н'), 
                                    new Bsk("сенная", 60, 'L', 999, 78, 'L', 999, 6, 'ч'), 
                                    new Bsk("середина", 1, 'L', 999, 45, 'L', 999, 10, 'н'), 
                                    new Bsk("середина", 2, 'L', 999, 52, 'L', 999, 10, 'ч'), 
                                    new Bsk("середина", 47, 'L', 999, 69, 'L', 999, 6, 'н'), 
                                    new Bsk("середина", 54, 'L', 999, 58, 'L', 999, 6, 'ч'), 
                                    new Bsk("третьяковская", 1, 'L', 999, 7, 'L', 999, 7, 'н'), 
                                    new Bsk("третьяковская", 2, 'L', 999, 10, 'L', 999, 7, 'ч'), 
                                    new Bsk("третьяковская", 9, 'L', 999, 63, 'L', 999, 6, 'н'), 
                                    new Bsk("третьяковская", 18, 'L', 999, 60, 'L', 999, 6, 'ч'), 
                                    new Bsk("третьяковский переулок", 0, 'L', 999, 0, 'L', 999, 6, 'ч'), 
                                    new Bsk("тургенева переулок", 0, 'L', 999, 0, 'L', 999, 4, 'ч'), 
                                    new Bsk("терешковой", 1, 'L', 999, 9, 'L', 999, 4, 'н'), 
                                    new Bsk("терешковой", 11, 'L', 999, 25, 'а', 1, 10, 'н'), 
                                    new Bsk("терешковой", 16, 'L', 999, 20, 'L', 999, 10, 'ч'), 
                                    new Bsk("устиновская", 1, 'L', 999, 13, 'L', 999, 1, 'н'), 
                                    new Bsk("устиновская", 2, 'L', 999, 14, 'L', 999, 1, 'ч'), 
                                    new Bsk("устиновская", 17, 'L', 999, 59, 'L', 999, 8, 'н'), 
                                    new Bsk("устиновская", 16, 'L', 999, 48, 'L', 999, 8, 'ч'), 
                                    new Bsk("устиновский проезд", 0, 'L', 999, 0, 'L', 999, 8, 'ч'), 
                                    new Bsk("устиновский переулок", 0, 'L', 999, 0, 'L', 999, 8, 'ч'), 
                                    new Bsk("урицкого", 0, 'L', 999, 0, 'L', 999, 8, 'ч'), 
                                    new Bsk("чкалова", 1, 'L', 999, 31, 'L', 999, 10, 'н'), 
                                    new Bsk("чкалова", 2, 'L', 999, 16, 'L', 999, 10, 'ч'), 
                                    new Bsk("чкалова", 33, 'L', 999, 55, 'L', 999, 7, 'н'), 
                                    new Bsk("чкалова", 57, 'L', 999, 69, 'L', 999, 6, 'н'), 
                                    new Bsk("элеваторный проезд", 0, 'L', 999, 0, 'L', 999, 10, 'ч'), 
                                    new Bsk("юности переулок", 0, 'L', 999, 0, 'L', 999, 9, 'ч'), 
                                    new Bsk("юбилейная", 1, 'L', 999, 45, 'L', 999, 9, 'н'), 
                                    new Bsk("юбилейная", 2, 'L', 999, 48, 'L', 999, 9, 'ч'), 
                                    new Bsk("юбилейная", 47, 'L', 999, 65, 'а', 1, 7, 'н'), 
                                    new Bsk("юбилейная", 50, 'L', 999, 90, 'L', 999, 7, 'ч'), 
                                    new Bsk("юбилейная", 63, 'L', 999, 101, 'L', 999, 5, 'н'), 
                                    new Bsk("юбилейная", 92, 'L', 999, 126, 'L', 999, 5, 'ч'), 
                                    new Bsk("юбилейный тупик", 0, 'L', 999, 0, 'L', 999, 5, 'ч'), 
                                    new Bsk("школа", 5, 'L', 999, 5, 'L', 999, 5, 'н'), 
                                    new Bsk("школа", 9, 'L', 999, 9, 'L', 999, 7, 'н'), 
                                    new Bsk("школа", 12, 'L', 999, 12, 'L', 999, 4, 'ч')};

    public VoiceRecognitionActivity() {
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(!AutoListen) {
            long Delay = System.currentTimeMillis() - PressedTime;
//        if(System.currentTimeMillis()-PressedTime > 500){
            if (Delay > 500) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажатие

                        if (!OnLine) {
                            PressedTime = System.currentTimeMillis();
                            StartSluhanie(-1);
                            OnLine = true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE: // движение
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                        StopSlushanie();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        //OnLine=
                        //StopSlushanie();
                        break;
                }
            } else {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    returnedText.setText("Харош дятлить :)\nПауза должна быть 500 мс, а у тебя " + String.valueOf(Delay));
            }
        }
        return false;
    }




    enum Orient {
        V,
        H,
        VH;

        private Orient() {
        }
    }

    private Orient getScreenOrientation() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return Orient.V;
        } else {
            return  Orient.H;
        }
    }

    public void StartSluhanie(int sZaderjkoyinMILLIS) {
        //this.speech.destroy();
        this.progressBar.setVisibility(View.VISIBLE);
        this.progressBar.setIndeterminate(true);
        if (sZaderjkoyinMILLIS != -1) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    StartSluhanie(-1);

                }
            }, sZaderjkoyinMILLIS);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO"}, 100);
        }

    }
    void StopSlushanie()
    {
        //if(System.currentTimeMillis()-PressedTime<500)this.speech.destroy();
        //else
            this.speech.stopListening();
    }
    public void Oshibka(String OshT) {
        this.Pic.setImageResource(R.drawable.nipanyatna);
        this.CurrPic = R.drawable.nipanyatna;
        Log.i(this.LOG_TAG, OshT);
        this.UchastokText.setScaleX(1.0F);
        this.UchastokText.setScaleY(1.0F);
        this.UchastokText.setText(OshT);
        this.returnedText.setText("");
    }

    public void Pauza(int millis) {
        try {
            Thread.sleep((long)millis);
        } catch (InterruptedException var3) {
        }

    }

    public void SerayaHernya(String m) {
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
    }

    public void SerayaHernyaLong(String m) {
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }

    public void FillULT() {
        if(!Zapolneno) {
            Zapolneno=true;
            this.ULT.clear();
            boolean UjeEst = false;
            Bsk[] var2 = this.Piska;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Bsk piska = var2[var4];
                UjeEst = false;
                Iterator var6 = this.ULT.iterator();

                while (var6.hasNext()) {
                    CUL ult = (CUL) var6.next();
                    if (ult.Street == piska.Street) {
                        UjeEst = true;
                        ult.AddDiap(piska);
                        break;
                    }
                }

                if (!UjeEst) {
                    this.ULT.add(new CUL(piska.Street));
                    ((CUL) this.ULT.get(this.ULT.size() - 1)).AddDiap(piska);
                }
            }

            Iterator var8 = this.ULT.iterator();

            while (var8.hasNext()) {
                CUL ult = (CUL) var8.next();
                ult.SortAtTheAddingEnd();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.returnedText = (TextView)this.findViewById(R.id.textView_Golos);
        this.AKYNText = (TextView)this.findViewById(R.id.textView_AKYN);
        this.UchastokText = (TextView)this.findViewById(R.id.textView_Uchastok);
        this.progressBar = (ProgressBar)this.findViewById(R.id.progressBar1);
        this.Pic = (ImageView)this.findViewById(R.id.imageViewPic);
        this.ALLscreen=(LinearLayout)this.findViewById(R.id.AllScreen);
        this.FillULT();
        this.progressBar.setVisibility(View.VISIBLE);
        this.speech = SpeechRecognizer.createSpeechRecognizer(this);
        OnLine =false;
        ALLscreen.setOnTouchListener(this);
        Log.i(this.LOG_TAG, "isRecognitionAvailable: " + SpeechRecognizer.isRecognitionAvailable(this));
        this.speech.setRecognitionListener(this);
        this.recognizerIntent = new Intent("android.speech.action.VOICE_SEARCH_HANDS_FREE");
        this.recognizerIntent.putExtra("android.speech.extra.LANGUAGE", "ru-RU");
        this.recognizerIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        this.recognizerIntent.putExtra("android.speech.extra.DICTATION_MODE", true);
        this.recognizerIntent.putExtra("android.speech.extra.MAX_RESULTS", 1);




        OnClickListener oclBtnOk = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                StartSluhanie(-1);

            }
        };

        //this.Pic.setOnClickListener(oclBtnOk);
        //this.Pic.setOnTouchListener(PicTouch);
        //this.




        //OnClickListener oclBtnOk = new OnClickListener(this);

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == 0) {
                    this.speech.startListening(this.recognizerIntent);
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
            default:
        }
    }

    public void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        if (this.speech != null) {
            this.speech.destroy();
            this.Pic.setImageResource(R.drawable.pereryv);
            if(AutoListen)this.StartSluhanie(100);
            Log.i(this.LOG_TAG, "destroy");
            OnLine=false;
        }

    }

    public void onBeginningOfSpeech() {
        Log.i(this.LOG_TAG, "onBeginningOfSpeech");
        this.progressBar.setIndeterminate(false);
        this.progressBar.setMax(10);
    }

    public void onBufferReceived(byte[] buffer) {
        Log.i(this.LOG_TAG, "onBufferReceived: " + buffer);
    }

    public void onEndOfSpeech() {
        Log.i(this.LOG_TAG, "onEndOfSpeech");
        this.progressBar.setIndeterminate(true);
        this.Pic.setImageResource(R.drawable.pereryv);
    }

    public void onError(int errorCode) {
        OnLine=false;
        String errorMessage = this.getErrorText(errorCode);
        Log.d(this.LOG_TAG, "FAILED " + errorMessage);
        if (!errorMessage.equals("No match")) {
            this.returnedText.setText(errorMessage);
        } else {
            this.Pic.setImageResource(R.drawable.pereryv);
        }

    }

    public void onEvent(int arg0, Bundle arg1) {
        Log.i(this.LOG_TAG, "onEvent");
    }

    public void onPartialResults(Bundle arg0) {
        Log.i(this.LOG_TAG, "onPartialResults----------------------");
    }

    public void onReadyForSpeech(Bundle arg0) {
        this.Pic.setImageResource(R.drawable.kto_tam);
        Log.i(this.LOG_TAG, "onReadyForSpeech");
        OnLine=true;
    }

    public int PoiskPoTablice(Bsk G) {
        int ChisloG = G.HouseStart * 1000;
        ArrayList<Integer> ResUch = new ArrayList();
        ResUch.clear();
        if (G.Est_LitStartD()) {
            ChisloG += G.LiterStartDec;
        }

        for(CUL t:ULT) {
            if (t.Street.equals(G.Street)) {
                for (Home h : t.Houmis) {
                    if (ChisloG == h.ChisloHL) {// точное попадание в границу
                        return h.Uchastok;
                    }
                    if (h.ChisloHL == 0) {
                        ResUch.add(0, h.Uchastok);
                    } else if ((G.ChetNechet == h.ChetNe4et || h.ChetNe4et == 'п') && ChisloG > h.ChisloHL) {
                        switch (h.DiapLR) {
                            case L:
                                ResUch.add(0, h.Uchastok);
                                break;
                            case R:
                                ResUch.remove(0);
                        }
                    }
                }
            }
        }

        if (ResUch.size() == 0) {// просто не найдено
            return -1;
        }

        if (ResUch.size() > 1) {//больше одного участка
            return -2;
        }

        return (Integer)ResUch.get(0);
    }

    public void onResults(Bundle results) {
        boolean Pereslushat = false;
        OnLine=false;
        Log.i(this.LOG_TAG, "onResults");
        ArrayList<String> GolosList = results.getStringArrayList("results_recognition");
        if (GolosList.size() > 0) {
            this.G.Forget();
            String text = (String)GolosList.get(0);
            text = text.toLowerCase();
            this.AKYNText.setText(text);
            GolosList.clear();
            String[] var5 = text.split(" ");
            int var6 = var5.length;


            int HousePos;
            for(HousePos = 0; HousePos < var6; ++HousePos) {
                String retval = var5[HousePos];
                GolosList.add(retval);
            }

            if (GolosList.size() <= 1) {
                this.Oshibka("Ощущается некая\n\r недосказанность... ");
            } else {
                int GolosCount = GolosList.size() - 1;
                int KorpusPos = -1;
                HousePos = GolosCount;
                int LiterPos = -1;
                String mayBeLiter = (String)GolosList.get(GolosCount);
                if (mayBeLiter.equals("анал")) {
                    this.Oshibka("Кто же тебя \n\r такому научил?)))");
                } else {
                    HUIbreak: {
                        if (((String)GolosList.get(GolosCount - 1)).equals("корпус")) {
                            LiterPos = GolosCount;
                            HousePos = GolosCount - 2;
                            var6 = GolosCount - 1;
                        } else {
                            if (mayBeLiter.matches("^([а-яё]+)$")) {
                                LiterPos = GolosCount;
                                HousePos = GolosCount - 1;
                            }

                            if (mayBeLiter.matches("^([a-z]+)$")) {
                                this.Oshibka("Я не ем буквы - это опасно");
                                break HUIbreak;
                            }
                        }

                        if (LiterPos != -1) {
                            if (mayBeLiter.matches("^([а-яё]+)$")) {
                                if (mayBeLiter.length() <= 1) {
                                    this.Oshibka("Я не ем буквы - это опасно");
                                    break HUIbreak;
                                }

                                this.G.LiterStart = mayBeLiter.charAt(0);
                                this.G.LiterStartDec = this.Alp.indexOf(mayBeLiter.charAt(0));
                            } else {
                                try {
                                    this.G.LiterStartDec = Integer.parseInt((String)GolosList.get(LiterPos));
                                } catch (NumberFormatException var14) {
                                    this.Oshibka("Не расслышал. Литера не оцифровывается(");
                                    break HUIbreak;
                                }
                            }
                        }

                        try {
                            this.G.HouseStart = Integer.parseInt((String)GolosList.get(HousePos));
                        } catch (NumberFormatException var12) {
                            this.Oshibka("Не расслышал. Номер дома не число(");
                            break HUIbreak;
                        } catch (ArrayIndexOutOfBoundsException var13) {
                            this.Oshibka("Не расслышал. Номер дома - какое-то говно:" + HousePos);
                            break HUIbreak;
                        }

                        this.G.Street = (String)GolosList.get(0);
                        if (HousePos < 1) {
                            this.Oshibka("Не расслышал. \n\b Позиция дома меньше 2. ");
                        } else {
                            for(int i = 1; i < HousePos; ++i) {
                                StringBuilder var10000 = new StringBuilder();
                                Bsk var10002 = this.G;
                                var10002.Street = var10000.append(var10002.Street).append(" ").append((String)GolosList.get(i)).toString();
                            }

                            if (this.G.HouseStart % 2 == 0) {
                                this.G.ChetNechet = 'ч';
                            } else {
                                this.G.ChetNechet = 'н';
                            }

                            String Perenos = " ";
                            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                                this.Pic.setImageResource(R.drawable.vert);
                                Perenos = "\n";
                            } else {
                                this.Pic.setImageResource(R.drawable.hor);
                            }
                            if(G.Street.equals("пешковского"))G.Street= "печковского";
                            if(G.Street.equals("суровикино переулок"))G.Street= "суровикина переулок";

                            String ResultPoGolosu = this.G.Street + Perenos + "Дом: " + this.G.HouseStart;
                            if (this.G.Est_LitStartD()) {
                                if (this.G.Est_LitStart()) {
                                    ResultPoGolosu = ResultPoGolosu +  " Корпус: " + this.G.LiterStart;
                                } else {
                                    ResultPoGolosu = ResultPoGolosu +  " Корпус: " + this.G.LiterStartDec;
                                }
                            }

                            ResultPoGolosu = ResultPoGolosu.substring(0, 1).toUpperCase() + ResultPoGolosu.substring(1);
                            this.returnedText.setText(ResultPoGolosu);
                            this.G.Uchastok = this.PoiskPoTablice(this.G);
                            switch(this.G.Uchastok) {
                                case -2:
                                    this.UchastokText.setText("0_o");
                                    this.Oshibka("Найдено больше одного участка");
                                    break;
                                case -1:
                                    this.Pic.setImageResource(R.drawable.ne_naydeno);
                                    this.UchastokText.setScaleX(1.0F);
                                    this.UchastokText.setScaleY(1.0F);
                                    this.UchastokText.setText("СОЖГИ\n\r:)");
                                    this.Pauza(500);
                                    break;
                                default:
                                    this.UchastokText.setScaleX(2.0F);
                                    this.UchastokText.setScaleY(2.0F);
                                    this.UchastokText.setText(Integer.toString(this.G.Uchastok));
                                    this.UchastokText.setBackgroundColor(0xFF4CAF50);
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Chernenie();
                                        }
                                    }, 2000);

                            }
                        }
                    }
                }
            }
            boolean Gek=false;
            if(text.contains("слушай постоянно")){AutoListen=true;Gek=true;}
            if(text.contains("слушай через раз")){AutoListen=false;Gek=true;}
            if(text.contains("замри")){
                Gek=true;
                switch (getScreenOrientation()){
                    case V:
                        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //для портретного режима
                        break;
                    case H:
                        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //для альбомного режима
                        break;
                }
            }
            if(text.contains("отомри")) {setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);Gek=true;}

            if(Gek){this.UchastokText.setText("гэк"); this.Pic.setImageResource(R.drawable.gek);}



                if (!text.contains("да никто")) {
                if(AutoListen)this.StartSluhanie(500);
            } else {
                this.Pic.setImageResource(R.drawable.da_nikto);
                this.UchastokText.setText("");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ExitNahui();
                    }
                }, 1000);

            }
        }

    }

    void Chernenie()
    {this.UchastokText.setBackgroundColor(0x0);}
    void ExitNahui() {
        this.finish();
    }

    public void onRmsChanged(float rmsdB) {
        this.progressBar.setProgress((int)rmsdB);
    }

    public String getErrorText(int errorCode) {
        String message;
        //OnLine=true;
        Pic.setImageResource(R.drawable.nipanyatna);
        switch(errorCode) {
            case 1:
                message = "Network timeout";
                break;
            case 2:
                message = "Network error";
                break;
            case 3:
                message = "Audio recording error";
                break;
            case 4:
                message = "error from server";
                break;
            case 5:
                message = "Не барабань :)";
                StopSlushanie();
                if(AutoListen)this.StartSluhanie(-1);
                break;
            case 6:
                message = "ну и кто там-то?";
                Pic.setImageResource(R.drawable.pereryv);
                if(AutoListen)this.StartSluhanie(-1);
                break;
            case 7:
                message = "No match";
                if(AutoListen)this.StartSluhanie(-1);
                break;
            case 8:
                message = "занято. Если долго не алё - поверни телефон";
                //this.speech.destroy();
                break;
            case 9:
                message = "Insufficient permissions";
                break;
            default:
                message = "Didn't understand, please try again.";
        }

        return message;
    }
}
