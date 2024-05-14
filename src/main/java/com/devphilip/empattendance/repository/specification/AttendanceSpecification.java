package com.devphilip.empattendance.repository.specification;

import com.devphilip.empattendance.model.Attendance;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

public class AttendanceSpecification {

    public static Specification<Attendance> byDate(String date) {
        return (root, query, cb) -> cb.equal(root.get("date"), date);
    }

    public static Specification<Attendance> byMonth(String month) {
        return (root, cq, cb) -> cb.equal(root.get("month"), month);
    }

    public static Specification<Attendance> byStaffId(String staffId) {
        return (root, cq, cb) -> {
            Subquery<Attendance> attendanceSubquery = cq.subquery(Attendance.class);
            Root<Attendance> attendanceRoot = attendanceSubquery.from(Attendance.class);
            attendanceSubquery.select(attendanceRoot).where(cb.equal(root.get("employee").get("staffId"), staffId));
            return cb.exists(attendanceSubquery);
        };
    }

    public static Specification<Attendance> byDepartmentId(Long departmentId) {
        return (root, cq, cb) -> {
            Subquery<Attendance> attendanceSubquery = cq.subquery(Attendance.class);
            Root<Attendance> attendanceRoot = attendanceSubquery.from(Attendance.class);
            attendanceSubquery.select(attendanceRoot).where(cb.equal(root.get("employee").get("department").get("id"), departmentId));
            return cb.exists(attendanceSubquery);
        };
    }

    public static Specification<Attendance> byDateRange(String startDate, String endDate) {
        return (root, cq, cb) -> cb.between(root.get("date"), startDate, endDate);
    }

    public static Specification<Attendance> byStatus(String status) {
        return (root, cq, cb) -> cb.equal(root.get("status"), status);
    }
}
