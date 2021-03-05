cmake_minimum_required(VERSION 3.5)

project(lab1 LANGUAGES CXX)

set(CMAKE_INCLUDE_CURRENT_DIR ON)

set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(Qt5 COMPONENTS Core LinguistTools REQUIRED)
find_package(Qt5Widgets REQUIRED)

# set(TS_FILES lab1_en_US.ts)

add_executable(lab1
  main.cpp
  ${TS_FILES}
)

target_link_libraries(lab1 Qt5::Core Qt5::Widgets Qt5::Gui)

qt5_create_translation(QM_FILES ${CMAKE_SOURCE_DIR} ${TS_FILES})
