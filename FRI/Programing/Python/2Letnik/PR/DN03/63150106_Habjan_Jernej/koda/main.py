import csv
from collections import defaultdict
from csv import DictReader
from random import choice
import matplotlib.pyplot as plt
import Orange
import matplotlib.pyplot as plt
import numpy as np
from collections import Counter
from pylab import *
from sklearn import metrics
from sklearn.linear_model import LinearRegression, Lasso, LogisticRegression
from sklearn.metrics import f1_score
from sklearn.metrics import mean_squared_error, accuracy_score, precision_score, recall_score
from sklearn.metrics import roc_curve, auc
from sklearn.naive_bayes import GaussianNB
from plotting import plot_fit_residual, plot_coefficients

from sklearn import preprocessing
from sklearn import utils
import matplotlib.pyplot as plt
from scipy.stats import pearsonr


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


def get_movie_users(ratings_X, table_id_index, min_count):
    movies_count = defaultdict(int)
    for line in ratings_X:
        table_id = line[table_id_index]
        # print(movie_id)
        movies_count[table_id] += 1

    movies_count_filtered = []
    for table_id, movie_count in movies_count.items():

        if movie_count >= min_count:
            movies_count_filtered.append(table_id)
    return movies_count_filtered


def openMatrix_X():
    X = []
    read_file = "matrix_X"
    with open(read_file, encoding="utf8") as f:
        reader = csv.reader(f)  ##READ CSV
        for row_count, row in enumerate(reader):
            X.append(row)
    X = np.array(X)
    return X


