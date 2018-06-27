import math
import operator
from collections import defaultdict, OrderedDict
from csv import DictReader

import Orange
import matplotlib.pyplot as plt
import numpy as np
import scipy.cluster.hierarchy as sch
from numpy import *
from scipy.stats import multivariate_normal as mvn
from sklearn.metrics import *


class KMeans:
    def __init__(self, k=10, max_iter=100):
        """
        Initialize KMeans clustering model.

        :param k
            Number of clusters.
        :param max_iter
            Maximum number of iterations.
        """

        self.k = k
        self.max_iter = max_iter

    def fit(self, X):
        """
        Fit the Kmeans model to data.

        :param X
            Numpy array of shape (n, p)
            n: number of data examples
            p: number of features (attributes)

        :return 
            labels: array of shape (n, ), cluster labels (0..k-1)
            centers: array of shape (k, p, )
        """

        n, p = X.shape
        labels = np.random.choice(range(self.k), size=n, replace=True)

        # Choose k random data points for initial centers
        centers = np.array([X[i] for i in np.random.choice(range(X.shape[0]),
                                                           size=self.k)])
        i = 0
        while i < self.max_iter:

            # Find nearest cluster
            for j, x in enumerate(X):
                ki = np.argmin(np.sum((centers - x) ** 2, axis=1))
                labels[j] = ki

            # Store previous centers
            previous_centers = centers.copy()

            # Move centroid
            for ki in range(self.k):
                centers[ki] = X[labels == ki].mean(axis=0)
            i += 1

        return labels, centers


def array_to_float(X):
    new = []
    for x in X:
        new.append([float(i) for i in x])
    return new


def isNan(x):
    if math.isnan(float(x)):
        return True
    else:
        return False


def get_mean_count_variance(ratings_X, use_improvement, min_count):
    ratings_X = array_to_float(ratings_X)
    ratings_X = sorted(ratings_X, key=operator.itemgetter(1))

    dict_id_mean = {}
    len_ratings_X = len(ratings_X)

    var_array = []

    for i in range(len_ratings_X):
        if isNan(ratings_X[i][2]):  # this checks if it isnt nan
            continue
        movie_id = ratings_X[i][1]
        rating = ratings_X[i][2]
        var_array.append(rating)

        if i + 1 < len_ratings_X and movie_id != ratings_X[i + 1][1]:
            if (use_improvement):
                if (len(var_array) >= min_count):
                    np_var_array = np.array(var_array)
                    dict_id_mean[movie_id] = [np.mean(np_var_array), len(np_var_array), np.var(np_var_array)]

            else:
                np_var_array = np.array(var_array)
                dict_id_mean[movie_id] = [np.mean(np_var_array), len(np_var_array), np.var(np_var_array)]

                var_array = []

    return dict_id_mean  # returns dict-> movieId: [mean_movie_rating, num_ratings, movie_ratings_var)


