import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.stream.Collectors;

class MyMorseCode {
    private static final HashMap<String, String> MORSE_CODE = new HashMap<String, String>() {
        {
            put(".-", "A");
            put(".-", "A");
            put("-...", "B");
            put("-.-.", "C");
            put("-..", "D");
            put(".", "E");
            put("..-.", "F");
            put("--.", "G");
            put("....", "H");
            put("..", "I");
            put(".---", "J");
            put("-.-", "K");
            put(".-..", "L");
            put("--", "M");
            put("-.", "N");
            put("---", "O");
            put(".--.", "P");
            put("--.-", "Q");
            put(".-.", "R");
            put("...", "S");
            put("-", "T");
            put("..-", "U");
            put("...-", "V");
            put(".--", "W");
            put("-..-", "X");
            put("-.--", "Y");
            put("--..", "Z");
            put("-----", "0");
            put(".----", "1");
            put("..---", "2");
            put("...--", "3");
            put("....-", "4");
            put(".....", "5");
            put("-....", "6");
            put("--...", "7");
            put("---..", "8");
            put("----.", "9");
            put(".-.-.-", ".");
            put("--..--", ",");
            put("..--..", "?");
            put(".----.", "'");
            put("-.-.--", "!");
            put("-..-.", "/");
            put("-.--.", "(");
            put("-.--.-", ")");
            put(".-...", "&");
            put("---...", ",");
            put("-.-.-.", ";");
            put("-...-", "=");
            put(".-.-.", "+");
            put("-....-", "-");
            put("..--.-", "_");
            put(".-..-.", "\"");
            put("...-..-", "$");
            put(".--.-.", "@");
            put("...---...", "SOS");
        }
    };

    public static String get(String m) {
        return MORSE_CODE.get(m);
    }

    public static String put(String key, String value) {
        return MORSE_CODE.put(key, value);
    }
}

class Centroid {
    // Centroid position
    double sampled_bits;
    // What bits Centroid has?
    String bits;
    // Which cluster is Centroid in?
    int cluster;
    KMeansClustering parent;

    Centroid(KMeansClustering parent, String bits, double sampled_bits) {
        this.parent = parent;
        this.bits = bits;
        this.sampled_bits = sampled_bits;
    }

    public void observe() {
        // Calculate distances from this Centroid's bits to cluster centroid's
        HashMap<Double, Integer> observations = new HashMap<Double, Integer>();

        for (int i = 0; i < this.parent.cluster_centroids_locations.length; i += 1) {
            observations.put(Math.abs(this.sampled_bits - this.parent.cluster_centroids_locations[i]), i);
        }

        double min_distance = Double.MAX_VALUE;
        int min_index = 0;

        for (double distance : observations.keySet()) {
            if (distance <= min_distance) {
                min_distance = distance;
                min_index = observations.get(distance);
            }
        }

        // Which is the most closest cluster from this Centroid
        this.cluster = min_index;
    }
}

class KMeansClustering {
    // How many clusters are?
    int clusters_number;
    // Each node
    ArrayList<Centroid> centroids;
    // All Bits
    List<String> bits_groups;
    // Mean positions of centroids of clusters
    double[] cluster_centroids_locations;
    // Sample rate
    double sample_rate;

    KMeansClustering(double min_sample_rate, List<String> bits_groups, String which_bit) {
        if (which_bit == "0") {
            // 1(''), 3(' '), 7(" ")
            this.clusters_number = 3;
            this.cluster_centroids_locations = new double[] { 1f, 3f, 7f };
        } else {
            // 1('.'), 3('-')
            this.clusters_number = 2;
            this.cluster_centroids_locations = new double[] { 1f, 3f };
        }

        this.bits_groups = bits_groups;
        this.centroids = new ArrayList<Centroid>();

        for (String bits : bits_groups) {
            this.centroids.add(new Centroid(this, bits, bits.length() / min_sample_rate));
        }

        this.calc();
    }

    public String getMorseCodeByBits(String bits) {
        for (Centroid centroid : this.centroids) {
            if (centroid.bits.equals(bits) && this.clusters_number == 3) {
                switch (centroid.cluster) {
                    case 0:
                        return "";
                    case 1:
                        return " ";
                    default:
                        return "   ";
                }
            } else if (centroid.bits.equals(bits) && this.clusters_number == 2) {
                switch (centroid.cluster) {
                    case 0:
                        return ".";
                    default:
                        return "-";
                }
            }
        }

        throw new RuntimeException("Shouldn't reach here!");
    }

    private void calc() {
        while (true) {
            // Make sure Centroids to be in some cluster.
            for (Centroid centroid : this.centroids) {
                centroid.observe();
            }

            double[] old_cluster_centroids_locations = this.cluster_centroids_locations.clone();

            // Find new location of cluster_centroids_locations since it's possible getting
            // new mean position of all the observations in each cluster.
            for (int i = 0; i < this.clusters_number; i += 1) {
                this.cluster_centroids_locations[i] = this.getMeanLocationFromCluster(i);
            }

            boolean is_equal = true;

            for (int i = 0; i < this.clusters_number; i += 1) {
                if (this.cluster_centroids_locations[i] != old_cluster_centroids_locations[i]) {
                    is_equal = false;
                    break;
                }
            }

            if (is_equal) {
                // Make sure Centroids to be in some cluster.
                for (Centroid centroid : this.centroids) {
                    centroid.observe();
                }
                break;
            }
        }
    }

