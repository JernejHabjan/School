from collections import defaultdict
from csv import DictReader
from datetime import datetime

from pylab import *


##converting from string to float numpy ax:
# avg = ratings_ax1[:, 2].astype(float).sum() / ratings_ax1.shape[0]
# numpy_ratings_ax = np.array(ratings_ax)
# print(movies_ax[:,2]) #3. stolpec

def getDate(date_num):
    t = date_num  # Unix - time
    date = datetime.fromtimestamp(t).strftime('%Y-%m-%d')
    return date


def join(arr_a, arr_b, ind_a, ind_b):
    merged = []
    for row_a, row_b in zip(arr_a, arr_b):
        if row_a[ind_a] == row_b[ind_b]:
            del row_b[ind_b]  # zbrisemo index in ostane samo tisti pri arr_a
            merged.append(row_a + row_b)
            # else:
            #    merged.append(row_a) #LEFT JOIN???
    return merged


def mergeExample(ratings_ax, movies_ax):
    ind_rating_idMovie = 1
    ind_movie_idMovie = 0
    ind_id_rating = 2

    merged = join(ratings_ax, movies_ax, ind_rating_idMovie,
                  ind_movie_idMovie)  # ratings -> userId,movieId,rating,timestamp,  movies-> title,genres
    print(merged[0])


def getAvg(ax):
    numpy_ax = np.array(ax)
    return ax[:, 2].astype(float).mean()


def isNan(x):
    if math.isnan(float(x)):
        return True
    else:
        return False


def array_to_float(ax):
    new = []
    for x in ax:
        new.append([float(i) for i in x])
    return new


def get_mean_rating_count(ratings_ax, use_improvement, min_ratings, return_count):
    ratings_ax = array_to_float(ratings_ax)
    ratings_ax = sorted(ratings_ax, key=operator.itemgetter(1))

    dict_id_mean = {}
    len_ratings_ax = len(ratings_ax)
    vsota = 0
    count = 0
    for i in range(len_ratings_ax):
        if isNan(ratings_ax[i][2]):  # this checks if it isnt nan
            continue
        movie_id = ratings_ax[i][1]
        rating = ratings_ax[i][2]
        vsota += rating
        count += 1

        if i + 1 < len_ratings_ax and movie_id != ratings_ax[i + 1][1]:
            if use_improvement:
                if count >= min_ratings:  # - improvement -> v slovar dodajamo ratingse z min # ocenami
                    if return_count:
                        dict_id_mean[movie_id] = [round(vsota / count, 2), count]  # return tuple(avg, count)
                    else:
                        dict_id_mean[movie_id] = round(vsota / count, 2)
            else:
                if return_count:
                    dict_id_mean[movie_id] = [round(vsota / count, 2), count]  # return tuple(avg, count)
                else:
                    dict_id_mean[movie_id] = round(vsota / count, 2)
            vsota = count = 0
    return dict_id_mean


def _01_bestRatedMovies(ratings_ax, movies_ax, useImprovement, min_ratings, return_count):
    dict_id_mean = get_mean_rating_count(ratings_ax, useImprovement, min_ratings,
                                         return_count)  # poklicemo izboljsano funkcijo

    sorted_top10 = sorted(dict_id_mean.items(), key=operator.itemgetter(1), reverse=True)[:10]

    for row in sorted_top10:
        movie_id = str(row[0])
        for row_movie in movies_ax:
            if float(movie_id) == float(row_movie[0]):
                print(row_movie[1] + " - " + str(row[1]))


