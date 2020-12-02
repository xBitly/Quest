package ru.xbitly.quest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView title_story, text_story, hp, brains, stars, variant1, variant2, variant3;
    Button one_button, two_button, three_button;
    LinearLayout restart_button;
    public static Person manager;
    public static Story story;

    Situation[] mas_situation =
            {
                    //Первая ситуация
                            new Situation(
                                    "ДЕТСКИЙ САД",
                                    "Москва. Вы только пришли в детский сад и тут же злая воспитательница застаила " +
                                            "делать рассказ о любимом городе.\n" + "Известно что воспитаельница из Рязани.",
                                    "Списать всё у Ильи", "Сделать рассказ о Москве",
                                    "Сделать рассказ о Рязани", 3, 0, 0, 0),




                    //Исход при выборе варианта номер 1
                            new Situation(
                                    "ТЕМНЫЙ ЛЕС",
                                    "Списав всё у Ильи воспитательница понял, что вы списывали, так еще и списали плохой рассказ," +
                                            " вас отчислили из детского сада, вас украл эльф и утащил в темный лес.",
                                    "Остаться жить с эльфом", "Попытаться сбежать от эльфа",
                                    "Молиться о пощаде", 3, -50, -10, -10),

                    //Исход при выборе варианта номер 2
                            new Situation("ТЕМНЫЙ ЛЕС",
                                    "Написав о Москве, вы стали умнее, но воспитательница была злой и оставила вас " +
                                            "навсегда в саду. Эльф к концу вашей жизни украл вас в лес.", "Остаться жить с эльфом",
                                    "Попытаться сбежать от эльфа", "Молиться о пощаде",
                                    3, -20, 20, -10),

                    //Исход при выборе варианта номер 3
                            new Situation("ШКОЛА",
                                    "Вы растрогали воспитательницу, показали, что достойны обучаться в школе " +
                                            "и вас отправили в 5 класс. Урок математики, контрольная работа. Вы не готовились.",
                                    "Списать у Коли", "Сделать самостоятельно", "Сказать учительнице всё, как есть",
                                    3, 5, 70, 90),




                    //После выбора варианта 1 исход при выборе 1
                            new Situation("ТЕМНЫЙ ЛЕС", "Оставишь жить с эльфом, вы прожили долгую и счастливую жизнь," +
                                    " но в конце концов умерли.", "Конец", "Конец", "Конец",
                                    0, -50, 0, 0),

                    //После выбора варианта 1 исход при выборе 2
                            new Situation("ТЕМНЫЙ ЛЕС", "Эльф вас догнал и заставил остаться," +
                                     " спустя 20 лет вы умерли.", "Конец", "Конец", "Конец",
                                    0, -50, 0, 0),

                    //После выбора варианта 1 исход при выборе 3
                            new Situation("ДОМ", "Эльф пощадил вас и отвел домой," +
                                    " вы больше никогда не выходили на улицу.", "Конец", "Конец", "Конец",
                                    0, -10, 10, 0),




                    //После выбора варианта 2 исход при выборе 1
                            new Situation("ТЕМНЫЙ ЛЕС", "Оставишь жить с эльфом, вы прожили долгую и счастливую жизнь," +
                                    " но в конце концов умерли.", "Конец", "Конец", "Конец",
                                    0, -80, 0, 0),

                    //После выбора варианта 2 исход при выборе 2
                            new Situation("ТЕМНЫЙ ЛЕС", "Эльф вас догнал и заставил остаться," +
                                    " спустя 20 лет вы умерли.", "Конец", "Конец", "Конец",
                                    0, -80, 0, 0),

                    //После выбора варианта 2 исход при выборе 3
                            new Situation("ДОМ", "Эльф пощадил вас и отвел домой," +
                                    " вы больше никогда не выходили на улицу.", "Конец", "Конец", "Конец",
                                    0, -10, 10, 0),




                    //После выбора варианта 3 исход при выборе 1
                            new Situation("ЛЕС", "Коля, как оказалось хорошо знал математику, " +
                                    "вы получили 5, окончили школу и стали жить с эльфом, он рубил вам дрова всю оставшуюся жизнь.",
                                    "Конец", "Конец", "Конец",
                                    0, +10, 10, 5),

                    //После выбора варианта 3 исход при выборе 2
                            new Situation("ЛЕС", "Вы пишете контрольную работу на 2, вас отчисляют и вы живете " +
                                    "с эльфом в лису, помогая ему рубить дрова.",
                                    "Конец", "Конец", "Конец",
                                    0, -10, -10, -5),

                    //После выбора варианта 3 исход при выборе 3
                            new Situation("ШКОЛА", "Вы умерли.",
                                    "Конец", "Конец", "Конец",
                                    0, -105, -40, -60),
            };

    int road_of_story = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        variant1 = findViewById(R.id.variant_one);
        variant2 = findViewById(R.id.variant_two);
        variant3 = findViewById(R.id.variant_three);
        title_story = findViewById(R.id.title_story);
        text_story = findViewById(R.id.text_story);
        hp = findViewById(R.id.hp);
        brains = findViewById(R.id.brain);
        stars = findViewById(R.id.stars);
        one_button = findViewById(R.id.one_button);
        two_button = findViewById(R.id.two_button);
        three_button = findViewById(R.id.three_button);
        restart_button = findViewById(R.id.restart_button);

        manager = new Person();
        story = new Story(mas_situation[road_of_story]);

        manager.health += story.current_situation.dHealth;
        manager.brain += story.current_situation.dBrain;
        manager.star += story.current_situation.dStar;

        show_info_story();

        one_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_current_road_story(1);
                play_story();
                show_info_story();
            }
        });

        two_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_current_road_story(2);
                play_story();
                show_info_story();
            }
        });

        three_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_current_road_story(3);
                play_story();
                show_info_story();
            }
        });

        restart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_alert_dialog();
            }
        });
    }

    public void set_current_road_story(int factor){
        if(road_of_story == 0){
            road_of_story = factor;
        }else{
            road_of_story = factor + 3*road_of_story;
        }
    }

    public void show_info_story(){
        manager.health += story.current_situation.dHealth;
        manager.brain += story.current_situation.dBrain;
        manager.star += story.current_situation.dStar;

        hp.setText(Integer.toString(manager.health));
        brains.setText(Integer.toString(manager.brain));
        stars.setText(Integer.toString(manager.star));
        title_story.setText(story.current_situation.title);
        text_story.setText(story.current_situation.text);
        variant1.setText(story.current_situation.variant1);
        variant2.setText(story.current_situation.variant2);
        variant3.setText(story.current_situation.variant3);
    }

    public void play_story(){
        if(story.isEnd()){
            show_alert_dialog();
        }else{
            story = new Story(mas_situation[road_of_story]);
        }
    }

    public void show_alert_dialog(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View view = inflater.inflate(R.layout.dialog_text_view, null);
        dialog.setView(view);


        dialog.setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();
        alert.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red));
        Objects.requireNonNull(alert.getWindow()).setBackgroundDrawableResource(R.drawable.dialog_background);

    }

}