def _0_prepare(ratings_X):
    print("getting movies and users...")
    movies_count_filtered = get_movie_users(ratings_X, 1, 100)
    users_count_filtered = get_movie_users(ratings_X, 0, 100)

    ##reduce users and movies
    reduced_movies = []
    reduced_users = []

    max_movie_count = 700
    max_users_count = 40

    # max_movie_count = 10
    # max_users_count = 5
    for i in range(max_movie_count):
        reduced_movies.append(choice(movies_count_filtered))
        if (i < max_users_count):
            reduced_users.append(choice(users_count_filtered))
    print("hashing movies and users...")

    hashed_user_movie = {}

    for ratings_line in ratings_X:
        ratings_user_id = ratings_line[0]
        ratings_movie_id = ratings_line[1]
        ratings_rating = ratings_line[2]
        index = str(ratings_user_id) + "$" + str(ratings_movie_id)

        hashed_user_movie[index] = ratings_rating

    print("getting ratings...")

    X = []
    header = ["film/uporabnik"]
    header.extend(np.array([x for x in reduced_users]))
    # print(header)
    X.append(header)
    for movie in reduced_movies:
        vrstica = [str(movie)]
        # print(movie)
        for user in reduced_users:
            index = str(user) + "$" + str(movie)
            if index in hashed_user_movie.keys():

                vrstica.append(hashed_user_movie[index])
            else:
                vrstica.append(0)
        X.append(vrstica)
    X = np.array(X)
    print("writting to file")
    write_filename = "matrix_X"

    if not os.path.exists(os.path.dirname(write_filename)):
        try:
            os.makedirs(os.path.dirname(write_filename))
        except OSError as exc:  # Guard against race condition
            # print("ERROR CREATING DIR")
            pass
    with open(write_filename, mode='w+', encoding="utf8", newline='') as f:
        writer = csv.writer(f, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        for row in X:
            writer.writerow(row)
def odstrani_nicle(x_learn, y_learn, x_test,y_test):
    # odstranimo NIČLE!!
    temp_x_learn = []
    temp_y_learn = []
    for x_list, y_value in zip(x_learn, y_learn):
        if float(y_value) != 0.0:
            temp_x_learn.append(x_list)
            temp_y_learn.append(y_value)
    x_learn = temp_x_learn
    y_learn = temp_y_learn

    temp_x_test = []
    temp_y_test = []
    for x_list, y_value in zip(x_test, y_test):
        if float(y_value) != 0.0:
            temp_x_test.append(x_list)
            temp_y_test.append(y_value)
    x_test = temp_x_test
    y_test = temp_y_test

    x_learn = np.array(x_learn)
    y_learn = np.array(y_learn)

    x_test = np.array(x_test)
    y_test = np.array(y_test)

    return x_learn, y_learn, x_test,y_test

def _1_regression(predict_me = False):
    X = openMatrix_X()

    ##split into learn and test sets
    nrows, ncols = shape(X)

    num_cross_validations = ncols - 1
    num_cross_validations = ncols - 1
    if num_cross_validations > ncols - 1:
        num_cross_validations = ncols - 1
    if predict_me:
        num_cross_validations = 20

    ocena_vrednotenja = []
    ocena_vrednotenja_lasso = []
    for i in range(0, num_cross_validations):
        idx_learn = list(set(np.random.randint(1, nrows, size=int(nrows * 0.75))))
        idx_test = list(set([i for i in range(1, nrows)]) - set(idx_learn))

        learn_all = X[idx_learn, :]
        test_all = X[idx_test, :]
        # learn = np.array(learn) #z imenom filma kot prvi atrib
        # test = np.array(test) #z imenom filma kot prvi atrib
        learn = np.array(learn_all).astype(float)
        test = np.array(test_all).astype(float)
        learn = learn[:, 1:]
        test = test[:, 1:]



        ## izbira ciljnega atributa Y
        y_index = [i]
        if predict_me:
            y_index = [ncols-2] #last index
        x_index = list(set([i for i in range(ncols - 1)]) - set(y_index))  # x so vsi ostali indeksi

        y_learn = learn[:, y_index]
        x_learn = learn[:, x_index]

        y_test = test[:, y_index]
        x_test = test[:, x_index]

        x_learn, y_learn, x_test, y_test = odstrani_nicle(x_learn, y_learn, x_test,y_test)

        # Ucenje modela
        model = LinearRegression()
        model.fit(x_learn, y_learn)
        hx = model.predict(x_test)

        if predict_me:
            for i, vrstica in enumerate(hx):
                print(test_all[i,0], vrstica[0])


        mse = mean_squared_error(hx, y_test)
        var = np.var(hx - y_index)
        explained_var = 100.0 * (np.var(y_test) - np.var(hx - y_test)) / np.var(y_test)
        ocena_vrednotenja.append([mse, np.abs(explained_var)])

        ### LASSO ##############

        model = Lasso(alpha=0.5)  # UPORABILI LASSO REGRESIJO

        #print(x_learn)
        #print(y_learn)

        model.fit(x_learn, y_learn)
        hx = model.predict(x_test)
        #plot_coefficients(model.coef_)  ########## TODO!
        mse = mean_squared_error(hx, y_test)
        var = np.var(hx - y_index)
        explained_var = 100.0 * (np.var(y_test) - np.var(hx - y_test)) / np.var(y_test)
        ocena_vrednotenja_lasso.append([mse, np.abs(explained_var)])

    ocena_vrednotenja = np.array(ocena_vrednotenja)
    ocena_vrednotenja_lasso = np.array(ocena_vrednotenja_lasso)

    print("Ocena vrednotenja MSE je: " + str(np.mean(ocena_vrednotenja[:,0])))
    print("Ocena vrednotenja MSE Lasso je: " + str(np.mean(ocena_vrednotenja_lasso[:,0])))

    print("Ocena vrednotenja EXPLAINED VAR je: " + str(np.mean(ocena_vrednotenja[:, 1])))
    print("Ocena vrednotenja EXPLAINED VAR Lasso je: " + str(np.mean(ocena_vrednotenja_lasso[:, 1])))

    """plt.bar(range(len(ocena_vrednotenja)), np.array(ocena_vrednotenja)[:,0], align='center')
    plt.xticks(rotation=50)
    plt.xticks(range(len(ocena_vrednotenja)), X[0, 1:], size='small')
    plt.title("Ocena vrednotenja za film")
    plt.xlabel("žanri")
    plt.ylabel("število filmov določenega žanra")
    plt.show()"""

    plt.figure(1)
    plt.subplot(221)
    plt.title('MSE')
    plt.ylim(0, 5)
    plt.axhline(mean(ocena_vrednotenja[:,0]))
    plt.grid(True)

    plt.ylabel("Napaka")
    plt.plot(ocena_vrednotenja[:,0], 'bo')

    plt.subplot(222)
    plt.title('MSE Lasso')
    plt.ylim(0, 5)
    plt.axhline(mean(ocena_vrednotenja_lasso[:, 0]))
    plt.grid(True)

    plt.ylabel("Napaka")
    plt.plot(ocena_vrednotenja_lasso[:, 0], 'bo')

    plt.subplot(223)
    plt.title('Pojasnjena varianca')
    plt.axhline(mean(ocena_vrednotenja[:, 1]))
    plt.xlabel("Modeli")
    plt.ylim(0, 100)
    plt.ylabel("Napaka")
    plt.plot(ocena_vrednotenja[:,1], 'bo')

    plt.subplot(224)
    plt.title('Pojasnjena varianca Lasso (abs)')
    plt.axhline(mean(ocena_vrednotenja_lasso[:, 1]))
    plt.ylim(0, 100)
    plt.xlabel("Modeli")
    plt.ylabel("Napaka")
    plt.plot(ocena_vrednotenja_lasso[:, 1], 'bo')

    plt.show()

def _2_classification():
    def binarize(y_table):
        a = []
        for vrstica in y_table:
            #print(vrstica)
            ele = vrstica
            a.append([1]) if (ele >= 3) else a.append([0])
        return a

    X = openMatrix_X()

    ##split into learn and test sets
    nrows, ncols = shape(X)

    # num_cross_validations = ncols - 1
    num_cross_validations = ncols-1
    if num_cross_validations > ncols - 1:
        num_cross_validations = ncols - 1

    roc_array = []
    acc_array = []
    for i in range(0, num_cross_validations):
        idx_learn = list(set(np.random.randint(1, nrows, size=int(nrows * 0.75))))
        idx_test = list(set([i for i in range(1, nrows)]) - set(idx_learn))

        learn = X[idx_learn, :]
        test = X[idx_test, :]
        # learn = np.array(learn) #z imenom filma kot prvi atrib
        # test = np.array(test) #z imenom filma kot prvi atrib
        learn = np.array(learn).astype(float)
        test = np.array(test).astype(float)
        learn = learn[:, 1:]
        test = test[:, 1:]

        ## izbira ciljnega atributa Y
        y_index = [i]
        x_index = list(set([i for i in range(ncols - 1)]) - set(y_index))  # x so vsi ostali indeksi

        y_learn = np.array(learn[:, y_index]).ravel()
        x_learn = np.array(learn[:, x_index])

        y_test = np.array(test[:, y_index]).ravel()
        x_test = np.array(test[:, x_index])

        #x_learn, y_learn, x_test, y_test = odstrani_nicle(x_learn, y_learn, x_test, y_test)

        y_learn = np.array(binarize(y_learn)).ravel()
        y_test = np.array(binarize(y_test)).ravel()

        #klasifikacija
        classifier = GaussianNB()
        classifier.fit(x_learn, y_learn)
        predicted = classifier.predict(x_test)
        expected = y_test

        # summarize the fit of the model
        print(metrics.classification_report(expected, predicted))
        print(metrics.confusion_matrix(expected, predicted))

        acc_score = accuracy_score(y_test, predicted)
        prec_score = precision_score(y_test, predicted)
        rec_score = recall_score(y_test, predicted)
        f1_score_ = f1_score(y_test, predicted)

        # Compute ROC curve and ROC area for each class
        # fpr  false positive rate
        # tpr  true positive rate -> SENSITIVITY

        fpr, tpr, _ = roc_curve(y_test, predicted)
        roc_auc = auc(fpr, tpr)

        #print("false positive rate: " + str(fpr) + " true positive rate: " + str(tpr) + " roc auc: " + str(roc_auc))

        roc_array.append([fpr, tpr,roc_auc])
        acc_array.append(acc_score)

    c = Counter(X[1:, 1:].ravel())

    print(c.most_common()[0][1] / ((nrows-1) * (ncols-1)))



    roc_array = np.array(roc_array)
    roc_auc = mean(roc_array[:, 2])
    print("Mean ACC:", mean(acc_array), "Mean ROC auc: ", roc_auc)



    plt.plot(acc_array, 'bo')
    plt.title("Classification Accuracy")
    plt.ylabel("Natančnost")
    plt.xlabel("Primerki")
    plt.ylim([0,1])
    plt.axhline(mean(acc_array))
    plt.show()



    fpr = mean(roc_array[:,0])
    tpr = mean(roc_array[:,1])
    print("Mean True Positive Rate:", fpr, "Mean False Positive Rate: ", tpr)


    roc_auc = mean(roc_array[:,2])

    plt.figure()
    lw = 2
    plt.plot(fpr, tpr, color='darkorange',
             lw=lw, label='ROC curve (area = %0.2f)' % roc_auc)
    plt.plot([0, 1], [0, 1], color='navy', lw=lw, linestyle='--')
    plt.xlim([0.0, 1.0])
    plt.ylim([0.0, 1.05])
    plt.xlabel('False Positive Rate')
    plt.ylabel('True Positive Rate')
    plt.title('Klasifikacija filmov')
    plt.legend(loc="lower right")
    plt.show()



def main():
    # ratings_X, movies_X, link_X, cast_X, tags_X = fill_arrays()
    # _0_prepare(ratings_X)
    #_1_regression()
    _1_regression(True) ##predict me - bonus
    #_2_classification()


main()
