import java.io.*;
import java.util.*;
import java.security.AccessControlException;

public class StageAnalyze {
	private Enemy[] _enemy = new Enemy[100];
	public Enemy[] GetTemporaryEnemy(){return _enemy;}
	private String[] _str = new String[100];		// �G�̐��ۑ�
	private String[] _token = new String[10];		// ���������ۑ�
	private int _numStr = 0;		// �s���ۑ�
	public int GetStringNumber(){return _numStr;}

	private LinkedList<String[]> _tokenList;
	public LinkedList<String[]> GetScenario(){return _tokenList;}
	
	// �X�e�[�W�f�[�^�����
	public void Analyze(String filePath)  throws AccessControlException
	{
		String temp = null;
		StringTokenizer st;
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader buffererdReader = null;

		try
		{
			inputStreamReader=new InputStreamReader(new FileInputStream(filePath));
			buffererdReader = new BufferedReader(inputStreamReader);

			_tokenList = new LinkedList<String[]>();
			
			//	�Ƃ肠������s���ǂ�
			while (buffererdReader.ready()){
				// �R�����g�@��΂�
				if((temp = buffererdReader.readLine()).startsWith("//")) continue;
				// �{�X�̏����͂����ŉ��
				else if(temp.startsWith("*"))
				{
					// �{�X�N���X��new���Ă��̃N���X�ňꎞ�I�ɃC���X�^���X��ۑ�����΂����񂶂�Ȃ����Ȃ��H
					// �{�X�N���X�͊K�w�\���ɂ��Ĕj��\�ȃp�[�c�����������肷���STG���ۂ����
					// ���͖������Ȃ̂Ŏ���
					continue;
				}

				// ��������ʏ�G
				// �G�̔z�u�����͂�����ƕ��G��
				_str[_numStr] = temp;
				// ��������
				st = new StringTokenizer(_str[_numStr],",");
			
				_token = new String[10];
				// ���������g�[�N�����ꎞ�I�Ɋi�[
				for(int i=0; st.hasMoreTokens(); i++) {
						_token[i] = st.nextToken();
				}
				
				if(_token[0] != null)
				{
					_tokenList.add(_token);
					_numStr++;
				}
				// �f�o�b�O�p�ɓǂݍ��񂾃X�e�b�v����\��
				
				System.out.print("Token:");
				for(String[] s: _tokenList)
				{
					for(int i=0; i<s.length;i++)
					{
						System.out.print(s[i]+",");
					}
					System.out.println();
				}
				System.out.println();
			}
		}catch ( IOException e ){
			return;
		}finally{
			try{
				if(inputStreamReader!=null){
					inputStreamReader.close();
				}
				if(fileInputStream!=null){
					fileInputStream.close();
				}
				if(buffererdReader!=null){
					buffererdReader.close();
				}
			}catch ( IOException e ){
				return;
			}
		}
	}
	
	public Enemy GetTemporaryEnemy(EnemyManager em, int num)
	{
		String[] tempString = _tokenList.get(num);
		Enemy temp = new Enemy(em);
		temp.SetAppearTime(new Integer(tempString[0]).intValue());
		temp.SetPos((new Float(tempString[1]).floatValue()),(new Float(tempString[2]).floatValue()));
		temp.SetVX(new Float(tempString[3]).floatValue());
		temp.SetVY(new Float(tempString[4]).floatValue());
		temp.SetHP(new Integer(tempString[5]).intValue());
		temp.SetDEF(new Integer(tempString[6]).intValue());
		// ������state�p�^�[����strategy�p�^�[�����g���Ă��炩���ߗp�ӂ�����Ԃ�o�^����`���ɂ��Ă������E�E�E�Ƃ������ނ��낻�̕�������
		temp.SetBulletIntvl(new Integer(tempString[7]).intValue());
		temp.SetBulletType(new Integer(tempString[8]).intValue());
		temp.SetBulletSpeed(new Integer(tempString[9]).intValue());
	
		return temp;
	}
}
