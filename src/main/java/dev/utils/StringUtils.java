package dev.utils;

import java.time.LocalDate;

public class StringUtils {

	public static String choisirValeurString(String valeurBase, String valeurVue) {
		return valeurVue != null && !valeurVue.trim().isEmpty() ? valeurVue.trim() : valeurBase;
	}

	public static float choisirValeurFloat(float valeurBase, float valeurVue) {
		return valeurVue != valeurBase ? valeurVue : valeurBase;
	}
	
	public static int choisirValeurInt(int valeurBase, int valeurVue) {
		return valeurVue != valeurBase ? valeurVue : valeurBase;
	}

	public static LocalDate choisirValeurDate(LocalDate valeurBase, LocalDate valeurVue) {
		return valeurVue != valeurBase ? valeurVue : valeurBase;
	}
}
