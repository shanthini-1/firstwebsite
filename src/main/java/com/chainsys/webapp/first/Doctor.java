package com.chainsys.webapp.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.miniproject.commonutils.ExceptionManager;
import com.chainsys.miniproject.commonutils.InvalidInputDataException;
import com.chainsys.miniproject.commonutils.Validator;
import com.chainsys.miniproject.dao.DoctorDao;
import com.chainsys.miniproject.pojo.Doctors;

/**
 * Servlet implementation class Doctor
 */
public class Doctor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getMethod();
		if(methodName.equals("GET")) {
			doGet(request,response);
		}
		else {
			doPost(request,response);
		}
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		List<Doctors> allDoctors = DoctorDao.getAllDoctor();
		Iterator<Doctors> dociterator = allDoctors.iterator();
		out.print(
				"<table border=\"1\"><tr><th>Doctor ID</th><th>Doctor Name</th><th>Date Of Birth</th><th>E-mail</th><th>Speciality</th><th>City</th><th>Phone Number</th><th>Standard Fee</th></tr>");
		while (dociterator.hasNext()) {
			Doctors result = dociterator.next();
			out.print("<tr><td>" + result.getDoc_id() + "</td><td>" + result.getDoc_name() + "</td><td>"
					+ result.getDoc_dob() + "</td><td>" + result.getDoc_email() + "</td><td>" + result.getSpeciality()
					+ "</td><td>" + result.getCity() + "</td><td>" + result.getDoc_phone_no() + "</td><td>"
					+ result.getStandard_fee() + "</td></tr>");
		}
		out.print("</table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String submitValue = request.getParameter("submit");
		System.out.println(submitValue);
		if (submitValue.equals("UPDATE")) {
			doPut(request, response);
		} else if (submitValue.equals("DELETE")) {
			doDelete(request, response);
		} else if (submitValue.equals("ADD")) {
			int result = 0;
			Doctors newdoctor = null;
			String source = "AddNewDoctor";
			String message = "<h1>Error while " + source + "</h1>";
			try {
				newdoctor = new Doctors();
				String id = request.getParameter("id");
				try {
					Validator.checkStringForParse(id);
				} catch (InvalidInputDataException err) {
					message += " Error in Doctor id input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				int dId = Integer.parseInt(id);
				try {
					Validator.CheckNumberForGreaterThanZero(dId);
				} catch (InvalidInputDataException err) {
					message += " Error in Doctor id input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newdoctor.setDoc_id(dId);
//---------------------------------------------				
				String name = request.getParameter("name");
				try {
					Validator.checkStringOnly(name);
				} catch (InvalidInputDataException err) {
					message += " Error in Doctor Name input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
					}
				try {
					Validator.checkStringSpacesLength(name);
				} catch (InvalidInputDataException err) {
					message += " Error in DoctoName input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newdoctor.setDoc_name(name);
//----------------------------------------------				
				String sDate = request.getParameter("date");
				try {
					Validator.checkDateFormat(sDate);
				} catch (InvalidInputDataException err) {
					message += " Error in Date Of Birth input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				Date date = null;
				try {
					date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
				} catch (ParseException err) {
					message += " Error in Date Of Birth input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newdoctor.setDoc_dob(date);
//---------------------------------------------------				
				System.out.println("Enter email :");
				String mail = request.getParameter("email");
				try {
					Validator.checkMail(mail);
				} catch (InvalidInputDataException err) {
					message += " Error in Email input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newdoctor.setDoc_email(mail);
//----------------------------------------------------				
				System.out.println("Enter city");
				String cty = request.getParameter("city");
				try {
					Validator.checkStringOnly(cty);
				} catch (InvalidInputDataException err) {
					message += " Error in City input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				try {
					Validator.checkStringSpacesLength(name);
				} catch (InvalidInputDataException err) {
					message += " Error in City input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newdoctor.setCity(cty);
//-----------------------------------------------------				
				System.out.println("Enter doctor phone number");
				String phno = request.getParameter("phone");
				try {
					Validator.checkStringForParse(phno);
				} catch (InvalidInputDataException err) {
					message += " Error in Phone Number input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				try {
					Validator.checklengthOfPhno(phno);
				} catch (InvalidInputDataException err) {
					message += " Error in Phone Number input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				long pno = Long.parseLong(phno);

				newdoctor.setDoc_phone_no(pno);
//------------------------------------------------------					
				System.out.println("Enter Speciality");
				try {
					Validator.checkStringOnly(cty);
				} catch (InvalidInputDataException err) {
					message += " Error in Speciality input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				String spec = request.getParameter("speciality");
				try {
					Validator.checkStringSpacesLength(spec);
				} catch (InvalidInputDataException err) {
					message += " Error in Speciality input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newdoctor.setSpeciality(spec);
//------------------------------------				
				System.out.println("Enter standard fees");
				String fees = request.getParameter("fee");
				try {
					Validator.checkStringForParseFloat(fees);
				} catch (InvalidInputDataException err) {
					message += " Error in Standard Fee input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				float sfee = Integer.parseInt(fees);
				try {
					Validator.checkfee(sfee);
				} catch (InvalidInputDataException err) {
					message += " Error in Standard Fee input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newdoctor.setStandard_fee(sfee);
//---------------------------------
				result = DoctorDao.insertDoctor(newdoctor);
				System.out.println(result);
			} catch (NumberFormatException err) {
				message += " Error in Inserting input values </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			out.print("<div> Added Doctor: " + result + "</div>");
		} else {
			out.print("<h1> SELECT VALID CHOICE </h1>");
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "UpdateDoctor";
		String message = "<h1>Error while " + source + "</h1>";
		int result = 0;
		try {
			Doctors newdoctor = new Doctors();
			System.out.println("Enter Doctor id");
			String id = request.getParameter("id");
			try {
				Validator.checkStringForParse(id);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor Id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			int dId = Integer.parseInt(id);
			try {
				Validator.CheckNumberForGreaterThanZero(dId);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor Id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newdoctor.setDoc_id(dId);
//---------------------------------------------				
			System.out.println("Enter Doctor name");
			String name = request.getParameter("name");
			try {
				Validator.checkStringOnly(name);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor Name input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}
			try {
				Validator.checkStringSpacesLength(name);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor Name input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newdoctor.setDoc_name(name);
//----------------------------------------------				
			System.out.println("Enter dob as dd/mm/yyyy");
			String sDate = request.getParameter("date");
			try {
				Validator.checkDateFormat(sDate);
			} catch (InvalidInputDataException err) {
				message += " Error in Date Of Birth input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			Date date = null;
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
			} catch (ParseException err) {
				message += " Error in Date Of Birth input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newdoctor.setDoc_dob(date);
//---------------------------------------------------				
			System.out.println("Enter email :");
			String mail = request.getParameter("email");
			try {
				Validator.checkMail(mail);
			} catch (InvalidInputDataException err) {
				message += " Error in Email input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newdoctor.setDoc_email(mail);
//----------------------------------------------------				
			System.out.println("Enter city");
			String cty = request.getParameter("city");
			try {
				Validator.checkStringOnly(cty);
			} catch (InvalidInputDataException err) {
				message += " Error in City input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.checkStringSpacesLength(cty);
			} catch (InvalidInputDataException err) {
				message += " Error in City input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}
			newdoctor.setCity(cty);
//-----------------------------------------------------				
			System.out.println("Enter doctor phone number");
			String phno = request.getParameter("phone");
			try {
				Validator.checkStringForParse(phno);
			} catch (InvalidInputDataException err) {
				message += " Error in Phone Number input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.checklengthOfPhno(phno);
			} catch (InvalidInputDataException err) {
				message += " Error in Phone Number input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			long pno = Long.parseLong(phno);

			newdoctor.setDoc_phone_no(pno);
//------------------------------------------------------					
			System.out.println("Enter Speciality");
			String spec = request.getParameter("speciality");
			try {
				Validator.checkStringOnly(spec);
			} catch (InvalidInputDataException err) {
				message += " Error in Speciality input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.checkStringSpacesLength(spec);
			} catch (InvalidInputDataException err) {
				message += " Error in Speciality input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newdoctor.setSpeciality(spec);
//------------------------------------				
			System.out.println("Enter standard fees");
			String fees = request.getParameter("fee");
			try {
				Validator.checkStringForParseFloat(fees);
			} catch (InvalidInputDataException err) {
				message += " Error in Standard Fee input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			float sfee = Integer.parseInt(fees);
			try {
				Validator.checkfee(sfee);
			} catch (InvalidInputDataException err) {
				message += " Error in Standard Fee input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newdoctor.setStandard_fee(sfee);
//---------------------------------
			result = DoctorDao.updateDoctor(newdoctor);
			System.out.println(result);
		} catch (Exception err) {
			message += " Error in Updating input values </p>";
			String errorPage = ExceptionManager.HandleException(err, source, message);
			out.print(errorPage);
			return;
		}
		out.print("<div> Updated Doctor: " + result + "</div>");

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "DeleteDoctor";
		String message = "<h1>Error while " + source + "</h1>";
		int result = 0;
		try {
			System.out.println("Enter doctor id");
			String s1 = request.getParameter("id");
			try {
				Validator.checkStringForParse(s1);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor Id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			int id = Integer.parseInt(s1);
			try {
				Validator.CheckNumberForGreaterThanZero(id);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor Id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			result = DoctorDao.deleteDoctor(id);

			System.out.println(result);
		} catch (NumberFormatException err) {
			message += " Error in Delete record </p>";
			String errorPage = ExceptionManager.HandleException(err, source, message);
			out.print(errorPage);
			return;
		}
		out.print("<div> Deleted Doctor: " + result + "</div>");
	}

}
