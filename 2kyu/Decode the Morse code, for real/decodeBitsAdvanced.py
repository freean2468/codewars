import sys
import re
from statistics import mean

# should estimate rate (bits per dot)

class Centroid:
    pass

# In a nutshell, K-means clustering tries to minimise the distances between the observations that belong to a cluster and maximise the distance between the different clusters.
class KMeansClustering:
#     How many clusters are?
    clusters_number: int
#     each nodes
    centroids: list[Centroid]
#     bits
    bits: list[str]
#     each clusters' centroids mean location
    cluster_centroids_locations: list[float]
    
    sample_rate: float

#     bits must be sorted
    def __init__(self, min_sample_rate: float, bits: [str], which_bit: str):
        if which_bit == '0':
            #         1(''), 3(' '), 7("   ")
            self.clusters_number = 3
            self.cluster_centroids_locations = [1, 3, 7]
            # print(
            #     f'[in KMeans init for 0] self.cluster_centroids_locations : {self.cluster_centroids_locations}')
        else:
            #             1('-'), 3('-')
            self.clusters_number = 2
            self.cluster_centroids_locations = [1, 3]
            # print(
            #     f'[in KMeans init for 1] self.cluster_centroids_locations : {self.cluster_centroids_locations}')
            
        self.bits = bits
        self.centroids = []

        for bit in bits:
            self.centroids.append(Centroid(self, bit, len(bit) / min_sample_rate))

#         print(f'bits : {bits}')

        self.calc()

    def calc(self):
        while True:
#         make sure centroids to be in some cluster.
            for centroid in self.centroids:
                centroid.observe()
                
            # print(f'self.cluster_centroids_locations : {self.cluster_centroids_locations}')

            old_cluster_centroids_locations = self.cluster_centroids_locations.copy()
#             find new location of cluster_centroids_locations since it's possible getting the mean of all the observations in each cluster.
            for cluster_number in range(self.clusters_number):
                # print(
                #     f'cluster_number : { cluster_number }, get_mean_location_from_cluster : {self.get_mean_location_from_cluster(cluster_number)}')
                self.cluster_centroids_locations[cluster_number] = self.get_mean_location_from_cluster(
                    cluster_number)

            if old_cluster_centroids_locations == self.cluster_centroids_locations:
                # print(
                #     f'[in KMeans calc for {self.clusters_number}] self.cluster_centroids_locations : {self.cluster_centroids_locations}')
                
#         make sure centroids to be in some cluster.
                for centroid in self.centroids:
                    centroid.observe()
                break

    def get_mean_location_from_cluster(self, cluster_number) -> float:
        result = [
            centroid.location for centroid in self.centroids if centroid.cluster == cluster_number]
        if len(result) == 0:
            return self.cluster_centroids_locations[cluster_number]
        else:
            return mean(result)

    def get_cluster_centroid_bit(self, bits: str) -> str:
        for centroid in self.centroids:
            if centroid.bits == bits and self.clusters_number == 3:
                # print(f'centroid.cluster : {centroid.cluster}, centroid.bits : {centroid.bits}, bits: {bits}')
                if centroid.cluster == 0:
                    return ''
                elif centroid.cluster == 1:
                    return ' '
                else:
                    return "   "
            elif centroid.bits == bits and self.clusters_number == 2:
                # print(f'centroid.cluster : {centroid.cluster}, centroid.bits : {centroid.bits}, bits: {bits}')
                if centroid.cluster == 0:
                    return '.'
                else:
                    return '-'


class Centroid:
#     Centroid position
    location: float
#     what bits centoid has?
    bits: str
#     which cluster centroid in?
    cluster: int
    parent: KMeansClustering

    def __init__(self, parent: KMeansClustering, bits: str, sampled_bits):
        self.location = sampled_bits
        self.bits = bits
        self.parent = parent
        self.observe()

    def observe(self):
