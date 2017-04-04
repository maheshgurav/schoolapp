package com.samarthsoft.prabandhak.utilities;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.LeavingCertificate;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.enums.Gender;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.form.entities.StudentForm;
import com.samarthsoft.prabandhak.reports.AttendanceDetails;
import com.samarthsoft.prabandhak.reports.AttendanceSheetStudentDetails;
import com.samarthsoft.prabandhak.reports.AttendanceSummary;
import com.samarthsoft.prabandhak.reports.GRReportObject;
import com.samarthsoft.prabandhak.reports.LeavingCertificateReportObject;
import com.samarthsoft.prabandhak.reports.StudentReportObject;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Component
public class ReportsControllerUtility {
	@Autowired
	private StudentControllerUtility studentControllerUtility;
	@Autowired
	private CommonControllerUtility commonControllerUtility;
	@Autowired
	private StudentCertificatesControllerUtility studentCertificatesControllerUtility;
	@Autowired
	private NumberToWordConverterUtility numberToWordConverterUtility;

	public JasperPrint fillJasperReport(List<Object> objectsForReport, HttpServletRequest request, String reportName,
			Map<String, Object> parameterMap) {
		JasperPrint jasperPrint = null;
		InputStream reportStream = null;
		try {
			/*
			 * Report report = (Report)
			 * DBCommunicator.getApiServices().getGenericApi()
			 * .getObjectByColumnName(Report.class, "reportName",
			 * reportName).get(0);
			 */
    		parameterMap.put("school_organisation", "Bharati Vidyapeeth");
    		parameterMap.put("school_name", "M K Gurav Vidyalaya");
    		parameterMap.put("school_address", "Daundaj, Tal : Purandar, Dist : Pune, Pin Code : 412 305");
    		parameterMap.put("school_grant_details", "School Grant No. S/1/PA-NS-PR Poona-4. Dt. 12 May 1964");
    		parameterMap.put("other_info", "(Grant in Code Rule No. 17)");
			reportStream = this.getClass().getResourceAsStream(getReportByName(reportName));
			// reportStream = new ByteArrayInputStream(report.getActualReport());
			JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(objectsForReport);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasperPrint;
	}

	public String getReportByName(String reportName) {
		if (reportName.equalsIgnoreCase("CLASSWISE_STUDENTS")) {
			return "/reports/CLASSWISE_STUDENTS.jrxml";
		}
		if (reportName.equalsIgnoreCase("ICARDS")) {
			return "/reports/ICARDS.jrxml";
		}
		if (reportName.equalsIgnoreCase("GR")) {
			return "/reports/GR.jrxml";
		}
		if (reportName.equalsIgnoreCase("BLANK_ATTENDANCE_PAGE_SECOND")) {
			return "/reports/BLANK_ATTENDANCE_PAGE_SECOND.jrxml";
		}
		if (reportName.equalsIgnoreCase("BLANK_ATTENDANCE")) {
			return "/reports/BLANK_ATTENDANCE.jrxml";
		}
		if (reportName.equalsIgnoreCase("ATTENDANCE_PAGE_FIRST")) {
			return "/reports/ATTENDANCE_PAGE_FIRST_Updated.jrxml";
		}
		if (reportName.equalsIgnoreCase("ATTENDANCE")) {
			return "/reports/ATTENDANCE.jrxml";
		}
		if (reportName.equalsIgnoreCase("ATTENDANCE_PAGE_THIRD")) {
			return "/reports/ATTENDANCE_PAGE_THIRD.jrxml";
		}
		if (reportName.equalsIgnoreCase("BONAFIED")) {
			return "/reports/BONAFIED.jrxml";
		}
		if (reportName.equalsIgnoreCase("STUDENT_REPORT_CARD")) {
			return "/reports/STUDENT_REPORT_CARD.jrxml";
		}
		
		return "";
	}

	public List<Object> getStudentsForReport(List<Filter> filters) {
		List<Object> result = new ArrayList<Object>();
		StudentForm[] students = studentControllerUtility
				.getStudentFormObjects(studentControllerUtility.getStudents(filters));
		int count = 1;
		for (StudentForm studentFormObject : students) {
			StudentReportObject studentReportObject = new StudentReportObject();
			studentReportObject.setCasteAndSubcaste(studentFormObject.getStudent().getCaste());
			studentReportObject.setGeneralRegisterNumber(studentFormObject.getStudent().getPRN());
			studentReportObject.setRecordNumber(count++);
			studentReportObject.setScholarshipName(studentFormObject.getStudent().getScholarshipName());
			studentReportObject.setStudentBirthDate(studentFormObject.getBirthDate());
			studentReportObject.setStudentClassAndDivision(
					commonControllerUtility.getStandardAndDivision(studentFormObject.getStudent()));
			studentReportObject.setClassAndDivision(
					commonControllerUtility.getStandardAndDivision(studentFormObject.getStudent()));
			studentReportObject.setStudentName(studentFormObject.getStudent().getName().toString());
			studentReportObject.setStudentSchoolJoiningDate(studentFormObject.getAdmissionDate());
			studentReportObject.setRollNumber(studentFormObject.getStudent().getRollNumber());
			studentReportObject.setBloodGroup(studentFormObject.getStudent().getBloodGroup());
			/*
			 * if (studentFormObject.getStudent().getCurrentAddress() != null)
			 * studentReportObject.setAddress(JsonConverter.fromJson(
			 * studentFormObject.getStudent().getCurrentAddress(),
			 * Address.class).toString());
			 */
			studentReportObject.setAddress("-");
			if (Calendar.getInstance().get(Calendar.MONTH) + 1 >= 6)
				studentReportObject.setiCardForYear(Calendar.getInstance().get(Calendar.YEAR) + "-"
						+ (Calendar.getInstance().get(Calendar.YEAR) + 1));
			else
				studentReportObject.setiCardForYear((Calendar.getInstance().get(Calendar.YEAR) - 1) + "-"
						+ Calendar.getInstance().get(Calendar.YEAR));

			result.add(studentReportObject);
		}
		return result;
	}

	public List<Filter> getFilterForReport(String reportType, HttpServletRequest request) {
		List<Filter> filters = new ArrayList<Filter>();
		String classGuid = RequestCommon.getAttributeValueFromRequest("classguid", request);
		String gender = RequestCommon.getAttributeValueFromRequest("gender", request);
		String scholarshipGuid = RequestCommon.getAttributeValueFromRequest("scholarshipguid", request);
		String casteGuid = RequestCommon.getAttributeValueFromRequest("casteguid", request);
		String category = RequestCommon.getAttributeValueFromRequest("category", request);
		if (!ValidatorCommon.checkFieldNullOrEmpty(gender)) {
			if (gender.equals("0"))
				filters.add(new Filter("gender", Gender.Male, RestrictionType.EQ));
			if (gender.equals("1"))
				filters.add(new Filter("gender", Gender.Female, RestrictionType.EQ));
		}
		if (!ValidatorCommon.checkFieldNullOrEmpty(classGuid)) {
			filters.add(new Filter("standard", classGuid.split("-")[0].trim(), RestrictionType.EQ));
			filters.add(new Filter("division", classGuid.split("-")[1].trim(), RestrictionType.EQ));
		}
		if (!ValidatorCommon.checkFieldNullOrEmpty(scholarshipGuid))
			filters.add(new Filter("scholarshipType", scholarshipGuid, RestrictionType.EQ));
		if (!ValidatorCommon.checkFieldNullOrEmpty(category))
			filters.add(new Filter("category", category, RestrictionType.EQ));
		if (!ValidatorCommon.checkFieldNullOrEmpty(casteGuid))
			filters.add(new Filter("caste", casteGuid, RestrictionType.EQ));
		return filters;
	}

	public JRHtmlExporter getHtmlExporter(JasperPrint jasperPrint, ByteArrayOutputStream outputStream) {
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		exporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		return exporter;
	}

	public JRPdfExporter getPdfExporter(JasperPrint jasperPrint, ByteArrayOutputStream outputStream) {
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		exporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		return exporter;
	}

	public LeavingCertificateReportObject getLeavingCertificateReportObject(Student student,
			LeavingCertificate leavingCertificate) {
		LeavingCertificateReportObject leavingCertificateReportObject = new LeavingCertificateReportObject();
		leavingCertificateReportObject.setBirthAddress(student.getPlaceOfBirth().toString().toUpperCase());
		leavingCertificateReportObject.setCaste(student.getCaste().toUpperCase());
		leavingCertificateReportObject.setDateOfBirth(DateUtility.convertTimeToString(student.getDateOfBirth()));
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(student.getDateOfBirth());
		// leavingCertificateReportObject.setDateOfBirthInWord(dateToWordConverter.getDateInWords(cal));
		if (leavingCertificateReportObject.getDateOfBirth() != null
				&& leavingCertificateReportObject.getDateOfBirth().contains("/")) {
			String[] birthDateComponents = leavingCertificateReportObject.getDateOfBirth().split("/");
			if (birthDateComponents != null && birthDateComponents.length == 3) {
				int date = Integer.parseInt(birthDateComponents[0].toString());
				int month = Integer.parseInt(birthDateComponents[1].toString());
				int year = Integer.parseInt(birthDateComponents[2].toString());
				leavingCertificateReportObject.setDateOfBirthInWord(
						numberToWordConverterUtility.convertDateToWord(date, month, year).toUpperCase());
			}
		} else {
			leavingCertificateReportObject.setDateOfBirthInWord("-");
		}
		leavingCertificateReportObject.setDateOfJoining(DateUtility.convertTimeToString(student.getDateOfAdmission()));
		leavingCertificateReportObject
				.setFathersName(student.getName().getMiddleName() + " " + student.getName().getLastName());
		leavingCertificateReportObject.setIsDuplicateCertificate("");
		leavingCertificateReportObject.setLeavingCertificateIssueDateForReport(
				DateUtility.convertTimeToString(DateUtility.getCalendarWithoutTime().getTimeInMillis()));
		leavingCertificateReportObject.setMothersName(student.getFirstNameOfMother().toUpperCase());
		leavingCertificateReportObject.setMotherTongue("Marathi".toUpperCase());
		leavingCertificateReportObject
				.setNameOfPreviousSchoolAttended(student.getNameOfLastAttendedSchool().toUpperCase());
		leavingCertificateReportObject.setPermenentRegistrationNumber(student.getPRN().toUpperCase());
		if (leavingCertificate.getStudyingSinceMonthAndYear() != null
				&& !leavingCertificate.getStudyingSinceMonthAndYear().isEmpty())
			leavingCertificateReportObject.setStandard(student.getStandard().toUpperCase() + "TH STUDYING SINCE "
					+ leavingCertificate.getStudyingSinceMonthAndYear().toUpperCase());
		else
			leavingCertificateReportObject.setStandard(student.getStandard().toUpperCase());
		leavingCertificateReportObject.setStudentName(student.getName().toStringSurnameFirst().toUpperCase());
		leavingCertificateReportObject.setConduct(leavingCertificate.getConduct().toUpperCase());
		leavingCertificateReportObject.setProgress(leavingCertificate.getProgress().toUpperCase());
		leavingCertificateReportObject.setReasonOfLeaving(leavingCertificate.getReasonOfLeaving().toUpperCase());
		leavingCertificateReportObject.setRemark(leavingCertificate.getRemark().toUpperCase());
		leavingCertificateReportObject
				.setDateOfLeaving(DateUtility.convertTimeToString(leavingCertificate.getDateOfLeaving()));
		return leavingCertificateReportObject;
	}

	public List<Object> getStudentInfoForAttendanceReport(List<Filter> filters) {
		List<Object> result = new ArrayList<Object>();
		List<Object> students = DBCommunicator.getApiServices().getGenericApi().getFilteredListWithOrder(Student.class,
				filters, null, "rollNumber", "asc");
		List<AttendanceDetails> attendanceInfo = new ArrayList<AttendanceDetails>();
		for (int count = 1; count <= 31; count++) {
			AttendanceDetails attendanceDetails = new AttendanceDetails();
			attendanceDetails.setDate(count + "");
			attendanceDetails.setPresentOrAbsent("");
			attendanceInfo.add(attendanceDetails);
		}
		for (Object object : students) {
			Student student = (Student) object;
			AttendanceSheetStudentDetails details = new AttendanceSheetStudentDetails();
			details.setAttendanceInfo(attendanceInfo);
			details.setCaste(student.getCaste());
			details.setDateOfBirth(DateUtility.convertTimeToString(student.getDateOfBirth()));
			details.setFeeDetails("");
			details.setGeneralRegisterNumber(student.getPRN());
			details.setNumberOfDaysStudentPresentInMonth("30");
			details.setNumberOfDaysStudentPresentInYear("30");
			details.setRemark("a");
			details.setStudentName(student.getName().toString());
			details.setStudentRollNumber(student.getRollNumber());
			details.setSubcaste(student.getCategory());
			details.setTotalNumberDaysStudentPresent("");
			result.add(details);
		}
		return result;
	}

	public AttendanceSummary getAttendanceSummary(String standard, String division, int totalBoys, int totalGirls,
			String month, String year) {
		AttendanceSummary attendanceSummary = new AttendanceSummary();
		attendanceSummary.setAttendanceForMonth(month + "-" + year);
		attendanceSummary.setClassTeacherName("");
		attendanceSummary.setDivision(division);
		attendanceSummary.setStandard(standard);
		attendanceSummary.setTotalBoys(totalBoys);
		attendanceSummary.setTotalGirls(totalGirls);
		attendanceSummary.setTotalStudents(totalBoys + totalGirls);
		return attendanceSummary;
	}

	public Map<String, Object> getAttendanceReportParameters(List<Object> students) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		for (Object object : students) {
			Student student = (Student) object;
			checkCasteAndPutInMap(parameterMap, "Muslim", "Muslim", student);
			checkCasteAndPutInMap(parameterMap, "Musalman", "Muslim", student);
			checkCasteAndPutInMap(parameterMap, "Boudhha", "Boudhha", student);
			checkCasteAndPutInMap(parameterMap, "Sikh", "Sikh", student);
			checkCasteAndPutInMap(parameterMap, "Christen", "Christen", student);
			checkCasteAndPutInMap(parameterMap, "Parasi", "Parasi", student);
			checkCasteAndPutInMap(parameterMap, "Jain", "Jain", student);

			checkCategoryAndPutInMap(parameterMap, "SC", "Sc", student);
			checkCategoryAndPutInMap(parameterMap, "ST", "St", student);
			checkCategoryAndPutInMap(parameterMap, "BC", "Bc", student);
			checkCategoryAndPutInMap(parameterMap, "NT", "Nt", student);
			checkCategoryAndPutInMap(parameterMap, "NTC", "Nt", student);
			checkCategoryAndPutInMap(parameterMap, "NTD", "Nt", student);
			checkCategoryAndPutInMap(parameterMap, "VJNT", "Vjnt", student);
			checkCategoryAndPutInMap(parameterMap, "Open", "Open", student);
			checkCategoryAndPutInMap(parameterMap, "OBC", "Obc", student);
			checkCategoryAndPutInMap(parameterMap, "SBC", "Sbc", student);

			checkScholarshipTypeAndPutInMap(parameterMap, "EBC", "EbcStudents", student);
			checkScholarshipTypeAndPutInMap(parameterMap, "Paying total fee", "PayingTotalFeeStudents", student);
			checkScholarshipTypeAndPutInMap(parameterMap, "Free education for girls", "FreeEducationStudents", student);
			checkScholarshipTypeAndPutInMap(parameterMap, "BC/BC once failed", "BcStudents", student);
			checkScholarshipTypeAndPutInMap(parameterMap, "Higher secondary school teacher's child", "", student);
			checkScholarshipTypeAndPutInMap(parameterMap, "Primary teacher's child(PTC)", "PtcStudents", student);
			checkScholarshipTypeAndPutInMap(parameterMap, "Freedom fighters", "FreedomFightersStudents", student);
			checkScholarshipTypeAndPutInMap(parameterMap, "Ex.army serviceman", "ExArmyStudents", student);
			checkScholarshipTypeAndPutInMap(parameterMap, "Other consession", "OtherStudents", student);
			checkScholarshipTypeAndPutInMap(parameterMap, "Secondary teacher's child(STC)", "StcStudents", student);
		}
		return parameterMap;
	}