def _1_(ratings_X):
    def draw_hist(np_var_above_0, np_var_all):

        ##narišemo histogram:
        '''plt.subplot(1, 2, 1)
        plt.title("Varianca filmov z več kot 5 ocenami")
        plt.hist(np_var_above_0, normed=False, bins=12)
        plt.xlabel("Y - Varianca ocene filma")

        plt.subplot(1, 2, 2)
        '''
        plt.title("Varianca vseh filmov")
        plt.hist(np_var_all, normed=False, bins=12)
        plt.xlabel("Y - Varianca ocene filma")

        # plt.tight_layout()
        plt.show()

    def top5_perc_variance(dict_id_mean_all):
        st_varianc = len(dict_id_mean_all.keys())
        five_percent = int(st_varianc * 0.05)
        sorted_x = sorted(dict_id_mean_all.items(), key=lambda i: i[1][2], reverse=True)
        for i in range(five_percent):
            print('movieId: {:6}, rating: {:5}, num_ratings: {:3}, variance: {:6}'.format(int(sorted_x[i][0]),
                                                                                          round(sorted_x[i][1][0], 2),
                                                                                          int(sorted_x[i][1][1], ),
                                                                                          round(sorted_x[i][1][2], 4)))

    def porazdelitve(np_var_above_0, np_var_all):
        a, b = (1, 12)
        '''plt.subplot(1, 2, 1)
        sample = np_var_above_0
        n = len(sample)  # velikost vzorca

        xr = np.linspace(0, np.max(sample), 100)  # interval X
        # Ocenimo parametre iz vzorca
        mu_fit = np.mean(sample)
        var = (n - 1) / n * np.var(sample)
        print("srednja vrednost: " + str(round(mu_fit, 4)) + " varianca: " + str(round(var, 4)))
        P_fit = [mvn.pdf(x, mu_fit, var) for x in xr]
        # P_fit = [beta.pdf(x, a, b) for x in xr] ##beta

        # plt.figure()
        plt.hist(sample, label="Vzorec", normed=True)

        plt.plot(xr, P_fit, label="P(X) ocenjena", linewidth=2.0)  # ocenjena porazdelitev je model
        plt.xlabel("Varianca ocen nad 5 ocenami")
        plt.legend()

        plt.subplot(1, 2, 2)'''
        sample = np_var_all
        n = len(sample)  # velikost vzorca

        xr = np.linspace(0, np.max(sample), 100)  # interval X
        # Ocenimo parametre iz vzorca
        mu_fit = np.mean(sample)
        var = (n - 1) / n * np.var(sample)

        print("mean: " + str(round(mu_fit, 4)) + " varianca: " + str(round(var, 4)))

        # parameters = beta.fit(sample)
        # P_fit = [beta.pdf(x, *parameters) for x in xr]

        # print(parameters)
        P_fit = [mvn.pdf(x, mu_fit, var) for x in xr]
        # P_fit = [beta.pdf(x, a, b) for x in xr] ## beta

        # plt.figure()
        plt.hist(sample, label="Vzorec", normed=True)

        plt.plot(xr, P_fit, label="P(X) ocenjena", linewidth=2.0)  # ocenjena porazdelitev je model
        plt.xlabel("Varianca ocen vseh ocen")
        plt.legend()

        # plt.tight_layout()
        plt.show()

    ##CODE##

    dict_id_mean_above_0 = get_mean_count_variance(ratings_X, True, 5)  # variance ocen samo nad 5 ocen na film
    dict_id_mean_all = get_mean_count_variance(ratings_X, False, 0)  # vse variance vseh ocen

    # get var in nparray->
    np_var = []
    # for key, value in dict_id_mean_above_0.items():
    #    np_var.append(value[2])
    # np_var_above_0 = np.array(np_var)

    np_var = []
    for key, value in dict_id_mean_all.items():
        np_var.append(value[2])
    np_var_all = np.array(np_var)

    np_var_above_0 = []
    # draw_hist(np_var_above_0, np_var_all)


    ####PORAZDELITEV:
    porazdelitve(np_var_above_0, np_var_all)

    # izpiši top 5% z max varianco
    # top5_perc_variance(dict_id_mean_all)



    return