def _02_genres(movies_ax):
    def visualize(arr_imena, arr_num):

        plt.bar(range(len(arr_num)), arr_num, align='center')
        plt.xticks(rotation=50)
        plt.xticks(range(len(arr_num)), arr_imena, size='small')
        plt.title("Porazdelitev žanrov")
        plt.xlabel("žanri")
        plt.ylabel("število filmov določenega žanra")
        plt.show()

    genres_dict = defaultdict(int)

    for movie_row in movies_ax:
        genres = movie_row[2].split("|")

        for genre in genres:
            genres_dict[genre] += 1
    genres_dict_desc = sorted(genres_dict.items(), key=operator.itemgetter(1), reverse=True)

    arr_imena = []
    arr_num = []
    for item in genres_dict_desc:
        arr_imena.append(item[0])
        arr_num.append(item[1])
    arr_imena = arr_imena[:19]
    arr_num = arr_num[:19]

    visualize(arr_imena, arr_num)


def _03_relation(ratings_ax, use_improvement, min_ratings, return_count):
    def visualize_hist(avg_rating_movies, num_ratings_movies, avg_rating_movies_improved, num_ratings_movies_improved):

        plt.figure(figsize=(10, 4))

        plt.subplot(1, 2, 1)
        xlim([1, 5])
        plot(avg_rating_movies, num_ratings_movies, '.', color="green")
        xlabel('Povprečna ocena filma')
        ylabel('Število ocen')
        title('Relacija popularnosti z oceno - vse ocene')

        plt.subplot(1, 2, 2)
        xlim([1, 5])
        plot(avg_rating_movies_improved, num_ratings_movies_improved, '.', color="green")
        xlabel('Povprečna ocena filma')
        ylabel('Število ocen')
        title('Relacija popularnosti z oceno - nad 50 ocen')

        plt.tight_layout()
        plt.show()

    avg_rating_movies = []
    num_ratings_movies = []

    dict_id_mean_count = get_mean_rating_count(ratings_ax, use_improvement, min_ratings, return_count)
    for key, value in dict_id_mean_count.items():
        avg_rating_movies.append(value[0])
        num_ratings_movies.append(value[1])

    # visualization 3 - brez manj ocenjenih
    avg_rating_movies_improved = []
    num_ratings_movies_improved = []
    dict_id_mean_count = get_mean_rating_count(ratings_ax, True, min_ratings, return_count)
    for key, value in dict_id_mean_count.items():
        avg_rating_movies_improved.append(value[0])
        num_ratings_movies_improved.append(value[1])
    visualize_hist(avg_rating_movies, num_ratings_movies, avg_rating_movies_improved, num_ratings_movies_improved)