	private void checkCasteAndPutInMap(Map<String, Object> parameterMap, String casteToCompare, String prefix,
			Student student) {
		boolean isBoyAdded = false;
		boolean isGirlAdded = false;
		if (student.getCaste().toLowerCase().contains(casteToCompare.toLowerCase())
				&& student.getGender() == Gender.Male) {
			if (parameterMap.get(prefix + "Boys") != null) {
				parameterMap.put(prefix + "Boys", Integer.parseInt(parameterMap.get(prefix + "Boys").toString()) + 1);
			} else
				parameterMap.put(prefix + "Boys", 1);
			isBoyAdded = true;
		}
		if (student.getCaste().toLowerCase().contains(casteToCompare.toLowerCase())
				&& student.getGender() == Gender.Female) {
			if (parameterMap.get(prefix + "Girls") != null) {
				parameterMap.put(prefix + "Girls", Integer.parseInt(parameterMap.get(prefix + "Girls").toString()) + 1);
			} else
				parameterMap.put(prefix + "Girls", 1);
			isGirlAdded = true;
		}
		if (parameterMap.get(prefix + "Boys") == null) {
			parameterMap.put(prefix + "Boys", 0);
		}
		if (parameterMap.get(prefix + "Girls") == null) {
			parameterMap.put(prefix + "Girls", 0);
		}
		if (parameterMap.get("MinorityBoys") == null) {
			parameterMap.put("MinorityBoys", 0);
		}
		if (parameterMap.get("MinorityGirls") == null) {
			parameterMap.put("MinorityGirls", 0);
		}
		if (parameterMap.get("Total") == null) {
			parameterMap.put("Total", 0);
		}
		if (parameterMap.get("MinorityTotal") == null) {
			parameterMap.put("MinorityTotal", 0);
		}
		if (isBoyAdded)
			parameterMap.put("MinorityBoys", Integer.parseInt(parameterMap.get("MinorityBoys").toString()) + 1);
		if (isGirlAdded)
			parameterMap.put("MinorityGirls", Integer.parseInt(parameterMap.get("MinorityGirls").toString()) + 1);

		parameterMap.put("MinorityTotal", Integer.parseInt(parameterMap.get("MinorityBoys").toString())
				+ Integer.parseInt(parameterMap.get("MinorityGirls").toString()));

		parameterMap.put(prefix + "Total", Integer.parseInt(parameterMap.get(prefix + "Boys").toString())
				+ Integer.parseInt(parameterMap.get(prefix + "Girls").toString()));
	}

