import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

// �I�����̃��[�h
// �I�����Ȃ��Ń^�C�g���߂��Ă�����
public class ExitState implements ModeState{

	@Override
	public void init() {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void Show(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 16));
		g2.setPaint(Color.yellow);
		g2.drawString("�I���ł��B",10, 100);
		g2.drawString("���̂܂܃A�v���b�g�E�B���h�E����Ă��������B", 10,120);
	}

	@Override
	public void run(GameManager gm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void KeyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void KeyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void KeyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
