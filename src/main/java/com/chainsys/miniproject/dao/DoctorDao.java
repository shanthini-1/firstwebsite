package com.chainsys.miniproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.miniproject.pojo.Doctors;

public class DoctorDao {
	/*
	 * All Methods getConnection()
	 */
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

	public static int insertDoctor(Doctors newdoc) {
		String insertquery = "insert into Doctor (DOC_ID,DOC_NAME,DOC_DOB,DOC_EMIAL,SPECIALITY,CITY,PHONE_NO,STANDARD_FEE) values (?,?,?,?,?,?,?,?)";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(insertquery);
			ps.setInt(1, newdoc.getDoc_id());
			ps.setString(2, newdoc.getDoc_name());
			// convert java.util.Date to java.sql.date
			ps.setDate(3, convertTosqlDate(newdoc.getDoc_dob()));
			ps.setString(4, newdoc.getDoc_email());
			ps.setString(5, newdoc.getSpeciality());
			ps.setString(6, newdoc.getCity());
			ps.setLong(7, newdoc.getDoc_phone_no());
			ps.setFloat(8, newdoc.getStandard_fee());
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
	public static int updateDoctor(Doctors newdoc) {
		String updatequery = "update doctor set DOC_NAME=?,DOC_DOB=?,DOC_EMIAL=?,SPECIALITY=?,CITY=?,PHONE_NO=?,STANDARD_FEE=? where DOC_ID=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(updatequery);
			ps.setString(1, newdoc.getDoc_name());
			// convert java.util.Date to java.sql.date
			ps.setDate(2, convertTosqlDate(newdoc.getDoc_dob()));
			ps.setString(3, newdoc.getDoc_email());
			ps.setString(4, newdoc.getSpeciality());
			ps.setString(5, newdoc.getCity());
			ps.setLong(6, newdoc.getDoc_phone_no());
			ps.setFloat(7, newdoc.getStandard_fee());
			ps.setInt(8, newdoc.getDoc_id());
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
	public static int updateDoctorName(int id, String fname) {
		String updatequery = "update DOCTOR set DOC_NAME=? where DOC_ID=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(updatequery);
			ps.setString(1, fname);
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

	public static int updateDoctorStandardFee(int id, float standard_fee) {
		String updatequery = "update DOCTOR set STANDARD_FEE? where DOC_ID=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(updatequery);
			ps.setDouble(1, standard_fee);
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

	public static int deleteDoctor(int id) {
		String deletequery = "delete from doctor where DOC_ID=?";
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

	// To retrieve specific Doctor data using docloyee_id
	public static Doctors getDoctorById(int id) {
		Doctors doc = null;
		String selectquery = "select DOC_ID,DOC_NAME,DOC_DOB,DOC_EMIAL,SPECIALITY,CITY,PHONE_NO,STANDARD_FEE  from Doctor where DOC_ID = ? ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(selectquery);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			doc = new Doctors();
			if (rs.next()) {
				doc.setDoc_id(rs.getInt(1));
				doc.setDoc_name(rs.getString(2));
				java.util.Date date = new java.util.Date(rs.getDate(3).getTime());
				doc.setDoc_dob(date);
				doc.setDoc_email(rs.getString(4));
				doc.setSpeciality(rs.getString(5));
				doc.setCity(rs.getString(6));
				doc.setDoc_phone_no(rs.getLong(7));
				doc.setStandard_fee(rs.getFloat(8));
			
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
		return doc;

	}

	// To retrieve all doctor data
	public static List<Doctors> getAllDoctor() {
		List<Doctors> listOfDoctors = new ArrayList<Doctors>();
		Doctors doc = null;
		String selectquery = "select DOC_ID,DOC_NAME,DOC_DOB,DOC_EMIAL,SPECIALITY,CITY,PHONE_NO,STANDARD_FEE  from Doctor ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(selectquery);
			rs = ps.executeQuery();

			while (rs.next()) {
				doc = new Doctors();
				doc.setDoc_id(rs.getInt(1));
				doc.setDoc_name(rs.getString(2));
				java.util.Date date = new java.util.Date(rs.getDate(3).getTime());
				doc.setDoc_dob(date);
				doc.setDoc_email(rs.getString(4));
				doc.setSpeciality(rs.getString(5));
				doc.setCity(rs.getString(6));
				doc.setDoc_phone_no(rs.getLong(7));
				doc.setStandard_fee(rs.getFloat(8));
				listOfDoctors.add(doc);
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
		return listOfDoctors;
	}
}