def _2_(ratings_X, movies_ax):
    def get_movie_users(ratings_X):
        ratings_X = array_to_float(ratings_X)
        ratings_X = sorted(ratings_X, key=operator.itemgetter(1))

        dict_id_mean = {}
        len_ratings_X = len(ratings_X)

        var_array = []
        user_array = []
        for i in range(len_ratings_X):
            if isNan(ratings_X[i][2]):  # this checks if it isnt nan
                continue
            movie_id = ratings_X[i][1]
            if (movie_id == 1):  ##cudn
                continue
            rating = ratings_X[i][2]
            user = ratings_X[i][0]
            var_array.append(rating)
            user_array.append(user)

            if i + 1 < len_ratings_X and movie_id != ratings_X[i + 1][1]:
                # print(max(var_array))

                temp = []
                for i in range(len(var_array)):
                    if i % 2 == 0:
                        temp.append(var_array[i])

                np_var_array = np.array(temp)

                dict_id_mean[movie_id] = [np.mean(np_var_array), len(np_var_array), user_array, np_var_array]

                var_array = user_array = []

        return dict_id_mean  ##returns movieId-> [mean, count, users, ratings]

    def draw(dict_id_mean_all, movies_ax):

        def get_best_parameters(X):

            metrics = ["cityblock", "cosine", "euclidean", "jaccard"]
            # "cityblock",  "cosine","euclidean","jaccard"
            methods = ["single", "complete", "ward"]
            # "ward","single","complete"


            arr = []
            for meth in methods:
                for metr in metrics:
                    try:
                        L = sch.linkage(X, method=meth, metric=metr)
                        for t in range(1, 100):
                            predictions = sch.fcluster(L, t=t, criterion="distance").ravel()
                            try:
                                score = silhouette_score(X, predictions, metric=metr)
                                arr.append([score, t, meth, metr])
                            except:
                                pass
                    except:
                        pass

            arr.sort(reverse=True)
            print(arr[:5])

        def linkeage(dict_id_mean_all, movies_ax):
            min_len = min(map(len, dict_id_mean_all.values()))

            X = []
            for key, value in dict_id_mean_all.items():
                X.append(value[:min_len])

            # get_best_parameters(X)
            ##BEST PARAMETERS:
            # [0.36891013390662158, 14, 'single', 'euclidean'], [0.32807192229414534, 16, 'complete', 'euclidean']


            t = 16  # izracunano
            L = sch.linkage(X, method="complete", metric="euclidean")
            labels = []
            for movie_id in dict_id_mean_all.keys():
                for row_movie in movies_ax:
                    if float(movie_id) == float(row_movie[0]):
                        try:
                            labels.append(row_movie[1])
                        except:
                            labels.append(int(movie_id))
            D = sch.dendrogram(L, labels=labels)
            plt.plot([0, 1000], [t, t], "k--")
            plt.ylabel("Razdalje")
            plt.xlabel("Filmi")
            plt.show()

        def kMeans(dict_id_mean_all):
            # Testirajte razred KMeans
            min_len = min(map(len, dict_id_mean_all.values()))
            X = []
            for key, value in dict_id_mean_all.items():
                X.append(value[:min_len])

            X = np.array(X)
            # Trenutno so gruče dodeljene naključno
            model = KMeans(k=3, max_iter=10)
            labels, centers = model.fit(X[:, :min_len])  # tle nastavu dimenzijo na 3

            plt.figure(figsize=(10, 10))
            color = {0: "red", 1: "blue", 2: "yellow"}
            for c, x in zip(labels, X):
                plt.plot(x[0], x[1], ".", color=color[c],
                         markersize=3 * x[2])  # tretja dimenzija -> večje so pike bližje so nam
            for c, x in enumerate(centers):
                plt.plot(x[0], x[1], "s", color=color[c], markersize=6 * x[2])
            plt.xlabel("Atribut 0")
            plt.ylabel("Atribut 1")
            plt.show()

        def getData(dict_id_mean_all):

            ###
            '''users = [] ####### TO JE ZA DOBIT USERJE K SO NAJVECKRAT OCENIL
            for value in dict_id_mean_all.values():
                users += value[2]
    
            dict={}
            for user in users:
                dict[user] = users.count(user)
            sorted_x = sorted(dict.items(), key=operator.itemgetter(1), reverse = True)

            with open('users_rated.txt', 'a') as the_file:
                for key, value in sorted_x:

                    the_file.write(str(key) + ";" + str(value) + "\n")
            '''  ###

            dict = {}  ##tle not mam zdj userje pod kljuce k so najveckrat ocenil
            with open('users_rated.txt') as f:
                lines = f.readlines()
                for line in lines:
                    splitted = line.split(";")
                    dict[splitted[0]] = splitted[1][:-2]

            dict1 = {}
            i = 0
            for key, value in dict.items():
                dict1[key] = value
                i += 1
            dict = dict1

            dic = defaultdict(list)
            for usr in dict.keys():
                user = float(usr)

                for key, value in dict_id_mean_all.items():
                    ##get users rating and write it in dict
                    user_arr = value[2]
                    ratings_arr = value[3]
                    mean_rating = (value[0] + np.mean(ratings_arr)) / 2.0  # mean  + mean_userja

                    for i in range(len(user_arr)):

                        if user == float(user_arr[i]):
                            try:

                                dic[key].append(ratings_arr[i])
                            except:
                                pass
                            break
                    else:
                        # print(mean_rating)
                        dic[key].append(mean_rating)

            return dic  # movie -> rating_usr1, rating_usr2...
            # movie -> rating_usr1, rating_usr2...

        ##CODE####
        X = getData(dict_id_mean_all)

        ##draw
        linkeage(X, movies_ax)
        # kMeans(X)

    def top100movies(ratings_X):
        dict_id_mean_all = get_movie_users(ratings_X)

        sorted_top100 = OrderedDict(
            sorted(dict_id_mean_all.items(), key=lambda i: i[1][1], reverse=True)[:100])  # sort watch count
        return sorted_top100

    ##CODE##
    ##get 100 top movies:
    dict_id_mean_all = top100movies(ratings_X)

    draw(dict_id_mean_all, movies_ax)

    return


