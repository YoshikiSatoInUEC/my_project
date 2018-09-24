import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// ������MVC�ł�����V��C�ɂ�����

// MVC�̊ϓ_�őg�ނƂ����
// M�̓Q�[���S�̂̏�ԂƂ���ɑ΂��鏈�����Ǘ�����GameManager�N���X
// V��JPanel�ł̕\���BGameManager�̏�Ԃɉ����ď���������
// �ǂ����L�[���͂Ȃ��C��JPanel��KeyListener�����Ċ撣���Ă��炤
// GameManager�ɂ͏�Ԃ��������A�����؂�ւ�������
// state�p�^�[���ɂ܂Ƃ߂�Γ��͌n����������

public class STG extends JPanel implements Runnable, KeyListener{

	public static Thread mainThread = null;
	// ���C���֐�
	public static void main(String args[])
	{
		// �K����J�t���[����p��
		JFrame frame = new JFrame();

		// ���C���p�l���i�V���[�e�B���O�����s����p�l���j��V�K�쐬
		STG app = new STG();
		// �t���[���ɓo�^
		frame.getContentPane().add(app);
		// �e��t���[���̐ݒ�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �E�B���h�E�X�^�C��
		frame.setBounds(10, 10, 480, 640); // �E�B���h�E�T�C�Y
		frame.setTitle("Templete Shooting"); // �^�C�g��
		frame.setVisible(true); // ������悤�ɂ��Ȃ��Ƃ�

		// ���C���X���b�h��
		mainThread = new Thread(app);
		
		// �ݒ�I������̂Ń��C���p�l�����������ĊJ�n
		app.init();
	}
	
	// �Q�[���}�l�[�W���̕ێ�
	private GameManager _gmanager;

	// �`��Ώۃo�b�t�@
	private Image buffer;
	private Graphics bufferGraphics;
	
	public void init(){
		setBackground(Color.black);
		setForeground(Color.white);

		if (buffer == null){
			buffer = createImage(480, 640);
			bufferGraphics = buffer.getGraphics();
		}

		addKeyListener(this);
		requestFocus();
		
		_gmanager = new GameManager(this);
		
	    mainThread.start();
	}


	// �X���b�h�œ����֐���������Ȃ�
	public void run(){
		while (true){
			try{
				Thread.sleep(20);	// FPS�����E�E�E�ł������������Ă邩�炠��܊֌W�˂�����
			}catch (InterruptedException e){
				break;
			}

			Graphics2D g2 = (Graphics2D) bufferGraphics;	// 2D�g������

			g2.setBackground(Color.black);
			g2.clearRect(0, 0, 480, 640);

			// �A���`�G�C���A�V���O
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(4.0f));

			// �Q�[��������
			_gmanager.GameMainUpdate();	
			
			// ���̕ӂŕ`��
			ShowObjects(g2);
			
			// ��߲��
			repaint();
		}
	}

	// �`�施��
	public void ShowObjects(Graphics2D g2)
	{
		_gmanager.State().Show(g2);
	}
	
	// �ĕ`�施�߂̍ۂɂ͂���𒣂�Ȃ���
	public void paintComponent(Graphics g){
			g.setColor(Color.black);
			g.clearRect(0, 0, 480, 640);
			g.drawImage(buffer, 0, 0, this);
	}

	// ���͌n�B��Ԃɂ��؂�ւ���
	public void keyPressed(KeyEvent e){
		_gmanager.State().KeyPressed(e);
	}

	public void keyReleased(KeyEvent e){
		_gmanager.State().KeyReleased(e);
	}
	public void keyTyped(KeyEvent e)
	{
		_gmanager.State().KeyTyped(e);
	}

}
