import SwiftUI
import Shared

struct ContentView: View {
    
    @State private var shouldOpenAbout = false
    
    var body: some View {
        NavigationStack{
            ArticlesView(viewModel: .init())
                .toolbar {
                    ToolbarItem {
                        Button {
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        }
                        .popover(isPresented: $shouldOpenAbout) {
//                            AboutScreen()
                            DeviceInfoView()
                        }
                    }
                }
        }
    }
}

//struct ContentView: View {
//    @ObservedObject private(set) var viewModel: ViewModel
//
//    var body: some View {
//        NavigationView { // Optional: For a title and navigation
//            VStack {
//                Text("Greetings:")
//                    .font(.headline)
//                List(viewModel.greetings, id: \.self) { greeting in
//                    Text(greeting)
//                }
//
//                DeviceInfoView()
//                ArticlesView(viewModel: .init())
//
//                Text("Products:")
//                    .font(.headline)
//                    .padding(.top)
//
//                if viewModel.isLoadingProducts {
//                    ProgressView()
//                } else if viewModel.products.isEmpty {
//                    Text("No products found.")
//                } else {
//                    List(viewModel.products, id: \.id) { product in
//                        // Assuming Product has an 'id'
//                        VStack(alignment: .leading) {
//                            Text(product.name) // Assuming Product has a 'name'
//                                .font(.title3)
//                            // Add other product details here
//                            // Text("Price: \(product.price)")
//                        }
//                    }
//                }
//            }
//            .navigationTitle("KMP App")
//            .task { // Use .task for view lifecycle-tied async operations
//                await viewModel.startObserving()
//            }
//        }
//    }
//
//}

extension ContentView {
    @MainActor
    class ViewModel: ObservableObject {
        @Published var greetings: [String] = []
        @Published var products: [Shared.Product] = [] // Use the type from your Shared module
        @Published var isLoadingProducts: Bool = false
        @Published var errorMessage: String? = nil

        private let greeting = Greeting() // Create an instance of Greeting

        func startObserving() async {
            // Observe greetings
            Task {
                do {
                    for try await phrase in greeting.greet() {
                        self.greetings.append(phrase)
                    }
                } catch {
                    // Handle errors from the greet flow if necessary
                    print("Error observing greetings: \(error)")
                    self.errorMessage = "Failed to load greetings: \(error.localizedDescription)"
                }
            }

            // Observe products
            await fetchProducts()
        }

        func fetchProducts() async {
            isLoadingProducts = true
            errorMessage = nil
            Task {
                do {
                    // The Flow<List<Product>> will emit one item: the list of products.
                    // So, we expect the loop to run once.
                    for try await productList in greeting.getProducts() {
                        self.products = productList // Assign the entire list
                        print("Swift: Fetched products: \(productList.count)")
                        break // Exit after the first emission since it's a list
                    }
                } catch {
                    print("Swift: Error fetching products: \(error)")
                    self.errorMessage = "Failed to load products: \(error.localizedDescription)"
                    self.products = [] // Clear products on error or set to an empty state
                }
                isLoadingProducts = false
            }
        }
    }
}

struct ListView: View {
    let phrases: Array<String>

    var body: some View {
        List(phrases, id: \.self) {
            Text($0)
        }
    }
}

// struct ContentView_Previews: PreviewProvider {
//     static var previews: some View {
//         ContentView(viewModel: ContentView.ViewModel())
//     }
// }