	private void checkCategoryAndPutInMap(Map<String, Object> parameterMap, String categoryToCompare, String prefix,
			Student student) {
		boolean isBoyAdded = false;
		boolean isGirlAdded = false;
		if (student.getCategory().toLowerCase().equals(categoryToCompare.toLowerCase())
				&& student.getGender() == Gender.Male) {
			if (parameterMap.get(prefix + "Boys") != null) {
				parameterMap.put(prefix + "Boys", Integer.parseInt(parameterMap.get(prefix + "Boys").toString()) + 1);
			} else
				parameterMap.put(prefix + "Boys", 1);
			isBoyAdded = true;
		}
		if (student.getCategory().toLowerCase().equals(categoryToCompare.toLowerCase())
				&& student.getGender() == Gender.Female) {
			if (parameterMap.get(prefix + "Girls") != null) {
				parameterMap.put(prefix + "Girls", Integer.parseInt(parameterMap.get(prefix + "Girls").toString()) + 1);
			} else
				parameterMap.put(prefix + "Girls", 1);
			isGirlAdded = true;
		}
		if (parameterMap.get(prefix + "Boys") == null) {
			parameterMap.put(prefix + "Boys", 0);
		}
		if (parameterMap.get(prefix + "Girls") == null) {
			parameterMap.put(prefix + "Girls", 0);
		}
		if (parameterMap.get("totalBoys") == null) {
			parameterMap.put("totalBoys", 0);
		}
		if (parameterMap.get("totalGirls") == null) {
			parameterMap.put("totalGirls", 0);
		}
		if (isBoyAdded)
			parameterMap.put("totalBoys", Integer.parseInt(parameterMap.get("totalBoys").toString()) + 1);
		if (isGirlAdded)
			parameterMap.put("totalGirls", Integer.parseInt(parameterMap.get("totalGirls").toString()) + 1);

		parameterMap.put(prefix + "Total", Integer.parseInt(parameterMap.get(prefix + "Boys").toString())
				+ Integer.parseInt(parameterMap.get(prefix + "Girls").toString()));
		parameterMap.put("total", Integer.parseInt(parameterMap.get("totalBoys").toString())
				+ Integer.parseInt(parameterMap.get("totalGirls").toString()));
	}