def fill_arrays():
    tags_X = []
    reader = DictReader(open("source/tags.csv", "rt", encoding="utf-8"))
    for row in reader:
        user_tags = row["userId"]
        movie_tags = row["movieId"]
        tag = row["tag"]
        timestamp_tags = row["timestamp"]
        tags_X.append([user_tags, movie_tags, tag, timestamp_tags])

    ratings_X = Orange.data.Table("source/ratings.csv")
    movies_X = Orange.data.Table("source/movies.csv")
    link_X = Orange.data.Table("source/links.csv")
    cast_X = Orange.data.Table("source/cast.csv")
    #   tags_X = Orange.data.Table("source/tags.csv") -todo ---ASK CANNOT PARSE DATASET- ORANGE WHY NOT

    return ratings_X, movies_X, link_X, cast_X, tags_X


def main():
    ratings_X, movies_X, link_X, cast_X, tags_X = fill_arrays()

    _1_(ratings_X)
    '''
    1. del - iskanje osamelcev
		primer osamelca - eni uporabniki dajejo zelo dobre ocene filmom drugi pa zelo slabe ocene

		prvo pogledamo kako so ocene porazdeljene in iz porazdelive vn osamelce (ocenit katera se najbolj prilega tem podatkom)
			porazdelitve- gledamo distribucijo ocen - povprečje in varianco
		vsak film skompresiraš v številko in potem gledaš porazdelitev in potem gledat kje je varianco največja
			ta številka -> varianca
		film.ocena -> povp ocena, varianco(če je normalno porazdeljen)
		vse variance vseh filmov -> naredimo histogram -> zanima nas 5% ki je najbolj na desni -> največjo varianco

		zanima nas film z max varianco

		Vse variance v en vector in pol hist(vec)
    '''

    # _2_(ratings_X, movies_X)

    '''
    2. del - gručenje
		100 najbolj gledanih filmov -> 100 največkrat ocenjenih

		izmed teh filmov poiščeš gruče

		lahko bi gručil na podlagi vseh uporabnikov
		razdaljo med filmov bi definirali ALI
			- da primerjamo kateri je ocenil film
			- da primerjamo ocene ki so ocenili film

		da priporočamo uporabniku film glede na podlago ostalih uporabnikov

		zreducirati moramo uporabnike ali vse uporabnike
		 (ali samo tiste ki so ocenili...)


		koliko skupin je med izbranimi
		        - silhuetni score (tega dj.. kmeans je optional)
                - kmeans
                (ker jih je samo 100 lahko oba uporabimo)
            --vizualizirati in argumentirati
    '''


main()