def _4_through_time(ratings_ax, movies_ax, movie_name1, movie_name2):
    interval = 20

    def get_movie_id(movie_ax, movie_name):
        for movie_row in movie_ax:
            if movie_row[1] == movie_name:
                return movie_row[0]

    def get_movie_ratings_timestamps(ratings_ax, movie_id):
        ratings_timestamps = []
        for rating_row in ratings_ax:
            if rating_row[1] == movie_id:
                ratings_timestamps.append((rating_row[2], rating_row[3]))
        return ratings_timestamps

    def getIntervals(ax, len_interval, use_uncomplete_interval):
        ratings_timestamps_grouped = {}  ##timestamp in avg od ocen --- tuki not storam
        interval = len_interval  # -- po tok delam intervale
        len_ratings_timestamps = len(ax)  # loopam dokler ne dosežen tega
        inserted = 0
        i = 0  # -- count
        j = 0  # -- inner count

        if len_ratings_timestamps < 2 * interval:
            print("not enough data - too small number of ratings")
            return ratings_timestamps_grouped
        while i * interval + j < len_ratings_timestamps:

            j = 0
            sum = 0
            while i * interval + j < len_ratings_timestamps and j < interval:
                index = i * interval + j
                sum += float(ax[index][0])  # pristejemo oceno

                j += 1

            if i * interval + j < len_ratings_timestamps:
                ratings_timestamps_grouped[getDate(ax[i][1])] = round(sum / j, 2)
                inserted += 1
            i += 1

        if use_uncomplete_interval:  ## pazi zgubim zadnjih par filmov ki jih je manj kot interval -- zato vrstice dol
            last_in_interval = i - 1
            left = len_ratings_timestamps - inserted  ##tok jih ne sede v 1 interval
            if (left > interval / 3):  # če jih je še kj notr omembe vredn
                i = 0
                sum = 0
                while (i < left):
                    sum += float(ax[last_in_interval + i][0])  # pristejemo oceno
                    i += 1
                ratings_timestamps_grouped[getDate(ax[i - 1][1])] = round(sum / i, 2)

        return ratings_timestamps_grouped

    def visualize(date_array1, rating_array1, date_array2, rating_array2):

        #######################
        plt.figure(figsize=(10, 4))

        plt.subplot(1, 2, 1)
        plot(range(len(rating_array1)), rating_array1, '-', color="green")
        plt.xticks(rotation=50)
        plt.xticks(range(len(rating_array1)), date_array1, size='small')
        ylim([1, 5])
        xlabel('Časovni intervali ocen')
        ylabel('Povprečna ocena filma')
        title("Pulp Fiction (1994)")

        plt.subplot(1, 2, 2)
        ylim([1, 5])
        plot(range(len(rating_array2)), rating_array2, '-', color="green")
        plt.xticks(rotation=50)
        plt.xticks(range(len(rating_array2)), date_array2, size='small')
        xlabel('Časovni intervali ocen')
        ylabel('Povprečna ocena filma')
        title("Schindler's List (1993)")

        plt.tight_layout()
        plt.show()

    def get_date_ratings(ratings_ax, movies_ax, movie_name):
        movie_id = get_movie_id(movies_ax, movie_name)

        ratings_timestamps = get_movie_ratings_timestamps(ratings_ax, movie_id)

        ratings_timestamps = array_to_float(ratings_timestamps)
        ratings_timestamps = sorted(ratings_timestamps, key=operator.itemgetter(1),
                                    reverse=True)  # sort po datumu padajoče

        # naredimo intervale:
        ratings_timestamps_grouped = getIntervals(ratings_timestamps, interval, True)  ##use uncomplete intervals
        ratings_timestamps_grouped = collections.OrderedDict(
            sorted(ratings_timestamps_grouped.items()))  ##sorted movies ascending by date

        date_array = []
        rating_array = []
        for key, value in ratings_timestamps_grouped.items():
            date_array.append(key)
            rating_array.append(value)
        return date_array, rating_array

    date_array1, rating_array1 = get_date_ratings(ratings_ax, movies_ax, movie_name1)
    date_array2, rating_array2 = get_date_ratings(ratings_ax, movies_ax, movie_name2)

    if len(date_array1 and date_array2):
        visualize(date_array1, rating_array1, date_array2, rating_array2)


def _5_popularnost_igralcev(cast_ax, ratings_ax):
    def get_actors_movies(cast_ax):
        igralci_movies = defaultdict(list)
        for vrstica in cast_ax:
            igralci = vrstica[1].split("|")
            for item_igralec in igralci:
                if item_igralec != "":  #### NEKATERI IGRALCI MAJO MISSING IMENA
                    igralci_movies[item_igralec].append(int(vrstica[0]))  # igralcu appendamo movie id
        return igralci_movies

    def get_max_rating(dict_id_mean_count):
        max_rating = 0
        for _tuple in dict_id_mean_count.values():
            rating = float(_tuple[1])
            if max_rating < rating:
                max_rating = rating
        return max_rating

    def calculate_popularnost(actor_movie_ids, dict_id_mean_count, max_rating):
        actors_weights = {}
        for actor, movies in actor_movie_ids.items():
            vsota = 0
            for movie in movies:
                if movie in dict_id_mean_count:
                    avg_ocena_filma = float(dict_id_mean_count[movie][0])
                    st_ratingov_filma = int(dict_id_mean_count[movie][1])

                    weight = st_ratingov_filma / max_rating
                    vsota += weight * avg_ocena_filma  # tist film k ma vlik ratingsov bo mev dobro utež in bo bliz 1
            actors_weights[actor] = vsota
        return actors_weights

    actor_movie_ids = get_actors_movies(cast_ax)
    # actorname -> {movie, movie} #str ->  [int int]
    dict_id_mean_count = get_mean_rating_count(ratings_ax, False, 50, True)  # vse ratingse pobere
    # movieid -> meanRating, countRatings  int -> [int, int]


    max_rating = get_max_rating(dict_id_mean_count)

    actors_weights = calculate_popularnost(actor_movie_ids, dict_id_mean_count, max_rating)

    desc_popularnost = sorted(actors_weights.items(), key=operator.itemgetter(1), reverse=True)

    ##izpis
    for i in range(10):
        print(desc_popularnost[i][0])


