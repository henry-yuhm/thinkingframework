package org.thinking.sce.statemachine.dispatcher.service;

import org.springframework.statemachine.state.PseudoStateKind;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class StateKindService {
    public Map<String, String> findAll() {
        Map<String, String> map = new LinkedHashMap<>(16);

        EnumSet.allOf(PseudoStateKind.class).forEach(kind -> map.put(kind.name().toLowerCase(), kind.toString()));

        return map;
    }
}