#         calc distances from this centroid's bit to cluster centroid's location
        observations_to_the_cluster_centroid: [(float, int)] = []
        for index, cluster_centroid_location in enumerate(self.parent.cluster_centroids_locations):
#             print(f'cluster_centroid_location : {cluster_centroid_location}, self.location : {self.location}, abs(self.location - cluster_centroid_location): {abs(self.location - cluster_centroid_location)}')
            observations_to_the_cluster_centroid.append(
                (abs(self.location - cluster_centroid_location), index))

        minimum: (float, int) = (sys.maxsize, 0) if len(
            self.parent.cluster_centroids_locations) != 0 else (0, 0)
        for observation in observations_to_the_cluster_centroid:
            minimum = observation if observation[0] <= minimum[0] else minimum
#             minimum = observation if observation[0] < minimum[0] else minimum

#          which is the most closest cluster from this centroid
        self.cluster = minimum[1]


# if you try to estimate rate (bits per dot), it would not be 1 or 2,
# it would be about (110 + 170) / 2 / 100 = 1.4.
# Your algorithm should deal with situations like this well.

def decodeBitsAdvanced(bits: str) -> str:
    stripped = bits.strip('0')

    # print(f'stripped : { stripped }')
    
    while True :
        one_found: [str] = re.findall(r'1+', stripped)
        o_found: [str] = re.findall(r'0+', stripped)
        one_groups = dict()
        o_groups = dict()

        min_sum = 0
        min_count = 0

        min_bit_repeat = sys.maxsize

        for set in one_found:
            min_bit_repeat = len(set) if len(set) < min_bit_repeat else min_bit_repeat
            one_groups[set] = 1 if one_groups.get(
                set) == None else int(one_groups.get(set)) + 1

            if len(set) <= 2:
                min_count += 1
                min_sum += len(set)

        for set in o_found:
            min_bit_repeat = len(set) if len(set) < min_bit_repeat else min_bit_repeat
            o_groups[set] = 1 if o_groups.get(
                set) == None else int(o_groups.get(set)) + 1
        
        if min_bit_repeat == sys.maxsize:
            return ''

        if min_bit_repeat >= 2:
            stripped = stripped.replace('1'*min_bit_repeat, '1').replace('0'*min_bit_repeat, '0')
        else:
            break
    
    # print(f'min_bit_repeat : { min_bit_repeat }')
    
    if min_sum == 0:
        min_sum = 1
    
    if min_count == 0:
        min_count = 1
    
    min_sample_rate =  min_sum / min_count
        
    # print(f'min_sample_rate : {min_sample_rate}')

#  1 sequence will end up either '.' or '-'.
# But to decide which will be, it needs to be divided into two groups based on k-means Clustering

    one_keys = dict(sorted(one_groups.items()))
    one_clustering = KMeansClustering(min_sample_rate, list(one_keys.keys()), '1')

    o_keys = dict(sorted(o_groups.items()))
    o_clustering = KMeansClustering(min_sample_rate, list(o_keys.keys()), '0')

#   0 sequence will end up one of '', ' ' and "   ".
# But to decide which will be, it needs to be divide into three groups based on k-means Clustering

    one_keys_in_desc = dict(sorted(one_keys.items(), reverse=True))
    o_keys_in_desc = dict(sorted(o_keys.items(), reverse=True))

    for key in one_keys_in_desc:
        stripped = stripped.replace(
            key, one_clustering.get_cluster_centroid_bit(key))

    for key in o_keys_in_desc:
        stripped = stripped.replace(
            key, o_clustering.get_cluster_centroid_bit(key))

    return stripped


def decodeMorse(morseCode: str) -> str:
    # print(morseCode)
    if morseCode == '':
        return ''

    MORSE_CODE['_'] = ' '
    manipulated = morseCode.strip().replace('   ', ' _ ').split(' ')

    return ''.join(list(map(lambda v: MORSE_CODE[v], manipulated)))