def fill_arrays():
    ratings_ax = []
    movies_ax = []
    link_ax = []
    cast_ax = []
    tags_ax = []

    reader = DictReader(open("source/ratings.csv", "rt", encoding="utf-8"))
    for row in reader:
        user_ratings = row["userId"]
        movie_ratings = row["movieId"]
        rating = row["rating"]
        timestamp_ratings = row["timestamp"]
        ratings_ax.append([user_ratings, movie_ratings, rating, timestamp_ratings])

    reader = DictReader(open("source/movies.csv", "rt", encoding="utf-8"))
    for row in reader:
        movie_movies = row["movieId"]
        title = row["title"]
        genres = row["genres"]
        # movies_ax.append([movie_movies, title, genres.split("|")])
        movies_ax.append([movie_movies, title, genres])

    reader = DictReader(open("source/links.csv", "rt", encoding="utf-8"))
    for row in reader:
        movie_link = row["movieId"]
        imdbId_link = row["imdbId"]
        tmdbId_link = row["tmdbId"]
        link_ax.append([movie_link, imdbId_link, tmdbId_link])

    reader = DictReader(open("source/cast.csv", "rt", encoding="utf-8"))
    for row in reader:
        movie_cast = row["movieId"]
        cast = row["cast"]
        cast_ax.append([movie_cast, cast])

    reader = DictReader(open("source/tags.csv", "rt", encoding="utf-8"))
    for row in reader:
        user_tags = row["userId"]
        movie_tags = row["movieId"]
        tag = row["tag"]
        timestamp_tags = row["timestamp"]
        tags_ax.append([user_tags, movie_tags, tag, timestamp_tags])

    return ratings_ax, movies_ax, link_ax, cast_ax, tags_ax


def loop_movies(ratings_ax, movies_ax):
    movies = {
        "Shawshank Redemption, The (1994)",
        "Godfather, The (1972)",
        "African Queen, The (1951)",
        "Godfather: Part II, The (1974)",
        "Maltese Falcon, The (1941)",
        "Usual Suspects, The (1995)",
        "Raging Bull (1980)",
        "Chinatown (1974)",
        "Rear Window (1954)",
        "12 Angry Men (1957)",
        "Schindler's List (1993)",
        "City of God (Cidade de Deus) (2002)",
        "Seven Samurai (Shichinin no samurai) (1954)",
        "North by Northwest (1959)",
        "One Flew Over the Cuckoo's Nest (1975)",
        "Fargo (1996)",
        "Pulp Fiction (1994)",
        "Psycho (1960)",
        "Dark Knight, The (2008)",
        "American Beauty (1999)"
    }

    for str in movies:
        print(str)
        _4_through_time(ratings_ax, movies_ax, str)


def main():
    ratings_ax, movies_ax, link_ax, cast_ax, tags_ax = fill_arrays()

    # _01_bestRatedMovies(ratings_ax,movies_ax, True, 50, False)
    # _02_genres(movies_ax)
    # _03_relation(ratings_ax, False, 50, True)
    # _4_through_time(ratings_ax, movies_ax, "Pulp Fiction (1994)", "Schindler's List (1993)")
    # _5_popularnost_igralcev(cast_ax, ratings_ax)


main()
