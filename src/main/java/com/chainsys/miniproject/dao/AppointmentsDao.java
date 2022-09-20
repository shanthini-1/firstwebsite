package com.chainsys.miniproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chainsys.miniproject.pojo.Appointment;



public class AppointmentsDao {
	private static Connection getConnection() {
		String drivername = "oracle.jdbc.OracleDriver";
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "system";
		String password = "Chennai268$";
		try {
			Class.forName(drivername);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	private static java.sql.Date convertTosqlDate(java.util.Date date) {
		java.sql.Date sqldate = new java.sql.Date(date.getTime());
		return sqldate;
	}

	public static int insertAppointments(Appointment newapt) {
		String insertquery = "insert into Appointments (APPOINTMENT_ID,APPOINTMENT_DATE,PATIENT_NAME,PATIENT_PHNO,FEE_COLLECTED,DOC_ID) values (?,?,?,?,?,?)";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(insertquery);
			ps.setInt(1, newapt.getAppointment_id());
			// convert java.util.Date to java.sql.date
			ps.setDate(2, convertTosqlDate(newapt.getAppointment_date()));
			ps.setString(3, newapt.getPatient_name());
			ps.setLong(4, newapt.getPat_phone_no());
			ps.setFloat(5, newapt.getFee_collected());
			ps.setInt(6, newapt.getDoc_id());
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	// for updating all the columns of the table
	public static int updateAppointments(Appointment newapt) {
		String updatequery = "update APPOINTMENTS set APPOINTMENT_DATE=?,PATIENT_NAME=?,PATIENT_PHNO=?,FEE_COLLECTED=?,DOC_ID=? where APPOINTMENT_ID=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(updatequery);
			// convert java.util.Date to java.sql.date
			ps.setDate(1, convertTosqlDate(newapt.getAppointment_date()));
			ps.setString(2, newapt.getPatient_name());
			ps.setLong(3, newapt.getPat_phone_no());
			ps.setFloat(4, newapt.getFee_collected());
			ps.setInt(5, newapt.getDoc_id());
			ps.setInt(6, newapt.getAppointment_id());
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rows;
	}

	// to update only one column of the table
	public static int updateAppointmentsDate(int id, Date date) {
		String updatequery = "update APPOINTMENTS set APPOINTMENT_DATE=? where APPOINTMENT_ID=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(updatequery);
			ps.setDate(1, convertTosqlDate(date));
			ps.setInt(2, id);
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rows;
	}

	public static int updateAppointmentsCollectedFee(int id, float fee_collected) {
		String updatequery = "update APPOINTMENTS set FEE_COLLECTED=? where DOC_ID=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(updatequery);
			ps.setFloat(1, fee_collected);
			ps.setInt(2, id);
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}
	public static int updateAppointmentsCollectedFee(int id, String pname) {
		String updatequery = "update APPOINTMENTS set PATIENT_NAME=? where DOC_ID=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(updatequery);
			ps.setString(1, pname);
			ps.setInt(2, id);
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	public static int deleteAppointments(int id) {
		String deletequery = "delete from appointments where APPOINTMENT_ID=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;

		try {
			con = getConnection();
			ps = con.prepareStatement(deletequery);
			ps.setInt(1, id);
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	// To retrieve specific Appointments data using aptloyee_id
	public static Appointment getAppointmentsById(int id) {
		Appointment apt = null;
		String selectquery = "select APPOINTMENT_ID,APPOINTMENT_DATE,PATIENT_NAME,PATIENT_PHNO,FEE_COLLECTED,DOC_ID  from Appointments where APPOINTMENT_ID = ? ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(selectquery);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			apt = new Appointment();
			if (rs.next()) {
				apt.setAppointment_id(rs.getInt(1));
				java.util.Date date = new java.util.Date(rs.getDate(2).getTime());
				apt.setAppointment_date(date);
				apt.setPatient_name(rs.getString(3));
				apt.setPat_phone_no(rs.getLong(4));
				apt.setFee_collected(rs.getFloat(5));
				apt.setDoc_id(rs.getInt(6));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return apt;

	}

	// To retrieve all apt data
	public static List<Appointment> getAllAppointments() {
		List<Appointment> listOfAppointments = new ArrayList<Appointment>();
		Appointment apt = null;
		String selectquery = "select APPOINTMENT_ID,APPOINTMENT_DATE,PATIENT_NAME,PATIENT_PHNO,FEE_COLLECTED,DOC_ID  from Appointments ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(selectquery);
			rs = ps.executeQuery();

			while (rs.next()) {
				apt = new Appointment();
				apt.setAppointment_id(rs.getInt(1));
				java.util.Date date = new java.util.Date(rs.getDate(2).getTime());
				apt.setAppointment_date(date);
				apt.setPatient_name(rs.getString(3));
				apt.setPat_phone_no(rs.getLong(4));
				apt.setFee_collected(rs.getFloat(5));
				apt.setDoc_id(rs.getInt(6));
				listOfAppointments.add(apt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		return listOfAppointments;
		}
}
