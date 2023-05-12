import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Battle extends JFrame {

    private DPanel dPanel;

    public static void main(String[] args) {
        PlayMusic("Battle/back.wav");


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Battle frame = new Battle();
                    frame.setVisible(true);
                    frame.addContents();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Battle() {
        super("Battle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        dPanel = new DPanel();
        Color myColor1 = new Color(225, 198, 153);
        dPanel.setBackground(myColor1);
        dPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        dPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(dPanel);

        //Animator updates the positions of elements and repaints the battle scene
        Animator animator = new Animator(this);

        //Timer alerts animator every 50 ms
        Timer timer = new Timer(50, animator);

        timer.start();
    }
    public void addContents() {
        DPanel dPanel = this.getDrawingPanel();

        // Generate random numbers between 5 and 10, and generate rocks, papers and scissors of such numbers
        int num = (int)(Math.random() * 6) + 5;
        for (int i = 0; i < num; i++) {
            dPanel.addDrawableItem(new Element(dPanel, "Battle/rock.png", 3.0, 1));
        }

        num = (int)(Math.random() * 6) + 5;
        for (int i = 0; i < num; i++) {
            dPanel.addDrawableItem(new Element(dPanel, "Battle/paper.png", 3.0, 2));
        }

        num = (int)(Math.random() * 6) + 5;
        for (int i = 0; i < num; i++) {
            dPanel.addDrawableItem(new Element(dPanel, "Battle/scissors.png", 3.0, 3));
        }

        //Set elements on the battle scene
        dPanel.initializeEverything();

    }

    public DPanel getDrawingPanel() {
        return dPanel;
    }

    public void setDrawingPanel(DPanel dPanel) {
        this.dPanel = dPanel;
    }

    //Play background music continuously
    public static void PlayMusic(String location){
        try{
            File musicPath = new File(location);
            if(musicPath.exists()){

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else{
                System.out.println("cant find file");
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
