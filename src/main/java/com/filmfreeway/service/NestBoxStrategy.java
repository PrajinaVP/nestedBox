package com.filmfreeway.service;

import com.filmfreeway.model.Box;

import java.util.List;

/**
 * Strategy to nest boxes per orientation.
 *
 * @author Prajina
 */
public interface NestBoxStrategy {
    List<Box> nestBox(int childCount, int length, int width);
}
