package action;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import dao.studentDao;
import model.student;

public class Server {

	public static void main(String[] args) throws SQLException{
		studentDao sd = new studentDao();
		student stu = new student();
		String sendmsg = null;
		try {
			DatagramSocket rece = new DatagramSocket(10001);
			DatagramSocket send = new DatagramSocket();
			byte[] buf = new byte[1024];
			DatagramPacket dp= new DatagramPacket(buf, buf.length);
			DatagramPacket send_dp;
			while (true) {
			
				rece.receive(dp);
				String ip = dp.getAddress().getHostAddress();
				String text = new String(dp.getData(), 0, dp.getLength(),"utf-8");
				System.out.println(ip + ":" + text);
				
				String[] str = text.split(",");
				switch(str[0])
				{
				case "studyid_find":
					try{
						stu = sd.studyid_get(str[1]);
						System.out.println(stu.toString());
						sendmsg = stu.toString();
					}
					catch(Exception e){
						sendmsg = "ѧ�Ų�ѯʧ��,���ܲ��޴��ˣ�";
						System.out.println("ѧ�Ų�ѯʧ�ܣ�");
					}
					break;
					
				case "name_find":
					try{
						stu = sd.name_get(str[1]);
						System.out.println(stu.toString());
						sendmsg = stu.toString();
						System.out.println("������ѯ�ɹ���");
					}
					catch(Exception e){
						sendmsg = "������ѯʧ�ܣ�";
						System.out.println("������ѯʧ�ܣ�");
					}
					break;
				case "addstu":
					try{
						stu.setName(str[1]);
						stu.setStudyid(str[2]);
						stu.setAge(Integer.valueOf(str[3]));
						stu.setSex(str[4]);
						stu.setAddress(str[5]);
						stu.setFrom(str[6]);
						stu.setStdclass(str[7]);
						stu.setGrade(str[8]);
						stu.setFdyname(str[9]);
						stu.setEmail(str[10]);
						stu.setPhonenum(str[11]);
						stu.setBirthday(Date.valueOf(str[12]));
						stu.setCollege(str[13]);
						stu.setCreate_date(new java.sql.Date( new java.util.Date().getTime()));
						stu.setUpdate_date(new java.sql.Date( new java.util.Date().getTime()));
						System.out.println("׼����ӡ������Ϣ��");
						System.out.println(stu.toString());
						System.out.println("������Ϣ��ӡ�꣡");
						sd.addStudent(stu);
						sendmsg = "�����Ϣ�ɹ���\n";
					}
					catch(Exception e){
						sendmsg = "�����Ϣʧ��";
						System.out.println("�����Ϣʧ�ܣ�");
					}
					break;
				case "updatestu":
					try{
						stu.setStudyid(str[2]);
						if(!stu.getStudyid().trim().isEmpty())
						{
							try {
								stu = sd.studyid_get(stu.getStudyid());
							} catch (Exception e) {
								sendmsg = "ѧ�Ŵ��󣬲��޴���";
								System.out.println("ѧ�Ŵ���");
								break;
							}
							
						}
						else
						{
							sendmsg = "������ѧ��";
							break;
						}
						if(!str[1].trim().isEmpty())
						{
							stu.setName(str[1]);
							System.out.println(stu.getName());
						}
						if(!str[3].trim().isEmpty())
						{
							stu.setAge(Integer.valueOf(str[3]));
							System.out.println(stu.getAge());
						}
						if(!str[4].trim().isEmpty())
						{
							stu.setSex(str[4]);
							System.out.println(stu.getSex());
						}
						if(!str[5].trim().isEmpty())
						{
							stu.setAddress(str[5]);
							System.out.println(stu.getAddress());
						}
						if(!str[6].trim().isEmpty())
						{
							stu.setFrom(str[6]);
							System.out.println(stu.getFrom());
						}
						if(!str[7].trim().isEmpty())
						{
							stu.setStdclass(str[7]);
							System.out.println(stu.getStdclass());
						}
						if(!str[8].trim().isEmpty())
						{
							stu.setGrade(str[8]);
							System.out.println(stu.getGrade());
						}
						System.out.println("11111111111111111");
						if(!str[9].trim().isEmpty())
						{
							System.out.println("sadfgasdfa");
							stu.setFdyname(str[9]);
							System.out.println(stu.getFdyname());
						}
						if(!str[10].trim().isEmpty())
						{
							stu.setEmail(str[10]);
							System.out.println(stu.getEmail());
						}
						
						if(!str[11].trim().isEmpty())
						{
							stu.setPhonenum(str[11]);
							System.out.println(stu.getPhonenum());
						}

						if(!str[12].trim().isEmpty())
						{
							stu.setBirthday(Date.valueOf(str[12]));
							System.out.println(stu.getBirthday());
						}
						
						if(!str[13].trim().isEmpty())
						{
							stu.setCollege(str[13]);
							System.out.println(stu.getCollege());
						}
						
						System.out.println("......");
						stu.setUpdate_date(new java.sql.Date( new java.util.Date().getTime()));
						System.out.println(stu.getUpdate_date());
						System.out.println("׼����ӡ������Ϣ��");
						System.out.println(stu.toString());
						System.out.println("������Ϣ��ӡ�꣡");
						sd.updateStudent(stu);
						sendmsg = "�޸���Ϣ�ɹ���\n"+stu.toString()+"\n\n\n";
					}
					catch(Exception e){
						sendmsg = "�޸���Ϣ�����ݿ�����ʧ��";
						System.out.println("�����Ϣʧ�ܣ�");
					}
					break;
				case "delete":
					try {
						stu = sd.studyid_get(str[1]);
						System.out.println("ɾ���ɹ�\n\n"+stu.toString());
						sd.delStudent(str[1]);
					} catch (Exception e) {
						sendmsg = "ɾ��ʧ�ܣ���ȷ����Ϣ���²�����";
						break;
					}
					sendmsg = "ɾ���ɹ�\n\n"+stu.toString()+"\n\n\n";
					break;

				}
				byte[] send_buf = sendmsg.getBytes("utf-8");
				send_dp = new DatagramPacket(send_buf, send_buf.length, InetAddress.getByName(ip), 10001);
				send.send(send_dp);
				System.out.println("��Ϣ�ѷ��ͣ�");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
