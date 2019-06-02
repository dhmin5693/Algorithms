template <class T>
class vector {
private:
	int _size; // vector의 실제 크기
	int capacity; // vector의 최대 크기
	T* data;

public:
	vector() {
		_size = 0;
		capacity = 32;
		data = new T[capacity];
	}

	~vector() {
		delete[] data;
	}

	void clear() {
		size = 0;
		capacity = 32;
		delete[] data;
		data = new T[capacity];
	}

	int size() const {
		return _size;
	}

	T* begin() const {
		return &data[0];
	}

	T* end() const {
		return &data[0] + _size;
	}

	void resize(int newSize) {
		T* temp;
		temp = new T[newSize];

		for (int i = 0; i < _size; ++i) {
			temp[i] = data[i];
		}

		delete[] data;
		data = temp;
		capacity = newSize;
	}

	void push_back(T newData) {
		if (_size == capacity) {
			resize(capacity * 2);
		}
		data[_size++] = newData;
	}

	void pop_back() {
		--size;
	}

	T& operator [](int idx) {
		return data[idx];
	}

	T& operator [](int idx) const {
		return data[idx];
	}
};