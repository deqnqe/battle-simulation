import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Animator implements ActionListener {

    private Battle battle;

    public Animator(Battle battle) {
        this.setBattle(battle);
    }

    public void actionPerformed(ActionEvent arg0) {
        Battle battle = this.getBattle();
        DPanel dPanel = battle.getDrawingPanel();

        // Move one step and repaint
        dPanel.moveEverythingOneStep();
        dPanel.repaint();
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

}