	private void checkScholarshipTypeAndPutInMap(Map<String, Object> parameterMap, String scholarshipToCompare,
			String prefix, Student student) {
		if (parameterMap.get("ScholarshipWiseTotalStudents") == null) {
			parameterMap.put("ScholarshipWiseTotalStudents", 0);
		}
		if (student.getScholarshipName().toLowerCase().equals(scholarshipToCompare.toLowerCase())) {
			if (parameterMap.get(prefix) != null) {
				parameterMap.put(prefix, Integer.parseInt(parameterMap.get(prefix).toString()) + 1);
			} else
				parameterMap.put(prefix, 1);
			parameterMap.put("ScholarshipWiseTotalStudents",
					Integer.parseInt(parameterMap.get("ScholarshipWiseTotalStudents").toString()) + 1);
		}
		if (parameterMap.get(prefix) == null) {
			parameterMap.put(prefix, 0);
		}
	}

	public List<Object> getGRReportObjects() {
		List<Object> students = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Student.class, null,
				null);
		List<String> studentGuids = new ArrayList<String>();
		for (Object object : students) {
			studentGuids.add(((Student) object).getGuid());
		}
		Map<String, Object> leavingCertificates = studentCertificatesControllerUtility
				.getLeavingCertificates(studentGuids);
		List<Object> objects = new ArrayList<Object>();
		for (Object object : students) {
			Student student = (Student) object;
			LeavingCertificate leavingCertificate = (LeavingCertificate) leavingCertificates.get(student.getGuid());
			GRReportObject grReportObject = new GRReportObject();
			if (leavingCertificate != null) {
				grReportObject.setProgress(leavingCertificate.getProgress());
				grReportObject.setRemarkOrReason(leavingCertificate.getRemark());
				grReportObject.setConduct(leavingCertificate.getConduct());
				grReportObject.setDateOfLeaving(DateUtility.convertTimeToString(leavingCertificate.getDateOfLeaving()));
			} else {
				grReportObject.setProgress("-");
				grReportObject.setRemarkOrReason("-");
				grReportObject.setConduct("-");
				grReportObject.setDateOfLeaving("-");
			}

			grReportObject.setCaste(student.getCaste());
			grReportObject.setDateOfAdmission(DateUtility.convertTimeToString(student.getDateOfAdmission()));
			grReportObject.setDateOfBirthInNameAndNumber(DateUtility.convertTimeToString(student.getDateOfBirth()));
			grReportObject.setFirstName(student.getName().getFirstName());
			grReportObject.setGeneralRegisterNumber(student.getPRN());
			grReportObject.setLastName(student.getName().getLastName());
			grReportObject.setLastSchoolAttended(student.getNameOfLastAttendedSchool());
			grReportObject.setMiddleName(student.getName().getMiddleName());
			grReportObject.setMothersName(student.getFirstNameOfMother());
			grReportObject.setMotherTongue(student.getMotherTongue());
			grReportObject.setNationality(student.getNationality());
			grReportObject.setPlaceOfBirth(student.getPlaceOfBirth().toString());
			grReportObject.setStandardAndClassIntoWhichAdmitted(student.getAddmissionTakenInclass());
			grReportObject.setStandardIntoWhichLeftFromSchool(student.getStandard() + "-" + student.getDivision());
			objects.add(grReportObject);
		}
		return objects;
	}

	private String getFileFromURL() {
		URL url = this.getClass().getClassLoader().getResource("templates");
		return url.getFile();
	}

	/*
	 * public String generateHTML(List<Object> objects, String templateName) {
	 * ByteArrayOutputStream outputStream = null; Template template = null;
	 * String result = ""; try { VelocityEngine ve = new VelocityEngine();
	 * ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH,
	 * getFileFromURL()); ve.init(); VelocityContext context = new
	 * VelocityContext(); context.put("generatedImageText", ""); template =
	 * ve.getTemplate(templateName); context.put("students", objects);
	 * StringWriter writer = new StringWriter(); template.merge(context,
	 * writer); result = writer.toString(); } catch (Exception ex) {
	 * ex.printStackTrace(); } return result; }
	 */
	/*
	 * public ByteArrayOutputStream generatePdf(HttpServletResponse response,
	 * List<Object> objects, String templateName) { try { return
	 * generatePdf(generateHTML(objects, templateName).toString(), response); }
	 * catch (Exception ex) { ex.printStackTrace(); } return null; }
	 */
	/*
	 * private ByteArrayOutputStream generatePdf(String htmlString,
	 * HttpServletResponse response) { ByteArrayOutputStream outputStream = new
	 * ByteArrayOutputStream(); try { // step 1 Document document = new
	 * Document(); // step 2 PdfWriter writer = PdfWriter.getInstance(document,
	 * outputStream); // step 3 document.open(); // step 4 InputStream stream =
	 * new ByteArrayInputStream(htmlString.getBytes(StandardCharsets.UTF_8));
	 * XMLWorkerHelper.getInstance().parseXHtml(writer, document, stream); //
	 * step 5 document.close();
	 * 
	 * System.out.println("PDF Created!"); } catch (Exception ex) {
	 * ex.printStackTrace(); } return outputStream; }
	 */

}