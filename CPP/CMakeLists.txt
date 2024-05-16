cmake_minimum_required(VERSION 3.28)
project(CPP)

set(CMAKE_CXX_STANDARD 14)

add_executable(CPP main.cpp
        matrix.cpp
        smart_ptr.cpp
)
