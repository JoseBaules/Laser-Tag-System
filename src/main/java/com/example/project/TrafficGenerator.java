package com.example.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TrafficGenerator {

        public static List<String> getRandomKeys(List idList) {
            List<String> keys = new ArrayList<>(idList);
            List<String> randomKeys = new ArrayList<>(keys);

            //Random randomGenerator = new Random();
            //String randomElement = keys.get(rand.nextInt(keys.size()));
            /*for(int i = 0; i < keys.size(); i++){
                int index = randomGenerator.nextInt(keys.size());
                randomKeys.add(keys.get(index));
            }*/
            Collections.shuffle(keys);
            //return randomKeys;
            return keys;
        }

        public static String combineKeys(List<String> keys, String separator){
            String all = "";
            Random randomGenerator = new Random();
            //for(int i = 0; i < keys.size() - 1; i++){
            for(int i = 0; i < 1; i++){
                all = all + keys.get(randomGenerator.nextInt(keys.size())) + separator;
            }

            all += keys.get(randomGenerator.nextInt(keys.size()-1));//last element does not need separator.

            return all;
        }
    }

