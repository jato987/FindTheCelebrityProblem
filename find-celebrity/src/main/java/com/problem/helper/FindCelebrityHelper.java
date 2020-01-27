package com.problem.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.omg.CORBA.portable.ApplicationException;

import com.problem.dto.Person;

/**
 * Utility class to execute utilities to find a subject in a graph with a set
 * with n size and a list with 0 size.
 * 
 * @author developer
 *
 */
public final class FindCelebrityHelper {

	public static final String NO_CELEBRITIES = "There is no person that have the requisities of the problem";
	public static final String RESULT_FILE = "Results for file: ";
	public static final String CSV_NOTVALID = "Csv not valid";
	/**
	 * Private constructor for utility class.
	 */
	private FindCelebrityHelper() {
	}

	/**
	 * Excute task.
	 * 
	 * @param args
	 * @throws ApplicationException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ApplicationException, IOException {

		Map<String, String> readCsvs = readCsvs();

		for (Map.Entry<String, String> entryFile : readCsvs.entrySet()) {

			Map<String, Person> people = new HashMap<String, Person>();
			
			String[] relations = entryFile.getValue().split(";");
			
			System.out.println(RESULT_FILE + entryFile.getKey() + " -> ");
			
			if (validateRelation(relations)) {

				List<Person> celebritiesFound = new ArrayList<Person>();

				Arrays.stream(relations).forEach(relation -> addRelation(relation, people));

				for (Map.Entry<String, Person> entry : people.entrySet()) {
					Person findcelebrity = findcelebrity(entry.getValue(), people);
					if (findcelebrity != null) {
						celebritiesFound.add(findcelebrity);
					}
				}

				if (celebritiesFound.isEmpty()) {
					System.out.println(NO_CELEBRITIES);
				} else {
					celebritiesFound
							.forEach(person -> System.out.println("Celebrity Found with name: " + person.getName()));
				}

			} else {
				System.out.println(CSV_NOTVALID);
			}
			
			System.out.println("##################");

		}

	}

	private static Person findcelebrity(Person subject, Map<String, Person> people) {
		int knowitSize = subject.getKnowit().size();
		int knowmeSize = subject.getKnowme().size();

		if (knowitSize == 0 && knowmeSize == people.size() - 1) {
			return subject;
		}

		return null;
	}

	private static void addRelation(String relation, Map<String, Person> people) {
		String[] split = relation.split(",");
		String name1 = split[0];
		String name2 = split[1];

		if (!name1.equals(name2)) {

			Person person1 = people.get(name1);
			if (person1 == null) {
				person1 = new Person(name1);
				people.put(name1, person1);
			}
			Set<String> knowit = person1.getKnowit();
			knowit.add(name2);

			Person person2 = people.get(name2);
			if (person2 == null) {
				person2 = new Person(name2);
				people.put(name2, person2);
			}
			Set<String> knowme = person2.getKnowme();
			knowme.add(name1);
		}
	}

	private static boolean validateRelation(String[] relatios) throws ApplicationException {
		for (int i = 0; i < relatios.length; i++) {
			String[] split = relatios[i].split(",");
			if (split.length != 2) {
				return false;
			}
		}
		return true;
	}

	public static Map<String, String> readCsvs() throws IOException {
		File currentDirFile = new File(".");
		String helper = currentDirFile.getAbsolutePath();

		Map<String, String> csvs = new HashMap<String, String>();

		ClassLoader classLoader = FindCelebrityHelper.class.getClassLoader();

		try (Stream<Path> paths = Files.walk(Paths.get(helper + "/target/classes/com/problems/files"))) {
			paths.filter(Files::isRegularFile).forEach(file -> {
				Path fileName = file.getFileName();
				File e = new File(classLoader.getResource("com/problems/files/" + fileName).getFile());
				try {
					byte[] readAllBytes = Files.readAllBytes(e.toPath());
					String s = new String(readAllBytes);
					csvs.put(fileName.toString(), s);
				} catch (IOException e1) {
					csvs.put(fileName.toString(), CSV_NOTVALID);
				}
			});
		}

		return csvs;
	}
}
