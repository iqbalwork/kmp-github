import SwiftUI
import Shared

struct ContentView: View {
    
    @StateObject private var viewModel = MainViewModel()

    var body: some View {
        VStack(spacing: 12) {
            
            // Username input
            TextField(
                "Type github username here!",
                text: $viewModel.username
            )
            .textFieldStyle(.roundedBorder)
            
            // Search button (optional if you use it later)
            Button {
                viewModel.getUsers()
            } label: {
                Text("Search")
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.borderedProminent)
            
            // User list
            ScrollView {
                LazyVStack(spacing: 8) {
                    ForEach(viewModel.users, id: \.id) { user in
                        UserItem(user: user)
                    }
                }
            }
            
            // Error message (optional UI)
            if !viewModel.errorMessage.isEmpty {
                Text(viewModel.errorMessage)
                    .foregroundColor(.red)
                    .font(.caption)
            }
        }
        .padding(16)
        .onAppear {
            viewModel.getUsers()
        }
    }
    
//    @State private var showContent = false
//    var body: some View {
//        VStack {
//            Button("Click me!") {
//                withAnimation {
//                    showContent = !showContent
//                }
//            }
//
//            if showContent {
//                VStack(spacing: 16) {
//                    Image(systemName: "swift")
//                        .font(.system(size: 200))
//                        .foregroundColor(.accentColor)
//                    Text("SwiftUI: \(Greeting().greet())")
//                }
//                .transition(.move(edge: .top).combined(with: .opacity))
//            }
//        }
//        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
//        .padding()
//    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
