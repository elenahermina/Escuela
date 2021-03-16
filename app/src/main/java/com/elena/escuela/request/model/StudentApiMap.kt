package com.elena.escuela.request.model

import com.elena.escuela.student.Student

class StudentApiMap {

    companion object{
        fun mapToStudent(studentApi: StudentApi) : Student {
            val student = Student(studentApi.email, studentApi.name.name, studentApi.name.surname, null)
            return student
        }

        fun mapToStudentList(listStudentApi: List<StudentApi>) : List<Student> {
            val list = mutableListOf<Student>()
            listStudentApi.forEach {
               val student = mapToStudent(it)
                list.add(student)
            }
            return list
        }
    }
}