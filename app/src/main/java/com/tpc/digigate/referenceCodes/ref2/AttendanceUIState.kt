package com.tpc.digigate.referenceCodes.ref2

data class AttendanceUIState(
    val dates: List<Date> = emptyList<Date>()
)

data class Date(
    val date: String = "",
    val students: List<Student> = emptyList<Student>()
)

data class Student(
    val name: String = "",
    val rollNo: String = ""
)
