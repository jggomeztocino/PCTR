#include <iostream>
#include <random>
#include <mutex>
#include <thread>
#include <vector>

std::mutex lock;
long totalHits = 0;

void MonteCarlo(long numPuntos) {
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_real_distribution<> dis(0.0, 1.0);

    long hits = 0;
    for (long i = 0; i < numPuntos; ++i) {
        double x = dis(gen);
        double y = dis(gen);

        if (y <= x * x) {
            ++hits;
        }
    }

    if(hits > 0){
        lock.lock();
        totalHits += hits;
        lock.unlock();
    }
}

int main() {
    long numPuntos;
    int numHilos;

    std::cout << "Introduce el n. de puntos por hilo: ";
    std::cin >> numPuntos;

    std::cout << "Introduce el n. de hilos: ";
    std::cin >> numHilos;

    std::vector<std::thread> hilos;
    for (int i = 0; i < numHilos; ++i) {
        hilos.push_back(std::thread(MonteCarlo, numPuntos));
    }

    for (auto& hilo : hilos) {
        hilo.join();
    }

    float area = (float)(totalHits) / (numPuntos * numHilos);
    std::cout << "Integral aproximada = " << area << "\n";

    return 0;
}
