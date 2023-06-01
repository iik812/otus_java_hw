package ru.otus;

import annotation.Log;

class TestLogging implements TestLoggingInterface {

        @Log
        @Override
        public void calculation() {
            System.out.println("Null");
        }

        @Log
        @Override
        public void calculation(int param1) {
            System.out.println("Summ: " + param1);
        }

        @Log
        @Override
        public void calculation(int param1, int param2) {
            System.out.println("Summ: " + param1 + " " + param2);
        }

        @Override
        public void calculation(int param1, int param2, String param3) {
            System.out.println("Summ: " + param1 +" " + param2 +" " + Integer.parseInt(param3));
        }
    }
