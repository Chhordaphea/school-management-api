### Getting Started
### Installation
* JDK 17 required for all installation methods
* Install Postgresql

<!--Steps to test API-->

=== [AuthController](JWT,Authorize,Authenticate)====
[Login] : /api/bo/v1/auth/login

==== [File Upload Controller] ====
[Upload File] : /api/bo/v1/files/upload-image

==== [Profile Controller] ====
[Get Account Info]        : /api/bo/v1/profile
[Update Account Info]  : /api/bo/v1/profile

==== [Dashboard Controller] ====
[Get Summary Data] : /api/bo/v1/summary

==== [Students Controller] ====
[Get All Students] : /api/bo/v1/students
[Create Student]   : /api/bo/v1/students
[Delete Students] (can be one or multi) : /api/bo/v1/students
[Update Student]  :  /api/bo/v1/students/{id}

==== [Teachers Controller] ====
[Get All Teachers] : /api/bo/v1/teachers
[Create Teacher]   : /api/bo/v1/teachers
[Delete Teachers] (can be one or multi) : /api/bo/v1/teachers
[Update Teacher]  :  /api/bo/v1/teachers/{id}

==== [Classes Controller] ====
[Get All Classes]  : /api/bo/v1/classes
[Create Class]      : /api/bo/v1/classes
[Get Teacher List in Class ]  : /api/bo/v1/classes/{id}/teachers
[Get Student List in Class ]  : /api/bo/v1/classes/{id}/students