    private double getMeanLocationFromCluster(int cluster_number) {
        ArrayList<Double> distances = new ArrayList<Double>();

        for (Centroid centroid : this.centroids) {
            if (centroid.cluster == cluster_number) {
                distances.add(centroid.sampled_bits);
            }
        }

        if (distances.size() == 0) {
            return this.cluster_centroids_locations[cluster_number];
        } else {
            double sum = 0;
            for (double distance : distances) {
                sum += distance;
            }
            return sum / distances.size();
        }
    }

}

public class MorseCodeDecoder {

    /**
     * Given a string in Morse Code, returns the English translation.
     *
     * Accept dots, dashes and spaces, returns human-readable message.
     */
    public static String decodeMorse(String morseCode) {
        if (morseCode.equals("")) {
            return "";
        }

        MyMorseCode.put("_", " ");
        String[] manipulated = morseCode.strip().replace("   ", " _ ").split(" ");
        List<String> morse_code_list = Arrays.asList(manipulated);

        String converted = morse_code_list.stream().map(code -> MyMorseCode.get(String.valueOf(code)))
                .collect(Collectors.joining());

        String result = String.join("", converted);

        return result;
    }

    /**
     * Given a string of bits, which may or may not begin or end with '0's,
     * and which may have some variation in the length of the time unit used,
     * returns the Morse Code translation of this message.
     *
     * Accepts 0s and 1s, return dots, dashes and spaces
     *
     */
    public static String decodeBitsAdvanced(String bits) {
        // If you try to estimate rate (bits per dot), it would not be 1 or 2,
        // It would be about (110 + 170) / 2 / 100 = 1.4.
        // Your algorithm should deal with situations like this well.
        String stripped = bits.replaceAll("^0+|0+$", "");

        double sample_rate = 0;
        List<String> one_bits = new ArrayList<String>();
        List<String> o_bits = new ArrayList<String>();

        while (true) {
            // Let's figure it out how many times "1" and "0" bits sequences repeat.
            int one_minimum = MorseCodeDecoder.getBitsMinimumRepeat("1", stripped);
            int o_minimum = MorseCodeDecoder.getBitsMinimumRepeat("0", stripped);

            if (one_minimum == 0 && o_minimum == 0) {
                return "";
            } else if (one_minimum >= 2 && o_minimum >= 2) {
                int min = one_minimum < o_minimum ? one_minimum : o_minimum;
                stripped = stripped.replace(new String(new char[min]).replace("\0", "1"), "1");
                stripped = stripped.replace(new String(new char[min]).replace("\0", "0"), "0");
                continue;
            } else {
                HashSet<String> one_bits_set = new HashSet<String>();
                HashSet<String> o_bits_set = new HashSet<String>();

                Matcher one_matcher = Pattern.compile("1+").matcher(stripped);
                double sum = 0;
                double count = 0;

                while (one_matcher.find()) {
                    String group = one_matcher.group();
                    int bits_length = group.length();
                    if (bits_length <= 2) {
                        sum += bits_length;
                        count += 1;
                    }
                    one_bits_set.add(one_matcher.group());
                }

                if (sum == 0) {
                    sum = 1;
                    count = 1;
                }

                // if you have trouble discerning if the particular sequence of 1's is a dot or
                // a dash, assume it's a dot
                sample_rate = one_minimum >= 2 && o_minimum == 0 ? one_minimum : sum / count;

                Matcher o_matcher = Pattern.compile("0+").matcher(stripped);

                while (o_matcher.find()) {
                    o_bits_set.add(o_matcher.group());
                }

                one_bits.addAll(one_bits_set);
                o_bits.addAll(o_bits_set);
                break;
            }
        }

        // 1 bits will end up either '.' or '-'.
        // But to decide which will be, it needs to be divided into two groups based on
        // K-Means Clustering
        KMeansClustering one_clustering = new KMeansClustering(sample_rate, one_bits, "1");
        KMeansClustering o_clustering = new KMeansClustering(sample_rate, o_bits, "0");

        one_bits.sort(Comparator.reverseOrder());
        o_bits.sort(Comparator.reverseOrder());

        for (String key : one_bits) {
            stripped = stripped.replace(key, one_clustering.getMorseCodeByBits(key));
        }

        for (String key : o_bits) {
            stripped = stripped.replace(key, o_clustering.getMorseCodeByBits(key));
        }

        return stripped;
    }

    public static int getBitsMinimumRepeat(String which_bit, String source) {
        Matcher m = Pattern.compile(String.format(
                "%s+", which_bit))
                .matcher(source);
        int minimum = Integer.MAX_VALUE;

        while (m.find()) {
            if (m.group().length() < minimum) {
                minimum = m.group().length();
            }
        }

        if (minimum == Integer.MAX_VALUE) {
            minimum = 0;
        }

        return minimum;
    }
}