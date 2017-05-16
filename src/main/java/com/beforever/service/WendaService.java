package com.beforever.service;

import org.springframework.stereotype.Service;

/**
 * Created by BeForever on 17/5/17.
 */
@Service
public class WendaService {
    public String getMessage(int userId) {
        return "Hello Message:" + String.valueOf(userId);
    }
}
