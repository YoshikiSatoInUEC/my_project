/*
 * �쐬��: 2004/08/24
 *
 * ���̐������ꂽ�R�����g�̑}�������e���v���[�g��ύX���邽��
 * �E�B���h�E > �ݒ� > Java > �R�[�h���� > �R�[�h�ƃR�����g
 */

/**
 * @author Administrator
 *
 * ���̐������ꂽ�R�����g�̑}�������e���v���[�g��ύX���邽��
 * �E�B���h�E > �ݒ� > Java > �R�[�h���� > �R�[�h�ƃR�����g
 */
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class Fighter extends BaseObject{
	protected int nLeft;	// �c�@��

	// �L�[���͂̃t���O
	protected boolean bKeyLeft;
	protected boolean bKeyRight;
	protected boolean bKeyUp;
	protected boolean bKeyDown;
	protected int bKeyZ;

	public final static int KB_NONE	=	0;
	public final static int KB_TRIG	= 	1;
	public final static int KB_PUSH	= 	2;
	public final static int KB_PULL	=	3;

	private int numShot;	// ��ʕ\���V���b�g��
	private int numValidShot; // ���ݍ���V���b�g��
	private int shotTimer = 0;

	private Shot shot[];

	// �����N���X�����ꂽ���@�V���b�g�Ǘ��N���X
	// ���퓬�@�̓V���b�g�����������󂯎����B�ړ��̓V���b�g���g���s��
	class Shot extends BaseObject{

		// �R���X�g���N�^
		public Shot()
		{
			super();
		}

		// �e�ړ�
		public void Move()
		{
			if(!this.isEnable) return;

			if(fY >= 0)
			{
				fX += fVX;
				fY += fVY;
			}
			else
				this.Enable(false);
		}

		// �e�\��
		public void Show(Graphics2D g2)
		{
			if(!this.isEnable) return;

			g2.setPaint(Color.white);
			g2.fill(new Ellipse2D.Double(fX - 10f, fY - 10f, 10f, 20f));

		}
	}

	public Fighter()
	{
		super();
		nLeft = 0;
		bKeyLeft = bKeyRight = bKeyUp = bKeyDown =  false;

		bKeyZ = KB_NONE;

		numShot = 6;
		numValidShot = 6;

		shot = new Shot[numShot];
		for(int i=0; i<numShot; i++)
		{
			shot[i] = new Shot();
		}
	}

	public Shot[] GetShot()
	{
		return shot;
	}

	public int GetNumShot()
	{
		return numShot;
	}


	// ���̂ւ�ꏏ�̊֐��ɂ��o���邯�ǖ��O�̕t�����߂�ǂ������̂�
	public void Show(Graphics2D g2)
	{
		// �V���b�g
		for(int i=0; i<numShot; i++)
		{
			shot[i].Show(g2);
		}

		// ���@
		if(!isEnable) return;

		g2.setPaint(Color.blue);
		g2.fill(new Arc2D.Double( (int)fX - 55, (int)fY - 55, 110, 110, 250, 40, Arc2D.PIE));
		g2.setPaint(Color.yellow);
		g2.fill(new Arc2D.Double( (int)fX - 40, (int)fY + 30, 10, 15, 0, 360, Arc2D.PIE));
		g2.fill(new Arc2D.Double( (int)fX + 30, (int)fY + 30, 10, 15, 0, 360, Arc2D.PIE));
	}

	// �ړ�
	public void Move()
	{
		// �V���b�g
		for(int i=0; i<numShot; i++)
		{
			shot[i].Move();
		}

		if(!isEnable) return;

		if(bKeyLeft)
		{
			if(fX >= 0)
	 			fX -= fVX;
		}
		else if(bKeyRight)
		{
			if(fX <= 480)
				fX += fVX;
		}

		if(bKeyUp)
		{
			if(fY >= - 30)
				fY -= fVY;
		}
		else if(bKeyDown)
		{
			if(fY <= 610)
				fY += fVY;
		}
	}


	// �V���b�g����
	public void Shoot()
	{
		if(!isEnable) return;

		// �{�^���������ς���Ȃ��ă{�^���ŏ��ɉ�������
		if(bKeyZ == KB_TRIG)
		{
			shotTimer = 0;
		}

		// �{�^��������Ƃ邩�[�H
		if(bKeyZ == KB_PUSH)
		{
			// 2��Ɉ�����
			if(shotTimer % 2 == 0)
			{
				if(numValidShot>=2)
				{
					// 2�R��肽���̂�
					for(int i=0; i<2; i++)
					{
						// �܂��������邩�ǂ��������
						for(int j=0; j<numShot; j++)
						{
							if(shot[j].isEnable) continue;
							shot[j].SetVX(2-4*i);
							shot[j].SetVY(-40);
							shot[j].SetPos(Fighter.this.GetX() - 20 + 50*i, Fighter.this.GetY());
							shot[j].Enable(true);
							break;
						}
					}
				}
			}
			shotTimer++;
		}
	}


	// �{�^�������Ă�Ƃ�
	public void KeyPressedAnalyze(KeyEvent e)
	{
		if(!isEnable) return;

		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			bKeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			bKeyRight = true;
			break;
		case KeyEvent.VK_UP:
			bKeyUp = true;
			break;
		case KeyEvent.VK_DOWN:
			bKeyDown = true;
			break;
		case KeyEvent.VK_Z:
			bKeyZ	= KB_PUSH;
			break;
		}
	}

	// �{�^���������Ƃ�
	public void KeyReleasedAnalyze(KeyEvent e)
	{
		if(!isEnable) return;

		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			bKeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			bKeyRight = false;
			break;
		case KeyEvent.VK_UP:
			bKeyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			bKeyDown = false;
			break;
		case KeyEvent.VK_Z:
			bKeyZ	= KB_PULL;
			break;
		}
	}

	// �{�^���������u��
	public void KeyTypedAnalyze(KeyEvent e)
	{
		if(!isEnable) return;

		switch(e.getKeyCode())
		{
		case KeyEvent.VK_Z:
			bKeyZ	= KB_TRIG;
			break;
		}
	}

}
