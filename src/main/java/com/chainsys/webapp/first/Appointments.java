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
import com.chainsys.miniproject.dao.AppointmentsDao;
import com.chainsys.miniproject.pojo.Appointment;

/**
 * Servlet implementation class Appointments
 */
public class Appointments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Appointments() {
		super();
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

		List<Appointment> allAppointmentss = AppointmentsDao.getAllAppointments();
		Iterator<Appointment> dociterator = allAppointmentss.iterator();
		out.print("<table border=\"1\"><tr><th>Appointment ID</th><th>Appointment Date</th><th>Patient Name</th><th>Patient Phone</th><th>Fee Collected</th><th>Doctor Id</th></tr>");
		while (dociterator.hasNext()) {
			Appointment result = dociterator.next();
			out.print("<tr><td>" + result.getAppointment_id() + "</td><td>" + result.getAppointment_date() + "</td><td>"
					+ result.getPatient_name() + "</td><td>" + result.getPat_phone_no() + "</td><td>" + result.getFee_collected()
					+ "</td><td>" + result.getDoc_id() + "</td></tr>");
		
		}
		out.print("</table>");
	}//APPOINTMENT_ID,APPOINTMENT_DATE,PATIENT_NAME,PATIENT_PHNO,FEE_COLLECTED,DOC_ID

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
			String source = "AddNewApointment";
			String message = "<h1>Error while "+source+"</h1>";
			Appointment newapt = null;
			try {
				
				newapt = new Appointment();
				String id = request.getParameter("id");
				try {
					Validator.checkStringForParse(id);
				} catch (InvalidInputDataException err) {
					message += " Error in Appointment id input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
					}
				int aId = Integer.parseInt(id);
				try {
					Validator.CheckNumberForGreaterThanZero(aId);
				} catch (InvalidInputDataException err) {
					message += " Error in Appointment id input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
					}
				newapt.setAppointment_id(aId);
//------------------------			
				String name = request.getParameter("name");
				try {
					Validator.checkStringSpacesLength(name);
				} catch (InvalidInputDataException err) {
					message += " Error in Patient Name input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;				
					}
				newapt.setPatient_name(name);
//------------------------------
				String sDate = request.getParameter("date");
				try {
					Validator.checkDateFormat(sDate);
				} catch (InvalidInputDataException err) {
					message += " Error in Appointment Date input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;				
					}

				Date date = null;
				try {
					date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
				} catch (ParseException err) {
					message += " Error in Appointment Date input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
					}
				newapt.setAppointment_date(date);
//-------------------------------			
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
				newapt.setPat_phone_no(pno);
//-----------------------------------			
				String docid = request.getParameter("docid");
				try {
					Validator.checkStringForParse(docid);
				} catch (InvalidInputDataException err) {
					message += " Error in Doctor Id input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
					}
				int dId = Integer.parseInt(docid);
				try {
					Validator.CheckNumberForGreaterThanZero(dId);
				} catch (InvalidInputDataException err) {
					message += " Error in Doctor Id input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
					}
				newapt.setDoc_id(dId);
//--------------------------------		
				String fee = request.getParameter("fee");
				try {
					Validator.checkStringForParseFloat(fee);
				} catch (InvalidInputDataException err) {
					message += " Error in Fee Collected input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
					}
				float cfee = Float.parseFloat(fee);
				try {
					Validator.checkfee(cfee);
				} catch (InvalidInputDataException err) {
					message += " Error in Fee Collected input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
					}
				newapt.setFee_collected(cfee);
//----------------------------------			
				result = AppointmentsDao.insertAppointments(newapt);
			} catch (NumberFormatException err) {
				message += " Error in Inserting input data </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}
			out.println("<div> Add New Appointment: " + result + "</div>");
		} else {
			out.print("<h1> SELECT VALID CHOICE </h1>");
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "UpdateEmployee";
		String message = "<h1>Error while " + source + "</h1>";
		int result = 0;
		Appointment newapt =null;
		try {
			newapt = new Appointment();
			String id = request.getParameter("id");
			try {
				Validator.checkStringForParse(id);
			} catch (InvalidInputDataException err) {
				message += " Error in Appointment id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}
			int aId = Integer.parseInt(id);
			try {
				Validator.CheckNumberForGreaterThanZero(aId);
			} catch (InvalidInputDataException err) {
				message += " Error in Appointment id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}
			newapt.setAppointment_id(aId);
//------------------------			
			String name = request.getParameter("name");
			try {
				Validator.checkStringSpacesLength(name);
			} catch (InvalidInputDataException err) {
				message += " Error in Patient Name input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}
			newapt.setPatient_name(name);
//------------------------------
			String sDate = request.getParameter("date");
			try {
				Validator.checkDateFormat(sDate);
			} catch (InvalidInputDataException err) {
				message += " Error in Appointment Date input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}

			Date date = null;
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
			} catch (ParseException err) {
				message += " Error in Appointment Date input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}
			newapt.setAppointment_date(date);
//-------------------------------			
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
			newapt.setPat_phone_no(pno);
//-----------------------------------			
			String docid = request.getParameter("docid");
			try {
				Validator.checkStringForParse(docid);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor Id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;			
				}
			int dId = Integer.parseInt(docid);
			try {
				Validator.CheckNumberForGreaterThanZero(dId);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor Id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}
			newapt.setDoc_id(dId);
//--------------------------------		
			String fee = request.getParameter("fee");
			try {
				Validator.checkStringForParseFloat(fee);
			} catch (InvalidInputDataException err) {
				message += " Error in Fee Collected input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}
			float cfee = Float.parseFloat(fee);
			try {
				Validator.checkfee(cfee);
			} catch (InvalidInputDataException err) {
				message += " Error in Fee Collected input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}
			newapt.setFee_collected(cfee);
//----------------------------------			
			result = AppointmentsDao.updateAppointments(newapt);
		} catch (Exception err) {
			message += " Error in Updating the input data</p>";
			String errorPage = ExceptionManager.HandleException(err, source, message);
			out.print(errorPage);
			return;
			}

		out.println("<div> Update Appointment: " + result + "</div>");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "DeleteEmployee";
		String message = "<h1>Error while " + source + "</h1>";
		String s1 = null;
		int result = 0;
		try {
			System.out.println("Enter Appointment Id ");
			s1 = request.getParameter("id");
			try {
				Validator.checkStringForParse(s1);
			} catch (InvalidInputDataException err) {
				message += " Error in Appointment id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;			}
			int id = Integer.parseInt(s1);
			try {
				Validator.CheckNumberForGreaterThanZero(id);
			} catch (InvalidInputDataException err) {
				message += " Error in Appointment id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
				}
			result = AppointmentsDao.deleteAppointments(id);
			System.out.println(result);
		} catch (NumberFormatException err) {
			message += " Error in Appointment id input </p>";
			String errorPage = ExceptionManager.HandleException(err, source, message);
			out.print(errorPage);
			return;
			}
		out.print("<div> Deleted Appointment: " + result + "</div>");
	}

}
